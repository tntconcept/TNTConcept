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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.autentia.tnt.dao.ITransferObject;

/**
 * Transfer object to store Books
 * @author stajanov code generator
 */
public class Book implements Serializable, ITransferObject 
{

/* book - generated by stajanov (do not edit/delete) */







  // Fields
  
  
    
  private Integer id;

      
      
  private String name;

      
      
  private String author;

      
      
  private String ISBN;

      
      
  private String URL;

      
      
  private BigDecimal price;

      
      
  private Date purchaseDate;

      
      
  private Integer ownerId;

      
      
  private Integer departmentId;

      
      
  private Date insertDate;

      
      
  private Date updateDate;

        
  
    
  private User lentTo;

        	 	

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
      
  
  
  public String getAuthor() {
    return author;
  }
  public void setAuthor( String author ) {
    this.author = author;
  }
      
  
  
  public String getISBN() {
    return ISBN;
  }
  public void setISBN( String ISBN ) {
    this.ISBN = ISBN;
  }
      
  
  
  public String getURL() {
    return URL;
  }
  public void setURL( String URL ) {
    this.URL = URL;
  }
      
  
  
  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice( BigDecimal price ) {
    this.price = price;
  }
      
  
  
  public Date getPurchaseDate() {
    return purchaseDate;
  }
  public void setPurchaseDate( Date purchaseDate ) {
    this.purchaseDate = purchaseDate;
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
        
  
  
  public User getLentTo() {
    return lentTo;
  }
  public void setLentTo( User lentTo ) {
    this.lentTo = lentTo;
  }
        
    
    
    
  
  @Override
  public boolean equals( Object that )
  {
  	try {
  		if (that == null) 
  			return false;
  		else 
    		return this.getId().equals( ((Book)that).getId() );
    } catch (Exception e) {
		return false;
	}
  }
  
  @Override
  public int hashCode() {
  	  if(this.getId()==null)
  	  	return super.hashCode();
  	  else	
	 	return this.getId().intValue();
	}

	public List<Integer> getOwnersId() {
		// TODO Auto-generated method stub
		return null;
	}

/* book - generated by stajanov (do not edit/delete) */
}
