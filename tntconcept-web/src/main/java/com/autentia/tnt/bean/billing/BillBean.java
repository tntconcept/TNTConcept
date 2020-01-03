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

package com.autentia.tnt.bean.billing;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.manager.billing.IVATypeManager;
import com.autentia.tnt.manager.billing.IVAReasonManager;
import org.acegisecurity.acls.domain.BasePermission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.bean.NavigationResults;
import com.autentia.tnt.bean.account.AccountEntryBean;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BillSearch;
import com.autentia.tnt.dao.search.ContactSearch;
import com.autentia.tnt.manager.account.AccountEntryManager;
import com.autentia.tnt.manager.account.AccountManager;
import com.autentia.tnt.manager.admin.ProjectManager;
import com.autentia.tnt.manager.billing.BillBreakDownManager;
import com.autentia.tnt.manager.billing.BillManager;
import com.autentia.tnt.manager.contacts.ContactManager;
import com.autentia.tnt.manager.contacts.OrganizationManager;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.upload.Uploader;
import com.autentia.tnt.upload.UploaderFactory;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.FileUtil;
import com.autentia.tnt.util.IvaApplicator;
import com.autentia.tnt.util.SpringUtils;
import sun.tools.java.BinaryAttribute;

/**
 * UI bean for Bill objects.
 * 
 * @author stajanov code generator
 */
public class BillBean extends BaseBean {

	private int year = Calendar.getInstance().get(Calendar.YEAR);
	
	private boolean CREATE_BILL_PAYMENT = true;

	private boolean readOnlyBill;
	
	private boolean DONT_CREATE_BILL_PAYMENT = false;
	
	private static int ALL_YEARS = 0;

	private List<SelectItem> exemptReasons;
	private List<SelectItem> noExemptReasons;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public static int getMaximumYears() {
		return ConfigurationUtil.getDefault().getAccountEntryMaximumYears();
	}

	public List<SelectItem> getYears() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		FacesContext context = FacesContext.getCurrentInstance();
		Locale locale = context.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(
				"com.autentia.tnt.resources.messages", locale);

		Date fecha = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		int actual = cal.get(Calendar.YEAR);
		ret.add(new SelectItem(Integer.valueOf(actual), bundle
				.getString("bill.fiscalYear.now")));
		ret.add(new SelectItem(Integer.valueOf(ALL_YEARS), bundle
				.getString("bill.fiscalYear.all")));

		for (int i = 1; i < getMaximumYears(); i++) {
			ret.add(new SelectItem(Integer.valueOf(actual - i), "" + (actual - i)));
		}

