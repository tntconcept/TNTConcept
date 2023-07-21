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

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.lang3.StringUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Conversor de fechas a año.
 * @author AUTENTIA
 */

public class Date2YearConverter implements Converter
{
  /**
   * Devuelve la representaci�n Date de JAVA de un valor
   */
  public Object getAsObject(FacesContext context, UIComponent component, String value)
  throws ConverterException
  {
    DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    Date fecha = null;
    
    if(!StringUtils.isEmpty(value))
    {
      try
      {
        fecha = fmt.parse(value);
      }
      catch(Exception pe)
      {
        String mensaje = "El valor no se corresponde con una fecha válida";
        throw new ConverterException(new FacesMessage(mensaje));
      }
    }
    return fecha;
  }
  /**
   * Devuelve la representacion cadena de una fecha
   */
  public String getAsString(FacesContext context, UIComponent component, Object value)
  throws ConverterException
  {
    DateFormat fmt = new SimpleDateFormat("yyyy");
    String valor = null;
    if(value==null)
    {
      valor = "";
    }
    else
    {
      valor = fmt.format( value );
    }
    return valor;
  }
  
}
