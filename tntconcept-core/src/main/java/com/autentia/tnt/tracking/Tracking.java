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

package com.autentia.tnt.tracking;

import java.util.Map;
import java.util.Set;



public interface Tracking {

	
	

	public Set<EntityChange> getChangesHistory();

	public Integer getId();

	public void setChangesHistory(Set<EntityChange> history) ;

	public boolean isTrackingActive() ;

	public void setTrackingActive(boolean trackingActive) ;

	public void addEntityChange(String field, String oldValue, String newValue);
	
	public void addEntityChange(String field, String oldValue, String newValue, String keyAux) ;

//	public EntityChange getEntityChange(String field, String oldValue, String newValue);
//	
//	public EntityChange getEntityChange(String field, String oldValue, String newValue,
//			String keyAux);

	public void commitCurrentChanges() ;
	
	public void beginChangesTracking();
	
	public void clearCurrentChanges();

	public  Map<String, EntityChange> getCurrentChanges();

	public void setCurrentChanges( Map<String, EntityChange> changes);
	
	public void notifyChanges();
	
}
