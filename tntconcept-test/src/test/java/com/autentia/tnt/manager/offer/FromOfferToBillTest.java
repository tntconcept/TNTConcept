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

package com.autentia.tnt.manager.offer;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.autentia.tnt.businessobject.Bill;
import com.autentia.tnt.businessobject.BillState;
import com.autentia.tnt.businessobject.BillType;
import com.autentia.tnt.businessobject.Contact;
import com.autentia.tnt.businessobject.Offer;
import com.autentia.tnt.businessobject.OfferCost;
import com.autentia.tnt.businessobject.OfferPotential;
import com.autentia.tnt.businessobject.OfferRole;
import com.autentia.tnt.businessobject.OfferState;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.dao.hibernate.BillDAO;
import com.autentia.tnt.dao.hibernate.ContactDAO;
import com.autentia.tnt.dao.hibernate.OfferDAO;
import com.autentia.tnt.dao.hibernate.OrganizationDAO;
import com.autentia.tnt.dao.hibernate.ProjectDAO;
import com.autentia.tnt.manager.billing.BillManager;
import com.autentia.tnt.util.HibernateUtil;
import com.autentia.tnt.util.testing.SpringUtilsForTesting;

public class FromOfferToBillTest {

	private static SessionFactory sessionFactory;
	
	private static final String OFFER_NUMBER = "1122";
	private static final String OFFER_TITLE = "oferta genérica por prestación de servicios";
	private static final String OFFER_DESCRIPTION = "descripción genérica";
	
	private Offer offer = new Offer();
	private Contact contact = new Contact();
	private Project project = new Project();
	private Organization organization = new Organization();
	
	@BeforeClass
	public static void init() throws Exception {
		SpringUtilsForTesting.configure(new ClassPathXmlApplicationContext("applicationContext-test.xml"));
		sessionFactory = HibernateUtil.getSessionFactory();
		sessionFactory.openSession();
	}

	@Before
	public void beginTransaction() throws Exception {
		sessionFactory.getCurrentSession().beginTransaction();
	}

	@After
	public void rollbackTransaction() throws Exception {
		if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		sessionFactory.getCurrentSession().close();
	}

	/**
	 * Comprobacion de que se haga la conversion sin guardar ni tener en cuenta conceptos
	 */
	@Test
	public void testConversionWithoutConceptsAndNoSaving() {
		final BillManager billManager = (BillManager) SpringUtilsForTesting.getSpringBean("managerBill");
		insertInitialData();
		final Bill bill = billManager.convertFromOfferToBill(offer);
		
		// comprobaciones
		if (bill.getState() != BillState.EMITTED) {
			fail("el estado debe ser '" + BillState.EMITTED + "' en vez de '" + bill.getState() + "'");
		}		
		if (bill.getBillType() != BillType.ISSUED) {
			fail("el estado debe ser '" + BillType.ISSUED + "' en vez de '" + bill.getBillType() + "'");
		}
		if (!bill.getContact().equals(contact)) {
			fail("el contacto debe ser '" + contact.getName() + "' en vez de '" + bill.getContact().getName() + "'");
		}
		if (!bill.getName().equals(offer.getDescription())) {
			fail("el nombre de la factura debe ser '" + bill.getName() + "' en vez de '" + offer.getTitle() + "'");
		}
	}	

	/**
	 * Comprobacion de que se haga la conversion guardando sin conceptos
	 */
	@Test
	public void testConversionWithoutConceptsAndSaving() {
		final BillManager billManager = (BillManager) SpringUtilsForTesting.getSpringBean("managerBill");
		final BillDAO billDAO = (BillDAO) SpringUtilsForTesting.getSpringBean("daoBill");
		insertInitialData();
		Bill bill = billManager.convertFromOfferToBill(offer);
		
		// esto deberia hacerlo el usuario a traves de la página 
		bill = insertDataByUserInWeb(bill);
		billDAO.insert(bill);
		if (bill.getTotal().floatValue() != new BigDecimal(0).floatValue()) {
			fail ("es una factura sin conceptos ... el total deberia ser de 0 en vez de '" + bill.getTotal() + "'");
		}
	}	
	
	/**
	 * Comprobacion de que se haga la conversion guardando con costes materiales
	 */
	@Test
	public void testConversionWithOfferCosts() {
		final BillManager billManager = (BillManager) SpringUtilsForTesting.getSpringBean("managerBill");
		final BillDAO billDAO = (BillDAO) SpringUtilsForTesting.getSpringBean("daoBill");
		insertInitialData();
		insertOfferCosts();
		Bill bill = billManager.convertFromOfferToBill(offer);
		
		// esto deberia hacerlo el usuario a traves de la página 
		bill = insertDataByUserInWeb(bill);
		billDAO.insert(bill);
		if (bill.getTotal().floatValue() != 5800) {
			fail ("el total de la factura deberia ser de '5800', pero ha recuperado '" + bill.getTotal() + "'");
		}
	}	
	
