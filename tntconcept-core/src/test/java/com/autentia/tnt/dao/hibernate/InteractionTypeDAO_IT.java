package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.InteractionType;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.InteractionTypeSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class InteractionTypeDAO_IT extends IntegrationTest {
    private final InteractionTypeDAO interactionTypeDAO;
    private final String expectedNameFirstRow = "No conformidad";

    public InteractionTypeDAO_IT() {
        interactionTypeDAO = (InteractionTypeDAO) SpringUtils.getSpringBean("daoInteractionType");
    }

    @Test
    public void shouldLoadById() {
        final InteractionType result = interactionTypeDAO.loadById(1);

        assertEquals(expectedNameFirstRow, result.getName());
    }

    @Test
    public void shouldGetById() {
        final InteractionType result = interactionTypeDAO.getById(1);

        assertEquals(expectedNameFirstRow, result.getName());
    }

    @Test
    public void getByIdShoulReturnNullWhenIdDoesntExists() {
        final InteractionType result = interactionTypeDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindInteractionTypes() {
        final List<InteractionType> result = interactionTypeDAO.search(new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void searchShouldFindByCriteria() {
        final InteractionTypeSearch interactionTypeSearch = new InteractionTypeSearch();
        interactionTypeSearch.setName(expectedNameFirstRow);

        final List<InteractionType> result = interactionTypeDAO.search(interactionTypeSearch, new SortCriteria());

        assert (result.size() == 1);
    }

    @Test
    public void updateShouldChangeObject() {
        final String updatedName = "change";
        final InteractionType interactionType = interactionTypeDAO.getById(1);
        interactionType.setName(updatedName);

        interactionTypeDAO.update(interactionType);

        final InteractionType result = interactionTypeDAO.getById(1);

        assertEquals(updatedName, result.getName());
    }

    @Test
    public void shouldNotFindAfterDelete() {
        final InteractionType interactionType = interactionTypeDAO.getById(1);

        interactionTypeDAO.delete(interactionType);

        assertThrows(DataAccException.class, () -> {
            final InteractionType result = interactionTypeDAO.loadById(1);
        });
    }

}
