package com.autentia.tnt.businessobject;

import com.autentia.tnt.dao.ITransferObject;
import net.sf.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ArchimedesSecuritySubject  implements Serializable, ITransferObject {

    private static final long serialVersionUID = -1L;

    private Integer id;
    private String principalName;
    private String attributes;

    public ArchimedesSecuritySubject(){

    }

    public ArchimedesSecuritySubject(final String principalName, final String attributes) {
        this.principalName = principalName;
        this.attributes = attributes;
    }



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

    /** MÉTODOS QUE SE QUEDAN VACÍOS POR ITRANSFER OBJECT */
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
}
