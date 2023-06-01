package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Offer;
import com.autentia.tnt.businessobject.OfferCost;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.OfferCostSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OfferCostDAO_IT extends IntegrationTest {
    private final OfferCostDAO offerCostDAO;
    private final String nameFirstRow = "name";

    public OfferCostDAO_IT() {
        offerCostDAO = (OfferCostDAO) SpringUtils.getSpringBean("daoOfferCost");
    }

    @Test
    public void shouldLoadById() {
        final OfferCost result = offerCostDAO.getById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowExceptionWhenIdDoesntExists() {
        final OfferCost result = offerCostDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        final OfferCost result = offerCostDAO.getById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final OfferCost result = offerCostDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindOfferCosts() {
        final List<OfferCost> result = offerCostDAO.search(new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void searchShouldFindOfferCostsByCriteria() {
        final OfferCostSearch offerCostSearch = new OfferCostSearch();
        offerCostSearch.setName(nameFirstRow);

        final List<OfferCost> result = offerCostDAO.search(offerCostSearch, new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void updateShouldChangeOfferCost() {
        final String expectedName = "change";
        final OfferCost offerCost = offerCostDAO.getById(1);
        offerCost.setName(expectedName);

        offerCostDAO.update(offerCost);
        final OfferCost result = offerCostDAO.getById(1);

        assertEquals(expectedName, result.getName());
    }

    @Test
    public void shouldNotFindAfterDelete() {
        final OfferCost offerCost = offerCostDAO.getById(1);

        offerCostDAO.delete(offerCost);
        final OfferCost result = offerCostDAO.getById(1);

        assertNull(result);
    }

    @Test
    public void insertShouldPersistOfferCost() {
        final String expectedName = "offercosttest";
        final OfferCost offerCost = createOfferCost(expectedName);

    }

    private OfferCost createOfferCost(String name) {
        Offer offer = new Offer();
        offer.setId(1);

        final OfferCost offerCost = new OfferCost();
        offerCost.setOffer(offer);
        offerCost.setName(name);
        offerCost.setCost(BigDecimal.TEN);
        offerCost.setBillable(true);
        offerCost.setUnits(BigDecimal.ONE);
        return offerCost;
    }
}
