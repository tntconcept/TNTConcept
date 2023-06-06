package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Role;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.RoleSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RoleDAO_IT extends IntegrationTest {
    private final RoleDAO roleDAO;
    private final String nameFirstRow = "Administrador";

    public RoleDAO_IT() {
        roleDAO = (RoleDAO) SpringUtils.getSpringBean("daoRole");
    }

    @Test
    public void shouldLoadById() {
        final Role result = roleDAO.loadById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnExceptionWhenIdDoesntExists() {
        final Role result = roleDAO.loadById(Integer.MAX_VALUE);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        final Role result = roleDAO.getById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final Role result = roleDAO.getById(Integer.MAX_VALUE);

        assertNull(result);
    }

    @Test
    public void searchShouldFinRoles() {
        final List<Role> result = roleDAO.search(new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void searchShouldFindRolesByCriteria() {
        final RoleSearch roleSearch = new RoleSearch();
        roleSearch.setName(nameFirstRow);

        final List<Role> result = roleDAO.search(roleSearch, new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void updateShouldChangeRole() {
        final String expectedName = "change";
        final Role role = roleDAO.getById(1);
        role.setName(expectedName);

        roleDAO.update(role);
        final Role result = roleDAO.getById(1);

        assertEquals(expectedName, result.getName());
    }

    @Test
    public void shouldNotFindRoleAfterDelete() {
        final Role role = roleDAO.getById(1);

        roleDAO.delete(role);
        final Role result = roleDAO.getById(1);

        assertNull(result);
    }

    @Test
    public void insertShouldPersistRole() {
        final String expectedName = "newrole";
        final Role role = createRole(expectedName);

        roleDAO.insert(role);
        final List<Role> result = roleDAO.search(new SortCriteria());

        assertEquals(expectedName, result.get(result.size() - 1).getName());
    }

    private Role createRole(String name) {
        final Role role = new Role();
        role.setName(name);
        return role;
    }
}
