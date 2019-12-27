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

package com.autentia.tnt.xml;

import org.w3c.dom.*;

/**
 * Funcionalidad para facilitar el manejo de DOM (Document Object Model)
 * @author Carlos García. Autentia
 */
public class DOMUtils {

	/**
	 * @param  node Nodo sobre el que queremos consultar
	 * @param  name Nombre de la propiedad
	 * @return Devuelve el valor de una propiedad (atributo) o null en caso de que no exista.
	 */
	public static String getAttribute(org.w3c.dom.Node node, String name){
		return DOMUtils.getAttribute(node, name, null);
	}
	
	/**
	 * @param  node Nodo sobre el que queremos consultar
	 * @param  name Nombre de la propiedad
	 * @param  def  Valor por defecto
	 * @return Devuelve el valor de una propiedad (atributo) o 'def' en caso de que no exista.
	 */	
	public static String getAttribute(org.w3c.dom.Node node, String name, String def){
		Node	attr  = node.getAttributes().getNamedItem(name);
		String	value = def;
		if (attr != null){
			value = attr.getNodeValue();
		}
		return value;
	}	
	
	
}
