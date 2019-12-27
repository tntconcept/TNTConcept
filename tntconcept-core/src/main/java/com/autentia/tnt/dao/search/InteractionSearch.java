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
 * Class to search for Interaction objects
 * @author stajanov code generator
 */
public class InteractionSearch extends SearchCriteria 
{
/* generated by stajanov (do not edit/delete) */














  @Override
  public String getHQL() {
    StringBuilder ret = new StringBuilder();
    int iArgNum = 0;
            
      if( isStartCreationDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( startCreationDate == null ){
        ret.append( "creationDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "creationDate>=:arg"+(iArgNum++) );
      }
    }
    if( isEndCreationDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( endCreationDate == null ){
        ret.append( "creationDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "creationDate<=:arg"+(iArgNum++) );
      }
    }

              
      
    
    if( isInterestSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getInterest()==null ){
	ret.append( "interest is NULL" );
      } else {
	ret.append( "interest = :arg"+(iArgNum++) );
      }
    }

              
      
    
    if( isDescriptionSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getDescription()==null ){
	ret.append( "description is NULL" );
      } else {
	ret.append( "description = :arg"+(iArgNum++) );
      }
    }

              
      
    
    if( isFileSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getFile()==null ){
	ret.append( "file is NULL" );
      } else {
	ret.append( "file = :arg"+(iArgNum++) );
      }
    }

              
      
    
    if( isFileMimeSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getFileMime()==null ){
	ret.append( "fileMime is NULL" );
      } else {
	ret.append( "fileMime = :arg"+(iArgNum++) );
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

                  
      
    
    if( isProjectSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getProject()==null ){
	ret.append( "project is NULL" );
      } else {
	ret.append( "project = :arg"+(iArgNum++) );
      }
    }

              
      
    
    if( isTypeSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getType()==null ){
	ret.append( "type is NULL" );
      } else {
	ret.append( "type = :arg"+(iArgNum++) );
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

              
      
    
    if( isOfferSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getOffer()==null ){
	ret.append( "offer is NULL" );
      } else {
	ret.append( "offer = :arg"+(iArgNum++) );
      }
    }

