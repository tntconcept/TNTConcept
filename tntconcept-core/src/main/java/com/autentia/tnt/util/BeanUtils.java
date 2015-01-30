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

package com.autentia.tnt.util;

import com.autentia.tnt.dao.ITransferObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtilsBean;

public class BeanUtils
{
  /**
   * Devuelve la pila de una excepcion
   * @param ex la excepcion
   * @return la pila de la excepcion
   */
  public static String getErrorDesc( Throwable ex )
  {
    StringWriter sw=new StringWriter();
    ex.printStackTrace(new PrintWriter(sw)); //NOSONAR

    return sw.toString();
  }

	/**
	 * Copies one ITransferObject into another (excluding the id property)
	 * @param copyAccountEntry 
	 * @param periodicalAccountEntry 
	 */
	public static void copyTransferObject(ITransferObject source, ITransferObject dest )
	{
		try
		{
			BeanUtilsBean.getInstance().copyProperties(dest,source);
		} 
		catch (IllegalAccessException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		} 
		catch (InvocationTargetException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		}
	}
}
