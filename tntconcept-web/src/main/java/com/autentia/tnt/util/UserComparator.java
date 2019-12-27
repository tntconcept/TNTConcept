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

package com.autentia.tnt.util;

import java.util.Comparator;

import com.autentia.tnt.businessobject.User;

public class UserComparator implements Comparator<User> {
	
	public int compare(User o1, User o2) {
		if(o1.equals(o2))
			return 0;
		
		java.text.Collator col = java.text.Collator.getInstance(FacesUtils.getViewLocale());
		
		return col.compare(o1.getName(), o2.getName());		
		
	}

}
