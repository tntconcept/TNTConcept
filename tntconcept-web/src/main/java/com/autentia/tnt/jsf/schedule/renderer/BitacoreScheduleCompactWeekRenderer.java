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


import org.apache.myfaces.custom.schedule.HtmlSchedule;
import org.apache.myfaces.custom.schedule.model.ScheduleDay;
import org.apache.myfaces.custom.schedule.renderer.AbstractCompactScheduleRenderer;
import org.apache.myfaces.shared_tomahawk.renderkit.html.HTML;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;


/**
 * <p>
 * Renderer for the week view of the UISchedule component
 * </p>
 *
 * @author Jurgen Lust (latest modification by $Author: schof $)
 * @author Bruno Aranda (adaptation of Jurgen's code to myfaces)
 * @version $Revision: 382051 $
 */
public class BitacoreScheduleCompactWeekRenderer
    extends BitacoreAbstractCompactScheduleRenderer
    implements Serializable
{
    private static final long serialVersionUID = 5504081783797695487L;

    //~ Methods ----------------------------------------------------------------

    /**
     * @see javax.faces.render.Renderer#encodeBegin(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent)
     */
    public void encodeBegin(
        FacesContext context,
        UIComponent component
    )
        throws IOException
    {
        if (!component.isRendered()) {
            return;
        }

        super.encodeBegin(context, component);

        HtmlSchedule schedule = (HtmlSchedule) component;
        ResponseWriter writer = context.getResponseWriter();

        //container div for the schedule grid
        writer.startElement(HTML.DIV_ELEM, schedule);
        writer.writeAttribute(HTML.CLASS_ATTR, "schedule-compact-" + getTheme(schedule), null);
        writer.writeAttribute(
            HTML.STYLE_ATTR, "border-style: none; overflow: hidden;", null
        );

        writer.startElement(HTML.TABLE_ELEM, schedule);
        writer.writeAttribute(HTML.CLASS_ATTR, getStyleClass(schedule, "week"), null);
        writer.writeAttribute(
            HTML.STYLE_ATTR, "position: relative; left: 0px; top: 0px; width: 100%;",
            null
        );
        writer.writeAttribute(HTML.CELLPADDING_ATTR, "0", null);
        writer.writeAttribute(HTML.CELLSPACING_ATTR, "1", null);
        writer.writeAttribute("border", "0", null);
        writer.writeAttribute(HTML.WIDTH_ATTR, "100%", null);
        writer.startElement(HTML.TBODY_ELEM, schedule);

        Calendar cal = GregorianCalendar.getInstance();

        for (
            Iterator dayIterator = schedule.getModel().iterator();
                dayIterator.hasNext();
        ) {
            ScheduleDay day = (ScheduleDay) dayIterator.next();
            cal.setTime(day.getDate());

            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
            boolean isWeekend =
                (dayOfWeek == Calendar.SATURDAY) ||
                (dayOfWeek == Calendar.SUNDAY);

            if (
                (dayOfWeek == Calendar.MONDAY) ||
                    (dayOfWeek == Calendar.WEDNESDAY) ||
                    (dayOfWeek == Calendar.FRIDAY) ||
                    (dayOfWeek == Calendar.SUNDAY)
            ) {
                writer.startElement(HTML.TR_ELEM, schedule);
            }

            writeDayCell(
                context, writer, schedule, day, 50f, dayOfWeek, dayOfMonth,
                isWeekend, true, (dayOfWeek == Calendar.FRIDAY) ? 2 : 1
            );

            if (
                (dayOfWeek == Calendar.TUESDAY) ||
                    (dayOfWeek == Calendar.THURSDAY) ||
                    (dayOfWeek == Calendar.SATURDAY) ||
                    (dayOfWeek == Calendar.SUNDAY)
            ) {
                writer.endElement(HTML.TR_ELEM);
            }
        }

        writer.endElement(HTML.TBODY_ELEM);
        writer.endElement(HTML.TABLE_ELEM);

        writer.endElement(HTML.DIV_ELEM);
    }

    /**
     * @see org.apache.myfaces.custom.schedule.renderer.AbstractCompactScheduleRenderer#getDefaultRowHeight()
     */
    protected int getDefaultRowHeight()
    {
        return 200;
    }

    /**
     * @see org.apache.myfaces.custom.schedule.renderer.AbstractCompactScheduleRenderer#getRowHeightProperty()
     */
    protected String getRowHeightProperty()
    {
        return "compactWeekRowHeight";
    }
}
//The End
