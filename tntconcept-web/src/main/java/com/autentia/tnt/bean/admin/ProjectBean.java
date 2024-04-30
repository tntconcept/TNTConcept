/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.bean.admin;

import java.math.*;
import java.text.NumberFormat;
import java.util.*;

import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.*;

import com.autentia.tnt.manager.contacts.OfferManager;
import org.apache.commons.logging.*;

import com.autentia.tnt.bean.*;
import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.*;
import com.autentia.tnt.dao.search.*;
import com.autentia.tnt.manager.admin.ProjectCostManager;
import com.autentia.tnt.manager.admin.ProjectManager;
import com.autentia.tnt.manager.admin.ProjectRoleManager;
import com.autentia.tnt.manager.contacts.OrganizationManager;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.upload.*;
import com.autentia.tnt.util.*;

import org.acegisecurity.acls.domain.BasePermission;

/**
 * UI bean for Project objects.
 *
 * @author stajanov code generator
 */
public class ProjectBean extends BaseBean {
    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;

    public BigDecimal getTotalCost() {
        return project.getTotalCost();
    }


    /**
     * Go to edit page
     *
     * @return forward to EDIT page
     */

    public String copy() {
        project = null;
        int id = Integer.parseInt(FacesUtils.getRequestParameter(ROW_ID));
        copy(id);
        return NavigationResults.COPY_FROM_OFFER;
    }

    public void copy(int offerId) {
        Offer offer = manager.copyFromOffer(offerId);
        copy(offer);
    }

    public void copy(Offer offer) {
        offerNumberInput.setValue(String.valueOf(offer.getNumber()));
        this.offer = offer;

        if (project == null) {
            project = new Project();
        }

        if (project.getId() == null) {
            HashSet<ProjectRole> roles = new HashSet<>();
            HashSet<ProjectCost> costs = new HashSet<>();

            for (OfferRole role : offer.getRoles()) {
                ProjectRole pr = new ProjectRole();
                pr.setName(role.getName());
                pr.setCostPerHour(role.getCostPerHour());
                pr.setExpectedHours(role.getExpectedHours());
                roles.add(pr);
            }

            for (OfferCost cost : offer.getCosts()) {
                ProjectCost pc = new ProjectCost();
                pc.setName(cost.getName());
                pc.setBillable(cost.isBillable());
                pc.setCost(cost.getCost());
                costs.add(pc);
            }

            project.setRoles(roles);
            project.setCosts(costs);
        }

        project.setClient(offer.getOrganization());
        project.setStartDate(new Date());
        project.setOpen(true);

        setClientByOffer = true;

    }

    private boolean setClientByOffer;

    /* project - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(ProjectBean.class);

    /**
     * Active search object
     */
    private ProjectSearch search = new ProjectSearch();
    private OfferSearch offerSearch = new OfferSearch();

    /**
     * Manager
     */
    private static ProjectManager manager = ProjectManager.getDefault();

    /**
     * Upload service
     */
    private static final Uploader uploader = UploaderFactory.getInstance("project");


    /**
     * Active Project object
     */
    private Project project;

    private Offer offer;

    /**
     * Default sort column
     */
    private String sortColumn = "name";
    /**
     * Default sort order
     */
    private boolean sortAscending = true;

    /**
     * Quick search letter for ABC pager control
     */
    private Character letter;

    private HtmlInputText offerNumberInput = new HtmlInputText();

    private List<Offer> offerNumberList = new ArrayList<>();

    /** Language resources */

    public ProjectBean(){
        search.setOpen(Boolean.TRUE);
    }

    /**
     * List projects. Order depends on Faces parameter sort.
     *
     * @return the list of all projects sorted by requested criterion
     */
    public List<Project> getAll() {
        // Force the EntityManager cache to have all the organizations before the projects,
        // to avoid running a query for each project.
        OrganizationManager.getDefault().getAllEntities(null, new SortCriteria("name"));
        return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
    }

    // Getters to list possible values of related entities


