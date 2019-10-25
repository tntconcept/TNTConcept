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

package com.autentia.tnt.manager.contacts.advancedsearch;

import com.autentia.tnt.businessobject.Contact;
import com.autentia.tnt.dao.hibernate.ContactDAO;
import com.autentia.tnt.dao.hibernate.PositionDAO;
import com.autentia.tnt.dao.search.AdvancedSearchContactSearch;
import com.autentia.tnt.tracking.EntityChange;
import java.util.ArrayList;
import java.util.List;

public class ContactPositionManager {

    private static final int CONTACT_NAME = 0;
    private static final int CONTACT_ORGANIZATION = 1;
    private static final int CONTACT_DEPARTMENT = 2;
    private static final int CONTACT_POSITION = 3;
    private static final int CONTACT_EMAIL = 4;
    private static final int CONTACT_PHONE = 5;
    private static final int CONTACT_FAX = 6;
    private static final int CONTACT_COUNTRY = 7;
    private static final int CONTACT_PROVINCE = 8;
    private static final int CONTACT_CITY = 9;
    private static final int CONTACT_POSTALCODE = 10;
    private static final int CONTACT_ADDRESS = 11;

    private static final int POSITION_ORGANIZATION = 0;
    private static final int POSITION_DEPARTMENT = 1;
    private static final int POSITION_POSITION = 2;
    private static final int POSITION_EMAIL = 3;
    private static final int POSITION_PHONE = 4;
    private static final int POSITION_FAX = 5;
    private static final int POSITION_COUNTRY = 6;
    private static final int POSITION_PROVINCE = 7;
    private static final int POSITION_CITY = 8;
    private static final int POSITION_POSTALCODE = 9;
    private static final int POSITION_ADDRESS = 10;

    private ContactDAO contactDAO;
    private PositionDAO positionDAO;

    public ContactPositionManager (ContactDAO contactDAO, PositionDAO positionDAO) {
        this.contactDAO = contactDAO;
        this.positionDAO = positionDAO;
    }

    public ContactPositionManager () {
        contactDAO = ContactDAO.getDefault();
        positionDAO = PositionDAO.getDefault();
    }

    public List<ContactPosition> doAdvancedSearch(final AdvancedSearchContactSearch advancedSearch, boolean searchInChanges) {
        final List<Object> contacts = contactDAO.contactAdvancedSearch(advancedSearch);
        final List<Object> positions = positionDAO.contactAdvancedSearch(advancedSearch);
        final List<ContactPosition> contactPositions = new ArrayList<ContactPosition>(contacts.size() + positions.size());

        contactPositions.addAll(this.fromContactToContactPosition(contacts, true));
        contactPositions.addAll(this.fromPositionToContactPosition(positions));

        if (searchInChanges) {
            contactPositions.addAll(this.recoverChangesFromContact(advancedSearch));
        } 

        return contactPositions;
    }

    /**
     * TODO
     */
    private List<ContactPosition> recoverChangesFromContact (final AdvancedSearchContactSearch advancedSearch) {

        final List<EntityChange> changes = contactDAO.contactAdvancedSearchChanges(advancedSearch);
        final List<Contact> contacts = new ArrayList<Contact>(changes.size());
        final List<ContactPosition> contactPositions = new ArrayList<ContactPosition>(contacts.size());
        String changeValueDescription;

        for (EntityChange entityChange: changes) {
            final ContactPosition contactPosition = new ContactPosition();
            final Contact contact = contactDAO.loadById(entityChange.getEntityId());
            contactPosition.setName(contact.getName());

            // recovers the text to show in the description of the change
            if (!(entityChange.getNewValue() == null || entityChange.getNewValue().equals(""))) {
                changeValueDescription = entityChange.getOldValue() + " [" + entityChange.getNewValue() + "]";
            } else {
                changeValueDescription = entityChange.getOldValue();
            }

            // add the description by field
            if (entityChange.getField().equals(Contact.FIELD_POSITION))
            {
                contactPosition.setPosition(changeValueDescription);
            } 
            else if (entityChange.getField().equals(Contact.FIELD_ORGANIZATION))
            {
                contactPosition.setOrganization(changeValueDescription);
            } 
            else if (entityChange.getField().equals(Contact.FIELD_NAME))
            {
                contactPosition.setName(changeValueDescription);
            } 
            else if (entityChange.getField().equals(Contact.FIELD_DEPARTMENT))
            {
                contactPosition.setDepartment(changeValueDescription);
            }
            else if (entityChange.getField().equals(Contact.FIELD_EMAIL))
            {
                contactPosition.setEmail(changeValueDescription);
            }
            else if (entityChange.getField().equals(Contact.FIELD_PHONE))
            {
                contactPosition.setPhone(changeValueDescription);
            }
            else if (entityChange.getField().equals(Contact.FIELD_FAX))
            {
                contactPosition.setFax(changeValueDescription);
            }
            else if (entityChange.getField().equals(Contact.FIELD_COUNTRY))
            {
                contactPosition.setCountry(changeValueDescription);
            }
            else if (entityChange.getField().equals(Contact.FIELD_PROVINCE))
            {
                contactPosition.setProvince(changeValueDescription);
            }
            else if (entityChange.getField().equals(Contact.FIELD_CITY))
            {
                contactPosition.setCity(changeValueDescription);
            }
            else if (entityChange.getField().equals(Contact.FIELD_POSTALCODE))
            {
                contactPosition.setPostalCode(changeValueDescription);
            }
            else if (entityChange.getField().equals(Contact.FIELD_ADDRESS))
            {
                contactPosition.setAddress(changeValueDescription);
            }

            contactPositions.add(contactPosition);
        }
        return contactPositions;
    }

