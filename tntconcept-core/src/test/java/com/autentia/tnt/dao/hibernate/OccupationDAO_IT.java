package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Occupation;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.OccupationSearch;
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

public class OccupationDAO_IT extends IntegrationTest {
    private final OccupationDAO occupationDAO;
    private final String occupationDescription = "description";

    public OccupationDAO_IT() {
        occupationDAO = (OccupationDAO) SpringUtils.getSpringBean("daoOccupation");
    }

    @Override
    public void rollback() throws SQLException {
        super.rollback();
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().connection().prepareStatement("ALTER TABLE Occupation AUTO_INCREMENT=0").execute();
    }

    @Test
    public void shouldLoadById() {
        insertOccupation(occupationDescription);

        final Occupation result = occupationDAO.loadById(1);

        assertEquals(occupationDescription, result.getDescription());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnExceptionWhenIdDoesntExist() {
        final Occupation result = occupationDAO.loadById(1);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        insertOccupation(occupationDescription);

        final Occupation result = occupationDAO.getById(1);

        assertEquals(occupationDescription, result.getDescription());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExist() {
        final Occupation result = occupationDAO.getById(1);

        assertNull(result);
    }

    @Test
    public void searchShouldFindOccupations() {
        insertOccupation(occupationDescription);

        final List<Occupation> result = occupationDAO.search(new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void searchShouldFindByCriteria() {
        insertOccupation(occupationDescription);
        final OccupationSearch occupationSearch = new OccupationSearch();
        occupationSearch.setDescription(occupationDescription);

        final List<Occupation> result = occupationDAO.search(occupationSearch, new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void updateShouldChangeObject(){
        final String updatedDescription = "change";
        insertOccupation(occupationDescription);
        final Occupation occupation = occupationDAO.getById(1);
        occupation.setDescription(updatedDescription);

        occupationDAO.update(occupation);

        final Occupation result = occupationDAO.getById(1);

        assertEquals(updatedDescription, result.getDescription());
    }

    @Test
    public void shouldNotLoadByIdAfterDelete() {
        insertOccupation(occupationDescription);
        final Occupation occupation = occupationDAO.loadById(1);

        occupationDAO.delete(occupation);

        assertThrows(DataAccException.class, () -> {
            final Occupation result = occupationDAO.loadById(1);
        });
    }

    private void insertOccupation(String description) {

        UserForTesting user = new UserForTesting();
        user.setId(1);
        Project project = new Project();
        project.setId(1);
        Date date = Date.from(Instant.now());

        Occupation occupation = new Occupation();
        occupation.setUser(user);
        occupation.setProject(project);
        occupation.setStartDate(date);
        occupation.setEndDate(date);
        occupation.setDescription(description);
        occupation.setDuration(1);

        occupationDAO.insert(occupation);
    }
}
