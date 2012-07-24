/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.manager.billing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.AccountEntry;
import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.businessobject.Bill;
import com.autentia.tnt.businessobject.BillBreakDown;
import com.autentia.tnt.businessobject.BillPayment;
import com.autentia.tnt.businessobject.BillState;
import com.autentia.tnt.businessobject.BillType;
import com.autentia.tnt.businessobject.CreditTitle;
import com.autentia.tnt.businessobject.Offer;
import com.autentia.tnt.businessobject.OfferCost;
import com.autentia.tnt.businessobject.OfferRole;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.businessobject.ProjectCost;
import com.autentia.tnt.businessobject.ProjectRole;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.ActivityDAO;
import com.autentia.tnt.dao.hibernate.BillDAO;
import com.autentia.tnt.dao.hibernate.ProjectCostDAO;
import com.autentia.tnt.dao.hibernate.ProjectDAO;
import com.autentia.tnt.dao.hibernate.ProjectRoleDAO;
import com.autentia.tnt.dao.search.ActivitySearch;
import com.autentia.tnt.dao.search.BillSearch;
import com.autentia.tnt.dao.search.ProjectCostSearch;
import com.autentia.tnt.dao.search.ProjectRoleSearch;
import com.autentia.tnt.dao.search.ProjectSearch;
import com.autentia.tnt.util.IvaApplicator;
import com.autentia.tnt.util.SpringUtils;

public class BillManager {

	public Bill convertFromOfferToBill (final Offer offer) {
		final Bill bill = new Bill();
		
		// simple values
		bill.setContact(offer.getContact());
		bill.setName(offer.getDescription());
		bill.setState(BillState.EMITTED);
		bill.setBillType(BillType.ISSUED);
		
		// concepts
		final Set<BillBreakDown> billBreakDowns = new LinkedHashSet<BillBreakDown>();
		billBreakDowns.addAll(convertFromOfferCostsToBillBreakDowns(bill, offer.getCosts()));
		billBreakDowns.addAll(convertFromOfferRolesToBillBreakDowns(bill, offer.getRoles()));
		bill.setBreakDown(billBreakDowns);
		return bill;
	}

	private Set<BillBreakDown> convertFromOfferCostsToBillBreakDowns (final Bill bill, final Set<OfferCost> offerCosts) {
		final Set<BillBreakDown> billBreakDowns = new LinkedHashSet<BillBreakDown>();
		if (offerCosts != null)
		{
			for (OfferCost cost: offerCosts) {
				if (cost.isBillable()) {
					BillBreakDown billBreakDown = new BillBreakDown();
					billBreakDown.setConcept(cost.getName());
					billBreakDown.setUnits(cost.getUnits());
					billBreakDown.setAmount(cost.getCost());
					billBreakDown.setIva(cost.getIva());
					billBreakDown.setBill(bill);
					billBreakDowns.add(billBreakDown);
				}
			}
		}
		return billBreakDowns;
	}
	
	private Set<BillBreakDown> convertFromOfferRolesToBillBreakDowns (final Bill bill, final Set<OfferRole> offerRoles) {
		final Set<BillBreakDown> billBreakDowns = new LinkedHashSet<BillBreakDown>();
		if (offerRoles != null) {
			for (OfferRole role: offerRoles) {
				BillBreakDown billBreakDown = new BillBreakDown();
				billBreakDown.setConcept(role.getName());
				billBreakDown.setUnits(new BigDecimal(role.getExpectedHours()));
				billBreakDown.setAmount(role.getCostPerHour());
				billBreakDown.setIva(role.getIva());
				billBreakDown.setBill(bill);
				billBreakDowns.add(billBreakDown);
			}
		}
		return billBreakDowns;
	}
	
