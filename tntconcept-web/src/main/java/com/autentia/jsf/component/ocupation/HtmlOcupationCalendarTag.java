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

package com.autentia.jsf.component.ocupation;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Vacation Calendar Tag
 * 
 * @author ojss
 */
public class HtmlOcupationCalendarTag extends
		org.apache.myfaces.custom.calendar.HtmlInputCalendarTag {

	private static final Log	log						= LogFactory
																.getLog(HtmlOcupationCalendarTag.class);

	/** User bean */
	private String				ocupationModel			= null;

	/** Style class for week-end days */
	private String				weekEndCellClass		= null;

	/** working day hours */
	private String				workingDayHours			= null;
	
	/** extra days to show in component */
	private String 				extraDays				= null;
	
	/** style ti set extra days */
	private String 				extraDaysCellClass		= null;
	
	/** */
	private String 				vacancesCellClass		= null;
	
	/** */
	private String 				fullWorkCellClass		= null;
	
	/** */
	private String				partialWorkCellClass	= null;
	
	@Override
	public String getComponentType() {
		log.debug("getRendererType - return=\"" + HtmlOcupationCalendar.COMPONENT_TYPE + "\".");
		return HtmlOcupationCalendar.COMPONENT_TYPE;
	}

	@Override
	public String getRendererType() {
		log.debug("getRendererType - return=\"" + HtmlOcupationCalendar.DEFAULT_RENDERER_TYPE
				+ "\".");
		return HtmlOcupationCalendar.DEFAULT_RENDERER_TYPE;
	}

	@Override
	public void release() {
		super.release();
		weekEndCellClass = null;
		workingDayHours = null;
		ocupationModel = null;
	}

	@Override
	protected void setProperties(UIComponent component) {
		super.setProperties(component);

		setStringProperty(component, "weekEndCellClass", 	weekEndCellClass);
		setStringProperty(component, "workingDayHours", 	workingDayHours);
		setStringProperty(component, "extraDays", 			extraDays);
		setStringProperty(component, "extraDaysCellClass", 	extraDaysCellClass);
		setStringProperty(component, "vacancesCellClass", 	vacancesCellClass);
		setStringProperty(component, "fullWorkCellClass", 	fullWorkCellClass);
		setStringProperty(component, "partialWorkCellClass",partialWorkCellClass);

		if (ocupationModel != null) {
			if (isValueReference(ocupationModel)) {
				final Application application = FacesContext.getCurrentInstance().getApplication();
				final HtmlOcupationCalendar inputCalendarComponent = (HtmlOcupationCalendar) component;
				inputCalendarComponent.setValueBinding("ocupationModel", application
						.createValueBinding(ocupationModel));

			}
		}

	}

	/**
	 * sets style class
	 * 
	 * @param weekEndCellClass
	 *            class to set
	 */
	public void setWeekEndCellClass(String weekEndCellClass) {

		this.weekEndCellClass = weekEndCellClass;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setOcupationModel(String ocupationModel) {

		this.ocupationModel = ocupationModel;
	}
	
	/**
	 * @param user
	 *            the user to set
	 */
	public void setWorkingDayHours(String workingDayHours) {

		this.workingDayHours = workingDayHours;
	}

	public void setExtraDays(String extraDays) {
		this.extraDays = extraDays;
	}

	public void setExtraDaysCellClass(String extraDaysCellClass) {
		this.extraDaysCellClass = extraDaysCellClass;
	}

	public void setVacancesCellClass(String vacancesCellClass) {
		this.vacancesCellClass = vacancesCellClass;
	}

	public void setFullWorkCellClass(String fullWorkCellClass) {
		this.fullWorkCellClass = fullWorkCellClass;
	}

	public void setPartialWorkCellClass(String partialWorkCellClass) {
		this.partialWorkCellClass = partialWorkCellClass;
	}
	
	

}
