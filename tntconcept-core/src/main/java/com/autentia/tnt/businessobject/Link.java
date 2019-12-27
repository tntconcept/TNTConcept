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
import java.util.Date;
import java.util.List;

import com.autentia.tnt.dao.ITransferObject;
import com.autentia.tnt.dao.hibernate.UserDAO;

public class Link implements Serializable, ITransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String user;
	
	private String link;
	
	private Date insertDate;

    private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getOwnerId() {
		return null;
	}

	@Override
	public Integer getDepartmentId() {
		return null;
	}

	public void setOwnerId(Integer ownerId) {
		
	}

	public void setDepartmentId(Integer departmentId) {
		
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
		
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		
	}

	public List<Integer> getOwnersId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
