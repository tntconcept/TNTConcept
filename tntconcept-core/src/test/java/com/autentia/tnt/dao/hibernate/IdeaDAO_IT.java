package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Idea;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.IdeaSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class IdeaDAO_IT extends IntegrationTest {

    private final IdeaDAO ideaDAO;

    private final String expected = "description";

    private final UserForTesting user;

    public IdeaDAO_IT() {
        ideaDAO = (IdeaDAO) SpringUtils.getSpringBean("daoIdea");
        user = new UserForTesting();
        user.setId(1);
    }

    @Override
    public void rollback() throws SQLException {
        super.rollback();
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().connection().prepareStatement("ALTER TABLE Idea AUTO_INCREMENT=0").execute();
    }

    @Test
    public void shouldLoadById() {
        final Idea result = ideaDAO.loadById(1);

        assertEquals(expected, result.getDescription());
    }

    @Test
    public void shouldGetById() {
        final Idea result = ideaDAO.getById(1);

        assertEquals(expected, result.getDescription());
    }

    @Test
    public void searchShouldFindIdeas() {
        final List<Idea> result = ideaDAO.search(new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void searchShouldFindByCriteria() {
        final IdeaSearch ideaSearch = new IdeaSearch();
        ideaSearch.setDescription(expected);

        final List<Idea> result = ideaDAO.search(ideaSearch, new SortCriteria());

        assertEquals(expected, result.get(0).getDescription());
    }

    @Test
    public void updateShouldChangeObject() {
        final String updatedDescription = "change";

        final Idea idea = ideaDAO.getById(1);
        idea.setDescription(updatedDescription);
        final Idea result = ideaDAO.getById(1);

        assertEquals(updatedDescription, result.getDescription());
    }

    @Test
    public void shouldNotFindAfterDelete() {
        final Idea idea = ideaDAO.getById(1);
        ideaDAO.delete(idea);

        assertThrows(DataAccException.class, () -> {
            final Idea result = ideaDAO.loadById(1);
        });
    }

}
