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

package com.autentia.tnt.bean.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.businessobject.GlobalHourReport;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.ActivitySearch;
import com.autentia.tnt.dao.search.UserSearch;
import com.autentia.tnt.manager.activity.ActivityManager;
import com.autentia.tnt.manager.admin.ProjectManager;
import com.autentia.tnt.manager.admin.UserManager;

/**
 * UI bean for Activity objects.
 * 
 * @author stajanov code generator
 */
public class GlobalHoursReportBean extends BaseBean {
	
	/** Manager */
	private static ActivityManager				manager					= ActivityManager
																				.getDefault();
	/** Serial version field */
	private static final long					serialVersionUID		= 1L;
	

	/** Project DAO * */
	private static final ProjectManager			projectManager			= ProjectManager
																				.getDefault();


	private static UserManager		userManager				= UserManager
																				.getDefault();
	
	private List <User> usuarios = null;
		
	
	private Iterator<User> iterator = null;
	
	private boolean launchCSV = false;
	
	
	/**
	 * @return the launchCSV
	 */
	public boolean isLaunchCSV() {
		return launchCSV;
	}

	/**
	 * @param launchCSV the launchCSV to set
	 */
	public void setLaunchCSV(boolean launchCSV) {
		this.launchCSV = launchCSV;
	}

	/**
	 * Constructor
	 */
	public GlobalHoursReportBean() {
		
	}


	/** Logger */
	private static final Log			log					= LogFactory.getLog(GlobalHoursReportBean.class);


	
	/** Start date to show report */
	private Date								startDate			= new Date();
	
	/** End date to show report */
	private Date								endDate			= new Date();
	
	/** End date to show report */
	private Boolean								billable = new Boolean(false);
	
	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<GlobalHourReport> getAll() {
		
		
		// Retrieve activities for every User during that period of time				
		ActivitySearch search = new ActivitySearch();

		Calendar init = Calendar.getInstance();
			init.setTime(startDate);
		Calendar last = Calendar.getInstance();
			last.setTime(endDate);

			init.set(Calendar.HOUR_OF_DAY, init.getMinimum(Calendar.HOUR_OF_DAY));
			init.set(Calendar.MINUTE, init.getMinimum(Calendar.MINUTE));
			init.set(Calendar.SECOND, init.getMinimum(Calendar.SECOND));
			init.set(Calendar.MILLISECOND, init.getMinimum(Calendar.MILLISECOND));

			last.set(Calendar.HOUR_OF_DAY, last.getMaximum(Calendar.HOUR_OF_DAY));
			last.set(Calendar.MINUTE, last.getMaximum(Calendar.MINUTE));
			last.set(Calendar.SECOND, last.getMaximum(Calendar.SECOND));
			last.set(Calendar.MILLISECOND, last.getMaximum(Calendar.MILLISECOND));

			search.setStartStartDate(init.getTime());
			search.setEndStartDate(last.getTime());
		
	
			
		List <GlobalHourReport> listGlobal = new ArrayList<GlobalHourReport>();
			
		if(billable)
			search.setBillable(true);
		
		// Search activities during indicated dates
		List<Activity> activities = manager.getAllEntities(search, new SortCriteria("role.project.client.name"));
		
		
		// Search for projects in activities and create the global list.		
		
		for(Activity act : activities) {
			Project proj = act.getRole().getProject();
			GlobalHourReport unit = new GlobalHourReport();
			unit.setProject(proj);
			
			// an entry in the list represents a project
			if(!listGlobal.contains(unit))
				listGlobal.add(unit);
			
			// Retrieve the stored unit and save hours
			GlobalHourReport storedUnit = listGlobal.get(listGlobal.indexOf(unit));
			float horas = act.getDuration() / 60.0f;
			
			storedUnit.setUserHours(act.getUser(),horas);
			storedUnit.setIterator(usuarios.iterator());
			
			 
			
		}
					
			
		
		return listGlobal;
		
	}
	
	public String search() {
		return null;
	}
	
	
	public String getSize() {
				
		return ""+usuarios.size();
	}
	
	
	public List<User> getUsers() {	
		// Retrieve active users:
		UserSearch userSearch = new UserSearch();
		userSearch.setActive(true);
		usuarios = userManager.getAllEntities(userSearch, new SortCriteria("name"));
		return usuarios;		
		
	}

