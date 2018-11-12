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
