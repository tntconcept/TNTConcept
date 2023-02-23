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

package com.autentia.tnt.bean.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;

import org.acegisecurity.acls.domain.BasePermission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.bean.NavigationResults;
import com.autentia.tnt.businessobject.Department;
import com.autentia.tnt.businessobject.Position;
import com.autentia.tnt.businessobject.Tag;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.DepartmentSearch;
import com.autentia.tnt.dao.search.PositionSearch;
import com.autentia.tnt.manager.admin.DepartmentManager;
import com.autentia.tnt.manager.contacts.PositionManager;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.manager.tags.TagManager;
import com.autentia.tnt.upload.Uploader;
import com.autentia.tnt.upload.UploaderFactory;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.SpringUtils;


public class DepartmentBean extends BaseBean 
{

/* Department - generated by stajanov (do not edit/delete) */


















  /** Logger */
  private static final Log log = LogFactory.getLog(DepartmentBean.class);

 /** Active search object */
  private DepartmentSearch search = new DepartmentSearch();

  /** Manager */
  private static DepartmentManager manager = DepartmentManager.getDefault();
  
  /** Upload service */
  private static final Uploader uploader = UploaderFactory.getInstance("department");
    
                                                  
  /** Active Department object */
  private Department department;
  
  
  /** Default sort column */
  private String sortColumn = "name";
    
  /** Default sort order */
  private boolean sortAscending = true;

      /** Quick search letter for ABC pager control */
    private Character letter;
  
