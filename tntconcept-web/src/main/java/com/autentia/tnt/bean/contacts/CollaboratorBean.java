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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.acegisecurity.acls.domain.BasePermission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.bean.NavigationResults;
import com.autentia.tnt.businessobject.Collaborator;
import com.autentia.tnt.businessobject.Contact;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.CollaboratorSearch;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.contacts.CollaboratorManager;
import com.autentia.tnt.manager.contacts.ContactManager;
import com.autentia.tnt.manager.contacts.OrganizationManager;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.upload.Uploader;
import com.autentia.tnt.upload.UploaderFactory;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.SpringUtils;

public class CollaboratorBean extends BaseBean {


  /** Logger */
  private static final Log log = LogFactory.getLog(CollaboratorBean.class);

 /** Active search object */
  private CollaboratorSearch search = new CollaboratorSearch();

  /** Manager */
  private static CollaboratorManager manager = CollaboratorManager.getDefault();
  
  /** Upload service */
  private static final Uploader uploader = UploaderFactory.getInstance("collaborator");
    
                                  
  /** Active Collaborator object */
  private Collaborator collaborator;
  
  /** Default sort column */
  private String sortColumn = "id";

  /** Default sort order */
  private boolean sortAscending = true;

  
  /**
   * List collaborators. Order depends on Faces parameter sort.
   * @return the list of all collaborators sorted by requested criterion
   */
  public List<Collaborator> getAll(){
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
    ret.add(new SelectItem(null, ""));
    for( User ref : refs ){
      ret.add( new SelectItem(ref, ref.getName() ) );
    }
    return ret;	
  }
      
    
  /**
   * Get the list of all contacts
   * @return the list of all contacts
   */
  public List<SelectItem> getContacts(){  
    List<Contact> refs = ContactManager.getDefault().getAllEntities( null, new SortCriteria("name") );
	  
    ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
    ret.add(new SelectItem(null, ""));
    for( Contact ref : refs ){
    	ret.add( new SelectItem(ref, ref.getName() ) );
    }
    return ret;	
  }
      
    
  /**
   * Get the list of all organizations
   * @return the list of all organizations
   */
  public List<SelectItem> getOrganizations(){  
    List<Organization> refs = OrganizationManager.getDefault().getAllEntities( null, new SortCriteria("name") );
	  
    ArrayList<SelectItem> ret = new ArrayList<SelectItem>(); 
    ret.add(new SelectItem(null, ""));
    for( Organization ref : refs ){
      ret.add( new SelectItem(ref, ref.getName() ) );
    }
    return ret;	
  }
        
  // Getters to list possible values of enum fields
                          

  // Methods to create/remove instances of one-to-many entities (slave entities)
  
