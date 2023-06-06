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

        final AccountEntryGroup accountEntryGroup = accountEntryGroupDAO.loadById(accountEntryGroupId);

        assertEquals("Ingreso", accountEntryGroup.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullAccountEntryGroup() {
        final int accountEntryGroupId = Integer.MAX_VALUE;

        final AccountEntryGroup accountEntryGroup = accountEntryGroupDAO.loadById(accountEntryGroupId);

        assertNull(accountEntryGroup);
    }

    @Test
    public void getByIdShouldGetAccountEntryGroup() {
        final int accountEntryGroupId = 1;

        final AccountEntryGroup accountEntryGroup = accountEntryGroupDAO.getById(accountEntryGroupId);

        assertEquals("Ingreso", accountEntryGroup.getName());
    }

    @Test
    public void getByIdShouldGetNullAccountEntryGroup() {
        final int accountEntryGroupId = Integer.MAX_VALUE;

        final AccountEntryGroup accountEntryGroup = accountEntryGroupDAO.getById(accountEntryGroupId);

        assertNull(accountEntryGroup);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultAccountEntryGroup() {
        AccountEntryGroup accountEntryGroup = createAccountEntryGroup();
        accountEntryGroupDAO.insert(accountEntryGroup);

        List<AccountEntryGroup> accountEntryGroups = accountEntryGroupDAO.search(new SortCriteria());

        assert (accountEntryGroups.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedAccountEntryGroup() {
        AccountEntryGroup accountEntryGroup = createAccountEntryGroup();
        accountEntryGroupDAO.insert(accountEntryGroup);

        AccountEntryGroupSearch accountEntryGroupSearch = new AccountEntryGroupSearch();
        accountEntryGroupSearch.setName(accountEntryGroup.getName());
        List<AccountEntryGroup> accounts = accountEntryGroupDAO.search(accountEntryGroupSearch, new SortCriteria());

        assert (accounts.size() == 1);
    }

    @Test
    public void updateShouldChangeAccountEntryGroupName() {
        AccountEntryGroup accountEntryGroupToUpdate = accountEntryGroupDAO.getById(1);
        accountEntryGroupToUpdate.setName("Update");

        accountEntryGroupDAO.update(accountEntryGroupToUpdate);

        AccountEntryGroup updatedAccountEntryGroup = accountEntryGroupDAO.getById(1);
        assertEquals("Update", updatedAccountEntryGroup.getName());
    }

    @Test
    public void deleteShouldRemoveAccountEntryGroup() {
        AccountEntryGroup accountEntryGroupToDelete = accountEntryGroupDAO.getById(1);

        accountEntryGroupDAO.delete(accountEntryGroupToDelete);

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
