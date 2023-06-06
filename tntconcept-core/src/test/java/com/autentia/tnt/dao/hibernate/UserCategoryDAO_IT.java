package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.UserCategory;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.UserCategorySearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserCategoryDAO_IT extends IntegrationTest {

    private final UserCategoryDAO userCategoryDAO;


    public UserCategoryDAO_IT() {
        this.userCategoryDAO = (UserCategoryDAO) SpringUtils.getSpringBean("daoUserCategory");
    }

    @NotNull
    private static UserCategory createUserCategory() {
        final UserCategory userCategory = new UserCategory();
        userCategory.setName("RRHH");
        userCategory.setDepartmentId(1);
        return userCategory;
    }

    @Test
    public void getByIdShouldReturnNull() {
        final int userCategoryId = Integer.MAX_VALUE;

        final UserCategory userCategory = userCategoryDAO.getById(userCategoryId);

        assertNull(userCategory);
    }

    @Test
    public void getByIdShouldGetUserCategory() {
        final int userCategoryId = 1;

        final UserCategory userCategory = userCategoryDAO.getById(userCategoryId);

        assertNotNull(userCategory);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNull() {
        final int userCategoryId = 0;

        final UserCategory userCategory = userCategoryDAO.loadById(userCategoryId);

        assertNull(userCategory);
    }

    @Test
    public void loadByIdShouldGetUserCategory() {
        final int userCategoryId = 1;

        final UserCategory userCategory = userCategoryDAO.loadById(userCategoryId);

        assertNotNull(userCategory);
    }

    @Test
    public void searchShouldReturnUserCategory() {
        final UserCategorySearch userCategorySearch = new UserCategorySearch();
        userCategorySearch.setName("Socio");

        final List<UserCategory> userCategories = userCategoryDAO.search(userCategorySearch, new SortCriteria());

        assertEquals(1, userCategories.size());
        assertEquals("Socio", userCategories.get(0).getName());
    }

    @Test
    public void insertUserCategoryShouldGiveInstanceId() {
        final UserCategory userCategory = createUserCategory();

        userCategoryDAO.insert(userCategory);

        assertTrue(userCategory.getId() > 0);
    }

    @Test
    public void updateShouldChangeName() {
        final UserCategory userCategory = userCategoryDAO.getById(1);
        userCategory.setName("Recursos Humanos");

        userCategoryDAO.update(userCategory);

        final UserCategory userCategoryUpdated = userCategoryDAO.getById(1);
        assertEquals("Recursos Humanos", userCategoryUpdated.getName());
    }

    @Test
    public void deleteShouldRemoveUserCategory() {
        final UserCategory userCategory = userCategoryDAO.getById(1);

        userCategoryDAO.delete(userCategory);

        assertNull(userCategoryDAO.getById(1));
    }
}
