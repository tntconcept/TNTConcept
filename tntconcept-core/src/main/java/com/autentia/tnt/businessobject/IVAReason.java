package com.autentia.tnt.businessobject;

import com.autentia.tnt.dao.ITransferObject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class IVAReason implements Serializable, ITransferObject {

    private Integer id;
    private String code;
    private String reason;
    private boolean exempt;

    private Integer ownerId;
    private Integer departmentId;
    private Date insertDate;
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public boolean isExempt() {
        return exempt;
    }

    public void setExempt(boolean exempt) {
        this.exempt = exempt;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public Integer getOwnerId() {
        return ownerId;
    }

    @Override
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public Integer getDepartmentId() {
        return departmentId;
    }

    @Override
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public Date getInsertDate() {
        return insertDate;
    }

    private void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    private void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public List<Integer> getOwnersId() {
        return null;
    }
}