	public float getTotalHours() {
					
		ActivitySearch search = new ActivitySearch();

		Calendar init = Calendar.getInstance();
			init.setTime(startDate);
		Calendar last = Calendar.getInstance();
			last.setTime(endDate);

			init.set(Calendar.HOUR_OF_DAY, init.getMinimum(Calendar.HOUR_OF_DAY));
			init.set(Calendar.MINUTE, init.getMinimum(Calendar.MINUTE));
			init.set(Calendar.SECOND, init.getMinimum(Calendar.SECOND));
			init.set(Calendar.MILLISECOND, init.getMinimum(Calendar.MILLISECOND));

			last.set(Calendar.HOUR_OF_DAY, last.getMaximum(Calendar.HOUR_OF_DAY));
			last.set(Calendar.MINUTE, last.getMaximum(Calendar.MINUTE));
			last.set(Calendar.SECOND, last.getMaximum(Calendar.SECOND));
			last.set(Calendar.MILLISECOND, last.getMaximum(Calendar.MILLISECOND));

			search.setStartStartDate(init.getTime());
			search.setEndStartDate(last.getTime());
			
			if(billable)
				search.setBillable(true);
		// Search activities during indicated dates
		List<Activity> activities = manager.getAllEntities(search, new SortCriteria("role.project.client.name"));
		
		
		// Search for projects in activities and create the global list.		
		float totalHours = 0.0f;
		for(Activity act : activities) {
			
			float horas = act.getDuration() / 60.0f;			
			
			totalHours = totalHours + horas; 
			
		}
					
			
		
		return totalHours;
	}

	
	
	public float getNextUserTotal() {
		
		
		
		ActivitySearch search = new ActivitySearch();

		Calendar init = Calendar.getInstance();
			init.setTime(startDate);
		Calendar last = Calendar.getInstance();
			last.setTime(endDate);

			init.set(Calendar.HOUR_OF_DAY, init.getMinimum(Calendar.HOUR_OF_DAY));
			init.set(Calendar.MINUTE, init.getMinimum(Calendar.MINUTE));
			init.set(Calendar.SECOND, init.getMinimum(Calendar.SECOND));
			init.set(Calendar.MILLISECOND, init.getMinimum(Calendar.MILLISECOND));

			last.set(Calendar.HOUR_OF_DAY, last.getMaximum(Calendar.HOUR_OF_DAY));
			last.set(Calendar.MINUTE, last.getMaximum(Calendar.MINUTE));
			last.set(Calendar.SECOND, last.getMaximum(Calendar.SECOND));
			last.set(Calendar.MILLISECOND, last.getMaximum(Calendar.MILLISECOND));

			search.setStartStartDate(init.getTime());
			search.setEndStartDate(last.getTime());
			
			if(billable)
				search.setBillable(true);
		
			if(iterator==null)
				iterator = getUsers().iterator();
			User user = null;
			try {
				user = iterator.next();				
			} catch(NoSuchElementException e) {
				iterator = getUsers().iterator();
				user = iterator.next();
			}
			
			search.setUser(user);
		
		// Search activities during indicated dates
		List<Activity> activities = manager.getAllEntities(search, new SortCriteria("role.project.client.name"));
		
		
		// Search for projects in activities and create the global list.		
		float totalHours = 0.0f;
		for(Activity act : activities) {
			
			float horas = act.getDuration() / 60.0f;			
			
			totalHours = totalHours + horas; 
			
		}
					
			
		
		return totalHours;
	}

	public Boolean getBillable() {
		return billable;
	}

	public void setBillable(Boolean billable) {
		this.billable = billable;
	}
	
	public String exportCSV() {
		
		launchCSV = true;
		
		return null;
		
	}
	
	public String getParameters() {
		
		launchCSV = false;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		StringBuilder params = new StringBuilder();
		
		params.append("?");
		params.append("startDate=").append(sdf.format(startDate));
		params.append("&");
		params.append("endDate=").append(sdf.format(endDate));
		params.append("&");
		params.append("billable=").append(billable);
		
		return params.toString();
	}
	
}
