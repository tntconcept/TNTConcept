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

package com.autentia.jsf.tag;

import com.autentia.jsf.component.AbcPager;
import com.autentia.jsf.component.html.HtmlAbcPager;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * This class initializes attributes common to all AbcPager components.
 * @author ivan
 */
public abstract class AbcPagerTag extends BaseTag
{
  private String action;
  private String value;
  private String actionListener;
  private boolean immediate;
  private boolean allowUnselect;

  public void release()
  {
    super.release();
    setAction(null);
    setValue(null);
    setActionListener(null);
    setImmediate(false);
    setAllowUnselect(false);
  }

  public void setAction( String action ){
    this.action = action;
  }
  
  public void setValue( String value ){
    this.value = value;
  }

  public void setActionListener(String actionListener){
    this.actionListener = actionListener;
  }

  public void setImmediate(boolean immediate){
    this.immediate = immediate;
  }

  public void setAllowUnselect( boolean allowUnselect ){
    this.allowUnselect = allowUnselect;
  }
      
  protected void setProperties( UIComponent component )
  {
    super.setProperties(component);
    
    FacesContext context = FacesContext.getCurrentInstance();
    Application application = context.getApplication();
    AbcPager pager = (AbcPager)component;
    
    pager.setImmediate(immediate);
    pager.setAllowUnselect(allowUnselect);
    if( action!=null ){
      pager.setAction( application.createMethodBinding(action,null) );
    }
    if( value!=null ){
      pager.setLetterBinding( application.createValueBinding(value) );
    }
    if( actionListener!=null ){
      pager.setActionListener( application.createMethodBinding(actionListener,new Class[]{ActionEvent.class}) );
    }
  }
}
