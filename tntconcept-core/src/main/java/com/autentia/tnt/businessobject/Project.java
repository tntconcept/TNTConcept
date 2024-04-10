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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.autentia.tnt.dao.ITransferObject;


/**
 * Transfer object to store Projects
 * @author stajanov code generator
 */
/**
 * @author alumno
 *
 */
public class Project implements Serializable, ITransferObject 
{
  /** Serial version field */
  private static final long serialVersionUID = -1L;
  private static final Log log = LogFactory.getLog(Project.class);
  
  
  /**
   * Test if a project is finished as of now
   * @return true if project is finished
   */
  public boolean isFinished(){
    return !open;
  }
  
  
/* project - generated by stajanov (do not edit/delete) */







  // Fields
  
  
    
  private Integer id;

      
      
  private Date startDate;

      
      
  private Date endDate;

      
      
  private Boolean open;

      
      
  private String name;

      
      
  private String description;

      
      
  private Boolean billable;

      
      
  private Integer ownerId;

      
      
  private Integer departmentId;

      
      
  private Date insertDate;

      
      
  private Date updateDate;

        
  
    
  private Organization client;

  private Offer offer;

          
              
  private Set<ProjectRole> roles = new HashSet<ProjectRole>();

      
              
  private Set<ProjectCost> costs = new HashSet<ProjectCost>();

  private BigDecimal estimatedCost;
  
  private User user;

    	 	

  // Setters and getters
  
  
  
  public Integer getId() {
    return id;
  }
  
  public void setId( Integer id ) {
    this.id = id;
  }
      
  
  
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate( Date startDate ) {
    this.startDate = startDate;
  }
      
  
  
  public Date getEndDate() {
    return endDate;
  }
  public void setEndDate( Date endDate ) {
    this.endDate = endDate;
  }
      
  
  
  public Boolean getOpen() {
    return open;
  }
  public void setOpen( Boolean open ) {
    this.open = open;
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
      
  
  
  public Boolean getBillable() {
    return billable;
  }
  public void setBillable( Boolean billable ) {
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
  private void setInsertDate( Date insertDate ) {
    this.insertDate = insertDate;
  }
      
  
  
  public Date getUpdateDate() {
    return updateDate;
  }
  private void setUpdateDate( Date updateDate ) {
    this.updateDate = updateDate;
  }
        
  
  
  public Organization getClient() {
    return client;
  }
  public void setClient( Organization client ) {
    this.client = client;
  }
          
            
  public Set<ProjectRole> getRoles() {
    return roles;
  }
  public void setRoles( Set<ProjectRole> roles ) {
    this.roles = roles;
  }
      
            
  public Set<ProjectCost> getCosts() {
    return costs;
  }
  public void setCosts( Set<ProjectCost> costs ) {
    this.costs = costs;
  }

    public BigDecimal getEstimatedCost() { return estimatedCost; }

    public void setEstimatedCost(BigDecimal estimatedCost) { this.estimatedCost = estimatedCost; }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
  public boolean equals( Object that )
  {
  	try {
  		if (that == null) 
  			return false;
  		else 
    		return this.getId().equals( ((Project)that).getId() );
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

/* project - generated by stajanov (do not edit/delete) */
  
  public BigDecimal getTotalCost(){
    BigDecimal ret = new BigDecimal(0);
    
    if( getCosts()!=null ){
      for( ProjectCost cost : getCosts() ){
        BigDecimal c = cost.getCost();
        if( c!=null ){
          ret = ret.add( c );
        }
      }
    }
    if( getRoles()!=null ){
      for( ProjectRole role : getRoles() ){
        BigDecimal cph = role.getCostPerHour();
        if( cph!=null ){
          BigDecimal eh = new BigDecimal( role.getExpectedHours() );
          ret = ret.add( cph.multiply(  eh ) );
        }
      }
    }
    
    return ret;
  }

	public List<Integer> getOwnersId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @return Return the total worked time in minutes
	 */
	
	public long getWorkedHours(){
		  long total = 0;
		  if (this.getRoles()!=null){
			  for (ProjectRole role : this.getRoles()){
				  if(role.getActivities()!=null){
				   		 for (Activity activity : role.getActivities()){
						 total += activity.getDuration();
					}
				  }
			  }
		  }
		  return total;		
	}
	
	public long getExpectedHours(){
		  long			   total = 0;
		  if (this.getRoles()!=null){
			  for (ProjectRole role : this.getRoles()){
				  total += role.getExpectedHours();
			  }
		  }
		  return total;		
	}
	
	public double getPercentageWorked(){
		double expected = this.getExpectedHours();
		double worked	  = (double)this.getWorkedHours()/60;
		
		if (worked == 0){
			return 0;
		} else if (expected == 0){
			return 1;
		} else {
			return worked/expected;
		}
	}
	
	public double getPercentageCosts(){
		double finalcost = this.getCostPerProject().doubleValue();
		double expected	  = this.getTotalCost().doubleValue();
		
		if (finalcost == 0){
			return 0;
		} else if (expected == 0){
			return 1;
		} else {
			return finalcost/expected;
		}
	}
	

	public BigDecimal getCostPerProject(){
		BigDecimal 		 total = new BigDecimal(0);
		Set<ProjectRole> roles = this.getRoles();
		
		if (roles != null){
			for (ProjectRole role : roles){
				Set<Activity> activities = role.getActivities();
				if (activities != null){
					for (Activity activity : activities){
						BigDecimal converse = new BigDecimal(activity.getUser().getCostPerHour());					
						BigDecimal div = new BigDecimal( activity.getDuration());
						BigDecimal ret = div.multiply(converse);	
						total = total.add(ret.divide(new BigDecimal(60),2,BigDecimal.ROUND_HALF_UP));// += activity.getDuration()  * activity.getUser().getCostPerHour()/ 60 ;
					}
				}
			}
		}

		return total;
	}
}