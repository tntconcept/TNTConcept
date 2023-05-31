package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Inventary;
import com.autentia.tnt.businessobject.InventaryType;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.InventaryDAO;
import com.autentia.tnt.dao.search.InventarySearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class InventaryDAO_IT extends IntegrationTest {
    private final InventaryDAO inventaryDAO;
    private final String expected = "test";

    public InventaryDAO_IT() {
        inventaryDAO = (InventaryDAO) SpringUtils.getSpringBean("daoInventary");
    }

    @Override
    public void rollback() throws SQLException {
        super.rollback();
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().connection().prepareStatement("ALTER TABLE Inventory AUTO_INCREMENT=0").execute();
    }


    @Test
    public void shouldLoadById() {
        insertInventary(expected);

        final Inventary result = inventaryDAO.loadById(1);

        assertEquals(expected, result.getDescription());
    }

    @Test
    public void shouldGetById() {
        insertInventary(expected);

        final Inventary result = inventaryDAO.getById(1);

        assertEquals(expected, result.getDescription());
    }

    @Test
    public void searchShouldFindInventories() {
        insertInventary(expected);

        final List<Inventary> result = inventaryDAO.search(new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void searchShouldFindByCriteria() {
        insertInventary(expected);
        final InventarySearch inventarySearch = new InventarySearch();
        inventarySearch.setDescription(expected);

        final List<Inventary> result = inventaryDAO.search(inventarySearch, new SortCriteria());

        assertEquals(expected, result.get(0).getDescription());
    }

    @Test
    public void searchShouldNotFindIfCriteriaDoesntMatch() {
        insertInventary(expected);
        final InventarySearch inventarySearch = new InventarySearch();
        inventarySearch.setDescription("NoMatch");

        final List<Inventary> result = inventaryDAO.search(inventarySearch, new SortCriteria());

        assert (result.size() == 0);
    }

    @Test
    public void updateShouldChangeObject() {
        insertInventary(expected);
        final String updatedDescription = "change";

        final Inventary inventary = inventaryDAO.loadById(1);
        inventary.setDescription(updatedDescription);

        inventaryDAO.update(inventary);
        final Inventary result = inventaryDAO.loadById(1);

        assertEquals(updatedDescription, result.getDescription());
    }

    @Test
    public void shouldDelete() {
        insertInventary(expected);

        final Inventary inventary = inventaryDAO.loadById(1);
        inventaryDAO.delete(inventary);

        assertThrows(DataAccException.class, () -> {
            final Inventary result = inventaryDAO.loadById(1);
        });
    }

    private void insertInventary(String description) {
        final Inventary inventary = new Inventary();
        inventary.setBuyDate(Date.from(Instant.now()));
        inventary.setRenting(true);
        inventary.setCost(BigDecimal.TEN);
        inventary.setAmortizable(true);
        inventary.setSerialNumber("1234");
        inventary.setType(InventaryType.PC);
        inventary.setProvider("provider");
        inventary.setTrademark("trademark");
        inventary.setModel("model");
        inventary.setSpeed("speed");
        inventary.setStorage("storage");
        inventary.setRam("ram");
        inventary.setLocation("location");
        inventary.setDescription(description);

        inventaryDAO.insert(inventary);
    }
}
