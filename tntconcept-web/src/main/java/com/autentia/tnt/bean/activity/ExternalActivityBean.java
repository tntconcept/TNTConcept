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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.bean.NavigationResults;
import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.businessobject.DocumentCategory;
import com.autentia.tnt.businessobject.ExternalActivity;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.search.ExternalActivitySearch;
import com.autentia.tnt.manager.activity.ExternalActivityManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.util.SpringUtils;

public class ExternalActivityBean extends BaseBean {

	/** Serial version field */
	private static final long		serialVersionUID	= 1L;
	
	private ExternalActivity 		externalActivity;
	
	private ExternalActivityManager	manager				= ExternalActivityManager.getDefault();
	
	private ExternalActivitySearch  search				= new ExternalActivitySearch();
	
	private AuthenticationManager	authManager			= AuthenticationManager.getDefault();
	
	// local copies from ExternalActvity fields
	private Integer id;
	
	private String name;
	
	private String category;
	
	private String startDate;
	
	private String endDate;
	
	private String location;
	
	private String organizer;
	
	private String comments;
	
	private User owner;
	
	private Integer departmentId;
	
	private String startTime;
	
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	private String endTime;
	
	
	public ExternalActivityBean() {
		// Only show entries for current user
		search.setOwner(authManager.getCurrentPrincipal().getUser());
	}

	/**
	 * Whether or not create button is available for user
	 * 
	 * @return true if user can create objects of type Account
	 */
	public boolean isCreateAvailable() {
		return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(ExternalActivity.class));
	}
	
	public String create() {
		
		externalActivity = new ExternalActivity();
		
		return NavigationResults.CREATE;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the organizer
	 */
	public String getOrganizer() {
		return organizer;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param organizer the organizer to set
	 */
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	private Date dateFromDateAndTime(String date, String time) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Date ret = null;
		
		try {
			ret = sdf.parse(date+" "+time);
		} catch (ParseException e) {
		}
		
		return ret;
		
	}
	
}
