package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Offer;
import com.autentia.tnt.businessobject.OfferPotential;
import com.autentia.tnt.businessobject.OfferState;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.OfferSearch;
import com.autentia.tnt.test.utils.ContactForTesting;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OfferDAO_IT extends IntegrationTest {
    private final OfferDAO offerDAO;
    private final String titleFirstRow = "title1";

    public OfferDAO_IT() {
        offerDAO = (OfferDAO) SpringUtils.getSpringBean("daoOffer");
    }

    @Test
    public void shouldLoadById() {
        final Offer result = offerDAO.loadById(1);

        assertEquals(titleFirstRow, result.getTitle());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnExceptionWhenIdDoesntExists() {
        final Offer result = offerDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        final Offer result = offerDAO.getById(1);

        assertEquals(titleFirstRow, result.getTitle());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final Offer result = offerDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindContacts() {
        final List<Offer> result = offerDAO.search(new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void searchShouldFindContactsByCriteria() {
        final OfferSearch offerSearch = new OfferSearch();
        offerSearch.setTitle(titleFirstRow);

        final List<Offer> result = offerDAO.search(offerSearch, new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void updateShouldChangeOffer() {
        final String expectedTitle = "change";
        final Offer offer = offerDAO.getById(1);
        offer.setTitle(expectedTitle);

        offerDAO.update(offer);
        final Offer result = offerDAO.getById(1);

        assertEquals(expectedTitle, result.getTitle());
    }

    @Test
    public void shouldNotFindAfterDelete() {
        final Offer offer = offerDAO.getById(1);

        offerDAO.delete(offer);
        final Offer result = offerDAO.getById(1);

        assertNull(result);
    }

    @Test
    public void insertShouldPersistOffer() {
        final String expectedTitle = "title";
        final Offer offer = createOffer(expectedTitle);

        offerDAO.insert(offer);
        final List<Offer> result = offerDAO.search(new SortCriteria());

        assertEquals(expectedTitle, result.get(result.size() - 1).getTitle());
    }

    private Offer createOffer(String title) {
        final UserForTesting user = new UserForTesting();
        user.setId(1);
        final Organization organization = new Organization();
        organization.setId(1);
        final ContactForTesting contact = new ContactForTesting();
        contact.setId(1);

        final Offer offer = new Offer();
        offer.setNumber("1234");
        offer.setTitle(title);
        offer.setDescription("description");
        offer.setUser(user);
        offer.setOrganization(organization);
        offer.setContact(contact);
        offer.setCreationDate(Date.from(Instant.now()));
        offer.setOfferPotential(OfferPotential.HIGH);
        offer.setOfferState(OfferState.ACCEPTED);
        offer.setObservations("observations");
        offer.setShowIvaIntoReport(true);
        return offer;
    }


}
