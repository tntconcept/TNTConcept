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

import java.util.Calendar;
import java.util.Date;

import org.apache.myfaces.custom.schedule.model.ScheduleEntry;

import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.util.FacesUtils;

public class ActivityScheduleEntry implements ScheduleEntry {

	private final Activity activity;

	private final Calendar cal = Calendar.getInstance();

	public ActivityScheduleEntry(Activity activity) {
		this.activity = activity;
	}

	public String getDescription() {
		return activity.getDescription();
	}

	public Date getEndTime() {

		cal.setTime(getStartTime());
		cal.add(Calendar.MINUTE, activity.getDuration());

		return cal.getTime();
	}

	public String getId() {
		return Activity.class.getSimpleName()+"_"+String.valueOf(activity.getId());
	}

	public Date getStartTime() {
		return activity.getStartDate();
	}

	public String getSubtitle() {
		return getDuration() + "h." + (activity.isBillable() ? FacesUtils.formatMessage("activity.billable") : ""); 
	}

	public String getTitle() {
		return activity.getDescription();
	}

	public boolean isAllDay() {
		return false;
	}
	
	// other methods not implemented by interface ScheduleEntry

	public String getDuration() {
		
		return String.valueOf(Float.valueOf(activity.getDuration()) / 60);
	}

	public String getDetailTitle() {
		return activity.getRole().getProject().getClient().getName() + " - " + activity.getRole().getProject().getName() + " (" + activity.getRole().getName() + ")";
	}

	public String getDetailSubtitle() {
		return getSubtitle();
	}

	public String getDetailDescription() {
		return getDescription().replaceAll("\n", " ");
	}
	
	public boolean isBillable() {
		return activity.isBillable();
	}

	
}
