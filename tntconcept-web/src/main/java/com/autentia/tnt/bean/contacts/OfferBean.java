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

package com.autentia.tnt.bean.contacts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.acegisecurity.acls.domain.BasePermission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.bean.NavigationResults;
import com.autentia.tnt.bean.billing.BillBean;
import com.autentia.tnt.businessobject.Bill;
import com.autentia.tnt.businessobject.Contact;
import com.autentia.tnt.businessobject.Interaction;
import com.autentia.tnt.businessobject.Offer;
import com.autentia.tnt.businessobject.OfferCost;
import com.autentia.tnt.businessobject.OfferPotential;
import com.autentia.tnt.businessobject.OfferRejectReason;
import com.autentia.tnt.businessobject.OfferRole;
import com.autentia.tnt.businessobject.OfferState;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.ContactSearch;
import com.autentia.tnt.dao.search.OfferSearch;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.billing.BillManager;
import com.autentia.tnt.manager.contacts.ContactManager;
import com.autentia.tnt.manager.contacts.InteractionManager;
import com.autentia.tnt.manager.contacts.OfferCostManager;
import com.autentia.tnt.manager.contacts.OfferManager;
import com.autentia.tnt.manager.contacts.OfferRejectReasonManager;
import com.autentia.tnt.manager.contacts.OfferRoleManager;
import com.autentia.tnt.manager.contacts.OrganizationManager;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.upload.Uploader;
import com.autentia.tnt.upload.UploaderFactory;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.IvaApplicator;
import com.autentia.tnt.util.SpringUtils;

public class OfferBean extends BaseBean {

	private static final long serialVersionUID = 14353454354355L;

	/** Logger */
	

	/** Active search object */
	private OfferSearch search = new OfferSearch();

	/** Manager */
	private static OfferManager manager = OfferManager.getDefault();
	private static BillManager billManager = BillManager.getDefault();
	
	/** Upload service */
	private static final Uploader uploader = UploaderFactory.getInstance("offer");

	/** Active Offer object */
	private Offer offer;

	/** Default sort column */
	
	private String sortColumn = "creationDate";

	/** Default sort order */
	private boolean sortAscending = false;

	/** Quick search letter for ABC pager control */
	private Character letter;

	private String showSubreportRole = "NO";

	private String showSubreportCost = "NO";
	

	private static final Log log = LogFactory.getLog(OfferBean.class);
	public OfferBean () {
		
		search.setOfferState(OfferState.OPEN);		
				
	} 
	
	/**
	 * List offers. Order depends on Faces parameter sort.
	 * 
	 * @return the list of all offers sorted by requested criterion
	 */
	public List<Offer> getAll() {
		return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
	}

	// Getters to list possible values of related entities

	/**
	 * Get the list of all users
	 * 
	 * @return the list of all users
	 */
	public List<SelectItem> getUsers() {
		List<User> refs = UserManager.getDefault().getAllEntities(null, new SortCriteria("name"));

		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		for (User ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}
		return ret;
	}

	/**
	 * Get the list of all contacts
	 * 
	 * @return the list of all contacts
	 */
	public List<SelectItem> getContacts() {
		List<Contact> refs = ContactManager.getDefault().getAllEntities(null, new SortCriteria("name"));

		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		for (Contact ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}
		return ret;
	}

	/**
	 * Get the list of all interactionss
	 * 
	 * @return the list of all interactionss
	 */
	public List<SelectItem> getInteractionss() {
		List<Interaction> refs = InteractionManager.getDefault().getAllEntities(null, new SortCriteria("creationDate"));

		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		for (Interaction ref : refs) {
			ret.add(new SelectItem(ref, ref.getDescription()));
		}
		return ret;
	}

	/**
	 * Get the list of all roless
	 * 
	 * @return the list of all roless
	 */
	public List<SelectItem> getRoless() {
		List<OfferRole> refs = OfferRoleManager.getDefault().getAllEntities(null, new SortCriteria("name"));

		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		for (OfferRole ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}
		return ret;
	}

