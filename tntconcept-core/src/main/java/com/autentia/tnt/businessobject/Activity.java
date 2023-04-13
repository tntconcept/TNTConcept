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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.autentia.tnt.dao.ITransferObject;
import com.autentia.tnt.dao.hibernate.UserDAO;

/**
 * Transfer object to store Activitys
 * 
 * @author stajanov code generator
 */
public class Activity implements Serializable, ITransferObject {
	/** Serial version field */
	private static final long	serialVersionUID	= -1L;

/* activity - generated by stajanov (do not edit/delete) */







  // Fields
  
  
    
  private Integer id;

      
      
  private Date start;

      
      
  private int duration;

      
      
  private String description;

      
      
  private boolean billable;

      
      
  private Integer departmentId;

      
      
  private Date insertDate;

      
      
  private Date updateDate;

        
  
    
  private User user;

      
  
    
  private ProjectRole role;



  private boolean hasEvidences;

  private ActivityApprovalState approvalState = ActivityApprovalState.NA;



    // Setters and getters

    public boolean isHasEvidences() {
        return hasEvidences;
    }

    public void setHasEvidences(boolean hasEvidences) {
        this.hasEvidences = hasEvidences;
    }
  
  
  public Integer getId() {
    return id;
  }
  private void setId( Integer id ) {
    this.id = id;
  }
      
  
  
  public Date getStart() {
    return start;
  }
  public void setStart(Date start) {
    this.start = start;
  }
      
  
  
  public int getDuration() {
    return duration;
  }
  public void setDuration( int duration ) {
    this.duration = duration;
  }
      
  
  
  public String getDescription() {
    return description;
  }
  public void setDescription( String description ) {
    this.description = description;
  }
      
  
  
  public boolean isBillable() {
    return billable;
  }
  public void setBillable( boolean billable ) {
    this.billable = billable;
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
        
  
  
  public User getUser() {
    return user;
  }
  public void setUser( User user ) {
    this.user = user;
  }
      
  
  
  public ProjectRole getRole() {
    return role;
  }
  public void setRole( ProjectRole role ) {
    this.role = role;
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
    		return this.getId().equals( ((Activity)that).getId() );
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

/* activity - generated by stajanov (do not edit/delete) */
	
	public Date getEndDate() {
		Calendar cal = Calendar.getInstance();
		
		if (getStart() == null)
			return null;
		
		cal.setTime(getStart());
		cal.add(Calendar.MINUTE, getDuration());
		
		return cal.getTime();
	}

	public void setEndDate(Date endDate) {
		//do nothing
	}
	public List<Integer> getOwnersId() {
		// TODO Auto-generated method stub
		return null;
	}

    public ActivityApprovalState getApprovalState() {
        return approvalState;
    }

    public void setApprovalState(ActivityApprovalState approvalState) {
        this.approvalState = approvalState;
    }
}
