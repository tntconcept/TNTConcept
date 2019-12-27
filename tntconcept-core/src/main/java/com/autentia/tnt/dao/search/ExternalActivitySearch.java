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

package com.autentia.tnt.dao.search;

import java.util.ArrayList;
import java.util.Date;

import com.autentia.tnt.businessobject.DocumentCategory;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SearchCriteria;

public class ExternalActivitySearch extends SearchCriteria {
	
	private String name;
	private boolean nameSet;
	
	private String category;
	private boolean categorySet;	
	
	private Date startStartDate;
	private boolean startStartDateSet;
	
	private Date endStartDate;
	private boolean endStartDateSet;
	
	private Date startEndDate;
	private boolean startEndDateSet;
	
	private Date endEndDate;
	private boolean endEndDateSet;
	
	private String location;
	private boolean locationSet;
	
	private String organizer;
	private boolean organizerSet;
	
	private String comments;
	private boolean commentsSet;
	
	private DocumentCategory documentCategory;
	private boolean documentCategorySet;
	
	private User owner;
	private boolean ownerSet;
	
	private Integer departmentId;
	private boolean departmentIdSet;
	
	private Date startInsertDate;
	private boolean startInsertDateSet;
	
	private Date endInsertDate;
	private boolean endInsertDateSet;
	
	private Date startUpdateDate;
	private boolean startUpdateDateSet;
	
