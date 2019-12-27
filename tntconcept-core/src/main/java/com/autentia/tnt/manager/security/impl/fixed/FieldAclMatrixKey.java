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

package com.autentia.tnt.manager.security.impl.fixed;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.autentia.tnt.dao.ITransferObject;


class FieldAclMatrixKey {
	private Class<? extends ITransferObject> type;
	private int roleId;
	private String field;
	
	public FieldAclMatrixKey(Class<? extends ITransferObject> type, int roleId, String field) {
		this.type = type;
		this.roleId = roleId;
		this.field = field;
	}
	
	public Class<? extends ITransferObject> getType() {
		return type;
	}
	
	public void setType(Class<? extends ITransferObject> type) {
		this.type = type;
	}
	
	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public boolean equals(Object obj) {
		try {
			FieldAclMatrixKey key = (FieldAclMatrixKey)obj;
			EqualsBuilder eqb = new EqualsBuilder();
			eqb.append(this.getRoleId(), key.getRoleId());
			eqb.append(this.getField(), key.getField());
			eqb.append(this.getClass(), key.getClass());
			return eqb.isEquals();
		} catch (Exception e) {
			// obj is not an FieldAclMatrixKey instance
			return false;
		}
	}

	public int hashCode() {
			HashCodeBuilder hcb = new HashCodeBuilder();
			hcb.append(this.getRoleId());
			hcb.append(this.getField());
			hcb.append(this.getClass());
			return hcb.toHashCode();
	}
}