                  customGetHQL(ret,iArgNum);
    return ret.toString();
  }

  @Override
  public Object[] getArguments(){
    ArrayList<Object> ret = new ArrayList<Object>();
          
      if( isStartCreationDateSet() ){
        ret.add( startCreationDate );
    }
    if( isEndCreationDateSet() ){
        ret.add( endCreationDate );
    }

            
      if( isInterestSet() && getInterest()!=null ){
        ret.add( interest );
    }

     
            
      if( isDescriptionSet() && getDescription()!=null ){
        ret.add( description );
    }

            
      if( isFileSet() && getFile()!=null ){
        ret.add( file );
    }

            
      if( isFileMimeSet() && getFileMime()!=null ){
        ret.add( fileMime );
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

                
      if( isProjectSet() && getProject()!=null ){
        ret.add( project );
    }

            
      if( isTypeSet() && getType()!=null ){
        ret.add( type );
    }

            
      if( isUserSet() && getUser()!=null ){
        ret.add( user );
    }

            
      if( isOfferSet() && getOffer()!=null ){
        ret.add( offer );
    }

                  customGetArguments(ret);
    return ret.toArray();
  }

  @Override
  public void reset(){
          
      unsetStartCreationDate();
    unsetEndCreationDate();

            
      unsetInterest();
  
            
      
  
            
      unsetDescription();
  
            
      unsetFile();
  
            
      unsetFileMime();
  
            
      
  
            
      unsetDepartmentId();
  
            
      unsetStartInsertDate();
    unsetEndInsertDate();

            
      unsetStartUpdateDate();
    unsetEndUpdateDate();

                
      unsetProject();
  
            
      unsetType();
  
            
      unsetUser();
  
            
      unsetOffer();
  
                  customReset();
  }
    
  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    ret.append("InteractionSearch{");
          
  
    if( isStartCreationDateSet() ){
        ret.append( "(startCreationDate" );
        ret.append( "="+startCreationDate );
        ret.append( ")" );
    }
    if( isEndCreationDateSet() ){
        ret.append( "(endCreationDate" );
        ret.append( "="+endCreationDate );
        ret.append( ")" );
    }

            
  
          if( isInterestSet() ){
          ret.append( "(interest" );
          ret.append( "="+interest );
          ret.append( ")" );
      }

    
            
  
      

    
            
  
          if( isDescriptionSet() ){
          ret.append( "(description" );
          ret.append( "="+description );
          ret.append( ")" );
      }

    
            
  
          if( isFileSet() ){
          ret.append( "(file" );
          ret.append( "="+file );
          ret.append( ")" );
      }

    
            
  
          if( isFileMimeSet() ){
          ret.append( "(fileMime" );
          ret.append( "="+fileMime );
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

                
  
          if( isProjectSet() ){
          ret.append( "(project" );
          ret.append( "="+project );
          ret.append( ")" );
      }

    
            
  
          if( isTypeSet() ){
          ret.append( "(type" );
          ret.append( "="+type );
          ret.append( ")" );
      }

    
            
  
          if( isUserSet() ){
          ret.append( "(user" );
          ret.append( "="+user );
          ret.append( ")" );
      }

    
            
  
          if( isOfferSet() ){
          ret.append( "(offer" );
          ret.append( "="+offer );
          ret.append( ")" );
      }

    
                  customToString(ret);
    ret.append("}");
    return ret.toString();
  }

  // Getters and setters
      
  
    public boolean isStartCreationDateSet(){
        return startCreationDateSet;
    }
    public Date getStartCreationDate(){
        return startCreationDate;
    }
    public void setStartCreationDate( Date startCreationDate ){
        this.startCreationDate = startCreationDate;
        this.startCreationDateSet = true;
    }
    public void unsetStartCreationDate(){
        this.startCreationDateSet = false;
    }
    public boolean isEndCreationDateSet(){
        return endCreationDateSet;
    }
    public Date getEndCreationDate(){
        return endCreationDate;
    }
    public void setEndCreationDate( Date endCreationDate ){
        this.endCreationDate = endCreationDate;
        this.endCreationDateSet = true;
    }
    public void unsetEndCreationDate(){
        this.endCreationDateSet = false;
    }

        
  
        
    
    public boolean isInterestSet(){
        return interestSet;
    }
    public InteractionInterest getInterest(){
        return interest;
    }
    public void setInterest( InteractionInterest interest ){
        this.interest = interest;
        this.interestSet = true;
    }
    public void unsetInterest(){
        this.interestSet = false;
    }
        
  
    
    
    
    
        
  
    
    
    public boolean isDescriptionSet(){
        return descriptionSet;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription( String description ){
        this.description = description;
        this.descriptionSet = true;
    }
    public void unsetDescription(){
        this.descriptionSet = false;
    }
        
  
    
    
    public boolean isFileSet(){
        return fileSet;
    }
    public String getFile(){
        return file;
    }
    public void setFile( String file ){
        this.file = file;
        this.fileSet = true;
    }
    public void unsetFile(){
        this.fileSet = false;
    }
        
  
    
    
    public boolean isFileMimeSet(){
        return fileMimeSet;
    }
    public String getFileMime(){
        return fileMime;
    }
    public void setFileMime( String fileMime ){
        this.fileMime = fileMime;
        this.fileMimeSet = true;
    }
    public void unsetFileMime(){
        this.fileMimeSet = false;
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

          
  
    
    
    public boolean isProjectSet(){
        return projectSet;
    }
    public Project getProject(){
        return project;
    }
    public void setProject( Project project ){
        this.project = project;
        this.projectSet = true;
    }
    public void unsetProject(){
        this.projectSet = false;
    }
        
  
    
    
    public boolean isTypeSet(){
        return typeSet;
    }
    public InteractionType getType(){
        return type;
    }
    public void setType( InteractionType type ){
        this.type = type;
        this.typeSet = true;
    }
    public void unsetType(){
        this.typeSet = false;
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
        
  
    
    
    public boolean isOfferSet(){
        return offerSet;
    }
    public Offer getOffer(){
        return offer;
    }
    public void setOffer( Offer offer ){
        this.offer = offer;
        this.offerSet = true;
    }
    public void unsetOffer(){
        this.offerSet = false;
    }
        
  // Fields
      
      private boolean startCreationDateSet;
        private Date startCreationDate;
    private boolean endCreationDateSet;
        private Date endCreationDate;

        
      private boolean interestSet;
        private InteractionInterest interest;

        
  

        
      private boolean descriptionSet;
        private String description;

        
      private boolean fileSet;
        private String file;

        
      private boolean fileMimeSet;
        private String fileMime;

        
  
        
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

          
      private boolean projectSet;
        private Project project;
  
        
      private boolean typeSet;
        private InteractionType type;
  
        
      private boolean userSet;
        private User user;
  
        
      private boolean offerSet;
        private Offer offer;
  
        
  // Returns if there are a search condition active
  public boolean isSearchActive() {
    return customIsSearchActive()||startCreationDateSet||endCreationDateSet||interestSet||descriptionSet||fileSet||fileMimeSet||departmentIdSet||startInsertDateSet||endInsertDateSet||startUpdateDateSet||endUpdateDateSet||projectSet||typeSet||userSet||offerSet;
  }

/* generated by stajanov (do not edit/delete) */

	private void customGetHQL(StringBuilder ret, int iArgNum)
	{
	}

	private boolean customIsSearchActive()
	{
		return false;
	}

	private void customToString(StringBuilder ret)
	{
	}

	private void customReset()
	{
	}

	private void customGetArguments(ArrayList ret)
	{
	}
}
