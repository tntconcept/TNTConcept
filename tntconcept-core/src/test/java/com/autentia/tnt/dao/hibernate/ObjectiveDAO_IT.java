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

import java.sql.SQLException;
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

    @Override
    public void rollback() throws SQLException {
        super.rollback();
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().connection().prepareStatement("ALTER TABLE Objective AUTO_INCREMENT=0").execute();
    }

    @Test
    public void shouldLoadById() {
        insertObjective(objectiveName);

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
        insertObjective(objectiveName);

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
        insertObjective(objectiveName);

        final List<Objective> result = objectiveDAO.search(new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void searchShouldFindByCriteria() {
        insertObjective(objectiveName);
        final ObjectiveSearch objectiveSearch = new ObjectiveSearch();
        objectiveSearch.setName(objectiveName);

        final List<Objective> result = objectiveDAO.search(objectiveSearch, new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void updateShouldChangeObject() {
        final String updatedName = "change";
        insertObjective(objectiveName);
        final Objective objective = objectiveDAO.getById(1);
        objective.setName(updatedName);

        objectiveDAO.update(objective);
        final Objective result = objectiveDAO.getById(1);

        assertEquals(updatedName, result.getName());
    }

    @Test
    public void shouldNotLoadByIdAfterDelete() {
        insertObjective(objectiveName);
        final Objective objective = objectiveDAO.getById(1);

        objectiveDAO.delete(objective);

        assertThrows(DataAccException.class, () -> {
            final Objective result = objectiveDAO.loadById(1);
        });
    }

    private void insertObjective(String name) {

        UserForTesting user = new UserForTesting();
        user.setId(1);
        Project project = new Project();
        project.setId(1);

        Objective objective = new Objective();
        objective.setUser(user);
        objective.setProject(project);
        objective.setStartDate(Date.from(Instant.now()));
        objective.setState(ObjectiveState.PENDING);
        objective.setName(name);

        objectiveDAO.insert(objective);
    }
}
