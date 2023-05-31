package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Idea;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.IdeaSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Test;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
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
        insertIdea(expected, user);

        final Idea result = ideaDAO.loadById(1);

        assertEquals(expected, result.getDescription());
    }

    @Test
    public void shouldGetById() {
        insertIdea(expected, user);

        final Idea result = ideaDAO.getById(1);

        assertEquals(expected, result.getDescription());
    }

    @Test
    public void searchShouldFindIdeas() {
        insertIdea(expected, user);

        final List<Idea> result = ideaDAO.search(new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void searchShouldFindByCriteria() {
        insertIdea(expected, user);
        final IdeaSearch ideaSearch = new IdeaSearch();
        ideaSearch.setDescription(expected);

        final List<Idea> result = ideaDAO.search(ideaSearch, new SortCriteria());

        assertEquals(expected, result.get(0).getDescription());
    }

    @Test
    public void updateShouldChangeObject() {
        insertIdea(expected, user);
        final String updatedDescription = "change";

        final Idea idea = ideaDAO.getById(1);
        idea.setDescription(updatedDescription);
        final Idea result = ideaDAO.getById(1);

        assertEquals(updatedDescription, result.getDescription());
    }

    @Test
    public void shouldNotFindAfterDelete() {
        insertIdea(expected, user);

        final Idea idea = ideaDAO.getById(1);
        ideaDAO.delete(idea);

        assertThrows(DataAccException.class, () -> {
            final Idea result = ideaDAO.loadById(1);
        });
    }

    private void insertIdea(String description, User user) throws DataAccException {
        final Idea idea = new Idea();
        idea.setUser(user);
        idea.setCreationDate(Date.from(Instant.now()));
        idea.setDescription(description);
        idea.setCost("cost");
        idea.setBenefits("benefits");
        idea.setName("name");

        ideaDAO.insert(idea);
    }
}
