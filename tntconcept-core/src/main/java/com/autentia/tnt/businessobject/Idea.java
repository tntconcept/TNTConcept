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
import com.autentia.tnt.dao.hibernate.UserDAO;

/**
 * Transfer object to store Ideas
 * @author stajanov code generator
 */
public class Idea implements Serializable, ITransferObject 
{
  /** Serial version field */
  private static final long serialVersionUID = -1L;

/* idea - generated by stajanov (do not edit/delete) */







  // Fields
  
  
    
  private Integer id;

      
      
  private Date creationDate;

      
      
  private String name;

      
      
  private String description;

      
      
  private String benefits;

      
      
  private String cost;

      
      
  private Integer departmentId;

      
      
  private Date insertDate;

      
      
  private Date updateDate;

        
  
    
  private User user;

        	 	

  // Setters and getters
  
  
  
  public Integer getId() {
    return id;
  }
  private void setId( Integer id ) {
    this.id = id;
  }
      
  
  
  public Date getCreationDate() {
    return creationDate;
  }
  public void setCreationDate( Date creationDate ) {
    this.creationDate = creationDate;
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
      
  
  
  public String getBenefits() {
    return benefits;
  }
  public void setBenefits( String benefits ) {
    this.benefits = benefits;
  }
      
  
  
  public String getCost() {
    return cost;
  }
  public void setCost( String cost ) {
    this.cost = cost;
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
        
  
  
  public User getUser() {
    return user;
  }
  public void setUser( User user ) {
    this.user = user;
  }
        
      public Integer getOwnerId()
    {
      return user.getId();
    }
    
    
      public void setOwnerId(Integer ownerId) {
      user = UserDAO.getDefault().loadById(ownerId);
    }
    
  
  @Override
  public boolean equals( Object that )
  {
  	try {
  		if (that == null) 
  			return false;
  		else 
    		return this.getId().equals( ((Idea)that).getId() );
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

/* idea - generated by stajanov (do not edit/delete) */
}
