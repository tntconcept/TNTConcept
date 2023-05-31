package com.autentia.tnt.dao;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.dao.search.UserSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.SpringUtilsForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class UserDAO_IT extends IntegrationTest {

    private final UserDAO userDAO;

    public UserDAO_IT() {
        userDAO = (UserDAO) SpringUtils.getSpringBean("daoUser");
    }

    private User insertUserTest(String login) {
        User user = SpringUtilsForTesting.createUser(login);
        user.setActive(true);
        userDAO.insert(user);
        return user;
    }

    @Test
    public void shouldSearchByLogin() {
        final String login = "pepito";
        final User expected = insertUserTest(login);
        final User result = userDAO.searchByLogin(login);

        assertEquals(expected, result);
    }

    @Test
    public void shouldLoadById() {
        final int idUser = 1;

        final User result = userDAO.loadById(idUser);

        assertEquals("Administrador", result.getName());
    }

    @Test
    public void searchShouldFindUsers() {
        insertUserTest("pepito");
        SortCriteria sortCriteria = new SortCriteria();
        sortCriteria.add("name", true);

        List<User> result = userDAO.search(sortCriteria);

        assert (result.size() > 0);
    }

    @Test
    public void searchShouldFilterByName() {
        insertUserTest("pepito");
        SortCriteria sortCriteria = new SortCriteria();
        sortCriteria.add("name", true);
        UserSearch searchCriteria = new UserSearch();
        searchCriteria.setName("pepito");

        List<User> result = userDAO.search(searchCriteria, sortCriteria);

        assert (result.size() > 0);
    }

    @Test
    public void updateShouldChangeField() {
        User user = insertUserTest("pepito");
        SortCriteria sortCriteria = new SortCriteria();
        sortCriteria.add("name", true);
        UserSearch searchCriteria = new UserSearch();
        searchCriteria.setName(user.getName());

        List<User> users = userDAO.search(searchCriteria, sortCriteria);
        User userToUpdate = users.get(0);
        userToUpdate.setName("pepito2");
        searchCriteria.setName("pepito2");

        List<User> result = userDAO.search(searchCriteria, sortCriteria);

        assert (result.size() > 0);
    }

    @Test
    public void searchShouldNotFindDeletedUser() {
        User user = insertUserTest("pepito");
        SortCriteria sortCriteria = new SortCriteria();
        sortCriteria.add("name", true);
        UserSearch searchCriteria = new UserSearch();
        searchCriteria.setName(user.getName());

        List<User> users = userDAO.search(searchCriteria, sortCriteria);

        userDAO.delete(users.get(0));

        List<User> result = userDAO.search(searchCriteria, sortCriteria);

        assert (result.size() == 0);
    }

    @Test
    public void searchByActiveShouldFindUsers() {
        insertUserTest("pepito");
        SortCriteria sortCriteria = new SortCriteria();
        sortCriteria.add("active", true);

        List<User> result = userDAO.searchByActive(true, sortCriteria);

        assert (result.size() > 0);
    }

    @Test
    public void searchByLoginShouldFindUser() {
        User expected = insertUserTest("pepito");

        User result = userDAO.searchByLogin(expected.getName());

        assertEquals(expected.getName(), result.getName());
    }

    @Test
    public void searchByLoginShouldThrowDataNotFoundException () {
        String login = "pepito";

        assertThrows(DataNotFoundException.class, () -> {
            userDAO.searchByLogin(login);
        });
    }

    @Test
    public void searchByLoginShouldThrowDataIntegrityException() {
        String login = "pepito";
        insertUserTest(login);
        insertUserTest(login);
        assertThrows(DataIntegrityException.class, () -> {
            userDAO.searchByLogin(login);
        });

    }


}
