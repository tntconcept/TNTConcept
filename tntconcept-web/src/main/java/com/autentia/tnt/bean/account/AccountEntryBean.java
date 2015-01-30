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

package com.autentia.tnt.bean.account;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.acegisecurity.acls.domain.BasePermission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.bean.NavigationResults;
import com.autentia.tnt.bean.billing.BillBean;
import com.autentia.tnt.businessobject.Account;
import com.autentia.tnt.businessobject.AccountEntry;
import com.autentia.tnt.businessobject.AccountEntryType;
import com.autentia.tnt.businessobject.Bill;
import com.autentia.tnt.businessobject.BillState;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.AccountEntrySearch;
import com.autentia.tnt.manager.account.AccountEntryManager;
import com.autentia.tnt.manager.account.AccountEntryTypeManager;
import com.autentia.tnt.manager.account.AccountManager;
import com.autentia.tnt.manager.billing.BillManager;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.upload.Uploader;
import com.autentia.tnt.upload.UploaderFactory;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.SpringUtils;

public class AccountEntryBean extends BaseBean {

	private BigDecimal costs;

	private BigDecimal incomes;

	private BigDecimal total;
	
	private BigDecimal costsType;
	
	private BigDecimal incomesType;
	
	private BigDecimal totalType;

	private boolean hideInitialEntry = false;

	private static final int ALL_YEARS = 0;

	private static final BillManager billMgr = BillManager.getDefault();

	public boolean isHideInitialEntry() {
		return hideInitialEntry;
	}

	public void setHideInitialEntry(boolean hideInitialEntry) {
		this.hideInitialEntry = hideInitialEntry;
	}
	
	/**
	   * Reset search criteria
	   * @return forward to LIST page
	   */
	  public String reset(){
	  	search.reset();
		return list();    
	  }

	private void calcTotals(List<AccountEntry> res) {
		costs = new BigDecimal(0);
		incomes = new BigDecimal(0);
		
		costsType = new BigDecimal(0);
		incomesType = new BigDecimal(0);
		
		Hashtable mapaCajaTotales = new Hashtable();

		for (AccountEntry elem : res) {
			Integer accountAct = elem.getAccount().getId();
			BigDecimal accountValueAct = null;
			if (!mapaCajaTotales.containsKey(accountAct)) {
				mapaCajaTotales.put(accountAct, new BigDecimal(0));
			}

			accountValueAct = (BigDecimal) mapaCajaTotales.get(accountAct);

			BigDecimal actual = elem.getAmount();
			BigDecimal resul = accountValueAct.add(actual);
			elem.setAmountAccountNow(resul);
			mapaCajaTotales.remove(accountAct);
			mapaCajaTotales.put(accountAct, resul);

			if (actual.signum() >= 0) {
				setIncomes(incomes.add(actual));
			} else {
				setCosts(costs.add(actual));
			}
			
			if(elem.getType().getGroup().getId()==ConfigurationUtil.getDefault().getCostId()) {
				setCostsType(costsType.add(actual));
			} else {
				setIncomesType(incomesType.add(actual));				
			}
		}

		setTotal(incomes.add(costs));
		setTotalType(incomesType.add(costsType));
	}
	
	public BigDecimal getCostsType() {
		return costsType;
	}

	public void setCostsType(BigDecimal costsType) {
		this.costsType = costsType;
	}

	public BigDecimal getIncomesType() {
		return incomesType;
	}

	public void setIncomesType(BigDecimal incomesType) {
		this.incomesType = incomesType;
	}
	
	
	public BigDecimal getCosts() {
		return costs;
	}

	public void setCosts(BigDecimal costs) {
		this.costs = costs;
	}

	public BigDecimal getIncomes() {
		return incomes;
	}

	public void setIncomes(BigDecimal incomes) {
		this.incomes = incomes;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String select() {
		Integer id = Integer.parseInt(FacesUtils.getRequestParameter(ROW_ID));
		accountEntry = manager.getEntityById(id);
		BillBean bean = (BillBean) FacesUtils.getBean("billBean");
		accountEntry.getBills().add(bean.getBill());
		bean.getBill().getEntries().add(accountEntry);
		
		return NavigationResults.BILL_SEARCH_ENTRIES;		
	}

	public List<AccountEntry> getAllMinusBill() {

		return manager.getAllMinusBill(year, bill, new SortCriteria(sortColumn,
				sortAscending));
	}

	private int year = Calendar.getInstance().get(Calendar.YEAR);

	private int accountSelected = ALL_ACCOUNTS;

	private Bill bill;

	public static int ALL_ACCOUNTS = -1;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getAccountSelected() {
		return accountSelected;
	}

	public void setAccountSelected(int accountSelected) {
		this.accountSelected = accountSelected;
	}

	public List<SelectItem> getAllAccounts() {

		FacesContext context = FacesContext.getCurrentInstance();
		Locale locale = context.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(
				"com.autentia.tnt.resources.messages", locale);

		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();

		ret.add(new SelectItem(Integer.valueOf(ALL_ACCOUNTS), bundle
				.getString("accountEntry.allaccounts")));

		List<Account> refs = AccountManager.getDefault().getAllEntities(null,
				new SortCriteria("name"));

		for (Account ref : refs) {
			ret.add(new SelectItem(ref.getId(), ref.getName()));
		}

		return ret;

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
				.getString("accountEntry.fiscalYear.now")));
		ret.add(new SelectItem(Integer.valueOf(ALL_YEARS), bundle
				.getString("accountEntry.fiscalYear.all")));

