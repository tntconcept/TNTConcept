package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.businessobject.ProjectCost;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.ProjectCostSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class ProjectCostDAO_IT extends IntegrationTest {
    private final ProjectCostDAO projectCostDAO;
    private final ProjectDAO projectDAO;

    public ProjectCostDAO_IT() {
        projectCostDAO = (ProjectCostDAO) SpringUtils.getSpringBean("daoProjectCost");
        projectDAO = (ProjectDAO) SpringUtils.getSpringBean("daoProject");
    }

    @Test
    public void loadByIdShouldLoadProjectCost() {
        final int projectCostId = 1;

        final ProjectCost projectCost = projectCostDAO.loadById(projectCostId);

        assertEquals("Cost", projectCost.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullProjectCost() {
        final int projectCostId = Integer.MAX_VALUE;

        final ProjectCost projectCost = projectCostDAO.loadById(projectCostId);

        assertNull(projectCost);
    }

    @Test
    public void getByIdShouldGetProjectCost() {
        final int projectCostId = 1;

        final ProjectCost projectCost = projectCostDAO.getById(projectCostId);

        assertEquals("Cost", projectCost.getName());
    }

    @Test
    public void getByIdShouldGetNullProjectCost() {
        final int projectCostId = Integer.MAX_VALUE;

        final ProjectCost projectCost = projectCostDAO.getById(projectCostId);

        assertNull(projectCost);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultProjectCost() {
        final ProjectCost projectCost = createProjectCost();
        projectCostDAO.insert(projectCost);

        final List<ProjectCost> projectCosts = projectCostDAO.search(new SortCriteria());

        assertTrue(projectCosts.size() > 1);
        assertTrue(projectCost.getId() > 0);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedProjectCost() {
        final ProjectCost projectCost = createProjectCost();
        projectCostDAO.insert(projectCost);
        final ProjectCostSearch projectCostSearch = new ProjectCostSearch();
        projectCostSearch.setName(projectCost.getName());

        List<ProjectCost> projectCosts = projectCostDAO.search(projectCostSearch, new SortCriteria());

        assertEquals(1, projectCosts.size());
        assertEquals("Test", projectCosts.get(0).getName());
    }

    @Test
    public void updateShouldChangeProjectCostName() {
        final ProjectCost projectCostToUpdate = projectCostDAO.getById(1);
        projectCostToUpdate.setName("Update");

        projectCostDAO.update(projectCostToUpdate);

        final ProjectCost updatedProjectCost = projectCostDAO.getById(1);
        assertEquals("Update", updatedProjectCost.getName());
    }

    @Test
    public void deleteShouldRemoveProjectCost() {
        final ProjectCost projectCostToDelete = projectCostDAO.getById(1);

        projectCostDAO.delete(projectCostToDelete);

        assertNull(projectCostDAO.getById(1));
    }

    private ProjectCost createProjectCost() {
        final Project project = projectDAO.getById(1);
        final ProjectCost projectCost = new ProjectCost();
        projectCost.setName("Test");
        projectCost.setCost(BigDecimal.TEN);
        projectCost.setProject(project);
        projectCost.setOwnerId(1);
        projectCost.setDepartmentId(1);

        return projectCost;
    }
}