    private List<ContactPosition> fromContactToContactPosition (final List<Object> contacts, boolean active) {
        final List<ContactPosition> contactPositions = new ArrayList<ContactPosition>(contacts.size());
        for (Object object : contacts) {
            Object[] contact = (Object[]) object;
            final ContactPosition contactPosition = new ContactPosition();
            contactPosition.setName((String) contact[CONTACT_NAME]);
            contactPosition.setOrganization((String) contact[CONTACT_ORGANIZATION]);
            contactPosition.setDepartment((String) contact[CONTACT_DEPARTMENT]);
            contactPosition.setPosition((String) contact[CONTACT_POSITION]);
            contactPosition.setEmail((String) contact[CONTACT_EMAIL]);
            contactPosition.setPhone((String) contact[CONTACT_PHONE]);
            contactPosition.setFax((String) contact[CONTACT_FAX]);
            contactPosition.setCountry((String) contact[CONTACT_COUNTRY]);
            contactPosition.setProvince((String) contact[CONTACT_PROVINCE]);
            contactPosition.setCity((String) contact[CONTACT_CITY]);
            contactPosition.setPostalCode((String) contact[CONTACT_POSTALCODE]);
            contactPosition.setAddress((String) contact[CONTACT_ADDRESS]);
            contactPosition.setActive(active);
            contactPositions.add(contactPosition);
        }
        return contactPositions;
    }

    private List<ContactPosition> fromPositionToContactPosition (final List<Object> positions) {
        final List<ContactPosition> contactPositions = new ArrayList<ContactPosition>(positions.size());
        String organizationName, departmentName, positionName, email, phone, fax, country, province, city, postalCode, address;
        for (Object object : positions) {
            Object[] position = (Object[]) object;
            final ContactPosition contactPosition = new ContactPosition();

            organizationName = (String) position[POSITION_ORGANIZATION];
            if (organizationName == null || organizationName.equals("")) organizationName = "-";

            departmentName = (String) position[POSITION_DEPARTMENT];
            if (departmentName == null || departmentName.equals("")) departmentName = "-";

            positionName = (String) position[POSITION_POSITION];
            if (positionName == null || positionName.equals("")) positionName = "-";

            email = (String) position[POSITION_EMAIL];
            if (email == null || email.equals("")) email = "-";

            phone = (String) position[POSITION_PHONE];
            if (phone == null || phone.equals("")) phone = "-";

            fax = (String) position[POSITION_FAX];
            if (fax == null || fax.equals("")) fax = "-";

            country = (String) position[POSITION_COUNTRY];
            if (country == null || country.equals("")) country = "-";

            province = (String) position[POSITION_PROVINCE];
            if (province == null || province.equals("")) province = "-";

            city = (String) position[POSITION_CITY];
            if (city == null || city.equals("")) city = "-";

            postalCode = (String) position[POSITION_POSTALCODE];
            if (postalCode == null || postalCode.equals("")) postalCode = "-";

            address = (String) position[POSITION_ADDRESS];
            if (address == null || address.equals("")) address = "-";

            contactPosition.setOrganization(organizationName);
            contactPosition.setDepartment(departmentName);
            contactPosition.setPosition(positionName);
            contactPosition.setEmail(email);
            contactPosition.setPhone(phone);
            contactPosition.setFax(fax);
            contactPosition.setCountry(country);
            contactPosition.setProvince(province);
            contactPosition.setCity(city);
            contactPosition.setPostalCode(postalCode);
            contactPosition.setAddress(address);
            contactPosition.setActive(true);
            contactPositions.add(contactPosition);
        }
        return contactPositions;
    }
}