  /**
   * List departments. Order depends on Faces parameter sort.
   * @return the list of all departments sorted by requested criterion
   */
  public List<Department> getAll(){
    return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending) );      
  }

  // Getters to list possible values of related entities
      
    
  /**
   * Get the list of all parents
   * @return the list of all parents
   */
  public List<SelectItem> getParents(){  
    List<Department> refs = DepartmentManager.getDefault().getAllEntities( null, new SortCriteria("name") );
	  
    ArrayList<SelectItem> ret = new ArrayList<SelectItem>();  
    for( Department ref : refs ){
      ret.add( new SelectItem( ref, ref.getName() ) );
    }
    return ret;	
  }
        
  // Getters to list possible values of enum fields
                                      

  // Methods to create/remove instances of one-to-many entities (slave entities)
  
  /**
   * Whether or not create button is available for user
   * @return true if user can create objects of type Department
   */
  public boolean isCreateAvailable()
  {
    return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Department.class));
  }

  /**
   * Whether or not edit button is available for user
   * @return true if user can edit current object
   */
  public boolean isEditAvailable()
  {
    return SpringUtils.isAclPermissionGranted(department,BasePermission.WRITE);
  }

  /**
   * Whether or not delete button is available for user
   * @return true if user can delete current object
   */
  public boolean isDeleteAvailable()
  {
    return (department.getId()!=null) &&
	   SpringUtils.isAclPermissionGranted(department,BasePermission.DELETE);
  }

  /**
   * Go to create page
   * @return forward to CREATE page
   */
  public String create(){
    department = new Department();

    // se inserta el puesto 'Indefinido' en caso de existir
    final PositionSearch positionSearch = new PositionSearch();
    positionSearch.setName("Indefinido");
    List<Position> positions = PositionManager.getDefault().getAllEntities(positionSearch, new SortCriteria("name", true));
    Set<Position> positionsSet = new HashSet<Position>();
    for (Position position: positions){
    	positionsSet.add(position);
    }
    department.setPositions(positionsSet);
    
    return NavigationResults.CREATE;
  }

  /**
   * Go to detail page
   * @return forward to DETAIL page
   */
  public String detail(){
    Integer id = Integer.parseInt( FacesUtils.getRequestParameter(ROW_ID) );
    department = manager.getEntityById(id);

    return SpringUtils.isAclPermissionGranted( department, BasePermission.WRITE )
	    ? NavigationResults.EDIT
	    : NavigationResults.DETAIL;
  }

  /**
   * Save bean and stay on it
   * @return forward to list page
   */
  public String save(){
  
      doBeforeSave();
  
      if( department.getId()==null ){
        manager.insertEntity(department);
      } else {
        manager.updateEntity(department);
      }
      

                                                                                                      
      // Calls an after save action
      String result = doAfterSave(NavigationResults.LIST);

      // Unselect object
      department = null;

      return result;
  }
  
  /**
   * Delete bean and go back to beans list
   * @return forward to LIST page
   */
  public String delete(){
	try{
		manager.deleteEntity(department);
		department = null;
		return NavigationResults.LIST;
	}catch(DataAccException daex){
		FacesUtils.addErrorMessage("department", "department.deleteRestrict", null);
		return NavigationResults.EDIT;
	}
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
  public boolean isDepartmentSelected(){
    return department!=null;
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
  public DepartmentSearch getSearch(){
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
        
    
    
    
    
    public String getSearchDescription(){
        return search.getDescription();
    }
    public void setSearchDescription( String val ){
        if( search.isDescriptionSet() ) {
          search.setDescription( val );
        }
    }
    public boolean isSearchDescriptionValid(){
        return search.isDescriptionSet();
    }
    public void setSearchDescriptionValid( boolean val ){
        if( val ){
          search.setDescription( search.getDescription() );
        } else {
          search.unsetDescription();
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

          
    
    
        
    
    public Department getSearchParent(){
        return search.getParent();
    }
    public void setSearchParent( Department val ){
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
      	UIComponent comp = FacesUtils.getComponent("departments:list");
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
                                                  
  // Getters and setters to manipulate active Department object
  public java.lang.Integer getId() {
      return department.getId();
  }
      
    
    
    public String getName() {
    return department.getName();
  }
  public void setName( String name ) {
    department.setName( name );
  }
        
    
    
    public String getDescription() {
    return department.getDescription();
  }
  public void setDescription( String description ) {
    department.setDescription( description );
  }
        
    
    
    public Integer getOwnerId() {
    return department.getOwnerId();
  }
  public void setOwnerId( Integer ownerId ) {
    department.setOwnerId( ownerId );
  }
        
    
    
    public Integer getDepartmentId() {
    return department.getDepartmentId();
  }
  public void setDepartmentId( Integer departmentId ) {
    department.setDepartmentId( departmentId );
  }
        
    
    
    public Date getInsertDate() {
    return department.getInsertDate();
  }

    public Date getUpdateDate() {
    return department.getUpdateDate();
  }
    public Department getParent() {
    return department.getParent();
  }
  public void setParent( Department parent ) {
    department.setParent( parent );
  }
        
/* Department - generated by stajanov (do not edit/delete) */

  /**
   * Get the list of all parents
   * @return the list of all parents
   */
  public List<SelectItem> getParentsWithoutMe(){
	  List<Department> refs = null;
  
      if(department != null && department.getId()!=null) {
    	  search.setIdDifferentFrom(department.getId());
    	  refs = manager.getAllEntities(search,new SortCriteria("name"));		  
    	  search.unsetIdDifferentFrom();
      } else {
    	  refs = manager.getAllEntities(null,new SortCriteria("name"));
      }
	  
	  ArrayList<SelectItem> ret = new ArrayList<SelectItem>();  
	  	  	
	  ret.add(new SelectItem("","-- Ninguno --"));
	  
      for( Department ref : refs ){
          ret.add( new SelectItem( ref, ref.getName() ) );
      }
      return ret;
	  
  }
 
  

  
  
  
  
  
	private Position selectedPosition;
	
	public Position getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(Position selectedPosition) {
		this.selectedPosition = selectedPosition;
	}
	
	public List<SelectItem> getAllPositions() {
		final PositionSearch notDeletedSearch = new PositionSearch();
		notDeletedSearch.setDeleted(false);
		final List<Position> refs = PositionManager.getDefault().getAllEntities(notDeletedSearch, new SortCriteria("name"));
		final List<SelectItem> ret = new ArrayList<SelectItem>();
		for (Position ref : refs) {
				ret.add(new SelectItem(ref, ref.getName()));
		}
		return ret;
	}
	
	public String addPosition() {
		department.getPositions().add(getSelectedPosition());
		return NavigationResults.EDIT;
	}
	
	
	public Set<Position> getDepartmentPositions () {
		return department.getPositions();
	}
	
	public void setDepartmentPositions(Set<Position> positions) {
		department.setPositions(positions);
	}
	
	public String deletePosition() {
		final UIData table = (UIData) FacesUtils.getComponent("department:positions");
		final Position toDelete = (Position) table.getRowData();
		department.getPositions().remove(toDelete);
		return NavigationResults.EDIT;
	}
	
	
	
	private Tag selectedTag;
	
	public Tag getSelectedTag() {
		return selectedTag;
	}

	public void setSelectedTag(Tag selectedTag) {
		this.selectedTag = selectedTag;
	}
	
	public List<SelectItem> getAllTags() {
		final List<Tag> refs = TagManager.getDefault().getAllEntities(null, new SortCriteria("id"));
		final List<SelectItem> ret = new ArrayList<SelectItem>();
		for (Tag ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}
		return ret;
	}
	
	public String addTag() {
		department.getTags().add(getSelectedTag());
		return NavigationResults.EDIT;
	}
	
	
	public Set<Tag> getDepartmentTags () {
		return department.getTags();
	}
	
	public void setTags(Set<Tag> tags) {
		department.setTags(tags);
	}
	
	public String deleteTag() {
		final UIData table = (UIData) FacesUtils.getComponent("department:tags");
		final Tag toDelete = (Tag) table.getRowData();
		department.getTags().remove(toDelete);
		return NavigationResults.EDIT;
	}
	
	public boolean isAreTagsToSelect() {
		return !getAllTags().isEmpty();
	}

	public boolean isAreTagsSelected() {
		return !department.getTags().isEmpty();
	}
	
	public boolean isArePositionsToSelect() {
		return !getAllPositions().isEmpty();
	}

	public boolean isArePositionsSelected() {
		return !department.getPositions().isEmpty();
	}
	
}
