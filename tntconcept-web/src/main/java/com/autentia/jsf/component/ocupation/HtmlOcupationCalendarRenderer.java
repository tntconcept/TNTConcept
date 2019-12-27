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

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.shared_tomahawk.renderkit.JSFAttr;
import org.apache.myfaces.shared_tomahawk.renderkit.RendererUtils;
import org.apache.myfaces.shared_tomahawk.renderkit.html.HTML;
import org.apache.myfaces.shared_tomahawk.renderkit.html.HtmlRendererUtils;
import org.apache.myfaces.shared_tomahawk.util.MessageUtils;

/**
 * Calendar renderer. Render a Calendar in one row. Without popup mode.
 * 
 * @extends org.apache.myfaces.custom.calendar.HtmlCalendarRenderer
 */
public class HtmlOcupationCalendarRenderer extends
		org.apache.myfaces.custom.calendar.HtmlCalendarRenderer {

	private static final Log	log	= LogFactory.getLog(HtmlOcupationCalendarRenderer.class);

	@Override
	public void encodeEnd(FacesContext facesContext, UIComponent component) throws IOException {
		log.debug("encodeEnd - component=\"" + component + "\".");
		RendererUtils.checkParamValidity(facesContext, component, HtmlOcupationCalendar.class);

		HtmlOcupationCalendar inputCalendar = (HtmlOcupationCalendar) component;
		
		Locale currentLocale = facesContext.getViewRoot().getLocale();

		Date value = null;

		Calendar timeKeeper = Calendar.getInstance(currentLocale);
		timeKeeper.setTime(new Date());

		try {
			value = new CalendarDateTimeConverter().getAsDate(facesContext, inputCalendar);
			timeKeeper.setTime(value);
		} catch (IllegalArgumentException i) {
			log.warn("encodeEnd - IllegalArgumentException", i);
		}

		
		if (log.isDebugEnabled()) {
			log.debug("encodeEnd - timeKeeper=\"" + timeKeeper.getTime() + "\".");
		}

		DateFormatSymbols symbols = new DateFormatSymbols(currentLocale);

		String[] weekdays = mapShortWeekdays(symbols);

		int lastDayInMonth = timeKeeper.getActualMaximum(Calendar.DAY_OF_MONTH);

		int currentDay = timeKeeper.get(Calendar.DAY_OF_MONTH);

		if (currentDay > lastDayInMonth)
			currentDay = lastDayInMonth;

		timeKeeper.set(Calendar.DAY_OF_MONTH, 1);

		int weekDayOfFirstDayOfMonth = mapCalendarDayToCommonDay(timeKeeper
				.get(Calendar.DAY_OF_WEEK));

		int weekStartsAtDayIndex = mapCalendarDayToCommonDay(timeKeeper.getFirstDayOfWeek());

		ResponseWriter writer = facesContext.getResponseWriter();

		HtmlRendererUtils.writePrettyLineSeparator(facesContext);
		HtmlRendererUtils.writePrettyLineSeparator(facesContext);

		// render table
		writer.startElement(HTML.TABLE_ELEM, component);

		HtmlRendererUtils.renderHTMLAttributes(writer, component, HTML.UNIVERSAL_ATTRIBUTES);
		HtmlRendererUtils.renderHTMLAttributes(writer, component, HTML.EVENT_HANDLER_ATTRIBUTES);
		writer.flush();

		HtmlRendererUtils.writePrettyLineSeparator(facesContext);

		// render days
		writeDays(facesContext, writer, inputCalendar, timeKeeper, currentDay,
				weekStartsAtDayIndex, weekDayOfFirstDayOfMonth, lastDayInMonth, weekdays);

		writer.endElement(HTML.TABLE_ELEM);
	}

	private void writeDays(FacesContext facesContext, ResponseWriter writer,
			HtmlOcupationCalendar inputComponent, Calendar timeKeeper, int currentDay,
			int weekStartsAtDayIndex, int weekDayOfFirstDayOfMonth, int lastDayInMonth,
			String[] weekdays) throws IOException {
		
		if (log.isDebugEnabled()) {
			log.debug("writeDays - currentDay=\"" + currentDay + "\", weekDayOfFirstDayOfMonth=\""
					+ weekDayOfFirstDayOfMonth + "\", lastDayInMonth=\"" + lastDayInMonth
					+ "\", weekdays=\"" + Arrays.toString(weekdays) + "\".");
		}

		OcupationModel ocupation = inputComponent.getOcupation();
		
		Calendar cal;

		int space = (weekStartsAtDayIndex < weekDayOfFirstDayOfMonth) ? (weekDayOfFirstDayOfMonth - weekStartsAtDayIndex)
				: (weekdays.length - weekStartsAtDayIndex + weekDayOfFirstDayOfMonth);

		if (space == weekdays.length)
			space = 0;

		int extraDays = 0;
		
		try {
			extraDays = Integer.valueOf(inputComponent.getExtraDays()).intValue();
		} catch (NumberFormatException e) {
			// nothing to do
		}

		for (int i = 0-extraDays; i < lastDayInMonth+extraDays; i++) {

			cal = copyCalendar(facesContext, timeKeeper);
			cal.set(Calendar.DAY_OF_MONTH, i + 1);

			int dayofweek = cal.get(Calendar.DAY_OF_WEEK);

			String cellStyle = inputComponent.getDayCellClass();
			StringBuilder description = new StringBuilder();

			// checking for vacations days
				
			Collection<OcupationEntry> entries = ocupation.getOcupationEntries(cal.getTime());

			if (i < 0 || i >= lastDayInMonth) {
				cellStyle = inputComponent.getExtraDaysCellClass();
			} 
			
			if (entries != null && entries.size() > 0) {
				
				int performedHours = 0;
				int aEntryIsVacations = 0;
				
				for (OcupationEntry entry : entries) {
					
					performedHours += entry.getDuration();
					if (entry.isVacances()) {
						aEntryIsVacations = 1;					
					} else if(entry.isHoliday()) {
						aEntryIsVacations = 2;
					}
				
					description.append("[" + entry.getDescription() + "]");
				}
				
				if (aEntryIsVacations==1) {
					cellStyle = inputComponent.getVacancesCellClass();
				} else if (aEntryIsVacations==2) {
					cellStyle = inputComponent.getWeekEndCellClass();
				} else {
					if (performedHours >= Integer.valueOf(inputComponent.getWorkingDayHours()).intValue()) {
						cellStyle = inputComponent.getFullWorkCellClass();
					} else {
						cellStyle = inputComponent.getPartialWorkCellClass();
					}
				}
				
			
			} else {
				description = null;
			}
			
			// week-end days
			if (dayofweek == 1 || dayofweek == 7) {
				description = new StringBuilder("Fin de semana");
				cellStyle = inputComponent.getWeekEndCellClass();
			}
			
			writeCell(facesContext, writer, inputComponent, String.valueOf(cal.get(Calendar.DAY_OF_MONTH)), null, cellStyle, description == null ? null : description.toString());
		}
	}

	private void writeCell(FacesContext facesContext, ResponseWriter writer, UIInput component,
			String content, Date valueForLink, String styleClass, String description) throws IOException {
				
		writer.startElement(HTML.TD_ELEM, component);

		if (styleClass != null)
			writer.writeAttribute(HTML.CLASS_ATTR, styleClass, null);

		if (description != null) {
			writer.writeAttribute(HTML.TITLE_ATTR, description, null); 
		}
		
		writer.writeText(content, JSFAttr.VALUE_ATTR);

		writer.endElement(HTML.TD_ELEM);
	}

	private static String[] mapShortWeekdays(DateFormatSymbols symbols) {
		String[] weekdays = new String[7];

		String[] localeWeekdays = symbols.getShortWeekdays();

		weekdays[0] = localeWeekdays[Calendar.MONDAY];
		weekdays[1] = localeWeekdays[Calendar.TUESDAY];
		weekdays[2] = localeWeekdays[Calendar.WEDNESDAY];
		weekdays[3] = localeWeekdays[Calendar.THURSDAY];
		weekdays[4] = localeWeekdays[Calendar.FRIDAY];
		weekdays[5] = localeWeekdays[Calendar.SATURDAY];
		weekdays[6] = localeWeekdays[Calendar.SUNDAY];

		return weekdays;
	}

	private int mapCalendarDayToCommonDay(int day) {
		switch (day) {
		case Calendar.TUESDAY:
			return 1;
		case Calendar.WEDNESDAY:
			return 2;
		case Calendar.THURSDAY:
			return 3;
		case Calendar.FRIDAY:
			return 4;
		case Calendar.SATURDAY:
			return 5;
		case Calendar.SUNDAY:
			return 6;
		default:
			return 0;
		}
	}

	private Calendar copyCalendar(FacesContext facesContext, Calendar timeKeeper) {
		Calendar cal = Calendar.getInstance(facesContext.getViewRoot().getLocale());
		cal.setTime(timeKeeper.getTime());
		return cal;
	}

	public void decode(FacesContext facesContext, UIComponent component) {
		RendererUtils.checkParamValidity(facesContext, component, HtmlOcupationCalendar.class);

		String helperString = "";

		if (component instanceof EditableValueHolder) {

			Map<String, String> paramMap = facesContext.getExternalContext()
					.getRequestParameterMap();
			String clientId = component.getClientId(facesContext);

			if (HtmlRendererUtils.isDisabledOrReadOnly(component))
				return;

			if (paramMap.containsKey(clientId)) {
				String value = (String) paramMap.get(clientId);

				if (!value.equalsIgnoreCase(helperString)) {
					((EditableValueHolder) component).setSubmittedValue(value);
				} else {
					((EditableValueHolder) component).setSubmittedValue("");
				}
			} else {
				log.warn("Warning! Component : " + RendererUtils.getPathToComponent(component));
			}
		}
	}

	public Object getConvertedValue(FacesContext facesContext, UIComponent uiComponent,
			Object submittedValue) throws ConverterException {
		RendererUtils.checkParamValidity(facesContext, uiComponent, HtmlOcupationCalendar.class);

		UIInput uiInput = (UIInput) uiComponent;

		Converter converter = uiInput.getConverter();

		if (converter == null)
			converter = new CalendarDateTimeConverter();

		if (submittedValue != null && !(submittedValue instanceof String)) {
			throw new IllegalArgumentException("Submitted value of type String expected");
		}

		return converter.getAsObject(facesContext, uiComponent, (String) submittedValue);
	}

	public interface DateConverter extends Converter {
		public Date getAsDate(FacesContext facesContext, UIComponent uiComponent);
	}

	private static String getHelperString(UIComponent uiComponent) {
		return "";
	}

	public static class CalendarDateTimeConverter implements DateConverter {
		private static final String	CONVERSION_MESSAGE_ID	= "org.apache.myfaces.calendar.CONVERSION";

		public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
			if (s == null || s.trim().length() == 0 || s.equals(getHelperString(uiComponent)))
				return null;

			DateFormat dateFormat = createStandardDateFormat(facesContext);

			dateFormat.setLenient(false);

			try {
				return dateFormat.parse(s);
			} catch (ParseException e) {
				FacesMessage msg = MessageUtils.getMessage(CONVERSION_MESSAGE_ID, new Object[] {
						uiComponent.getId(), s });
				throw new ConverterException(msg, e);
			}
		}

		public Date getAsDate(FacesContext facesContext, UIComponent uiComponent) {
			return RendererUtils.getDateValue(uiComponent);
		}

		public static String createJSPopupFormat(FacesContext facesContext, String popupDateFormat) {

			if (popupDateFormat == null) {
				SimpleDateFormat defaultDateFormat = createStandardDateFormat(facesContext);
				popupDateFormat = defaultDateFormat.toPattern();
			}

			return popupDateFormat;
		}

		public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
			Date date = (Date) o;

			if (date == null)
				return getHelperString(uiComponent);

			DateFormat dateFormat = createStandardDateFormat(facesContext);

			dateFormat.setLenient(false);

			return dateFormat.format(date);
		}

		private static SimpleDateFormat createStandardDateFormat(FacesContext facesContext) {
			DateFormat dateFormat;
			dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, facesContext.getViewRoot()
					.getLocale());

			if (dateFormat instanceof SimpleDateFormat)
				return (SimpleDateFormat) dateFormat;
			else
				return new SimpleDateFormat("dd.MM.yyyy", facesContext.getViewRoot().getLocale());
		}

	}
}