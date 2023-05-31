package com.autentia.tnt.dao;

import com.autentia.tnt.businessobject.Interaction;
import com.autentia.tnt.businessobject.InteractionInterest;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.dao.hibernate.InteractionDAO;
import com.autentia.tnt.dao.search.InteractionSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.InteractionTypeForTesting;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Test;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InteractionDao_IT extends IntegrationTest {

    private final InteractionDAO interactionDAO;
    private final String expected = "test";

    public InteractionDao_IT() {
        interactionDAO = (InteractionDAO) SpringUtils.getSpringBean("daoInteraction");
    }

    @Override
    public void rollback() throws SQLException {
        super.rollback();
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().connection().prepareStatement("ALTER TABLE Interaction AUTO_INCREMENT=0").execute();
    }

    @Test
    public void shouldLoadById() {
        insertInteraction(expected);

        Interaction interaction = interactionDAO.loadById(1);

        assertEquals(expected, interaction.getDescription());
    }

    @Test
    public void shouldGetById() {
        insertInteraction(expected);

        Interaction interaction = interactionDAO.getById(1);

        assertEquals(expected, interaction.getDescription());
    }

    @Test
    public void searchShouldFindInteraction() {
        insertInteraction(expected);

        List<Interaction> interactionList = interactionDAO.search(new SortCriteria());

        assert (interactionList.size() > 0);
    }

    @Test
    public void searchShouldFindByDescription() {
        insertInteraction(expected);
        InteractionSearch searchCriteria = new InteractionSearch();
        searchCriteria.setDescription(expected);

        List<Interaction> interactionList = interactionDAO.search(searchCriteria, new SortCriteria());

        assertEquals(expected, interactionList.get(0).getDescription());
    }

    @Test
    public void updateShouldChangeRow() {
        insertInteraction(expected);
        String expectedChange = "test2";

        Interaction interaction = interactionDAO.loadById(1);
        interaction.setDescription(expectedChange);
        interactionDAO.update(interaction);
        Interaction interactionUpdated = interactionDAO.loadById(1);

        assertEquals(expectedChange, interactionUpdated.getDescription());
    }

    @Test
    public void shouldDelete() {
        insertInteraction(expected);
        InteractionSearch searchCriteria = new InteractionSearch();
        searchCriteria.setDescription(expected);

        List<Interaction> interactionList = interactionDAO.search(searchCriteria, new SortCriteria());
        interactionDAO.delete(interactionList.get(0));
        interactionList = interactionDAO.search(searchCriteria, new SortCriteria());

        assert (interactionList.size() == 0);
    }

    private void insertInteraction(String description) {
        Interaction interaction = new Interaction();
        interaction.setCreationDate(Date.from(Instant.now()));
        interaction.setInterest(InteractionInterest.LOW);
        interaction.setDescription(description);
        interaction.setFile("asd");
        interaction.setFileMime("jpg");

        Project project = new Project();
        project.setId(1);
        interaction.setProject(project);

        UserForTesting user = new UserForTesting();
        user.setId(1);
        interaction.setUser(user);

        InteractionTypeForTesting interactionType = new InteractionTypeForTesting();
        interactionType.setId(1);
        interaction.setType(interactionType);

        interactionDAO.insert(interaction);
    }

}
