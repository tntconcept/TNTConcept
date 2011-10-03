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

package com.autentia.tnt.bean.bulletin;


import java.io.*;
import java.util.*;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.*;

import org.acegisecurity.acls.domain.BasePermission;
import org.apache.commons.logging.*;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.autentia.tnt.bean.*;
import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.*;
import com.autentia.tnt.dao.search.*;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.bulletin.BulletinBoardCategoryManager;
import com.autentia.tnt.manager.bulletin.BulletinBoardManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.upload.*;
import com.autentia.tnt.util.*;

/**
 * UI bean for BulletinBoard objects.
 * @author stajanov code generator
 */
public class BulletinBoardBean extends BaseBean
{
	/** Serial version field */
	private static final long serialVersionUID = -1L;
	
	private static AuthenticationManager authManager = AuthenticationManager.getDefault();
	
	private static BulletinBoardCategoryManager bbcManager = BulletinBoardCategoryManager.getDefault();
	
	/**
	 * List public bulletinBoards sorted by creationDate inverse.
	 * @return a list of bulletinBoards
	 */
	public List<BulletinBoard> getPublic()
	{
		
		return manager.getPublic(new SortCriteria(sortColumn, sortAscending));
	}
	
	/**
	 * Select a bulletin board category to filter listed messages.
	 * @return forward to current page
	 */
	public String selectCategory()
	{
		Integer id = Integer.parseInt( FacesUtils.getRequestParameter(ROW_ID) );
		search.reset();
		search.setCategory( bbcManager.getEntityById(id) );
		return null;
	}
	
	/* bulletinBoard - generated by stajanov (do not edit/delete) */
	
	
	
	
	/** Logger */
	private static final Log log = LogFactory.getLog(BulletinBoardBean.class);
	
	/** Manager */
	private static BulletinBoardManager manager = BulletinBoardManager.getDefault();
	
	/** Upload service */
	private static final Uploader uploader = UploaderFactory.getInstance("bulletinBoard");
	
	
	/** Temporary documentPath field */
	private String oldDocumentPath;
	
	/** Uploaded documentPath object */
	private UploadedFile uploadDocumentPath;
	
	
	/** Active BulletinBoard object */
	private BulletinBoard bulletinBoard;
	
	/** Active search object */
	private BulletinBoardSearch search = new BulletinBoardSearch();
	
	/** Default sort column */
	private String sortColumn = "creationDate";
	
	/** Default sort order */
	private boolean sortAscending = false;
	
	/** Quick search letter for ABC pager control */
	private Character letter;
	
	/**
	 * List bulletinBoards. Order depends on Faces parameter sort.
	 * @return the list of all bulletinBoards sorted by requested criterion
	 */
	public List<BulletinBoard> getAll()
	{
		return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
	}
	
	// Getters to list possible values of related entities
	
	
	/**
	 * Get the list of all categorys
	 * @return the list of all categorys
	 */
	public List<SelectItem> getCategorys()
	{
		
		List<BulletinBoardCategory> refs = BulletinBoardCategoryManager.getDefault().getAllEntities(null,new SortCriteria("name"));
		
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		
		for( BulletinBoardCategory ref : refs )
		{
			ret.add( new SelectItem( ref, ref.getName() ) );
		}
		return ret;
		
	}
	
	
	/**
	 * Get the list of all users
	 * @return the list of all users
	 */
	public List<SelectItem> getUsers()
	{
		
		List<User> refs = UserManager.getDefault().getAllEntities(null,new SortCriteria("name"));
		
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		
		for( User ref : refs )
		{
			ret.add( new SelectItem( ref, ref.getName() ) );
		}
		return ret;
		
	}
	
	// Getters to list possible values of enum fields
	
	
	// Methods to create/remove instances of one-to-many entities (slave entities)
	
	/**
	   * Reset search criteria
	   * @return forward to LIST page
	   */
	  public String reset(){
	  	search.reset();
		return list();    
	  }
	
	/**
	 * Go to create page
	 * @return forward to CREATE page
	 */
	public String create()
	{
		bulletinBoard = new BulletinBoard();
		bulletinBoard.setCreationDate( new Date() );
		bulletinBoard.setUser( authManager.getCurrentPrincipal().getUser() );
		return NavigationResults.CREATE;
	}
	
	/**
	   * Go to detail page
	   * @return forward to DETAIL page
	   */
	  public String detail(){
	    Integer id = Integer.parseInt( FacesUtils.getRequestParameter(ROW_ID) );
	    bulletinBoard = manager.getEntityById(id);

	    return SpringUtils.isAclPermissionGranted( bulletinBoard, BasePermission.WRITE )
		    ? NavigationResults.EDIT
		    : NavigationResults.DETAIL;
	  }
	
	
	
//   Methods to create/remove instances of one-to-many entities (slave entities)
      
