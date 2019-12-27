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
package com.autentia.tnt.dao;

import java.text.*;
import java.util.*;

/**
 * Base class for all search criteria objects.
 * @author ivan
 */
public abstract class SearchCriteria 
{
	/** SQL date type formater */
	private static final Format dateFormat = new SimpleDateFormat("''yyyy-MM-dd''");

	/** SQL datetime type formater */
	private static final Format datetimeFormat = new SimpleDateFormat("''yyyy-MM-dd HH:mm:ss.SSS''");
	
	/** SQL date types */
	protected enum DateType {
		DATE, DATETIME
	}
	
	/**
	 * Return HQL statement to query by this criteria
	 * @return must return the HQL condition to make this query (an string of the type "WHERE ...")
	 */
	public abstract String getHQL();
	
	/**
	 * Return HQL statement arguments
	 * @return must return a list of objects used to populate the HQL query returned by SearchCriteria#getHQL()
	 */
	public abstract Object[] getArguments();
        
	/**
	 * Reset object (empty all criteria)
	 */
	public abstract void reset();
	
	/**
	 * Returns a string representation of the query. This representation is used in logs, not in the
	 * user interface (as it is not multilingual).
	 */
	public abstract String toString();
	
	/**
	 * Convert a Java date to an HQL expression
	 * @param date a Java date
	 * @param type SQL date type 
	 * @return an String with the format '1970-01-01 10:00:01.0' (including the quotes)
	 */
	protected String toHQL( Date date, DateType type ){
		switch( type ){
			case DATE:     return dateFormat.format(date);
			case DATETIME: return datetimeFormat.format(date);
		}
		return null;
	}
}
