package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.AccountEntryGroup;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.AccountEntryGroupSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AccountEntryGroupDAO_IT extends IntegrationTest {
    final AccountEntryGroupDAO accountEntryGroupDAO;

    public AccountEntryGroupDAO_IT() {
        accountEntryGroupDAO = (AccountEntryGroupDAO) SpringUtils.getSpringBean("daoAccountEntryGroup");
    }

    @Test
    public void loadByIdShouldLoadAccountEntryGroup() {
        final int accountEntryGroupId = 1;

        final AccountEntryGroup account = accountEntryGroupDAO.loadById(accountEntryGroupId);

        assertEquals("Ingreso", account.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullAccountEntryGroup() {
        final int accountId = Integer.MAX_VALUE;

        final AccountEntryGroup account = accountEntryGroupDAO.loadById(accountId);

        assertNull(account);
    }

    @Test
    public void getByIdShouldGetAccountEntryGroup() {
        final int accountId = 1;

        final AccountEntryGroup account = accountEntryGroupDAO.getById(accountId);

        assertEquals("Ingreso", account.getName());
    }

    @Test
    public void getByIdShouldGetNullAccountEntryGroup() {
        final int accountId = Integer.MAX_VALUE;

        final AccountEntryGroup account = accountEntryGroupDAO.getById(accountId);

        assertNull(account);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultAccountEntryGroup() {
        AccountEntryGroup account = createAccountEntryGroup();
        accountEntryGroupDAO.insert(account);

        List<AccountEntryGroup> accounts = accountEntryGroupDAO.search(new SortCriteria());

        assert(accounts.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedAccountEntryGroup() {
        AccountEntryGroup account = createAccountEntryGroup();
        accountEntryGroupDAO.insert(account);

        AccountEntryGroupSearch accountSearch = new AccountEntryGroupSearch();
        accountSearch.setName(account.getName());
        List<AccountEntryGroup> accounts = accountEntryGroupDAO.search(accountSearch, new SortCriteria());

        assert(accounts.size() == 1);
    }

    @Test
    public void updateShouldChangeAccountEntryGroupName() {
        AccountEntryGroup accountToUpdate = accountEntryGroupDAO.getById(1);
        accountToUpdate.setName("Update");

        accountEntryGroupDAO.update(accountToUpdate);

        AccountEntryGroup updatedAccountEntryGroup = accountEntryGroupDAO.getById(1);
        assertEquals("Update", updatedAccountEntryGroup.getName());
    }

    @Test
    public void deleteShouldRemoveAccountEntryGroup() {
        AccountEntryGroup accountToDelete = accountEntryGroupDAO.getById(1);

        accountEntryGroupDAO.delete(accountToDelete);

        assertNull(accountEntryGroupDAO.getById(1));
    }

    private AccountEntryGroup createAccountEntryGroup() {
        AccountEntryGroup accountEntryGroup = new AccountEntryGroup();
        accountEntryGroup.setName("Test");
        accountEntryGroup.setDescription("");
        accountEntryGroup.setOwnerId(1);
        accountEntryGroup.setDepartmentId(1);

        return accountEntryGroup;
    }
}
