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

package com.autentia.tnt.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DateValidator implements Validator 
{
	private static final Log log = LogFactory.getLog(DateValidator.class);
	
	/** */
	public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException 
	{
		log.info("validate - value = " + value );
		if( value!=null )
		{
			final Date date = (Date) value;
			// 0 is the year 1900
			if (date.getYear() < 0)
			{				
				throw new ValidatorException( new FacesMessage("La fecha debe tener el formato dd/mm/aaaa") );
			}
		}
	}
}
