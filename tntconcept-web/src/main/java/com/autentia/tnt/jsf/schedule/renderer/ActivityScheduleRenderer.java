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
import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.schedule.HtmlSchedule;
import org.apache.myfaces.custom.schedule.model.ScheduleModel;

public class ActivityScheduleRenderer extends Renderer implements Serializable {
	private static final long					serialVersionUID	= 1L;
	private static final Log 					log 				= LogFactory.getLog(ActivityScheduleRenderer.class);

	// ~ Instance fields
	// --------------------------------------------------------

	private final BitacoreScheduleCompactMonthRenderer	monthDelegate		= new BitacoreScheduleCompactMonthRenderer();
	private final BitacoreScheduleCompactWeekRenderer	weekDelegate		= new BitacoreScheduleCompactWeekRenderer();
	private final BitacoreScheduleDetailedDayRenderer	dayDelegate			= new BitacoreScheduleDetailedDayRenderer();

	// ~ Methods
	// ----------------------------------------------------------------

	/**
	 * @see javax.faces.render.Renderer#decode(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent)
	 */
	public void decode(FacesContext context, UIComponent component) {
		getDelegateRenderer(component).decode(context, component);
	}

	/**
	 * @see javax.faces.render.Renderer#encodeBegin(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent)
	 */
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		getDelegateRenderer(component).encodeBegin(context, component);
	}

	/**
	 * @see javax.faces.render.Renderer#encodeChildren(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent)
	 */
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		getDelegateRenderer(component).encodeChildren(context, component);
	}

	/**
	 * @see javax.faces.render.Renderer#encodeEnd(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent)
	 */
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		getDelegateRenderer(component).encodeEnd(context, component);
	}

	protected Renderer getDelegateRenderer(UIComponent component) {
		HtmlSchedule schedule = (HtmlSchedule) component;

		if ((schedule == null) || (schedule.getModel() == null)) {
			return dayDelegate;
		}

		switch (schedule.getModel().getMode()) {
		case ScheduleModel.WEEK:
			return weekDelegate;

		case ScheduleModel.MONTH:
			return monthDelegate;

		default:
			return dayDelegate;
		}
		
	}
}
// The End
