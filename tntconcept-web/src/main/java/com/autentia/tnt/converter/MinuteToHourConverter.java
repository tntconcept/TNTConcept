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

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * JSF converter to change minutes into dates
 * @author ivan
 */
public class MinuteToHourConverter implements Converter {
  private static Log log = LogFactory.getLog(MinuteToHourConverter.class);
  
  public Object getAsObject( FacesContext context, UIComponent component, String value ) throws ConverterException {
    log.debug("getAsObject - value="+value);
    
    if( value==null ){
      return null;
    } else {
      try {
        double val = Double.parseDouble(value.replaceAll(",","."));
        return new Integer((int)(val*60));
      } catch( NumberFormatException e ){
        throw new ConverterException("Error converting minutes: "+value,e);
      }
    }
  }
  
  public String getAsString( FacesContext context, UIComponent component, Object value ) throws ConverterException {
    if( value instanceof Integer ){
      int val = ((Integer)value).intValue();
      return Double.toString( val/60.0 );
    } else {
      return (String)value;
    }
  }
}
