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

package com.autentia.tnt.bean.activity;

import java.util.*;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.*;

import org.apache.commons.logging.*;

import com.autentia.tnt.bean.*;
import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.*;
import com.autentia.tnt.dao.search.*;
import com.autentia.tnt.manager.activity.ObjectiveManager;
import com.autentia.tnt.manager.admin.ProjectManager;
import com.autentia.tnt.manager.admin.SettingManager;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.contacts.OrganizationManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.upload.*;
import com.autentia.tnt.util.*;

import org.acegisecurity.acls.domain.BasePermission;

/**
 * UI bean for Objective objects.
 * @author stajanov code generator
 */
public class ObjectiveBean extends BaseBean
{
  /** Serial version field */
  private static final long serialVersionUID = -1L;
  
  /** Number of child objectives that will be created when propagating a past objective */
  private int childObjectivesCount = ConfigurationUtil.getDefault().getChildObjectivesCount();
  
  private final static AuthenticationManager authMgr = AuthenticationManager.getDefault();

  private final static ObjectiveManager objectiveMgr = ObjectiveManager.getDefault();
  
  /** Organization DAO **/
  private static final OrganizationManager organizationManager = OrganizationManager.getDefault();
  
	/** Settings manager */
	private static final SettingManager settings = SettingManager.getDefault();
	
  /** Active search object */
  private OrganizationSearch organizationSearch = new OrganizationSearch();
  
  /** Selected organization * */
  private Organization selectedOrganization = null;
  
  /** List of organizations */
  private List<Organization> organizations = new ArrayList<Organization>();
  
  private int userSelected = ALL_USERS;
  
  
  
  public static int ALL_USERS = -1;
  
  
  public void onSelectedUserChanged(ValueChangeEvent event) {
	    UIComponent comp1 = FacesUtils.getComponent("objectives:listThisWeek");
		HtmlDataTable tabla1 = (HtmlDataTable) comp1;
		tabla1.setFirst(0);
		
		UIComponent comp2 = FacesUtils.getComponent("objectives:listPastNotCompleted");
		HtmlDataTable tabla2 = (HtmlDataTable) comp2;
		tabla2.setFirst(0);
  }
  
  
  public List<SelectItem> getFiltrableUsers(){  
	    
	  	List<User> refs = new ArrayList<User>();
	  
	  	ObjectiveSearch search = new ObjectiveSearch();
	  		  	
	    List<Objective> objetivos = manager.getAllEntities(search, null); 
	    
	    
	    UserManager userManager = UserManager.getDefault();
	    UserSearch userSearch = new UserSearch();
	    	userSearch.setActive(true);
	    int sizeMaxUser = userManager.getAllEntities(userSearch, null).size();
	    
	    for(Objective obj: objetivos) {
	    	User us = obj.getUser();
	    	if(us.isActive() && !refs.contains(us)) {
	    		refs.add(us);
	    	}
	    	
	    	if(refs.size()>=sizeMaxUser)
	    		break;
	    	
	    }	    
	    
	    Collections.sort(refs, new UserComparator());
	    ArrayList<SelectItem> ret = new ArrayList<SelectItem>();    
	    
	    ret.add(new SelectItem(Integer.valueOf(ALL_USERS),"--"));
	    
	    for( User ref : refs ){
	      ret.add( new SelectItem( ref.getId(), ref.getName() ) );
	    }
	    
	   
	    
	    return ret;
  
  }
  
  public boolean isFiltrableUser() {	  
	  return getFiltrableUsers().size()>2;
  }
  
  
  /**
   * Mark a listed objective as completed.
   * @return forward to current page (null)
   */
  public String complete()
  {
    Integer id = Integer.parseInt( FacesUtils.getRequestParameter(ROW_ID) );
    objective = objectiveMgr.getEntityById(id);
    objective.setState(ObjectiveState.FINISHED);
    objectiveMgr.updateEntity(objective);
    return null;
  }
  
  /**
   * Show propagation screen for a non-completed objective.
   * @return forward to propagation page
   */
  public String propagate()
  {
    Integer id = Integer.parseInt( FacesUtils.getRequestParameter(ROW_ID) );
    objective = objectiveMgr.getEntityById(id);
    return NavigationResults.PROPAGATE;
  }
  
