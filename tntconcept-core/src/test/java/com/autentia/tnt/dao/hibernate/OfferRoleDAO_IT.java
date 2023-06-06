package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Offer;
import com.autentia.tnt.businessobject.OfferRole;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.OfferRoleSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OfferRoleDAO_IT extends IntegrationTest {
    private final OfferRoleDAO offerRoleDAO;
    private final String nameFirstRow = "name";

    public OfferRoleDAO_IT() {
        offerRoleDAO = (OfferRoleDAO) SpringUtils.getSpringBean("daoOfferRole");
    }

    @Test
    public void shouldLoadById() {
        final OfferRole result = offerRoleDAO.loadById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnExceptionWhenIdDoesntExists() {
        final OfferRole result = offerRoleDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        final OfferRole result = offerRoleDAO.getById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test
    public void getByIdShoulReturnNullWhenIdDoesntExists() {
        final OfferRole result = offerRoleDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindOfferRoles() {
        final List<OfferRole> result = offerRoleDAO.search(new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void searchShoulFindOfferRolesByCriteria() {
        final OfferRoleSearch offerRoleSearch = new OfferRoleSearch();
        offerRoleSearch.setName(nameFirstRow);

        final List<OfferRole> result = offerRoleDAO.search(offerRoleSearch, new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void updateShouldChangeOfferRole() {
        final String expectedName = "change";
        final OfferRole offerRole = offerRoleDAO.getById(1);
        offerRole.setName(expectedName);

        offerRoleDAO.update(offerRole);
        final OfferRole result = offerRoleDAO.getById(1);

        assertEquals(expectedName, result.getName());
    }

    @Test
    public void shouldNotFindAfterDelete() {
        final OfferRole offerRole = offerRoleDAO.getById(1);

        offerRoleDAO.delete(offerRole);
        final OfferRole result = offerRoleDAO.getById(1);

        assertNull(result);
    }

    @Test
    public void insertShouldPersistOfferRole() {
        final String expectedName = "newname";
        final OfferRole offerRole = createOfferRole(expectedName);

        offerRoleDAO.insert(offerRole);
        final List<OfferRole> result = offerRoleDAO.search(new SortCriteria());

        assertEquals(expectedName, result.get(result.size() - 1).getName());
    }

    private OfferRole createOfferRole(String name) {
        final Offer offer = new Offer();
        offer.setId(1);

        final OfferRole offerRole = new OfferRole();
        offerRole.setOffer(offer);
        offerRole.setName(name);
        offerRole.setCostPerHour(BigDecimal.TEN);
        offerRole.setExpectedHours(5);
        offerRole.setIva(BigDecimal.ONE);
        return offerRole;
    }


}