		for (int i = 1; i < getMaximumYears(); i++) {
			ret.add(new SelectItem(Integer.valueOf(actual - i), "" + (actual - i)));
		}

		return ret;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	/* accountEntry - generated by stajanov (do not edit/delete) */

	/** Active search object */
	private AccountEntrySearch search = new AccountEntrySearch();

	/** Manager */
	private static AccountEntryManager manager = AccountEntryManager
			.getDefault();

	/** Logger */
	private static final Log log = LogFactory.getLog(AccountEntryBean.class);

	/** Quick search letter for ABC pager control */
	private Character letter;

	/** Upload service */
	private static final Uploader uploader = UploaderFactory
			.getInstance("accountEntry");

	/** Active AccountEntry object */
	private AccountEntry accountEntry;

	/** Default sort column */
	private String sortColumn = "date";

	/** Default sort order */
	private boolean sortAscending = false;

	/**
	 * List accountEntrys. Order depends on Faces parameter sort.
	 * 
	 * @return the list of all accountEntrys sorted by requested criterion
	 */

	public List<AccountEntry> getAll() {

		if (year == ALL_YEARS) {
			search.unsetYear();
		} else {
			search.setYear(year);
		}
		search.setHideInitialEntry(hideInitialEntry);

		if (accountSelected != ALL_ACCOUNTS) {
			search.setAccount(AccountManager.getDefault().getEntityById(
					accountSelected));
		} else {
			search.unsetAccount();
		}

		List<AccountEntry> res = manager.getAllEntities(search,
				new SortCriteria(sortColumn, sortAscending));
		calcTotals(res);
		return res;
	}

	// Getters to list possible values of related (one-to-one or many-to-many)
	// entities

