package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.AccountEntry;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.AccountEntrySearch;
import com.autentia.tnt.test.utils.AccountEntryTypeForTesting;
import com.autentia.tnt.test.utils.AccountForTesting;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AccountEntryDAO_IT extends IntegrationTest {

    final AccountEntryDAO accountEntryDAO;

    public AccountEntryDAO_IT() {
        accountEntryDAO = (AccountEntryDAO) SpringUtils.getSpringBean("daoAccountEntry");
    }

    @Test
    public void loadByIdShouldLoadAccountEntry() {
        final int accountEntryId = 1;

        AccountEntry accountEntry = accountEntryDAO.loadById(accountEntryId);

        assertEquals("Test", accountEntry.getConcept());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnException() {
        final int accountEntryId = Integer.MAX_VALUE;

        AccountEntry accountEntry = accountEntryDAO.loadById(accountEntryId);

        assertNull(accountEntry);
    }

    @Test
    public void getByIdShouldGetAccountEntry() {
        final int accountEntryId = 1;

        AccountEntry accountEntry = accountEntryDAO.getById(accountEntryId);

        assertEquals("Test", accountEntry.getConcept());
    }

    @Test
    public void getByIdShouldGetNullAccountEntry() {
        final int accountEntryId = Integer.MAX_VALUE;

        AccountEntry accountEntry = accountEntryDAO.getById(accountEntryId);

        assertNull(accountEntry);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultAccountEntry() {
        AccountEntry accountEntryToInsert = createAccountEntry();
        accountEntryDAO.insert(accountEntryToInsert);

        List<AccountEntry> accountEntries = accountEntryDAO.search(new SortCriteria());

        assertTrue(accountEntries.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedAccountEntry() {
        AccountEntry accountEntryToInsert = createAccountEntry();
        accountEntryDAO.insert(accountEntryToInsert);

        AccountEntrySearch accountEntrySearch = new AccountEntrySearch();
        accountEntrySearch.setConcept(accountEntryToInsert.getConcept());
        List<AccountEntry> accountEntries = accountEntryDAO.search(accountEntrySearch, new SortCriteria());

        assertTrue(accountEntries.size() > 0);
    }

    @Test
    public void updateShouldChangeAccountEntryConcept() {
        final int accountEntryId = 1;
        AccountEntry accountEntry = accountEntryDAO.loadById(accountEntryId);
        
        accountEntry.setConcept("Update");
        accountEntryDAO.update(accountEntry);

        AccountEntry updatedAccountEntry = accountEntryDAO.getById(1);

        assertEquals("Update", updatedAccountEntry.getConcept());
    }

    @Test
    public void deleteShouldRemoveAccountEntry() {
        final int accountEntryId = 1;
        AccountEntry accountEntry = accountEntryDAO.getById(accountEntryId);

        accountEntryDAO.delete(accountEntry);

        assertNull(accountEntryDAO.getById(1));
    }

    private AccountEntry createAccountEntry() {
        AccountForTesting account = new AccountForTesting();
        account.setId(1);

        AccountEntryTypeForTesting accountEntryType = new AccountEntryTypeForTesting();
        accountEntryType.setId(1);

        AccountEntry accountEntry = new AccountEntry();
        accountEntry.setAccount(account);
        accountEntry.setType(accountEntryType);
        accountEntry.setDate(new Date());
        accountEntry.setAmountDate(new Date());
        accountEntry.setConcept("Test");
        accountEntry.setAmount(BigDecimal.ZERO);
        accountEntry.setObservations("");

        return accountEntry;
    }
}