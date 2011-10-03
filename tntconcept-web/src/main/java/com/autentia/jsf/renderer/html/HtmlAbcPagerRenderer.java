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

package com.autentia.jsf.renderer.html;

import com.autentia.jsf.component.AbcPager;
import com.autentia.jsf.component.html.HtmlAbcPager;
import com.autentia.jsf.util.HtmlUtil;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.render.Renderer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.component.UIComponent;
import java.io.IOException;

/**
 * The HTML renderer for the AbcPager component.
 */
public class HtmlAbcPagerRenderer extends Renderer
{
  private static final Map<Locale,String> LETTERS = new HashMap<Locale,String>();
  private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
  
  static
  {
    LETTERS.put( new Locale("es"), "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ" );
    LETTERS.put( Locale.ENGLISH, "ABCDEFGHIJKLMNOPQRSTUVWXYZ" );
    
    if( LETTERS.get(DEFAULT_LOCALE)==null )
    {
      throw new LinkageError("Default locale for HtmlAbcPagerRenderer has not been added to LETTERS map");
    }
  }
  
  public void encodeEnd(FacesContext facesContext, UIComponent uiComponent) throws IOException
  {
    // Skip not renderer components
    if( !uiComponent.isRendered() )
    {
      return;
    }
    
    // Get useful stuff
    FacesContext ctx = FacesContext.getCurrentInstance();
    ResponseWriter writer = facesContext.getResponseWriter();
    HtmlAbcPager pager = (HtmlAbcPager) uiComponent;
    Locale locale = new Locale( facesContext.getViewRoot().getLocale().getLanguage() );
    String clientId = pager.getClientId(ctx);
    Character selectedLetter = pager.getLetter();
    
    // Get alphabet for current locale
    String letters = LETTERS.get(locale);
    if( letters==null )
    {
      letters = LETTERS.get(DEFAULT_LOCALE);
    }
    
    // Render a hidden input to store selected letter
    writer.write("<input type='hidden' name='"+clientId+"' id='"+clientId+"' value=''>");

    // Render a javascript function to send the 
    String jsFunc = HtmlUtil.toValidJsIdent( clientId );
    writer.write( "<script>\n" );
    writer.write( "function "+jsFunc+"( letter ){\n" );
    writer.write( "  var ctl = document.all['"+clientId+"'];\n");
    writer.write( "  ctl.value= (letter==null) ? '"+AbcPager.NULL_LETTER+"' : letter;\n");
    writer.write( "  ctl.form.submit();\n");
    writer.write( "}\n" );
    writer.write( "</script>\n" );

    // Render unselect icon
    if( pager.isAllowUnselect() )
    {
      String styleClass = (selectedLetter==null)
                            ? pager.getSelectedStyleClass()
                            : pager.getStyleClass();
      writer.write("<a href='#' class='"+styleClass+"' onclick='"+jsFunc+"(null)'>" );
      writer.write("<img style='vertical-align: top;' border='0' src='"+ctx.getExternalContext().getRequestContextPath()
                      +pager.getUnselectImage()+"'></img>");
      writer.write("</a>\n" );
    }
    
    // Render letters as links
    for( Character letter : letters.toCharArray() )
    {
      String styleClass = letter.equals( selectedLetter )
                            ? pager.getSelectedStyleClass()
                            : pager.getStyleClass();

      writer.write("<a href='#' class='"+styleClass+"' onclick='"+jsFunc+"(\""+letter+"\")'>" );
      writer.write(letter);
      writer.write("</a>\n" );
    }
  }
}
