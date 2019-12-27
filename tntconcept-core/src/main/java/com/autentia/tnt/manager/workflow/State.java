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

import java.util.ArrayList;
import java.util.List;


public class State {
	
	private String name;
	private boolean permAdmin = false;
	private boolean permSuper = false;
	private boolean permStaff = false;
	private boolean permUser = false;
	private boolean permCli = false;
	private boolean permProjectManager = false;
	
	private List<Field> fields = new ArrayList<Field>();
	
	public State() {
		super();
	}
	
	public State(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	public Field getField(String name) {
		final int index = fields.indexOf(new Field(name));
		
		return (index >= 0) ? fields.get(index) : null;
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
		State other = (State) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public boolean isPermAdmin() {
		return permAdmin;
	}

	public void setPermAdmin(boolean permAdmin) {
		this.permAdmin = permAdmin;
	}

	public boolean isPermSuper() {
		return permSuper;
	}

	public void setPermSuper(boolean permSuper) {
		this.permSuper = permSuper;
	}

	public boolean isPermStaff() {
		return permStaff;
	}

	public void setPermStaff(boolean permStaff) {
		this.permStaff = permStaff;
	}

	public boolean isPermUser() {
		return permUser;
	}

	public void setPermUser(boolean permUser) {
		this.permUser = permUser;
	}

	public boolean isPermCli() {
		return permCli;
	}

	public void setPermCli(boolean permCli) {
		this.permCli = permCli;
	}

	public boolean isPermProjectManager() {
		return permProjectManager;
	}
	
	public void setPermProjectManager(boolean permProjectManager) {
		this.permProjectManager = permProjectManager;
	}
}
