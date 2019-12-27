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

package com.autentia.tnt.bean.holiday;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;


import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.businessobject.UserHolidaysState;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.UserSearch;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.holiday.UserHolidaysStateManager;
import com.autentia.tnt.manager.security.AuthenticationManager;

public class UserHolidaysStateBean extends BaseBean {

	
	/** Default sort column */
	  private String sortColumn = "name";
	    
	  /** Default sort order */
	  private boolean sortAscending = false;
	
	  private Date chargeYear = null;

      private static UserHolidaysStateManager		manager			= UserHolidaysStateManager.getDefault();

	public UserHolidaysStateBean() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MONTH, cal.getMinimum(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		
		chargeYear = cal.getTime();
	}
	
	public void changeDate(ValueChangeEvent event) {
		Date d = (Date) event.getNewValue();
		setChargeYear(d);
		setUserState(manager.calculateUserHolidaysState(AuthenticationManager.getDefault().getCurrentPrincipal().getUser(), this.getChargeYear()));
	}
	
	UserHolidaysState userState = null;
	
	public List<UserHolidaysState> getAll(){
	    
		List<UserHolidaysState> ret = new ArrayList<UserHolidaysState>();
		
		if(chargeYear==null)
			return ret;
		
				 
		 
		UserManager userManager = UserManager.getDefault();
		UserSearch searchUser = new UserSearch();		
			searchUser.setActive(true);
			
		List <User> users = userManager.getAllEntities(searchUser, new SortCriteria(sortColumn,sortAscending));					
		
		for(User us:users) {			
			ret.add(manager.calculateUserHolidaysState(us, this.getChargeYear()));
		}
				
		return ret;
		      
	}
	
	

	
	
	public String myHolidays() {
		setUserState(manager.calculateUserHolidaysState(AuthenticationManager.getDefault().getCurrentPrincipal().getUser(), this.getChargeYear()));
		return "myHolidays";
	}
	

	public Date getChargeYear() {
		return chargeYear;
	}

	public void setChargeYear(Date chargeYear) {
		this.chargeYear = chargeYear;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public boolean isSortAscending() {
		return sortAscending;
	}

	public void setSortAscending(boolean sortAscending) {
		this.sortAscending = sortAscending;
	}

	public UserHolidaysState getUserState() {
		return userState;
	}

	public void setUserState(UserHolidaysState userHolidaysState) {
		this.userState = userHolidaysState;
	}
	
	
	
	
}
