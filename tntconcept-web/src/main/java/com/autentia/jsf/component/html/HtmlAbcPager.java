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

package com.autentia.jsf.component.html;

import com.autentia.jsf.component.AbcPager;
import com.autentia.jsf.renderer.html.HtmlAbcPagerRenderer;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UICommand;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

/**
 * This is the AbcPager component when used in an HTML environment. You should not
 * use this class, but AbcPager instead, unless you are sure you will only render
 * HTML responses.
 */
public class HtmlAbcPager extends AbcPager
{
  public static final String DEFAULT_RENDERER_TYPE = HtmlAbcPagerRenderer.class.getName();
  public static final String COMPONENT_TYPE = HtmlAbcPager.class.getName();
      
  private String styleClass;
  private String selectedStyleClass;
  private String unselectImage;
      
  public HtmlAbcPager()
  {
    setRendererType(DEFAULT_RENDERER_TYPE);
  }
  
  public void restoreState( FacesContext context, Object state )
  {
    Object values[] = (Object[])state;
    super.restoreState(context, values[0]);
    this.styleClass         = (String)values[1];
    this.selectedStyleClass = (String)values[2];
    this.unselectImage      = (String)values[3];
  }
  
  public Object saveState(FacesContext context)
  {
    List<Object> state = new ArrayList<Object>();
    state.add( super.saveState(context) );
    state.add( styleClass );
    state.add( selectedStyleClass );
    state.add( unselectImage );
    return state.toArray();
  }

  public String getStyleClass()
  {
    return styleClass;
  }
  
  public void setStyleClass(String styleClass)
  {
    this.styleClass = styleClass;
  }

  public String getSelectedStyleClass()
  {
    return selectedStyleClass;
  }
  
  public void setSelectedStyleClass(String selectedStyleClass)
  {
    this.selectedStyleClass = selectedStyleClass;
  }

  public String getUnselectImage()
  {
    return unselectImage;
  }

  public void setUnselectImage(String unselectImage)
  {
    this.unselectImage = unselectImage;
  }
}
