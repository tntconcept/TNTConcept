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
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AccountEntryDAO_IT extends IntegrationTest {

    final AccountEntryDAO accountEntryDAO;

    public AccountEntryDAO_IT() {
        accountEntryDAO = (AccountEntryDAO) SpringUtils.getSpringBean("daoAccountEntry");
    }

    @Override
    public void rollback() throws SQLException {
        super.rollback();
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().connection().prepareStatement("ALTER TABLE AccountEntry AUTO_INCREMENT=0").execute();
    }

    @Test
    public void loadByIdShouldLoadAccountEntry() {
        AccountEntry accountEntryToInsert = createAccountEntry();
        accountEntryDAO.insert(accountEntryToInsert);

        AccountEntry accountEntry = accountEntryDAO.loadById(1);

        assertEquals("Test", accountEntry.getConcept());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnException() {
        AccountEntry accountEntryToInsert = createAccountEntry();
        accountEntryDAO.insert(accountEntryToInsert);

        AccountEntry accountEntry = accountEntryDAO.loadById(2);

        assertNull(accountEntry);
    }

    @Test
    public void getByIdShouldGetAccountEntry() {
        AccountEntry accountEntryToInsert = createAccountEntry();
        accountEntryDAO.insert(accountEntryToInsert);

        AccountEntry accountEntry = accountEntryDAO.getById(1);

        assertEquals("Test", accountEntry.getConcept());
    }

    @Test
    public void getByIdShouldGetNullAccountEntry() {
        AccountEntry accountEntryToInsert = createAccountEntry();
        accountEntryDAO.insert(accountEntryToInsert);

        AccountEntry accountEntry = accountEntryDAO.getById(2);

        assertNull(accountEntry);
    }

    @Test
    public void searchShouldReturnAccountEntry() {
        AccountEntry accountEntryToInsert = createAccountEntry();
        accountEntryDAO.insert(accountEntryToInsert);

        List<AccountEntry> accountEntries = accountEntryDAO.search(new SortCriteria());

        assertTrue(accountEntries.size() > 0);
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
        AccountEntry accountEntry = createAccountEntry();
        accountEntryDAO.insert(accountEntry);
        
        accountEntry.setConcept("Update");
        accountEntryDAO.update(accountEntry);

        AccountEntry updatedAccountEntry = accountEntryDAO.getById(1);

        assertEquals("Update", updatedAccountEntry.getConcept());
    }

    @Test
    public void deleteShouldRemoveAccountEntry() {
        AccountEntry accountEntryToInsert = createAccountEntry();
        accountEntryDAO.insert(accountEntryToInsert);

        AccountEntry accountEntry = accountEntryDAO.getById(1);
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