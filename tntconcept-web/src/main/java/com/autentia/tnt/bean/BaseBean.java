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

/*
 * BaseBean.java
 */
package com.autentia.tnt.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.util.BeanUtils;
import com.autentia.tnt.util.FacesUtils;

public class BaseBean {
	
	/** Numero de serie	 */
	private static final long serialVersionUID = 6390940830550036297L;
	
	/** etiqueta rowid */
	public static final String	ROW_ID	= "rowid";
	
	/** Logger de esta clase */
	protected final Log logger = LogFactory.getLog(this.getClass());	
	
	/**
	 * Constructor
	 */
	public BaseBean() {
	}
	
	/**
	 * Inicializa los beans
	 */ 
	protected void init() {
	}
	
	/**
	 * Gestion de errores. TODO: completar
	 */
	public String getReturnError(String msg,Throwable ex){
		printError(msg,ex);
		return NavigationResults.GO_ERROR;
	}
	
	/**
	 * Imprime un error
	 */
	public void printError(String msg,Throwable ex){
		String pila = BeanUtils.getErrorDesc(ex);
		this.logger.error(pila,ex);
		StringBuffer totalError= new StringBuffer(msg);
		totalError.append("<br><br>");
		totalError.append(pila);
		FacesUtils.addErrorMessage(null,totalError.toString()+ ": Intra error.");
	}
	
	/**
	 * Método que se ejecuta después de Save() y que cada implementación 
	 * podrá sobre escribir con funcionalidad adicional.
	 * @param result
	 * @return
	 */
	public String doAfterSave(String result) {
		return result;
	}

	/**
	 * Método que se ejecuta antes de Save() y que cada implementación 
	 * podrá sobre escribir con funcionalidad adicional.
	 * @param result
	 * @return
	 */
	public String doBeforeSave() {
		return null;
	}

}
