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

package com.autentia.tnt.manager.workflow;


public class Field {

	public static enum Permission {NONE,VIEW,EDIT};
	
	private String name;
	private Permission permAdmin = Permission.NONE;
	private Permission permSuper = Permission.NONE;
	private Permission permStaff = Permission.NONE;
	private Permission permUser = Permission.NONE;
	private Permission permCli = Permission.NONE;
	private Permission projectManager = Permission.NONE;
	
	public Field() {
		
	}
	
	public Field (String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Permission getPermAdmin() {
		return permAdmin;
	}
	public void setPermAdmin(Permission permAdmin) {
		this.permAdmin = permAdmin;
	}
	public Permission getPermSuper() {
		return permSuper;
	}
	public void setPermSuper(Permission permSuper) {
		this.permSuper = permSuper;
	}
	public Permission getPermStaff() {
		return permStaff;
	}
	public void setPermStaff(Permission permStaff) {
		this.permStaff = permStaff;
	}
	public Permission getPermUser() {
		return permUser;
	}
	public void setPermUser(Permission permUser) {
		this.permUser = permUser;
	}
	public Permission getPermCli() {
		return permCli;
	}
	public void setPermCli(Permission permCli) {
		this.permCli = permCli;
	}
	public void setPermProjectManager(Permission projectManager) {
		this.projectManager = projectManager;
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Field other = (Field) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
