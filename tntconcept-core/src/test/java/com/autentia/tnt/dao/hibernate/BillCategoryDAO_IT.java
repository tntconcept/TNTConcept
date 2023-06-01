package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.BillCategory;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BillCategorySearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BillCategoryDAO_IT extends IntegrationTest {
    final BillCategoryDAO billCategoryDAO;

    public BillCategoryDAO_IT() {
        billCategoryDAO = (BillCategoryDAO) SpringUtils.getSpringBean("daoBillCategory");
    }

    @Test
    public void loadByIdShouldLoadBillCategory() {
        final int billCategoryId = 1;

        final BillCategory billCategory = billCategoryDAO.loadById(billCategoryId);

        assertEquals("Factura", billCategory.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullBillCategory() {
        final int billCategoryId = Integer.MAX_VALUE;

        final BillCategory billCategory = billCategoryDAO.loadById(billCategoryId);

        assertNull(billCategory);
    }

    @Test
    public void getByIdShouldGetBillCategory() {
        final int billCategoryId = 1;

        final BillCategory billCategory = billCategoryDAO.getById(billCategoryId);

        assertEquals("Factura", billCategory.getName());
    }

    @Test
    public void getByIdShouldGetNullBillCategory() {
        final int billCategoryId = Integer.MAX_VALUE;

        final BillCategory billCategory = billCategoryDAO.getById(billCategoryId);

        assertNull(billCategory);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultBillCategory() {
        BillCategory billCategory = createBillCategory();
        billCategoryDAO.insert(billCategory);

        List<BillCategory> billCategories = billCategoryDAO.search(new SortCriteria());

        assert(billCategories.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedBillCategory() {
        BillCategory billCategory = createBillCategory();
        billCategoryDAO.insert(billCategory);

        BillCategorySearch billCategorySearch = new BillCategorySearch();
        billCategorySearch.setName(billCategory.getName());
        List<BillCategory> billCategories = billCategoryDAO.search(billCategorySearch, new SortCriteria());

        assert(billCategories.size() == 1);
    }

    @Test
    public void updateShouldChangeBillCategoryName() {
        BillCategory billCategoryToUpdate = billCategoryDAO.getById(1);
        billCategoryToUpdate.setName("Update");

        billCategoryDAO.update(billCategoryToUpdate);

        BillCategory updatedBillCategory = billCategoryDAO.getById(1);
        assertEquals("Update", updatedBillCategory.getName());
    }

    @Test
    public void deleteShouldRemoveBillCategory() {
        BillCategory billCategoryToDelete = billCategoryDAO.getById(1);

        billCategoryDAO.delete(billCategoryToDelete);

        assertNull(billCategoryDAO.getById(1));
    }

    private BillCategory createBillCategory() {

        BillCategory billCategory = new BillCategory();
        billCategory.setId(Integer.MAX_VALUE);
        billCategory.setCode("FA");
        billCategory.setName("Test");
        billCategory.setRectify(false);
        billCategory.setOwnerId(1);
        billCategory.setDepartmentId(1);

        return billCategory;
    }
}