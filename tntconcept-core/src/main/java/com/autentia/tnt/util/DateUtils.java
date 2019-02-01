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

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
	 * returns a java.util.Date with hours and minutes passed
	 * as argument. rest of date fields are despreciable.
	 * 
	 * @param hour hour to set
	 * @param minutes minute to set
	 * @return date with hour and minutes setted.
	 */
	public static Date timeToDate (int hour, int minutes) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.clear();
		
		calendar.set(Calendar.HOUR, hour);
		calendar.set(Calendar.MINUTE, minutes);
		
		return calendar.getTime();
	}
	
	
	/**
	 * Return a java.util.Date with hours and minutes minimum in a Day
	 * @param d
	 * @return
	 */
	
	public static Date minHourInDate(Date d) {
		Calendar calendar = Calendar.getInstance();

		calendar.clear();
		calendar.setTime(d);
		
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));
		
		
		return calendar.getTime();
		
	}
	
	
	
	
	public static Date addDays2Date(Date d, int days) {
		Calendar calendar = Calendar.getInstance();

		calendar.clear();
		calendar.setTime(d);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}
	
	
	
	/**
	 * Return a java.util.Date with hours and minutes minimum in a Day
	 * @param d
	 * @return
	 */
	
	public static Date maxHourInDate(Date d) {
		Calendar calendar = Calendar.getInstance();

		calendar.clear();
		calendar.setTime(d);
		
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));
		
		
		return calendar.getTime();
		
	}

	public static Date getFirstDayOfMonth(Date date) {
		
		Calendar firstDayOfMonth = Calendar.getInstance();
		firstDayOfMonth.setTime(DateUtils.minHourInDate(date));
		firstDayOfMonth.set(Calendar.DAY_OF_MONTH, firstDayOfMonth.getMinimum(Calendar.DAY_OF_MONTH));
		
		return firstDayOfMonth.getTime();
	}
	
	public static Date getLastDayOfMonth(Date date) {

		Calendar lastDayOfMonth = Calendar.getInstance();
		lastDayOfMonth.setTime(DateUtils.maxHourInDate(date));
		lastDayOfMonth.set(Calendar.DAY_OF_MONTH, lastDayOfMonth.getMaximum(Calendar.DAY_OF_MONTH));
		
		return lastDayOfMonth.getTime();
	}

	public static Date getFirstDayOfYear(Date date) {
		
		Calendar firstDayOfYear = Calendar.getInstance();
		firstDayOfYear.setTime(DateUtils.getFirstDayOfMonth(date));
		firstDayOfYear.set(Calendar.MONTH, firstDayOfYear.getMinimum(Calendar.MONTH));
		
		return firstDayOfYear.getTime();
	}


	public static Date getLastDayOfYear(Date date) {
		
		Calendar lastDayOfYear = Calendar.getInstance();
		lastDayOfYear.setTime(DateUtils.getLastDayOfMonth(date));
		lastDayOfYear.set(Calendar.MONTH, lastDayOfYear.getMaximum(Calendar.MONTH));
		
		return lastDayOfYear.getTime();
	}
	
}
