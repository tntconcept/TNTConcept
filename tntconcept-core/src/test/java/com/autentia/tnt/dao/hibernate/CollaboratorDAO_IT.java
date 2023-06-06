package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Collaborator;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.CollaboratorSearch;
import com.autentia.tnt.test.utils.ContactForTesting;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CollaboratorDAO_IT extends IntegrationTest {
    final CollaboratorDAO collaboratorDAO;

    public CollaboratorDAO_IT() {
        collaboratorDAO = (CollaboratorDAO) SpringUtils.getSpringBean("daoCollaborator");
    }

    @Test
    public void loadByIdShouldLoadCollaborator() {
        final int collaboratorId = 1;

        final Collaborator collaborator = collaboratorDAO.loadById(collaboratorId);

        final Integer userId = 1;
        assertEquals(userId, collaborator.getUser().getId());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullCollaborator() {
        final int collaboratorId = Integer.MAX_VALUE;

        final Collaborator collaborator = collaboratorDAO.loadById(collaboratorId);

        assertNull(collaborator);
    }

    @Test
    public void getByIdShouldGetCollaborator() {
        final int collaboratorId = 1;

        final Collaborator collaborator = collaboratorDAO.getById(collaboratorId);

        final Integer userId = 1;
        assertEquals(userId, collaborator.getUser().getId());
    }

    @Test
    public void getByIdShouldGetNullCollaborator() {
        final int collaboratorId = Integer.MAX_VALUE;

        final Collaborator collaborator = collaboratorDAO.getById(collaboratorId);

        assertNull(collaborator);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultCollaborator() {
        Collaborator collaborator = createCollaborator();
        collaboratorDAO.insert(collaborator);

        List<Collaborator> collaborators = collaboratorDAO.search(new SortCriteria());

        assert(collaborators.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedCollaborator() {
        Collaborator collaborator = createCollaborator();
        collaboratorDAO.insert(collaborator);

        CollaboratorSearch collaboratorSearch = new CollaboratorSearch();
        collaboratorSearch.setUser(collaborator.getUser());
        List<Collaborator> collaborators = collaboratorDAO.search(collaboratorSearch, new SortCriteria());

        assert(collaborators.size() == 2);
    }

    @Test
    public void updateShouldChangeCollaboratorOrganization() {
        Collaborator collaboratorToUpdate = collaboratorDAO.getById(1);
        final Integer organizationId = 2;
        Organization organization = new Organization();
        organization.setId(organizationId);
        collaboratorToUpdate.setOrganization(organization);

        collaboratorDAO.update(collaboratorToUpdate);

        Collaborator updatedCollaborator = collaboratorDAO.getById(1);
        assertEquals(organizationId, updatedCollaborator.getOrganization().getId());
    }

    @Test
    public void deleteShouldRemoveCollaborator() {
        Collaborator collaboratorToDelete = collaboratorDAO.getById(1);

        collaboratorDAO.delete(collaboratorToDelete);

        assertNull(collaboratorDAO.getById(1));
    }

    private Collaborator createCollaborator() {
        UserForTesting user = new UserForTesting();
        user.setId(1);

        ContactForTesting contact = new ContactForTesting();
        contact.setId(1);

        Organization organization = new Organization();
        organization.setId(1);

        Collaborator collaborator = new Collaborator();
        collaborator.setUser(user);
        collaborator.setContact(contact);
        collaborator.setOrganization(organization);
        collaborator.setName("Test");
        collaborator.setOwnerId(1);
        collaborator.setDepartmentId(1);

        return collaborator;
    }
}