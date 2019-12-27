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

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang.StringUtils;

/**
 * Validador de NIF
 * @author AUTENTIA
 */
public class NifValidator implements Validator 
{
	/**
	 * Efect�a el proceso de validaci�n
	 */
	public void validate(FacesContext context, 
			             UIComponent component, Object value) throws ValidatorException 
	{
		// Si el valor es null, lo transformamos en un valor vac�o
		String valor = StringUtils.defaultString((String)value);
		// el valor debe tener 9 posiciones
		valor=valor.toUpperCase();
		// Las 8 primeras posiciones deben ser n�meros y la �ltima una letra
		Pattern mask =  Pattern.compile("[0-9]{8,8}[A-Z]");
		Matcher matcher = mask.matcher(valor);
		if(!matcher.matches())
			throw new ValidatorException(new FacesMessage("El componente " + component.getId() + " no contiene un NIF válido. Las 8 primeras posiciones deben ser numéricas"));
		
		String dni=valor.substring(0,8);
		String digitoControl = valor.substring(8,9);
		// Calculamos la letra de control
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		int posicion_modulo = Integer.parseInt(dni)%23;
		String digitoControlCalculado = letras.substring(posicion_modulo,posicion_modulo+1);
		if(!digitoControl.equalsIgnoreCase(digitoControlCalculado))
			throw new ValidatorException(new FacesMessage("El componente " + component.getId() + " no contiene un NIF válido"));
		
	}
}
