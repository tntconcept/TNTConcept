/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.bean.contacts;

import com.autentia.tnt.bean.NavigationResults;
import com.autentia.tnt.businessobject.Department;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Position;
import com.autentia.tnt.businessobject.Tag;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.AdvancedSearchContactSearch;
import com.autentia.tnt.manager.admin.DepartmentManager;
import com.autentia.tnt.manager.contacts.OrganizationManager;
import com.autentia.tnt.manager.contacts.PositionManager;
import com.autentia.tnt.manager.contacts.advancedsearch.ContactPosition;
import com.autentia.tnt.manager.contacts.advancedsearch.ContactPositionManager;
import com.autentia.tnt.util.FacesUtils;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvancedSearchContactBean {

    public static final String CONTACTS_SESSION_KEY = "contacts_advanced_search";
    public static final String MESSAGES_SESSION_KEY = "messages_advanced_search";

    public static final String MESSAGE_CONTACT_NAME = "contact.name";
    public static final String MESSAGE_CONTACT_POSITION = "contact.position";
    public static final String MESSAGE_CONTACT_ORGANIZATION = "contact.organization";
    public static final String MESSAGE_CONTACT_DEPARTMENT = "contact.department";
    public static final String MESSAGE_CONTACT_EMAIL = "contact.email";
    public static final String MESSAGE_CONTACT_PHONE = "contact.phone";
    public static final String MESSAGE_CONTACT_FAX = "contact.fax";
    public static final String MESSAGE_CONTACT_COUNTRY = "contact.country";
    public static final String MESSAGE_CONTACT_PROVINCE = "contact.province";
    public static final String MESSAGE_CONTACT_CITY = "contact.city";
    public static final String MESSAGE_CONTACT_POSTALCODE = "contact.postalCode";
    public static final String MESSAGE_CONTACT_ADDRESS = "contact.address";

    private final AdvancedSearchContactSearch search;
    private List<ContactPosition> contactPositions;
    private boolean launchCSV = false;

    public AdvancedSearchContactBean() {
        this.search = new AdvancedSearchContactSearch();
        this.contactPositions = new ArrayList(0);
    }

    public String search() {
        this.contactPositions = new ContactPositionManager().doAdvancedSearch(this.search, false);
        return "search";
    }

    public String searchInChanges() {
        this.contactPositions = new ContactPositionManager().doAdvancedSearch(this.search, true);
        return "search";
    }

    public String advancedSearchNavigation() {
        this.contactPositions = null;
        return NavigationResults.ADVANCED_SEARCH;
    }

    public void exportCSV() {
        launchCSV = true;
        FacesUtils.putInSession(CONTACTS_SESSION_KEY, contactPositions);
        FacesUtils.putInSession(MESSAGES_SESSION_KEY, this.populateCSVHeaderTexts());
    }

    private Map<String, String> populateCSVHeaderTexts() {
        final Map<String, String> texts = new HashMap<String, String>();
        texts.put(MESSAGE_CONTACT_NAME, FacesUtils.getMessage("contact.name"));
        texts.put(MESSAGE_CONTACT_POSITION, FacesUtils.getMessage("contact.position"));
        texts.put(MESSAGE_CONTACT_ORGANIZATION, FacesUtils.getMessage("contact.organization"));
        texts.put(MESSAGE_CONTACT_DEPARTMENT, FacesUtils.getMessage("contact.department"));
        texts.put(MESSAGE_CONTACT_EMAIL, FacesUtils.getMessage("contact.email"));
        texts.put(MESSAGE_CONTACT_PHONE, FacesUtils.getMessage("contact.phone"));
        texts.put(MESSAGE_CONTACT_FAX, FacesUtils.getMessage("contact.fax"));
        texts.put(MESSAGE_CONTACT_COUNTRY, FacesUtils.getMessage("contact.country"));
        texts.put(MESSAGE_CONTACT_PROVINCE, FacesUtils.getMessage("contact.province"));
        texts.put(MESSAGE_CONTACT_CITY, FacesUtils.getMessage("contact.city"));
        texts.put(MESSAGE_CONTACT_POSTALCODE, FacesUtils.getMessage("contact.postalCode"));
        texts.put(MESSAGE_CONTACT_ADDRESS, FacesUtils.getMessage("contact.address"));
        return texts;
    }

    public boolean isLaunchCSV() {
        return launchCSV;
    }

    public void setLaunchCSV(boolean launchCSV) {
        this.launchCSV = launchCSV;
    }

    public List<ContactPosition> getContactPositions() {
        return this.contactPositions;
    }

    public void setContactPositions(List<ContactPosition> contactPositions) {
        this.contactPositions = contactPositions;
    }

    public List<SelectItem> getAllPositions() {
        List<Position> refs = PositionManager.getDefault().getAllEntities(null, new SortCriteria("id"));

        List ret = new ArrayList();
        for (Position ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }

        return ret;
    }

    public List<SelectItem> getAllDepartments() {
        List<Department> refs = DepartmentManager.getDefault().getAllEntities(null, new SortCriteria("id"));

        List ret = new ArrayList();
        for (Department ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }

        return ret;
    }

    public List<SelectItem> getAllOrganizations() {
        List<Organization> refs = OrganizationManager.getDefault().getAllEntities(null, new SortCriteria("id"));

        List ret = new ArrayList();
        for (Organization ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }

        return ret;
    }

    public List<Tag> getSearchTags() {
        return this.search.getTags();
    }

    public void setSearchTags(List<Tag> val) {
        if (this.search.isTagsSet()) {
            this.search.setTags(val);
        }
    }

    public boolean isSearchTagsValid() {
        return this.search.isTagsSet();
    }

    public void setSearchTagsValid(boolean val) {
        if (val) {
            this.search.setTags(this.search.getTags());
        } else {
            this.search.unsetTags();
        }
    }

    public List<Position> getSearchPositions() {
        return this.search.getPositions();
    }

    public void setSearchPositions(List<Position> val) {
        if (!this.search.isPositionsSet()) {
            this.search.setPositions(val);
        }
    }

    public boolean isSearchPositionsValid() {
        return this.search.isPositionsSet();
    }

    public void setSearchPositionsValid(boolean val) {
        if (val) {
            this.search.setPositions(this.search.getPositions());
        } else {
            this.search.unsetPositions();
        }
    }

    public List<Organization> getSearchOrganizations() {
        return this.search.getOrganizations();
    }

    public void setSearchOrganizations(List<Organization> val) {
        if (this.search.isOrganizationsSet()) {
            this.search.setOrganizations(val);
        }
    }

    public boolean isSearchOrganizationsValid() {
        return this.search.isOrganizationsSet();
    }

    public void setSearchOrganizationsValid(boolean val) {
        if (val) {
            this.search.setOrganizations(this.search.getOrganizations());
        } else {
            this.search.unsetOrganizations();
        }
    }

    public String getSearchCountry() {
        return this.search.getCountry();
    }

    public void setSearchCountry(String val) {
        if (!this.search.isCountrySet()) {
            this.search.setCountry(val);
        }
    }

    public boolean isSearchCountryValid() {
        return this.search.isCountrySet();
    }

    public void setSearchCountryValid(boolean val) {
        if (val) {
            this.search.setCountry(this.search.getCountry());
        } else {
            this.search.unsetCountry();
        }
    }

    public String getSearchName() {
        return this.search.getName();
    }

    public void setSearchName(String val) {
        if (this.search.isNameSet()) {
            this.search.setName(val);
        }
    }

    public boolean isSearchNameValid() {
        return this.search.isNameSet();
    }

    public void setSearchNameValid(boolean val) {
        if (val) {
            this.search.setName(this.search.getName());
        } else {
            this.search.unsetName();
        }
    }

    public List<Department> getSearchDepartments() {
        return this.search.getDepartments();
    }

    public void setSearchDepartments(List<Department> val) {
        if (this.search.isDepartmentsSet()) {
            this.search.setDepartments(val);
        }
    }

    public boolean isSearchDepartmentsValid() {
        return this.search.isDepartmentsSet();
    }

    public void setSearchDepartmentsValid(boolean val) {
        if (val) {
            this.search.setDepartments(this.search.getDepartments());
        } else {
            this.search.unsetDepartments();
        }
    }
}
