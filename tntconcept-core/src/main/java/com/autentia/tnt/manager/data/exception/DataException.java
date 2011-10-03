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

package com.autentia.tnt.manager.data.exception;

/**
 *
 * @author ivan
 */
public class DataException extends java.lang.Exception
{
  
  /**
   * Creates a new instance of <code>DataException</code> without detail message.
   */
  public DataException()
  {
  }
  
  /**
   * Constructs an instance of <code>DataException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public DataException(String msg)
  {
    super(msg);
  }
  
  /**
   * Constructs an instance of <code>DataException</code> with the specified detail message and cause.
   * @param msg the detail message.
   * @param cause the cause.
   */
  public DataException(String msg,Throwable cause)
  {
    super(msg,cause);
  }
}
