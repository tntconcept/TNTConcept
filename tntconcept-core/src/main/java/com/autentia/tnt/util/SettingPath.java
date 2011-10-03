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

/**
 * This class holds paths for Settings stored by SettingManager
 * 
 * @author ivan
 */
public final class SettingPath {

	public static final String	BITACORE_LAST_BILLABLE					= "bitacore.last.billable";
	public static final String	BITACORE_LAST_ROLEID					= "bitacore.last.roleId";
	public static final String	BITACORE_PREFERRED_THEME				= "bitacore.preferred.theme";
	public static final String	BITACORE_PREFERRED_MODE					= "bitacore.preferred.mode";
	public static final String	BITACORE_PREFERRED_DISPLAY_HOUR_FROM	= "bitacore.preferred.display.hourFrom";
	public static final String	BITACORE_PREFERRED_DISPLAY_HOUR_UNTIL	= "bitacore.preferred.display.hourUntil";
	public static final String	BITACORE_PREFERRED_DAY_HOUR_START		= "bitacore.preferred.day.hourStart";
	public static final String	BITACORE_PREFERRED_DAY_HOURS			= "bitacore.preferred.day.hours";
	public static final String	BITACORE_PREFERRED_HEADER_FORMAT        = "bitacore.preferred.headerFormat";
	public static final String	OBJECTIVE_LAST_PROJECTID				= "objective.last.projectId";
	public static final String  GENERAL_PREFERRED_LIST_SIZE 			= "general.preferred.listSize";
	public static final String  GENERAL_PREFERRED_LOCALE		 		= "general.preferred.locale";
	
	/** Nobody should construct objects of this type */
	private SettingPath() {
	}

}
