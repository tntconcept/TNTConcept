package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.CompanyState;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.CompanyStateSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CompanyStateDAO_IT extends IntegrationTest {
    final CompanyStateDAO companyStateDAO;

    public CompanyStateDAO_IT() {
        companyStateDAO = (CompanyStateDAO) SpringUtils.getSpringBean("daoCompanyState");
    }

    @Test
    public void loadByIdShouldLoadCompanyState() {
        final int companyStateId = 1;

        final CompanyState companyState = companyStateDAO.loadById(companyStateId);

        assertEquals("Test", companyState.getDescription());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullCompanyState() {
        final int companyStateId = Integer.MAX_VALUE;

        final CompanyState companyState = companyStateDAO.loadById(companyStateId);

        assertNull(companyState);
    }

    @Test
    public void getByIdShouldGetCompanyState() {
        final int companyStateId = 1;

        final CompanyState companyState = companyStateDAO.getById(companyStateId);

        assertEquals("Test", companyState.getDescription());
    }

    @Test
    public void getByIdShouldGetNullCompanyState() {
        final int companyStateId = Integer.MAX_VALUE;

        final CompanyState companyState = companyStateDAO.getById(companyStateId);

        assertNull(companyState);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultCompanyState() {
        CompanyState companyState = createCompanyState();
        companyStateDAO.insert(companyState);

        List<CompanyState> companyStates = companyStateDAO.search(new SortCriteria());

        assert(companyStates.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedCompanyState() {
        CompanyState companyState = createCompanyState();
        companyStateDAO.insert(companyState);

        CompanyStateSearch companyStateSearch = new CompanyStateSearch();
        companyStateSearch.setDescription(companyState.getDescription());
        List<CompanyState> companyStates = companyStateDAO.search(companyStateSearch, new SortCriteria());

        assert(companyStates.size() == 1);
    }

    @Test
    public void updateShouldChangeCompanyStateName() {
        CompanyState companyStateToUpdate = companyStateDAO.getById(1);
        companyStateToUpdate.setDescription("Update");

        companyStateDAO.update(companyStateToUpdate);

        CompanyState updatedCompanyState = companyStateDAO.getById(1);
        assertEquals("Update", updatedCompanyState.getDescription());
    }

    @Test
    public void deleteShouldRemoveCompanyState() {
        CompanyState companyStateToDelete = companyStateDAO.getById(1);

        companyStateDAO.delete(companyStateToDelete);

        assertNull(companyStateDAO.getById(1));
    }

    private CompanyState createCompanyState() {
        UserForTesting user = new UserForTesting();
        user.setId(1);

        CompanyState companyState = new CompanyState();
        companyState.setUser(user);
        companyState.setCreationDate(new Date());
        companyState.setDescription("CompanyState");
        companyState.setOwnerId(1);
        companyState.setDepartmentId(1);

        return companyState;
    }
}