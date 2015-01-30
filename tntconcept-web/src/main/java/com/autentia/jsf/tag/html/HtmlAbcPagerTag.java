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

package com.autentia.jsf.tag.html;

import com.autentia.jsf.component.AbcPager;
import com.autentia.jsf.component.html.HtmlAbcPager;
import com.autentia.jsf.tag.AbcPagerTag;
import java.awt.event.ActionEvent;
import javax.faces.application.Application;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

/**
 * This class initializes and creates HtmlAbcPager components.
 * @author ivan
 */
public class HtmlAbcPagerTag extends AbcPagerTag
{
  private String styleClass;
  private String selectedStyleClass;
  private String unselectImage;
  
  public void release()
  {
    super.release();
    setStyleClass(null);
    setSelectedStyleClass(null);
    setUnselectImage(null);
  }
  
  public void setStyleClass( String styleClass ){
    this.styleClass = styleClass;
  }

  public void setSelectedStyleClass( String selectedStyleClass ){
    this.selectedStyleClass = selectedStyleClass;
  }
  
  public void setUnselectImage( String unselectImage ){
    this.unselectImage = unselectImage;
  }
  
  public String getComponentType(){
    return HtmlAbcPager.COMPONENT_TYPE;
  }
  
  public String getRendererType(){
    return HtmlAbcPager.DEFAULT_RENDERER_TYPE;
  }
  
  protected void setProperties( UIComponent component )
  {
    super.setProperties(component);
    
//    FacesContext context = FacesContext.getCurrentInstance();
//    Application application = context.getApplication();
    HtmlAbcPager pager = (HtmlAbcPager)component;
    
    pager.setStyleClass( styleClass );
    pager.setSelectedStyleClass( selectedStyleClass );
    pager.setUnselectImage( unselectImage );
  }
  


}
