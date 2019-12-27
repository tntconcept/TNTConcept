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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.schedule.HtmlSchedule;
import org.apache.myfaces.custom.schedule.model.DefaultScheduleEntry;
import org.apache.myfaces.custom.schedule.model.ScheduleDay;
import org.apache.myfaces.custom.schedule.model.ScheduleEntry;
import org.apache.myfaces.custom.schedule.renderer.AbstractScheduleRenderer;
import org.apache.myfaces.custom.schedule.util.ScheduleUtil;
import org.apache.myfaces.shared_tomahawk.renderkit.RendererUtils;
import org.apache.myfaces.shared_tomahawk.renderkit.html.HTML;
import org.apache.myfaces.shared_tomahawk.renderkit.html.util.FormInfo;

import com.autentia.tnt.bean.activity.ActivityScheduleEntry;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * Abstract superclass for the week and month view renderers.
 * </p>
 * 
 * @author Jurgen Lust (latest modification by $Author: jlust $)
 * @author Bruno Aranda (adaptation of Jurgen's code to myfaces)
 * @version $Revision: 398348 $
 */
public abstract class BitacoreAbstractCompactScheduleRenderer extends AbstractScheduleRenderer
		implements Serializable {
	private static final Log	log	= LogFactory
											.getLog(BitacoreAbstractCompactScheduleRenderer.class);

	// ~ Methods
	// ----------------------------------------------------------------

	/**
	 * @see javax.faces.render.Renderer#encodeChildren(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent)
	 */
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		// the children are rendered in the encodeBegin phase
	}

	/**
	 * @see javax.faces.render.Renderer#encodeEnd(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent)
	 */
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		// all rendering is done in the begin phase
	}

	/**
	 * @return The default height, in pixels, of one row in the schedule grid
	 */
	protected abstract int getDefaultRowHeight();

	/**
	 * @return The name of the property that determines the row height
	 */
	protected abstract String getRowHeightProperty();

	/**
	 * @param attributes
	 *            The attributes
	 * 
	 * @return The row height, in pixels
	 */
	protected int getRowHeight(Map attributes) {
		int rowHeight = 0;

		try {
			rowHeight = Integer.valueOf((String) attributes.get(getRowHeightProperty())).intValue();
		} catch (Exception e) {
			rowHeight = 0;
		}

		if (rowHeight == 0) {
			rowHeight = getDefaultRowHeight();
		}

		return rowHeight;
	}

	/**
	 * <p>
	 * Draw one day in the schedule
	 * </p>
	 * 
	 * @param context
	 *            the FacesContext
	 * @param writer
	 *            the ResponseWriter
	 * @param schedule
	 *            the schedule
	 * @param day
	 *            the day that should be drawn
	 * @param cellWidth
	 *            the width of the cell
	 * @param dayOfWeek
	 *            the day of the week
	 * @param dayOfMonth
	 *            the day of the month
	 * @param isWeekend
	 *            is it a weekend day?
	 * @param isCurrentMonth
	 *            is the day in the currently selected month?
	 * @param rowspan
	 *            the rowspan for the table cell
	 * 
	 * @throws IOException
	 *             when the cell could not be drawn
	 */
	protected void writeDayCell(FacesContext context, ResponseWriter writer, HtmlSchedule schedule,
			ScheduleDay day, float cellWidth, int dayOfWeek, int dayOfMonth, boolean isWeekend,
			boolean isCurrentMonth, int rowspan) throws IOException {
		final String clientId = schedule.getClientId(context);
		final Map attributes = schedule.getAttributes();
		final FormInfo parentFormInfo = RendererUtils.findNestingForm(schedule, context);
		final String formId = parentFormInfo == null ? null : parentFormInfo.getFormName();
		final String dayHeaderId = clientId + "_header_" + ScheduleUtil.getDateId(day.getDate());
		final String dayBodyId = clientId + "_body_" + ScheduleUtil.getDateId(day.getDate());
		

		writer.startElement(HTML.TD_ELEM, schedule);

		writer.writeAttribute("rowspan", String.valueOf(rowspan), null);

		String dayClass = getStyleClass(schedule, isCurrentMonth ? "day" : "inactive-day") + " "
				+ getStyleClass(schedule, isWeekend ? "weekend" : "workday");

		writer.writeAttribute(HTML.CLASS_ATTR, dayClass, null);
		
		// determine the height of the day in pixels
		StringBuffer styleBuffer = new StringBuffer();
		
		int rowHeight = getRowHeight(attributes);
		String myRowHeight = "height: ";
		String myContentHeight = "height: ";

		if (rowHeight > 0) {
			if (isWeekend) {
				myRowHeight += (rowHeight / 2) + "px;";
				myContentHeight += ((rowHeight / 2) - 19) + "px;";
			} else {
				myRowHeight += (rowHeight + 1) + "px;"; // need to add 1 to get
				// the weekends right
				myContentHeight += ((rowHeight + 1) - 18) + "px;"; // 18
				// instead
				// of 19, to
				// get the
				// weekends
				// right
			}
		} else {
			myRowHeight += "100%;";
			myContentHeight += "100%;";
		}

		styleBuffer.append(myRowHeight);

		writer.writeAttribute(HTML.STYLE_ATTR, styleBuffer.toString() + " width: " + cellWidth
				+ "%;", null);

		writer.startElement(HTML.TABLE_ELEM, schedule);

		writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule, "day"), null);
		writer.writeAttribute(HTML.STYLE_ATTR, styleBuffer.toString() + " width: 100%;", null);

		writer.writeAttribute(HTML.CELLPADDING_ATTR, "0", null);
		writer.writeAttribute(HTML.CELLSPACING_ATTR, "0", null);

		// added by German
		final Iterator<DefaultScheduleEntry> it = day.iterator();
		float dayDuration = 0;

		while (it.hasNext()) {
			ScheduleEntry entry = (ScheduleEntry) it.next();
			Date start = entry.getStartTime();
			Date end = entry.getEndTime();

			dayDuration += ((float) (end.getTime() - start.getTime()) / (60 * 60 * 1000));
		}
		// end added by German
		
		// day header
		writer.startElement(HTML.TR_ELEM, schedule);
		writer.startElement(HTML.TD_ELEM, schedule);
		
		writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule, "header"), null);
		
		String headerStyle = "height: 18px; width: 100%; overflow: hidden;";

		//coloreamos los dias sin rellenar anteriores al actual
		if ((day.isWorkingDay() && day.getDate().before(new Date()) && (dayDuration <= 0))) {
			headerStyle += "background-color: #FCA673;";
		} 
		
		if((!day.isWorkingDay())) {
			headerStyle += "background-color: #8097FD;";
			
		}
		
		writer.writeAttribute(HTML.STYLE_ATTR, headerStyle, null);
		writer.writeAttribute(HTML.ID_ATTR, dayHeaderId, null);
		// register an onclick event listener to a day header which will capture
		// the date
		if (!schedule.isReadonly() && schedule.isSubmitOnClick()) {
			writer.writeAttribute(HTML.ONMOUSEUP_ATTR, "fireScheduleDateClicked(this, event, '"
					+ formId + "', '" + clientId + "');", null);
		}

		writer.writeText(getDateString(context, schedule, day.getDate()), null);

		// added by German
		if (dayDuration > 0) {
			writer.writeText(" [" + dayDuration + "h.]", null);
		}
		
		if(day.getSpecialDayName()!=null &&!"".equals(day.getSpecialDayName())) {
			writer.writeText(" " + day.getSpecialDayName(), null);
		}
			
		// end added by German

		writer.endElement(HTML.TD_ELEM);
		writer.endElement(HTML.TR_ELEM);

		// day content
		writer.startElement(HTML.TR_ELEM, schedule);
		writer.startElement(HTML.TD_ELEM, schedule);

		writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule, "content"), null);

		// determine the height of the day content in pixels
		StringBuffer contentStyleBuffer = new StringBuffer();
		contentStyleBuffer.append(myContentHeight);
		contentStyleBuffer.append(" width: 100%;");
		writer.writeAttribute(HTML.STYLE_ATTR, contentStyleBuffer.toString(), null);

		writer.startElement(HTML.DIV_ELEM, schedule);
		writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule, "contentview"), null);
		writer.writeAttribute(HTML.STYLE_ATTR,
				"width: 100%; height: 100%; overflow: auto; vertical-align: top;", null);

		// this extra div is required, because when a scrollbar is visible and
		// it is clicked, the fireScheduleTimeClicked() method is fired.
		writer.startElement(HTML.DIV_ELEM, schedule);
		writer.writeAttribute(HTML.STYLE_ATTR, "width: 100%; height: 100%; vertical-align: top;",
				null);

		writer.writeAttribute(HTML.ID_ATTR, dayBodyId, null);

		// register an onclick event listener to a day cell which will capture
		// the date
		if (!schedule.isReadonly() && schedule.isSubmitOnClick()) {
			writer.writeAttribute(HTML.ONMOUSEUP_ATTR, "fireScheduleTimeClicked(this, event, '"
					+ formId + "', '" + clientId + "');", null);
		}

		writer.startElement(HTML.TABLE_ELEM, schedule);
		writer.writeAttribute(HTML.STYLE_ATTR, "width: 100%;", null);

		writeEntries(context, schedule, day, writer);

		writer.endElement(HTML.TABLE_ELEM);
		writer.endElement(HTML.DIV_ELEM);
		writer.endElement(HTML.DIV_ELEM);
		writer.endElement(HTML.TD_ELEM);
		writer.endElement(HTML.TR_ELEM);
		writer.endElement(HTML.TABLE_ELEM);
		writer.endElement(HTML.TD_ELEM);
	}

	/**
	 * <p>
	 * Draw the schedule entries in the specified day cell
	 * </p>
	 * 
	 * @param context
	 *            the FacesContext
	 * @param schedule
	 *            the schedule
	 * @param day
	 *            the day
	 * @param writer
	 *            the ResponseWriter
	 * 
	 * @throws IOException
	 *             when the entries could not be drawn
	 */
	protected void writeEntries(FacesContext context, HtmlSchedule schedule, ScheduleDay day,
			ResponseWriter writer) throws IOException {
		final String clientId = schedule.getClientId(context);
		final FormInfo parentFormInfo = RendererUtils.findNestingForm(schedule, context);
		final String formId = parentFormInfo == null ? null : parentFormInfo.getFormName();
		final TreeSet entrySet = new TreeSet(comparator);

		for (Iterator entryIterator = day.iterator(); entryIterator.hasNext();) {
			ScheduleEntry entry = (ScheduleEntry) entryIterator.next();
			entrySet.add(entry);
		}

		for (Iterator entryIterator = entrySet.iterator(); entryIterator.hasNext();) {
			ScheduleEntry entry = (ScheduleEntry) entryIterator.next();
			writer.startElement(HTML.TR_ELEM, schedule);
			writer.startElement(HTML.TD_ELEM, schedule);

			if (isSelected(schedule, entry)) {
				writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule, "selected"), null);
			}

			// compose the CSS style for the entry box
			StringBuffer entryStyle = new StringBuffer();
			entryStyle.append("width: 100%;");
			String entryColor = getEntryRenderer(schedule).getColor(context, schedule, entry,
					isSelected(schedule, entry));
			if (isSelected(schedule, entry) && entryColor != null) {
				entryStyle.append(" background-color: ");
				entryStyle.append(entryColor);
				entryStyle.append(";");
				entryStyle.append(" border-color: ");
				entryStyle.append(entryColor);
				entryStyle.append(";");
			}

			writer.writeAttribute(HTML.STYLE_ATTR, entryStyle.toString(), null);

			// draw the tooltip
			if (showTooltip(schedule)) {
				getEntryRenderer(schedule).renderToolTip(context, writer, schedule, entry,
						isSelected(schedule, entry));
			}

			if (!isSelected(schedule, entry) && !schedule.isReadonly()) {
				writer.startElement("a", schedule);
				writer.writeAttribute("href", "#", null);

				writer.writeAttribute(HTML.ONMOUSEUP_ATTR, "fireEntrySelected('" + formId + "', '"
						+ clientId + "', '" + entry.getId() + "');", null);
			}

			// draw the content
			getEntryRenderer(schedule).renderContent(context, writer, schedule, day, entry, true,
					isSelected(schedule, entry));

			if (!isSelected(schedule, entry) && !schedule.isReadonly()) {
				writer.endElement("a");
			}

			writer.endElement(HTML.TD_ELEM);
			writer.endElement(HTML.TR_ELEM);
		}
	}

	private boolean isSelected(HtmlSchedule schedule, ScheduleEntry entry) {
		ScheduleEntry selectedEntry = schedule.getModel().getSelectedEntry();

		if (selectedEntry == null) {
			return false;
		}

		return selectedEntry.getId().equals(entry.getId());
	}

	/**
	 * In the compact renderer, we don't take the y coordinate of the mouse into
	 * account when determining the last clicked date.
	 */
	protected Date determineLastClickedDate(HtmlSchedule schedule, String dateId, String yPos) {
		Calendar cal = GregorianCalendar.getInstance();
		// the dateId is the schedule client id + "_" + yyyyMMdd
		String day = dateId.substring(dateId.lastIndexOf("_") + 1);
		Date date = ScheduleUtil.getDateFromId(day);

		if (date != null)
			cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, schedule.getVisibleStartHour());
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		log.debug("last clicked datetime: " + cal.getTime());
		return cal.getTime();
	}

}
// The End
