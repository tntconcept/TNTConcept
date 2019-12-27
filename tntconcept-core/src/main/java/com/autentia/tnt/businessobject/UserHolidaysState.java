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

package com.autentia.tnt.businessobject;

import java.io.Serializable;

public class UserHolidaysState implements Serializable {

	private User user;	
	private int totalAccepted = 0;
	private int totalYear = 0;
	
	public int getTotal() {
		return (getTotalYear() - totalAccepted);
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getTotalAccepted() {
		return totalAccepted;
	}
	public void setTotalAccepted(int totalAccepted) {
		this.totalAccepted = totalAccepted;
	}

	public int getTotalYear() {
		return totalYear;
	}

	public void setTotalYear(int totalYear) {
		this.totalYear = totalYear;
	}
	
	
	
	
}
