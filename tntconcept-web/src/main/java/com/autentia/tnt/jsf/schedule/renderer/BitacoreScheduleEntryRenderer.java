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

package com.autentia.tnt.jsf.schedule.renderer;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.apache.myfaces.custom.schedule.HtmlSchedule;
import org.apache.myfaces.custom.schedule.model.ScheduleDay;
import org.apache.myfaces.custom.schedule.model.ScheduleEntry;
import org.apache.myfaces.custom.schedule.renderer.ScheduleEntryRenderer;

import com.autentia.tnt.bean.activity.ActivityScheduleEntry;
import com.autentia.tnt.bean.activity.ExternalActivityScheduleEntry;

public class BitacoreScheduleEntryRenderer implements ScheduleEntryRenderer {

	private static ActivityScheduleEntryRenderer aseRenderer = new ActivityScheduleEntryRenderer();
	private static ExternalActivityScheduleEntryRenderer easeRenderer = new ExternalActivityScheduleEntryRenderer();
	
	public String getColor(FacesContext context, HtmlSchedule schedule,
			ScheduleEntry entry, boolean selected) {
		
		if (entry instanceof ActivityScheduleEntry) {
			return aseRenderer.getColor(context, schedule, entry, selected);
		} else if (entry instanceof ExternalActivityScheduleEntry) {
			return easeRenderer.getColor(context, schedule, entry, selected);
		}
		
		return null;
	}

	public void renderContent(FacesContext context, ResponseWriter writer,
			HtmlSchedule schedule, ScheduleDay day, ScheduleEntry entry,
			boolean compact, boolean selected) throws IOException {
		if (entry instanceof ActivityScheduleEntry) {
			aseRenderer.renderContent(context, writer, schedule, day, entry, compact, selected);
		} else if (entry instanceof ExternalActivityScheduleEntry) {
			easeRenderer.renderContent(context, writer, schedule, day, entry, compact, selected);
		}

	}

	public void renderToolTip(FacesContext context, ResponseWriter writer,
			HtmlSchedule schedule, ScheduleEntry entry, boolean selected)
			throws IOException {
		if (entry instanceof ActivityScheduleEntry) {
			aseRenderer.renderToolTip(context, writer, schedule, entry, selected);
		} else if (entry instanceof ExternalActivityScheduleEntry) {
			easeRenderer.renderToolTip(context, writer, schedule, entry, selected);
		}

	}

}
