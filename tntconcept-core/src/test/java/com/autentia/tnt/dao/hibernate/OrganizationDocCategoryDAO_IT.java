package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.OrganizationDocCategory;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OrganizationDocCategoryDAO_IT extends IntegrationTest {
    private final OrganizationDocCategoryDAO organizationDocCategoryDAO;
    private final String nameFirstRow = "NIF-IVA";

    public OrganizationDocCategoryDAO_IT() {
        organizationDocCategoryDAO = (OrganizationDocCategoryDAO) SpringUtils.getSpringBean("daoOrganizationDocCategory");
    }

    @Test
    public void shouldLoadById() {
        final OrganizationDocCategory result = organizationDocCategoryDAO.loadById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnExceptionWhenIdDoesntExists() {
        final OrganizationDocCategory result = organizationDocCategoryDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        final OrganizationDocCategory result = organizationDocCategoryDAO.getById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final OrganizationDocCategory result = organizationDocCategoryDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindOrganizationDocCategories() {
        final List<OrganizationDocCategory> result = organizationDocCategoryDAO.search(new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void updateShouldChangeOrganizationDocCategory() {
        final String expectedName = "change";
        final OrganizationDocCategory organizationDocCategory = organizationDocCategoryDAO.getById(1);
        organizationDocCategory.setName(expectedName);

        organizationDocCategoryDAO.update(organizationDocCategory);
        final OrganizationDocCategory result = organizationDocCategoryDAO.getById(1);

        assertEquals(expectedName, result.getName());
    }

    @Test
    public void shouldNotFindOrganizationDocCategoryByIdAfterDelete() {
        final String expectedName = "orgname";
        final OrganizationDocCategory organizationDocCategory = createOrganizationDocCategory(expectedName, 10);
        organizationDocCategoryDAO.insert(organizationDocCategory);

        final List<OrganizationDocCategory> organizationDocCategories = organizationDocCategoryDAO.search(new SortCriteria());
        final OrganizationDocCategory newOrganizationDocCategory = organizationDocCategories.get(organizationDocCategories.size() - 1);

        organizationDocCategoryDAO.delete(newOrganizationDocCategory);
        final OrganizationDocCategory result = organizationDocCategoryDAO.getById(newOrganizationDocCategory.getId());

        assertNull(result);
    }

    @Test
    public void insertShouldPersistOrganizationDocCategory() {
        final String expectedName = "newname";
        final OrganizationDocCategory organizationDocCategory = createOrganizationDocCategory(expectedName, 20);

        organizationDocCategoryDAO.insert(organizationDocCategory);
        final List<OrganizationDocCategory> result = organizationDocCategoryDAO.search(new SortCriteria());

        assertEquals(expectedName, result.get(result.size()-1).getName());
    }

    private OrganizationDocCategory createOrganizationDocCategory(String name, int id) {
        final OrganizationDocCategory organizationDocCategory = new OrganizationDocCategory();
        organizationDocCategory.setId(id);
        organizationDocCategory.setCode("123");
        organizationDocCategory.setName(name);
        return organizationDocCategory;
    }
}
