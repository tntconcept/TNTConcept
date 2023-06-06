package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.AccountType;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.AccountTypeSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AccountTypeDAO_IT extends IntegrationTest {
    final AccountTypeDAO accountTypeDAO;

    public AccountTypeDAO_IT() {
        accountTypeDAO = (AccountTypeDAO) SpringUtils.getSpringBean("daoAccountType");
    }

    @Test
    public void loadByIdShouldLoadAccountType() {
        final int accountTypeId = 1;

        final AccountType accountType = accountTypeDAO.loadById(accountTypeId);

        assertEquals("Caja", accountType.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullAccountType() {
        final int accountTypeId = Integer.MAX_VALUE;

        final AccountType accountType = accountTypeDAO.loadById(accountTypeId);

        assertNull(accountType);
    }

    @Test
    public void getByIdShouldGetAccountType() {
        final int accountTypeId = 1;

        final AccountType accountType = accountTypeDAO.getById(accountTypeId);

        assertEquals("Caja", accountType.getName());
    }

    @Test
    public void getByIdShouldGetNullAccountType() {
        final int accountTypeId = Integer.MAX_VALUE;

        final AccountType accountType = accountTypeDAO.getById(accountTypeId);

        assertNull(accountType);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultAccountType() {
        AccountType accountType = createAccountType();
        accountTypeDAO.insert(accountType);

        List<AccountType> accountTypes = accountTypeDAO.search(new SortCriteria());

        assert (accountTypes.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedAccountType() {
        AccountType accountType = createAccountType();
        accountTypeDAO.insert(accountType);

        AccountTypeSearch accountSearch = new AccountTypeSearch();
        accountSearch.setName(accountType.getName());
        List<AccountType> accountTypes = accountTypeDAO.search(accountSearch, new SortCriteria());

        assert (accountTypes.size() == 1);
    }

    @Test
    public void updateShouldChangeAccountName() {
        AccountType accountTypeToUpdate = accountTypeDAO.getById(1);
        accountTypeToUpdate.setName("Update");

        accountTypeDAO.update(accountTypeToUpdate);

        AccountType updatedAccountType = accountTypeDAO.getById(1);
        assertEquals("Update", updatedAccountType.getName());
    }

    @Test
    public void deleteShouldRemoveAccountType() {
        AccountType accountTypeToDelete = accountTypeDAO.getById(1);

        accountTypeDAO.delete(accountTypeToDelete);

        assertNull(accountTypeDAO.getById(1));
    }

    private AccountType createAccountType() {
        AccountType accountType = new AccountType();
        accountType.setName("Test");
        accountType.setOwnerId(1);
        accountType.setDepartmentId(1);

        return accountType;
    }
}