      /**
       * Whether or not create button is available for user
       * @return true if user can create objects of type User
       */
      public boolean isCreateAvailable()
      {
        return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(BulletinBoard.class));
      }

      /**
       * Whether or not edit button is available for user
       * @return true if user can edit current object
       */
      public boolean isEditAvailable()
      {
        return SpringUtils.isAclPermissionGranted(bulletinBoard,BasePermission.WRITE);
      }

      /**
       * Whether or not delete button is available for user
       * @return true if user can delete current object
       */
      public boolean isDeleteAvailable()
      {
        return (bulletinBoard.getId()!=null) &&
           SpringUtils.isAclPermissionGranted(bulletinBoard,BasePermission.DELETE);
      }
	
	
	
	/**
	 * Save bean and stay on it
	 * @return forward to list page
	 */
	public String save()
	{
		doBeforeSave();

		if( bulletinBoard.getId()==null )
		{
			manager.insertEntity(bulletinBoard);
		}
		else
		{
			manager.updateEntity(bulletinBoard);
		}
		
		
		
		// Handle uploads for documentPath field
		if( uploadDocumentPath!=null )
		{
			try
			{
				uploader.replace( Integer.toString(bulletinBoard.getId()), oldDocumentPath, uploadDocumentPath );
			}
			catch (IOException e)
			{
				log.error("save - exception uploading field documentPath",e);
				FacesUtils.addErrorMessage("documentPath","error.fileTransfer",e.getMessage());
			}
		}
		
		
		// Calls an after save action
		String result = doAfterSave(NavigationResults.LIST);
		
		// Unselect object
		bulletinBoard = null;
		
		return result;
	}
	
	/**
	 * Delete bean and go back to beans list
	 * @return forward to LIST page
	 */
	public String delete()
	{
		manager.deleteEntity(bulletinBoard);
		bulletinBoard = null;
		return NavigationResults.LIST;
	}
	
	/**
	 * Go back to beans list
	 * @return forward to LIST page
	 */
	public String list()
	{
		return NavigationResults.LIST;
	}
	
	/**
	 * Go to search page
	 * @return forward to SEARCH page
	 */
	public String search()
	{
		return NavigationResults.SEARCH;
	}
	
	/**
	 * Check if we have an active object.
	 * @return true is an object is selected
	 */
	public boolean isBulletinBoardSelected()
	{
		return bulletinBoard!=null;
	}
	
	// Getters and setters to manipulate sorting
	public boolean isSortAscending()
	{
		return sortAscending;
	}
	public void setSortAscending(boolean sortAscending)
	{
		this.sortAscending = sortAscending;
	}
	public String getSortColumn()
	{
		return sortColumn;
	}
	public void setSortColumn(String sortColumn)
	{
		this.sortColumn = sortColumn;
	}
	
	// Getters and setters to handle search
	public BulletinBoardSearch getSearch()
	{
		return search;
	}
	
	
	
	
	public Date getSearchStartCreationDate()
	{
		return search.getStartCreationDate();
	}
	public void setSearchStartCreationDate( Date val )
	{
		if( val!=null )
		{
			search.setStartCreationDate( val );
		}
		else
		{
			search.unsetStartCreationDate();
		}
	}
	public boolean isSearchStartCreationDateValid()
	{
		return search.isStartCreationDateSet();
	}
	public void setSearchStartCreationDateValid( boolean val )
	{
		if( val )
		{
			search.setStartCreationDate( search.getStartCreationDate() );
		}
		else
		{
			search.unsetStartCreationDate();
		}
	}
	public Date getSearchEndCreationDate()
	{
		return search.getEndCreationDate();
	}
	public void setSearchEndCreationDate( Date val )
	{
		if( val!=null )
		{
			search.setEndCreationDate( val );
		}
		else
		{
			search.unsetEndCreationDate();
		}
	}
	public boolean isSearchEndCreationDateValid()
	{
		return search.isEndCreationDateSet();
	}
	public void setSearchEndCreationDateValid( boolean val )
	{
		if( val )
		{
			search.setEndCreationDate( search.getEndCreationDate() );
		}
		else
		{
			search.unsetEndCreationDate();
		}
	}
	
	
	
	
	
	
	public String getSearchTitle()
	{
		return search.getTitle();
	}
	public void setSearchTitle( String val )
	{
		if( search.isTitleSet() )
		{
			search.setTitle( val );
		}
	}
	public boolean isSearchTitleValid()
	{
		return search.isTitleSet();
	}
	public void setSearchTitleValid( boolean val )
	{
		if( val )
		{
			search.setTitle( search.getTitle() );
		}
		else
		{
			search.unsetTitle();
		}
	}
	
	
	
	
	
	public String getSearchMessage()
	{
		return search.getMessage();
	}
	public void setSearchMessage( String val )
	{
		if( search.isMessageSet() )
		{
			search.setMessage( val );
		}
	}
	public boolean isSearchMessageValid()
	{
		return search.isMessageSet();
	}
	public void setSearchMessageValid( boolean val )
	{
		if( val )
		{
			search.setMessage( search.getMessage() );
		}
		else
		{
			search.unsetMessage();
		}
	}
	
	
	
	
	
	public String getSearchDocumentPath()
	{
		return search.getDocumentPath();
	}
	public void setSearchDocumentPath( String val )
	{
		if( search.isDocumentPathSet() )
		{
			search.setDocumentPath( val );
		}
	}
	public boolean isSearchDocumentPathValid()
	{
		return search.isDocumentPathSet();
	}
	public void setSearchDocumentPathValid( boolean val )
	{
		if( val )
		{
			search.setDocumentPath( search.getDocumentPath() );
		}
		else
		{
			search.unsetDocumentPath();
		}
	}
	
	
	
	
	
	public String getSearchDocumentContentType()
	{
		return search.getDocumentContentType();
	}
	public void setSearchDocumentContentType( String val )
	{
		if( search.isDocumentContentTypeSet() )
		{
			search.setDocumentContentType( val );
		}
	}
	public boolean isSearchDocumentContentTypeValid()
	{
		return search.isDocumentContentTypeSet();
	}
	public void setSearchDocumentContentTypeValid( boolean val )
	{
		if( val )
		{
			search.setDocumentContentType( search.getDocumentContentType() );
		}
		else
		{
			search.unsetDocumentContentType();
		}
	}
	
	
	
	
	
	public BulletinBoardCategory getSearchCategory()
	{
		return search.getCategory();
	}
	public void setSearchCategory( BulletinBoardCategory val )
	{
		if( search.isCategorySet() )
		{
			search.setCategory( val );
		}
	}
	public boolean isSearchCategoryValid()
	{
		return search.isCategorySet();
	}
	public void setSearchCategoryValid( boolean val )
	{
		if( val )
		{
			search.setCategory( search.getCategory() );
		}
		else
		{
			search.unsetCategory();
		}
	}
	
	
	
	
	
	public User getSearchUser()
	{
		return search.getUser();
	}
	public void setSearchUser( User val )
	{
		if( search.isUserSet() )
		{
			search.setUser( val );
		}
	}
	public boolean isSearchUserValid()
	{
		return search.isUserSet();
	}
	public void setSearchUserValid( boolean val )
	{
		if( val )
		{
			search.setUser( search.getUser() );
		}
		else
		{
			search.unsetUser();
		}
	}
	
	
	// Getters and setters to handle uploads
	
	public void setUploadDocumentPath( UploadedFile uploadDocumentPath )
	{
		if( uploadDocumentPath!=null )
		{
			oldDocumentPath = bulletinBoard.getDocumentPath();
			this.uploadDocumentPath = uploadDocumentPath;
			setDocumentPath( FileUtil.getFileName(uploadDocumentPath.getName()) );
			setDocumentContentType( uploadDocumentPath.getContentType() );
		}
	}
	public UploadedFile getUploadDocumentPath()
	{
		return uploadDocumentPath;
	}
	
	
	// Getters and setters to manipulate active BulletinBoard object
	public java.lang.Integer getId()
	{
		return bulletinBoard.getId();
	}
	
	
	
	public Date getCreationDate()
	{
		return bulletinBoard.getCreationDate();
	}
	public void setCreationDate( Date creationDate )
	{
		bulletinBoard.setCreationDate( creationDate );
	}
	
	
	
	public String getTitle()
	{
		return bulletinBoard.getTitle();
	}
	public void setTitle( String title )
	{
		bulletinBoard.setTitle( title );
	}
	
	
	
	public String getMessage()
	{
		return bulletinBoard.getMessage();
	}
	public void setMessage( String message )
	{
		bulletinBoard.setMessage( message );
	}
	
	
	
	public String getDocumentPath()
	{
		return bulletinBoard.getDocumentPath();
	}
	public void setDocumentPath( String documentPath )
	{
		bulletinBoard.setDocumentPath( documentPath );
	}
	
	
	
	public String getDocumentContentType()
	{
		return bulletinBoard.getDocumentContentType();
	}
	public void setDocumentContentType( String documentContentType )
	{
		bulletinBoard.setDocumentContentType( documentContentType );
	}
	
	
	
	
	public BulletinBoardCategory getCategory()
	{
		return bulletinBoard.getCategory();
	}
	public void setCategory( BulletinBoardCategory category )
	{
		bulletinBoard.setCategory( category );
	}
	
	
	
	
	public User getUser()
	{
		return bulletinBoard.getUser();
	}
	public void setUser( User user )
	{
		bulletinBoard.setUser( user );
	}
	
	/** Handle an ABC pager letter click: filter objects by specified starting letter */
	public void letterClicked()
	{
		if( letter!=null )
		{
			UIComponent comp = FacesUtils.getComponent("bulletinBoards:list");
			HtmlDataTable tabla = (HtmlDataTable) comp;
			tabla.setFirst(0);
			
			search.setTitle( letter+"%" );
		}
		else
		{
			search.unsetTitle();
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
	/* bulletinBoard - generated by stajanov (do not edit/delete) */
	
	
	/**
	   * Go to detail page public access
	   * @return forward to DETAIL page
	   */
	  public String detailPublic(){
	    
		Integer id = Integer.parseInt( FacesUtils.getRequestParameter(ROW_ID) );
	    bulletinBoard = manager.getEntityByIdPublic(id);

	    return NavigationResults.DETAIL;
	  }
	  
	  
	  
	
}
