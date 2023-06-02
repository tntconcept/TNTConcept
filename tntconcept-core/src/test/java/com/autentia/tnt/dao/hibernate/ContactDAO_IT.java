package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Contact;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.ContactSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ContactDAO_IT extends IntegrationTest {

    final ContactDAO contactDAO;

    public ContactDAO_IT() {
        contactDAO = (ContactDAO) SpringUtils.getSpringBean("daoContact");
    }

    @Test
    public void loadByIdShouldLoadContact() {
        final int contactId = 1;

        final Contact contact = contactDAO.loadById(contactId);

        assertEquals("name", contact.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullContact() {
        final int contactId = Integer.MAX_VALUE;

        final Contact contact = contactDAO.loadById(contactId);

        assertNull(contact);
    }

    @Test
    public void getByIdShouldGetContact() {
        final int contactId = 1;

        final Contact contact = contactDAO.getById(contactId);

        assertEquals("name", contact.getName());
    }

    @Test
    public void getByIdShouldGetNullContact() {
        final int contactId = Integer.MAX_VALUE;

        final Contact contact = contactDAO.getById(contactId);

        assertNull(contact);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultContact() {
        Contact contact = createContact();
        contactDAO.insert(contact);

        List<Contact> contacts = contactDAO.search(new SortCriteria());

        assert (contacts.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedContact() {
        Contact contact = createContact();
        contactDAO.insert(contact);

        ContactSearch contactSearch = new ContactSearch();
        contactSearch.setName(contact.getName());
        List<Contact> contacts = contactDAO.search(contactSearch, new SortCriteria());

        assert (contacts.size() == 1);
    }

    @Test
    public void updateShouldChangeContactName() {
        Contact contactToUpdate = contactDAO.getById(1);
        contactToUpdate.setName("Update");

        contactDAO.update(contactToUpdate);

        Contact updatedContact = contactDAO.getById(1);
        assertEquals("Update", updatedContact.getName());
    }

    @Test
    public void deleteShouldRemoveContact() {
        Contact contactToDelete = contactDAO.getById(1);

        contactDAO.delete(contactToDelete);

        assertNull(contactDAO.getById(1));
    }

    @Test
    public void contactAdvancedSearchShouldReturnExpectedContact() {
        Contact contact = createContact();
        contactDAO.insert(contact);

        ContactSearch contactSearch = new ContactSearch();
        contactSearch.setName(contact.getName());
        List<Contact> contacts = contactDAO.search(contactSearch, new SortCriteria());

        assert (contacts.size() == 1);
    }

    private Contact createContact() {
        Contact contact = new Contact();
        contact.setName("Contact");
        contact.setEmail("");
        contact.setPhone("");
        contact.setMobile("");
        contact.setNotified(false);
        contact.setEmail2("");
        contact.setPhone2("");
        contact.setFax("");
        contact.setAddress("");
        contact.setPostalCode("");
        contact.setCity("");
        contact.setCountry("");
        contact.setOwnerId(1);
        contact.setDepartmentId(1);

        return contact;
    }
}