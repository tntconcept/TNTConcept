package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Tutorial;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.TutorialSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TutorialDAO_IT extends IntegrationTest {
    final TutorialDAO tutorialDAO;

    public TutorialDAO_IT() {
        tutorialDAO = (TutorialDAO) SpringUtils.getSpringBean("daoTutorial");
    }

    @Test
    public void loadByIdShouldLoadTutorial() {
        final int tutorialId = 1;

        final Tutorial tutorial = tutorialDAO.loadById(tutorialId);

        assertEquals("Test", tutorial.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullTutorial() {
        final int tutorialId = Integer.MAX_VALUE;

        final Tutorial tutorial = tutorialDAO.loadById(tutorialId);

        assertNull(tutorial);
    }

    @Test
    public void getByIdShouldGetTutorial() {
        final int tutorialId = 1;

        final Tutorial tutorial = tutorialDAO.getById(tutorialId);

        assertEquals("Test", tutorial.getName());
    }

    @Test
    public void getByIdShouldGetNullTutorial() {
        final int tutorialId = Integer.MAX_VALUE;

        final Tutorial tutorial = tutorialDAO.getById(tutorialId);

        assertNull(tutorial);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultTutorial() {
        Tutorial tutorial = createTutorial();
        tutorialDAO.insert(tutorial);

        List<Tutorial> tutorials = tutorialDAO.search(new SortCriteria());

        assert(tutorials.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedTutorial() {
        Tutorial tutorial = createTutorial();
        tutorialDAO.insert(tutorial);

        TutorialSearch tutorialSearch = new TutorialSearch();
        tutorialSearch.setName(tutorial.getName());
        List<Tutorial> tutorials = tutorialDAO.search(tutorialSearch, new SortCriteria());

        assert(tutorials.size() == 1);
    }

    @Test
    public void searchByUserShouldReturnExpectedTutorial() {
        UserForTesting user = new UserForTesting();
        user.setId(1);
        TutorialSearch tutorialSearch = new TutorialSearch();
        tutorialSearch.setUser(user);
        List<Tutorial> tutorials = tutorialDAO.search(tutorialSearch, new SortCriteria());

        assert(tutorials.size() == 1);
    }

    @Test
    public void updateShouldChangeTutorialName() {
        Tutorial tutorialToUpdate = tutorialDAO.getById(1);
        tutorialToUpdate.setName("Update");

        tutorialDAO.update(tutorialToUpdate);

        Tutorial updatedTutorial = tutorialDAO.getById(1);
        assertEquals("Update", updatedTutorial.getName());
    }

    @Test
    public void deleteShouldRemoveTutorial() {
        Tutorial tutorialToDelete = tutorialDAO.getById(1);

        tutorialDAO.delete(tutorialToDelete);

        assertNull(tutorialDAO.getById(1));
    }

    private Tutorial createTutorial() {
        UserForTesting user = new UserForTesting();
        user.setId(1);

        Tutorial tutorial = new Tutorial();
        tutorial.setUser(user);
        tutorial.setMaxDeliveryDate(new Date());
        tutorial.setName("Tutorial");
        tutorial.setDescription("");
        tutorial.setOwnerId(1);
        tutorial.setDepartmentId(1);

        return tutorial;
    }
}