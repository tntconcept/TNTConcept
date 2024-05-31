package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.ProjectSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ProjectDAO_IT extends IntegrationTest {
    private final ProjectDAO projectDAO;

    public ProjectDAO_IT() {
        projectDAO = (ProjectDAO) SpringUtils.getSpringBean("daoProject");
    }

    @Test
    public void loadByIdShouldLoadProject() {
        final int projectId = 1;

        final Project project = projectDAO.loadById(projectId);

        assertEquals("Vacaciones", project.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullProject() {
        final int projectId = Integer.MAX_VALUE;

        final Project project = projectDAO.loadById(projectId);

        assertNull(project);
    }

    @Test
    public void getByIdShouldGetProject() {
        final int projectId = 1;

        final Project project = projectDAO.getById(projectId);

        assertEquals("Vacaciones", project.getName());
    }

    @Test
    public void getByIdShouldGetNullProject() {
        final int projectId = Integer.MAX_VALUE;

        final Project project = projectDAO.getById(projectId);

        assertNull(project);
    }

    @Test
    public void searchShouldReturnProject() {
        final ProjectSearch projectSearch = new ProjectSearch();
        projectSearch.setName("Vacaciones");

        List<Project> projects = projectDAO.search(projectSearch, new SortCriteria());

        assertEquals(1, projects.size());
        assertEquals("Vacaciones", projects.get(0).getName());
    }

    @Test
    public void insertProjectShouldGiveProjectAnId() {
        final Project project = createProject();

        projectDAO.insert(project);

        assertTrue(project.getId() > 0);
    }

    @Test
    public void updateShouldChangeProjectName() {
        Project projectToUpdate = projectDAO.getById(1);
        projectToUpdate.setName("ProjectName");

        projectDAO.update(projectToUpdate);

        Project updatedProject = projectDAO.getById(1);
        assertEquals("ProjectName", updatedProject.getName());
    }

    @Test
    public void deleteShouldRemoveProject() {
        Project projectToDelete = projectDAO.getById(1);

        projectDAO.delete(projectToDelete);

        assertNull(projectDAO.getById(1));
    }

    private Project createProject() {
        final Organization organization = new Organization();
        organization.setId(1);

        final Project project = new Project();
        project.setName("Project Test");
        project.setDescription("Project Test description");
        project.setOwnerId(1);
        project.setDepartmentId(1);
        project.setStartDate(new Date());
        project.setOpen(true);
        project.setClient(organization);

        projectDAO.insert(project);
        return project;
    }
}