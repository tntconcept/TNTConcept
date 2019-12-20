package com.autentia.tnt.businessobject;

import com.autentia.tnt.dao.ITransferObject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Country implements Serializable, ITransferObject {

    private Integer id;
    private int code;
    private String iso3166a1;
    private String iso3166a2;
    private String name;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getIso3166a1() {
        return iso3166a1;
    }

    public void setIso3166a1(String iso3166a1) {
        this.iso3166a1 = iso3166a1;
    }

    public String getIso3166a2() {
        return iso3166a2;
    }

    public void setIso3166a2(String iso3166a2) {
        this.iso3166a2 = iso3166a2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public List<Integer> getOwnersId() {
        return null;
    }
}
