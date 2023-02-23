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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.bean.NavigationResults;
import com.autentia.tnt.businessobject.AccountEntryGroup;
import com.autentia.tnt.businessobject.AccountEntryType;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.AccountEntryTypeSearch;
import com.autentia.tnt.manager.account.AccountEntryGroupManager;
import com.autentia.tnt.manager.account.AccountEntryTypeManager;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.upload.Uploader;
import com.autentia.tnt.upload.UploaderFactory;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.SpringUtils;

import org.acegisecurity.acls.domain.BasePermission;

public class AccountEntryTypeBean extends BaseBean {

/* accountEntryType - generated by stajanov (do not edit/delete) */


















  /** Logger */
  private static final Log log = LogFactory.getLog(AccountEntryTypeBean.class);

 /** Active search object */
  private AccountEntryTypeSearch search = new AccountEntryTypeSearch();

  /** Manager */
  private static AccountEntryTypeManager manager = AccountEntryTypeManager.getDefault();
  
  /** Upload service */
  private static final Uploader uploader = UploaderFactory.getInstance("accountEntryType");
    
                                                          
  /** Active AccountEntryType object */
  private AccountEntryType accountEntryType;
  
  /** Default sort column */
  private String sortColumn = "name";
    
  /** Default sort order */
  private boolean sortAscending = false;

      /** Quick search letter for ABC pager control */
    private Character letter;
  
