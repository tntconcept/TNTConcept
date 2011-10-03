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

public class WorkFlow {

	private String name;
	private List<State> states = new ArrayList<State>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
	}
	
	public State getState(String name) {
		final int index = states.indexOf(new State(name));
		
		return (index >= 0) ? states.get(index) : null;
	}
	
	public State getNextState(String name) {
		final int index = states.indexOf(new State(name));
				
		return (index >= 0 && (index+1) < states.size()) ? states.get(index+1) : null;
	}
}
