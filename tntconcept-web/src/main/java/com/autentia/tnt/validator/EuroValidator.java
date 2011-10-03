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

import java.math.*;

import javax.faces.application.*;
import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.validator.*;

import org.apache.commons.logging.*;

/**
 * 
 * @author ivan
 */
public class EuroValidator implements Validator 
{
	private static final Log log = LogFactory.getLog(EuroValidator.class);
	
	/** */
	public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException 
	{
		int scale = Integer.parseInt((String)component.getAttributes().get("scale"));
		int maxSize = Integer.parseInt((String)component.getAttributes().get("maxSize"));
		log.info("validate - value="+value );
		if( value!=null )
		{
			// Check if value is a BigDecimal
			if( !(value instanceof BigDecimal) ){
				log.info("validate - value is not a BigDecimal ("+value.getClass().getName()+")");
				throw new ValidatorException( new FacesMessage("Las cantidades monetarias deben ser de tipo BigDecimal") );
			}
			
			// Check if it has no more than scale decimal digits
			BigDecimal bd = (BigDecimal)value;
			if( bd.scale() > scale ){
				log.info("validate - value has more than 2 decimals ("+value+")");
				throw new ValidatorException( new FacesMessage("Las cantidades monetarias no pueden tener más de " + scale + " decimales") );
			}
			if (bd.precision() - bd.scale() > (maxSize - scale)){
				log.info("validate - value has more than " + maxSize + " numbers ("+value+")");
				throw new ValidatorException( new FacesMessage("Las cantidades monetarias no pueden tener más de " + maxSize + " cifras incluyendo los " + scale + " decimales") );
			}
		}
	}
}
