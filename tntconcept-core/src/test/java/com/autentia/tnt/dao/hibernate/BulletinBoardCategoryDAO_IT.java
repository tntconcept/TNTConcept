package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.BulletinBoardCategory;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BulletinBoardCategorySearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BulletinBoardCategoryDAO_IT extends IntegrationTest {
    final BulletinBoardCategoryDAO bulletinBoardCategoryDAO;

    public BulletinBoardCategoryDAO_IT() {
        bulletinBoardCategoryDAO = (BulletinBoardCategoryDAO) SpringUtils.getSpringBean("daoBulletinBoardCategory");
    }

    @Test
    public void loadByIdShouldLoadBulletinBoardCategory() {
        final int bulletinBoardCategoryId = 1;

        final BulletinBoardCategory bulletinBoardCategory = bulletinBoardCategoryDAO.loadById(bulletinBoardCategoryId);

        assertEquals("Publica", StringUtils.stripAccents(bulletinBoardCategory.getName()));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullBulletinBoardCategory() {
        final int bulletinBoardCategoryId = Integer.MAX_VALUE;

        final BulletinBoardCategory bulletinBoardCategory = bulletinBoardCategoryDAO.loadById(bulletinBoardCategoryId);

        assertNull(bulletinBoardCategory);
    }

    @Test
    public void getByIdShouldGetBulletinBoardCategory() {
        final int bulletinBoardCategoryId = 1;

        final BulletinBoardCategory bulletinBoardCategory = bulletinBoardCategoryDAO.getById(bulletinBoardCategoryId);

        assertEquals("Publica", StringUtils.stripAccents(bulletinBoardCategory.getName()));
    }

    @Test
    public void getByIdShouldGetNullBulletinBoardCategory() {
        final int bulletinBoardCategoryId = Integer.MAX_VALUE;

        final BulletinBoardCategory bulletinBoardCategory = bulletinBoardCategoryDAO.getById(bulletinBoardCategoryId);

        assertNull(bulletinBoardCategory);
    }

    @Test
    public void getPublicCategoryShouldReturnExpectedBulletinBoardCategory() {
        final BulletinBoardCategory bulletinBoardCategory = bulletinBoardCategoryDAO.getPublicCategory();

        assertEquals("Publica", StringUtils.stripAccents(bulletinBoardCategory.getName()));
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultBulletinBoardCategory() {
        BulletinBoardCategory bulletinBoardCategory = createBulletinBoardCategory();
        bulletinBoardCategoryDAO.insert(bulletinBoardCategory);

        List<BulletinBoardCategory> bulletinBoardCategories = bulletinBoardCategoryDAO.search(new SortCriteria());

        assert(bulletinBoardCategories.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedBulletinBoardCategory() {
        BulletinBoardCategory bulletinBoardCategory = createBulletinBoardCategory();
        bulletinBoardCategoryDAO.insert(bulletinBoardCategory);

        BulletinBoardCategorySearch bulletinBoardCategorySearch = new BulletinBoardCategorySearch();
        bulletinBoardCategorySearch.setName(bulletinBoardCategory.getName());
        List<BulletinBoardCategory> bulletinBoardCategories = bulletinBoardCategoryDAO.search(bulletinBoardCategorySearch, new SortCriteria());

        assert(bulletinBoardCategories.size() == 1);
    }

    @Test
    public void updateShouldChangeBulletinBoardCategoryName() {
        BulletinBoardCategory bulletinBoardCategoryToUpdate = bulletinBoardCategoryDAO.getById(1);
        bulletinBoardCategoryToUpdate.setName("Update");

        bulletinBoardCategoryDAO.update(bulletinBoardCategoryToUpdate);

        BulletinBoardCategory updatedBulletinBoardCategory = bulletinBoardCategoryDAO.getById(1);
        assertEquals("Update", updatedBulletinBoardCategory.getName());
    }

    @Test
    public void deleteShouldRemoveBulletinBoardCategory() {
        BulletinBoardCategory bulletinBoardCategoryToDelete = bulletinBoardCategoryDAO.getById(1);

        bulletinBoardCategoryDAO.delete(bulletinBoardCategoryToDelete);

        assertNull(bulletinBoardCategoryDAO.getById(1));
    }

    private BulletinBoardCategory createBulletinBoardCategory() {
        BulletinBoardCategory bulletinBoardCategory = new BulletinBoardCategory();
        bulletinBoardCategory.setName("Test");
        bulletinBoardCategory.setOwnerId(1);
        bulletinBoardCategory.setDepartmentId(1);

        return bulletinBoardCategory;
    }
}