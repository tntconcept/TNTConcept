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

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.myfaces.custom.schedule.model.ScheduleEntry;

import com.autentia.tnt.businessobject.ExternalActivity;
import com.autentia.tnt.util.FacesUtils;

public class ExternalActivityScheduleEntry implements ScheduleEntry {

	ExternalActivity externalActivity;
	private final long MILLISECS_PER_HOUR = 60*60*1000;
	
	public ExternalActivityScheduleEntry(ExternalActivity externalActivity) {
		super();
		this.externalActivity = externalActivity;
	}

	public String getDescription() {
		return externalActivity.getComments();
	}

	public Date getEndTime() {
		return externalActivity.getEndDate();
	}

	public String getId() {
		return ExternalActivity.class.getSimpleName()+"_"+String.valueOf(externalActivity.getId());
	}

	public Date getStartTime() {
		return externalActivity.getStart();
	}

	public String getSubtitle() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(getDuration()).append("h (").append(FacesUtils.formatMessage("externalActivities.scheduleTitle")).append(")");
		
		return sb.toString();
	}

	public String getTitle() {
		return externalActivity.getName();
	}

	public boolean isAllDay() {
		return false;
	}
	
	private String getDuration() {
		
		GregorianCalendar startCal = new GregorianCalendar();
		GregorianCalendar endCal = new GregorianCalendar();
		
		startCal.setTime(getStartTime());
		endCal.setTime(getEndTime());
		
		long duration = (endCal.getTimeInMillis() - startCal.getTimeInMillis())/MILLISECS_PER_HOUR;
			
		return String.valueOf(duration);
		
	}

}
