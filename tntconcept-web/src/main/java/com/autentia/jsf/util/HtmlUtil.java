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

package com.autentia.jsf.util;

/**
 *
 * @author ivan
 */
public abstract class HtmlUtil
{
  /**
   * Convert a string to a valid Javascript identifier. Useful to convert 
   * component ids into JS identifiers.
   * @param val value to convert
   * @return converted value
   */
  public static String toValidJsIdent( String val )
  {
    StringBuilder ret = new StringBuilder();

    ret.append('_');
    for( char c : val.toCharArray() )
    {
      if( Character.isLetterOrDigit(c) )
      {
        ret.append(c);
      }
      else
      {
        ret.append("_");
      }
    }
    
    return ret.toString();
  }
  
}
