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

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.shared_tomahawk.util.MessageUtils;

/**
 * JSF converter to change minutes into dates
 * @author ivan
 */
public class MinuteToHourConverter implements Converter {
  private static final String	REQUIRED_MESSAGE_ID	= "javax.faces.component.UIInput.REQUIRED";
  private static final String	CONVERSION_MESSAGE_ID	= "javax.faces.component.UIInput.CONVERSION";

  private static Log log = LogFactory.getLog(MinuteToHourConverter.class);
  
  public Object getAsObject( FacesContext context, UIComponent component, String value ) throws ConverterException {
    log.debug("getAsObject - value="+value);
    String allowDecimal= component.getAttributes().get("allowDecimal") == null ? "false"
            : component.getAttributes().get("allowDecimal").toString();
    if( value==null ){
      return null;
    } else {
        if (!Boolean.parseBoolean(allowDecimal)&&(value.contains(".")||value.contains(","))) {
            FacesMessage msg;
            msg = MessageUtils.getMessage(CONVERSION_MESSAGE_ID, new Object[]{
                    component.getId(), value});
            throw new ConverterException(msg);

        }else{
            try {
            return Integer.valueOf((int)(Double.parseDouble(value.replaceAll(",","."))*60));
            } catch( NumberFormatException e ){
                FacesMessage msg;
                if (value.equals("")){
                    msg = MessageUtils.getMessage(REQUIRED_MESSAGE_ID, new Object[]{
                            component.getId(), value});
                }else {
                    msg = MessageUtils.getMessage(CONVERSION_MESSAGE_ID, new Object[]{
                            component.getId(), value});
                }
                throw new ConverterException(msg,e);

            }
        }
    }
  }
  
  public String getAsString( FacesContext context, UIComponent component, Object value ) throws ConverterException {
    String allowDecimal= component.getAttributes().get("allowDecimal") == null ? "false"
            : component.getAttributes().get("allowDecimal").toString();
    if( value instanceof Integer ) {
      int val = (Integer) value;
      if (!Boolean.parseBoolean(allowDecimal)){
        return Integer.toString(val / 60);
      }else {
        return Double.toString( val/60.0 );
      }
    }else{
      return (String)value;
    }
  }
}