	/**
	 * Comprobacion de que se haga la conversion guardando con costes derivados del uso de personal
	 */
	@Test
	public void testConversionWithRolesCosts() {
		final BillManager billManager = (BillManager) SpringUtilsForTesting.getSpringBean("managerBill");
		final BillDAO billDAO = (BillDAO) SpringUtilsForTesting.getSpringBean("daoBill");
		insertInitialData();
		insertOfferRoles();
		Bill bill = billManager.convertFromOfferToBill(offer);
		
		// esto deberia hacerlo el usuario a traves de la página 
		bill = insertDataByUserInWeb(bill);
		billDAO.insert(bill);
		if (bill.getTotal().floatValue() != 1218) {
			fail ("el total de la factura deberia ser de '1218', pero ha recuperado '" + bill.getTotal() + "'");
		}
	}	
	
	/**
	 * Comprobacion de que se haga la conversion guardando con todo tipo de costes
	 */
	@Test
	public void testConversionWithOfferAndRolesCosts() {
		final BillManager billManager = (BillManager) SpringUtilsForTesting.getSpringBean("managerBill");
		final BillDAO billDAO = (BillDAO) SpringUtilsForTesting.getSpringBean("daoBill");
		insertInitialData();
		insertOfferCosts();
		insertOfferRoles();
		Bill bill = billManager.convertFromOfferToBill(offer);
		
		// esto deberia hacerlo el usuario a traves de la página 
		bill = insertDataByUserInWeb(bill);
		billDAO.insert(bill);
		if (bill.getTotal().floatValue() != 7018) {
			fail ("el total de la factura deberia ser de '7018', pero ha recuperado '" + bill.getTotal() + "'");
		}
	}	
	
    /**
     * Metodo encargado de insertar datos muy generales pero que son necesarios
     * para generar facturas en base a ofertas
     */
	private void insertInitialData() {
		
		final OfferDAO offerDAO = (OfferDAO) SpringUtilsForTesting.getSpringBean("daoOffer");
		final ProjectDAO projectDAO = (ProjectDAO) SpringUtilsForTesting.getSpringBean("daoProject");
		final OrganizationDAO organizationDAO = (OrganizationDAO) SpringUtilsForTesting.getSpringBean("daoOrganization");
		final ContactDAO contactDAO = (ContactDAO) SpringUtilsForTesting.getSpringBean("daoContact");
		
		contact.setName("Sergio Hermida");
		contactDAO.insert(contact);
		projectDAO.insert(project);
		organizationDAO.insert(organization);
		
		offer.setNumber(OFFER_NUMBER);
		offer.setOrganization(organization);
		offer.setContact(contact);
		offer.setTitle(OFFER_TITLE);
		offer.setDescription(OFFER_DESCRIPTION);
		offer.setOfferPotential(OfferPotential.MEDIUM);
		offer.setOfferState(OfferState.OPEN);
		offer.setCreationDate(new Date());
		offerDAO.insert(offer);
	}	
	
	/**
	 * Inserta costes materiales en la ofertas
	 */
	private void insertOfferCosts() {
		final Set<OfferCost> offerCosts = new LinkedHashSet<OfferCost>(2);
		
		final OfferCost paneles = new OfferCost();
		paneles.setBillable(true);
		paneles.setCost(new BigDecimal(1000));
		paneles.setIva(new BigDecimal(16));
		paneles.setName("paneles");
		paneles.setUnits(new BigDecimal(5));
		paneles.setOffer(offer);
		
		final OfferCost cristales = new OfferCost();
		cristales.setBillable(false);
		cristales.setCost(new BigDecimal(10));
		cristales.setIva(new BigDecimal(16));
		cristales.setName("paneles");
		cristales.setUnits(new BigDecimal(1));
		cristales.setOffer(offer);
		
		offerCosts.add(paneles);
		offerCosts.add(cristales);
		offer.setCosts(offerCosts);
	}
	
	/**
	 * Inserta costes imputables a personal en la ofertas
	 */
	private void insertOfferRoles() {
		final Set<OfferRole> offerRoles = new LinkedHashSet<OfferRole>(2);
		
		final OfferRole chapista = new OfferRole();
		chapista.setCostPerHour(new BigDecimal(30));
		chapista.setExpectedHours(10);
		chapista.setIva(new BigDecimal(16));
		chapista.setName("chapista");
		chapista.setOffer(offer);

		final OfferRole pintor = new OfferRole();
		pintor.setCostPerHour(new BigDecimal(25));
		pintor.setExpectedHours(30);
		pintor.setIva(new BigDecimal(16));
		pintor.setName("pintor");
		pintor.setOffer(offer);
		
		offerRoles.add(chapista);
		offerRoles.add(pintor);
		offer.setRoles(offerRoles);
	}
	/**
	 * Insercion de los datos que faltan en la factura. Esta insercion debe hacerla
	 * el usuario a traves de la aplicacion
	 */
	private Bill insertDataByUserInWeb(final Bill bill) {
		bill.setNumber("11222");
		bill.setOrderNumber("22211");
		bill.setCreationDate(new Date());
		bill.setStartBillDate(new Date());
		bill.setEndBillDate(new Date());
		bill.setProject(project);
		return bill;
	}
}
