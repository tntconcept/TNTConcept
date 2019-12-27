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
package com.autentia.jsf.component.ocupation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This interface represents must be implemented to a class
 * to represent vacations of 
 * 
 * @author german
 *
 */
public abstract class OcupationModel {

	private final Map<Number, OcupationEntry> idOcupationMap = new HashMap<Number, OcupationEntry>();
	private final Collection<OcupationEntry> ocupationEntries = new ArrayList<OcupationEntry>();
	
	private static final boolean isBetweenRange(Date aDay, OcupationEntry entry) {
		Calendar cals = Calendar.getInstance();
		Calendar cale = Calendar.getInstance();
		
		cals.setTime(entry.getStart());
		cale.setTime(entry.getEnd());
		
		cals.set(Calendar.HOUR_OF_DAY, cals.getMinimum(Calendar.HOUR));
		cals.set(Calendar.MINUTE, cals.getMinimum(Calendar.MINUTE));
		cals.set(Calendar.SECOND, cals.getMinimum(Calendar.SECOND));
		cals.set(Calendar.MILLISECOND, cals.getMinimum(Calendar.MILLISECOND));
		
		cale.set(Calendar.HOUR_OF_DAY, cale.getMaximum(Calendar.HOUR));
		cale.set(Calendar.MINUTE, cale.getMaximum(Calendar.MINUTE));
		cale.set(Calendar.SECOND, cale.getMaximum(Calendar.SECOND));
		cale.set(Calendar.MILLISECOND, cale.getMaximum(Calendar.MILLISECOND));
		
		return (aDay.after(cals.getTime()) && aDay.before(cale.getTime())) || (aDay.compareTo(cals.getTime())==0) || (aDay.compareTo(cale.getTime())==0) ;
	}
	
	public final void addOcupationEntries(Collection<OcupationEntry> entries) {
		addOcupationEntries(entries, false);
	}
	
	public final void addOcupationEntries(Collection<OcupationEntry> entries, boolean init) {
		if (init) {
			idOcupationMap.clear();
			ocupationEntries.clear();
		}
		
		for (OcupationEntry entry : entries) {
			idOcupationMap.put(entry.getId(), entry);
		}
		
		ocupationEntries.addAll(entries);
	}
	
	public final void addOcupationEntry(OcupationEntry entry) {
		
		idOcupationMap.put(entry.getId(), entry);
		
		ocupationEntries.add(entry);
	}
	
	public final Collection<OcupationEntry> getOcupationEntries(Date day){
		
		Collection<OcupationEntry> toReturn = new ArrayList<OcupationEntry>();
		
		for (OcupationEntry entry : ocupationEntries) {
			if (isBetweenRange(day, entry)) {
				toReturn.add(entry);
			}
		}
		
		return (toReturn.size() == 0) ? null : toReturn;
	}
	public final OcupationEntry getOcupationEntry(Number id){
		return idOcupationMap.get(id);
	}
	
	
		
	
	
}
