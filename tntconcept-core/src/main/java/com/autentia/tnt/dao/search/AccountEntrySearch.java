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



import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.*;
import com.autentia.tnt.util.ConfigurationUtil;

/**
 * Class to search for AccountEntry objects
 * @author stajanov code generator
 */
public class AccountEntrySearch extends SearchCriteria
{
/* generated by stajanov (do not edit/delete) */















  @Override
  public String getHQL() {
    StringBuilder ret = new StringBuilder();
    int iArgNum = 0;
            
    
      if( isStartDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( startDate == null ){
        ret.append( "date=:arg"+(iArgNum++) );
      } else {
        ret.append( "date>=:arg"+(iArgNum++) );
      }
    }
    if( isEndDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( endDate == null ){
        ret.append( "date=:arg"+(iArgNum++) );
      } else {
        ret.append( "date<=:arg"+(iArgNum++) );
      }
    }

              
    
      if( isStartAmountDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( startAmountDate == null ){
        ret.append( "amountDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "amountDate>=:arg"+(iArgNum++) );
      }
    }
    if( isEndAmountDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( endAmountDate == null ){
        ret.append( "amountDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "amountDate<=:arg"+(iArgNum++) );
      }
    }

              
    
  
        
    if( isConceptSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getConcept()==null ){
	ret.append( "concept is NULL" );
      } else {
	ret.append( "concept like :arg"+(iArgNum++) );
      }
    }

              
    
      if( isStartAmountSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( startAmount == null ){
        ret.append( "amount=:arg"+(iArgNum++) );
      } else {
        ret.append( "amount>=:arg"+(iArgNum++) );
      }
    }
    if( isEndAmountSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( endAmount == null ){
        ret.append( "amount=:arg"+(iArgNum++) );
      } else {
        ret.append( "amount<=:arg"+(iArgNum++) );
      }
    }

              
    
  
        
    if( isEntryNumberSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getEntryNumber()==null ){
	ret.append( "entryNumber is NULL" );
      } else {
	ret.append( "entryNumber = :arg"+(iArgNum++) );
      }
    }

              
    
  
        
    if( isDocNumberSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getDocNumber()==null ){
	ret.append( "docNumber is NULL" );
      } else {
	ret.append( "docNumber = :arg"+(iArgNum++) );
      }
    }

              
    
  
        
