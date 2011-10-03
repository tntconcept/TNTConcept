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

/**
 * XmlUtil.java
 */
package com.autentia.tnt.util;

/**
 * Clase con utilidades generales para la manipulaci�n de documentos XML
 * @author AUTENTIA
 *
 */
public class XmlUtil {

	/**
	 * Devuelve un valor en su representaci�n XML
	 *
	 * @param valor valor a transformar
	 *
	 * @return Representaci�n en XML
	 */
	public static String toXML(String valor)
	{
		  StringBuffer str=new StringBuffer();
		  for (int i=0;i<valor.length();++i)
		    	str.append(toXML(valor.charAt(i)));
		  return str.toString();
	}

	/**
	 * Convierte un caracter a su representaci�n en XML
	 *
	 * @param valor car�cter a transformar
	 *
	 * @return Representaci�n en XML
	 */
	public static String toXML(char valor)
	{
		  if (valor=='&')
		   	 return "&amp;";
		  else if (valor=='<')
		   	 return "&lt;";
		  else if (valor=='>')
		    	return "&gt;";
		  else
		    	return valor+"";
	}
}
