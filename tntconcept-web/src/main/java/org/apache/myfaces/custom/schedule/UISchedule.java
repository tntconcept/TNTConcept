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

package org.apache.myfaces.custom.schedule;

import java.io.Serializable;
import java.util.Iterator;

import javax.faces.component.ActionSource;
import javax.faces.component.UIComponentBase;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.el.MethodBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;

import org.apache.myfaces.custom.schedule.model.ScheduleDay;
import org.apache.myfaces.custom.schedule.model.ScheduleEntry;
import org.apache.myfaces.custom.schedule.model.ScheduleModel;
import org.apache.myfaces.custom.schedule.model.SimpleScheduleModel;
import org.apache.myfaces.custom.schedule.util.ScheduleUtil;

/**
 * <p>
 * A schedule component similar to the ones found in Outlook or Evolution
 * </p>
 *
 * @author Jurgen Lust (latest modification by $Author: jlust $)
 * @author Bruno Aranda (adaptation of Jurgen code to myfaces)
 * @version $Revision: 392301 $
 */
public class UISchedule extends UIComponentBase implements ValueHolder,
        Serializable, ActionSource
{
    private static final long serialVersionUID = 3440822180568539483L;

    private class ScheduleActionListener implements ActionListener
    {
        //~ Methods ------------------------------------------------------------

        /**
         * @see javax.faces.event.ActionListener#processAction(javax.faces.event.ActionEvent)
         */
        public void processAction(ActionEvent event)
                throws AbortProcessingException
        {
            UISchedule schedule = (UISchedule) event.getComponent();
            ScheduleEntry entry = schedule.getSubmittedEntry();
            schedule.getModel().setSelectedEntry(entry);
            schedule.setSubmittedEntry(null);

            if (schedule.getAction() != null)
            {
                getFacesContext().getApplication().getActionListener()
                        .processAction(event);
            }
        }
    }
    public static final String COMPONENT_FAMILY = "javax.faces.Panel";
    //~ Static fields/initializers ---------------------------------------------
    public static final String COMPONENT_TYPE = "org.apache.myfaces.Schedule";

    //~ Instance fields --------------------------------------------------------

    public static final String DEFAULT_RENDERER_TYPE = "org.apache.myfaces.Schedule";
    private MethodBinding actionBinding;
    private MethodBinding actionListenerBinding;
    private Converter converter;
    private Boolean immediate;
    private Boolean readonly;
    private ScheduleActionListener scheduleListener;
    private ScheduleEntry submittedEntry;
    private Object value;
    private Integer visibleEndHour;
    private Integer visibleStartHour;
    private Integer workingEndHour;

    //~ Constructors -----------------------------------------------------------

    private Integer workingStartHour;

    //~ Methods ----------------------------------------------------------------

    /**
     * Creates a new UISchedule object.
     */
    public UISchedule()
    {
        super();
        setRendererType(DEFAULT_RENDERER_TYPE);
        scheduleListener = new ScheduleActionListener();
        addActionListener(scheduleListener); 
    }

    /**
     * @see javax.faces.component.ActionSource#addActionListener(javax.faces.event.ActionListener)
     */
    public void addActionListener(ActionListener listener)
    {
        addFacesListener(listener);
    }

    /**
     * @see javax.faces.component.UIComponent#broadcast(javax.faces.event.FacesEvent)
     */
    public void broadcast(FacesEvent event) throws AbortProcessingException
    {
        super.broadcast(event);

        FacesContext context = getFacesContext();
        MethodBinding actionListenerBinding = getActionListener();

        if (actionListenerBinding != null)
        {
            actionListenerBinding.invoke(context, new Object[] { event });
        }
    }

    /**
     * <p>
     * Find the entry with the given id
     * </p>
     *
     * @param id the id
     *
     * @return the entry
     */
    public ScheduleEntry findEntry(String id)
    {
        if (id == null)
        {
            return null;
        }

        for (Iterator dayIterator = getModel().iterator(); dayIterator
                .hasNext();)
        {
            ScheduleDay day = (ScheduleDay) dayIterator.next();

            for (Iterator iter = day.iterator(); iter.hasNext();)
            {
                ScheduleEntry entry = (ScheduleEntry) iter.next();

                if (id.equals(entry.getId()))
                {
                    return entry;
                }
            }
        }

        return null;
    }

    /**
     * @see javax.faces.component.ActionSource#getAction()
     */
    public MethodBinding getAction()
    {
        return actionBinding;
    }

    /**
     * @see javax.faces.component.ActionSource#getActionListener()
     */
    public MethodBinding getActionListener()
    {
        return actionListenerBinding;
    }

    /**
     * @see javax.faces.component.ActionSource#getActionListeners()
     */
    public ActionListener[] getActionListeners()
    {
        return (ActionListener[]) getFacesListeners(ActionListener.class);
    }

    /**
     * @see javax.faces.component.ValueHolder#getConverter()
     */
    public Converter getConverter()
    {
        return converter;
    }

    /**
     * @see javax.faces.component.UIComponent#getFamily()
     */
    public String getFamily()
    {
        return COMPONENT_FAMILY;
    }

    /**
     * @see javax.faces.component.ValueHolder#getLocalValue()
     */
    public Object getLocalValue()
    {
        return value;
    }

    /**
     * <p>
     * The underlying model
     * </p>
     *
     * @return Returns the model.
     */
    public ScheduleModel getModel()
    {
        if (getValue() instanceof ScheduleModel)
        {
            return (ScheduleModel) getValue();
        }
        else
        {
            return new SimpleScheduleModel();
        }
    }

    /**
     * @see javax.faces.component.UIComponent#getRendersChildren()
     */
    public boolean getRendersChildren()
    {
        return true;
    }

    /**
     * <p>
     * The entry that was submitted on the last request
     * </p>
     *
     * @return Returns the submittedEntry.
     */
    public ScheduleEntry getSubmittedEntry()
    {
        return submittedEntry;
    }

    /**
     * @see javax.faces.component.ValueHolder#getValue()
     */
    public Object getValue()
    {
        return ScheduleUtil.getObjectProperty(this, value, "value", value);
    }

    /**
     * <p>
     * The last visible hour of the day
     * </p>
     *
     * @return Returns the visibleEndHour.
     */
    public int getVisibleEndHour()
    {
        return ScheduleUtil.getIntegerProperty(this, visibleEndHour,
                "visibleEndHour", 22);
    }

    /**
     * <p>
     * The first visible hour of the day
     * </p>
     *
     * @return Returns the visibleStartHour.
     */
    public int getVisibleStartHour()
    {
        return ScheduleUtil.getIntegerProperty(this, visibleStartHour,
                "visibleStartHour", 8);
    }

    /**
     * <p>
     * The last hour of the working day
     * </p>
     *
     * @return Returns the workingEndHour.
     */
    public int getWorkingEndHour()
    {
        return ScheduleUtil.getIntegerProperty(this, workingEndHour,
                "workingEndHour", 17);
    }

    /**
     * <p>
     * The first hour of the working day
     * </p>
     *
     * @return Returns the workingStartHour.
     */
    public int getWorkingStartHour()
    {
        return ScheduleUtil.getIntegerProperty(this, workingStartHour,
                "workingStartHour", 9);
    }

    /**
     * @see javax.faces.component.ActionSource#isImmediate()
     */
    public boolean isImmediate()
    {
        return ScheduleUtil.getBooleanProperty(this, immediate, "immediate",
                false);
    }

    /**
     * <p>
     * is this component read-only?
     * </p>
     *
     * @return is this component read-only
     */
    public boolean isReadonly()
    {
        return ScheduleUtil.getBooleanProperty(this, readonly, "readonly",
                false);
    }

    /**
     * @see javax.faces.component.UIComponent#queueEvent(javax.faces.event.FacesEvent)
     */
    public void queueEvent(FacesEvent event)
    {
        if (event instanceof ActionEvent)
        {
            if (isImmediate())
            {
                event.setPhaseId(PhaseId.APPLY_REQUEST_VALUES);
            }
            else
            {
                event.setPhaseId(PhaseId.INVOKE_APPLICATION);
            }
        }

        super.queueEvent(event);
    }

    /**
     * @see javax.faces.component.ActionSource#removeActionListener(javax.faces.event.ActionListener)
     */
    public void removeActionListener(ActionListener listener)
    {
        removeFacesListener(listener);
    }

    /**
     * @see javax.faces.component.StateHolder#restoreState(javax.faces.context.FacesContext,
     *      java.lang.Object)
     */
    public void restoreState(FacesContext context, Object state)
    {
        Object[] values = (Object[]) state;
        super.restoreState(context, values[0]);
        visibleStartHour = (Integer) values[1];
        visibleEndHour = (Integer) values[2];
        workingStartHour = (Integer) values[3];
        workingEndHour = (Integer) values[4];

        Boolean immediateState = (Boolean) values[5];

        if (immediateState != null)
        {
            setImmediate(immediateState.booleanValue());
        }

        Boolean readonlyState = (Boolean) values[6];

        if (readonlyState != null)
        {
            setReadonly(readonlyState.booleanValue());
        }

        value = values[7];
        actionListenerBinding = (MethodBinding) restoreAttachedState(context,
                values[8]);
        actionBinding = (MethodBinding) restoreAttachedState(context, values[9]);
      //  addActionListener(scheduleListener);
    }

    /**
     * @see javax.faces.component.StateHolder#saveState(javax.faces.context.FacesContext)
     */
    public Object saveState(FacesContext context)
    {
        removeActionListener(scheduleListener);

        Object[] values = new Object[10];
        values[0] = super.saveState(context);
        values[1] = visibleStartHour;
        values[2] = visibleEndHour;
        values[3] = workingStartHour;
        values[4] = workingEndHour;
        values[5] = immediate;
        values[6] = readonly;
        values[7] = value;
        values[8] = saveAttachedState(context, actionListenerBinding);
        values[9] = saveAttachedState(context, actionBinding);

        return values;
    }

    /**
     * @see javax.faces.component.ActionSource#setAction(javax.faces.el.MethodBinding)
     */
    public void setAction(MethodBinding action)
    {
        this.actionBinding = action;
    }

    /**
     * @see javax.faces.component.ActionSource#setActionListener(javax.faces.el.MethodBinding)
     */
    public void setActionListener(MethodBinding listener)
    {
        this.actionListenerBinding = listener;
    }

    /**
     * @see javax.faces.component.ValueHolder#setConverter(javax.faces.convert.Converter)
     */
    public void setConverter(Converter converter)
    {
        this.converter = converter;
    }

    /**
     * @see javax.faces.component.ActionSource#setImmediate(boolean)
     */
    public void setImmediate(boolean immediate)
    {
        this.immediate = Boolean.valueOf(immediate);
    }

    /**
     * <p>
     * The underlying model
     * </p>
     *
     * @param model The model to set.
     */
    public void setModel(ScheduleModel model)
    {
        setValue(model);
    }

    /**
     * <p>
     * is this component read-only?
     * </p>
     *
     * @param readonly is this component read-only
     */
    public void setReadonly(boolean readonly)
    {
        this.readonly = Boolean.valueOf(readonly);
    }

    /**
     * <p>
     * The entry that was submitted on the last request
     * </p>
     *
     * @param submittedEntry The submittedEntry to set.
     */
    public void setSubmittedEntry(ScheduleEntry submittedEntry)
    {
        this.submittedEntry = submittedEntry;
    }

    /**
     * @see javax.faces.component.ValueHolder#setValue(java.lang.Object)
     */
    public void setValue(Object value)
    {
        this.value = value;
    }

    /**
     * <p>
     * The last visible hour of the day
     * </p>
     *
     * @param visibleEndHour The visibleEndHour to set.
     */
    public void setVisibleEndHour(int visibleEndHour)
    {
        this.visibleEndHour = Integer.valueOf(visibleEndHour);
    }

    /**
     * <p>
     * The first visible hour of the day
     * </p>
     *
     * @param visibleStartHour The visibleStartHour to set.
     */
    public void setVisibleStartHour(int visibleStartHour)
    {
        this.visibleStartHour = Integer.valueOf(visibleStartHour);
    }

    /**
     * <p>
     * The last hour of the working day
     * </p>
     *
     * @param workingEndHour The workingEndHour to set.
     */
    public void setWorkingEndHour(int workingEndHour)
    {
        this.workingEndHour = Integer.valueOf(workingEndHour);
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * <p>
     * The first hour of the working day
     * </p>
     *
     * @param workingStartHour The workingStartHour to set.
     */
    public void setWorkingStartHour(int workingStartHour)
    {
        this.workingStartHour = Integer.valueOf(workingStartHour);
    }
}
//The End
