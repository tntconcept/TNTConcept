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














package com.autentia.tnt.dao.search;

import java.util.*;
import java.math.*;

import org.hibernate.type.*;

import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.*;

/**
 * Class to search for BulletinBoard objects
 * @author stajanov code generator
 */
public class CollaboratorSearch extends SearchCriteria 
{
/* generated by stajanov (do not edit/delete) */
















  @Override
  public String getHQL() {
    StringBuilder ret = new StringBuilder();
    int iArgNum = 0;
            
    
  
        
    if( isOwnerIdSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getOwnerId()==null ){
	ret.append( "ownerId is NULL" );
      } else {
	ret.append( "ownerId = :arg"+(iArgNum++) );
      }
    }

              
    
  
        
    if( isDepartmentIdSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getDepartmentId()==null ){
	ret.append( "departmentId is NULL" );
      } else {
	ret.append( "departmentId = :arg"+(iArgNum++) );
      }
    }

              
    
      if( isStartInsertDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( startInsertDate == null ){
        ret.append( "insertDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "insertDate>=:arg"+(iArgNum++) );
      }
    }
    if( isEndInsertDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( endInsertDate == null ){
        ret.append( "insertDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "insertDate<=:arg"+(iArgNum++) );
      }
    }

              
    
      if( isStartUpdateDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( startUpdateDate == null ){
        ret.append( "updateDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "updateDate>=:arg"+(iArgNum++) );
      }
    }
    if( isEndUpdateDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( endUpdateDate == null ){
        ret.append( "updateDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "updateDate<=:arg"+(iArgNum++) );
      }
    }

                  
    
  
        
    if( isUserSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getUser()==null ){
	ret.append( "user is NULL" );
      } else {
	ret.append( "user = :arg"+(iArgNum++) );
      }
    }

              
    
  
        
    if( isContactSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getContact()==null ){
	ret.append( "contact is NULL" );
      } else {
	ret.append( "contact = :arg"+(iArgNum++) );
      }
    }

              
    
  
        
    if( isOrganizationSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getOrganization()==null ){
	ret.append( "organization is NULL" );
      } else {
	ret.append( "organization = :arg"+(iArgNum++) );
      }
    }

                  customGetHQL(ret,iArgNum);
    return ret.toString();
  }

  @Override
  public Object[] getArguments(){
    ArrayList<Object> ret = new ArrayList<Object>();
            
  
      if( isOwnerIdSet() && getOwnerId()!=null ){
        ret.add( ownerId );
    }

              
  
      if( isDepartmentIdSet() && getDepartmentId()!=null ){
        ret.add( departmentId );
    }

              
  
      if( isStartInsertDateSet() ){
        ret.add( startInsertDate );
    }
    if( isEndInsertDateSet() ){
        ret.add( endInsertDate );
    }

              
  
      if( isStartUpdateDateSet() ){
        ret.add( startUpdateDate );
    }
    if( isEndUpdateDateSet() ){
        ret.add( endUpdateDate );
    }

                  
  
      if( isUserSet() && getUser()!=null ){
        ret.add( user );
    }

              
  
      if( isContactSet() && getContact()!=null ){
        ret.add( contact );
    }

              
  
      if( isOrganizationSet() && getOrganization()!=null ){
        ret.add( organization );
    }

                  customGetArguments(ret);
    return ret.toArray();
  }

  @Override
  public void reset(){
            
  
      unsetOwnerId();
  
              
  
      unsetDepartmentId();
  
              
  
      unsetStartInsertDate();
    unsetEndInsertDate();

              
  
      unsetStartUpdateDate();
    unsetEndUpdateDate();

                  
  
      unsetUser();
  
              
  
      unsetContact();
  
              
  
      unsetOrganization();
  
                  customReset();
  }
    
  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    ret.append("CollaboratorSearch{");
            
  
  
          if( isOwnerIdSet() ){
          ret.append( "(ownerId" );
          ret.append( "="+ownerId );
          ret.append( ")" );
      }

    
              
  
  
          if( isDepartmentIdSet() ){
          ret.append( "(departmentId" );
          ret.append( "="+departmentId );
          ret.append( ")" );
      }

    
              
  
  
    if( isStartInsertDateSet() ){
        ret.append( "(startInsertDate" );
        ret.append( "="+startInsertDate );
        ret.append( ")" );
    }
    if( isEndInsertDateSet() ){
        ret.append( "(endInsertDate" );
        ret.append( "="+endInsertDate );
        ret.append( ")" );
    }

              
  
  
    if( isStartUpdateDateSet() ){
        ret.append( "(startUpdateDate" );
        ret.append( "="+startUpdateDate );
        ret.append( ")" );
    }
    if( isEndUpdateDateSet() ){
        ret.append( "(endUpdateDate" );
        ret.append( "="+endUpdateDate );
        ret.append( ")" );
    }

                  
  
  
          if( isUserSet() ){
          ret.append( "(user" );
          ret.append( "="+user );
          ret.append( ")" );
      }

    
              
  
  
          if( isContactSet() ){
          ret.append( "(contact" );
          ret.append( "="+contact );
          ret.append( ")" );
      }

    
              
  
  
          if( isOrganizationSet() ){
          ret.append( "(organization" );
          ret.append( "="+organization );
          ret.append( ")" );
      }

    
                  customToString(ret);
    ret.append("}");
    return ret.toString();
  }

  // Getters and setters
        
  
  
    
    
    public boolean isOwnerIdSet(){
        return ownerIdSet;
    }
    public Integer getOwnerId(){
        return ownerId;
    }
    public void setOwnerId( Integer ownerId ){
        this.ownerId = ownerId;
        this.ownerIdSet = true;
    }
    public void unsetOwnerId(){
        this.ownerIdSet = false;
    }
          
  
  
    
    
    public boolean isDepartmentIdSet(){
        return departmentIdSet;
    }
    public Integer getDepartmentId(){
        return departmentId;
    }
    public void setDepartmentId( Integer departmentId ){
        this.departmentId = departmentId;
        this.departmentIdSet = true;
    }
    public void unsetDepartmentId(){
        this.departmentIdSet = false;
    }
          
  
  
    public boolean isStartInsertDateSet(){
        return startInsertDateSet;
    }
    public Date getStartInsertDate(){
        return startInsertDate;
    }
    public void setStartInsertDate( Date startInsertDate ){
        this.startInsertDate = startInsertDate;
        this.startInsertDateSet = true;
    }
    public void unsetStartInsertDate(){
        this.startInsertDateSet = false;
    }
    public boolean isEndInsertDateSet(){
        return endInsertDateSet;
    }
    public Date getEndInsertDate(){
        return endInsertDate;
    }
    public void setEndInsertDate( Date endInsertDate ){
        this.endInsertDate = endInsertDate;
        this.endInsertDateSet = true;
    }
    public void unsetEndInsertDate(){
        this.endInsertDateSet = false;
    }

          
  
  
    public boolean isStartUpdateDateSet(){
        return startUpdateDateSet;
    }
    public Date getStartUpdateDate(){
        return startUpdateDate;
    }
    public void setStartUpdateDate( Date startUpdateDate ){
        this.startUpdateDate = startUpdateDate;
        this.startUpdateDateSet = true;
    }
    public void unsetStartUpdateDate(){
        this.startUpdateDateSet = false;
    }
    public boolean isEndUpdateDateSet(){
        return endUpdateDateSet;
    }
    public Date getEndUpdateDate(){
        return endUpdateDate;
    }
    public void setEndUpdateDate( Date endUpdateDate ){
        this.endUpdateDate = endUpdateDate;
        this.endUpdateDateSet = true;
    }
    public void unsetEndUpdateDate(){
        this.endUpdateDateSet = false;
    }

            
  
  
    
    
    public boolean isUserSet(){
        return userSet;
    }
    public User getUser(){
        return user;
    }
    public void setUser( User user ){
        this.user = user;
        this.userSet = true;
    }
    public void unsetUser(){
        this.userSet = false;
    }
          
  
  
    
    
    public boolean isContactSet(){
        return contactSet;
    }
    public Contact getContact(){
        return contact;
    }
    public void setContact( Contact contact ){
        this.contact = contact;
        this.contactSet = true;
    }
    public void unsetContact(){
        this.contactSet = false;
    }
          
  
  
    
    
    public boolean isOrganizationSet(){
        return organizationSet;
    }
    public Organization getOrganization(){
        return organization;
    }
    public void setOrganization( Organization organization ){
        this.organization = organization;
        this.organizationSet = true;
    }
    public void unsetOrganization(){
        this.organizationSet = false;
    }
        
  // Fields
        
  
      private boolean ownerIdSet;
        private Integer ownerId;

          
  
      private boolean departmentIdSet;
        private Integer departmentId;

          
  
      private boolean startInsertDateSet;
        private Date startInsertDate;
    private boolean endInsertDateSet;
        private Date endInsertDate;

          
  
      private boolean startUpdateDateSet;
        private Date startUpdateDate;
    private boolean endUpdateDateSet;
        private Date endUpdateDate;

            
  
      private boolean userSet;
        private User user;
  
          
  
      private boolean contactSet;
        private Contact contact;
  
          
  
      private boolean organizationSet;
        private Organization organization;
  
        
  // Returns if there are a search condition active
  public boolean isSearchActive() {
    return customIsSearchActive()||ownerIdSet||departmentIdSet||startInsertDateSet||endInsertDateSet||startUpdateDateSet||endUpdateDateSet||userSet||contactSet||organizationSet;
  }


/* generated by stajanov (do not edit/delete) */
  
	private void customGetHQL(StringBuilder ret, int iArgNum)
	{
	}

	private void customGetArguments(ArrayList ret)
	{
	}

	private void customReset()
	{
	}

	private void customToString(StringBuilder ret)
	{
	}

	private boolean customIsSearchActive()
	{
		return false;
	}
}