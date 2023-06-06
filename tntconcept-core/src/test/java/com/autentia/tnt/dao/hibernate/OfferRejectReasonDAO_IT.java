package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.OfferRejectReason;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.OfferRejectReasonSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OfferRejectReasonDAO_IT extends IntegrationTest {
    private final OfferRejectReasonDAO offerRejectReasonDAO;
    private final String titleFirstRow = "Sin respuesta";

    public OfferRejectReasonDAO_IT() {
        offerRejectReasonDAO = (OfferRejectReasonDAO) SpringUtils.getSpringBean("daoOfferRejectReason");
    }

    @Test
    public void shouldLoadById() {
        final OfferRejectReason result = offerRejectReasonDAO.loadById(1);

        assertEquals(titleFirstRow, result.getTitle());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnExceptionWhenIdDoesntExists() {
        final OfferRejectReason result = offerRejectReasonDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        final OfferRejectReason result = offerRejectReasonDAO.getById(1);

        assertEquals(titleFirstRow, result.getTitle());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final OfferRejectReason result = offerRejectReasonDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindOfferRejectReasons() {
        final List<OfferRejectReason> result = offerRejectReasonDAO.search(new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void searchShouldFindByCriteria() {
        final OfferRejectReasonSearch offerRejectReasonSearch = new OfferRejectReasonSearch();
        offerRejectReasonSearch.setTitle(titleFirstRow);

        final List<OfferRejectReason> result = offerRejectReasonDAO.search(new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void updateShouldChangeObject() {
        final String updatedTitle = "change";
        final OfferRejectReason offerRejectReason = offerRejectReasonDAO.getById(1);
        offerRejectReason.setTitle(updatedTitle);

        offerRejectReasonDAO.update(offerRejectReason);
        final OfferRejectReason result = offerRejectReasonDAO.getById(1);

        assertEquals(updatedTitle, result.getTitle());
    }

    @Test
    public void insertShouldPersistObject() {
        final String expectedTitle = "title";
        final OfferRejectReason offerRejectReason = createOfferRejectReason(expectedTitle);

        offerRejectReasonDAO.insert(offerRejectReason);

        final List<OfferRejectReason> result = offerRejectReasonDAO.search(new SortCriteria());

        assertEquals(expectedTitle, result.get(result.size()-1).getTitle());
    }

    @Test
    public void shouldNotLoadByIdAfterDelete() {
        final OfferRejectReason offerRejectReason = offerRejectReasonDAO.getById(1);

        offerRejectReasonDAO.delete(offerRejectReason);

        assertThrows(DataAccException.class, () -> {
            final OfferRejectReason result = offerRejectReasonDAO.loadById(1);
        });
    }

    private OfferRejectReason createOfferRejectReason(String title) {
        final OfferRejectReason offerRejectReason = new OfferRejectReason();
        offerRejectReason.setTitle(title);
        offerRejectReason.setDescription("description");
        return offerRejectReason;
    }
}
