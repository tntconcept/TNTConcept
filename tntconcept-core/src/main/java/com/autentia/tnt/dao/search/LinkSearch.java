package com.autentia.tnt.dao.search;

import java.util.ArrayList;
import java.util.Date;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SearchCriteria;

public class LinkSearch extends SearchCriteria {

	public String getHQL() {
		
		StringBuilder ret = new StringBuilder();
	    int iArgNum = 0;
	    
	    if( isIdSet() ){
	        ret.append( (ret.length()==0) ? "WHERE " : " AND " );
	        if( getId()==null ){
	  	ret.append( "id is NULL" );
	        } else {
	  	ret.append( "id = :arg"+(iArgNum++) );
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
	    
	    if( isLinkSet() ){
	        ret.append( (ret.length()==0) ? "WHERE " : " AND " );
	        if( getLink()==null ){
	  	ret.append( "link is NULL" );
	        } else {
	  	ret.append( "link = :arg"+(iArgNum++) );
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
	    
	    return ret.toString();
	}

	public Object[] getArguments() {
		
		ArrayList<Object> ret = new ArrayList<Object>();
        
		  
		if( isIdSet() && getId()!=null ){
	        ret.add( id );
	    }
		
		if( isUserSet() && getUser()!=null ){
	        ret.add( user );
	    }
		
		if( isLinkSet() && getLink()!=null ){
	        ret.add( link );
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
		return ret.toArray();
	}

	public void reset() {
		unsetId();
		unsetUser();
		unsetLink();
		unsetEndInsertDate();
		unsetEndUpdateDate();
		unsetStartInsertDate();
		unsetStartUpdateDate();
	}

	public String toString() {
		StringBuilder ret = new StringBuilder();
	    ret.append("LinkSearch{");
	    
	    if( isIdSet() ){
	        ret.append( "(id" );
	        ret.append( "="+id );
	        ret.append( ")" );
	    }
	    
	    if( isUserSet() ){
	        ret.append( "(user" );
	        ret.append( "="+user );
	        ret.append( ")" );
	    }
	    if( isLinkSet() ){
	        ret.append( "(link" );
	        ret.append( "="+link );
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

	    
	    ret.append("}");
	    return ret.toString();
	}
	
	// Fields
    
	  
	private Integer id;
	private boolean idSet;
	private String user;
	private boolean userSet;
	private String link;
	private boolean linkSet;
	
	private boolean startInsertDateSet;
    private Date startInsertDate;
    private boolean endInsertDateSet;
    private Date endInsertDate;

    private boolean startUpdateDateSet;
    private Date startUpdateDate;
    private boolean endUpdateDateSet;
    private Date endUpdateDate;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.idSet = true;
	}

	public boolean isIdSet() {
		return idSet;
	}

	public void unsetId() {
		this.idSet = false;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
		this.userSet = true;
	}

	public boolean isUserSet() {
		return userSet;
	}

	public void unsetUser() {
		this.userSet = false;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
		this.linkSet = true;
	}

	public boolean isLinkSet() {
		return linkSet;
	}

	public void unsetLink() {
		this.linkSet = false;
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
	
	
	// Returns if there are a search condition active
	  public boolean isSearchActive() {
	    return startInsertDateSet||endInsertDateSet||startUpdateDateSet||endUpdateDateSet||idSet||userSet||linkSet;
	  }  
      

}
