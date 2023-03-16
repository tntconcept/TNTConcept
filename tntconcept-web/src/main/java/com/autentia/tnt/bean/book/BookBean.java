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

package com.autentia.tnt.bean.book;

import java.math.BigDecimal;
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
import com.autentia.tnt.businessobject.Book;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BookSearch;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.book.BookManager;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.upload.Uploader;
import com.autentia.tnt.upload.UploaderFactory;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.SpringUtils;

import org.acegisecurity.acls.domain.BasePermission;

public class BookBean extends BaseBean {

/* book - generated by stajanov (do not edit/delete) */


















  /** Logger */
  private static final Log log = LogFactory.getLog(BookBean.class);

 /** Active search object */
  private BookSearch search = new BookSearch();

  /** Manager */
  private static BookManager manager = BookManager.getDefault();
  
  /** Upload service */
  private static final Uploader uploader = UploaderFactory.getInstance("book");
    
                                                                                  
  /** Active Book object */
  private Book book;
  
  /** Default sort column */
  private String sortColumn = "name";
    
  /** Default sort order */
  private boolean sortAscending = false;

      /** Quick search letter for ABC pager control */
    private Character letter;
  
  /**
   * List books. Order depends on Faces parameter sort.
   * @return the list of all books sorted by requested criterion
   */
  public List<Book> getAll(){
    return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending) );      
  }

  // Getters to list possible values of related entities
      
    
  /**
   * Get the list of all lentTos
   * @return the list of all lentTos
   */
  public List<SelectItem> getLentTos(){  
    List<User> refs = UserManager.getDefault().getAllEntities( null, new SortCriteria("name") );
	  
    ArrayList<SelectItem> ret = new ArrayList<SelectItem>();  
    for( User ref : refs ){
      ret.add( new SelectItem( ref, ref.getName() ) );
    }
    return ret;	
  }
        
  // Getters to list possible values of enum fields
                                                              

  // Methods to create/remove instances of one-to-many entities (slave entities)
  
  /**
   * Whether or not create button is available for user
   * @return true if user can create objects of type Book
   */
  public boolean isCreateAvailable()
  {
    return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Book.class));
  }

  /**
   * Whether or not edit button is available for user
   * @return true if user can edit current object
   */
  public boolean isEditAvailable()
  {
    return SpringUtils.isAclPermissionGranted(book,BasePermission.WRITE);
  }

  /**
   * Whether or not delete button is available for user
   * @return true if user can delete current object
   */
  public boolean isDeleteAvailable()
  {
    return (book.getId()!=null) &&
	   SpringUtils.isAclPermissionGranted(book,BasePermission.DELETE);
  }

  /**
   * Go to create page
   * @return forward to CREATE page
   */
  public String create(){
    book = new Book();
    // Fecha de compra
 book.setPurchaseDate( new Date() );
   
    return NavigationResults.CREATE;
  }

  /**
   * Go to detail page
   * @return forward to DETAIL page
   */
  public String detail(){
    Integer id = Integer.parseInt( FacesUtils.getRequestParameter(ROW_ID) );
    book = manager.getEntityById(id);

    return SpringUtils.isAclPermissionGranted( book, BasePermission.WRITE )
	    ? NavigationResults.EDIT
	    : NavigationResults.DETAIL;
  }

  /**
   * Save bean and stay on it
   * @return forward to list page
   */
  public String save(){
  
      doBeforeSave();
  
      if( book.getId()==null ){
        manager.insertEntity(book);
      } else {
        manager.updateEntity(book);
      }
      

                                                                                                                                                                      
      // Calls an after save action
      String result = doAfterSave(NavigationResults.LIST);

      // Unselect object
      book = null;

      return result;
  }
    
  /**
   * Delete bean and go back to beans list
   * @return forward to LIST page
   */
  public String delete(){
    manager.deleteEntity(book);
    book = null;
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
  public boolean isBookSelected(){
    return book!=null;
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
  public BookSearch getSearch(){
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
        
    
    
    
    
    public String getSearchAuthor(){
        return search.getAuthor();
    }
    public void setSearchAuthor( String val ){
        if( search.isAuthorSet() ) {
          search.setAuthor( val );
        }
    }
    public boolean isSearchAuthorValid(){
        return search.isAuthorSet();
    }
    public void setSearchAuthorValid( boolean val ){
        if( val ){
          search.setAuthor( search.getAuthor() );
        } else {
          search.unsetAuthor();
        }
    }
        
    
    
    
    
    public String getSearchISBN(){
        return search.getISBN();
    }
    public void setSearchISBN( String val ){
        if( search.isISBNSet() ) {
          search.setISBN( val );
        }
    }
    public boolean isSearchISBNValid(){
        return search.isISBNSet();
    }
    public void setSearchISBNValid( boolean val ){
        if( val ){
          search.setISBN( search.getISBN() );
        } else {
          search.unsetISBN();
        }
    }
        
    
    
    
    
    public String getSearchURL(){
        return search.getURL();
    }
    public void setSearchURL( String val ){
        if( search.isURLSet() ) {
          search.setURL( val );
        }
    }
    public boolean isSearchURLValid(){
        return search.isURLSet();
    }
    public void setSearchURLValid( boolean val ){
        if( val ){
          search.setURL( search.getURL() );
        } else {
          search.unsetURL();
        }
    }
        
    
    
    
    
    public BigDecimal getSearchPrice(){
        return search.getPrice();
    }
    public void setSearchPrice( BigDecimal val ){
        if( search.isPriceSet() ) {
          search.setPrice( val );
        }
    }
    public boolean isSearchPriceValid(){
        return search.isPriceSet();
    }
    public void setSearchPriceValid( boolean val ){
        if( val ){
          search.setPrice( search.getPrice() );
        } else {
          search.unsetPrice();
        }
    }
        
    
        
      
    public Date getSearchStartPurchaseDate(){
        return search.getStartPurchaseDate();
    }
    public void setSearchStartPurchaseDate( Date val ){
        if( val!=null ){
          search.setStartPurchaseDate( val );
        } else {
          search.unsetStartPurchaseDate();
        }
    }
    public boolean isSearchStartPurchaseDateValid(){
        return search.isStartPurchaseDateSet();
    }
    public void setSearchStartPurchaseDateValid( boolean val ){
        if( val ){
          search.setStartPurchaseDate( search.getStartPurchaseDate() );
        } else {
          search.unsetStartPurchaseDate();
        }
    }
    public Date getSearchEndPurchaseDate(){
        return search.getEndPurchaseDate();
    }
    public void setSearchEndPurchaseDate( Date val ){
        if( val!=null ){
          search.setEndPurchaseDate( val );
        } else {
          search.unsetEndPurchaseDate();
        }
    }
    public boolean isSearchEndPurchaseDateValid(){
        return search.isEndPurchaseDateSet();
    }
    public void setSearchEndPurchaseDateValid( boolean val ){
        if( val ){
          search.setEndPurchaseDate( search.getEndPurchaseDate() );
        } else {
          search.unsetEndPurchaseDate();
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

          
    
    
        
    
    public User getSearchLentTo(){
        return search.getLentTo();
    }
    public void setSearchLentTo( User val ){
        if( search.isLentToSet() ) {
          search.setLentTo( val );
        }
    }
    public boolean isSearchLentToValid(){
        return search.isLentToSet();
    }
    public void setSearchLentToValid( boolean val ){
        if( val ){
          search.setLentTo( search.getLentTo() );
        } else {
          search.unsetLentTo();
        }
    }
        
      /** Handle an ABC pager letter click: filter objects by specified starting letter */
    public void letterClicked()
    {
      if( letter!=null ){
      	UIComponent comp = FacesUtils.getComponent("books:list");
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
                                                                                  
  // Getters and setters to manipulate active Book object
  public java.lang.Integer getId() {
      return book.getId();
  }
      
    
    
    public String getName() {
    return book.getName();
  }
  public void setName( String name ) {
    book.setName( name );
  }
        
    
    
    public String getAuthor() {
    return book.getAuthor();
  }
  public void setAuthor( String author ) {
    book.setAuthor( author );
  }
        
    
    
    public String getISBN() {
    return book.getISBN();
  }
  public void setISBN( String ISBN ) {
    book.setISBN( ISBN );
  }
        
    
    
    public String getURL() {
    return book.getURL();
  }
  public void setURL( String URL ) {
    book.setURL( URL );
  }
        
    
    
    public BigDecimal getPrice() {
    return book.getPrice();
  }
  public void setPrice( BigDecimal price ) {
      if (price == null) {
          book.setPrice(BigDecimal.ZERO);
      } else {
          book.setPrice(price);
      }
  }
        
    
    
    public Date getPurchaseDate() {
    return book.getPurchaseDate();
  }
  public void setPurchaseDate( Date purchaseDate ) {
    book.setPurchaseDate( purchaseDate );
  }
        
    
    
    public Integer getOwnerId() {
    return book.getOwnerId();
  }
  public void setOwnerId( Integer ownerId ) {
    book.setOwnerId( ownerId );
  }
        
    
    
    public Integer getDepartmentId() {
    return book.getDepartmentId();
  }
  public void setDepartmentId( Integer departmentId ) {
    book.setDepartmentId( departmentId );
  }
        
    
    
    public Date getInsertDate() {
    return book.getInsertDate();
  }
    
    public Date getUpdateDate() {
    return book.getUpdateDate();
  }
    public User getLentTo() {
    return book.getLentTo();
  }
  public void setLentTo( User lentTo ) {
    book.setLentTo( lentTo );
  }
        
/* book - generated by stajanov (do not edit/delete) */
  
  /** Quick search letter for ABC pager control */
	
  
  
  public List<SelectItem> getLentTosWithNull(){
	  List<SelectItem> ret = this.getLentTos();
	  ret.add(0,new SelectItem(""));
	  return ret;
	  
  }
  


  
  
}
