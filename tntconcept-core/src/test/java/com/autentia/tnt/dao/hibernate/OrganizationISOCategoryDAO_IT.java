package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.OrganizationISOCategory;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.OrganizationISOCategorySearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OrganizationISOCategoryDAO_IT extends IntegrationTest {
    private final OrganizationISOCategoryDAO organizationISOCategoryDAO;

    public OrganizationISOCategoryDAO_IT() {
        organizationISOCategoryDAO = (OrganizationISOCategoryDAO) SpringUtils.getSpringBean("daoOrganizationISOCategory");
    }

    @NotNull
    private static OrganizationISOCategory createOrganizationISOCategory() {
        final OrganizationISOCategory organizationISOCategory = new OrganizationISOCategory();
        organizationISOCategory.setName("E");
        organizationISOCategory.setDepartmentId(1);
        organizationISOCategory.setDescription("Description");
        return organizationISOCategory;
    }

    @Test
    public void getByIdShouldReturnNull() {
        final int organizationISOCategoryId = Integer.MAX_VALUE;

        final OrganizationISOCategory OrganizationISOCategory = organizationISOCategoryDAO.getById(organizationISOCategoryId);

        assertNull(OrganizationISOCategory);
    }

    @Test
    public void getByIdShouldGetOrganizationISOCategory() {
        final int organizationISOCategoryId = 1;

        final OrganizationISOCategory organizationISOCategory = organizationISOCategoryDAO.getById(organizationISOCategoryId);

        assertNotNull(organizationISOCategory);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNull() {
        final int organizationISOCategoryId = 0;

        final OrganizationISOCategory organizationISOCategory = organizationISOCategoryDAO.loadById(organizationISOCategoryId);

        assertNull(organizationISOCategory);
    }

    @Test
    public void loadByIdShouldGetOrganizationISOCategory() {
        final int organizationISOCategoryId = 1;

        final OrganizationISOCategory organizationISOCategory = organizationISOCategoryDAO.loadById(organizationISOCategoryId);

        assertNotNull(organizationISOCategory);
    }

    @Test
    public void searchShouldReturnOrganizationISOCategory() {
        final OrganizationISOCategorySearch organizationISOCategorySearch = new OrganizationISOCategorySearch();
        organizationISOCategorySearch.setName("A");

        final List<OrganizationISOCategory> organizationISOCategories = organizationISOCategoryDAO.search(organizationISOCategorySearch, new SortCriteria());

        assertEquals(1, organizationISOCategories.size());
        assertEquals("A", organizationISOCategories.get(0).getName());
    }

    @Test
    public void insertOrganizationISOCategoryShouldGiveInstanceId() {
        final OrganizationISOCategory organizationISOCategory = createOrganizationISOCategory();

        organizationISOCategoryDAO.insert(organizationISOCategory);

        assertTrue(organizationISOCategory.getId() > 0);
    }

    @Test
    public void updateShouldChangeName() {
        final OrganizationISOCategory organizationISOCategory = organizationISOCategoryDAO.getById(1);
        organizationISOCategory.setName("Name");

        organizationISOCategoryDAO.update(organizationISOCategory);

        final OrganizationISOCategory organizationISOCategoryUpdated = organizationISOCategoryDAO.getById(1);
        assertEquals("Name", organizationISOCategoryUpdated.getName());
    }

    @Test
    public void deleteShouldRemoveOrganizationISOCategory() {
        final OrganizationISOCategory organizationISOCategory = organizationISOCategoryDAO.getById(1);

        organizationISOCategoryDAO.delete(organizationISOCategory);

        assertNull(organizationISOCategoryDAO.getById(1));
    }

}