  /**
   * Whether or not create button is available for user
   * @return true if user can create objects of type Collaborator
   */
  public boolean isCreateAvailable()
  {
    return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Collaborator.class));
  }

  /**
   * Whether or not edit button is available for user
   * @return true if user can edit current object
   */
  public boolean isEditAvailable()
  {
    return SpringUtils.isAclPermissionGranted(collaborator,BasePermission.WRITE);
  }

  /**
   * Whether or not delete button is available for user
   * @return true if user can delete current object
   */
  public boolean isDeleteAvailable()
  {
    return (collaborator.getId()!=null) &&
	   SpringUtils.isAclPermissionGranted(collaborator,BasePermission.DELETE);
  }

  /**
   * Go to create page
   * @return forward to CREATE page
   */
  public String create(){
    collaborator = new Collaborator();
    return NavigationResults.CREATE;
  }

  /**
   * Go to detail page
   * @return forward to DETAIL page
   */
  public String detail(){
    Integer id = Integer.parseInt( FacesUtils.getRequestParameter(ROW_ID) );
    collaborator = manager.getEntityById(id);

    return SpringUtils.isAclPermissionGranted( collaborator, BasePermission.WRITE )
	    ? NavigationResults.EDIT
	    : NavigationResults.DETAIL;
  }

  /**
   * Save bean and stay on it
   * @return forward to list page
   */
  public String save(){
  
      if (doBeforeSave() != null) {
    	  return "";
      }
  
      if( collaborator.getId()==null ){
        manager.insertEntity(collaborator);
      } else {
        manager.updateEntity(collaborator);
      }
      

                                                                      
      // Calls an after save action
      String result = doAfterSave(NavigationResults.LIST);

      // Unselect object
      collaborator = null;

      return result;
  }
    
	public String doBeforeSave() {
		
		// comprobacion de que solo se ha seleccionado o un usuario, o un contacto o una organizacion
		int numRefs = 0;
		if (collaborator.getUser() != null){
			numRefs++;
	        CollaboratorSearch collaboratorSearch = new CollaboratorSearch();
	        collaboratorSearch.setUser(this.getUser());
	        if (!CollaboratorManager.getDefault().getAllEntities( collaboratorSearch, new SortCriteria("id")).isEmpty()) {
			    FacesUtils.addErrorMessage(null, "collaborator.error.duplicateUserCollaborator");
			    return "Fail";	
	        }			
		}
		if (collaborator.getContact() != null){
			numRefs++;
	        CollaboratorSearch collaboratorSearch = new CollaboratorSearch();
	        collaboratorSearch.setContact(this.getContact());
	        if (!CollaboratorManager.getDefault().getAllEntities( collaboratorSearch, new SortCriteria("id")).isEmpty()) {
			    FacesUtils.addErrorMessage(null, "collaborator.error.duplicateContactCollaborator");
			    return "Fail";	
	        }				
		}
		if (collaborator.getOrganization() != null) {
			numRefs++;
	        CollaboratorSearch collaboratorSearch = new CollaboratorSearch();
	        collaboratorSearch.setOrganization(this.getOrganization());
	        if (!CollaboratorManager.getDefault().getAllEntities( collaboratorSearch, new SortCriteria("id")).isEmpty()) {
			    FacesUtils.addErrorMessage(null, "collaborator.error.duplicateOrganizationCollaborator");
			    return "Fail";	
	        }				
		}
        if (numRefs != 1){
		    FacesUtils.addErrorMessage(null, "collaborator.error.justOne");
		    return "Fail";
		}		
        

        
        
        
		return null;
	}
  
  /**
   * Delete bean and go back to beans list
   * @return forward to LIST page
   */
  public String delete(){
	  try {
		  manager.deleteEntity(collaborator);
	  } catch (Exception e) {
			FacesUtils.addErrorMessage(null,"collaborator.error.cannotDelete", collaborator.getName());
	  }
	  collaborator = null;
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
   * Reset search criteria
   * @return forward to LIST page
   */
  public String reset(){
  	search.reset();
	return list();    
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
  public boolean isCollaboratorSelected(){
    return collaborator!=null;
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
  public CollaboratorSearch getSearch(){
    return search;
  }
      
    
    
    
    
    public Integer getSearchOwnerId(){
        return search.getOwnerId();
    }
    public void setSearchOwnerId( Integer val ){
        if( search.isOwnerIdSet() ) {
          search.setOwnerId( val );
        }
    }
    public boolean isSearchOwnerIdValid(){
        return search.isOwnerIdSet();
    }
    public void setSearchOwnerIdValid( boolean val ){
        if( val ){
          search.setOwnerId( search.getOwnerId() );
        } else {
          search.unsetOwnerId();
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
        
    
    
        
    
    public Contact getSearchContact(){
        return search.getContact();
    }
    public void setSearchContact( Contact val ){
        if( search.isContactSet() ) {
          search.setContact( val );
        }
    }
    public boolean isSearchContactValid(){
        return search.isContactSet();
    }
    public void setSearchContactValid( boolean val ){
        if( val ){
          search.setContact( search.getContact() );
        } else {
          search.unsetContact();
        }
    }
        
    
    
        
    
    public Organization getSearchOrganization(){
        return search.getOrganization();
    }
    public void setSearchOrganization( Organization val ){
        if( search.isOrganizationSet() ) {
          search.setOrganization( val );
        }
    }
    public boolean isSearchOrganizationValid(){
        return search.isOrganizationSet();
    }
    public void setSearchOrganizationValid( boolean val ){
        if( val ){
          search.setOrganization( search.getOrganization() );
        } else {
          search.unsetOrganization();
        }
    }
        
  
  // Getters and setters to handle uploads
                                  
  // Getters and setters to manipulate active Collaborator object
  public java.lang.Integer getId() {
      return collaborator.getId();
  }
      
    
    
    public Integer getOwnerId() {
    return collaborator.getOwnerId();
  }
  public void setOwnerId( Integer ownerId ) {
    collaborator.setOwnerId( ownerId );
  }
        
    
    
    public Integer getDepartmentId() {
    return collaborator.getDepartmentId();
  }
  public void setDepartmentId( Integer departmentId ) {
    collaborator.setDepartmentId( departmentId );
  }
        
    
    
    public Date getInsertDate() {
    return collaborator.getInsertDate();
  }
  public void setInsertDate( Date insertDate ) {
    collaborator.setInsertDate( insertDate );
  }
        
    
    
    public Date getUpdateDate() {
    return collaborator.getUpdateDate();
  }
  public void setUpdateDate( Date updateDate ) {
    collaborator.setUpdateDate( updateDate );
  }
          
    
    
  
    public User getUser() {
    return collaborator.getUser();
  }
  public void setUser( User user ) {
    collaborator.setUser( user );
  }
        
    
    
  
    public Contact getContact() {
    return collaborator.getContact();
  }
  public void setContact( Contact contact ) {
    collaborator.setContact( contact );
  }
        
    
    
  
    public Organization getOrganization() {
    return collaborator.getOrganization();
  }
  public void setOrganization( Organization organization ) {
    collaborator.setOrganization( organization );
  }
        
  public String getName(){
	  return collaborator.getName();
  }
  
  public void setName(String name){
	  collaborator.setName(name);   
  }

/* collaborator - generated by stajanov (do not edit/delete) */
}