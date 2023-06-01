package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.BillBreakDown;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BillBreakDownSearch;
import com.autentia.tnt.test.utils.BillForTesting;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BillBreakDownDAO_IT extends IntegrationTest {
    final BillBreakDownDAO billBreakDownDAO;

    public BillBreakDownDAO_IT() {
        billBreakDownDAO = (BillBreakDownDAO) SpringUtils.getSpringBean("daoBillBreakDown");
    }

    @Test
    public void loadByIdShouldLoadBillBreakDown() {
        final int billBreakDownId = 1;

        final BillBreakDown billBreakDown = billBreakDownDAO.loadById(billBreakDownId);

        assertEquals("Test", billBreakDown.getConcept());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullBillBreakDown() {
        final int billBreakDownId = Integer.MAX_VALUE;

        final BillBreakDown billBreakDown = billBreakDownDAO.loadById(billBreakDownId);

        assertNull(billBreakDown);
    }

    @Test
    public void getByIdShouldGetBillBreakDown() {
        final int billBreakDownId = 1;

        final BillBreakDown billBreakDown = billBreakDownDAO.getById(billBreakDownId);

        assertEquals("Test", billBreakDown.getConcept());
    }

    @Test
    public void getByIdShouldGetNullBillBreakDown() {
        final int billBreakDownId = Integer.MAX_VALUE;

        final BillBreakDown billBreakDown = billBreakDownDAO.getById(billBreakDownId);

        assertNull(billBreakDown);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultBillBreakDown() {
        BillBreakDown billBreakDown = createBillBreakDown();
        billBreakDownDAO.insert(billBreakDown);

        List<BillBreakDown> billBreakDowns = billBreakDownDAO.search(new SortCriteria());

        assert(billBreakDowns.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedBillBreakDown() {
        BillBreakDown billBreakDown = createBillBreakDown();
        billBreakDownDAO.insert(billBreakDown);

        BillBreakDownSearch billBreakDownSearch = new BillBreakDownSearch();
        billBreakDownSearch.setConcept(billBreakDown.getConcept());
        List<BillBreakDown> billBreakDowns = billBreakDownDAO.search(billBreakDownSearch, new SortCriteria());

        assert(billBreakDowns.size() == 1);
    }

    @Test
    public void updateShouldChangeBillBreakDownName() {
        BillBreakDown billBreakDownToUpdate = billBreakDownDAO.getById(1);
        billBreakDownToUpdate.setConcept("Update");

        billBreakDownDAO.update(billBreakDownToUpdate);

        BillBreakDown updatedBillBreakDown = billBreakDownDAO.getById(1);
        assertEquals("Update", updatedBillBreakDown.getConcept());
    }

    @Test
    public void deleteShouldRemoveBillBreakDown() {
        BillBreakDown billBreakDownToDelete = billBreakDownDAO.getById(1);

        billBreakDownDAO.delete(billBreakDownToDelete);

        assertNull(billBreakDownDAO.getById(1));
    }

    private BillBreakDown createBillBreakDown() {
        BillForTesting bill = new BillForTesting();
        bill.setId(1);

        BillBreakDown billBreakDown = new BillBreakDown();
        billBreakDown.setBill(bill);
        billBreakDown.setConcept("Concept");
        billBreakDown.setUnits(BigDecimal.ONE);
        billBreakDown.setAmount(new BigDecimal("10.20"));
        billBreakDown.setIva(new BigDecimal("21.00"));
        billBreakDown.setOwnerId(1);
        billBreakDown.setDepartmentId(1);

        return billBreakDown;
    }
}