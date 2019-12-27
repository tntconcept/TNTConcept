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

package com.autentia.jsf.component.ocupation;

import java.util.Date;


/**
 * Represents each entry of model. An entry may paint
 * many days of component
 * 
 * @author german
 *
 */
public interface OcupationEntry {

	/**
	 * id of this entry
	 * @return unique id
	 */
	public Number getId();
	
	/**
	 * start date of range (included)
	 * @return start date of range
	 */
	public Date getStart();
	
	/**
	 * end day of range (included)
	 * @return end day of range
	 */
	public Date getEnd();
	
	
	/**
	 * Description of entry
	 * @return
	 */
	public String getDescription();
	
	/**
	 * entry is vacances
	 * @return
	 */
	public boolean isVacances();
	
	
	/**
	 * entry holiday
	 * @return
	 */
	public boolean isHoliday();
	
	
	/**
	 * Associated object
	 * @return asociated object
	 */
	public Object getAssociatedObject();
	
	
	/**
	 * duration of task
	 * @return duration of task in minutes
	 */
	public int getDuration();
}
