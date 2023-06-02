package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.CreditTitle;
import com.autentia.tnt.businessobject.CreditTitleState;
import com.autentia.tnt.businessobject.CreditTitleType;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.CreditTitleSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CreditTitleDAO_IT extends IntegrationTest {
    final CreditTitleDAO creditTitleDAO;

    public CreditTitleDAO_IT() {
        creditTitleDAO = (CreditTitleDAO) SpringUtils.getSpringBean("daoCreditTitle");
    }

    @Test
    public void loadByIdShouldLoadCreditTitle() {
        final int creditTitleId = 1;

        final CreditTitle creditTitle = creditTitleDAO.loadById(creditTitleId);

        assertEquals("Test", creditTitle.getConcept());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullCreditTitle() {
        final int creditTitleId = Integer.MAX_VALUE;

        final CreditTitle creditTitle = creditTitleDAO.loadById(creditTitleId);

        assertNull(creditTitle);
    }

    @Test
    public void getByIdShouldGetCreditTitle() {
        final int creditTitleId = 1;

        final CreditTitle creditTitle = creditTitleDAO.getById(creditTitleId);

        assertEquals("Test", creditTitle.getConcept());
    }

    @Test
    public void getByIdShouldGetNullCreditTitle() {
        final int creditTitleId = Integer.MAX_VALUE;

        final CreditTitle creditTitle = creditTitleDAO.getById(creditTitleId);

        assertNull(creditTitle);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultCreditTitle() {
        CreditTitle creditTitle = createCreditTitle();
        creditTitleDAO.insert(creditTitle);

        List<CreditTitle> creditTitles = creditTitleDAO.search(new SortCriteria());

        assert(creditTitles.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedCreditTitle() {
        CreditTitle creditTitle = createCreditTitle();
        creditTitleDAO.insert(creditTitle);

        CreditTitleSearch creditTitleSearch = new CreditTitleSearch();
        creditTitleSearch.setConcept(creditTitle.getConcept());
        List<CreditTitle> creditTitles = creditTitleDAO.search(creditTitleSearch, new SortCriteria());

        assert(creditTitles.size() == 1);
    }

    @Test
    public void updateShouldChangeCreditTitleName() {
        CreditTitle creditTitleToUpdate = creditTitleDAO.getById(1);
        creditTitleToUpdate.setConcept("Update");

        creditTitleDAO.update(creditTitleToUpdate);

        CreditTitle updatedCreditTitle = creditTitleDAO.getById(1);
        assertEquals("Update", updatedCreditTitle.getConcept());
    }

    @Test
    public void deleteShouldRemoveCreditTitle() {
        CreditTitle creditTitleToDelete = creditTitleDAO.getById(1);

        creditTitleDAO.delete(creditTitleToDelete);

        assertNull(creditTitleDAO.getById(1));
    }

    private CreditTitle createCreditTitle() {
        Organization organization = new Organization();
        organization.setId(1);

        CreditTitle creditTitle = new CreditTitle();
        creditTitle.setNumber("");
        creditTitle.setConcept("CreditTitle");
        creditTitle.setAmount(BigDecimal.TEN);
        creditTitle.setState(CreditTitleState.EMITTED);
        creditTitle.setType(CreditTitleType.RECEIVED);
        creditTitle.setIssueDate(new Date());
        creditTitle.setOrganization(organization);
        creditTitle.setObservations("");
        creditTitle.setOwnerId(1);
        creditTitle.setDepartmentId(1);

        return creditTitle;
    }
}