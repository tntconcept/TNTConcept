package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Account;
import com.autentia.tnt.businessobject.AccountEntryType;
import com.autentia.tnt.businessobject.Frequency;
import com.autentia.tnt.businessobject.PeriodicalAccountEntry;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.PeriodicalAccountEntrySearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PeriodicalAccountEntryDAO_IT extends IntegrationTest {
    private final PeriodicalAccountEntryDAO periodicalAccountEntryDAO;
    private final AccountDAO accountDAO;
    private final AccountEntryTypeDAO accountEntryTypeDAO;
    private final FrequencyDAO frequencyDAO;

    public PeriodicalAccountEntryDAO_IT() {
        periodicalAccountEntryDAO = (PeriodicalAccountEntryDAO) SpringUtils.getSpringBean("daoPeriodicalAccountEntry");
        accountDAO = (AccountDAO) SpringUtils.getSpringBean("daoAccount");
        accountEntryTypeDAO = (AccountEntryTypeDAO) SpringUtils.getSpringBean("daoAccountEntryType");
        frequencyDAO = (FrequencyDAO) SpringUtils.getSpringBean("daoFrequency");
    }

    @NotNull
    private PeriodicalAccountEntry createPeriodicalAccountEntry() {
        final Account account = accountDAO.getById(1);
        final AccountEntryType accountEntryType = accountEntryTypeDAO.getById(1);
        final Frequency frequency = frequencyDAO.getById(1);

        final PeriodicalAccountEntry periodicalAccountEntry = new PeriodicalAccountEntry();
        periodicalAccountEntry.setConcept("Concept");
        periodicalAccountEntry.setDepartmentId(1);
        periodicalAccountEntry.setAccount(account);
        periodicalAccountEntry.setType(accountEntryType);
        periodicalAccountEntry.setAmount(BigDecimal.ONE);
        periodicalAccountEntry.setRise(BigDecimal.ZERO);
        periodicalAccountEntry.setObservations("Observations");
        periodicalAccountEntry.setConcept("Concept");
        periodicalAccountEntry.setFrequency(frequency);
        periodicalAccountEntry.setDate(new Date());
        return periodicalAccountEntry;
    }

    @Test
    public void getByIdShouldReturnNull() {
        final int periodicalAccountEntryId = Integer.MAX_VALUE;

        final PeriodicalAccountEntry PeriodicalAccountEntry = periodicalAccountEntryDAO.getById(periodicalAccountEntryId);

        assertNull(PeriodicalAccountEntry);
    }

    @Test
    public void getByIdShouldGetPeriodicalAccountEntry() {
        final int periodicalAccountEntryId = 1;

        final PeriodicalAccountEntry periodicalAccountEntry = periodicalAccountEntryDAO.getById(periodicalAccountEntryId);

        assertNotNull(periodicalAccountEntry);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNull() {
        final int periodicalAccountEntryId = 0;

        final PeriodicalAccountEntry periodicalAccountEntry = periodicalAccountEntryDAO.loadById(periodicalAccountEntryId);

        assertNull(periodicalAccountEntry);
    }

    @Test
    public void loadByIdShouldGetPeriodicalAccountEntry() {
        final int periodicalAccountEntryId = 1;

        final PeriodicalAccountEntry periodicalAccountEntry = periodicalAccountEntryDAO.loadById(periodicalAccountEntryId);

        assertNotNull(periodicalAccountEntry);
    }

    @Test
    public void searchShouldReturnPeriodicalAccountEntry() {
        final PeriodicalAccountEntrySearch periodicalAccountEntrySearch = new PeriodicalAccountEntrySearch();
        periodicalAccountEntrySearch.setConcept("Concept");

        final List<PeriodicalAccountEntry> periodicalAccountEntries = periodicalAccountEntryDAO.search(periodicalAccountEntrySearch, new SortCriteria());

        assertEquals(1, periodicalAccountEntries.size());
        assertEquals("Concept", periodicalAccountEntries.get(0).getConcept());
    }

    @Test
    public void insertPeriodicalAccountEntryShouldGiveInstanceId() {
        final PeriodicalAccountEntry periodicalAccountEntry = createPeriodicalAccountEntry();

        periodicalAccountEntryDAO.insert(periodicalAccountEntry);

        assertTrue(periodicalAccountEntry.getId() > 0);
    }

    @Test
    public void updateShouldChangeName() {
        final PeriodicalAccountEntry periodicalAccountEntry = periodicalAccountEntryDAO.getById(1);
        periodicalAccountEntry.setConcept("ConceptUpdate");

        periodicalAccountEntryDAO.update(periodicalAccountEntry);

        final PeriodicalAccountEntry periodicalAccountEntryUpdated = periodicalAccountEntryDAO.getById(1);
        assertEquals("ConceptUpdate", periodicalAccountEntryUpdated.getConcept());
    }

    @Test
    public void deleteShouldRemovePeriodicalAccountEntry() {
        final PeriodicalAccountEntry periodicalAccountEntry = periodicalAccountEntryDAO.getById(1);

        periodicalAccountEntryDAO.delete(periodicalAccountEntry);

        assertNull(periodicalAccountEntryDAO.getById(1));
    }

}