	/**
	 * Get the list of all costss
	 * 
	 * @return the list of all costss
	 */
	public List<SelectItem> getCostss() {
		List<OfferCost> refs = OfferCostManager.getDefault().getAllEntities(null, new SortCriteria("name"));

		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		for (OfferCost ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}
		return ret;
	}

	// Getters to list possible values of enum fields

	public String getOfferStateFormatted() {
		if (this.getOfferState() != null) {
			return FacesUtils.formatMessage("OfferState." + this.getOfferState().name());
		} else {
			return "";
		}
	}

	/**
	 * Get the list of all OfferState values
	 * 
	 * @return the list of all OfferState values
	 */
	public List<SelectItem> getOfferStates() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		OfferState[] vals = OfferState.values();
		for (OfferState val : vals) {
			ret.add(new SelectItem(val, FacesUtils.formatMessage("OfferState." + val.name())));
		}
		
		return ret;
	}
	
	
	
	public String getOfferPotentialFormatted() {
		if (this.getOfferPotential() != null) {
			return FacesUtils.formatMessage("OfferPotential." + this.getOfferPotential().name());
		} else {
			return "";
		}
	}

	/**
	 * Get the list of all OfferPotential values
	 * 
	 * @return the list of all OfferPotential values
	 */
	public List<SelectItem> getOfferPotentials() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		OfferPotential[] vals = OfferPotential.values();
		for (OfferPotential val : vals) {
			ret.add(new SelectItem(val, FacesUtils.formatMessage("OfferPotential." + val.name())));
		}
		return ret;
	}

	// Methods to create/remove instances of one-to-many entities (slave
	// entities)

	/**
	 * Create a new empty instance of the one-to-many field
	 * 
	 * @return forward to the same page
	 */
	public String createRoles() {
		OfferRole item = new OfferRole();
		item.setOffer(offer);
		IvaApplicator.applyIvaToTaxableObject(offer.getCreationDate(), item);
		if (offer.getRoles() == null) {
			offer.setRoles(new HashSet());
		}
		offer.getRoles().add(item);
		return null;
	}

	/**
	 * Delete selected instance of the one-to-many field
	 * 
	 * @return forward to the same page
	 */
	public String deleteRoles() {
		UIData table = (UIData)FacesUtils.getComponent("offer:roles");
		offer.getRoles().remove(table.getRowData());
		return null;
	}

	/**
	 * Create a new empty instance of the one-to-many field
	 * 
	 * @return forward to the same page
	 */
	public String createCosts() {
		OfferCost item = new OfferCost();
		item.setOffer(offer);
		IvaApplicator.applyIvaToTaxableObject(offer.getCreationDate(), item);
		if (offer.getCosts() == null) {
			offer.setCosts(new HashSet());
		}
		offer.getCosts().add(item);
		return null;
	}

	/**
	 * Delete selected instance of the one-to-many field
	 * 
	 * @return forward to the same page
	 */
	public String deleteCosts() {
		UIData table = (UIData)FacesUtils.getComponent("offer:costs");
		offer.getCosts().remove(table.getRowData());
		return null;
	}

	/**
	 * Whether or not create button is available for user
	 * 
	 * @return true if user can create objects of type Offer
	 */
	public boolean isCreateAvailable() {
		return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Offer.class));
	}

	/**
	 * Whether or not edit button is available for user
	 * 
	 * @return true if user can edit current object
	 */
	public boolean isEditAvailable() {
		return SpringUtils.isAclPermissionGranted(offer, BasePermission.WRITE);
	}

	/**
	 * Whether or not delete button is available for user
	 * 
	 * @return true if user can delete current object
	 */
	public boolean isDeleteAvailable() {
		return (offer.getId() != null) && SpringUtils.isAclPermissionGranted(offer, BasePermission.DELETE);
	}

	public boolean isSavedEntity() {
		return offer.getId() != null;
	}
	
	/**
	 * Reset search criteria
	 * 
	 * @return forward to LIST page
	 */
	public String reset() {
		search.reset();
		return list();
	}

	/**
	 * Go to create page
	 * 
	 * @return forward to CREATE page
	 */
	public String create() {
		offer = new Offer();
		offer.setUser(SpringUtils.getPrincipal().getUser());
		offer.setCreationDate(new Date());

		return NavigationResults.CREATE;
	}

	/**
	 * Go to detail page
	 * 
	 * @return forward to DETAIL page
	 */
	public String detail() {

		showSubreportRole = "NO";
		showSubreportCost = "NO";
		Integer id = Integer.parseInt(FacesUtils.getRequestParameter(ROW_ID));
		offer = manager.getEntityById(id);

		return SpringUtils.isAclPermissionGranted(offer, BasePermission.WRITE) ? NavigationResults.EDIT
				: NavigationResults.DETAIL;
	}

	/**
	 * Save bean and stay on it
	 * 
	 * @return forward to list page
	 */
	public String save() {

		doBeforeSave();

		final Set<Interaction> interactionsWithProject = new HashSet<Interaction>();
		if (offer != null && offer.getInteractions() != null) {
			for (Interaction interaction: offer.getInteractions()) {
				if (interaction.getProject() != null) {
					interactionsWithProject.add(interaction);
				}
			}
		}
		if(offer!=null){
			offer.setInteractions(interactionsWithProject);
			
			if (offer.getId() == null) {
				manager.insertEntity(offer);
			} else {
				manager.updateEntity(offer);
			}
		}
		// nos mantenemos en la pantalla de edición de ofertas
		return null;
	}

	/**
	 * Delete bean and go back to beans list
	 * 
	 * @return forward to LIST page
	 */
	public String delete() {
		manager.deleteEntity(offer);
		offer = null;
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
	public boolean isOfferSelected() {
		return offer != null;
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
	public OfferSearch getSearch() {
		return search;
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

	public String getSearchTitle() {
		return search.getTitle();
	}

	public void setSearchTitle(String val) {
		if (search.isTitleSet()) {
			search.setTitle(val);
			
		}
	}

	public boolean isSearchTitleValid() {
		return search.isTitleSet();
	}

	public void setSearchTitleValid(boolean val) {
		if (val) {
			search.setTitle(search.getTitle());
		} else {
			search.unsetTitle();
		}
	}

	public String getSearchDescription() {
		return search.getDescription();
	}

	public void setSearchDescription(String val) {
		if (search.isDescriptionSet()) {
			search.setDescription(val);
		}
	}

	public boolean isSearchDescriptionValid() {
		return search.isDescriptionSet();
	}

	public void setSearchDescriptionValid(boolean val) {
		if (val) {
			search.setDescription(search.getDescription());
		} else {
			search.unsetDescription();
		}
	}

	public String getSearchObservations() {
		return search.getObservations();
	}

	public void setSearchObservations(String val) {
		if (search.isObservationsSet()) {
			search.setObservations(val);
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

	public Date getSearchStartValidityDate() {
		return search.getStartValidityDate();
	}

	public void setSearchStartValidityDate(Date val) {
		if (val != null) {
			
			search.setStartValidityDate(val);
		} else {
			search.unsetStartValidityDate();
		}
	}

	public boolean isSearchStartValidityDateValid() {
		return search.isStartValidityDateSet();
	}

	public void setSearchStartValidityDateValid(boolean val) {
		if (val) {
			search.setStartValidityDate(search.getStartValidityDate());
		} else {
			search.unsetStartValidityDate();
		}
	}

	public Date getSearchEndValidityDate() {
		return search.getEndValidityDate();
	}

	public void setSearchEndValidityDate(Date val) {
		if (val != null) {
			
			search.setEndValidityDate(val);
		} else {
			search.unsetEndValidityDate();
		}
	}

	public boolean isSearchEndValidityDateValid() {
		return search.isEndValidityDateSet();
	}

	public void setSearchEndValidityDateValid(boolean val) {
		if (val) {
			search.setEndValidityDate(search.getEndValidityDate());
		} else {
			search.unsetEndValidityDate();
		}
	}

	public Date getSearchStartMaturityDate() {
		return search.getStartMaturityDate();
	}

	public void setSearchStartMaturityDate(Date val) {
		if (val != null) {
			
			search.setStartMaturityDate(val);
		} else {
			search.unsetStartMaturityDate();
		}
	}

	public boolean isSearchStartMaturityDateValid() {
		return search.isStartMaturityDateSet();
	}

	public void setSearchStartMaturityDateValid(boolean val) {
		if (val) {
			search.setStartMaturityDate(search.getStartMaturityDate());
		} else {
			search.unsetStartMaturityDate();
		}
	}

	public Date getSearchEndMaturityDate() {
		return search.getEndMaturityDate();
	}

	public void setSearchEndMaturityDate(Date val) {
		if (val != null) {
			
			search.setEndMaturityDate(val);
		} else {
			search.unsetEndMaturityDate();
		}
	}

	public boolean isSearchEndMaturityDateValid() {
		return search.isEndMaturityDateSet();
	}

	public void setSearchEndMaturityDateValid(boolean val) {
		if (val) {
			search.setEndMaturityDate(search.getEndMaturityDate());
		} else {
			search.unsetEndMaturityDate();
		}
	}

	public OfferState getSearchOfferState() {
		
		return search.getOfferState();
	}

	public void setSearchOfferState(OfferState val) {
		
		if (search.isOfferStateSet()) {
			
			search.setOfferState(val);
			
		}
	}


	public boolean isSearchOfferStateValid() {
		
		return search.isOfferStateSet();
	}

	public void setSearchOfferStateValid(boolean val) {
		if (val) {
			search.setOfferState(search.getOfferState());
		} else {
			search.unsetOfferState();
		}
	}

	
	
	public OfferPotential getSearchOfferPotential() {
		return search.getOfferPotential();
	}

	public void setSearchOfferPotential(OfferPotential val) {
		if (search.isOfferPotentialSet()) {
			
			search.setOfferPotential(val);
		}
	}

	public boolean isSearchOfferPotentialValid() {
		return search.isOfferPotentialSet();
	}

	public void setSearchOfferPotentialValid(boolean val) {
		if (val) {
			search.setOfferPotential(search.getOfferPotential());
		} else {
			search.unsetOfferPotential();
		}
	}

	public BigDecimal getSearchTaxBase() {
		return search.getTaxBase();
	}

	public void setSearchTaxBase(BigDecimal val) {
		if (search.isTaxBaseSet()) {
			search.setTaxBase(val);
		}
	}

	public BigDecimal getSearchTotalRoles() {
		return search.getTotalRoles();
	}

	public void setSearchTotalRoles(BigDecimal val) {
		if (search.isTotalRolesSet()) {
			search.setTotalRoles(val);
		}
	}

	public boolean isSearchTotalRolesValid() {
		return search.isTotalRolesSet();
	}

	public void setSearchTotalRolesValid(boolean val) {
		if (val) {
			search.setTotalRoles(search.getTotalRoles());
		} else {
			search.unsetTotalRoles();
		}
	}

	public BigDecimal getSearchTotalCosts() {
		return search.getTotalCosts();
	}

	public void setSearchTotalCosts(BigDecimal val) {
		if (search.isTotalCostsSet()) {
			search.setTotalCosts(val);
		}
	}

	public boolean isSearchTotalCostsValid() {
		return search.isTotalCostsSet();
	}

	public void setSearchTotalCostsValid(boolean val) {
		if (val) {
			search.setTotalCosts(search.getTotalCosts());
		} else {
			search.unsetTotalCosts();
		}
	}

	public BigDecimal getSearchTotal() {
		return search.getTotal();
	}

	public void setSearchTotal(BigDecimal val) {
		if (search.isTotalSet()) {
			search.setTotal(val);
		}
	}

	public boolean isSearchTotalValid() {
		return search.isTotalSet();
	}

	public void setSearchTotalValid(boolean val) {
		if (val) {
			search.setTotal(search.getTotal());
		} else {
			search.unsetTotal();
		}
	}

	public Integer getSearchOwnerId() {
		return search.getOwnerId();
	}

	public void setSearchOwnerId(Integer val) {
		if (search.isOwnerIdSet()) {
			search.setOwnerId(val);
		}
	}

	public boolean isSearchOwnerIdValid() {
		return search.isOwnerIdSet();
	}

	public void setSearchOwnerIdValid(boolean val) {
		if (val) {
			search.setOwnerId(search.getOwnerId());
		} else {
			search.unsetOwnerId();
		}
	}

	public Integer getSearchDepartmentId() {
		return search.getDepartmentId();
	}

	public void setSearchDepartmentId(Integer val) {
		if (search.isDepartmentIdSet()) {
			search.setDepartmentId(val);
		}
	}

	public boolean isSearchDepartmentIdValid() {
		return search.isDepartmentIdSet();
	}

	public void setSearchDepartmentIdValid(boolean val) {
		if (val) {
			search.setDepartmentId(search.getDepartmentId());
		} else {
			search.unsetDepartmentId();
		}
	}

	public Date getSearchStartInsertDate() {
		return search.getStartInsertDate();
	}

	public void setSearchStartInsertDate(Date val) {
		if (val != null) {
			search.setStartInsertDate(val);
		} else {
			search.unsetStartInsertDate();
		}
	}

	public boolean isSearchStartInsertDateValid() {
		return search.isStartInsertDateSet();
	}

	public void setSearchStartInsertDateValid(boolean val) {
		if (val) {
			search.setStartInsertDate(search.getStartInsertDate());
		} else {
			search.unsetStartInsertDate();
		}
	}

	public Date getSearchEndInsertDate() {
		return search.getEndInsertDate();
	}

	public void setSearchEndInsertDate(Date val) {
		if (val != null) {
			search.setEndInsertDate(val);
		} else {
			search.unsetEndInsertDate();
		}
	}

	public boolean isSearchEndInsertDateValid() {
		return search.isEndInsertDateSet();
	}

	public void setSearchEndInsertDateValid(boolean val) {
		if (val) {
			search.setEndInsertDate(search.getEndInsertDate());
		} else {
			search.unsetEndInsertDate();
		}
	}

	public Date getSearchStartUpdateDate() {
		return search.getStartUpdateDate();
	}

	public void setSearchStartUpdateDate(Date val) {
		if (val != null) {
			search.setStartUpdateDate(val);
		} else {
			search.unsetStartUpdateDate();
		}
	}

	public boolean isSearchStartUpdateDateValid() {
		return search.isStartUpdateDateSet();
	}

	public void setSearchStartUpdateDateValid(boolean val) {
		if (val) {
			search.setStartUpdateDate(search.getStartUpdateDate());
		} else {
			search.unsetStartUpdateDate();
		}
	}

	public Date getSearchEndUpdateDate() {
		return search.getEndUpdateDate();
	}

	public void setSearchEndUpdateDate(Date val) {
		if (val != null) {
			search.setEndUpdateDate(val);
		} else {
			search.unsetEndUpdateDate();
		}
	}

	public boolean isSearchEndUpdateDateValid() {
		return search.isEndUpdateDateSet();
	}

	public void setSearchEndUpdateDateValid(boolean val) {
		if (val) {
			search.setEndUpdateDate(search.getEndUpdateDate());
		} else {
			search.unsetEndUpdateDate();
		}
	}

	public User getSearchUser() {
		return search.getUser();
	}

	public void setSearchUser(User val) {
		if (search.isUserSet()) {
			
			search.setUser(val);
		}
	}

	public boolean isSearchUserValid() {
		return search.isUserSet();
	}

	public void setSearchUserValid(boolean val) {
		if (val) {
			search.setUser(search.getUser());
		} else {
			search.unsetUser();
		}
	}

	public Organization getSearchOrganization() {
		return search.getOrganization();
	}

	public void setSearchOrganization(Organization val) {
		if (search.isOrganizationSet()) {
			search.setOrganization(val);
			
		}
	}

	public boolean isSearchOrganizationValid() {
		return search.isOrganizationSet();
	}

	public void setSearchOrganizationValid(boolean val) {
		if (val) {
			search.setOrganization(search.getOrganization());
		} else {
			search.unsetOrganization();
		}
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

	public OfferRejectReason getSearchOfferRejectReason() {
		return search.getOfferRejectReason();
	}

	public void setSearchOfferRejectReason(OfferRejectReason val) {
		if (search.isOfferRejectReasonSet()) {
			
			search.setOfferRejectReason(val);
		}
	}

	public boolean isSearchOfferRejectReasonValid() {
		return search.isOfferRejectReasonSet();
	}

	public void setSearchOfferRejectReasonValid(boolean val) {
		if (val) {
			search.setOfferRejectReason(search.getOfferRejectReason());
		} else {
			search.unsetOfferRejectReason();
		}
	}

	public List<Interaction> getSearchInteractions() {
		return search.getInteractions();
	}

	public void setSearchInteractions(List<Interaction> val) {
		if (search.isInteractionsSet()) {
			search.setInteractions(val);
		}
	}

	public boolean isSearchInteractionsValid() {
		return search.isInteractionsSet();
	}

	public void setSearchInteractionsValid(boolean val) {
		if (val) {
			search.setInteractions(search.getInteractions());
		} else {
			search.unsetInteractions();
		}
	}

	public List<OfferRole> getSearchRoles() {
		return search.getRoles();
	}

	public void setSearchRoles(List<OfferRole> val) {
		if (search.isRolesSet()) {
			search.setRoles(val);
		}
	}

	public boolean isSearchRolesValid() {
		return search.isRolesSet();
	}

	public void setSearchRolesValid(boolean val) {
		if (val) {
			search.setRoles(search.getRoles());
		} else {
			search.unsetRoles();
		}
	}

	public List<OfferCost> getSearchCosts() {
		return search.getCosts();
	}

	public void setSearchCosts(List<OfferCost> val) {
		if (search.isCostsSet()) {
			search.setCosts(val);
		}
	}

	public boolean isSearchCostsValid() {
		return search.isCostsSet();
	}

	public void setSearchCostsValid(boolean val) {
		if (val) {
			search.setCosts(search.getCosts());
		} else {
			search.unsetCosts();
		}
	}

	/**
	 * Handle an ABC pager letter click: filter objects by specified starting letter
	 */
	public void letterClicked() {
		if (letter != null) {
			UIComponent comp = FacesUtils.getComponent("offers:list");
			HtmlDataTable tabla = (HtmlDataTable)comp;
			tabla.setFirst(0);

			search.setTitle(letter + "%");
		} else {
			search.unsetTitle();
		}
	}

	public Character getLetter() {
		return letter;
	}

	public void setLetter(Character letter) {
		this.letter = letter;
	}

	// Getters and setters to handle uploads

	// Getters and setters to manipulate active Offer object
	public java.lang.Integer getId() {
		return offer.getId();
	}

	public String getNumber() {
		return offer.getNumber();
	}

	public void setNumber(String number) {
		offer.setNumber(number);
	}

	public String getTitle() {
		return offer.getTitle();
	}

	public void setTitle(String title) {
		offer.setTitle(title);
	}

	public String getDescription() {
		return offer.getDescription();
	}

	public void setDescription(String description) {
		offer.setDescription(description);
	}

	public String getObservations() {
		return offer.getObservations();
	}

	public void setObservations(String observations) {
		offer.setObservations(observations);
	}

	public Date getCreationDate() {
		return offer.getCreationDate();
	}

	public void setCreationDate(Date creationDate) {
		offer.setCreationDate(creationDate);
	}

	public Date getValidityDate() {
		return offer.getValidityDate();
	}

	public void setValidityDate(Date validityDate) {
		offer.setValidityDate(validityDate);
	}

	public Date getMaturityDate() {
		return offer.getMaturityDate();
	}

	public void setMaturityDate(Date maturityDate) {
		offer.setMaturityDate(maturityDate);
	}

	public OfferState getOfferState() {
		return offer.getOfferState();
	}

	public void setOfferState(OfferState offerState) {
		offer.setOfferState(offerState);
	}

	public OfferPotential getOfferPotential() {
		return offer.getOfferPotential();
	}

	public void setOfferPotential(OfferPotential offerPotential) {
		offer.setOfferPotential(offerPotential);
	}

	public BigDecimal getTaxBaseRoles() {
		return offer.getTaxBaseRoles();
	}

	public BigDecimal getIvaRoles() {
		return offer.getIvaRoles();
	}

	public boolean isShowIvaIntoReport() {
		return offer.isShowIvaIntoReport();
	}

	public void setShowIvaIntoReport(boolean showIvaIntoReport) {
		this.offer.setShowIvaIntoReport(showIvaIntoReport);
	}

	public BigDecimal getTaxBaseCosts() {
		return offer.getTaxBaseCosts();
	}

	public BigDecimal getIvaCosts() {
		return offer.getIvaCosts();
	}

	public BigDecimal getTotalRoles() {
		return offer.getTotalRoles();
	}

	public BigDecimal getTotalCosts() {
		return offer.getTotalCosts();
	}

	public BigDecimal getTotal() {
		return offer.getTotal();
	}

	public Integer getOwnerId() {
		return offer.getOwnerId();
	}

	public void setOwnerId(Integer ownerId) {
		offer.setOwnerId(ownerId);
	}

	public Integer getDepartmentId() {
		return offer.getDepartmentId();
	}

	public void setDepartmentId(Integer departmentId) {
		offer.setDepartmentId(departmentId);
	}

	public Date getInsertDate() {
		return offer.getInsertDate();
	}

	public Date getUpdateDate() {
		return offer.getUpdateDate();
	}

	public User getUser() {
		return offer.getUser();
	}

	public void setUser(User user) {
		offer.setUser(user);
	}

	public Organization getOrganization() {
		
		if (offer.getOrganization() == null) {
			offer.setOrganization(OrganizationManager.getDefault().getMyOrganization());
		}
		return offer.getOrganization();
	}

	public void setOrganization(Organization organization) {
		offer.setOrganization(organization);
	}

	public Contact getContact() {
		return offer.getContact();
	}

	public void setContact(Contact contact) {
		offer.setContact(contact);
	}

	public OfferRejectReason getOfferRejectReason() {
		return offer.getOfferRejectReason();
	}

	public void setOfferRejectReason(OfferRejectReason offerRejectReason) {
		offer.setOfferRejectReason(offerRejectReason);
	}

	public Set<Interaction> getInteractions() {
		return offer.getInteractions();
	}

	public void setInteractions(Set<Interaction> interactions) {
		offer.setInteractions(interactions);
	}

	public Set<OfferRole> getRoles() {
		return offer.getRoles();
	}

	public void setRoles(Set<OfferRole> roles) {
		offer.setRoles(roles);
	}

	public Set<OfferCost> getCosts() {
		return offer.getCosts();
	}

	public void setCosts(Set<OfferCost> costs) {
		offer.setCosts(costs);
	}

	/* Offer - generated by stajanov (do not edit/delete) */

	/**
	 * Get the list of all organizations
	 * 
	 * @return the list of all organizations
	 */
	public List<SelectItem> getOrganizations() {
		List<Organization> refs = OrganizationManager.getDefault().getAllEntities(null, new SortCriteria("name"));

		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		for (Organization ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}

		ret.add(0, new SelectItem(""));

		return ret;
	}

	/** Selected organization * */

	public List<SelectItem> getContactsBySelectedOrganization() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();

		ContactSearch cs = new ContactSearch();
		cs.setOrganization(getOrganization());
		List<Contact> refs = ContactManager.getDefault().getAllEntities(cs, new SortCriteria("name"));
		for (Contact ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}

		// Añadiendo el campo vacio
		ret.add(0, new SelectItem(""));

		return ret;
	}

	public void onSelectedOrganizationChanged(ValueChangeEvent event) {
		setOrganization((Organization)event.getNewValue());

		InteractionBean ib = (InteractionBean)FacesUtils.getBean("interactionBean");
		ib.setSelectedOrganization(getOrganization());

		FacesUtils.renderResponse();
	}

	/**
	 * Get the list of all offerRejectReasons
	 * 
	 * @return the list of all offerRejectReasons
	 */
	public List<SelectItem> getOfferRejectReasons() {
		List<OfferRejectReason> refs = OfferRejectReasonManager.getDefault().getAllEntities(null,
				new SortCriteria("title"));

		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		for (OfferRejectReason ref : refs) {
			ret.add(new SelectItem(ref, ref.getTitle()));
		}

		// Añadiendo el campo vacio
		ret.add(0, new SelectItem(""));
		return ret;
	}

	/**
	 * Create a new empty instance of the one-to-many field
	 * 
	 * @return forward to the same page
	 */
	public String createInteractions() {
		Interaction item = new Interaction();
		item.setOffer(offer);
		if (item.getUser() == null) {
			item.setUser(SpringUtils.getPrincipal().getUser());
		}
		item.setCreationDate(new Date());
		
		InteractionBean iB = (InteractionBean)FacesUtils.getBean("interactionBean");
		iB.setInteractionFromOffer(item);
		iB.setSelectedOrganization(OrganizationManager.getDefault().getMyOrganization());

		if (offer.getInteractions() == null) {
			offer.setInteractions(new HashSet());
		}
		offer.getInteractions().add(item);

		return NavigationResults.OFFER_CREATE_INTERACTION;
	}

	/**
	 * Create a new empty instance of the one-to-many field
	 * 
	 * @return forward to the same page
	 */
	public String editInteractions() {
		UIData table = (UIData)FacesUtils.getComponent("offer:interactions");
		InteractionBean iB = (InteractionBean)FacesUtils.getBean("interactionBean");
		Interaction interactionSelectedInOfferEdit = (Interaction)table.getRowData();

		if (interactionSelectedInOfferEdit.getId() != null) { 
			Interaction interaction = InteractionManager.getDefault().getEntityById(interactionSelectedInOfferEdit.getId());
			iB.setInteractionFromOffer(interaction);
			iB.setSelectedOrganization(interaction.getProject().getClient());
		} else {
			iB.setInteractionFromOffer(interactionSelectedInOfferEdit);
		}
		
		return NavigationResults.OFFER_CREATE_INTERACTION;
	}

	/**
	 * Delete selected instance of the one-to-many field
	 * 
	 * @return forward to the same page
	 */
	public String deleteInteractions() {
		UIData table = (UIData)FacesUtils.getComponent("offer:interactions");
		offer.getInteractions().remove(table.getRowData());
		Integer idActual = offer.getId();
		save();
		offer = OfferManager.getDefault().getEntityById(idActual);
		return null;
	}

	public boolean getPuedoCrearProyecto() {
		return offer.getId() != null;
	}

	public boolean getPuedoImprimirOferta() {
		if (offer.getRoles() != null && !offer.getRoles().isEmpty()) showSubreportRole = "YES";

		// se comprueba que haya costes y que al menos uno sea facturable
		if (offer.getCosts() != null) {
			if (!offer.getCosts().isEmpty()) {
				for (OfferCost offerCost : offer.getCosts()) {
					if (offerCost.isBillable()) showSubreportCost = "YES";
				}
			}
		}
		return offer.getId() != null;
	}

	private Integer idSelectedOrganization = 0;

	public Integer getIdSelectedOrganization() {

		idSelectedOrganization = ConfigurationUtil.getDefault().getIdOurCompany();
		return idSelectedOrganization;
	}

	public void setIdSelectedOrganization(Integer idOrganization) {
		idSelectedOrganization = idOrganization;
	}

	public boolean isInteractionsAvailable() {
		return (offer.getId() != null);
	}

	public String getShowSubreportCost() {
		return showSubreportCost;
	}

	public String getShowSubreportRole() {
		return showSubreportRole;
	}
	
	public String convertFromOfferToBill () {
		final Bill bill = billManager.convertFromOfferToBill(offer);
		final BillBean billBean = (BillBean) FacesUtils.getBean("billBean");
		billBean.setBill(bill);
		return "editBill";
	}
	
	public String duplicateOffer () {
		offer = manager.duplicateOffer(offer);
		FacesUtils.clearDomRoot();
		return null;
	}	
}
