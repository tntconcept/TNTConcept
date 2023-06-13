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
 * Transfer object to store ProjectCosts
 * @author stajanov code generator
 */
public class ProjectCost implements Serializable, ITransferObject 
{
  /** Serial version field */
  private static final long serialVersionUID = -1L;

/* projectCost - generated by stajanov (do not edit/delete) */









  // Fields
  
  
    
  private Integer id;

  private Date date;
      
  private String name;

      
      
  private BigDecimal cost;

      
      
  private boolean billable;

      
      
  private Integer ownerId;

      
      
  private Integer departmentId;

      
      
  private Date insertDate;

      
      
  private Date updateDate;

        
  
    
  private Project project;

        	 	

  // Setters and getters
  
  
  
  public Integer getId() {
    return id;
  }
  private void setId( Integer id ) {
    this.id = id;
  }
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getName() {
    return name;
  }
  public void setName( String name ) {
    this.name = name;
  }
      
  
  
  public BigDecimal getCost() {
    return cost;
  }
  public void setCost( BigDecimal cost ) {
    this.cost = cost;
  }
      
  
  
  public boolean isBillable() {
    return billable;
  }
  public void setBillable( boolean billable ) {
    this.billable = billable;
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
  private void setInsertDate(Date insertDate) {
    this.insertDate = insertDate;
  }
      
  
  
  public Date getUpdateDate() {
    return updateDate;
  }
  private void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }
        
  
  
  public Project getProject() {
    return project;
  }
  public void setProject( Project project ) {
    this.project = project;
  }
        
      
      
      
    
  public boolean equals( Object that )
  {
  try {
	  if (that == null) 
  		return false;
  	else 
    	return this.getId().equals( ((ProjectCost)that).getId() );
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
/* projectCost - generated by stajanov (do not edit/delete) */
	public List<Integer> getOwnersId() {
		// TODO Auto-generated method stub
		return null;
	}
}
