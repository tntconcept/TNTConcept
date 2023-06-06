package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.businessobject.ProjectRole;
import com.autentia.tnt.businessobject.TimeUnitType;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.ProjectRoleSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProjectRoleDAO_IT extends IntegrationTest {
    private final ProjectRoleDAO projectRoleDAO;
    private final String nameFirstRow = "vacaciones";

    public ProjectRoleDAO_IT() {
        projectRoleDAO = (ProjectRoleDAO) SpringUtils.getSpringBean("daoProjectRole");
    }

    @Test
    public void shouldLoadById() {
        final ProjectRole result = projectRoleDAO.loadById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnExceptionWhenIdDoesntExists() {
        final ProjectRole result = projectRoleDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        final ProjectRole result = projectRoleDAO.getById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final ProjectRole result = projectRoleDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindProjectRoles() {
        final List<ProjectRole> result = projectRoleDAO.search(new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void searchShouldFindProjectRolesByCriteria() {
        final ProjectRoleSearch projectRoleSearch = new ProjectRoleSearch();
        projectRoleSearch.setName(nameFirstRow);

        final List<ProjectRole> result = projectRoleDAO.search(projectRoleSearch, new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void updateShouldChangeObject() {
        final String expectedName = "change";
        final ProjectRole projectRole = projectRoleDAO.getById(1);
        projectRole.setName(expectedName);

        projectRoleDAO.update(projectRole);
        final ProjectRole result = projectRoleDAO.getById(1);

        assertEquals(expectedName, result.getName());
    }

    @Test
    public void shouldNotFindProjectRoleAfterDelete() {
        final ProjectRole projectRole = projectRoleDAO.getById(1);

        projectRoleDAO.delete(projectRole);
        final ProjectRole result = projectRoleDAO.getById(1);

        assertNull(result);
    }

    @Test
    public void insertShouldPersistProjectRole() {
        final String expectedName = "newRole";
        final ProjectRole projectRole = createProjectRole(expectedName);

        projectRoleDAO.insert(projectRole);
        final List<ProjectRole> result = projectRoleDAO.search(new SortCriteria());

        assertEquals(expectedName, result.get(result.size() - 1).getName());
    }

    private ProjectRole createProjectRole(String name) {
        final Project project = new Project();
        project.setId(1);

        final ProjectRole projectRole = new ProjectRole();
        projectRole.setProject(project);
        projectRole.setName(name);
        projectRole.setCostPerHour(BigDecimal.TEN);
        projectRole.setExpectedHours(10);
        projectRole.setRequiredEvidence(true);
        projectRole.setMaxAllowed(100);
        projectRole.setTimeUnit(TimeUnitType.MINUTES);
        projectRole.setIsWorkingTime(true);
        projectRole.setIsApprovalRequired(false);
        return projectRole;
    }
}
