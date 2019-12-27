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
import org.apache.myfaces.custom.schedule.model.ScheduleDay;
import org.apache.myfaces.custom.schedule.model.ScheduleEntry;
import org.apache.myfaces.custom.schedule.renderer.AbstractScheduleRenderer;
import org.apache.myfaces.custom.schedule.util.ScheduleUtil;
import org.apache.myfaces.shared_tomahawk.renderkit.RendererUtils;
import org.apache.myfaces.shared_tomahawk.renderkit.html.HTML;
import org.apache.myfaces.shared_tomahawk.renderkit.html.util.FormInfo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.ValueBinding;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * Renderer for the day and workweek views of the Schedule component
 * </p>
 *
 * @author Jurgen Lust (latest modification by $Author: jlust $)
 * @author Bruno Aranda (adaptation of Jurgen's code to myfaces)
 * @version $Revision: 392301 $
 */
public class BitacoreScheduleDetailedDayRenderer extends AbstractScheduleRenderer
        implements Serializable
{
    private static final Log log = LogFactory.getLog(BitacoreScheduleDetailedDayRenderer.class);
    private static final long serialVersionUID = -5103791076091317355L;

    //~ Instance fields --------------------------------------------------------

    private final int defaultRowHeightInPixels = 22;

    //~ Methods ----------------------------------------------------------------

    /**
     * @see javax.faces.render.Renderer#encodeBegin(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent)
     */
    public void encodeBegin(FacesContext context, UIComponent component)
            throws IOException
    {
        if (!component.isRendered())
        {
            return;
        }

        super.encodeBegin(context, component);

        HtmlSchedule schedule = (HtmlSchedule) component;
        ResponseWriter writer = context.getResponseWriter();
        int rowHeight = getRowHeight(schedule.getAttributes());

        //the number of rows in the grid is the number of half hours between
        //visible start hour and visible end hour, plus 1 for the header
        int numberOfRows = ((getRenderedEndHour(schedule) - getRenderedStartHour(schedule)) * 2) + 1;

        //the grid height = 22 pixels times the number of rows + 3, for the
        //table border and the cellpadding
        int gridHeight = (numberOfRows * rowHeight) + 3 + 10;

        //container div for the schedule grid
        writer.startElement(HTML.DIV_ELEM, schedule);
        writer.writeAttribute(HTML.CLASS_ATTR, "schedule-detailed-"
                + getTheme(schedule), null);
        writer.writeAttribute(HTML.STYLE_ATTR, "height: "
                + String.valueOf(gridHeight) + "px; overflow: hidden;", null);
        writeBackground(context, schedule, writer);
        writeForegroundStart(context, schedule, writer);
    }

    /**
     * @see javax.faces.render.Renderer#encodeChildren(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent)
     */
    public void encodeChildren(FacesContext context, UIComponent component)
            throws IOException
    {
        if (!component.isRendered())
        {
            return;
        }

        HtmlSchedule schedule = (HtmlSchedule) component;
        ResponseWriter writer = context.getResponseWriter();
        String clientId = schedule.getClientId(context);
        FormInfo parentFormInfo = RendererUtils.findNestingForm(schedule, context);
        String formId = parentFormInfo == null ? null : parentFormInfo.getFormName();

        for (Iterator dayIterator = schedule.getModel().iterator(); dayIterator
                .hasNext();)
        {
            ScheduleDay day = (ScheduleDay) dayIterator.next();
            String dayBodyId = clientId + "_body_" + ScheduleUtil.getDateId(day.getDate());
            writer.startElement(HTML.TD_ELEM, schedule);
            writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                    "column"), null);
            writer.writeAttribute(HTML.STYLE_ATTR, "height: 100%;", null);
            writer.startElement(HTML.DIV_ELEM, schedule);
            writer.writeAttribute(HTML.ID_ATTR, dayBodyId, null);
            writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                    "column"), null);
            writer
                    .writeAttribute(
                            HTML.STYLE_ATTR,
                            "position: relative; top: 0px; left: 0px; width: 100%; height: 100%; z-index: 0;",
                            null);
            //register an onclick event listener to a column which will capture
            //the y coordinate of the mouse, to determine the hour of day
            if (!schedule.isReadonly() && schedule.isSubmitOnClick()) {
                writer.writeAttribute(
                        HTML.ONMOUSEUP_ATTR,
                        "fireScheduleTimeClicked(this, event, '"
                        + formId + "', '"
                        + clientId
                        + "');",
                        null);
            }
            writeEntries(context, schedule, day, writer);
            writer.endElement(HTML.DIV_ELEM);
            writer.endElement(HTML.TD_ELEM);
        }
    }

    /**
     * @see javax.faces.render.Renderer#encodeEnd(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent)
     */
    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException
    {
        if (!component.isRendered())
        {
            return;
        }

        ResponseWriter writer = context.getResponseWriter();

        writeForegroundEnd(writer);
        writer.endElement(HTML.DIV_ELEM);
    }

    protected String getCellClass(HtmlSchedule schedule, int column, int row, int hour)
    {
        String cellClass = "free";
        ScheduleDay day = (ScheduleDay) schedule.getModel().get(column);

        if (!day.isWorkingDay())
        {
            return getStyleClass(schedule, cellClass);
        }

        if (hour >= schedule.getWorkingStartHour()
                && hour < schedule.getWorkingEndHour())
        {
            cellClass = ((row % 2) == 0) ? "even" : "uneven";
        }

        return getStyleClass(schedule, cellClass);
    }

    protected boolean isSelected(HtmlSchedule schedule, EntryWrapper entry)
    {
        ScheduleEntry selectedEntry = schedule.getModel().getSelectedEntry();

        if (selectedEntry == null)
        {
            return false;
        }

        boolean returnboolean = selectedEntry.getId().equals(
                entry.entry.getId());

        return returnboolean;
    }

    protected void maximizeEntries(EntryWrapper[] entries, int numberOfColumns)
    {
        for (int i = 0; i < entries.length; i++)
        {
            EntryWrapper entry = entries[i];

            //now see if we can expand the entry to the columns on the right
            while (((entry.column + entry.colspan) < numberOfColumns)
                    && entry.canFitInColumn(entry.column + entry.colspan))
            {
                entry.colspan++;
            }
        }
    }

    protected void scanEntries(EntryWrapper[] entries, int index)
    {
        if (entries.length <= 0)
        {
            return;
        }

        EntryWrapper entry = entries[index];
        entry.column = 0;

        //see what columns are already taken
        for (int i = 0; i < index; i++)
        {
            if (entry.overlaps(entries[i]))
            {
                entry.overlappingEntries.add(entries[i]);
                entries[i].overlappingEntries.add(entry);
            }
        }

        //find an available column
        while (!entry.canFitInColumn(entry.column))
        {
            entry.column++;
        }

        //recursively scan the remaining entries for overlaps
        if (++index < entries.length)
        {
            scanEntries(entries, index);
        }
    }

    protected void writeBackground(FacesContext context, HtmlSchedule schedule,
                                 ResponseWriter writer) throws IOException
    {
        final int rowHeight = getRowHeight(schedule.getAttributes()) - 1;
        final int headerHeight = rowHeight + 10;
        writer.startElement(HTML.DIV_ELEM, schedule);
        writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                "background"), null);
        writer
                .writeAttribute(
                        HTML.STYLE_ATTR,
                        "position: absolute; left: 0px; top: 0px; width: 100%; height: 100%; z-index: 0;",
                        null);

        //background table for the schedule grid
        writer.startElement(HTML.TABLE_ELEM, schedule);
        writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                "background"), null);
        writer.writeAttribute(HTML.CELLPADDING_ATTR, "0", null);
        writer.writeAttribute(HTML.CELLSPACING_ATTR, "1", null);
        writer.writeAttribute(HTML.STYLE_ATTR, "width: 100%; height: 100%",
                null);
        writer.startElement(HTML.TBODY_ELEM, schedule);

        //header row, containing the column names
        writer.startElement(HTML.TR_ELEM, schedule);
        writer.startElement(HTML.TD_ELEM, schedule);
        writer.writeAttribute(HTML.CLASS_ATTR,
                getStyleClass(schedule, "gutter"), null);
        writer
                .writeAttribute(
                        HTML.STYLE_ATTR,
                        "height: "
                                + rowHeight
                                + "px; border-style: none; border-width: 0px; overflow: hidden; padding: 0px",
                        null);
        writer.startElement(HTML.DIV_ELEM, schedule);
        writer
                .writeAttribute(HTML.STYLE_ATTR, "height: 1px; width: 56px",
                        null);
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.TD_ELEM);

        float columnWidth = (schedule.getModel().size() == 0) ? 100
                : ((float)100 / schedule.getModel().size());

        for (Iterator dayIterator = schedule.getModel().iterator(); dayIterator
                .hasNext();)
        {
            ScheduleDay day = (ScheduleDay) dayIterator.next();
            writer.startElement(HTML.TD_ELEM, schedule);
            writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                    "header"), null);
            writer
                    .writeAttribute(
                            HTML.STYLE_ATTR,
                            "height: " + headerHeight + "px; border-style: none; border-width: 0px; overflow: hidden;",
                            null);
            writer.writeAttribute(HTML.WIDTH_ATTR, String.valueOf(columnWidth)
                    + "%", null);
            writer.startElement(HTML.DIV_ELEM, schedule);
            writer
                    .writeAttribute(
                            HTML.STYLE_ATTR,
                            "position: relative; left: 0px; top: 0px; width: 100%; height: 100%;",
                            null);

            //write the date
            writer.startElement(HTML.SPAN_ELEM, schedule);
            writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                    "date"), null);
            writer
                    .writeAttribute(
                            HTML.STYLE_ATTR,
                            "position: absolute; left: 0px; top: 0px; height: 15px; width: 100%; vertical-align: top; overflow: hidden; white-space: nowrap;",
                            null);
            writer.writeText(getDateString(context, schedule, day.getDate()),
                    null);
            writer.endElement(HTML.SPAN_ELEM);

            //write the name of the holiday, if there is one
            if ((day.getSpecialDayName() != null)
                    && (day.getSpecialDayName().length() > 0))
            {
                writer.startElement(HTML.SPAN_ELEM, schedule);
                writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                        "holiday"), null);
                writer
                        .writeAttribute(
                                HTML.STYLE_ATTR,
                                "position: absolute; left: 0px; top: 15px; width: 100%; vertical-align: top; overflow: hidden; white-space: nowrap;",
                                null);
                writer.writeText(day.getSpecialDayName(), null);
                writer.endElement(HTML.SPAN_ELEM);
            }

            writer.endElement(HTML.DIV_ELEM);
            writer.endElement(HTML.TD_ELEM);
        }

        writer.endElement(HTML.TR_ELEM);

        int startHour = getRenderedStartHour(schedule);
        int endHour = getRenderedEndHour(schedule);
        int numberOfRows = (endHour - startHour) * 2;

        for (int row = 0; row < numberOfRows; row++)
        {
            writer.startElement(HTML.TR_ELEM, schedule);

            //write the hours of the day on the left
            //this only happens on even rows, or every hour
            if ((row % 2) == 0)
            {
                writer.startElement(HTML.TD_ELEM, schedule);
                writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                        "gutter"), null);
                writer
                        .writeAttribute(
                                HTML.STYLE_ATTR,
                                "height: "
                                        + rowHeight
                                        + "px; border-style: none; border-width: 0px; overflow: hidden; padding: 0px",
                                null);
                writer.writeAttribute("rowspan", "2", null);
                writer.startElement(HTML.SPAN_ELEM, schedule);
                writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                        "hours"), null);
                writer.writeAttribute(HTML.STYLE_ATTR,
                        "vertical-align: top; height: 100%; padding: 0px;",
                        null);
                writer.writeText(String.valueOf(startHour + (row / 2)), null);
                writer.endElement(HTML.SPAN_ELEM);
                writer.startElement(HTML.SPAN_ELEM, schedule);
                writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                        "minutes"), null);
                writer.writeAttribute(HTML.STYLE_ATTR,
                        "vertical-align: top; height: 100%; padding: 0px;",
                        null);
                writer.writeText("00", null);
                writer.endElement(HTML.SPAN_ELEM);
                writer.endElement(HTML.TD_ELEM);
            }

            //write the cells of the day columns on this row
            for (int column = 0; column < schedule.getModel().size(); column++)
            {
                writer.startElement(HTML.TD_ELEM, schedule);
                writer.writeAttribute(HTML.CLASS_ATTR, getCellClass(schedule,
                        column, row, startHour + (row / 2)), null);
                writer.writeAttribute(HTML.STYLE_ATTR,
                        "overflow: hidden; height: " + rowHeight + "px;", null);
                writer.writeAttribute(HTML.WIDTH_ATTR, String
                        .valueOf(columnWidth)
                        + "%", null);
                writer.write(HTML.NBSP_ENTITY);
                writer.endElement(HTML.TD_ELEM);
            }

            writer.endElement(HTML.TR_ELEM);
        }

        writer.endElement(HTML.TBODY_ELEM);
        writer.endElement(HTML.TABLE_ELEM);
        writer.endElement(HTML.DIV_ELEM);
    }

    protected int getRenderedStartHour(HtmlSchedule schedule)
    {
        int startHour = schedule.getVisibleStartHour();

        //default behaviour: do not auto-expand the schedule to display all
        //entries
        if (!expandToFitEntries(schedule)) return startHour;

        for (Iterator dayIterator = schedule.getModel().iterator(); dayIterator.hasNext();)
        {
            ScheduleDay day = (ScheduleDay) dayIterator.next();
            int dayStart = day.getFirstEventHour();

            if (dayStart < startHour) {
                startHour = dayStart;
            }
        }

        return startHour;
    }

    protected int getRenderedEndHour(HtmlSchedule schedule)
    {
        int endHour = schedule.getVisibleEndHour();

        //default behaviour: do not auto-expand the schedule to display all
        //entries
        if (!expandToFitEntries(schedule)) return endHour;

        for (Iterator dayIterator = schedule.getModel().iterator(); dayIterator.hasNext();)
        {
            ScheduleDay day = (ScheduleDay) dayIterator.next();
            int dayEnd = day.getLastEventHour();

            if (dayEnd > endHour) {
                endHour = dayEnd;
            }
        }

        return endHour;
    }

    protected void writeEntries(FacesContext context, HtmlSchedule schedule,
                              ScheduleDay day, ResponseWriter writer) throws IOException
    {
        final String clientId = schedule.getClientId(context);
        FormInfo parentFormInfo = RendererUtils.findNestingForm(schedule, context);
        String formId = parentFormInfo == null ? null : parentFormInfo.getFormName();

        TreeSet entrySet = new TreeSet();

        for (Iterator entryIterator = day.iterator(); entryIterator.hasNext();)
        {
            entrySet.add(new EntryWrapper((ScheduleEntry) entryIterator.next(),
                    day));
        }

        EntryWrapper[] entries = (EntryWrapper[]) entrySet
                .toArray(new EntryWrapper[entrySet.size()]);

        //determine overlaps
        scanEntries(entries, 0);

        //determine the number of columns within this day
        int maxColumn = 0;

        for (Iterator entryIterator = entrySet.iterator(); entryIterator
                .hasNext();)
        {
            EntryWrapper wrapper = (EntryWrapper) entryIterator.next();
            maxColumn = Math.max(wrapper.column, maxColumn);
        }

        int numberOfColumns = maxColumn + 1;

        //make sure the entries take up all available space horizontally
        maximizeEntries(entries, numberOfColumns);

        //now determine the width in percent of 1 column
        float columnWidth = (float)100 / numberOfColumns;

        //and now draw the entries in the columns
        for (Iterator entryIterator = entrySet.iterator(); entryIterator
                .hasNext();)
        {
            EntryWrapper wrapper = (EntryWrapper) entryIterator.next();
            boolean selected = isSelected(schedule, wrapper);
            //compose the CSS style for the entry box
            StringBuffer entryStyle = new StringBuffer();
            entryStyle.append(wrapper.getBounds(schedule, columnWidth));
            String entryBorderColor = getEntryRenderer(schedule).getColor(
                    context, schedule, wrapper.entry, selected);
            if (entryBorderColor != null)
            {
                entryStyle.append(" border-color: ");
                entryStyle.append(entryBorderColor);
                entryStyle.append(";");
            }

            if (selected)
            {
                writer.startElement(HTML.DIV_ELEM, schedule);
                writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                        "entry-selected"), null);
                writer.writeAttribute(HTML.STYLE_ATTR, entryStyle.toString(),
                        null);

                //draw the tooltip
                if (showTooltip(schedule))
                {
                    getEntryRenderer(schedule).renderToolTip(context, writer,
                            schedule, wrapper.entry, selected);
                }

                //draw the content
                getEntryRenderer(schedule).renderContent(context, writer,
                        schedule, day, wrapper.entry, false, selected);
                writer.endElement(HTML.DIV_ELEM);
            }
            else
            {
                //if the schedule is read-only, the entries should not be
                //hyperlinks
                writer.startElement(
                        schedule.isReadonly() ? HTML.DIV_ELEM : HTML.ANCHOR_ELEM, schedule);

                //draw the tooltip
                if (showTooltip(schedule))
                {
                    getEntryRenderer(schedule).renderToolTip(context, writer,
                            schedule, wrapper.entry, selected);
                }

                if (!schedule.isReadonly())
                {
                    writer.writeAttribute("href", "#", null);

                    writer.writeAttribute(
                            HTML.ONMOUSEUP_ATTR,
                            "fireEntrySelected('"
                            + formId + "', '"
                            + clientId + "', '"
                            + wrapper.entry.getId()
                            + "');",
                            null);
                }

                writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                        "entry"), null);
                writer.writeAttribute(HTML.STYLE_ATTR, entryStyle.toString(),
                        null);

                //draw the content
                getEntryRenderer(schedule).renderContent(context, writer,
                        schedule, day, wrapper.entry, false, selected);

                writer.endElement(schedule.isReadonly() ? HTML.DIV_ELEM : "a");
            }
        }
    }

    protected void writeForegroundEnd(ResponseWriter writer) throws IOException
    {
        writer.endElement(HTML.TR_ELEM);
        writer.endElement(HTML.TABLE_ELEM);
        writer.endElement(HTML.DIV_ELEM);
    }

    protected void writeForegroundStart(FacesContext context,
                                      HtmlSchedule schedule, ResponseWriter writer) throws IOException
    {
        final int rowHeight = getRowHeight(schedule.getAttributes()) - 1;
        final int headerHeight = rowHeight + 10;
        final String clientId = schedule.getClientId(context);
        FormInfo parentFormInfo = RendererUtils.findNestingForm(schedule, context);
        String formId = parentFormInfo == null ? null : parentFormInfo.getFormName();

        writer.startElement(HTML.DIV_ELEM, schedule);
        writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                "foreground"), null);
        writer
                .writeAttribute(
                        HTML.STYLE_ATTR,
                        "position: absolute; left: 0px; top: 0px; width: 100%; height: 100%;	z-index: 2;",
                        null);

        writer.startElement(HTML.TABLE_ELEM, schedule);
        writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                "foreground"), null);
        writer.writeAttribute(HTML.CELLSPACING_ATTR, "1", null);
        writer.writeAttribute(HTML.CELLPADDING_ATTR, "0", null);
        writer.writeAttribute(HTML.STYLE_ATTR, "width: 100%; height: 100%",
                null);
        writer.startElement(HTML.TR_ELEM, schedule);
        writer.startElement(HTML.TD_ELEM, schedule);
        writer.startElement(HTML.DIV_ELEM, schedule);
        writer
                .writeAttribute(HTML.STYLE_ATTR, "height: 1px; width: 56px",
                        null);
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.TD_ELEM);

        float columnWidth = (schedule.getModel().size() == 0) ? 100
                : ((float)100 / schedule.getModel().size());

        for (Iterator dayIterator = schedule.getModel().iterator(); dayIterator
                .hasNext();)
        {
            ScheduleDay day = (ScheduleDay) dayIterator.next();
            final String dayHeaderId = clientId + "_header_" + ScheduleUtil.getDateId(day.getDate());
            writer.startElement(HTML.TD_ELEM, schedule);
            writer.writeAttribute(HTML.ID_ATTR, dayHeaderId, null);
            writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule,
                    "header"), null);
            writer
                    .writeAttribute(
                            HTML.STYLE_ATTR,
                            "height: " + headerHeight + "px; border-style: none; border-width: 0px; overflow: hidden;",
                            null);
            writer.writeAttribute(HTML.WIDTH_ATTR, String.valueOf(columnWidth)
                    + "%", null);
            //register an onclick event listener to a column header which will
            //be used to determine the date
            if (!schedule.isReadonly() && schedule.isSubmitOnClick()) {
                writer.writeAttribute(
                        HTML.ONMOUSEUP_ATTR,
                        "fireScheduleDateClicked(this, event, '"
                        + formId + "', '"
                        + clientId
                        + "');",
                        null);
            }

            writer.endElement(HTML.TD_ELEM);
        }

        writer.endElement(HTML.TR_ELEM);

        writer.startElement(HTML.TR_ELEM, schedule);
        writer.startElement(HTML.TD_ELEM, schedule);
        writer.startElement(HTML.DIV_ELEM, schedule);
        writer
                .writeAttribute(HTML.STYLE_ATTR, "height: 1px; width: 56px",
                        null);
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.TD_ELEM);
    }

    //~ Inner Classes ----------------------------------------------------------

    protected String getRowHeightProperty()
    {
        return "detailedRowHeight";
    }

    protected int getDefaultRowHeight()
    {
        return defaultRowHeightInPixels;
    }

    /**
     * In the detailed day renderer, we take the y coordinate of the mouse
     * into account when determining the last clicked date.
     */
    protected Date determineLastClickedDate(HtmlSchedule schedule, String dateId, String yPos) {
        Calendar cal = GregorianCalendar.getInstance();
        //the dateId is the schedule client id + "_" + yyyyMMdd 
        String day = dateId.substring(dateId.lastIndexOf("_") + 1);
        Date date = ScheduleUtil.getDateFromId(day);

        if (date != null) cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, getRenderedStartHour(schedule));
        //OK, we have the date, let's determine the time
        try {
            int y = Integer.parseInt(yPos);
            int halfHourHeight = getRowHeight(schedule.getAttributes());
            int minutes = y * 30 / halfHourHeight;
            cal.add(Calendar.MINUTE, minutes);
        } catch (NumberFormatException nfe) {
            log.debug("y position is not a number");
        }
        log.debug("last clicked datetime: " + cal.getTime());
        return cal.getTime();
    }

    /**
     * <p>
     * When the start- and endtime of an entry are the same, should the entry
     * be rendered, fitting the entry box to the text? 
     * </p>
     * 
     * @param component the component
     * @return whether or not zero length entries should be rendered
     */
    protected boolean renderZeroLengthEntries(UIComponent component) {
        //first check if the renderZeroLengthEntries property is a value binding expression
        ValueBinding binding = component.getValueBinding("renderZeroLengthEntries");
        if (binding != null)
        {
            Boolean value = (Boolean) binding.getValue(FacesContext
                    .getCurrentInstance());

            if (value != null)
            {
                return value.booleanValue();
            }
        }
        //it's not a value binding expression, so check for the string value
        //in the attributes
        Map attributes = component.getAttributes();
        return Boolean.valueOf((String) attributes.get("renderZeroLengthEntries"))
                .booleanValue();
    }

    /**
     * <p>
     * When the start- and endtime of an entry are the same, should the entry
     * be rendered, fitting the entry box to the text? 
     * </p>
     * 
     * @param component the component
     * @return whether or not zero length entries should be rendered
     */
    protected boolean expandToFitEntries(UIComponent component) {
        //first check if the expandToFitEntries property is a value binding expression
        ValueBinding binding = component.getValueBinding("expandToFitEntries");
        if (binding != null)
        {
            Boolean value = (Boolean) binding.getValue(FacesContext
                    .getCurrentInstance());

            if (value != null)
            {
                return value.booleanValue();
            }
        }
        //it's not a value binding expression, so check for the string value
        //in the attributes
        Map attributes = component.getAttributes();
        return Boolean.valueOf((String) attributes.get("expandToFitEntries"))
                .booleanValue();
    }


    protected class EntryWrapper implements Comparable
    {
        //~ Static fields/initializers -----------------------------------------

        private static final int HALF_HOUR = 1000 * 60 * 30;

        //~ Instance fields ----------------------------------------------------

        private final ScheduleDay day;
        private final ScheduleEntry entry;
        private final TreeSet overlappingEntries;
        private int colspan;
        private int column;

        //~ Constructors -------------------------------------------------------

        EntryWrapper(ScheduleEntry entry, ScheduleDay day)
        {
            this.entry = entry;
            this.day = day;
            this.column = 0;
            this.colspan = 1;
            this.overlappingEntries = new TreeSet();
        }

        //~ Methods ------------------------------------------------------------

        /**
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        public int compareTo(Object o)
        {
            return comparator.compare(entry, o);
        }

        /**
         * @see java.lang.Object#equals(java.lang.Object)
         */
        public boolean equals(Object o)
        {
            if (o instanceof EntryWrapper)
            {
                EntryWrapper other = (EntryWrapper) o;

                boolean returnboolean = (entry.getStartTime()
                        .equals(other.entry.getStartTime()))
                        && (entry.getEndTime().equals(other.entry.getEndTime()))
                        && (entry.getId().equals(other.entry.getId()))
                        && (day.equals(other.day));
                /*
                 new EqualsBuilder().append(
                 entry.getStartTime(), other.entry.getStartTime()
                 ).append(entry.getEndTime(), other.entry.getEndTime())
                 .append(
                 entry.getId(), other.entry.getId()
                 ).append(day, other.day).isEquals();
                 */
                return returnboolean;
            }

            return false;
        }

        /**
         * @see java.lang.Object#hashCode()
         */
        public int hashCode()
        {
            int returnint = entry.getStartTime().hashCode()
                    ^ entry.getEndTime().hashCode() ^ entry.getId().hashCode();

            return returnint;
        }

        /**
         * <p>
         * Determine the bounds of this entry, in CSS position attributes
         * </p>
         *
         * @param schedule the schedule
         * @param columnWidth the width of a column
         *
         * @return the bounds
         */
        String getBounds(HtmlSchedule schedule, float columnWidth)
        {
            int rowHeight = getRowHeight(schedule.getAttributes());
            float width = (columnWidth * colspan) - 0.5f;
            float left = column * columnWidth;
            Calendar cal = GregorianCalendar.getInstance();
            cal.setTime(day.getDate());

            int curyear = cal.get(Calendar.YEAR);
            int curmonth = cal.get(Calendar.MONTH);
            int curday = cal.get(Calendar.DATE);

            cal.setTime(entry.getStartTime());
            cal.set(curyear, curmonth, curday);

            long startMillis = cal.getTimeInMillis();
            cal.set(Calendar.HOUR_OF_DAY, getRenderedStartHour(schedule));
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            long visibleStartMillis = cal.getTimeInMillis();
            startMillis = day.equalsDate(entry.getStartTime()) ? Math.max(
                    startMillis, visibleStartMillis) : visibleStartMillis;
            cal.setTime(entry.getEndTime());
            cal.set(curyear, curmonth, curday);

            long endMillis = cal.getTimeInMillis();
            cal.set(Calendar.HOUR_OF_DAY, getRenderedEndHour(schedule));
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            long visibleEndMillis = cal.getTimeInMillis();
            endMillis = day.equalsDate(entry.getEndTime()) ? Math.min(
                    endMillis, visibleEndMillis) : visibleEndMillis;

            int top = (int) (((startMillis - visibleStartMillis) * rowHeight) / HALF_HOUR);
            int height = (int) (((endMillis - startMillis) * rowHeight) / HALF_HOUR);
            StringBuffer buffer = new StringBuffer();

            boolean entryVisible = height > 0 || renderZeroLengthEntries(schedule);

            if (!entryVisible)
            {
                buffer.append("visibility: hidden; ");
            }
            buffer.append("position: absolute; height: ");
            if (height > 0) {
                buffer.append(height + "px");
            } else if (entryVisible) {
                buffer.append("auto");
            } else {
                buffer.append("0px");
            }
            buffer.append("; top: ");
            buffer.append(top);
            buffer.append("px; left: ");
            buffer.append(left);
            buffer.append("%; width: ");
            buffer.append(width);
            buffer
                    .append("%; padding: 0px; overflow: hidden; border-width: 1.0px; border-style:solid;");

            return buffer.toString();
        }

        /**
         * <p>
         * Can this entry fit in the specified column?
         * </p>
         *
         * @param column the column
         *
         * @return whether the entry fits
         */
        boolean canFitInColumn(int column)
        {
            for (Iterator overlapIterator = overlappingEntries.iterator(); overlapIterator
                    .hasNext();)
            {
                EntryWrapper overlap = (EntryWrapper) overlapIterator.next();

                if (overlap.column == column)
                {
                    return false;
                }
            }

            return true;
        }

        /**
         * <p>
         * What is the minimum end time allocated to this event?
         * Where the event has a duration, the end time of the event
         * is the minimum end time.<br />
         * Where the event has no duration, a minimum end time of half
         * and hour after the start is implemented.
         * </p>
         * @return The minimum end time of the event
         */
        Date minimumEndTime() {
            Date start = entry.getStartTime();
            Date end = entry.getEndTime();

            return end != null ?
                    (end.after(start) ? end : new Date(start.getTime() + HALF_HOUR))
                    : null;
        }

        /**
         * <p>
         * Does this entry overlap with another?
         * </p>
         *
         * @param other the other entry
         *
         * @return whether the entries overlap
         */
        boolean overlaps(EntryWrapper other)
        {
            Date start = entry.getStartTime();
        	Date end = minimumEndTime();

            if ((start == null) || (end == null))
            {
                return false;
            }

            boolean returnboolean = (start.before(
                    other.minimumEndTime()) && end.after(
                    other.entry.getStartTime()));

            return returnboolean;
        }
    }
}
//The End
