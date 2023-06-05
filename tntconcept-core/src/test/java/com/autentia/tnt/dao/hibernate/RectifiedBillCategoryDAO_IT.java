package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.RectifiedBillCategory;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RectifiedBillCategoryDAO_IT extends IntegrationTest {
    private final RectifiedBillCategoryDAO rectifiedBillCategoryDAO;
    private final String nameSecondRow = "Por diferencias";

    public RectifiedBillCategoryDAO_IT() {
        rectifiedBillCategoryDAO = (RectifiedBillCategoryDAO) SpringUtils.getSpringBean("daoRectifiedBillCategory");
    }

    @Test
    public void shouldLoadById() {
        final RectifiedBillCategory result = rectifiedBillCategoryDAO.loadById(2);

        assertEquals(nameSecondRow, result.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnExceptionWhenIdDoesntExists() {
        final RectifiedBillCategory result = rectifiedBillCategoryDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        final RectifiedBillCategory result = rectifiedBillCategoryDAO.getById(2);

        assertEquals(nameSecondRow, result.getName());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final RectifiedBillCategory result = rectifiedBillCategoryDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindRectifiedBillCategories() {
        final List<RectifiedBillCategory> result = rectifiedBillCategoryDAO.search(new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void updateShouldChangeRectifiedBillCategory() {
        final String expectedName = "change";
        final RectifiedBillCategory rectifiedBillCategory = rectifiedBillCategoryDAO.getById(1);
        rectifiedBillCategory.setName(expectedName);

        rectifiedBillCategoryDAO.update(rectifiedBillCategory);
        final RectifiedBillCategory result = rectifiedBillCategoryDAO.getById(1);

        assertEquals(expectedName, result.getName());
    }

    @Test
    public void shouldNotFindRectifiedBillCategoryAfterDelete() {
        final RectifiedBillCategory rectifiedBillCategory = rectifiedBillCategoryDAO.getById(1);

        rectifiedBillCategoryDAO.delete(rectifiedBillCategory);
        final RectifiedBillCategory result = rectifiedBillCategoryDAO.getById(1);

        assertNull(result);
    }

    @Test
    public void insertShouldPersistRectifiedBillCategory() {
        final String expectedName = "newRectifiedBillCategory";
        final RectifiedBillCategory rectifiedBillCategory = createRectifiedBillCategory(expectedName);

        rectifiedBillCategoryDAO.insert(rectifiedBillCategory);
        final List<RectifiedBillCategory> result = rectifiedBillCategoryDAO.search(new SortCriteria());

        assertEquals(expectedName, result.get(result.size() - 1).getName());
    }

    private RectifiedBillCategory createRectifiedBillCategory(String name) {
        final RectifiedBillCategory rectifiedBillCategory = new RectifiedBillCategory();
        rectifiedBillCategory.setName(name);
        rectifiedBillCategory.setId(100);
        rectifiedBillCategory.setCode("12");
        return rectifiedBillCategory;
    }
}