  /**
   * List accountEntryTypes. Order depends on Faces parameter sort.
   * @return the list of all accountEntryTypes sorted by requested criterion
   */
  public List<AccountEntryType> getAll(){
    return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending) );      
  }

  // Getters to list possible values of related entities
      
    
  /**
   * Get the list of all groups
   * @return the list of all groups
   */
  public List<SelectItem> getGroups(){  
    List<AccountEntryGroup> refs = AccountEntryGroupManager.getDefault().getAllEntities( null, new SortCriteria("name") );
	  
    ArrayList<SelectItem> ret = new ArrayList<SelectItem>();  
    for( AccountEntryGroup ref : refs ){
      ret.add( new SelectItem( ref, ref.getName() ) );
    }
    return ret;	
  }
      
    
  
        
  // Getters to list possible values of enum fields
                                            

  // Methods to create/remove instances of one-to-many entities (slave entities)
  
  /**
   * Whether or not create button is available for user
   * @return true if user can create objects of type AccountEntryType
   */
  public boolean isCreateAvailable()
  {
    return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(AccountEntryType.class));
  }

  /**
   * Whether or not edit button is available for user
   * @return true if user can edit current object
   */
  public boolean isEditAvailable()
  {
    return SpringUtils.isAclPermissionGranted(accountEntryType,BasePermission.WRITE);
  }

  /**
   * Whether or not delete button is available for user
   * @return true if user can delete current object
   */
  public boolean isDeleteAvailable()
  {
    return (accountEntryType.getId()!=null) &&
	   SpringUtils.isAclPermissionGranted(accountEntryType,BasePermission.DELETE);
  }

  /**
   * Go to create page
   * @return forward to CREATE page
   */
  public String create(){
    accountEntryType = new AccountEntryType();
       
    return NavigationResults.CREATE;
  }

  /**
   * Go to detail page
   * @return forward to DETAIL page
   */
  public String detail(){
    Integer id = Integer.parseInt( FacesUtils.getRequestParameter(ROW_ID) );
    accountEntryType = manager.getEntityById(id);

    return SpringUtils.isAclPermissionGranted( accountEntryType, BasePermission.WRITE )
	    ? NavigationResults.EDIT
	    : NavigationResults.DETAIL;
  }

  /**
   * Save bean and stay on it
   * @return forward to list page
   */
  public String save(){
  
      doBeforeSave();
  
      if( accountEntryType.getId()==null ){
        manager.insertEntity(accountEntryType);
      } else {
        manager.updateEntity(accountEntryType);
      }
      

                                                                                                                      
      // Calls an after save action
      String result = doAfterSave(NavigationResults.LIST);

      // Unselect object
      accountEntryType = null;

      return result;
  }
    
  /**
   * Delete bean and go back to beans list
   * @return forward to LIST page
   */
  public String delete(){
    manager.deleteEntity(accountEntryType);
    accountEntryType = null;
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
  public boolean isAccountEntryTypeSelected(){
    return accountEntryType!=null;
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
  public AccountEntryTypeSearch getSearch(){
    return search;
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
        
    
    
    
    
    public String getSearchObservations(){
        return search.getObservations();
    }
    public void setSearchObservations( String val ){
        if( search.isObservationsSet() ) {
          search.setObservations( val );
        }
    }
    public boolean isSearchObservationsValid(){
        return search.isObservationsSet();
    }
    public void setSearchObservationsValid( boolean val ){
        if( val ){
          search.setObservations( search.getObservations() );
        } else {
          search.unsetObservations();
        }
    }
        
    
    
    
    
    public Integer getSearchCustomizableId(){
        return search.getCustomizableId();
    }
    public void setSearchCustomizableId( Integer val ){
        if( search.isCustomizableIdSet() ) {
          search.setCustomizableId( val );
        }
    }
    public boolean isSearchCustomizableIdValid(){
        return search.isCustomizableIdSet();
    }
    public void setSearchCustomizableIdValid( boolean val ){
        if( val ){
          search.setCustomizableId( search.getCustomizableId() );
        } else {
          search.unsetCustomizableId();
        }
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

          
    
    
        
    
    public AccountEntryGroup getSearchGroup(){
        return search.getGroup();
    }
    public void setSearchGroup( AccountEntryGroup val ){
        if( search.isGroupSet() ) {
          search.setGroup( val );
        }
    }
    public boolean isSearchGroupValid(){
        return search.isGroupSet();
    }
    public void setSearchGroupValid( boolean val ){
        if( val ){
          search.setGroup( search.getGroup() );
        } else {
          search.unsetGroup();
        }
    }
        
    
    
        
    
    public AccountEntryType getSearchParent(){
        return search.getParent();
    }
    public void setSearchParent( AccountEntryType val ){
        if( search.isParentSet() ) {
          search.setParent( val );
        }
    }
    public boolean isSearchParentValid(){
        return search.isParentSet();
    }
    public void setSearchParentValid( boolean val ){
        if( val ){
          search.setParent( search.getParent() );
        } else {
          search.unsetParent();
        }
    }
        
      /** Handle an ABC pager letter click: filter objects by specified starting letter */
    public void letterClicked()
    {
      if( letter!=null ){
      	UIComponent comp = FacesUtils.getComponent("accountEntryTypes:list");
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
  
  // Getters and setters to handle uploads
                                                          
  // Getters and setters to manipulate active AccountEntryType object
  public java.lang.Integer getId() {
      return accountEntryType.getId();
  }
      
    
    
    public String getName() {
    return accountEntryType.getName();
  }
  public void setName( String name ) {
    accountEntryType.setName( name );
  }
        
    
    
    public String getObservations() {
    return accountEntryType.getObservations();
  }
  public void setObservations( String observations ) {
    accountEntryType.setObservations( observations );
  }
        
    
    
    public Integer getCustomizableId() {
    return accountEntryType.getCustomizableId();
  }
  public void setCustomizableId( Integer customizableId ) {
    accountEntryType.setCustomizableId( customizableId );
  }
        
    
    
    public Integer getOwnerId() {
    return accountEntryType.getOwnerId();
  }
  public void setOwnerId( Integer ownerId ) {
    accountEntryType.setOwnerId( ownerId );
  }
        
    
    
    public Integer getDepartmentId() {
    return accountEntryType.getDepartmentId();
  }
  public void setDepartmentId( Integer departmentId ) {
    accountEntryType.setDepartmentId( departmentId );
  }
        
    
    
    public Date getInsertDate() {
    return accountEntryType.getInsertDate();
  }

    public Date getUpdateDate() {
    return accountEntryType.getUpdateDate();
  }
  
    public AccountEntryGroup getGroup() {
    return accountEntryType.getGroup();
  }
  public void setGroup( AccountEntryGroup group ) {
    accountEntryType.setGroup( group );
  }
        
    
    
  
    public AccountEntryType getParent() {
    return accountEntryType.getParent();
  }
  public void setParent( AccountEntryType parent ) {
    accountEntryType.setParent( parent );
  }
        
/* accountEntryType - generated by stajanov (do not edit/delete) */
  
  
  /**
   * Get the list of all parents
   * @return the list of all parents
   */
  public List<SelectItem> getParents(){  
    /*List<AccountEntryType> refs = AccountEntryTypeManager.getDefault().getAllEntities( null, new SortCriteria("name") );
	  
    ArrayList<SelectItem> ret = new ArrayList<SelectItem>();  
    for( AccountEntryType ref : refs ){
      ret.add( new SelectItem( ref, ref.getName() ) );
    }
    return ret;
    */
	return getParentsWithNull();
  }
  
  
  public List<SelectItem> getParentsWithNull(){
	  
      ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
     
      if(accountEntryType!=null) {
    	  if (this.getId()!= null){
    		  search.setDifferentId(this.getId());
    	  }
      }
      search.setParent(null);      
      
      List<AccountEntryType> refs = manager.getAllEntities(search,new SortCriteria("name"));
      for( AccountEntryType ref : refs ){
          ret.add( new SelectItem( ref, ref.getName() ) );
      }
	  ret.add(0,new SelectItem(FacesUtils.getMessage("select.noneValue")));
	  search.unsetDifferentId();
	  search.unsetParent();
	  return ret;
	  
  }
  
  
}
