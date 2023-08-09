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

import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.convert.*;

import org.apache.commons.lang3.*;
import org.apache.commons.logging.*;

/**
 * JAVA5 enums converter for JSF. Converts ordinal into Enum and vice versa.
 * @author ivan
 */

public class EnumConverter implements Converter {
  private static Log log = LogFactory.getLog(EnumConverter.class);
  
  public Object getAsObject( FacesContext context, UIComponent component, String value )
      throws ConverterException {
    Class clazz = component.getValueBinding("value").getType(context);
    log.debug("getAsObject - clazz="+clazz.getName());
    
    Enum ret = null;
    if( !StringUtils.isEmpty(value) ) {
      try {
        Enum[] values = (Enum[])clazz.getEnumConstants();
        ret = values[Integer.parseInt(value)];

      } catch( Exception e ) {
        log.warn("getAsObject - exception converting value '"+value+"' to "+clazz.getName(),e);
        throw new ConverterException( "Error converting value '"+value+"' to "+clazz.getName(), e );
      }
      
    }
    return ret;
  }
  
  public String getAsString( FacesContext context, UIComponent component, Object value )
      throws ConverterException {
    String ret = null;
    if( value!=null ){
      if( value instanceof Enum ){
        ret = Integer.toString(((Enum)value).ordinal());
      } else if( value instanceof String ){
        ret = value.toString();
      }
    }
    return ret;
  }
}
