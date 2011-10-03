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

import com.autentia.tnt.bean.account.AccountEntryBean;
import com.autentia.tnt.businessobject.AccountEntryGroup;
import com.autentia.tnt.businessobject.AccountEntryType;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.FacesUtils;

/**
 * 
 * @author jclopez
 */
public class AccountEntryValidator implements Validator 
{
	private static final Log log = LogFactory.getLog(EuroValidator.class);
	
	/** */
	public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException 
	{
		log.info("validate - value = " + value );
		if ( value!= null )
		{
			// Check if value is a BigDecimal
			if( !(value instanceof BigDecimal) ){
				log.info("validate - value is not a BigDecimal ("+value.getClass().getName()+")");
				throw new ValidatorException( new FacesMessage("Las cantidades monetarias deben ser de tipo BigDecimal") );
			}
			
			// Check if it has no more than 2 decimal digits
			BigDecimal bd = (BigDecimal)value;
			if( bd.scale()>2 ){
				log.info("validate - value has more than 2 decimals ("+value+")");
				throw new ValidatorException( new FacesMessage("Las cantidades monetarias no pueden tener mas de dos decimales") );
			}
			AccountEntryBean bean = (AccountEntryBean) FacesUtils.getBean("accountEntryBean");
			AccountEntryType type = bean.getType();
			AccountEntryGroup group = type.getGroup();
			
			if (group.getId() == ConfigurationUtil.getDefault().getCostId())
			{
				if ( bd.signum() != -1 )
				{
					log.info("validate - value cost is negative ("+value+")");
					throw new ValidatorException( new FacesMessage("La cantidad debe ser negativa") );
				}
			}
			if (group.getId() == ConfigurationUtil.getDefault().getIncomeId())
			{
				if ( bd.signum() != 1 )
				{
					log.info("validate - value incom is positive ("+value+")");
					throw new ValidatorException( new FacesMessage("La cantidad debe ser positiva") );
				}
			}
			
		}
	}
}

