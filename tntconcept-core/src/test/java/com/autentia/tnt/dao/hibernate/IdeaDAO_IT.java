package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Idea;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.IdeaSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class IdeaDAO_IT extends IntegrationTest {

    private final IdeaDAO ideaDAO;

    private final String expected = "description";

    public IdeaDAO_IT() {
        ideaDAO = (IdeaDAO) SpringUtils.getSpringBean("daoIdea");
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

    @Test
    public void insertShouldPersistIdea() {
        final String expectedName = "name";
        final Idea idea = createIdea(expectedName);

        ideaDAO.insert(idea);

        final List<Idea> result = ideaDAO.search(new SortCriteria());

        assertEquals(expectedName, result.get(result.size() - 1).getName());
    }

    private Idea createIdea(String name) {
        final UserForTesting user = new UserForTesting();
        user.setId(1);

        final Idea idea = new Idea();
        idea.setUser(user);
        idea.setCreationDate(Date.from(Instant.now()));
        idea.setDescription("description");
        idea.setCost("cost");
        idea.setBenefits("benefits");
        idea.setName(name);
        return idea;
    }

}