		return ret;
	}

	public List<SelectItem> getIVAReasons(BigDecimal iva) {
		loadIVAReasons();

		if (iva.compareTo(BigDecimal.ZERO) == 0) {
			return this.exemptReasons;
		}

		return this.noExemptReasons;
	}

	private void loadIVAReasons() {
		List<IVAReason> refs = IVAReasonManager.getDefault().getAllEntities(new SortCriteria("id"));

		exemptReasons = new ArrayList<>();
		noExemptReasons = new ArrayList<>();

		for (IVAReason ref : refs) {

			SelectItem selectItem = new SelectItem(ref, ref.getCode() + " - " + ref.getReason());

			if (ref.isExempt()) {
				this.exemptReasons.add(selectItem);
			} else {
				this.noExemptReasons.add(selectItem);
			}
		}
	}

	public Organization getProvider() {
	    return bill.getProvider();
	  }
	  public void setProvider( Organization provider ) {
	    bill.setProvider( provider );
	  }
	
	 public Organization getSearchProvider(){
	        return search.getProvider();
	    }
	    public void setSearchProvider( Organization val ){
	        if(search.isProviderSet()) {
	          search.setProvider( val );
	        }
	    }
	    public boolean isSearchProviderValid(){
	        return search.isProviderSet();
	    }
	    public void setSearchProviderValid( boolean val ){
	        if( val ){
	          search.setProvider( search.getProvider() );
	        } else {
	          search.unsetProvider();
	        }
	    }
	
	/**
	   * Get the list of all providers
	   * @return the list of all providers
	   */
	  public List<SelectItem> getProviders(){  
	  		
	  	  List<Organization> refs = OrganizationManager.getDefault().getProvidersAndClients();		  
		  
		  ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
	      
	      for( Organization ref : refs ){
	          ret.add( new SelectItem( ref, ref.getName() ) );
	      }
	      return ret;	
	        
	  }
	
	
	public boolean getPuedoBuscarBitacore() {
		return (bill.getBillType() == BillType.ISSUED)
		&& (bill.getId() != null);
	}
	
	public String searchInBitacore() {
		if (bill.getId() == null)
			return save();

		return NavigationResults.BILL_SEARCH_IN_BITACORE;
	}

	public List<SelectItem> getAccounts() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		
		List<Account> refs = AccountManager.getDefault().getAllEntities(null,new SortCriteria("name"));
		for (Account ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}
		return ret;
	}

	BillType selectedType = BillType.ISSUED;

	private CreditTitle creditTitle = new CreditTitle();
	
	
	
	public CreditTitle getCreditTitle() {
		return creditTitle;
	}

	public void setCreditTitle(CreditTitle creditTitle) {
		this.creditTitle = creditTitle;
	}

	/**
	 * Get the list of all BillType values
	 * 
	 * @return the list of all BillType values
	 */
	public List<SelectItem> getBillTypes() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		BillType[] vals = BillType.values();
		for (BillType val : vals) {
			ret.add(new SelectItem(val, FacesUtils.formatMessage("BillType."
					+ val.name())));
		}
		return ret;
	}
	
	public Contact getContact() {
		return bill.getContact();
	}

	public void setContact(Contact contact) {
		bill.setContact(contact);
	}

	public BillType getBillType() {
		return bill.getBillType();
	}

	public void setBillType(BillType billType) {
		bill.setBillType(billType);
	}

	public String getOrderNumber() {
		return bill.getOrderNumber();
	}

	public void setOrderNumber(String orderNumber) {
		bill.setOrderNumber(orderNumber);
	}

	public Contact getSearchContact() {
		return search.getContact();
	}

	public void setSearchContact(Contact val) {
		if (search.isContactSet()) {
			search.setContact(val);
		}
	}

	public boolean isSearchContactValid() {
		return search.isContactSet();
	}

	public void setSearchContactValid(boolean val) {
		if (val) {
			search.setContact(search.getContact());
		} else {
			search.unsetContact();
		}
	}

	/**
	 * Get the list of all contacts
	 * 
	 * @return the list of all contacts
	 */
	public List<SelectItem> getContacts() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<Contact> refs = ContactManager.getDefault().getAllEntities(null,new SortCriteria("name"));
		for (Contact ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}
		return ret;
	}

	public String getSearchOrderNumber() {
		return search.getOrderNumber();
	}

	public void setSearchOrderNumber(String val) {
		if (search.isOrderNumberSet()) {
			search.setOrderNumber(val);
		}
	}

	public boolean isSearchOrderNumberValid() {
		return search.isOrderNumberSet();
	}

	public void setSearchOrderNumberValid(boolean val) {
		if (val) {
			search.setOrderNumber(search.getOrderNumber());
		} else {
			search.unsetOrderNumber();
		}
	}

	/** Serial version field */
	private static final long serialVersionUID = -1L;

	

	private BigDecimal totals;
	
	private BigDecimal totalsNoTaxes;
	
	private BigDecimal totalsTaxes;
	
	private BigDecimal totalsUnpaid;

	public BigDecimal getTotals() {
		return totals;
	}

	public void setTotals(BigDecimal total) {
		this.totals = total;
	}

	public BigDecimal getTotal() {
		return bill.getTotal();
	}
	
	public BigDecimal getBillPaymentTotal() {
		return bill.getBillPaymentTotal();
	}

	public BigDecimal getTotalNoTaxes() {
		return bill.getTotalNoTaxes();
	}
	
	// reloads the page to update the total of the bill
	public String reloadTotal() {
		return null;
	}
	
	public BigDecimal getTotalsNoTaxes() {
		return totalsNoTaxes;
	}
	public void setTotalsNoTaxes(BigDecimal totalsNoTaxes) {
		this.totalsNoTaxes = totalsNoTaxes;
	}
		

	public BigDecimal getTotalsTaxes() {
		return totalsTaxes;
	}
	public void setTotalsTaxes(BigDecimal totalsTaxes) {
		this.totalsTaxes = totalsTaxes;
	}

	public BigDecimal getTotalsUnpaid() {
		return totalsUnpaid;
	}
	
	public void setTotalsUnpaid(BigDecimal totalsUnpaid) {
		this.totalsUnpaid = totalsUnpaid;
	}
	
	private void calcTotals(List<Bill> res) {

		BigDecimal totals = new BigDecimal(0);
		BigDecimal totalsNoTaxes = new BigDecimal(0);
		BigDecimal totalsUnpaid = new BigDecimal(0);
		for (Bill elem : res) {
			totals = totals.add(elem.getTotal());
			totalsNoTaxes = totalsNoTaxes.add(elem.getTotalNoTaxes());
			totalsUnpaid = totalsUnpaid.add(elem.getUnpaid());
		}

		setTotals(totals);
		setTotalsNoTaxes(totalsNoTaxes);
		setTotalsTaxes(totals.subtract(totalsNoTaxes));
		setTotalsUnpaid(totalsUnpaid);
	}

	/** Selected organization * */
	private Organization selectedOrganization = null;
	private Integer idSelectedOrganization = 0;

	public Organization getSelectedOrganization() {

		if (bill != null && bill.getProject() != null) {
			selectedOrganization = bill.getProject().getClient();
			return selectedOrganization;
		}

		if (selectedOrganization == null) {
			selectedOrganization = OrganizationManager.getDefault().getMyOrganization();
		}
		
		return selectedOrganization;
	}

	public void setSelectedOrganization(Organization organization) {
		
		selectedOrganization = organization;
		if(bill!=null) {
			Project project = bill.getProject();
		
			if (project != null) {
				project.setClient(organization);
			}
		}
	}

	public Integer getIdSelectedOrganization() {

		idSelectedOrganization = ConfigurationUtil.getDefault().getIdOurCompany();
		return idSelectedOrganization;
	}

	public void setIdSelectedOrganization(Integer idOrganization) {
		idSelectedOrganization = idOrganization;
	}

	public List<SelectItem> getProjectsBySelectedOrganization() {

		List<Project> refs = ProjectManager.getDefault().getProjectsByOrganization(selectedOrganization); 
			
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		ret.add(new SelectItem(null, FacesUtils.getMessage("bill.allProjects")));
		
		for (Object ref : refs) {
			Project proy = (Project) ref;
			String asterisco = proy.isFinished() ? "(*)" : "";
			ret.add(new SelectItem(proy, proy.getName() + asterisco));
		}

		return ret;
	}

	public List<SelectItem> getContactsBySelectedOrganization() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		
	  ContactSearch cs = new ContactSearch();
	  cs.setOrganization(getSelectedOrganization());
		List<Contact> refs = ContactManager.getDefault().getAllEntities(cs,new SortCriteria("name"));
		for (Contact ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}

		//	Añadiendo el campo vacio
		ret.add(0, new SelectItem(""));
				
		return ret;
	}

	public List<SelectItem> getProjectsOpenBySelectedOrganization() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<Project> refs = null;
		if(bill.getId()!=null){
			refs = ProjectManager.getDefault().getProjectsByOrganization(selectedOrganization);
		}else{
			refs = ProjectManager.getDefault().getOpenProjectsByOrganization(selectedOrganization);
		}
		for (Project proy : refs) {
			String asterisco = proy.isFinished() ? "(*)" : "";
			ret.add(new SelectItem(proy, proy.getName() + asterisco));
		}
		
		return ret;
	}
	
	public void onSelectedOrganizationChanged(ValueChangeEvent event) {
		setSelectedOrganization((Organization) event.getNewValue());

		FacesUtils.renderResponse();
	}

	public void onSelectedOrganizationSearchChanged(ValueChangeEvent event) {
		Organization now = (Organization) event.getNewValue();
		if (now == null)
			return;
		setSearchProjectValid(true);
		setSelectedOrganization(now);
		FacesUtils.renderResponse();
	}

	public void onEditSelected(ActionEvent event) {
		setSelectedOrganization(null);
	}

	/**
	 * Get the list of all projects
	 * 
	 * @return the list of all projects
	 */
	public List<SelectItem> getOpenprojects() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();

		List<Project> refs = ProjectManager.getDefault().getAllEntities(null,new SortCriteria("name"));
		for (Project proy : refs) {
			ret.add(new SelectItem(proy, proy.getName()));
		}
		return ret;
	}

	public String unselect() {
		int idEntry = Integer.parseInt(FacesUtils
				.getRequestParameter("idEntry"));
		
		int id = bill.getId();
		bill.getEntries().remove(AccountEntryManager.getDefault().getEntityById(idEntry));
		
		save();

		bill = manager.getEntityById(id);
		return NavigationResults.EDIT;
	}

	/**
	 * Get the list of all categoriass
	 * 
	 * @return the list of all categoriass
	 */
	public List<SelectItem> getEntriess() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<AccountEntry> refs = AccountEntryManager.getDefault().getAllEntities(null,new SortCriteria("concept")); 
			
		for (AccountEntry ref : refs) {
			ret.add(new SelectItem(ref, ref.getConcept()));
		}
		return ret;
	}

	/**
	 * Get the list of all projects
	 * 
	 * @return the list of all projects
	 */
	public List<SelectItem> getProjects() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<Project> refs = ProjectManager.getDefault().getAllEntities(null,new SortCriteria("name")); 
			
		for (Project ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}
		return ret;
	}

	public Set<AccountEntry> getEntries() {
		return bill.getEntries();
	}

	public void setEntries(Set<AccountEntry> entries) {
		bill.setEntries(entries);
	}

	public String searchEntries() {
		AccountEntryBean bean = (AccountEntryBean) FacesUtils
				.getBean("accountEntryBean");
		bean.setBill(bill);
		setBill(bill);
		return NavigationResults.BILL_SEARCH_ENTRIES;
	}

	/**
	 * Get the list of all organizations
	 * 
	 * @return the list of all organizations
	 */
	public List<SelectItem> getOrganizations() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<Organization> refs = OrganizationManager.getDefault().getAllEntities(null,new SortCriteria("name"));
			
		for (Organization ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}
		return ret;
	}

	/* bill - generated by stajanov (do not edit/delete) */

	/** Logger */
	private static final Log log = LogFactory.getLog(BillBean.class);

		

	/** Upload service */
	private static final Uploader uploader = UploaderFactory
			.getInstance("bill");

	/** Temporary file field */
	private String oldFile;

	/** Uploaded file object */
	private UploadedFile uploadFile;

	/** Active Bill object */
	private Bill bill;
	
	/** Active search object */
	 private BillSearch search = new BillSearch();
	  
	/** Manager */
	private static BillManager manager = BillManager.getDefault();
	
	/** Quick search letter for ABC pager control */
    private Character letter;
    
	/** Default sort column */
	private String sortColumn = "creationDate";

	/** Default sort order */
	private boolean sortAscending = false;
	
	
	/** Handle an ABC pager letter click: filter objects by specified starting letter */
    public void letterClicked()
    {
      if( letter!=null ){
      	UIComponent comp = FacesUtils.getComponent("bills:list");
        HtmlDataTable tabla = (HtmlDataTable) comp;
        tabla.setFirst(0);
      	
        search.setName( letter+"%" );
      } else {
        search.unsetName();
      }
    }

    public Character getLetter()
    {
      return letter;
    }

    public void setLetter( Character letter )
    {
      this.letter = letter;
      
    }
	
	
	/**
	 * List bills. Order depends on Faces parameter sort.
	 * 
	 * @return the list of all bills sorted by requested criterion
	 */
	public List<Bill> getAll() {
		
		if( year==ALL_YEARS ){
			search.unsetYear();
		} else {
			search.setYear(year);
		}
		search.setBillType(getSelectedType());
		search.unsetCreditTitle();
		
	    //Organización seleccionada en la búsqueda
		search.setProjects(manager.getProjectsByOrganization(selectedOrganization));
		
		List<Bill> res = null;

		res = manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending)); 
			

		calcTotals(res);

		return res;
	}

	
	
	// Getters to list possible values of related entities

	// Getters to list possible values of enum fields

	// Getters to list possible values of enum fields

	public String getPaymentModeFormatted() {
		return FacesUtils.formatMessage("BillPaymentMode."
				+ this.getPaymentMode().name());
	}

	/**
	 * Get the list of all PaymentMode values
	 * 
	 * @return the list of all PaymentMode values
	 */
	public List<SelectItem> getPaymentModes() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		BillPaymentMode[] vals = BillPaymentMode.values();
		for (BillPaymentMode val : vals) {
			ret.add(new SelectItem(val, FacesUtils
					.formatMessage("BillPaymentMode." + val.name())));
		}
		return ret;
	}

	public String getStateFormatted() {
		return FacesUtils.formatMessage("BillState." + this.getState().name());
	}

	/**
	 * Get the list of all State values
	 * 
	 * @return the list of all State values
	 */
	public List<SelectItem> getStates() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		BillState[] vals = BillState.values();
		for (BillState val : vals) {
			ret.add(new SelectItem(val, FacesUtils.formatMessage("BillState."
					+ val.name())));
		}
		return ret;
	}

	// Methods to create/remove instances of one-to-many entities (slave
	// entities)

	/**
	 * Go to create page
	 * 
	 * @return forward to CREATE page
	 */
	public String create() {
		bill = new Bill();
	    bill.setCreationDate(new Date());
		bill.setStartBillDate(new Date());
		bill.setEndBillDate(new Date());
		bill.setBillType(getSelectedType());
		bill.setSubmitted(0);
		selectedOrganization = null;
		return NavigationResults.CREATE;
	}

	/**
	 * Go to edit page
	 * 
	 * @return forward to EDIT page
	 */
	public String edit() {
		Integer id = Integer.parseInt( FacesUtils.getRequestParameter(ROW_ID) );
	      bill = manager.getEntityById(id);
			
		return NavigationResults.EDIT;
	}

	public String returnToEdit() {
		BillManager.getDefault().calculateBillIsPaid(bill);
		return edit();
	}
	
	
	
	public List<Bill> getAllMinusCreditTitle() {

		if( year==ALL_YEARS ){
			search.unsetYear();
		} else {
			search.setYear(year);
		}
		
		search.setCreditTitle(creditTitle);
		search.setBillType(selectedType);
		return manager.getAllMinusCreditTitle(search, new SortCriteria(sortColumn,
				sortAscending));
	}
	
	
	/**
	 * Save bean and stay on it
	 * 
	 * @return forward to list page
	 */
	public String save() {
		
		if( bill.getId()==null )
		{
			manager.insertEntity(bill);
		} 
		else 
		{
			manager.updateEntity(bill);
		}

		// Handle uploads for file field
		if (uploadFile != null) {
			try {
				uploader.replace(Integer.toString(bill.getId()), oldFile, uploadFile);
			} catch (IOException e) {
				log.error("save - exception uploading field file", e);
				FacesUtils.addErrorMessage("file", "error.fileTransfer", e.getMessage());
			}
		}

		//FacesUtils.addInfoMessage("billType", "bill.saveBillOK");
		return NavigationResults.EDIT;
	}

	/**
	 * Delete bean and go back to beans list
	 * 
	 * @return forward to LIST page
	 */
	public String delete() {
		manager.deleteEntity(bill);
		bill = null;
		return NavigationResults.LIST;
	}

	/**
	 * Go back to beans list
	 * 
	 * @return forward to LIST page
	 */
	public String list() {
		return NavigationResults.LIST;
	}

	/**
	 * Go to search page
	 * 
	 * @return forward to SEARCH page
	 */
	public String search() {
		return NavigationResults.SEARCH;		
	}

	/**
	 * Check if we have an active object.
	 * 
	 * @return true is an object is selected
	 */
	public boolean isBillSelected() {
		return bill != null;
	}

	// Getters and setters to manipulate sorting
	public boolean isSortAscending() {
		return sortAscending;
	}

	public void setSortAscending(boolean sortAscending) {
		this.sortAscending = sortAscending;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	// Getters and setters to handle search

	public Date getSearchStartCreationDate() {
		return search.getStartCreationDate();
	}

	public void setSearchStartCreationDate(Date val) {
		if (val != null) {
			search.setStartCreationDate(val);
		} else {
			search.unsetStartCreationDate();
		}
	}

	public boolean isSearchStartCreationDateValid() {
		return search.isStartCreationDateSet();
	}

	public void setSearchStartCreationDateValid(boolean val) {
		if (val) {
			search.setStartCreationDate(search.getStartCreationDate());
		} else {
			search.unsetStartCreationDate();
		}
	}

	public Date getSearchEndCreationDate() {
		return search.getEndCreationDate();
	}

	public void setSearchEndCreationDate(Date val) {
		if (val != null) {
			search.setEndCreationDate(val);
		} else {
			search.unsetEndCreationDate();
		}
	}

	public boolean isSearchEndCreationDateValid() {
		return search.isEndCreationDateSet();
	}

	public void setSearchEndCreationDateValid(boolean val) {
		if (val) {
			search.setEndCreationDate(search.getEndCreationDate());
		} else {
			search.unsetEndCreationDate();
		}
	}

	public Date getSearchStartExpiration() {
		return search.getStartExpiration();
	}

	public void setSearchStartExpiration(Date val) {
		if (val != null) {
			search.setStartExpiration(val);
		} else {
			search.unsetStartExpiration();
		}
	}

	public boolean isSearchStartExpirationValid() {
		return search.isStartExpirationSet();
	}

	public void setSearchStartExpirationValid(boolean val) {
		if (val) {
			search.setStartExpiration(search.getStartExpiration());
		} else {
			search.unsetStartExpiration();
		}
	}

	public Date getSearchEndExpiration() {
		return search.getEndExpiration();
	}

	public void setSearchEndExpiration(Date val) {
		if (val != null) {
			search.setEndExpiration(val);
		} else {
			search.unsetEndExpiration();
		}
	}

	public boolean isSearchEndExpirationValid() {
		return search.isEndExpirationSet();
	}

	public void setSearchEndExpirationValid(boolean val) {
		if (val) {
			search.setEndExpiration(search.getEndExpiration());
		} else {
			search.unsetEndExpiration();
		}
	}

	public BillPaymentMode getSearchPaymentMode() {
		return search.getPaymentMode();
	}

	public void setSearchPaymentMode(BillPaymentMode val) {
		if (search.isPaymentModeSet()) {
			search.setPaymentMode(val);
		}
	}

	public boolean isSearchPaymentModeValid() {
		return search.isPaymentModeSet();
	}

	public void setSearchPaymentModeValid(boolean val) {
		if (val) {
			search.setPaymentMode(search.getPaymentMode());
		} else {
			search.unsetPaymentMode();
		}
	}

	public BillState getSearchState() {
		return search.getState();
	}

	public void setSearchState(BillState val) {
		if (search.isStateSet()) {
			search.setState(val);
		}
	}

	public boolean isSearchStateValid() {
		return search.isStateSet();
	}

	public void setSearchStateValid(boolean val) {
		if (val) {
			search.setState(search.getState());
		} else {
			search.unsetState();
		}
	}

	

	public String getSearchNumber() {
		return search.getNumber();
	}

	public void setSearchNumber(String val) {
		if (search.isNumberSet()) {
			search.setNumber(val);
		}
	}

	public boolean isSearchNumberValid() {
		return search.isNumberSet();
	}

	public void setSearchNumberValid(boolean val) {
		if (val) {
			search.setNumber(search.getNumber());
		} else {
			search.unsetNumber();
		}
	}

	public String getSearchBookNumber() {
		return search.getBookNumber();
	}

	public void setSearchBookNumber(String val) {
		if (search.isBookNumberSet()) {
			search.setBookNumber(val);
		}
	}

	public boolean isSearchBookNumberValid() {
		return search.isBookNumberSet();
	}

	public void setSearchBookNumberValid(boolean val) {
		if (val) {
			search.setBookNumber(search.getBookNumber());
		} else {
			search.unsetBookNumber();
		}
	}
	
	public String getSearchName() {
		return search.getName();
	}

	public void setSearchName(String val) {
		if (search.isNameSet()) {
			search.setName(val);
		}
	}

	public boolean isSearchNameValid() {
		return search.isNameSet();
	}

	public void setSearchNameValid(boolean val) {
		if (val) {
			search.setName(search.getName());
		} else {
			search.unsetName();
		}
	}

	public String getSearchFile() {
		return search.getFile();
	}

	public void setSearchFile(String val) {
		if (search.isFileSet()) {
			search.setFile(val);
		}
	}

	public boolean isSearchFileValid() {
		return search.isFileSet();
	}

	public void setSearchFileValid(boolean val) {
		if (val) {
			search.setFile(search.getFile());
		} else {
			search.unsetFile();
		}
	}

	public String getSearchFileMime() {
		return search.getFileMime();
	}

	public void setSearchFileMime(String val) {
		if (search.isFileMimeSet()) {
			search.setFileMime(val);
		}
	}

	public boolean isSearchFileMimeValid() {
		return search.isFileMimeSet();
	}

	public void setSearchFileMimeValid(boolean val) {
		if (val) {
			search.setFileMime(search.getFileMime());
		} else {
			search.unsetFileMime();
		}
	}

	public Project getSearchProject() {
		return search.getProject();
	}

	public void setSearchProject(Project val) {
		if (search.isProjectSet()) {
			search.setProject(val);
		}
	}

	public boolean isSearchProjectValid() {
		return search.isProjectSet();
	}

	public void setSearchProjectValid(boolean val) {
		if (val) {
			search.setProject(search.getProject());
		} else {
			search.unsetProject();
		}
	}

	// Getters and setters to handle uploads

	public void setUploadFile(UploadedFile uploadFile) {
		if (uploadFile != null) {
			oldFile = bill.getFile();
			this.uploadFile = uploadFile;
			setFile(FileUtil.getFileName(uploadFile.getName()));
			setFileMime(uploadFile.getContentType());
		}
	}

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	// Getters and setters to manipulate active Bill object
	public java.lang.Integer getId() {
		return bill.getId();
	}

	public Date getCreationDate() {
		return bill.getCreationDate();
	}

	public void setCreationDate(Date creationDate) {
		bill.setCreationDate(creationDate);
	}

	public BillPaymentMode getPaymentMode() {
		return bill.getPaymentMode();
	}

	public void setPaymentMode(BillPaymentMode paymentMode) {
		bill.setPaymentMode(paymentMode);
	}

	public BillState getState() {
		return bill.getState();
	}

	public void setState(BillState state) {
		bill.setState(state);
	}

	
	public String getNumber() {
		return bill.getNumber();
	}

	public void setNumber(String number) {
		bill.setNumber(number);
	}

	public String getBookNumber() {
		return bill.getBookNumber();
	}

	public void setBookNumber(String bookNumber) {
		bill.setBookNumber(bookNumber);
	}
	
	public String getName() {
		return bill.getName();
	}

	public void setName(String name) {
		bill.setName(name);
	}

	public String getFile() {
		return bill.getFile();
	}

	public void setFile(String file) {
		bill.setFile(file);
	}

	public String getFileMime() {
		return bill.getFileMime();
	}

	public void setFileMime(String fileMime) {
		bill.setFileMime(fileMime);
	}

	public String getObservations() {
		return bill.getObservations();
	}

	public void setObservations(String observations) {
		bill.setObservations(observations);
	}

	public Project getProject() {
		return bill.getProject();
	}

	public void setProject(Project project) {
		bill.setProject(project);
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public BillSearch getSearch() {
		return search;
	}

	/* bill - generated by stajanov (do not edit/delete) */
	

	/*
	 * Create a new empty instance of the one-to-many field @return forward to
	 * the same page
	 */
	public String createBreakDown() {
		BillBreakDown item = new BillBreakDown();
		item.setBill(bill);
		IvaApplicator.applyIvaToTaxableObject(bill.getCreationDate(), item);
		item.setUnits(new BigDecimal(1));
		if (bill.getBreakDown() == null) {
			bill.setBreakDown(new HashSet());
		}
		
		bill.getBreakDown().add(item);
		return null;
	}

	public String createBillPayment() {
		final BillPayment item = new BillPayment();
		item.setBill(bill);
		if (bill.getBillPayment() == null) {
			bill.setBillPayment(new HashSet());
		}
		bill.getBillPayment().add(item);
		return null;
	}

	
	/**
	 * Delete selected instance of the one-to-many field
	 * 
	 * @return forward to the same page
	 */
	public String deleteBreakDown() {
		UIData table = (UIData) FacesUtils.getComponent("bill:breakDown");
		bill.getBreakDown().remove(table.getRowData());
		return null;
	}

	public String deleteBillPayment() {
		UIData table = (UIData) FacesUtils.getComponent("bill:payment");
		bill.getBillPayment().remove(table.getRowData());
		return null;
	}
	
	/**
	 * Get the list of all breakDowns
	 * 
	 * @return the list of all breakDowns
	 */
	public List<SelectItem> getBreakDowns() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<BillBreakDown> refs = BillBreakDownManager.getDefault().getAllEntities(null,new SortCriteria("concept"));
		for (BillBreakDown ref : refs) {
			ret.add(new SelectItem(ref, ref.getConcept()));
		}
		return ret;
	}

	public List<SelectItem> getIVAType(){
		ArrayList<SelectItem> ret = new ArrayList<>();
		List<IVAType> refs = IVATypeManager.getDefault().getAllEntities(new SortCriteria("id"));
		for (IVAType ref : refs) {
			ret.add(new SelectItem(ref.getIva(), ref.getIva().toString() +" - "+ ref.getName()));
		}
		return ret;
	}

	List<BillBreakDown> bitacoreBreakDowns = null;

	public List<BillBreakDown> getAllBitacoreBreakDowns() {

		List<BillBreakDown> desgloses = manager.getAllBitacoreBreakDowns(getStartBillDate(),getEndBillDate(), 
										getProject());

		setBitacoreBreakDowns(desgloses);
		return desgloses;
	}

	public List<BillBreakDown> getSearchBreakDown() {
		return getSearch().getBreakDown();
	}

	public void setSearchBreakDown(List<BillBreakDown> val) {
		if (search.isBreakDownSet()) {
			search.setBreakDown(val);
		}
	}

	public boolean isSearchBreakDownValid() {
		return search.isBreakDownSet();
	}

	public void setSearchBreakDownValid(boolean val) {
		if (val) {
			search.setBreakDown(search.getBreakDown());
		} else {
			search.unsetBreakDown();
		}
	}

	public Set<BillBreakDown> getBreakDown() {
		return bill.getBreakDown();
	}

	public void setBreakDown(Set<BillBreakDown> breakDown) {
		bill.setBreakDown(breakDown);
	}

	public Set<BillPayment> getBillPayment() {
		return bill.getBillPayment();
	}

	public void setBillPayment(Set<BillPayment> breakDown) {
		bill.setBillPayment(breakDown);
	}
	
	public Date getSearchStartStartBillDate() {
		return search.getStartStartBillDate();
	}

	public void setSearchStartStartBillDate(Date val) {
		if (val != null) {
			search.setStartStartBillDate(val);
		} else {
			search.unsetStartStartBillDate();
		}
	}

	public boolean isSearchStartStartBillDateValid() {
		return search.isStartStartBillDateSet();
	}

	public void setSearchStartStartBillDateValid(boolean val) {
		if (val) {
			search.setStartStartBillDate(search.getStartStartBillDate());
		} else {
			search.unsetStartStartBillDate();
		}
	}

	public Date getSearchEndStartBillDate() {
		return search.getEndStartBillDate();
	}

	public void setSearchEndStartBillDate(Date val) {
		if (val != null) {
			search.setEndStartBillDate(val);
		} else {
			search.unsetEndStartBillDate();
		}
	}

	public boolean isSearchEndStartBillDateValid() {
		return search.isEndStartBillDateSet();
	}

	public void setSearchEndStartBillDateValid(boolean val) {
		if (val) {
			search.setEndStartBillDate(search.getEndStartBillDate());
		} else {
			search.unsetEndStartBillDate();
		}
	}

	public Date getSearchStartEndBillDate() {
		return search.getStartEndBillDate();
	}

	public void setSearchStartEndBillDate(Date val) {
		if (val != null) {
			search.setStartEndBillDate(val);
		} else {
			search.unsetStartEndBillDate();
		}
	}

	public boolean isSearchStartEndBillDateValid() {
		return search.isStartEndBillDateSet();
	}

	public void setSearchStartEndBillDateValid(boolean val) {
		if (val) {
			search.setStartEndBillDate(search.getStartEndBillDate());
		} else {
			search.unsetStartEndBillDate();
		}
	}

	public Date getSearchEndEndBillDate() {
		return search.getEndEndBillDate();
	}

	public void setSearchEndEndBillDate(Date val) {
		if (val != null) {
			search.setEndEndBillDate(val);
		} else {
			search.unsetEndEndBillDate();
		}
	}

	public boolean isSearchEndEndBillDateValid() {
		return search.isEndEndBillDateSet();
	}

	public void setSearchEndEndBillDateValid(boolean val) {
		if (val) {
			search.setEndEndBillDate(search.getEndEndBillDate());
		} else {
			search.unsetEndEndBillDate();
		}
	}

	public Date getStartBillDate() {
		return bill.getStartBillDate();
	}

	public void setStartBillDate(Date startBillDate) {
		bill.setStartBillDate(startBillDate);
	}

	public Date getEndBillDate() {
		return bill.getEndBillDate();
	}

	public void setEndBillDate(Date endBillDate) {
		bill.setEndBillDate(endBillDate);
	}

	public BillType getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(BillType selectedType) {
		this.selectedType = selectedType;
	}

	public boolean getPuedoImprimir() {
		return (bill.getBillType() == BillType.ISSUED)
				&& (bill.getId() != null);
	}
	
	public boolean getPuedoPintarProvider() {
		return (getSelectedType() == BillType.RECIEVED);
				
	}
	
	public boolean isReceivedBillType() {
		return (bill != null && (bill.getBillType() == BillType.RECIEVED));
	}

	public Account getSelectedAccount() {
		return bill.getAccount();
	}

	public void setSelectedAccount(Account selectedAccount) {
		bill.setAccount(selectedAccount);
	}

	public String importBitacore() {
		for (BillBreakDown bbd : getBitacoreBreakDowns()) {
			if (bbd.isSelected()) {
				BillBreakDown item = new BillBreakDown();
				item.setConcept(bbd.getConcept());
				item.setBill(bill);
				item.setIva(bbd.getIva());
				item.setAmount(bbd.getAmount());
				item.setUnits(bbd.getUnits());
				if (bill.getBreakDown() == null) {
					bill.setBreakDown(new HashSet());
				}
				bill.getBreakDown().add(item);
			}

		}

		return NavigationResults.IMPORT_BITACORE;
	}

	public List<BillBreakDown> getBitacoreBreakDowns() {
		return bitacoreBreakDowns;
	}

	public void setBitacoreBreakDowns(List<BillBreakDown> bitacoreBreakDowns) {
		this.bitacoreBreakDowns = bitacoreBreakDowns;
	}
	
	/**
	   * Whether or not create button is available for user
	   * @return true if user can create objects of type Bill
	   */
	  public boolean isCreateAvailable()
	  {
	    return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Bill.class));
	  }

	  /**
	   * Whether or not edit button is available for user
	   * @return true if user can edit current object
	   */
	  public boolean isEditAvailable()
	  {
	    return SpringUtils.isAclPermissionGranted(bill,BasePermission.WRITE);
	  }

	  /**
	   * Whether or not delete button is available for user
	   * @return true if user can delete current object
	   */
	  public boolean isDeleteAvailable()
	  {
	    return (bill.getId()!=null) &&
		   SpringUtils.isAclPermissionGranted(bill,BasePermission.DELETE);
	  }
	
	  /**
	   * Go to detail page
	   * @return forward to DETAIL page
	   */
	  public String detail(){
	    Integer id = Integer.parseInt( FacesUtils.getRequestParameter(ROW_ID) );
	    bill = manager.getEntityById(id);

	    return SpringUtils.isAclPermissionGranted( bill, BasePermission.WRITE )
		    ? NavigationResults.EDIT
		    : NavigationResults.DETAIL;
	  }
	  
	  /**
	   * Reset search criteria
	   * @return forward to LIST page
	   */
	  public String reset(){
	  	search.reset();
		return list();    
	  }
	  
      public String select() {
		Integer id = Integer.parseInt(FacesUtils.getRequestParameter(ROW_ID));
		bill = manager.getEntityById(id);
		CreditTitleBean bean = (CreditTitleBean) FacesUtils.getBean("creditTitleBean");
		manager.insertBillInCreditTitle(bill, bean.getCreditTitle(), CREATE_BILL_PAYMENT);
		bean.getBills().add(bill);
		bean.save();
		return NavigationResults.CREDIT_TITLE_SEARCH_BILLS;
      }
	  
      public String selectWitoutBillPayment() {
  		Integer id = Integer.parseInt(FacesUtils.getRequestParameter(ROW_ID));
  		bill = manager.getEntityById(id);
  		CreditTitleBean bean = (CreditTitleBean) FacesUtils.getBean("creditTitleBean");
  		manager.insertBillInCreditTitle(bill, bean.getCreditTitle(), DONT_CREATE_BILL_PAYMENT);
  		bean.getBills().add(bill);
  		bean.save();
  		return NavigationResults.CREDIT_TITLE_SEARCH_BILLS;
        }
      
      
      
      public Date getExpiration() {
    	  if (bill != null && bill.getExpiration() != null) {
    		  final Calendar calendar = Calendar.getInstance();
    		  calendar.setTime(bill.getExpiration());
    		  calendar.add(Calendar.DATE, 1);
    		  return calendar.getTime();
    	  }    	      	  
    	  return null;
      }
      
      public void setExpiration(Date date) {
    	  // do nothing    	 
      }

	public BillBean() {
		this.readOnlyBill = ConfigurationUtil.getDefault().getReadOnlyBill();

	}

	/**
  	 * @return Devuelve la cantidad pendiente de ser pagada
  	 */
  	public BigDecimal getUnpaid(){
		return bill.getUnpaid();
  	}

	public boolean isReadOnlyBill() {
		return readOnlyBill;
	}

	public void setReadOnlyBill(boolean readOnlyBill) {
		this.readOnlyBill = readOnlyBill;
	}
}
