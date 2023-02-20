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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.autentia.tnt.dao.ITransferObject;
import com.autentia.tnt.dao.hibernate.UserDAO;

/**
 * TransferObject to store External Activities
 * @author Daniel Hernandez del Peso
 *
 */
public class ExternalActivity implements Serializable, ITransferObject {

	
	/** Serial version field */
	private static final long serialVersionUID = -1L;
	
	private Integer id;
	
	private String name;
	
	private String category;
	
	private Date startDate;
	
	private Date endDate;
	
	private String location;
	
	private String organizer;
	
	private String comments;
	
	private DocumentCategory documentCategory;
	
	private User owner;
	
	private Integer departmentId;
	
	private Date insertDate;
	
	private Date updateDate;
	
	private Set<ActivityFile> files = new HashSet<ActivityFile>();
	
	//TransferObject methods
    public Integer getDepartmentId() {
    	return departmentId;
	}

	public Integer getId() {
		return id;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public Integer getOwnerId() {
		return owner.getId();
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
		
	}

	private void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
		
	}

	public void setOwnerId(Integer ownerId) {
		this.owner = UserDAO.getDefault().loadById(ownerId);
	}

	private void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	

	//Fields getters and setter (some of them are included in TransferObject Methods
	
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public DocumentCategory getDocumentCategory() {
		return documentCategory;
	}

	public void setDocumentCategory(DocumentCategory documentCategory) {
		this.documentCategory = documentCategory;
	}

	
	//Overriding equals and hashCode
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
    public int hashCode() {
        final HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(getName());
        hcb.append(getCategory());
        hcb.append(getStartDate());
        hcb.append(getEndDate());
        hcb.append(getLocation());
        hcb.append(getOrganizer());
        hcb.append(getComments());
        hcb.append(getInsertDate());
        hcb.append(getUpdateDate());
        hcb.append(getDocumentCategory());
        hcb.append(getDepartmentId());
        hcb.append(getOwnerId());
        
        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEquals = false;
        try {
            final ExternalActivity other = (ExternalActivity)obj;
            final EqualsBuilder eqb = new EqualsBuilder();
            eqb.append(getName(), other.getName());
            eqb.append(getCategory(), other.getCategory());
            eqb.append(getStartDate(), other.getStartDate());
            eqb.append(getEndDate(), other.getEndDate());
            eqb.append(getLocation(), other.getLocation());
            eqb.append(getOrganizer(), other.getOrganizer());
            eqb.append(getComments(), other.getComments());
            eqb.append(getInsertDate(), other.getInsertDate());
            eqb.append(getUpdateDate(), other.getUpdateDate());
            eqb.append(getDocumentCategory(), other.getDocumentCategory());
            eqb.append(getDepartmentId(), other.getDepartmentId());
            eqb.append(getOwnerId(), other.getOwnerId());

            isEquals = eqb.isEquals();

        } catch (Exception e) {
            // Sobre todo si no se puede hacer la conversi�n de tipos,
            // y en general si se produce cualquier error.
            isEquals = false;
        }

        return isEquals;
    }
	
	/**
	 * @return the files
	 */
	public Set<ActivityFile> getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(Set<ActivityFile> files) {
		this.files = files;
	}

	public List<Integer> getOwnersId() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
