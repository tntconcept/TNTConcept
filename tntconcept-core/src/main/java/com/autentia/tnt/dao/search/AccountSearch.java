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
 * Class to search for Account objects
 * @author stajanov code generator
 */
public class AccountSearch extends SearchCriteria 
{
/* account - generated by stajanov (do not edit/delete) */















  @Override
  public String getHQL() {
    StringBuilder ret = new StringBuilder();
    int iArgNum = 0;
            
    
  
        
    if( isNameSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getName()==null ){
	ret.append( "name is NULL" );
      } else {
	ret.append( "name like :arg"+(iArgNum++) );
      }
    }

              
    
  
        
    if( isNumberSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getNumber()==null ){
	ret.append( "number is NULL" );
      } else {
	ret.append( "number = :arg"+(iArgNum++) );
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

                  
    
  
        
    if( isTypeSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getType()==null ){
	ret.append( "type is NULL" );
      } else {
	ret.append( "type = :arg"+(iArgNum++) );
      }
    }

                  customGetHQL(ret,iArgNum);
    return ret.toString();
  }

  @Override
  public Object[] getArguments(){
    ArrayList<Object> ret = new ArrayList<Object>();
            
  
      if( isNameSet() && getName()!=null ){
        ret.add( name );
    }

              
  
      if( isNumberSet() && getNumber()!=null ){
        ret.add( number );
    }

              
  
      if( isDescriptionSet() && getDescription()!=null ){
        ret.add( description );
    }

              
  
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

                  
  
      if( isTypeSet() && getType()!=null ){
        ret.add( type );
    }

                  customGetArguments(ret);
    return ret.toArray();
  }

  @Override
  public void reset(){
            
  
      unsetName();
  
              
  
      unsetNumber();
  
              
  
      unsetDescription();
  
              
  
      unsetOwnerId();
  
              
  
      unsetDepartmentId();
  
              
  
      unsetStartInsertDate();
    unsetEndInsertDate();

              
  
      unsetStartUpdateDate();
    unsetEndUpdateDate();

                  
  
      unsetType();
  
                  customReset();
  }
    
  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    ret.append("AccountSearch{");
            
  
  
          if( isNameSet() ){
          ret.append( "(name" );
          ret.append( "="+name );
          ret.append( ")" );
      }

    
              
  
  
          if( isNumberSet() ){
          ret.append( "(number" );
          ret.append( "="+number );
          ret.append( ")" );
      }

    
              
  
  
          if( isDescriptionSet() ){
          ret.append( "(description" );
          ret.append( "="+description );
          ret.append( ")" );
      }

    
              
  
  
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

                  
  
  
          if( isTypeSet() ){
          ret.append( "(type" );
          ret.append( "="+type );
          ret.append( ")" );
      }

    
                  customToString(ret);
    ret.append("}");
    return ret.toString();
  }

  // Getters and setters
        
  
  
    
    
    public boolean isNameSet(){
        return nameSet;
    }
    public String getName(){
        return name;
    }
    public void setName( String name ){
        this.name = name;
        this.nameSet = true;
    }
    public void unsetName(){
        this.nameSet = false;
    }
          
  
  
    
    
    public boolean isNumberSet(){
        return numberSet;
    }
    public String getNumber(){
        return number;
    }
    public void setNumber( String number ){
        this.number = number;
        this.numberSet = true;
    }
    public void unsetNumber(){
        this.numberSet = false;
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

            
  
  
    
    
    public boolean isTypeSet(){
        return typeSet;
    }
    public AccountType getType(){
        return type;
    }
    public void setType( AccountType type ){
        this.type = type;
        this.typeSet = true;
    }
    public void unsetType(){
        this.typeSet = false;
    }
        
  // Fields
        
  
      private boolean nameSet;
        private String name;

          
  
      private boolean numberSet;
        private String number;

          
  
      private boolean descriptionSet;
        private String description;

          
  
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

            
  
      private boolean typeSet;
        private AccountType type;
  
        
  // Returns if there are a search condition active
  public boolean isSearchActive() {
    return customIsSearchActive()||nameSet||numberSet||descriptionSet||ownerIdSet||departmentIdSet||startInsertDateSet||endInsertDateSet||startUpdateDateSet||endUpdateDateSet||typeSet;
  }


/* account - generated by stajanov (do not edit/delete) */

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