	public void insertBillInCreditTitle (final Bill bill, final CreditTitle creditTitle, boolean createBillPayment) {

		// la factura cambia de estado a 'incluida en pagaré' [bugzilla:2620]
		bill.setState(BillState.IN_CREDITTITLE);
		
		// Al asociar un pagaré a una factura no debería ser obligatorio que aparezca un nuevo vencimiento en la factura [bugzilla:3016]
		if (createBillPayment) {
			// se crea un pago de dicha factura en la fecha y cantidades indicadas en el pagaré [bugzilla:2641] 
			final BillPayment payment = new BillPayment();
			payment.setBill(bill);
			payment.setAmount(creditTitle.getAmount().abs());
			payment.setExpirationDate(creditTitle.getExpirationDate());
			bill.getBillPayment().add(payment);
		}
		this.updateEntity(bill);
	}
	
	public List<BillBreakDown> getAllBitacoreBreakDowns(Date start, Date end, Project project) {

		final List<BillBreakDown> desgloses = new ArrayList<BillBreakDown>();

		ActivityDAO activityDAO = ActivityDAO.getDefault();
		ActivitySearch actSearch = new ActivitySearch();
		actSearch.setBillable(new Boolean(true));
		actSearch.setStartStartDate(start);
		actSearch.setEndStartDate(end);

		List<Activity> actividadesTotal = new ArrayList<Activity>();
		Hashtable user_roles = new Hashtable();

		ProjectRoleDAO projectRoleDAO = ProjectRoleDAO.getDefault();
		ProjectRoleSearch prjRolSearch = new ProjectRoleSearch();
		prjRolSearch.setProject(project);
		List<ProjectRole> roles = projectRoleDAO.search(prjRolSearch, new SortCriteria("id", false));
		for (ProjectRole proyRole : roles) {
			actSearch.setRole(proyRole);
			List<Activity> actividades = activityDAO.search(actSearch,
					new SortCriteria("startDate", false));
			actividadesTotal.addAll(actividades);
		}

		for (Activity act : actividadesTotal) {
			String key = act.getRole().getId().toString()
					+ act.getUser().getId().toString();

			if (!user_roles.containsKey(key)) {
				Hashtable value = new Hashtable();
				value.put("ROLE", act.getRole());
				value.put("USER", act.getUser());
				user_roles.put(key, value);
			}
		}

		Enumeration en = user_roles.keys();

		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			Hashtable pair = (Hashtable) user_roles.get(key);
			actSearch.setBillable(new Boolean(true));
			actSearch.setStartStartDate(start);
			actSearch.setEndStartDate(end);

			ProjectRole pR = (ProjectRole) pair.get("ROLE");
			User u = (User) pair.get("USER");
			actSearch.setRole(pR);
			actSearch.setUser(u);
			List<Activity> actividadesUsuarioRol = activityDAO.search(
					actSearch, new SortCriteria("startDate", false));

			BillBreakDown brd = new BillBreakDown();
			brd.setConcept("Imputaciones (usuario - rol): " + u.getName()
					+ " - " + pR.getName());
			brd.setAmount(pR.getCostPerHour());
			
			IvaApplicator.applyIvaToTaxableObject(start, brd);
			
			BigDecimal unitsTotal = new BigDecimal(0);
			for (Activity act : actividadesUsuarioRol) {
				BigDecimal unitsActual = new BigDecimal(act.getDuration());
				unitsActual = unitsActual.divide(new BigDecimal(60), 2,
						RoundingMode.HALF_UP);
				unitsTotal = unitsTotal.add(unitsActual);
			}
			brd.setUnits(unitsTotal);
			brd.setSelected(true);
			desgloses.add(brd);
		}


		ProjectCostDAO prjCostDAO = ProjectCostDAO.getDefault();
		ProjectCostSearch prjCostSearch = new ProjectCostSearch();
		prjCostSearch.setProject(project);
		List<ProjectCost> costes = prjCostDAO.search(prjCostSearch, new SortCriteria("id", false));
		for (ProjectCost proyCost : costes) {
			BillBreakDown brd = new BillBreakDown();
			brd.setConcept("Coste: " + proyCost.getName());
			brd.setUnits(new BigDecimal(1));
			brd.setAmount(proyCost.getCost());
			
			IvaApplicator.applyIvaToTaxableObject(start, brd);
			
			brd.setSelected(true);
			desgloses.add(brd);
		}
		
