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

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.shared_tomahawk.util._ComponentUtils;

/**
 * Coupation Calendar Tag
 * 
 * @author german
 */
public class HtmlOcupationCalendar extends org.apache.myfaces.custom.calendar.HtmlInputCalendar {

	private static final Log	log						= LogFactory
																.getLog(HtmlOcupationCalendar.class);

	public static final String	COMPONENT_TYPE			= HtmlOcupationCalendar.class.getName();
	public static final String	DEFAULT_RENDERER_TYPE	= HtmlOcupationCalendarRenderer.class
																.getName();
	/** User bean binding */
	private OcupationModel		ocupation				= null;

	/** week-end days style class */
	private String				weekEndCellClass		= null;

	/** vacation days style class */
	private Integer				workingDayHours			= null;
	
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

	/** constructor */
	public HtmlOcupationCalendar() {

		if (log.isDebugEnabled()) {
			log.debug("HtmlInputCalendar - setRendererType=\"" + DEFAULT_RENDERER_TYPE + "\".");
		}
		setRendererType(DEFAULT_RENDERER_TYPE);

	}

	@Override
	public String getFamily() {
		if (log.isDebugEnabled()) {
			log.debug("getFamily - return=\"" + COMPONENT_TYPE + "\".");
		}
		return COMPONENT_TYPE;
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
	 * gets style class
	 * 
	 * @return weekEndCellClass class to set
	 */
	public String getWeekEndCellClass() {
		if (weekEndCellClass != null)
			return weekEndCellClass;
		ValueBinding vb = getValueBinding("weekEndCellClass");
		return vb != null ? _ComponentUtils.getStringValue(getFacesContext(), vb) : null;
	}

	/**
	 * sets working day hours
	 * 
	 * @param workingDayHours
	 *            hours to set
	 */
	public void setWorkingDayHours(String workingDayHours) {

		this.workingDayHours = Integer.valueOf(workingDayHours);
	}

	/**
	 * gets style class
	 * 
	 * @return vacationDayCellClass class to set
	 */
	public String getWorkingDayHours() {
		
		if (workingDayHours != null)
			return workingDayHours.toString();
		ValueBinding vb = getValueBinding("workingDayHours");
		return vb != null ? _ComponentUtils.getStringValue(getFacesContext(), vb) : null;
	}

	/**
	 * @return the User bean
	 */
	public OcupationModel getOcupation() {
		if (log.isDebugEnabled()) {
			log.debug("component - getVacationModel bean=\"" + ocupation + "\"");
		}
		if (ocupation != null)
			return ocupation;
		ValueBinding vb = getValueBinding("ocupationModel");
		return vb != null ? (OcupationModel) vb.getValue(getFacesContext()) : null;
	}

	/**
	 * @param bean
	 *            the User bean to set
	 */
	public void setOcupation(OcupationModel ocupation) {
		if (log.isDebugEnabled()) {
			log.debug("component - setVacations bean=\"" + ocupation + "\"");
		}
		this.ocupation = ocupation;
	}

	
	
	public String getExtraDays() {
		if (extraDays != null)
			return extraDays;
		ValueBinding vb = getValueBinding("extraDays");
		return vb != null ? _ComponentUtils.getStringValue(getFacesContext(), vb) : null;
	}

	public void setExtraDays(String extraDays) {
		this.extraDays = extraDays;
	}

	public String getExtraDaysCellClass() {
		if (extraDaysCellClass != null)
			return extraDaysCellClass;
		ValueBinding vb = getValueBinding("extraDaysCellClass");
		return vb != null ? _ComponentUtils.getStringValue(getFacesContext(), vb) : null;
	}

	public void setExtraDaysCellClass(String extraDaysCellClass) {
		this.extraDaysCellClass = extraDaysCellClass;
	}

	public String getVacancesCellClass() {
		if (vacancesCellClass != null)
			return vacancesCellClass;
		ValueBinding vb = getValueBinding("vacancesCellClass");
		return vb != null ? _ComponentUtils.getStringValue(getFacesContext(), vb) : null;
	}

	public void setVacancesCellClass(String vacancesCellClass) {
		this.vacancesCellClass = vacancesCellClass;
	}

	public String getFullWorkCellClass() {
		if (fullWorkCellClass != null)
			return fullWorkCellClass;
		ValueBinding vb = getValueBinding("fullWorkCellClass");
		return vb != null ? _ComponentUtils.getStringValue(getFacesContext(), vb) : null;
	}

	public void setFullWorkCellClass(String fullWorkCellClass) {
		this.fullWorkCellClass = fullWorkCellClass;
	}

	public String getPartialWorkCellClass() {
		if (partialWorkCellClass != null)
			return partialWorkCellClass;
		ValueBinding vb = getValueBinding("partialWorkCellClass");
		return vb != null ? _ComponentUtils.getStringValue(getFacesContext(), vb) : null;
	}

	public void setPartialWorkCellClass(String partialWorkCellClass) {
		this.partialWorkCellClass = partialWorkCellClass;
	}

	public void setWorkingDayHours(Integer workingDayHours) {
		this.workingDayHours = workingDayHours;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.component.UIComponentBase#restoreState(javax.faces.context.FacesContext,
	 *      java.lang.Object)
	 */
	@Override
	public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;
		super.restoreState(context, values[0]);
		ocupation 				= (OcupationModel) values[1];
		weekEndCellClass 		= (String) values[2];
		workingDayHours 		= (Integer) values[3];
		extraDays				= (String) values[4];
		extraDaysCellClass		= (String) values[5];
		vacancesCellClass		= (String) values[6];
		fullWorkCellClass		= (String) values[7];
		partialWorkCellClass	= (String) values[8];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.component.UIComponentBase#saveState(javax.faces.context.FacesContext)
	 */
	@Override
	public Object saveState(FacesContext context) {
		Object values[] = new Object[26];
		values[0] = super.saveState(context);
		values[1] = ocupation;
		values[2] = weekEndCellClass;
		values[3] = workingDayHours;
		values[4] = extraDays;
		values[5] = extraDaysCellClass;
		values[6] = vacancesCellClass;
		values[7] = fullWorkCellClass;
		values[8] = partialWorkCellClass;
		return ((Object) (values));
	}

}
