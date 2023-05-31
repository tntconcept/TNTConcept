package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.IVAType;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class IVATypeDAO_IT extends IntegrationTest {
    private final IVATypeDAO ivaTypeDAO;
    private final String expectedNameFirstId = "IVA General";

    public IVATypeDAO_IT() {
        ivaTypeDAO = (IVATypeDAO) SpringUtils.getSpringBean("daoIVAType");
    }

    @Test
    public void shouldLoadById() {
        final IVAType result = ivaTypeDAO.loadById(1);

        assertEquals(expectedNameFirstId, result.getName());
    }

    @Test
    public void shouldGetById() {
        final IVAType result = ivaTypeDAO.getById(1);

        assertEquals(expectedNameFirstId, result.getName());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final IVAType ivaReason = ivaTypeDAO.getById(100);

        assertNull(ivaReason);
    }

    @Test
    public void searchShouldFindIVATypes() {
        final List<IVAType> result = ivaTypeDAO.search(new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void updateShouldChangeObject() {
        final String updatedName = "change";
        final IVAType ivaType = ivaTypeDAO.getById(1);
        ivaType.setName(updatedName);

        final IVAType result = ivaTypeDAO.getById(1);

        assertEquals(updatedName, result.getName());
    }

    @Test
    public void shouldNotLoadByIdAfterDelete() {
        final IVAType ivaType = ivaTypeDAO.getById(1);

        ivaTypeDAO.delete(ivaType);

        assertThrows(DataAccException.class, () -> {
            final IVAType result = ivaTypeDAO.loadById(1);
        });
    }

}