  /**
   * Confirm propagation action for a non-completed objective.
   * @return forward to list page
   */
  public String confirmPropagate()
  {
    
    // TODO: añadir campo padre del objetivo para trazabilidad de los objetivos propagados
    
    // Set objective as expired and update it
    objective.setState( ObjectiveState.EXPIRED );
    objectiveMgr.updateEntity(objective);
    
    // Create child objectives
    for( int i=0 ; i<childObjectivesCount ; i++ )
    {
      Objective child = new Objective();
      child.setEndDate(calculateEndDate());
      child.setStartDate(new Date());
      child.setProject(objective.getProject());
      child.setUser(objective.getUser());
      child.setName(FacesUtils.formatMessage("msg.pendingObjective",i+1,objective.getName()));
      child.setState(ObjectiveState.PENDING);
      objectiveMgr.insertEntity(child);
    }
    
    return NavigationResults.LIST;
  }
  
  
  public List<Objective> getPastNotCompleted(boolean filterUser) {
	// Search objectives
	    ObjectiveSearch search = new ObjectiveSearch();
	    
	    Date fechaPlusOne = null;
	    
	    Calendar cal = new GregorianCalendar();
	    	cal.add(Calendar.DATE, -1);    
	    
	    search.setEndEndDate(cal.getTime());
	    search.setState(ObjectiveState.PENDING);
	    
	    if(userSelected!=ALL_USERS && filterUser) {    	
	    	search.setUser(UserManager.getDefault().getEntityById(userSelected));
	    }
	    
	    return objectiveMgr.getAllEntities( search, new SortCriteria(sortColumn,sortAscending) );
  }
  
  
  /**
   * List past non-complete objectives sorted by name.
   * @return the list of objectives
   */
  public List<Objective> getPastNotCompleted()
  {
        return getPastNotCompleted(true);
    
  }
  
  public List<Objective> getCurrent(boolean filterUser) 
  {
	// Calculate future friday
      
	    // Search objectives
	    ObjectiveSearch s = new ObjectiveSearch();
	    
	    Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, -1);	
		
	    s.setStartEndDate(cal.getTime());
	    
	    if(userSelected!=ALL_USERS && filterUser) {    	
	    	s.setUser(UserManager.getDefault().getEntityById(userSelected));
	    }
	    
	    
	    return objectiveMgr.getAllEntities( s, new SortCriteria(sortColumn,sortAscending) );
  }
  
  
  /**
   * List objectives for this week. Order depends on Faces parameter sort.
   * @return the list of objectives sorted by requested criterion
   */
  public List<Objective> getCurrent()
  {
    return getCurrent(true);
  }
  
  /**
   * Get number of child objectives to be created
   * @return number of child objectives to be created
   */
  public int getChildObjectivesCount()
  {
    return childObjectivesCount;
  }
  
  /**
   * Set number of child objectives to be created
   * @param childObjectivesCount number of child objectives to be created
   */
  public void setChildObjectivesCount( int childObjectivesCount )
  {
    this.childObjectivesCount = childObjectivesCount;
  }
  
  /**
   * Get possible values for childObjectivesCount to be shown in a combo box.
   * @return list of possible values for childObjectivesCount
   */
  public List<SelectItem> getChildObjectivesCounts()
  {
    List<SelectItem> ret = new ArrayList<SelectItem>();
    int i;
    for( i=ConfigurationUtil.getDefault().getMinChildObjectivesCount() ;
    i<=ConfigurationUtil.getDefault().getMaxChildObjectivesCount() ;
    i++ )
    {
      ret.add( new SelectItem( i, Integer.toString(i) ) );
    }
    return ret;
  }
  
  /**
   * Calculate objective end date: the end date is the nearest future friday
   * starting today.
   * @return the end date based on today's date
   */
  private Date calculateEndDate()
  {
    return moveToFriday( new Date(), true );
  }
  
  /**
   * Move a date to one of its surrounding fridays.
   * @param d the reference date
   * @param inFuture whether to move to future/previous friday
   * @return the requested friday
   */
  private Date moveToFriday( Date d, boolean inFuture )
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    switch( cal.get(Calendar.DAY_OF_WEEK) )
    {
      case Calendar.MONDAY:
        cal.add( Calendar.DAY_OF_WEEK, inFuture ? 4 : -3 );
        break;
      case Calendar.TUESDAY:
        cal.add( Calendar.DAY_OF_WEEK, inFuture ? 3 : -4 );
        break;
      case Calendar.WEDNESDAY:
        cal.add( Calendar.DAY_OF_WEEK, inFuture ? 2 : -5 );
        break;
      case Calendar.THURSDAY:
        cal.add( Calendar.DAY_OF_WEEK, inFuture ? 1 : -6 );
        break;
      case Calendar.FRIDAY:
        cal.add( Calendar.DAY_OF_WEEK, inFuture ? 0 : -7 );
        break;
      case Calendar.SATURDAY:
        cal.add( Calendar.DAY_OF_WEEK, inFuture ? 6 : -1 );
        break;
      case Calendar.SUNDAY:
        cal.add( Calendar.DAY_OF_WEEK, inFuture ? 5 : -2 );
        break;
    }
    return cal.getTime();
  }

  @Override
  public String doAfterSave( String result )
  {
	Setting val = settings.get(SettingPath.OBJECTIVE_LAST_PROJECTID, true);
	SettingManager.setValue(val, objective.getProject().getId());
	 settings.save(val);
    return super.doAfterSave( result );
  }
	
	
  
