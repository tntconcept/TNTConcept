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

/**
 * Transfer object to store BulletinBoards
 * 
 * @author stajanov code generator
 */
public class ActivityFile implements Serializable, ITransferObject {
	/** Serial version field */
	private static final long serialVersionUID = -1L;

	// Fields
	private Integer id;
	private ExternalActivity externalActivity;
	private Date insertDate;
	private Date updateDate;
	private User user;
	private Integer ownerId;
	private Integer departmentId;
	private String file;
	private String fileMime;

	@Override
	public boolean equals(Object that) {
		try {
			if (that == null)
				return false;
			else
				return this.getId().equals(((ActivityFile) that).getId());
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int hashCode() {
		if (this.getId() == null)
			return super.hashCode();
		else
			return this.getId().intValue();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}

	/**
	 * @param insertDate the insertDate to set
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the ownerId
	 */
	public Integer getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return the departmentId
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @return the fileMime
	 */
	public String getFileMime() {
		return fileMime;
	}

	/**
	 * @param fileMime the fileMime to set
	 */
	public void setFileMime(String fileMime) {
		this.fileMime = fileMime;
	}

	/**
	 * @return the externalActivity
	 */
	public ExternalActivity getExternalActivity() {
		return externalActivity;
	}

	/**
	 * @param externalActivity the externalActivity to set
	 */
	public void setExternalActivity(ExternalActivity externalActivity) {
		this.externalActivity = externalActivity;
	}

	public List<Integer> getOwnersId() {
		// TODO Auto-generated method stub
		return null;
	}

}
