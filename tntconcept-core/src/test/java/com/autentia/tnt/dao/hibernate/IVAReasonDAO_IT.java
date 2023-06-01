package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.IVAReason;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class IVAReasonDAO_IT extends IntegrationTest {
    private final IVAReasonDAO ivaReasonDAO;
    private final String expectedReasonFirstRow = "Exenta por el articulo 20";

    public IVAReasonDAO_IT() {
        ivaReasonDAO = (IVAReasonDAO) SpringUtils.getSpringBean("daoIVAReason");
    }

    @Test
    public void shouldLoadById() {
        final IVAReason ivaReason = ivaReasonDAO.loadById(1);

        assertEquals(expectedReasonFirstRow, ivaReason.getReason());
    }

    @Test
    public void shouldGetById() {
        final IVAReason ivaReason = ivaReasonDAO.getById(1);

        assertEquals(expectedReasonFirstRow, ivaReason.getReason());
    }

    @Test
    public void getByIdShoulReturnNullWhenIdDoesntExists() {
        final IVAReason ivaReason = ivaReasonDAO.getById(100);

        assertNull(ivaReason);
    }

    @Test
    public void searchShouldFinIVAReason() {
        final List<IVAReason> result = ivaReasonDAO.search(new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void updateShouldChangeObject() {
        final String updatedReason = "change";
        final IVAReason ivaReason = ivaReasonDAO.getById(1);

        ivaReason.setReason(updatedReason);
        ivaReasonDAO.update(ivaReason);

        final IVAReason result = ivaReasonDAO.getById(1);

        assertEquals(updatedReason, result.getReason());
    }

    @Test
    public void shouldNotLoadByIdAfterDelete() {
        final IVAReason ivaReason = ivaReasonDAO.getById(1);

        ivaReasonDAO.delete(ivaReason);

        assertThrows(DataAccException.class, () -> {
            final IVAReason result = ivaReasonDAO.loadById(1);
        });
    }

    @Test
    public void insertShouldPersistIVAReason() {
        final String expectedReason = "reason";
        final IVAReason ivaReason = createIVAReason(expectedReason);

        ivaReasonDAO.insert(ivaReason);
        final List<IVAReason> result = ivaReasonDAO.search(new SortCriteria());

        assertEquals(expectedReason, result.get(result.size()-1).getReason());
    }

    private IVAReason createIVAReason(String reason) {
        final IVAReason ivaReason = new IVAReason();
        ivaReason.setCode("12");
        ivaReason.setReason(reason);
        ivaReason.setExempt(true);
        ivaReason.setId(100);
        return ivaReason;
    }
}
