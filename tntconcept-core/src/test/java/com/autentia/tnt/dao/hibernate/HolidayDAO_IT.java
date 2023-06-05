package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Holiday;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.HolidaySearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class HolidayDAO_IT extends IntegrationTest {
    final HolidayDAO holidayDAO;

    public HolidayDAO_IT() {
        holidayDAO = (HolidayDAO) SpringUtils.getSpringBean("daoHoliday");
    }

    @Test
    public void loadByIdShouldLoadHoliday() {
        final int holidayId = 1;

        final Holiday holiday = holidayDAO.loadById(holidayId);

        assertEquals("Test", holiday.getDescription());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullHoliday() {
        final int holidayId = Integer.MAX_VALUE;

        final Holiday holiday = holidayDAO.loadById(holidayId);

        assertNull(holiday);
    }

    @Test
    public void getByIdShouldGetHoliday() {
        final int holidayId = 1;

        final Holiday holiday = holidayDAO.getById(holidayId);

        assertEquals("Test", holiday.getDescription());
    }

    @Test
    public void getByIdShouldGetNullHoliday() {
        final int holidayId = Integer.MAX_VALUE;

        final Holiday holiday = holidayDAO.getById(holidayId);

        assertNull(holiday);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultHoliday() {
        Holiday holiday = createHoliday();
        holidayDAO.insert(holiday);

        List<Holiday> holidays = holidayDAO.search(new SortCriteria());

        assert(holidays.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedHoliday() {
        Holiday holiday = createHoliday();
        holidayDAO.insert(holiday);

        HolidaySearch holidaySearch = new HolidaySearch();
        holidaySearch.setDescription(holiday.getDescription());
        List<Holiday> holidays = holidayDAO.search(holidaySearch, new SortCriteria());

        assert(holidays.size() == 1);
    }

    @Test
    public void updateShouldChangeHolidayName() {
        Holiday holidayToUpdate = holidayDAO.getById(1);
        holidayToUpdate.setDescription("Update");

        holidayDAO.update(holidayToUpdate);

        Holiday updatedHoliday = holidayDAO.getById(1);
        assertEquals("Update", updatedHoliday.getDescription());
    }

    @Test
    public void deleteShouldRemoveHoliday() {
        Holiday holidayToDelete = holidayDAO.getById(1);

        holidayDAO.delete(holidayToDelete);

        assertNull(holidayDAO.getById(1));
    }

    private Holiday createHoliday() {
        Holiday holiday = new Holiday();
        holiday.setDescription("Holiday");
        holiday.setDate(new Date());
        holiday.setOwnerId(1);
        holiday.setDepartmentId(1);

        return holiday;
    }
}