	/**
	 * Get the list of all accounts
	 * 
	 * @return the list of all accounts
	 */
	public List<SelectItem> getAccounts() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<Account> refs = AccountManager.getDefault().getAllEntities(null,
				new SortCriteria("name"));
		for (Account ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}
		return ret;
	}

	/**
	 * Get the list of all types
	 * 
	 * @return the list of all types
	 */
	public List<SelectItem> getTypes() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<AccountEntryType> refs = AccountEntryTypeManager.getDefault()
				.getAllEntities(null, new SortCriteria("name"));
		for (AccountEntryType ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}
		ret.add(0, new SelectItem(""));
		return ret;
	}

	// Getters to list possible values of enum fields

	// Methods to create/remove instances of one-to-many entities (slave
	// entities)

	/**
	 * Go to create page
	 * 
	 * @return forward to CREATE page
	 */
	public String create() {
		accountEntry = new AccountEntry();
		accountEntry.setDate(new Date());
		accountEntry.setAmountDate(new Date());

		return NavigationResults.CREATE;
	}

	/**
	 * Go to edit page
	 * 
	 * @return forward to EDIT page
	 */
	public String edit() {
		Integer id = Integer.parseInt(FacesUtils.getRequestParameter(ROW_ID));
		accountEntry = manager.getEntityById(id);
		return NavigationResults.EDIT;
	}

	/**
	 * Go to detail page
	 * 
	 * @return forward to DETAIL page
	 */
	public String detail() {
		Integer id = Integer.parseInt(FacesUtils.getRequestParameter(ROW_ID));
		accountEntry = manager.getEntityById(id);

		return SpringUtils.isAclPermissionGranted(accountEntry,
				BasePermission.WRITE) ? NavigationResults.EDIT
				: NavigationResults.DETAIL;
	}

	/**
	 * Save bean and stay on it
	 * 
	 * @return forward to list page
	 */
	public String save() {
		if (accountEntry.getId() == null) {
			manager.insertEntity(accountEntry);
		} else {
			manager.updateEntity(accountEntry);
		}

		return NavigationResults.LIST;
	}

	/**
	 * Delete bean and go back to beans list
	 * 
	 * @return forward to LIST page
	 */
	public String delete() {
		manager.deleteEntity(accountEntry);
		accountEntry = null;
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
	public boolean isAccountEntrySelected() {
		return accountEntry != null;
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
	public Date getSearchStartDate() {
		return search.getStartDate();
	}

	public void setSearchStartDate(Date val) {
		if (val != null) {
			search.setStartDate(val);
		} else {
			search.unsetStartDate();
		}
	}

	public boolean isSearchStartDateValid() {
		return search.isStartDateSet();
	}

	public void setSearchStartDateValid(boolean val) {
		if (val) {
			search.setStartDate(search.getStartDate());
		} else {
			search.unsetStartDate();
		}
	}

	public Date getSearchEndDate() {
		return search.getEndDate();
	}

	public void setSearchEndDate(Date val) {
		if (val != null) {
			search.setEndDate(val);
		} else {
			search.unsetEndDate();
		}
	}

	public boolean isSearchEndDateValid() {
		return search.isEndDateSet();
	}

	public void setSearchEndDateValid(boolean val) {
		if (val) {
			search.setEndDate(search.getEndDate());
		} else {
			search.unsetEndDate();
		}
	}

	public Date getSearchStartAmountDate() {
		return search.getStartAmountDate();
	}

	public void setSearchStartAmountDate(Date val) {
		if (val != null) {
			search.setStartAmountDate(val);
		} else {
			search.unsetStartAmountDate();
		}
	}

	public boolean isSearchStartAmountDateValid() {
		return search.isStartAmountDateSet();
	}

	public void setSearchStartAmountDateValid(boolean val) {
		if (val) {
			search.setStartAmountDate(search.getStartAmountDate());
		} else {
			search.unsetStartAmountDate();
		}
	}

	public Date getSearchEndAmountDate() {
		return search.getEndAmountDate();
	}

	public void setSearchEndAmountDate(Date val) {
		if (val != null) {
			search.setEndAmountDate(val);
		} else {
			search.unsetEndAmountDate();
		}
	}

	public boolean isSearchEndAmountDateValid() {
		return search.isEndAmountDateSet();
	}

	public void setSearchEndAmountDateValid(boolean val) {
		if (val) {
			search.setEndAmountDate(search.getEndAmountDate());
		} else {
			search.unsetEndAmountDate();
		}
	}

	public String getSearchConcept() {
		return search.getConcept();
	}

	public void setSearchConcept(String val) {
		if (search.isConceptSet()) {
			search.setConcept(val);
		}		
	}

	public boolean isSearchConceptValid() {
		return search.isConceptSet();
	}

	public void setSearchConceptValid(boolean val) {
		if (val) {
			search.setConcept(search.getConcept());			
		} else {
			search.unsetConcept();
		}
		
	}

	public BigDecimal getSearchStartAmount() {
		return search.getStartAmount();
	}

	public void setSearchStartAmount(BigDecimal val) {
		if (search.isStartAmountSet()) {
			search.setStartAmount(val);
		}		
	}

	public boolean isSearchStartAmountValid() {
		return search.isStartAmountSet();
	}

	public void setSearchStartAmountValid(boolean val) {
		if (val) {	
			search.setStartAmount(search.getStartAmount());
		} else {
			search.unsetStartAmount();
		}
	}

	public BigDecimal getSearchEndAmount() {
		return search.getEndAmount();
	}

	public void setSearchEndAmount(BigDecimal val) {
		if (search.isEndAmountSet()) {
			search.setEndAmount(val);
		}		

	}

	public boolean isSearchEndAmountValid() {
		return search.isEndAmountSet();
	}

	public void setSearchEndAmountValid(boolean val) {
		if (val) {
			search.setEndAmount(search.getEndAmount());
		} else {
			search.unsetEndAmount();
		}
	}

	public String getSearchEntryNumber() {
		return search.getEntryNumber();
	}

	public void setSearchEntryNumber(String val) {
		if (search.isEntryNumberSet()) {
			search.setEntryNumber(val);
		}
	}

	public boolean isSearchEntryNumberValid() {
		return search.isEntryNumberSet();
	}

	public void setSearchEntryNumberValid(boolean val) {
		if (val) {
			search.setEntryNumber(search.getEntryNumber());
		} else {
			search.unsetEntryNumber();
		}
	}

	public String getSearchDocNumber() {
		return search.getDocNumber();
	}

	public void setSearchDocNumber(String val) {
		if (search.isDocNumberSet()) {
			search.setDocNumber(val);
		}
	}

	public boolean isSearchDocNumberValid() {
		return search.isDocNumberSet();
	}

	public void setSearchDocNumberValid(boolean val) {
		if (val) {
			search.setDocNumber(search.getDocNumber());
		} else {
			search.unsetDocNumber();
		}
	}

	public String getSearchObservations() {
		return search.getObservations();
	}

	public void setSearchObservations(String val) {
		if (val != null) {
			search.setObservations(val);
		} else {
			search.unsetObservations();
		}
	}

	public boolean isSearchObservationsValid() {
		return search.isObservationsSet();
	}

	public void setSearchObservationsValid(boolean val) {
		if (val) {
			search.setObservations(search.getObservations());
		} else {
			search.unsetObservations();
		}
	}

	public Account getSearchAccount() {
		return search.getAccount();
	}

	public void setSearchAccount(Account val) {
		if (val != null) {
			search.setAccount(val);
		} else {
			search.unsetAccount();
		}
	}

	public boolean isSearchAccountValid() {
		return search.isAccountSet();
	}

	public void setSearchAccountValid(boolean val) {
		if (val) {
			search.setAccount(search.getAccount());
		} else {
			search.unsetAccount();
		}
	}

	public AccountEntryType getSearchType() {
		return search.getType();
	}

	public void setSearchType(AccountEntryType val) {
		if (val != null) {
			search.setType(val);
		} else {
			search.unsetType();
		}
	}

	public boolean isSearchTypeValid() {
		return search.isTypeSet();
	}

	public void setSearchTypeValid(boolean val) {
		if (val) {
			
			search.setType(search.getType());
		} else {
			search.unsetType();
		}
	}

	// Getters and setters to handle uploads

	// Getters and setters to manipulate active AccountEntry object
	public java.lang.Integer getId() {
		return accountEntry.getId();
	}

	public Date getDate() {
		return accountEntry.getDate();
	}

	public void setDate(Date date) {
		accountEntry.setDate(date);
	}

	public Date getAmountDate() {
		return accountEntry.getAmountDate();
	}

	public void setAmountDate(Date amountDate) {
		accountEntry.setAmountDate(amountDate);
	}

	public String getConcept() {
		return accountEntry.getConcept();
	}

	public void setConcept(String concept) {
		accountEntry.setConcept(concept);
	}

	public BigDecimal getAmount() {
		return accountEntry.getAmount();
	}

	public void setAmount(BigDecimal amount) {
		accountEntry.setAmount(amount);
	}

	public String getEntryNumber() {
		return accountEntry.getEntryNumber();
	}

	public void setEntryNumber(String entryNumber) {
		accountEntry.setEntryNumber(entryNumber);
	}

	public String getDocNumber() {
		return accountEntry.getDocNumber();
	}

	public void setDocNumber(String docNumber) {
		accountEntry.setDocNumber(docNumber);
	}

	public String getObservations() {
		return accountEntry.getObservations();
	}

	public void setObservations(String observations) {
		accountEntry.setObservations(observations);
	}

	public Account getAccount() {
		return accountEntry.getAccount();
	}

	public void setAccount(Account account) {
		accountEntry.setAccount(account);
	}

	public AccountEntryType getType() {
		return accountEntry.getType();
	}

	public void setType(AccountEntryType type) {
		accountEntry.setType(type);
	}

	/**
	 * Handle an ABC pager letter click: filter objects by specified starting
	 * letter
	 */
	public void letterClicked() {
		if (letter != null) {
			UIComponent comp = FacesUtils.getComponent("accountEntrys:list");
			HtmlDataTable tabla = (HtmlDataTable) comp;
			tabla.setFirst(0);

			search.setConcept(letter + "%");
		} else {
			search.unsetConcept();
		}
	}

	public Character getLetter() {
		return letter;
	}

	public void setLetter(Character letter) {
		this.letter = letter;
	}

	/**
	 * Whether or not create button is available for user
	 * 
	 * @return true if user can create objects of type AccountEntry
	 */
	public boolean isCreateAvailable() {
		return SpringUtils.isRolePermissionGranted(Permission
				.Entity_Create(AccountEntry.class));
	}

	/**
	 * Whether or not edit button is available for user
	 * 
	 * @return true if user can edit current object
	 */
	public boolean isEditAvailable() {
		return SpringUtils.isAclPermissionGranted(accountEntry,
				BasePermission.WRITE);
	}

	/**
	 * Whether or not delete button is available for user
	 * 
	 * @return true if user can delete current object
	 */
	public boolean isDeleteAvailable() {
		return (accountEntry.getId() != null)
				&& SpringUtils.isAclPermissionGranted(accountEntry,
						BasePermission.DELETE);
	}

	/* accountEntry - generated by stajanov (do not edit/delete) */

	public void valueTypeChanged(ValueChangeEvent event) {
		this.setType((AccountEntryType) event.getNewValue());
		FacesUtils.renderResponse();
	}

	public AccountEntrySearch getSearch() {
		return search;
	}

	public boolean getCanCopy() {
		return (accountEntry.getId() != null);
	}

	public BigDecimal getTotalType() {
		return totalType;
	}

	public void setTotalType(BigDecimal totalType) {
		this.totalType = totalType;
	}

}