	private Date endUpdateDate;
	private boolean endUpdateDateSet;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		nameSet = true;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
		categorySet = true;
	}

	public Date getStartStartDate() {
		return startStartDate;
	}

	public void setStartStartDate(Date startStartDate) {
		this.startStartDate = startStartDate;
		startStartDateSet = true;
	}

	public Date getEndStartDate() {
		return endStartDate;
	}

	public void setEndStartDate(Date endStartDate) {
		this.endStartDate = endStartDate;
		endStartDateSet =  true;
	}

	public Date getStartEndDate() {
		return startEndDate;
	}

	public void setStartEndDate(Date startEndDate) {
		this.startEndDate = startEndDate;
		startEndDateSet = true;
	}

	public Date getEndEndDate() {
		return endEndDate;
	}

	public void setEndEndDate(Date endEndDate) {
		this.endEndDate = endEndDate;
		endEndDateSet = true;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
		locationSet = true;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
		organizerSet = true;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
		commentsSet = true;
	}

	public DocumentCategory getDocumentCategory() {
		return documentCategory;
	}

	public void setDocumentCategory(DocumentCategory documentCategory) {
		this.documentCategory = documentCategory;
		documentCategorySet = true;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
		ownerSet = true;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
		departmentIdSet = true;
	}

	public Date getStartInsertDate() {
		return startInsertDate;
	}

	public void setStartInsertDate(Date startInsertDate) {
		this.startInsertDate = startInsertDate;
		startInsertDateSet = true;
	}

	public Date getEndInsertDate() {
		return endInsertDate;
	}

	public void setEndInsertDate(Date endInsertDate) {
		this.endInsertDate = endInsertDate;
		endInsertDateSet = true;
	}

	public Date getStartUpdateDate() {
		return startUpdateDate;
	}

	public void setStartUpdateDate(Date startUpdateDate) {
		this.startUpdateDate = startUpdateDate;
		startUpdateDateSet = true;
	}

	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
		endUpdateDateSet = true;
	}

	public boolean isNameSet() {
		return nameSet;
	}

	public boolean isCategorySet() {
		return categorySet;
	}

	public boolean isStartStartDateSet() {
		return startStartDateSet;
	}

	public boolean isEndStartDateSet() {
		return endStartDateSet;
	}

	public boolean isStartEndDateSet() {
		return startEndDateSet;
	}

	public boolean isEndEndDateSet() {
		return endEndDateSet;
	}

	public boolean isLocationSet() {
		return locationSet;
	}

	public boolean isOrganizerSet() {
		return organizerSet;
	}

	public boolean isCommentsSet() {
		return commentsSet;
	}

	public boolean isDocumentCategorySet() {
		return documentCategorySet;
	}

	public boolean isOwnerSet() {
		return ownerSet;
	}

	public boolean isDepartmentIdSet() {
		return departmentIdSet;
	}

	public boolean isStartInsertDateSet() {
		return startInsertDateSet;
	}

	public boolean isEndInsertDateSet() {
		return endInsertDateSet;
	}

	public boolean isStartUpdateDateSet() {
		return startUpdateDateSet;
	}

	public boolean isEndUpdateDateSet() {
		return endUpdateDateSet;
	}

	
	//Unsetters
	public void unsetName() {
		this.nameSet = false;
	}

	public void unsetCategory() {
		this.categorySet = false;
	}

	public void unsetStartStartDate() {
		this.startStartDateSet = false;
	}

	public void unsetEndStartDate() {
		this.endStartDateSet = false;
	}

	public void unsetStartEndDate() {
		this.startEndDateSet = false;
	}

	public void unsetEndEndDate() {
		this.endEndDateSet = false;
	}

	public void unsetLocation() {
		this.locationSet = false;
	}

	public void unsetOrganizer() {
		this.organizerSet = false;
	}

	public void unsetComments() {
		this.commentsSet = false;
	}

	public void unsetDocumentCategory() {
		this.documentCategorySet = false;
	}

	public void unsetOwner() {
		this.ownerSet = false;
	}

	public void unsetDepartmentId() {
		this.departmentIdSet = false;
	}

	public void unsetStartInsertDate() {
		this.startInsertDateSet = false;
	}

	public void unsetEndInsertDate() {
		this.endInsertDateSet = false;
	}

	public void unsetStartUpdateDate() {
		this.startUpdateDateSet = false;
	}

	public void unsetEndUpdateDate() {
		this.endUpdateDateSet = false;
	}

	@Override
	public Object[] getArguments() {

		ArrayList<Object> ret = new ArrayList<Object>();
		
		if (isNameSet()) {
			ret.add(name);
		}
		
		if (isCategorySet()) {
			ret.add(category);
		}
		
		if (isStartStartDateSet()) {
			ret.add(startStartDate);
		}
		
		if (isEndStartDateSet()) {
			ret.add(endStartDate);
		}
		
		if (isStartEndDateSet()) {
			ret.add(startEndDate);
		}
		
		if (isEndEndDateSet()) {
			ret.add(endEndDate);
		}
		
		if (isLocationSet()) {
			ret.add(location);
		}
		
		if(isOrganizerSet()) {
			ret.add(organizer);
		}
		
		if (isCommentsSet()) {
			ret.add(comments);
		}
		
		if (isDocumentCategorySet()) {
			ret.add(documentCategory);
		}
		
		if (isOwnerSet()){
			ret.add(owner);
		}
		
		if (isDepartmentIdSet()) {
			ret.add(departmentId);
		}
		
		if (isStartInsertDateSet()) {
			ret.add(startInsertDate);
		}
		
		if (isEndInsertDateSet()) {
			ret.add(endInsertDate);
		}
		
		if (isStartUpdateDateSet()) {
			ret.add(startUpdateDate);
		}
		
		if (isEndUpdateDateSet()) {
			ret.add(endUpdateDate);
		}
		
		return ret.toArray();
	}

	@Override
	public String getHQL() {
		// TODO Auto-generated method stub
		StringBuilder ret = new StringBuilder();
		int iArgNum = 0;
		
		if (isNameSet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getName() == null) {
				ret.append("name is null");
			} else {
				ret.append("name=:arg"+(iArgNum++));
			}
			ret.append(name);
		}
		
		if (isCategorySet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getCategory() == null) {
				ret.append("category is null");
			} else {
				ret.append("category=:arg"+(iArgNum++));
			}
			
		}
		
		if (isStartStartDateSet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getStartStartDate() == null) {
				ret.append("startDate =:arg"+(iArgNum++));
			} else {
				ret.append("startDate>=:arg"+(iArgNum++));
			}
		}
		
		if (isEndStartDateSet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getEndStartDate() == null) {
				ret.append("startDate =:arg"+(iArgNum++));
			} else {
				ret.append("startDate<=:arg"+(iArgNum++));
			}
		}
		
		if (isStartEndDateSet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getStartEndDate() == null) {
				ret.append("endDate =:arg"+(iArgNum++));
			} else {
				ret.append("endDate>=:arg"+(iArgNum++));
			}
		}
		
		if (isEndEndDateSet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getEndEndDate() == null) {
				ret.append("endDate =:arg"+(iArgNum++));
			} else {
				ret.append("endDate<=:arg"+(iArgNum++));
			}
		}
		
		if (isLocationSet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getLocation() == null) {
				ret.append("location is null");
			} else {
				ret.append("location=:arg"+(iArgNum++));
			}
		}
		
		if(isOrganizerSet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getOrganizer() == null) {
				ret.append("organizer is null");
			} else {
				ret.append("organizer=:arg"+(iArgNum++));
			}
		}
		
		if (isCommentsSet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getComments() == null) {
				ret.append("comments is null");
			} else {
				ret.append("comments=:arg"+(iArgNum++));
			}
		}
		
		if (isDocumentCategorySet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getDocumentCategory() == null) {
				ret.append("documentCategory is null");
			} else {
				ret.append("documentCategory=:arg"+(iArgNum++));
			}
		}
		
		if (isOwnerSet()){
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getOwner() == null) {
				ret.append("owner is null");
			} else {
				ret.append("owner=:arg"+(iArgNum++));
			}
		}
		
		if (isDepartmentIdSet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getDepartmentId() == null) {
				ret.append("departmentId is null");
			} else {
				ret.append("departmentId=:arg"+(iArgNum++));
			}
		}
		
		if (isStartInsertDateSet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getStartInsertDate() == null) {
				ret.append("insertDate =:arg"+(iArgNum++));
			} else {
				ret.append("insertDate>=:arg"+(iArgNum++));
			}
		}
		
		if (isEndInsertDateSet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getEndInsertDate() == null) {
				ret.append("insertDate =:arg"+(iArgNum++));
			} else {
				ret.append("insertDate<=:arg"+(iArgNum++));
			}
		}
		
		if (isStartUpdateDateSet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getStartUpdateDate() == null) {
				ret.append("updateDate =:arg"+(iArgNum++));
			} else {
				ret.append("updateDate>=:arg"+(iArgNum++));
			}
		}
		
		if (isEndUpdateDateSet()) {
			ret.append( (ret.length()==0) ? "WHERE " : " AND " );
			if (getEndUpdateDate() == null) {
				ret.append("updateDate =:arg"+(iArgNum++));
			} else {
				ret.append("updateDate<=:arg"+(iArgNum++));
			}
		}
		
		return ret.toString();
	}

	@Override
	public void reset() {
		
		unsetCategory();
		unsetComments();
		unsetDepartmentId();
		unsetDocumentCategory();
		unsetEndEndDate();
		unsetEndInsertDate();
		unsetEndStartDate();
		unsetEndUpdateDate();
		unsetLocation();
		unsetName();
		unsetOrganizer();
		unsetOwner();
		unsetStartEndDate();
		unsetStartInsertDate();
		unsetStartStartDate();
		unsetStartUpdateDate();
		
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append("ExternalActivitySearch{");
		
		if (isNameSet()) {
			ret.append("(name=");
			ret.append(name);
			ret.append(")");
		}
		
		if (isCategorySet()) {
			ret.append("category=");
			ret.append(category);
			ret.append(")");
		}
		
		if (isStartStartDateSet()) {
			ret.append("startStartDate=");
			ret.append(startStartDate);
			ret.append(")");
		}
		
		if (isEndStartDateSet()) {
			ret.append("(endStartDate=");
			ret.append(endStartDate);
			ret.append(")");
		}
		
		if (isStartEndDateSet()) {
			ret.append("(startEndDate=");
			ret.append(startEndDate);
			ret.append(")");
		}
		
		if (isEndEndDateSet()) {
			ret.append("(endEndDate=");
			ret.append(endEndDate);
			ret.append(")");
		}
		
		if (isLocationSet()) {
			ret.append("(location=");
			ret.append(location);
			ret.append(")");
		}
		
		if(isOrganizerSet()) {
			ret.append("(organizer=");
			ret.append(organizer);
			ret.append(")");
		}
		
		if (isCommentsSet()) {
			ret.append("(comments=");
			ret.append(comments);
			ret.append(")");
		}
		
		if (isDocumentCategorySet()) {
			ret.append("(documentCategory=");
			ret.append(documentCategory);
			ret.append(")");
		}
		
		if (isOwnerSet()){
			ret.append("(owner=");
			ret.append(owner);
			ret.append(")");
		}
		
		if (isDepartmentIdSet()) {
			ret.append("(departmentId=");
			ret.append(departmentId);
			ret.append(")");
		}
		
		if (isStartInsertDateSet()) {
			ret.append("(startInsertDate=");
			ret.append(startInsertDate);
			ret.append(")");
		}
		
		if (isEndInsertDateSet()) {
			ret.append("(endInsertDate=");
			ret.append(endInsertDate);
			ret.append(")");
		}
		
		if (isStartUpdateDateSet()) {
			ret.append("(startUpdateDate=");
			ret.append(startUpdateDate);
			ret.append(")");
		}
		
		if (isEndUpdateDateSet()) {
			ret.append("(endUpdateDate=");
			ret.append(endUpdateDate);
			ret.append(")");
		}
		
		return ret.toString();
	}

}
