package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Account;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.AccountSearch;
import com.autentia.tnt.test.utils.AccountTypeForTesting;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AccountDAO_IT extends IntegrationTest {

    final AccountDAO accountDAO;

    public AccountDAO_IT() {
        accountDAO = (AccountDAO) SpringUtils.getSpringBean("daoAccount");
    }

    @Test
    public void loadByIdShouldLoadAccount() {
        final int accountId = 1;

        final Account account = accountDAO.loadById(accountId);

        assertEquals("Caja", account.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullAccount() {
        final int accountId = Integer.MAX_VALUE;

        final Account account = accountDAO.loadById(accountId);

        assertNull(account);
    }

    @Test
    public void getByIdShouldGetAccount() {
        final int accountId = 1;

        final Account account = accountDAO.getById(accountId);

        assertEquals("Caja", account.getName());
    }

    @Test
    public void getByIdShouldGetNullAccount() {
        final int accountId = Integer.MAX_VALUE;

        final Account account = accountDAO.getById(accountId);

        assertNull(account);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultAccount() {
        Account account = createAccount();
        accountDAO.insert(account);

        List<Account> accounts = accountDAO.search(new SortCriteria());

        assert(accounts.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedAccount() {
        Account account = createAccount();
        accountDAO.insert(account);

        AccountSearch accountSearch = new AccountSearch();
        accountSearch.setName(account.getName());
        List<Account> accounts = accountDAO.search(accountSearch, new SortCriteria());

        assert(accounts.size() == 1);
    }

    @Test
    public void updateShouldChangeAccountName() {
        Account accountToUpdate = accountDAO.getById(1);
        accountToUpdate.setName("Update");

        accountDAO.update(accountToUpdate);

        Account updatedAccount = accountDAO.getById(1);
        assertEquals("Update", updatedAccount.getName());
    }

    @Test
    public void deleteShouldRemoveAccount() {
        Account accountToDelete = accountDAO.getById(1);

        accountDAO.delete(accountToDelete);

        assertNull(accountDAO.getById(1));
    }

    private Account createAccount() {
        AccountTypeForTesting accountType = new AccountTypeForTesting();
        accountType.setId(1);

        Account account = new Account();
        account.setNumber("");
        account.setName("Test");
        account.setDescription("");
        account.setOwnerId(1);
        account.setDepartmentId(1);
        account.setType(accountType);

        return account;
    }
}