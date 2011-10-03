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

import com.autentia.tnt.dao.IDataAccessObject;
import com.autentia.tnt.dao.ITransferObject;

import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.convert.*;

import org.apache.commons.lang.*;
import org.apache.commons.logging.*;

/**
 * Organization converter for JSF. Converts id into Organization and Organization
 * into id.
 * @author ivan
 */
public class EntityConverter implements Converter {
  /** Logger */
  private static Log log = LogFactory.getLog(EntityConverter.class);
  
  /** DAO class suffix */
  private static final String SUFFIX = "DAO";
  
  /** DAO class prefix */
  private static final String PREFIX = "com.autentia.tnt.dao.hibernate.";

  // TODO: remove this attribute from here and define a custom tag to initialize this converter
  /** Attribute to set in converter's component to force the type of field used in bean  */
  private static final String ATT_VALUE_CLASS = "com.autentia.tnt.converter.EntityConverter.valueClass";
  
  /** */
  public Object getAsObject( FacesContext context, UIComponent component, String value )
  throws ConverterException {
    try {
      // Get id
      Integer id;
      try{
        id = Integer.parseInt(value);
      } catch( NumberFormatException e ){
        id = null;
      }
      
      if( id!=null ){
        // Get field class from component's attribute or guess it from value binding
        String valueClass = (String)component.getAttributes().get( ATT_VALUE_CLASS );
        Class clazz = (valueClass!=null)
                          ? Class.forName(valueClass)
                          : component.getValueBinding("value").getType(context);
        log.debug("getAsObject - clazz="+clazz.getName());

        // Compose manager class name
        String mgrClassName = clazz.getSimpleName() + SUFFIX;
        IDataAccessObject mgr = (IDataAccessObject) Class.forName(PREFIX+mgrClassName).newInstance();

        // Retrieve object from database
        return mgr.getById( id );
      } else {
        return null;
      }
    } catch( Exception e ){
      log.warn("getAsObject - exception",e);
      throw new ConverterException("Exception converting entity id to object",e);
    }
  }
  
  /** */
  public String getAsString( FacesContext context, UIComponent component, Object value )
  throws ConverterException {
    if( value instanceof String ){
      return (String)value;
    }
    
    try {
      ITransferObject obj = (ITransferObject)value;
      return (obj==null) ? null : obj.getId().toString();
    } catch( Exception e ){
      log.warn("getAsObject - exception",e);
      throw new ConverterException("Exception converting entity object to id",e);
    }
  }
}
