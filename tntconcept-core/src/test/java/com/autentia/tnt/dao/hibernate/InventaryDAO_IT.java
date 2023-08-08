package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Inventary;
import com.autentia.tnt.businessobject.InventaryType;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.InventarySearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class InventaryDAO_IT extends IntegrationTest {
    private final InventaryDAO inventaryDAO;
    private final String expected = "test";

    public InventaryDAO_IT() {
        inventaryDAO = (InventaryDAO) SpringUtils.getSpringBean("daoInventary");
    }


    @Test
    public void shouldLoadById() {
        final Inventary result = inventaryDAO.loadById(1);

        assertEquals(expected, result.getDescription());
    }

    @Test
    public void shouldGetById() {
        final Inventary result = inventaryDAO.getById(1);

        assertEquals(expected, result.getDescription());
    }

    @Test
    public void searchShouldFindInventories() {
        final List<Inventary> result = inventaryDAO.search(new SortCriteria());

        assertFalse(result.isEmpty());
    }

    @Test
    public void searchShouldFindByCriteria() {
        final InventarySearch inventarySearch = new InventarySearch();
        inventarySearch.setDescription(expected);

        final List<Inventary> result = inventaryDAO.search(inventarySearch, new SortCriteria());

        assertEquals(expected, result.get(0).getDescription());
    }

    @Test
    public void searchShouldNotFindIfCriteriaDoesntMatch() {
        final InventarySearch inventarySearch = new InventarySearch();
        inventarySearch.setDescription("NoMatch");

        final List<Inventary> result = inventaryDAO.search(inventarySearch, new SortCriteria());

        assertTrue(result.isEmpty());
    }

    @Test
    public void updateShouldChangeObject() {
        final String updatedDescription = "change";

        final Inventary inventary = inventaryDAO.loadById(1);
        inventary.setDescription(updatedDescription);

        inventaryDAO.update(inventary);
        final Inventary result = inventaryDAO.loadById(1);

        assertEquals(updatedDescription, result.getDescription());
    }

    @Test
    public void shouldDelete() {
        final Inventary inventary = inventaryDAO.getById(1);
        inventaryDAO.delete(inventary);

        assertThrows(DataAccException.class, () -> inventaryDAO.loadById(1));
    }

    @Test
    public void insertShouldPersistInventory() {
        final String expectedDescription = "desc";
        final Inventary inventary = createInventary(expectedDescription);

        inventaryDAO.insert(inventary);
        final List<Inventary> result = inventaryDAO.search(new SortCriteria());

        assertEquals(expectedDescription, result.get(result.size() - 1).getDescription());
    }

    private Inventary createInventary(String description) {
        final Inventary inventary = new Inventary();
        inventary.setBuyDate(Date.from(Instant.now()));
        inventary.setCost(BigDecimal.TEN);
        inventary.setSerialNumber("serial");
        inventary.setType(InventaryType.PC);
        inventary.setProvider("provider");
        inventary.setTrademark("trademark");
        inventary.setModel("model");
        inventary.setSpeed("speed");
        inventary.setStorage("storage");
        inventary.setRam("ram");
        inventary.setLocation("location");
        inventary.setDescription(description);
        return inventary;
    }

}
