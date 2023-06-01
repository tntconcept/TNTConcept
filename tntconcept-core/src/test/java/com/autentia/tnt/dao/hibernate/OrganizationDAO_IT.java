package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.OrganizationSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.OrganizationISOCategoryForTesting;
import com.autentia.tnt.test.utils.OrganizationTypeForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OrganizationDAO_IT extends IntegrationTest {
    private final OrganizationDAO organizationDAO;
    private final String nameFirstRow = "Nuestra empresa";

    public OrganizationDAO_IT() {
        organizationDAO = (OrganizationDAO) SpringUtils.getSpringBean("daoOrganization");
    }

    @Test
    public void shoulLodadById() {
        final Organization result = organizationDAO.loadById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnExceptionWhenIdDoesntExists() {
        final Organization result = organizationDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        final Organization result = organizationDAO.getById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final Organization result = organizationDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindOrganizations() {
        final List<Organization> result = organizationDAO.search(new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void searchShouldFindOrganizationsByCriteria() {
        final OrganizationSearch organizationSearch = new OrganizationSearch();
        organizationSearch.setName(nameFirstRow);

        final List<Organization> result = organizationDAO.search(organizationSearch, new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void updateShouldChangeOrganization() {
        final String expectedName = "change";
        final Organization organization = organizationDAO.getById(1);
        organization.setName(expectedName);

        organizationDAO.update(organization);
        final Organization result = organizationDAO.getById(1);

        assertEquals(expectedName, result.getName());
    }

    @Test
    public void shouldNotFindOrganizationAfterDelete() {
        final String expectedName = "orgname";
        final Organization organization = createOrganization(expectedName);
        organizationDAO.insert(organization);

        final List<Organization> organizations = organizationDAO.search(new SortCriteria());
        final Organization newOrganization = organizations.get(organizations.size() - 1);
        organizationDAO.delete(newOrganization);
        final Organization result = organizationDAO.getById(newOrganization.getId());

        assertNull(result);
    }

    @Test
    public void insertShouldPersistOrganization() {
        final String expectedName = "orgname";
        final Organization organization = createOrganization(expectedName);

        organizationDAO.insert(organization);
        final List<Organization> result = organizationDAO.search(new SortCriteria());

        assertEquals(expectedName, result.get(result.size() - 1).getName());
    }

    @Test
    public void searchByOrganizationTypeShouldFindOrganizations() {
        OrganizationTypeForTesting organizationType = new OrganizationTypeForTesting();
        organizationType.setId(1);
        organizationType.setName("Cliente");

        final List<Organization> result = organizationDAO.searchByOrganizationTypes(new SortCriteria(), organizationType);

        assert result.size() > 0;
    }

    private Organization createOrganization(String name) {
        OrganizationTypeForTesting organizationType = new OrganizationTypeForTesting();
        organizationType.setId(1);
        OrganizationISOCategoryForTesting organizationISOCategory = new OrganizationISOCategoryForTesting();
        organizationISOCategory.setId(1);
        OrganizationDocCategory organizationDocCategory = new OrganizationDocCategory();
        organizationDocCategory.setId(1);
        Province province = new Province();
        province.setId(1);
        Country country = new Country();
        country.setId(1);

        final Organization organization = new Organization();
        organization.setType(organizationType);
        organization.setCategory(organizationISOCategory);
        organization.setName(name);
        organization.setDocumentNumber("1232");
        organization.setPhone("11111111111111");
        organization.setStreet("street");
        organization.setNumber("11111111111111");
        organization.setLocator("locator");
        organization.setPostalCode("28000");
        organization.setCity("Madrid");
        organization.setProvince(province);
        organization.setState("state");
        organization.setCountry(country);
        organization.setFax("11111111111111");
        organization.setEmail("test@test.com");
        organization.setWebsite("website.com");
        organization.setFtpsite("ftp");
        organization.setNotes("notes");
        organization.setOrganizationDocCategory(organizationDocCategory);
        organization.setFreelance(true);
        return organization;
    }
}
