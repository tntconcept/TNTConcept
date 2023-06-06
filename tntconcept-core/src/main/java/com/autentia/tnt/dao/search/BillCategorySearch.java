package com.autentia.tnt.dao.search;

import com.autentia.tnt.dao.SearchCriteria;

import java.util.ArrayList;

public class BillCategorySearch extends SearchCriteria {

    private boolean nameSet;

    private String name;

    @Override
    public String getHQL() {
        int iArgNum = 0;
        StringBuilder ret = new StringBuilder();

        if (isNameSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getName() == null) {
                ret.append("name is NULL");
            } else {
                ret.append("name like :arg" + (iArgNum++));
            }
        }

        return ret.toString();
    }

    @Override
    public Object[] getArguments() {
        ArrayList<Object> ret = new ArrayList<>();

        if (isNameSet() && getName() != null) {
            ret.add(name);
        }

        return ret.toArray();
    }

    @Override
    public void reset() {
        unsetName();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (isNameSet()) {
            ret.append("(name");
            ret.append("=" + name);
            ret.append(")");
        }

        return ret.toString();
    }

    public void unsetName() {
        this.nameSet = false;
    }

    public boolean isNameSet() {
        return nameSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.nameSet = true;
    }
}
