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
 * DataAccException.java
 */
package com.autentia.tnt.dao;
/**
 * Clase padre de todas las excepciones que se produzcan en la capa de acceso a datos
 * 
 * @author <a href="www.autentia.com">AUTENTIA</a>
 */
public class DataAccException extends RuntimeException{
	/**
	 * Construye una excepci�n con mensaje
	 * 
	 * @param msg Mensaje adicional de error
	 */
	public DataAccException(String msg)
	{
		super(msg);
	}
	
	/**
	 * Construye una excepci�n con un mensaje y la excepci�n original
	 * 
	 * @param msg Mensaje adicional de error
	 * @param e Excepci�n original
	 */
	public DataAccException(String msg,Throwable e)
	{
		super(msg,e);
	}
	
	
}
