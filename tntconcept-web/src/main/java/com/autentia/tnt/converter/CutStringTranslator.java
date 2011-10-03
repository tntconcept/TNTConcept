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

package com.autentia.tnt.converter;

import com.autentia.tnt.util.FacesUtils;

import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.convert.*;

import org.apache.commons.lang.*;
import org.apache.commons.logging.*;

/**
 * Translator for Strings. Converts a String to a reduced-length String. This 
 * class is a translator, not a true converter. It can only be used to output 
 * String values, but not to convert input values.
 * @author ivan
 */
public class CutStringTranslator implements Converter {
  /** Logger */
  private static Log log = LogFactory.getLog(CutStringTranslator.class);
  
  /** Maximum length of reduced Strings */
  private static final int MAX_LENGTH = 30;
  
  /** */
  public Object getAsObject( FacesContext context, UIComponent component, String value )
      throws ConverterException {
    throw new ConverterException("CutStringTranslator cannot be used as an I/O converter: "+
                                  "only conversion from long to short String is supported, and not the other way");
  }
  
  /** */
  public String getAsString( FacesContext context, UIComponent component, Object value )
      throws ConverterException {
    if( value==null ){
      return null;
    } else {
      String val = value.toString();
      if( val.length()>MAX_LENGTH ){
        return val.substring(0,MAX_LENGTH) + "...";
      } else {
        return val;
      }
    }
  }
}
