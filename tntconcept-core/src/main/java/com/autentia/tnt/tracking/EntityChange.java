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
import java.util.List;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.ITransferObject;

public class EntityChange implements ITransferObject {
	// Fields
	private Integer id;
	private String field;
	private String oldValue;
	private String newValue;
	private User user;
	private Date insertDate;
	private Integer ownerId;
	private Integer departmentId;
	private String entityName;
	private Integer entityId;
	/**
	 * Necesario para TrackingObject a la hora de poder meter varios cambios en el "Mapa" de cambios para un mismo campo (ej. relaciones)
	 */
	private String auxKey;
	
	
	public String getAuxKey() {
		return auxKey;
	}

	public void setAuxKey(String auxKey) {
		this.auxKey = auxKey;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	

	

	public List<Integer> getOwnersId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getUpdateDate() {
        return null;
    }

    public void setUpdateDate(Date updateDate) {
    }

	
	
	

	

}
