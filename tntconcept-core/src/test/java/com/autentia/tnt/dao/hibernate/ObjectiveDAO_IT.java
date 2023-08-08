package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Objective;
import com.autentia.tnt.businessobject.ObjectiveState;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.ObjectiveSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ObjectiveDAO_IT extends IntegrationTest {
    private final ObjectiveDAO objectiveDAO;
    private final String objectiveName = "name";

    public ObjectiveDAO_IT() {
        objectiveDAO = (ObjectiveDAO) SpringUtils.getSpringBean("daoObjective");
    }

    @Test
    public void shouldLoadById() {
        final Objective result = objectiveDAO.loadById(1);

        assertEquals(objectiveName, result.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadBIdShouldThrowExceptionWhenIdDoesntExist() {
        final Objective result = objectiveDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        final Objective result = objectiveDAO.getById(1);

        assertEquals(objectiveName, result.getName());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExist() {
        final Objective result = objectiveDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindObjectives() {
        final List<Objective> result = objectiveDAO.search(new SortCriteria());

        assertFalse(result.isEmpty());
    }

    @Test
    public void searchShouldFindByCriteria() {
        final ObjectiveSearch objectiveSearch = new ObjectiveSearch();
        objectiveSearch.setName(objectiveName);

        final List<Objective> result = objectiveDAO.search(objectiveSearch, new SortCriteria());

        assertFalse(result.isEmpty());
    }

    @Test
    public void updateShouldChangeObject() {
        final String updatedName = "change";
        final Objective objective = objectiveDAO.getById(1);
        objective.setName(updatedName);

        objectiveDAO.update(objective);
        final Objective result = objectiveDAO.getById(1);

        assertEquals(updatedName, result.getName());
    }

    @Test
    public void shouldNotLoadByIdAfterDelete() {
        final Objective objective = objectiveDAO.getById(1);

        objectiveDAO.delete(objective);

        assertThrows(DataAccException.class, () -> objectiveDAO.loadById(1));
    }

    @Test
    public void insertShouldPersistObjective() {
        final String expectedName = "testname";
        final Objective objective = createObjective(expectedName);

        objectiveDAO.insert(objective);
        final List<Objective> result = objectiveDAO.search(new SortCriteria());

        assertEquals(expectedName, result.get(result.size() - 1).getName());
    }

    private Objective createObjective(String name) {
        final UserForTesting user = new UserForTesting();
        user.setId(1);
        final Project project = new Project();
        project.setId(1);

        final Objective objective = new Objective();
        objective.setName(name);
        objective.setProject(project);
        objective.setUser(user);
        objective.setStartDate(Date.from(Instant.now()));
        objective.setState(ObjectiveState.PENDING);
        return objective;
    }
}
