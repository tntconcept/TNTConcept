package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.WorkingAgreement;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.WorkingAgreementSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class WorkingAgreementDAO_IT extends IntegrationTest {
    private final WorkingAgreementDAO workingAgreementDAO;

    public WorkingAgreementDAO_IT() {
        this.workingAgreementDAO = (WorkingAgreementDAO) SpringUtils.getSpringBean("daoWorkingAgreement");
    }

    @Test
    public void getByIdShouldReturnNull() {
        final int workingAgreementId = Integer.MAX_VALUE;

        final WorkingAgreement WorkingAgreement = workingAgreementDAO.getById(workingAgreementId);

        assertNull(WorkingAgreement);
    }

    @Test
    public void getByIdShouldGetWorkingAgreement() {
        final int workingAgreementId = 1;

        final WorkingAgreement workingAgreement = workingAgreementDAO.getById(workingAgreementId);

        assertNotNull(workingAgreement);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNull() {
        final int WorkingAgreementId = 0;

        final WorkingAgreement workingAgreement = workingAgreementDAO.loadById(WorkingAgreementId);

        assertNull(workingAgreement);
    }

    @Test
    public void loadByIdShouldGetWorkingAgreement() {
        final int workingAgreementId = 1;

        final WorkingAgreement workingAgreement = workingAgreementDAO.loadById(workingAgreementId);

        assertNotNull(workingAgreement);
    }

    @Test
    public void searchShouldReturnWorkingAgreement() {
        final WorkingAgreementSearch workingAgreementSearch = new WorkingAgreementSearch();
        workingAgreementSearch.setName("Convenio Nuestra Empresa");

        final List<WorkingAgreement> workingAgreements = workingAgreementDAO.search(workingAgreementSearch, new SortCriteria());

        assertEquals(1, workingAgreements.size());
        assertEquals("Convenio Nuestra Empresa", workingAgreements.get(0).getName());
    }

    @Test
    public void searchShouldReturnWorkingAgreementTerms() {
        final WorkingAgreementSearch workingAgreementSearch = new WorkingAgreementSearch();
        workingAgreementSearch.setName("Convenio Nuestra Empresa");

        final List<WorkingAgreement> workingAgreements = workingAgreementDAO.search(workingAgreementSearch, new SortCriteria());

        assertEquals(2, workingAgreements.get(0).getTerms().size());
    }

    @Test
    public void searchShouldReturnNoResults() {
        final WorkingAgreementSearch workingAgreementSearch = new WorkingAgreementSearch();
        workingAgreementSearch.setName("Other");

        final List<WorkingAgreement> workingAgreements = workingAgreementDAO.search(workingAgreementSearch, new SortCriteria());

        assertTrue(workingAgreements.isEmpty());
    }

    @Test
    public void updateShouldChangeName() {
        final WorkingAgreement workingAgreement = workingAgreementDAO.getById(1);
        workingAgreement.setName("Convenio colectivo");

        workingAgreementDAO.update(workingAgreement);

        final WorkingAgreement workingAgreementUpdatedName = workingAgreementDAO.getById(1);
        assertEquals("Convenio colectivo", workingAgreementUpdatedName.getName());
    }

    @Test
    public void deleteShouldRemoveWorkingAgreement() {
        final WorkingAgreement workingAgreement = workingAgreementDAO.getById(1);

        workingAgreementDAO.delete(workingAgreement);

        assertNull(workingAgreementDAO.getById(1));
    }
}
