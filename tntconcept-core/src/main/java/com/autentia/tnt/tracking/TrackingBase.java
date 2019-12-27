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

public abstract class TrackingBase implements Tracking {

	private TrackChanges trackingService = new TrackChanges(this);


	public void commitCurrentChanges() {
		trackingService.commitChanges();
	}

	public void beginChangesTracking() {
		trackingService.beginChangesTracking();
	}

	public void addEntityChange(String field, String oldValue, String newValue) {
		trackingService.addEntityChange(field, oldValue, newValue);
		
	}

	public void addEntityChange(String field, String oldValue, String newValue,
			String keyAux) {
		trackingService.addEntityChange(field, oldValue, newValue,keyAux);
		
	}

	public Set<EntityChange> getChangesHistory() {
		return trackingService.getChangesHistory();
	}

	public boolean isTrackingActive() {
		return trackingService.isTrackingActive();
	}

	public void setChangesHistory(Set<EntityChange> history) {
		trackingService.setChangesHistory(history);
		
	}

	public void setTrackingActive(boolean trackingActive) {
		trackingService.setTrackingActive(trackingActive);
		
	}

	public  Map<String, EntityChange> getCurrentChanges() {
		return trackingService.getCurrentChanges();
	}

	public void setCurrentChanges( Map<String, EntityChange> changes) {
		trackingService.setCurrentChanges(changes);
		
	}

	public EntityChange getEntityChange(String field, String oldValue,
			String newValue) {
		return trackingService.getEntityChange(field, oldValue, newValue);
	}

	public EntityChange getEntityChange(String field, String oldValue,
			String newValue, String keyAux) {
		return trackingService.getEntityChange(field, oldValue, newValue, keyAux);
	}

	public void clearCurrentChanges() {
		trackingService.clearCurrentChanges();
		
	}

	public abstract Integer getId();

	public void notifyChanges() {
		// do nothing
		
	}

}
