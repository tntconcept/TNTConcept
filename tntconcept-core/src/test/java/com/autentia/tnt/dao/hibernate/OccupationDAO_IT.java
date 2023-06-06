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

    @Test
    public void shouldLoadById() {
        final Occupation result = occupationDAO.loadById(1);

        assertEquals(occupationDescription, result.getDescription());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnExceptionWhenIdDoesntExist() {
        final Occupation result = occupationDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        final Occupation result = occupationDAO.getById(1);

        assertEquals(occupationDescription, result.getDescription());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExist() {
        final Occupation result = occupationDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindOccupations() {
        final List<Occupation> result = occupationDAO.search(new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void searchShouldFindByCriteria() {
        final OccupationSearch occupationSearch = new OccupationSearch();
        occupationSearch.setDescription(occupationDescription);

        final List<Occupation> result = occupationDAO.search(occupationSearch, new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void updateShouldChangeObject() {
        final String updatedDescription = "change";
        final Occupation occupation = occupationDAO.getById(1);
        occupation.setDescription(updatedDescription);

        occupationDAO.update(occupation);

        final Occupation result = occupationDAO.getById(1);

        assertEquals(updatedDescription, result.getDescription());
    }

    @Test
    public void shouldNotLoadByIdAfterDelete() {
        final Occupation occupation = occupationDAO.getById(1);

        occupationDAO.delete(occupation);

        assertThrows(DataAccException.class, () -> {
            final Occupation result = occupationDAO.loadById(1);
        });
    }

    @Test
    public void insertShouldPersistOccupation() {
        final String expectedDescription = "testdesc";
        final Occupation occupation = createOccupation(expectedDescription);

        occupationDAO.insert(occupation);
        final List<Occupation> result = occupationDAO.search(new SortCriteria());

        assertEquals(expectedDescription, result.get(result.size() - 1).getDescription());
    }

    private Occupation createOccupation(String description) {
        final UserForTesting user = new UserForTesting();
        user.setId(1);
        final Project project = new Project();
        project.setId(1);
        Date date = Date.from(Instant.now());

        final Occupation occupation = new Occupation();
        occupation.setProject(project);
        occupation.setUser(user);
        occupation.setStartDate(date);
        occupation.setEndDate(date);
        occupation.setDescription(description);
        occupation.setDuration(1);
        return occupation;
    }
}