/* objective - generated by stajanov (do not edit/delete) */

















  /** Logger */
  private static final Log log = LogFactory.getLog(ObjectiveBean.class);

 /** Active search object */
  private ObjectiveSearch search = new ObjectiveSearch();

  /** Manager */
  private static ObjectiveManager manager = ObjectiveManager.getDefault();
  
  /** Upload service */
  private static final Uploader uploader = UploaderFactory.getInstance("objective");
    
                                                                  
  /** Active Objective object */
  private Objective objective;
  
  /** Default sort column */
  private String sortColumn = "name";
    
  /** Default sort order */
  private boolean sortAscending = true;

  
  /**
   * List objectives. Order depends on Faces parameter sort.
   * @return the list of all objectives sorted by requested criterion
   */
  public List<Objective> getAll(){
    return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending) );      
  }

  // Getters to list possible values of related entities
      
    
  /**
   * Get the list of all users
   * @return the list of all users
   */
  public List<SelectItem> getUsers(){  
    List<User> refs = UserManager.getDefault().getAllEntities( null, new SortCriteria("name") );
	  
    ArrayList<SelectItem> ret = new ArrayList<SelectItem>();  
    for( User ref : refs ){
      ret.add( new SelectItem( ref, ref.getName() ) );
    }
    return ret;	
  }
      
    
  /**
   * Get the list of all projects
   * @return the list of all projects
   */
  public List<SelectItem> getProjects(){  
    List<Project> refs = ProjectManager.getDefault().getAllEntities( null, new SortCriteria("name") );
	  
    ArrayList<SelectItem> ret = new ArrayList<SelectItem>();  
    for( Project ref : refs ){
      ret.add( new SelectItem( ref, ref.getName() ) );
    }
    return ret;	
  }
        
  // Getters to list possible values of enum fields
            
  public String getStateFormatted(){
    if(this.getState()!=null) {
      return FacesUtils.formatMessage("ObjectiveState."+this.getState().name());
    } else {
      return "";
    }
  }

  /**
   * Get the list of all State values
   * @return the list of all State values
   */
  public List<SelectItem> getStates(){
    ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
    ObjectiveState[] vals = ObjectiveState.values();
    for( ObjectiveState val : vals ){
      ret.add( new SelectItem( val, FacesUtils.formatMessage("ObjectiveState."+val.name()) ) );
    }
    return ret;
  }
                                                

  // Methods to create/remove instances of one-to-many entities (slave entities)
  
  /**
   * Whether or not create button is available for user
   * @return true if user can create objects of type Objective
   */
  public boolean isCreateAvailable()
  {
    return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Objective.class));
  }

  /**
   * Whether or not edit button is available for user
   * @return true if user can edit current object
   */
  public boolean isEditAvailable()
  {
    return SpringUtils.isAclPermissionGranted(objective,BasePermission.WRITE);
  }

  /**
   * Whether or not delete button is available for user
   * @return true if user can delete current object
   */
  public boolean isDeleteAvailable()
  {
    return (objective.getId()!=null) &&
	   SpringUtils.isAclPermissionGranted(objective,BasePermission.DELETE);
  }

  /**
   * Go to create page
   * @return forward to CREATE page
   */
  public String create(){
	sortColumn = "name";
    objective = new Objective();
    objective.setStartDate( new Date() );
    objective.setEndDate( calculateEndDate() );
    objective.setUser( authMgr.getCurrentPrincipal().getUser() );
    objective.setState( ObjectiveState.PENDING );   
		// Preselect last selected options\n\
		int projectId = SettingManager.getInt(settings.get( SettingPath.OBJECTIVE_LAST_PROJECTID, false ), -1);
		if( projectId!=-1 )
		{
			Project project = ProjectManager.getDefault().getEntityById(projectId);
			setSelectedOrganization(project.getClient());
			objective.setProject(project);
		}
		return NavigationResults.CREATE;
  }

  /**
   * Go to detail page
   * @return forward to DETAIL page
   */
  public String detail(){
	  
	sortColumn = "name";
	
    Integer id = Integer.parseInt( FacesUtils.getRequestParameter(ROW_ID) );
    objective = manager.getEntityById(id);
    
    return SpringUtils.isAclPermissionGranted( objective, BasePermission.WRITE )
	    ? NavigationResults.EDIT
	    : NavigationResults.DETAIL;
  }

  /**
   * Save bean and stay on it
   * @return forward to list page
   */
  public String save(){
  
      doBeforeSave();
  
      if( objective.getId()==null ){
        manager.insertEntity(objective);
      } else {
        manager.updateEntity(objective);
      }
      

                                                                                                                                      
      // Calls an after save action
      String result = doAfterSave(NavigationResults.LIST);

      // Unselect object
      objective = null;

      return result;
  }
    
  /**
   * Delete bean and go back to beans list
   * @return forward to LIST page
   */
  public String delete(){
    manager.deleteEntity(objective);
    objective = null;
    return NavigationResults.LIST;
  }

  /**
   * Go back to beans list
   * @return forward to LIST page
   */
  public String list(){
    return NavigationResults.LIST;
  }

  /**
   * Go to search page
   * @return forward to SEARCH page
   */
  public String search(){
    return NavigationResults.SEARCH;
  }

  /** 
   * Check if we have an active object.
   * @return true is an object is selected
   */
  public boolean isObjectiveSelected(){
    return objective!=null;
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
  public ObjectiveSearch getSearch(){
    return search;
  }
      
    
    
        
    
    public ObjectiveState getSearchState(){
        return search.getState();
    }
    public void setSearchState( ObjectiveState val ){
        if( search.isStateSet() ) {
          search.setState( val );
        }
    }
    public boolean isSearchStateValid(){
        return search.isStateSet();
    }
    public void setSearchStateValid( boolean val ){
        if( val ){
          search.setState( search.getState() );
        } else {
          search.unsetState();
        }
    }
        
    
    
    
    
    public String getSearchName(){
        return search.getName();
    }
    public void setSearchName( String val ){
        if( search.isNameSet() ) {
          search.setName( val );
        }
    }
    public boolean isSearchNameValid(){
        return search.isNameSet();
    }
    public void setSearchNameValid( boolean val ){
        if( val ){
          search.setName( search.getName() );
        } else {
          search.unsetName();
        }
    }
        
    
    
    
    
    public String getSearchLog(){
        return search.getLog();
    }
    public void setSearchLog( String val ){
        if( search.isLogSet() ) {
          search.setLog( val );
        }
    }
    public boolean isSearchLogValid(){
        return search.isLogSet();
    }
    public void setSearchLogValid( boolean val ){
        if( val ){
          search.setLog( search.getLog() );
        } else {
          search.unsetLog();
        }
    }
        
    
        
      
    public Date getSearchStartStartDate(){
        return search.getStartStartDate();
    }
    public void setSearchStartStartDate( Date val ){
        if( val!=null ){
          search.setStartStartDate( val );
        } else {
          search.unsetStartStartDate();
        }
    }
    public boolean isSearchStartStartDateValid(){
        return search.isStartStartDateSet();
    }
    public void setSearchStartStartDateValid( boolean val ){
        if( val ){
          search.setStartStartDate( search.getStartStartDate() );
        } else {
          search.unsetStartStartDate();
        }
    }
    public Date getSearchEndStartDate(){
        return search.getEndStartDate();
    }
    public void setSearchEndStartDate( Date val ){
        if( val!=null ){
          search.setEndStartDate( val );
        } else {
          search.unsetEndStartDate();
        }
    }
    public boolean isSearchEndStartDateValid(){
        return search.isEndStartDateSet();
    }
    public void setSearchEndStartDateValid( boolean val ){
        if( val ){
          search.setEndStartDate( search.getEndStartDate() );
        } else {
          search.unsetEndStartDate();
        }
    }

        
    
        
      
    public Date getSearchStartEndDate(){
        return search.getStartEndDate();
    }
    public void setSearchStartEndDate( Date val ){
        if( val!=null ){
          search.setStartEndDate( val );
        } else {
          search.unsetStartEndDate();
        }
    }
    public boolean isSearchStartEndDateValid(){
        return search.isStartEndDateSet();
    }
    public void setSearchStartEndDateValid( boolean val ){
        if( val ){
          search.setStartEndDate( search.getStartEndDate() );
        } else {
          search.unsetStartEndDate();
        }
    }
    public Date getSearchEndEndDate(){
        return search.getEndEndDate();
    }
    public void setSearchEndEndDate( Date val ){
        if( val!=null ){
          search.setEndEndDate( val );
        } else {
          search.unsetEndEndDate();
        }
    }
    public boolean isSearchEndEndDateValid(){
        return search.isEndEndDateSet();
    }
    public void setSearchEndEndDateValid( boolean val ){
        if( val ){
          search.setEndEndDate( search.getEndEndDate() );
        } else {
          search.unsetEndEndDate();
        }
    }

        
    
    
    
    
    public Integer getSearchDepartmentId(){
        return search.getDepartmentId();
    }
    public void setSearchDepartmentId( Integer val ){
        if( search.isDepartmentIdSet() ) {
          search.setDepartmentId( val );
        }
    }
    public boolean isSearchDepartmentIdValid(){
        return search.isDepartmentIdSet();
    }
    public void setSearchDepartmentIdValid( boolean val ){
        if( val ){
          search.setDepartmentId( search.getDepartmentId() );
        } else {
          search.unsetDepartmentId();
        }
    }
        
    
        
      
    public Date getSearchStartInsertDate(){
        return search.getStartInsertDate();
    }
    public void setSearchStartInsertDate( Date val ){
        if( val!=null ){
          search.setStartInsertDate( val );
        } else {
          search.unsetStartInsertDate();
        }
    }
    public boolean isSearchStartInsertDateValid(){
        return search.isStartInsertDateSet();
    }
    public void setSearchStartInsertDateValid( boolean val ){
        if( val ){
          search.setStartInsertDate( search.getStartInsertDate() );
        } else {
          search.unsetStartInsertDate();
        }
    }
    public Date getSearchEndInsertDate(){
        return search.getEndInsertDate();
    }
    public void setSearchEndInsertDate( Date val ){
        if( val!=null ){
          search.setEndInsertDate( val );
        } else {
          search.unsetEndInsertDate();
        }
    }
    public boolean isSearchEndInsertDateValid(){
        return search.isEndInsertDateSet();
    }
    public void setSearchEndInsertDateValid( boolean val ){
        if( val ){
          search.setEndInsertDate( search.getEndInsertDate() );
        } else {
          search.unsetEndInsertDate();
        }
    }

        
    
        
      
    public Date getSearchStartUpdateDate(){
        return search.getStartUpdateDate();
    }
    public void setSearchStartUpdateDate( Date val ){
        if( val!=null ){
          search.setStartUpdateDate( val );
        } else {
          search.unsetStartUpdateDate();
        }
    }
    public boolean isSearchStartUpdateDateValid(){
        return search.isStartUpdateDateSet();
    }
    public void setSearchStartUpdateDateValid( boolean val ){
        if( val ){
          search.setStartUpdateDate( search.getStartUpdateDate() );
        } else {
          search.unsetStartUpdateDate();
        }
    }
    public Date getSearchEndUpdateDate(){
        return search.getEndUpdateDate();
    }
    public void setSearchEndUpdateDate( Date val ){
        if( val!=null ){
          search.setEndUpdateDate( val );
        } else {
          search.unsetEndUpdateDate();
        }
    }
    public boolean isSearchEndUpdateDateValid(){
        return search.isEndUpdateDateSet();
    }
    public void setSearchEndUpdateDateValid( boolean val ){
        if( val ){
          search.setEndUpdateDate( search.getEndUpdateDate() );
        } else {
          search.unsetEndUpdateDate();
        }
    }

          
    
    
        
    
    public User getSearchUser(){
        return search.getUser();
    }
    public void setSearchUser( User val ){
        if( search.isUserSet() ) {
          search.setUser( val );
        }
    }
    public boolean isSearchUserValid(){
        return search.isUserSet();
    }
    public void setSearchUserValid( boolean val ){
        if( val ){
          search.setUser( search.getUser() );
        } else {
          search.unsetUser();
        }
    }
        
    
    
        
    
    public Project getSearchProject(){
        return search.getProject();
    }
    public void setSearchProject( Project val ){
        if( search.isProjectSet() ) {
          search.setProject( val );
        }
    }
    public boolean isSearchProjectValid(){
        return search.isProjectSet();
    }
    public void setSearchProjectValid( boolean val ){
        if( val ){
          search.setProject( search.getProject() );
        } else {
          search.unsetProject();
        }
    }
        
  
  // Getters and setters to handle uploads
                                                                  
  // Getters and setters to manipulate active Objective object
  public java.lang.Integer getId() {
      return objective.getId();
  }
      
    
  
  
    public ObjectiveState getState() {
    return objective.getState();
  }
  public void setState( ObjectiveState state ) {
    objective.setState( state );
  }
        
    
    
    public String getName() {
    return objective.getName();
  }
  public void setName( String name ) {
    objective.setName( name );
  }
        
    
    
    public String getLog() {
    return objective.getLog();
  }
  public void setLog( String log ) {
    objective.setLog( log );
  }
        
    
    
    public Date getStartDate() {
    return objective.getStartDate();
  }
  public void setStartDate( Date startDate ) {
    objective.setStartDate( startDate );
  }
        
    
    
    public Date getEndDate() {
    return objective.getEndDate();
  }
  public void setEndDate( Date endDate ) {
    objective.setEndDate( endDate );
  }
        
    
    
    public Integer getDepartmentId() {
    return objective.getDepartmentId();
  }
  public void setDepartmentId( Integer departmentId ) {
    objective.setDepartmentId( departmentId );
  }
        
    
    
    public Date getInsertDate() {
    return objective.getInsertDate();
  }
    
    public Date getUpdateDate() {
    return objective.getUpdateDate();
  }
    public User getUser() {
    return objective.getUser();
  }
  public void setUser( User user ) {
    objective.setUser( user );
  }
        
    
    
  
    public Project getProject() {
    return objective.getProject();
  }
  public void setProject( Project project ) {
    objective.setProject( project );
  }
        
/* objective - generated by stajanov (do not edit/delete) */
  
  /**
   * Get the list of all organizations
   *
   * @return the list of all organizations
   */
  public List<SelectItem> getOrganizations()
  {
    ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
    organizations = organizationManager.getAllEntities(organizationSearch, new SortCriteria(sortColumn, sortAscending));
    
    for (Organization ref : organizations)
    {
      ret.add(new SelectItem(ref, ref.getName()));
    }
    return ret;
  }
  
  /**
   * Set the selectedOrganization value when the combo value changes
   * @param event
   */
  public void onSelectedOrganizationChanged(ValueChangeEvent event)
  {
    setSelectedOrganization((Organization) event.getNewValue());
    
    FacesUtils.renderResponse();
  }
  
  /**
   * Get the list of all projects
   * @return the list of all projects
   */
  public List<SelectItem> getProjectsOpenBySelectedOrganization()
  {
    
    List<Project> refs = ProjectManager.getDefault().getProjectsByOrganization(selectedOrganization); 
    ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
    for (Object ref : refs)
    {
      Project proy = (Project) ref;
      if (!proy.isFinished())
      {
        ret.add(new SelectItem(proy, proy.getName()));
      }
    }
    
    return ret;
  }
  
  /**
   * Get the selectedOrganization value
   * @return a Organization selected
   */
  public Organization getSelectedOrganization()
  {
    
    if (objective != null && objective.getProject() != null)
    {
      selectedOrganization = objective.getProject().getClient();
      return selectedOrganization;
    }
    else
    {
      selectedOrganization = organizations.get(0);
    }
    
    return selectedOrganization;
  }
  
  /**
   * @param selectedOrganization the selectedOrganization to set
   */
  public void setSelectedOrganization(Organization selectedOrganization)
  {
    this.selectedOrganization = selectedOrganization;
  }

public int getUserSelected() {
	return userSelected;
}

public void setUserSelected(int userSelected) {
	this.userSelected = userSelected;
	
	
	
}
  
}
