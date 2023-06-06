package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.OrganizationType;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.OrganizationTypeSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OrganizationTypeDAO_IT extends IntegrationTest {
    private final OrganizationTypeDAO organizationTypeDAO;

    public OrganizationTypeDAO_IT() {
        organizationTypeDAO = (OrganizationTypeDAO) SpringUtils.getSpringBean("daoOrganizationType");
    }

    @NotNull
    private static OrganizationType createOrganizationType() {
        final OrganizationType organizationType = new OrganizationType();
        organizationType.setName("E");
        organizationType.setDepartmentId(1);
        organizationType.setDescription("Description");
        return organizationType;
    }

    @Test
    public void getByIdShouldReturnNull() {
        final int organizationTypeId = Integer.MAX_VALUE;

        final OrganizationType OrganizationType = organizationTypeDAO.getById(organizationTypeId);

        assertNull(OrganizationType);
    }

    @Test
    public void getByIdShouldGetOrganizationType() {
        final int organizationTypeId = 1;

        final OrganizationType organizationType = organizationTypeDAO.getById(organizationTypeId);

        assertNotNull(organizationType);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNull() {
        final int organizationTypeId = 0;

        final OrganizationType organizationType = organizationTypeDAO.loadById(organizationTypeId);

        assertNull(organizationType);
    }

    @Test
    public void loadByIdShouldGetOrganizationType() {
        final int organizationTypeId = 1;

        final OrganizationType organizationType = organizationTypeDAO.loadById(organizationTypeId);

        assertNotNull(organizationType);
    }

    @Test
    public void searchShouldReturnOrganizationType() {
        final OrganizationTypeSearch organizationTypeSearch = new OrganizationTypeSearch();
        organizationTypeSearch.setName("Cliente");

        final List<OrganizationType> organizationISOCategories = organizationTypeDAO.search(organizationTypeSearch, new SortCriteria());

        assertEquals(1, organizationISOCategories.size());
        assertEquals("Cliente", organizationISOCategories.get(0).getName());
    }

    @Test
    public void insertOrganizationTypeShouldGiveInstanceId() {
        final OrganizationType organizationType = createOrganizationType();

        organizationTypeDAO.insert(organizationType);

        assertTrue(organizationType.getId() > 0);
    }

    @Test
    public void updateShouldChangeName() {
        final OrganizationType organizationType = organizationTypeDAO.getById(1);
        organizationType.setName("Name");

        organizationTypeDAO.update(organizationType);

        final OrganizationType organizationTypeUpdated = organizationTypeDAO.getById(1);
        assertEquals("Name", organizationTypeUpdated.getName());
    }

    @Test
    public void deleteShouldRemoveOrganizationType() {
        final OrganizationType organizationType = organizationTypeDAO.getById(1);

        organizationTypeDAO.delete(organizationType);

        assertNull(organizationTypeDAO.getById(1));
    }

}