		return desgloses;
	  
  }
  
  /**
   * Get a list of bills between start and end dates
   * @param search
   * @param sort
   * @param start
   * @param end
   * @return
   */
  public List<Bill> getAllEntities(BillSearch search, SortCriteria sort, Date start, Date end){
	  List<Bill> res = billDAO.search( search, sort );
	  List<Bill> list = new ArrayList<Bill>();
	  Calendar calendar = Calendar.getInstance();
	  
	  // Recorremos la lista de Bills
	  for ( Bill item : res ) {
		  if (item.getExpiration() != null) {		  
			  // Metemos en el calendar la fecha del item
			  calendar.setTime( item.getExpiration() );
			  
			  // Si la fecha está dentro del rango de fechas seleccionado se añade a la lista con la fecha calculada del próximo pago 
			  if ( (calendar.getTimeInMillis() >= start.getTime()) && (calendar.getTimeInMillis() <= end.getTime()) ) {
				  // Añadimos a la lista
				  list.add( item);
			  }
		  }
	  }
	
	  return list;
  }
  
  public void calculateBillIsPaid (final Bill bill) {
	  if (bill.getState() != BillState.PAID) 
	  {
		BigDecimal accountEntryTotal = new BigDecimal(0);
		for (AccountEntry accountEntry: bill.getEntries()) {
			accountEntryTotal = accountEntryTotal.add(accountEntry.getAmount());
		}
		if (accountEntryTotal.abs().compareTo(bill.getTotal()) >= 0) {
			bill.setState(BillState.PAID);
			updateEntity(bill);
		} 
	  }
  }
  
  /**
   * Busca todos los proyectos de una organización
   * 
   * @param client es la organización por la que vamos a consultar
   * @return la lista de proyectos
   */
  public List<Project> getProjectsByOrganization(Organization client) {
	  final ProjectSearch projectSearch = new ProjectSearch();
	  projectSearch.setClient(client);
	  
	  return ProjectDAO.getDefault().search(projectSearch, new SortCriteria("name"));
  }
  
/* Bill - generated by stajanov (do not edit/delete) */


  /** Logger */
  private static final Log log = LogFactory.getLog(BillManager.class);

  /** Bill DAO **/
  private BillDAO billDAO;

  /**
   * Get default BillManager as defined in Spring's configuration file.
   * @return the default singleton BillManager
   */
  public static BillManager getDefault()
  {
    return (BillManager)SpringUtils.getSpringBean("managerBill");
  }

  /** 
   * Empty constructor needed by CGLIB (Spring AOP)
   */
  protected BillManager()
  {
  }
	
  /** 
   * Default constructor 
   * @deprecated do not construct managers alone: use Spring's declared beans
   */
  public BillManager( BillDAO billDAO )
  {
    this.billDAO = billDAO;
  }

  /**
   * List bills. 
   * @param search search filter to apply
   * @param sort sorting criteria
   * @return the list of all bills sorted by requested criterion
   */
  public List<Bill> getAllEntities(BillSearch search, SortCriteria sort){
    return billDAO.search( search, sort );
  }
  
  /**
   * Get bill by primary key.
   * @return bill selected by id.
   */
  public Bill getEntityById(int id){
    return billDAO.getById(id);	    
  }
	
  /**
   * Insert bill. 
   */
  public void insertEntity(Bill bill) {
    billDAO.insert(bill);
  }
	 
  /**
   * Update bill. 
   */
  public void updateEntity(Bill bill) {
    billDAO.update(bill);
  }

  /**
   * Delete bill. 
   */
  public void deleteEntity(Bill bill) {
    billDAO.delete(bill);
  }
/* Bill - generated by stajanov (do not edit/delete) */

	
	public List<Bill> getAllMinusCreditTitle(BillSearch search, SortCriteria criteria){		
		return billDAO.search( search, criteria );
	}
	
}
