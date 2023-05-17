package com.autentia.tnt.businessobject;

import com.autentia.tnt.dao.ITransferObject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ArchimedesSecuritySubject implements Serializable, ITransferObject {

    private static final long serialVersionUID = -1L;

    private Integer id;
    private String principalName;
    private String attributes;

    @Override
    public Integer getId() {
        return id;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public String getAttributes() {
        return attributes;
    }

    public static ArchimedesSecuritySubject from(final String principalName, final String subject) {
        final ArchimedesSecuritySubject entity = new ArchimedesSecuritySubject();
        entity.principalName = principalName;
        entity.attributes = subject;
        return entity;
    }


    /**
     * MÉTODOS QUE SE QUEDAN VACÍOS POR ITRANSFER OBJECT
     */
    @Override
    public Integer getOwnerId() {
        return null;
    }

    @Override
    public Integer getDepartmentId() {
        return null;
    }

    @Override
    public void setOwnerId(final Integer ownerId) {

    }

    @Override
    public void setDepartmentId(final Integer departmentId) {

    }

    @Override
    public Date getInsertDate() {
        return null;
    }

    @Override
    public Date getUpdateDate() {
        return null;
    }

    @Override
    public List<Integer> getOwnersId() {
        return null;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
}
