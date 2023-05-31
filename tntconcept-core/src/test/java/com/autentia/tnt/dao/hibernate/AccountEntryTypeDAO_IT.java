package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.AccountEntryType;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.AccountEntryTypeSearch;
import com.autentia.tnt.test.utils.AccountEntryGroupForTesting;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AccountEntryTypeDAO_IT extends IntegrationTest {

    final AccountEntryTypeDAO accountEntryTypeDAO;

    public AccountEntryTypeDAO_IT() {
        accountEntryTypeDAO = (AccountEntryTypeDAO) SpringUtils.getSpringBean("daoAccountEntryType");
    }

    @Test
    public void loadByIdShouldLoadAccountEntryType() {
        final int accountEntryTypeId = 1;

        final AccountEntryType account = accountEntryTypeDAO.loadById(accountEntryTypeId);

        assertEquals("Arranque inicial", account.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullAccountEntryType() {
        final int accountEntryTypeId = Integer.MAX_VALUE;

        final AccountEntryType accountEntryType = accountEntryTypeDAO.loadById(accountEntryTypeId);

        assertNull(accountEntryType);
    }

    @Test
    public void getByIdShouldGetAccountEntryType() {
        final int accountEntryTypeId = 1;

        final AccountEntryType accountEntryType = accountEntryTypeDAO.getById(accountEntryTypeId);

        assertEquals("Arranque inicial", accountEntryType.getName());
    }

    @Test
    public void getByIdShouldGetNullAccountEntryType() {
        final int accountEntryTypeId = Integer.MAX_VALUE;

        final AccountEntryType accountEntryType = accountEntryTypeDAO.getById(accountEntryTypeId);

        assertNull(accountEntryType);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultAccountEntryType() {
        AccountEntryType accountEntryType = createAccountEntryType();
        accountEntryTypeDAO.insert(accountEntryType);

        List<AccountEntryType> accountEntryTypes = accountEntryTypeDAO.search(new SortCriteria());

        assert(accountEntryTypes.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedAccountEntryType() {
        AccountEntryType accountEntryType = createAccountEntryType();
        accountEntryTypeDAO.insert(accountEntryType);

        AccountEntryTypeSearch accountEntryTypeSearch = new AccountEntryTypeSearch();
        accountEntryTypeSearch.setName(accountEntryType.getName());
        List<AccountEntryType> accountEntryTypes = accountEntryTypeDAO.search(accountEntryTypeSearch, new SortCriteria());

        assert(accountEntryTypes.size() == 1);
    }

    @Test
    public void updateShouldChangeAccountEntryTypeName() {
        AccountEntryType accountEntryTypeToUpdate = accountEntryTypeDAO.getById(1);
        accountEntryTypeToUpdate.setName("Update");

        accountEntryTypeDAO.update(accountEntryTypeToUpdate);

        AccountEntryType updatedAccountEntryType = accountEntryTypeDAO.getById(1);
        assertEquals("Update", updatedAccountEntryType.getName());
    }

    @Test
    public void deleteShouldRemoveAccountEntryType() {
        AccountEntryType accountEntryTypeToDelete = accountEntryTypeDAO.getById(1);

        accountEntryTypeDAO.delete(accountEntryTypeToDelete);

        assertNull(accountEntryTypeDAO.getById(1));
    }

    private AccountEntryType createAccountEntryType() {
        AccountEntryGroupForTesting accountEntryGroup = new AccountEntryGroupForTesting();
        accountEntryGroup.setId(1);

        AccountEntryType accountEntryType = new AccountEntryType();
        accountEntryType.setName("Test");
        accountEntryType.setObservations("");
        accountEntryType.setOwnerId(1);
        accountEntryType.setDepartmentId(1);
        accountEntryType.setGroup(accountEntryGroup);

        return accountEntryType;
    }
    
}
