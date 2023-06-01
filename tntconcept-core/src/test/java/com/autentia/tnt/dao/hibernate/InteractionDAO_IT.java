package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Interaction;
import com.autentia.tnt.businessobject.InteractionInterest;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.InteractionSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.InteractionTypeForTesting;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InteractionDAO_IT extends IntegrationTest {

    private final InteractionDAO interactionDAO;
    private final String expected = "test";

    public InteractionDAO_IT() {
        interactionDAO = (InteractionDAO) SpringUtils.getSpringBean("daoInteraction");
    }

    @Test
    public void shouldLoadById() {
        final Interaction interaction = interactionDAO.loadById(1);

        assertEquals(expected, interaction.getDescription());
    }

    @Test
    public void shouldGetById() {
        final Interaction interaction = interactionDAO.getById(1);

        assertEquals(expected, interaction.getDescription());
    }

    @Test
    public void searchShouldFindInteraction() {
        final List<Interaction> interactionList = interactionDAO.search(new SortCriteria());

        assert (interactionList.size() > 0);
    }

    @Test
    public void searchShouldFindByDescription() {
        final InteractionSearch searchCriteria = new InteractionSearch();
        searchCriteria.setDescription(expected);

        final List<Interaction> interactionList = interactionDAO.search(searchCriteria, new SortCriteria());

        assertEquals(expected, interactionList.get(0).getDescription());
    }

    @Test
    public void updateShouldChangeRow() {
        final String expectedChange = "test2";

        final Interaction interaction = interactionDAO.loadById(1);
        interaction.setDescription(expectedChange);
        interactionDAO.update(interaction);
        final Interaction interactionUpdated = interactionDAO.loadById(1);

        assertEquals(expectedChange, interactionUpdated.getDescription());
    }

    @Test
    public void shouldDelete() {
        final InteractionSearch searchCriteria = new InteractionSearch();
        searchCriteria.setDescription(expected);

        final List<Interaction> interactionList = interactionDAO.search(searchCriteria, new SortCriteria());
        interactionDAO.delete(interactionList.get(0));
        final List<Interaction> result = interactionDAO.search(searchCriteria, new SortCriteria());

        assert (result.size() == 0);
    }

    @Test
    public void insertShouldPersistInteraction() {
        final String expectedDescription = "desc";
        final Interaction interaction = createInteraction(expectedDescription);

        interactionDAO.insert(interaction);
        final List<Interaction> result = interactionDAO.search(new SortCriteria());

        assertEquals(expectedDescription, result.get(result.size() - 1).getDescription());
    }

    private Interaction createInteraction(String description) {
        final Project project = new Project();
        project.setId(1);
        final InteractionTypeForTesting interactionType = new InteractionTypeForTesting();
        interactionType.setId(1);
        final UserForTesting user = new UserForTesting();
        user.setId(1);

        final Interaction interaction = new Interaction();
        interaction.setCreationDate(Date.from(Instant.now()));
        interaction.setInterest(InteractionInterest.LOW);
        interaction.setDescription(description);
        interaction.setFile("file");
        interaction.setFileMime("mime");
        interaction.setType(interactionType);
        interaction.setUser(user);
        interaction.setProject(project);
        return interaction;
    }

}