    public List<SelectItem> getClients() {
        final var clients = OrganizationManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<>();

        if (project != null && getClient() != null) {
            for (Organization ref : clients) {
                if (getClient().equals(ref)) {
                    ret.add(0, new SelectItem(ref, ref.getName()));
                } else {
                    ret.add(new SelectItem(ref, ref.getName()));
                }
            }
        } else {
            for (Organization ref : clients) {
                ret.add(new SelectItem(ref, ref.getName()));
            }
        }

        return ret;
    }

    public List<SelectItem> getBillingTypes() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle msg = ResourceBundle.getBundle("com.autentia.tnt.resources.messages", locale);

        return Arrays.stream(BillingType.values())
                .map(type -> new SelectItem(type, msg.getString("project.billingType."+type.name().toLowerCase()))).toList();
    }

    public List<SelectItem> getRequireEvidenceTypes() {

        return Arrays.stream(RequireEvidenceType.values())
                .map(type -> new SelectItem(type, type.name())).toList();
    }

    /**
     * Get the list of all roless
     *
     * @return the list of all roless
     */
    public List<SelectItem> getRoless() {
        List<ProjectRole> refs = ProjectRoleManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (ProjectRole ref : refs) {
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
        List<ProjectCost> refs = ProjectCostManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (ProjectCost ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }

    // Getters to list possible values of enum fields


    // Methods to create/remove instances of one-to-many entities (slave entities)

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createRoles() {
        ProjectRole item = new ProjectRole();
        item.setProject(project);
        item.setRequireEvidence(RequireEvidenceType.NO);
        if (project.getRoles() == null) {
            project.setRoles(new HashSet());
        }
        project.getRoles().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editRoles() {
        ProjectRole item = new ProjectRole();
        item.setProject(project);
        if (project.getRoles() == null) {
            project.setRoles(new HashSet());
        }
        project.getRoles().add(item);
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteRoles() {
        UIData table = (UIData) FacesUtils.getComponent("project:roles");
        ProjectRole pr = (ProjectRole) table.getRowData();

        if (checkProjectRolesHaveActivities(pr)){
            FacesUtils.addErrorMessage("project", "projectRole.deleteRestrict", String.format("'%s'",pr.getName()));
            return NavigationResults.EDIT;
        } else {
            project.getRoles().remove(pr);
        }
        return null;
    }

    private boolean checkProjectRolesHaveActivities(ProjectRole projectRole) {
        final Project projectStored = manager.getEntityById(project.getId());

        return projectStored.getRoles().stream()
                .anyMatch(storedRole -> storedRole.getId().equals(projectRole.getId()) && !storedRole.getActivities().isEmpty());
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createCosts() {
        ProjectCost item = new ProjectCost();
        item.setProject(project);
        if (project.getCosts() == null) {
            project.setCosts(new HashSet());
        }
        project.getCosts().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editCosts() {
        ProjectCost item = new ProjectCost();
        item.setProject(project);
        if (project.getCosts() == null) {
            project.setCosts(new HashSet());
        }
        project.getCosts().add(item);
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteCosts() {
        UIData table = (UIData) FacesUtils.getComponent("project:costs");
        project.getCosts().remove(table.getRowData());
        return null;
    }

    /**
     * Whether or not create button is available for user
     *
     * @return true if user can create objects of type Project
     */
    public boolean isCreateAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Project.class));
    }

    /**
     * Whether or not edit button is available for user
     *
     * @return true if user can edit current object
     */
    public boolean isEditAvailable() {
        return SpringUtils.isAclPermissionGranted(project, BasePermission.WRITE);
    }

    /**
     * Whether or not delete button is available for user
     *
     * @return true if user can delete current object
     */
    public boolean isDeleteAvailable() {
        return (project.getId() != null) && SpringUtils.isAclPermissionGranted(project, BasePermission.DELETE);
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
        project = new Project();
        project.setStartDate(new Date());
        project.setBillable(true);
        offerNumberInput.setDisabled(false);
        return NavigationResults.CREATE;
    }

    /**
     * Go to detail page
     *
     * @return forward to DETAIL page
     */
    public String detail() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter(ROW_ID));
        project = manager.getEntityById(id);

        String offerNumber = (project.getOffer() != null) ? project.getOffer().getNumber() : "";
        offerNumberInput.setValue(offerNumber);

        offerNumberInput.setDisabled(false);

        return SpringUtils.isAclPermissionGranted(project,
                BasePermission.WRITE) ? NavigationResults.EDIT : NavigationResults.DETAIL;
    }

    /**
     * Save bean and stay on it
     *
     * @return forward to list page
     */
    public String save() {

        doBeforeSave();

        if (offer != null && (project.getOffer() == null || !project.getOffer().equals(offer))) {
            project.setOffer(offer);
        }

        if (project.getId() == null) {
            manager.insertEntity(project);
        } else {
            manager.updateEntity(project);
        }

        // Unselect object
        project = null;

        return NavigationResults.LIST;
    }

    /**
     * Delete bean and go back to beans list
     *
     * @return forward to LIST page
     */
    public String delete() {
        boolean checkProjectHasRoles = !project.getRoles().isEmpty();

        if (checkProjectHasRoles){
            FacesUtils.addErrorMessage("project", "project.deleteRestrict");
            return NavigationResults.EDIT;
        } else {
            manager.deleteEntity(project);
            project = null;
            offerNumberInput.setValue("");
            offerNumberList.clear();
            return NavigationResults.LIST;
        }
    }

    /**
     * Go back to beans list
     *
     * @return forward to LIST page
     */
    public String list() {
        project = null;
        offerNumberInput.setValue("");
        offerNumberList.clear();
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
    public boolean isProjectSelected() {
        return project != null;
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
    public ProjectSearch getSearch() {
        return search;
    }


    public Date getSearchStartStartDate() {
        return search.getStartStartDate();
    }

    public void setSearchStartStartDate(Date val) {
        if (val != null) {
            search.setStartStartDate(val);
        } else {
            search.unsetStartStartDate();
        }
    }

    public boolean isSearchStartStartDateValid() {
        return search.isStartStartDateSet();
    }

    public void setSearchStartStartDateValid(boolean val) {
        if (val) {
            search.setStartStartDate(search.getStartStartDate());
        } else {
            search.unsetStartStartDate();
        }
    }

    public Date getSearchEndStartDate() {
        return search.getEndStartDate();
    }

    public void setSearchEndStartDate(Date val) {
        if (val != null) {
            search.setEndStartDate(val);
        } else {
            search.unsetEndStartDate();
        }
    }

    public boolean isSearchEndStartDateValid() {
        return search.isEndStartDateSet();
    }

    public void setSearchEndStartDateValid(boolean val) {
        if (val) {
            search.setEndStartDate(search.getEndStartDate());
        } else {
            search.unsetEndStartDate();
        }
    }


    public Date getSearchStartEndDate() {
        return search.getStartEndDate();
    }

    public void setSearchStartEndDate(Date val) {
        if (val != null) {
            search.setStartEndDate(val);
        } else {
            search.unsetStartEndDate();
        }
    }

    public boolean isSearchStartEndDateValid() {
        return search.isStartEndDateSet();
    }

    public void setSearchStartEndDateValid(boolean val) {
        if (val) {
            search.setStartEndDate(search.getStartEndDate());
        } else {
            search.unsetStartEndDate();
        }
    }

    public Date getSearchEndEndDate() {
        return search.getEndEndDate();
    }

    public void setSearchEndEndDate(Date val) {
        if (val != null) {
            search.setEndEndDate(val);
        } else {
            search.unsetEndEndDate();
        }
    }

    public boolean isSearchEndEndDateValid() {
        return search.isEndEndDateSet();
    }

    public void setSearchEndEndDateValid(boolean val) {
        if (val) {
            search.setEndEndDate(search.getEndEndDate());
        } else {
            search.unsetEndEndDate();
        }
    }


    public Boolean getSearchOpen() {
        return search.getOpen();
    }

    public void setSearchOpen(Boolean val) {
        if (search.isOpenSet()) {
            search.setOpen(val);
        }
    }

    public boolean isSearchOpenValid() {
        return search.isOpenSet();
    }

    public void setSearchOpenValid(boolean val) {
        if (val) {
            search.setOpen(search.getOpen());
        } else {
            search.unsetOpen();
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


    public Boolean getSearchBillable() {
        return search.getBillable();
    }

    public void setSearchBillable(Boolean val) {
        if (search.isBillableSet()) {
            search.setBillable(val);
        }
    }

    public boolean isSearchBillableValid() {
        return search.isBillableSet();
    }

    public void setSearchBillableValid(boolean val) {
        if (val) {
            search.setBillable(search.getBillable());
        } else {
            search.unsetBillable();
        }
    }


    public BillingType getSearchBillingType() {
        return search.getBillingType();
    }

    public void setSearchBillingType(BillingType val) {
        if (search.isBillingTypeSet()) {
            search.setBillingType(val);
        }
    }

    public boolean isSearchBillingTypeValid() {
        return search.isBillingTypeSet();
    }

    public void setSearchBillingTypeValid(boolean val) {
        if (val) {
            search.setBillingType(search.getBillingType());
        } else {
            search.unsetBillingType();
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


    public Organization getSearchClient() {
        return search.getClient();
    }

    public void setSearchClient(Organization val) {
        if (search.isClientSet()) {
            search.setClient(val);
        }
    }

    public boolean isSearchClientValid() {
        return search.isClientSet();
    }

    public void setSearchClientValid(boolean val) {
        if (val) {
            search.setClient(search.getClient());
        } else {
            search.unsetClient();
        }
    }

    public BigDecimal getSearchEstimatedCost() {
        return search.getEstimatedCost();
    }

    public void setSearchEstimatedCost(BigDecimal val) {
        if (search.isEstimatedCostSet()) {
            search.setEstimatedCost(val);
        }
    }

    public boolean isSearchEstimatedCostValid() {
        return search.isEstimatedCostSet();
    }

    public void setSearchEstimatedCostValid(boolean val) {
        if (val) {
            search.setEstimatedCost(search.getEstimatedCost());
        } else {
            search.unsetEstimatedCost();
        }
    }


    public List<ProjectRole> getSearchRoles() {
        return search.getRoles();
    }

    public void setSearchRoles(List<ProjectRole> val) {
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


    public List<ProjectCost> getSearchCosts() {
        return search.getCosts();
    }

    public void setSearchCosts(List<ProjectCost> val) {
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


    public String getSearchOrder() {
        return search.getOrder();
    }

    public void setSearchOrder(String val) {
        if (search.isOrderSet()) {
            search.setOrder(val);
        }
    }

    public boolean isSearchOrderValid() {
        return search.isOrderSet();
    }

    public void setSearchOrderValid(boolean val) {
        if (val) {
            search.setOrder(search.getOrder());
        } else {
            search.unsetOrder();
        }
    }

    /**
     * Handle an ABC pager letter click: filter objects by specified starting letter
     */
    public void letterClicked() {
        if (letter != null) {
            UIComponent comp = FacesUtils.getComponent("projects:list");
            HtmlDataTable tabla = (HtmlDataTable) comp;
            tabla.setFirst(0);

            search.setName(letter + "%");
        } else {
            search.unsetName();
        }
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    // Getters and setters to handle uploads

    // Getters and setters to manipulate active Project object
    public java.lang.Integer getId() {
        return project.getId();
    }


    public Date getStartDate() {
        return project.getStartDate();
    }

    public void setStartDate(Date startDate) {
        project.setStartDate(startDate);
    }


    public Date getEndDate() {
        return project.getEndDate();
    }

    public void setEndDate(Date endDate) {
        project.setEndDate(endDate);
    }


    public Boolean getOpen() {
        return project.getOpen();
    }

    public void setOpen(Boolean open) {
        project.setOpen(open);
    }


    public String getName() {
        return project.getName();
    }

    public void setName(String name) {
        project.setName(name);
    }


    public String getDescription() {
        return project.getDescription();
    }

    public void setDescription(String description) {
        project.setDescription(description);
    }


    public Boolean getBillable() {
        return project.getBillable();
    }

    public void setBillable(Boolean billable) {
        project.setBillable(billable);
    }


    public BillingType getBillingType() {
        return project.getBillingType();
    }

    public void setBillingType(BillingType billingType) {
        project.setBillingType(billingType);
    }


    public Integer getOwnerId() {
        return project.getOwnerId();
    }

    public void setOwnerId(Integer ownerId) {
        project.setOwnerId(ownerId);
    }


    public Integer getDepartmentId() {
        return project.getDepartmentId();
    }

    public void setDepartmentId(Integer departmentId) {
        project.setDepartmentId(departmentId);
    }


    public Date getInsertDate() {
        return project.getInsertDate();
    }

    public Date getUpdateDate() {
        return project.getUpdateDate();
    }

    public String getOrder() {
        return project.getOrder();
    }

    public void setOrder(String order) {
        project.setOrder(order);
    }

    public Organization getClient() {
        return project.getClient();
    }

    public void setClient(Organization client) {
        if (project.getId() == null && setClientByOffer) {
            project.setClient(offer.getOrganization());
            setClientByOffer = false;
        } else {
            project.setClient(client);
        }
    }

    public BigDecimal getEstimatedCost() {
        return project.getEstimatedCost();
    }

    public void setEstimatedCost(BigDecimal estimatedCost) {
        project.setEstimatedCost(estimatedCost);
    }


    public Set<ProjectRole> getRoles() {

        return project.getRoles();
    }

    public void setRoles(Set<ProjectRole> roles) {
        project.setRoles(roles);
    }


    public Set<ProjectCost> getCosts() {
        return project.getCosts();
    }

    public void setCosts(Set<ProjectCost> costs) {
        project.setCosts(costs);
    }

    /* project - generated by stajanov (do not edit/delete) */

    // Para arreglar un problema que surge en copy from Offer
    public String doBeforeSave() {
        if (project != null) {
            if (project.getRoles() != null) {
                for (ProjectRole pr : project.getRoles()) {
                    if (pr.getProject() == null) pr.setProject(project);

                }
            }

            if (project.getCosts() != null) {
                for (ProjectCost pc : project.getCosts()) {
                    if (pc.getProject() == null) pc.setProject(project);

                }
            }
        }

        offerNumberInput.setValue("");
        offerNumberList.clear();

        return null;
    }

    public String getWorkedHours() {
        long total = project.getWorkedHours();
        String totalWorked = "0";

        if (total != 0) {
            NumberFormat format = NumberFormat.getInstance();
            format.setMaximumFractionDigits(2);
            totalWorked = format.format(total / 60);  // Convertimos a horas
        }

        return totalWorked;
    }

    public long getExpectedHours() {

        return project.getExpectedHours();
    }

    public double getPercentageWorked() {

        return project.getPercentageWorked();

    }

    public double getPercentageCosts() {
        return project.getPercentageCosts();
    }

    public String getCostPerProject() {
        BigDecimal total = project.getCostPerProject();
        String totalCost;

        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);
        totalCost = format.format(total.doubleValue());

        return totalCost;
    }


    public HtmlInputText getOfferNumberInput() {
        return offerNumberInput;
    }

    public void setOfferNumberInput(HtmlInputText offerNumberInput) {
        this.offerNumberInput = offerNumberInput;
    }

    private List<Offer> getOfferNumberList() {
        return offerNumberList;
    }


    public List<SelectItem> getFilterOffer() {

        final List<SelectItem> ret = new ArrayList<>();

        for (Offer offer : getOfferNumberList()) {
            ret.add(new SelectItem(offer.getId(), offer.getNumber() + " - " + offer.getTitle()));
        }

        return ret;
    }


    private void setOfferNumberList(List<Offer> offerNumberList) {
        this.offerNumberList = offerNumberList;
    }


    public void refreshOfferList(ValueChangeEvent event) {
        String input = event.getNewValue().toString();
        StringBuilder query = new StringBuilder();
        List<Offer> offerList = new ArrayList<>();

        if (!input.isEmpty() && input.length() > 3) {

            query.append(input).append("%");

            offerSearch.setNumber(query.toString());

            offerList = OfferManager.getDefault().getAllEntities(offerSearch, new SortCriteria("number", true));

            this.offerNumberInput.setValue(event.getNewValue().toString());

            if (offerList.size() == 1) {
                copy(offerList.get(0));
            }
        }

        this.setOfferNumberList(offerList);
    }

    public void refreshOffer(ValueChangeEvent event) {
        int offerId = Integer.parseInt(event.getNewValue().toString());
        if (offerId > 0) copy(offerId);
    }
}
