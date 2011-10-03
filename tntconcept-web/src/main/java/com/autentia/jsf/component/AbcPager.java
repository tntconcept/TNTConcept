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

package com.autentia.jsf.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.ActionSource;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponentBase;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.el.EvaluationException;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;

/**
 * This is a component that can be used to page through different results using
 * an alphabet. The pagination must be done by the backbean: this component
 * only allows to bind the selected letter to property in the backbean and also
 * invoke an action method when any of the letters is clicked.
 * @author ivan
 */
public class AbcPager extends UIComponentBase implements ActionSource
{
  public static final String COMPONENT_FAMILY = AbcPager.class.getName();
  public static final String COMPONENT_TYPE = AbcPager.class.getName();
  
  public static final char NULL_LETTER = '0';
  
  private ValueBinding letterBinding;
  private MethodBinding action;
  private MethodBinding actionListener;
  private boolean immediate;
  private boolean allowUnselect;
  
  private Character letter;
  
  protected AbcPager()
  {
  }
  
  public void restoreState( FacesContext context, Object state )
  {
    Object values[] = (Object[])state;
    super.restoreState(context, values[0]);
    this.letterBinding   = (ValueBinding)restoreAttachedState( FacesContext.getCurrentInstance(), values[1] );
    this.action          = (MethodBinding)restoreAttachedState( FacesContext.getCurrentInstance(), values[2] );
    this.actionListener  = (MethodBinding)restoreAttachedState( FacesContext.getCurrentInstance(), values[3] );
    this.immediate       = (Boolean)values[4];
    this.letter          = (Character)values[5];
    this.allowUnselect   = (Boolean)values[6];
  }
  
  public Object saveState(FacesContext context)
  {
    List<Object> state = new ArrayList<Object>();
    state.add( super.saveState(context) );
    state.add( saveAttachedState( FacesContext.getCurrentInstance(), letterBinding ) );
    state.add( saveAttachedState( FacesContext.getCurrentInstance(), action ) );
    state.add( saveAttachedState( FacesContext.getCurrentInstance(), actionListener ) );
    state.add( immediate );
    state.add( letter );
    state.add( allowUnselect );
    return state.toArray();
  }
  
  public String getFamily()
  {
    return COMPONENT_FAMILY;
  }
  
  public ValueBinding getLetterBinding()
  {
    return letterBinding;
  }
  
  public void setLetterBinding( ValueBinding letterBinding )
  {
    this.letterBinding = letterBinding;
  }
  
  public MethodBinding getAction()
  {
    return action;
  }
  
  public void setAction( MethodBinding action )
  {
    this.action = action;
  }
  
  public MethodBinding getActionListener()
  {
    return actionListener;
  }
  
  public void setActionListener( MethodBinding actionListener )
  {
    this.actionListener = actionListener;
  }
  
  public boolean isImmediate()
  {
    return immediate;
  }
  
  public void setImmediate( boolean immediate )
  {
    this.immediate = immediate;
  }
  
  public boolean isAllowUnselect()
  {
    return allowUnselect;
  }

  public void setAllowUnselect( boolean allowUnselect )
  {
    this.allowUnselect = allowUnselect;
  }
  
  public void addActionListener( ActionListener actionListener )
  {
    addFacesListener(actionListener);
  }
  
  public ActionListener[] getActionListeners()
  {
    return (ActionListener[])getFacesListeners(ActionListener.class);
  }
  
  public void removeActionListener( ActionListener actionListener )
  {
    removeFacesListener( actionListener );
  }
  
  public void decode(FacesContext context)
  {
    // Store local value for later model update
    Object param = context.getExternalContext().getRequestParameterMap().get( getClientId(context) );
    if( param!=null && param.toString().length()>0 )
    {
      // Store new submitted value
      letter = param.toString().charAt(0);

      // If a value was submitted queue an ActionEvent
      queueEvent( new ActionEvent(this) );
      
      // If immediate skip to renderResponse
      if( isImmediate() )
      {
        FacesContext.getCurrentInstance().renderResponse();
      }
    }
  }
  
  public void processUpdates(FacesContext context)
  {
    if( letterBinding != null ){
      letterBinding.setValue(FacesContext.getCurrentInstance(),getTranslatedLetter());
    }
  }
  
  public void queueEvent(FacesEvent event)
  {
    if (event != null && event instanceof ActionEvent)
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
  
  public void broadcast( FacesEvent event ) throws AbortProcessingException
  {
    super.broadcast(event);
    
    if (event instanceof ActionEvent)
    {
      FacesContext context = getFacesContext();
      
      MethodBinding actionListenerBinding = getActionListener();
      if (actionListenerBinding != null)
      {
        try
        {
          actionListenerBinding.invoke(context, new Object[] {event});
        }
        catch (EvaluationException e)
        {
          Throwable cause = e.getCause();
          if (cause != null && cause instanceof AbortProcessingException)
          {
            throw (AbortProcessingException)cause;
          }
          else
          {
            throw e;
          }
        }
      }
      
      ActionListener defaultActionListener = context.getApplication().getActionListener();
      if (defaultActionListener != null)
      {
        defaultActionListener.processAction((ActionEvent)event);
      }
    }
  }

  public Character getLetter()
  {
    if( letter==null && letterBinding!=null )
    {
      letter = (Character)letterBinding.getValue(FacesContext.getCurrentInstance());
    }
    return getTranslatedLetter();
  }

  public void setLetter(Character letter)
  {
    if( letter==null ){
      this.letter = NULL_LETTER;
    } else {
      this.letter = letter;
    }
  }

  private Character getTranslatedLetter()
  {
    if( letter==null || letter.equals(NULL_LETTER) ){
      return null;
    } else {
      return letter;
    }
  }
}
