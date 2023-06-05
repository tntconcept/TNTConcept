package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.FinancialRatio;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.FinancialRatioSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class FinancialRatioDAO_IT extends IntegrationTest {
    final FinancialRatioDAO financialRatioDAO;

    public FinancialRatioDAO_IT() {
        financialRatioDAO = (FinancialRatioDAO) SpringUtils.getSpringBean("daoFinancialRatio");
    }

    @Test
    public void loadByIdShouldLoadFinancialRatio() {
        final int financialRatioId = 1;

        final FinancialRatio financialRatio = financialRatioDAO.loadById(financialRatioId);

        assertEquals("Test", financialRatio.getTitle());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullFinancialRatio() {
        final int financialRatioId = Integer.MAX_VALUE;

        final FinancialRatio financialRatio = financialRatioDAO.loadById(financialRatioId);

        assertNull(financialRatio);
    }

    @Test
    public void getByIdShouldGetFinancialRatio() {
        final int financialRatioId = 1;

        final FinancialRatio financialRatio = financialRatioDAO.getById(financialRatioId);

        assertEquals("Test", financialRatio.getTitle());
    }

    @Test
    public void getByIdShouldGetNullFinancialRatio() {
        final int financialRatioId = Integer.MAX_VALUE;

        final FinancialRatio financialRatio = financialRatioDAO.getById(financialRatioId);

        assertNull(financialRatio);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultFinancialRatio() {
        FinancialRatio financialRatio = createFinancialRatio();
        financialRatioDAO.insert(financialRatio);

        List<FinancialRatio> financialRatios = financialRatioDAO.search(new SortCriteria());

        assert(financialRatios.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedFinancialRatio() {
        FinancialRatio financialRatio = createFinancialRatio();
        financialRatioDAO.insert(financialRatio);

        FinancialRatioSearch financialRatioSearch = new FinancialRatioSearch();
        financialRatioSearch.setTitle(financialRatio.getTitle());
        List<FinancialRatio> financialRatios = financialRatioDAO.search(financialRatioSearch, new SortCriteria());

        assert(financialRatios.size() == 1);
    }

    @Test
    public void updateShouldChangeFinancialRatioName() {
        FinancialRatio financialRatioToUpdate = financialRatioDAO.getById(1);
        financialRatioToUpdate.setTitle("Update");

        financialRatioDAO.update(financialRatioToUpdate);

        FinancialRatio updatedFinancialRatio = financialRatioDAO.getById(1);
        assertEquals("Update", updatedFinancialRatio.getTitle());
    }

    @Test
    public void deleteShouldRemoveFinancialRatio() {
        FinancialRatio financialRatioToDelete = financialRatioDAO.getById(1);

        financialRatioDAO.delete(financialRatioToDelete);

        assertNull(financialRatioDAO.getById(1));
    }

    private FinancialRatio createFinancialRatio() {
        FinancialRatio financialRatio = new FinancialRatio();
        financialRatio.setTitle("FinancialRatio");
        financialRatio.setRatioDate(new Date());
        financialRatio.setBanksAccounts(BigDecimal.ONE);
        financialRatio.setCustomers(BigDecimal.ZERO);
        financialRatio.setStocks(BigDecimal.ZERO);
        financialRatio.setAmortizations(BigDecimal.ZERO);
        financialRatio.setInfrastructures(BigDecimal.ZERO);
        financialRatio.setShortTermLiability(BigDecimal.ZERO);
        financialRatio.setObligationBond(BigDecimal.ZERO);
        financialRatio.setCapital(BigDecimal.ZERO);
        financialRatio.setReserves(BigDecimal.ZERO);
        financialRatio.setIncomes(BigDecimal.ZERO);
        financialRatio.setExpenses(BigDecimal.ZERO);
        financialRatio.setOtherExploitationExpenses(BigDecimal.ZERO);
        financialRatio.setFinancialExpenses(BigDecimal.ZERO);
        financialRatio.setTaxes(BigDecimal.ZERO);
        financialRatio.setOwnerId(1);
        financialRatio.setDepartmentId(1);

        return financialRatio;
    }
}