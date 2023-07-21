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
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.ValueBinding;

import org.apache.commons.lang3.StringUtils;
import org.apache.myfaces.custom.schedule.HtmlSchedule;
import org.apache.myfaces.custom.schedule.model.ScheduleDay;
import org.apache.myfaces.custom.schedule.model.ScheduleEntry;
import org.apache.myfaces.custom.schedule.renderer.ScheduleEntryRenderer;
import org.apache.myfaces.shared_tomahawk.renderkit.html.HTML;

import com.autentia.tnt.bean.activity.ActivityScheduleEntry;
import com.autentia.tnt.bean.activity.ExternalActivityScheduleEntry;

/**
 * The default implementation of the ScheduleEntryRenderer
 * 
 * @author Jurgen Lust (latest modification by $Author$)
 * @version $Revision$
 */
public class ExternalActivityScheduleEntryRenderer implements ScheduleEntryRenderer, Serializable {
	private static final long	serialVersionUID	= 1L;

	/**
	 * @see org.apache.myfaces.custom.schedule.renderer.ScheduleEntryRenderer#renderContent(javax.faces.context.FacesContext,
	 *      javax.faces.context.ResponseWriter,
	 *      org.apache.myfaces.custom.schedule.HtmlSchedule,
	 *      org.apache.myfaces.custom.schedule.model.ScheduleDay,
	 *      org.apache.myfaces.custom.schedule.model.ScheduleEntry, boolean,
	 *      boolean)
	 */
	public void renderContent(FacesContext context, ResponseWriter writer, HtmlSchedule schedule,
			ScheduleDay day, ScheduleEntry entry, boolean compact, boolean selected)
			throws IOException {
		if (compact) {
			renderCompactContent(context, writer, schedule, day, entry, selected);
		} else {
			if (selected) {
				StringBuffer entryStyle = new StringBuffer();
				entryStyle.append("height: 100%; width: 100%;");
				// the left border of a selected entry should have the same
				// color as the entry border
				String entryColor = getColor(context, schedule, entry, selected);
				if (entryColor != null) {
					entryStyle.append("border-color: ");
					entryStyle.append(entryColor);
					entryStyle.append(";");
				}
				// draw the contents of the selected entry
				writer.startElement(HTML.DIV_ELEM, null);
				writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule, "text"), null);
				writer.writeAttribute(HTML.STYLE_ATTR, entryStyle.toString(), null);

				renderDetailedContentText(context, writer, schedule, day, entry, selected);

				writer.endElement(HTML.DIV_ELEM);
			} else {
				renderDetailedContentText(context, writer, schedule, day, entry, selected);
			}
		}

	}

	protected void renderCompactContent(FacesContext context, ResponseWriter writer,
			HtmlSchedule schedule, ScheduleDay day, ScheduleEntry entry, boolean selected)
			throws IOException {
		StringBuffer text = new StringBuffer();
		Date startTime = entry.getStartTime();

		if (day.getDayStart().after(entry.getStartTime())) {
			startTime = day.getDayStart();
		}

		Date endTime = entry.getEndTime();

		if (day.getDayEnd().before(entry.getEndTime())) {
			endTime = day.getDayEnd();
		}

		if (!entry.isAllDay()) {
			DateFormat format = DateFormat.getTimeInstance(DateFormat.SHORT);
			text.append(format.format(startTime));
			if (!startTime.equals(endTime)) {
				text.append("-");
				text.append(format.format(endTime));
			}
			text.append(": ");
		}
		text.append(StringUtils.abbreviate(entry.getTitle(), 20));

		writer.startElement(HTML.SPAN_ELEM, schedule);
		writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule, "externalActivity"), null);
		
		writer.writeText(text.toString(), null);
		
		writer.endElement(HTML.SPAN_ELEM);
		
	}

	protected void renderDetailedContentText(FacesContext context, ResponseWriter writer,
			HtmlSchedule schedule, ScheduleDay day, ScheduleEntry entry, boolean selected)
			throws IOException {
		// write the title of the entry
		if (entry.getTitle() != null) {
			writer.startElement(HTML.SPAN_ELEM, schedule);
			writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule, "title"), null);
			writer.writeText(entry.getTitle(), null);
			writer.endElement(HTML.SPAN_ELEM);
		}
		if (entry.getSubtitle() != null) {
			writer.startElement("br", schedule);
			writer.endElement("br");
			writer.startElement(HTML.SPAN_ELEM, schedule);
			writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule, "subtitle"), null);
			writer.writeText(entry.getSubtitle(), null);
			writer.endElement(HTML.SPAN_ELEM);
		}
	}

	/**
	 * @see org.apache.myfaces.custom.schedule.renderer.ScheduleEntryRenderer#getColor(javax.faces.context.FacesContext,
	 *      org.apache.myfaces.custom.schedule.HtmlSchedule,
	 *      org.apache.myfaces.custom.schedule.model.ScheduleEntry, boolean)
	 */
	public String getColor(FacesContext context, HtmlSchedule schedule, ScheduleEntry entry,
			boolean selected) {
		return null;
	}

	/**
	 * @see org.apache.myfaces.custom.schedule.renderer.ScheduleEntryRenderer#renderToolTip(javax.faces.context.FacesContext,
	 *      javax.faces.context.ResponseWriter,
	 *      org.apache.myfaces.custom.schedule.HtmlSchedule,
	 *      org.apache.myfaces.custom.schedule.model.ScheduleEntry, boolean)
	 */
	public void renderToolTip(FacesContext context, ResponseWriter writer, HtmlSchedule schedule,
			ScheduleEntry e, boolean selected) throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("return makeTrue(domTT_activate(this, event, 'caption', '");

		ExternalActivityScheduleEntry entry = (ExternalActivityScheduleEntry) e;
		
		if (entry.getTitle() != null) {
			buffer.append(escape(entry.getTitle()));
		}

		buffer.append("', 'content', '<i>");

		if (entry.getSubtitle() != null) {
			buffer.append(escape(entry.getSubtitle()));
		}

		buffer.append("</i>");

		if (entry.getDescription() != null) {
			buffer.append("<br/>");
			buffer.append(escape(entry.getDescription()));
		}

		buffer.append("', 'trail', true));");
		writer.writeAttribute("onmouseover", buffer.toString(), null);
	}

	private String escape(String text) {
		if (text == null) {
			return null;
		}

		return text.replaceAll("'", "\\\\\'").replaceAll("\n", "\\\\n").replaceAll("\r", "\\\\r");
	}

	/**
	 * <p>
	 * Allow the developer to specify custom CSS classnames for the schedule
	 * component.
	 * </p>
	 * 
	 * @param component
	 *            the component
	 * @param className
	 *            the default CSS classname
	 * @return the custom classname
	 */
	protected String getStyleClass(UIComponent component, String className) {
		// first check if the styleClass property is a value binding expression
		ValueBinding binding = component.getValueBinding(className);
		if (binding != null) {
			String value = (String) binding.getValue(FacesContext.getCurrentInstance());

			if (value != null) {
				return value;
			}
		}
		// it's not a value binding expression, so check for the string value
		// in the attributes
		Map attributes = component.getAttributes();
		String returnValue = (String) attributes.get(className);
		return returnValue == null ? className : returnValue;
	}

}