    if( isObservationsSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getObservations()==null ){
	ret.append( "observations is NULL" );
      } else {
	ret.append( "observations = :arg"+(iArgNum++) );
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

                  
    
  
        
    if( isAccountSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getAccount()==null ){
	ret.append( "account is NULL" );
      } else {
	ret.append( "account = :arg"+(iArgNum++) );
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

                      
    
      if( isBillsSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      ret.append( "bills.id IN (:arg"+(iArgNum++)+")" );
    }

          customGetHQL(ret,iArgNum);
    return ret.toString();
  }

  
	
	private void customGetHQL(StringBuilder ret, int iArgNum)
	{
		if( isYearSet() )
		{
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			ret.append( "year(date)=:arg"+(iArgNum++));
		}
		
		if( isBillSet() )
		{
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			ret.append( "id NOT IN(SELECT ent.id from Bill as bill join bill.entries ent where bill.id = :arg"+(iArgNum++)+")" );
		}// select b.baz from Foo f join f.bars b

		
		if( isHideInitialEntrySet() && hideInitialEntry )
		{
			
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			ret.append( "type NOT IN(SELECT id from AccountEntryType as type where type.group.id = :arg"+(iArgNum++)+")");
		}
	}
  
  @Override
  public Object[] getArguments(){
    ArrayList<Object> ret = new ArrayList<Object>();
            
  
      if( isStartDateSet() ){
        ret.add( startDate );
    }
    if( isEndDateSet() ){
        ret.add( endDate );
    }

              
  
      if( isStartAmountDateSet() ){
        ret.add( startAmountDate );
    }
    if( isEndAmountDateSet() ){
        ret.add( endAmountDate );
    }

              
  
      if( isConceptSet() && getConcept()!=null ){
        ret.add( concept );
    }

              
  
      if( isStartAmountSet() ){
        ret.add( startAmount );
    }
    if( isEndAmountSet() ){
        ret.add( endAmount );
    }

              
  
      if( isEntryNumberSet() && getEntryNumber()!=null ){
        ret.add( entryNumber );
    }

              
  
      if( isDocNumberSet() && getDocNumber()!=null ){
        ret.add( docNumber );
    }

              
  
      if( isObservationsSet() && getObservations()!=null ){
        ret.add( observations );
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

                  
  
      if( isAccountSet() && getAccount()!=null ){
        ret.add( account );
    }

              
  
      if( isTypeSet() && getType()!=null ){
        ret.add( type );
    }

                      
  
            
    if( isBillsSet() ){
      List<Integer> params = new ArrayList<Integer>();
      for( ITransferObject to : bills )
      {
        params.add( to.getId() );
      }
      ret.add( params );
    }

          customGetArguments(ret);
    return ret.toArray();
  }

  @Override
  public void reset(){
            
  
      unsetStartDate();
    unsetEndDate();

              
  
      unsetStartAmountDate();
    unsetEndAmountDate();

              
  
      unsetConcept();
  
              
  
      unsetStartAmount();
    unsetEndAmount();

              
  
      unsetEntryNumber();
  
              
  
      unsetDocNumber();
  
              
  
      unsetObservations();
  
              
  
      unsetOwnerId();
  
              
  
      unsetDepartmentId();
  
              
  
      unsetStartInsertDate();
    unsetEndInsertDate();

              
  
      unsetStartUpdateDate();
    unsetEndUpdateDate();

                  
  
      unsetAccount();
  
              
  
      unsetType();
  
                      
  
      unsetBills();
  
          customReset();
  }
    
  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    ret.append("AccountEntrySearch{");
            
  
  
    if( isStartDateSet() ){
        ret.append( "(startDate" );
        ret.append( "="+startDate );
        ret.append( ")" );
    }
    if( isEndDateSet() ){
        ret.append( "(endDate" );
        ret.append( "="+endDate );
        ret.append( ")" );
    }

              
  
  
    if( isStartAmountDateSet() ){
        ret.append( "(startAmountDate" );
        ret.append( "="+startAmountDate );
        ret.append( ")" );
    }
    if( isEndAmountDateSet() ){
        ret.append( "(endAmountDate" );
        ret.append( "="+endAmountDate );
        ret.append( ")" );
    }

              
  
  
          if( isConceptSet() ){
          ret.append( "(concept" );
          ret.append( "="+concept );
          ret.append( ")" );
      }

    
              
  
  
    if( isStartAmountSet() ){
        ret.append( "(startAmount" );
        ret.append( "="+startAmount );
        ret.append( ")" );
    }
    if( isEndAmountSet() ){
        ret.append( "(endAmount" );
        ret.append( "="+endAmount );
        ret.append( ")" );
    }

              
  
  
          if( isEntryNumberSet() ){
          ret.append( "(entryNumber" );
          ret.append( "="+entryNumber );
          ret.append( ")" );
      }

    
              
  
  
          if( isDocNumberSet() ){
          ret.append( "(docNumber" );
          ret.append( "="+docNumber );
          ret.append( ")" );
      }

    
              
  
  
          if( isObservationsSet() ){
          ret.append( "(observations" );
          ret.append( "="+observations );
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

                  
  
  
          if( isAccountSet() ){
          ret.append( "(account" );
          ret.append( "="+account );
          ret.append( ")" );
      }

    
              
  
  
          if( isTypeSet() ){
          ret.append( "(type" );
          ret.append( "="+type );
          ret.append( ")" );
      }

    
                      
  
  
          if( isBillsSet() ){
          ret.append( "(bills={" );
          for( Object o : bills.toArray() ){
            ret.append( ","+o );
          }
          ret.append( "})" );
      }

    
          customToString(ret);
    ret.append("}");
    return ret.toString();
  }

  // Getters and setters
        
  
  
    public boolean isStartDateSet(){
        return startDateSet;
    }
    public Date getStartDate(){
        return startDate;
    }
    public void setStartDate( Date startDate ){
        this.startDate = startDate;
        this.startDateSet = true;
    }
    public void unsetStartDate(){
        this.startDateSet = false;
    }
    public boolean isEndDateSet(){
        return endDateSet;
    }
    public Date getEndDate(){
        return endDate;
    }
    public void setEndDate( Date endDate ){
        this.endDate = endDate;
        this.endDateSet = true;
    }
    public void unsetEndDate(){
        this.endDateSet = false;
    }

          
  
  
    public boolean isStartAmountDateSet(){
        return startAmountDateSet;
    }
    public Date getStartAmountDate(){
        return startAmountDate;
    }
    public void setStartAmountDate( Date startAmountDate ){
        this.startAmountDate = startAmountDate;
        this.startAmountDateSet = true;
    }
    public void unsetStartAmountDate(){
        this.startAmountDateSet = false;
    }
    public boolean isEndAmountDateSet(){
        return endAmountDateSet;
    }
    public Date getEndAmountDate(){
        return endAmountDate;
    }
    public void setEndAmountDate( Date endAmountDate ){
        this.endAmountDate = endAmountDate;
        this.endAmountDateSet = true;
    }
    public void unsetEndAmountDate(){
        this.endAmountDateSet = false;
    }

          
  
  
    
    
    public boolean isConceptSet(){
        return conceptSet;
    }
    public String getConcept(){
        return concept;
    }
    public void setConcept( String concept ){
        this.concept = concept;
        this.conceptSet = true;
    }
    public void unsetConcept(){
        this.conceptSet = false;
    }
          
  
  
    public boolean isStartAmountSet(){
        return startAmountSet;
    }
    public BigDecimal getStartAmount(){
        return startAmount;
    }
    public void setStartAmount( BigDecimal startAmount ){
        this.startAmount = startAmount;
        this.startAmountSet = true;
    }
    public void unsetStartAmount(){
        this.startAmountSet = false;
    }
    public boolean isEndAmountSet(){
        return endAmountSet;
    }
    public BigDecimal getEndAmount(){
        return endAmount;
    }
    public void setEndAmount( BigDecimal endAmount ){
        this.endAmount = endAmount;
        this.endAmountSet = true;
    }
    public void unsetEndAmount(){
        this.endAmountSet = false;
    }

          
  
  
    
    
    public boolean isEntryNumberSet(){
        return entryNumberSet;
    }
    public String getEntryNumber(){
        return entryNumber;
    }
    public void setEntryNumber( String entryNumber ){
        this.entryNumber = entryNumber;
        this.entryNumberSet = true;
    }
    public void unsetEntryNumber(){
        this.entryNumberSet = false;
    }
          
  
  
    
    
    public boolean isDocNumberSet(){
        return docNumberSet;
    }
    public String getDocNumber(){
        return docNumber;
    }
    public void setDocNumber( String docNumber ){
        this.docNumber = docNumber;
        this.docNumberSet = true;
    }
    public void unsetDocNumber(){
        this.docNumberSet = false;
    }
          
  
  
    
    
    public boolean isObservationsSet(){
        return observationsSet;
    }
    public String getObservations(){
        return observations;
    }
    public void setObservations( String observations ){
        this.observations = observations;
        this.observationsSet = true;
    }
    public void unsetObservations(){
        this.observationsSet = false;
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

            
  
  
    
    
    public boolean isAccountSet(){
        return accountSet;
    }
    public Account getAccount(){
        return account;
    }
    public void setAccount( Account account ){
        this.account = account;
        this.accountSet = true;
    }
    public void unsetAccount(){
        this.accountSet = false;
    }
          
  
  
    
    
    public boolean isTypeSet(){
        return typeSet;
    }
    public AccountEntryType getType(){
        return type;
    }
    public void setType( AccountEntryType type ){
        this.type = type;
        this.typeSet = true;
    }
    public void unsetType(){
        this.typeSet = false;
    }
              
  
  
    	    	        
    
    public boolean isBillsSet(){
        return billsSet;
    }
    public List<Bill> getBills(){
        return bills;
    }
    public void setBills( List<Bill> bills ){
        this.bills = bills;
        this.billsSet = true;
    }
    public void unsetBills(){
        this.billsSet = false;
    }
    
  // Fields
        
  
      private boolean startDateSet;
        private Date startDate;
    private boolean endDateSet;
        private Date endDate;

          
  
      private boolean startAmountDateSet;
        private Date startAmountDate;
    private boolean endAmountDateSet;
        private Date endAmountDate;

          
  
      private boolean conceptSet;
        private String concept;

          
  
      private boolean startAmountSet;
        private BigDecimal startAmount;
    private boolean endAmountSet;
        private BigDecimal endAmount;

          
  
      private boolean entryNumberSet;
        private String entryNumber;

          
  
      private boolean docNumberSet;
        private String docNumber;

          
  
      private boolean observationsSet;
        private String observations;

          
  
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

            
  
      private boolean accountSet;
        private Account account;
  
          
  
      private boolean typeSet;
        private AccountEntryType type;
  
              
  
      private boolean billsSet;
            	private List<Bill> bills;
      
    
  // Returns if there are a search condition active
  public boolean isSearchActive() {
    return customIsSearchActive()||startDateSet||endDateSet||startAmountDateSet||endAmountDateSet||conceptSet||startAmountSet||endAmountSet||entryNumberSet||docNumberSet||observationsSet||ownerIdSet||departmentIdSet||startInsertDateSet||endInsertDateSet||startUpdateDateSet||endUpdateDateSet||accountSet||typeSet||billsSet;
  }


/* generated by stajanov (do not edit/delete) */
	
	private Bill bill = null;
	private boolean billSet = false;
	
	
	public Bill getBill()
	{
		return bill;
	}
	
	public void setBill(Bill bill)
	{
		this.bill = bill;
		this.billSet = true;
	}
	
	public boolean isBillSet()
	{
		return billSet;
	}
	
	public void unsetBill()
	{
		this.billSet = false;
	}
	
	private boolean hideInitialEntry = false;
	private boolean hideInitialEntrySet = false;
	
	public boolean isHideInitialEntry()
	{
		return hideInitialEntry;
	}
	
	public void setHideInitialEntry(boolean hideInitialEntry)
	{
		this.hideInitialEntry = hideInitialEntry;
		this.hideInitialEntrySet = true;
	}
	
	public boolean isHideInitialEntrySet()
	{
		return this.hideInitialEntrySet;
	}
	
	public void unsetHideInitialEntry()
	{
		this.hideInitialEntrySet = false;
	}
	
	private int year;
	private boolean yearSet = false;
	
	public int getYear()
	{
		return year;
	}
	
	public void setYear(int year)
	{
		this.year = year;
		this.yearSet = true;
	}
	
	public boolean isYearSet()
	{
		return yearSet;
	}
	
	public void unsetYear()
	{
		this.yearSet = false;
	}
	
	private void customGetArguments(ArrayList ret)
	{
		if( isYearSet() )
		{
			ret.add( year );
		}
		if( isBillSet() )
		{
			ret.add( bill.getId() );
		}
		if( isHideInitialEntrySet() )
		{
			if( hideInitialEntry )
			{
				ret.add( ConfigurationUtil.getDefault().getInitialEntryId() );
			}
		}
	}
	
	private void customReset()
	{
		unsetYear();
		unsetHideInitialEntry();
		unsetBill();
	}
	
	private void customToString(StringBuilder ret)
	{
		if( isYearSet() )
		{
			ret.append( "(year" );
			ret.append( "="+year );
			ret.append( ")" );
		}
		if( isHideInitialEntrySet() )
		{
			ret.append( "(hideInitialEntry" );
			ret.append( "="+hideInitialEntry );
			ret.append( ")" );
		}
		if( isBillSet() )
		{
			ret.append( "(bill" );
			ret.append( "="+bill );
			ret.append( ")" );
		}
	}
	
	private boolean customIsSearchActive()
	{
		return yearSet||billSet||hideInitialEntrySet;
	}
	
	
	
}

