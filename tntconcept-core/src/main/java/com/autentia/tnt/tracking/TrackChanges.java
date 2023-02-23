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

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.autentia.tnt.manager.security.AuthenticationManager;


public class TrackChanges {

	private Tracking dto;
	
	private boolean trackingActive = false;

	private Map<String, EntityChange> currentChanges = new HashMap<String, EntityChange>();

	private Set<EntityChange> changesHistory = new HashSet<EntityChange>(0);
	
	public TrackChanges(Tracking dto) {
		this.dto=dto;
	}
	public void commitChanges() {
		this.getChangesHistory().addAll(this.currentChanges.values());
		
	}
	public void beginChangesTracking() {
		this.trackingActive = true;
		currentChanges = new HashMap<String, EntityChange>();
		
	}
	public void addEntityChange(String field, String oldValue, String newValue) {
		this.addEntityChange(field, oldValue, newValue, "");
		
	}
	public void addEntityChange(String field, String oldValue, String newValue,
			String keyAux) {
		EntityChange entityChange = getEntityChange(field, oldValue, newValue, keyAux);
		if(entityChange!=null){
			addChange(entityChange);
		}
	}
	
	public EntityChange getEntityChange(String field, String oldValue, String newValue){
		return getEntityChange(field, oldValue, newValue, null);
	}
	
	public EntityChange getEntityChange(String field, String oldValue, String newValue,
			String keyAux){
		if (this.isTrackingActive()) {
			if (!StringUtils.equals(oldValue, newValue)) {
				final EntityChange change = new EntityChange();
				change.setField(field);
				change.setOldValue(oldValue);
				change.setNewValue(newValue);
				change.setEntityName(dto.getClass().getCanonicalName());
				change.setEntityId(dto.getId());
				change.setUser(AuthenticationManager.getDefault()
						.getCurrentPrincipal().getUser());
				change.setAuxKey(StringUtils.defaultIfEmpty(keyAux, ""));
				return change;
			}
		}
		return null;
	}
	private void addChange(EntityChange change) {
		String key = change.getField() + change.getAuxKey();
		EntityChange prev = currentChanges.get(key);
		if (prev != null) {
			prev.setNewValue(change.getNewValue());
		} else {
			prev = change;
		}
		if (!StringUtils.equals(prev.getOldValue(), prev.getNewValue())) {
			currentChanges.put(key, prev);
		} else {
			currentChanges.remove(prev.getField());
		}
	}
	
	public Set<EntityChange> getChangesHistory() {
		return this.changesHistory;
	}
	public void setChangesHistory(Set<EntityChange> history) {
		this.changesHistory = history;
	}
	public boolean isTrackingActive() {
		return this.trackingActive;
	}
	public void setTrackingActive(boolean trackingActive) {
		this.trackingActive =trackingActive;
	}
	public  Map<String, EntityChange> getCurrentChanges() {
		return this.currentChanges;
		
	}
	public void setCurrentChanges(Map<String, EntityChange> changes) {
		this.currentChanges = changes;
	}
	public void clearCurrentChanges() {
		this.currentChanges.clear();
	}

}
