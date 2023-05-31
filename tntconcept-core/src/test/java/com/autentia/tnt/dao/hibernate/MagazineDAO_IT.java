package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Magazine;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.MagazineSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class MagazineDAO_IT extends IntegrationTest {
    private final MagazineDAO magazineDAO;
    private final String magazineName = "name";

    public MagazineDAO_IT() {
        magazineDAO = (MagazineDAO) SpringUtils.getSpringBean("daoMagazine");
    }

    @Override
    public void rollback() throws SQLException {
        super.rollback();
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().connection().prepareStatement("ALTER TABLE Magazine AUTO_INCREMENT=0").execute();
    }

    @Test
    public void shouldLoadById() {
        insertMagazine(magazineName);

        final Magazine result = magazineDAO.loadById(1);

        assertEquals(magazineName, result.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrownAnExceptionWhenIdDoesntExists() {
        final Magazine result = magazineDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        insertMagazine(magazineName);

        final Magazine result = magazineDAO.getById(1);

        assertEquals(magazineName, result.getName());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final Magazine result = magazineDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindMagazines() {
        insertMagazine(magazineName);

        final List<Magazine> result = magazineDAO.search(new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void searchShouldFindByCriteria() {
        insertMagazine(magazineName);
        final MagazineSearch magazineSearch = new MagazineSearch();
        magazineSearch.setName(magazineName);

        final List<Magazine> result = magazineDAO.search(magazineSearch, new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void updateShouldChangeObject() {
        final String updatedName = "change";
        insertMagazine(magazineName);

        final Magazine magazine = magazineDAO.getById(1);
        magazine.setName(updatedName);
        magazineDAO.update(magazine);

        final Magazine result = magazineDAO.getById(1);

        assertEquals(updatedName, result.getName());
    }

    @Test
    public void shouldNotLoadByIdAfterDelete() {
        insertMagazine(magazineName);
        final Magazine magazine = magazineDAO.getById(1);

        magazineDAO.delete(magazine);

        assertThrows(DataAccException.class, () -> {
            final Magazine result = magazineDAO.loadById(1);
        });
    }

    private void insertMagazine(String name) {
        Magazine magazine = new Magazine();
        magazine.setName(name);
        magazine.setDescription("description");

        magazineDAO.insert(magazine);
    }
}
