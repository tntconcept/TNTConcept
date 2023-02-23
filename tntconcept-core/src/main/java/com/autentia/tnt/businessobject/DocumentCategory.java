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

package com.autentia.tnt.businessobject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.autentia.tnt.dao.ITransferObject;
import com.autentia.tnt.util.ConfigurationUtil;

/**
 * Transfer object to store Contacts
 * @author stajanov code generator
 */
public class DocumentCategory implements Serializable, ITransferObject 
{
/* documentCategory - generated by stajanov (do not edit/delete) */









  // Fields
  
  
    
  private Integer id;

      
      
  private String name;

      
      
  private String description;

      
      
  private String code;

      
      
  private Date documentsLastUpdate;

      
      
  private Integer ownerId;

      
      
  private Integer departmentId;

      
      
  private Date insertDate;

      
      
  private Date updateDate;

        
  
    
  private DocumentCategory padre;

        	 	

  // Setters and getters
  
  
  
  public Integer getId() {
    return id;
  }
  private void setId( Integer id ) {
    this.id = id;
  }
      
  
  
  public String getName() {
    return name;
  }
  public void setName( String name ) {
    this.name = name;
  }
      
  
  
  public String getDescription() {
    return description;
  }
  public void setDescription( String description ) {
    this.description = description;
  }
      
  
  
  public String getCode() {
    return code;
  }
  public void setCode( String code ) {
    this.code = code;
  }
      
  
  
  public Date getDocumentsLastUpdate() {
    return documentsLastUpdate;
  }
  public void setDocumentsLastUpdate( Date documentsLastUpdate ) {
    this.documentsLastUpdate = documentsLastUpdate;
  }
      
  
  
  public Integer getOwnerId() {
    return ownerId;
  }
  public void setOwnerId( Integer ownerId ) {
    this.ownerId = ownerId;
  }
      
  
  
  public Integer getDepartmentId() {
    return departmentId;
  }
  public void setDepartmentId( Integer departmentId ) {
    this.departmentId = departmentId;
  }
      
  
  
  public Date getInsertDate() {
    return insertDate;
  }
  private void setInsertDate( Date insertDate ) {
    this.insertDate = insertDate;
  }
      
  
  
  public Date getUpdateDate() {
    return updateDate;
  }
  public void setUpdateDate( Date updateDate ) {
    this.updateDate = updateDate;
  }
        
  
  
  public DocumentCategory getPadre() {
    return padre;
  }
  public void setPadre( DocumentCategory padre ) {
    this.padre = padre;
  }
        
      
      
      
    
  public boolean equals( Object that )
  {
  	try {
	  if (that == null) 
  		return false;
  	else 
    	return this.getId().equals( ((DocumentCategory)that).getId() );
  } catch (Exception e) {
		return false;
	}
  }
  
  public int hashCode() {
  	  if(this.getId()==null)
  	  	return super.hashCode();
  	  else	
	 	return this.getId().intValue();
	}
/* documentCategory - generated by stajanov (do not edit/delete) */

  Boolean hasUpdatedDocuments = null;

  /**
   * Returns if this category has updated documents
   */
  public Boolean getHasUpdatedDocuments() {
    if (hasUpdatedDocuments==null) {
	  if (this.documentsLastUpdate!=null ) {		  
		  long threshold = (new Date()).getTime() - ConfigurationUtil.getDefault().getUpdatedCategoryDaysInMillis();
	      if (this.documentsLastUpdate.getTime() - threshold > 0 ) {
			  hasUpdatedDocuments = Boolean.TRUE;
		  } else {
			  hasUpdatedDocuments = Boolean.FALSE;
		  }
	  } else {
		  hasUpdatedDocuments = Boolean.FALSE;
	  }
	}
    return hasUpdatedDocuments;
  }

  public void setHasUpdatedDocuments(Boolean hasUpdatedDocuments) {
    this.hasUpdatedDocuments = hasUpdatedDocuments;
  }

	public List<Integer> getOwnersId() {
		// TODO Auto-generated method stub
		return null;
	}

}
