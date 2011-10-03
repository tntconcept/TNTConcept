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
 *
 */
package com.autentia.tnt.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Managed bean to get today's date.
 * @author ivan
 */
public class DateTimeBean extends BaseBean
{
  private static final long serialVersionUID = 2389350693480920613L;
  
  private final DateFormat fmt = new SimpleDateFormat("EEEE, d-MMMM-yyyy");
  
  /**
   * @return today's date
   * @throws ParseException
   */
  public String getDate() throws ParseException
  {
    return fmt.format( new Date() );
  }
}
