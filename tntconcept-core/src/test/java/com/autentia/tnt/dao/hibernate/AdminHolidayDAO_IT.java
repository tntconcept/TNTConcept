package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.AdminHoliday;
import com.autentia.tnt.businessobject.HolidayState;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.AdminHolidaySearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AdminHolidayDAO_IT extends IntegrationTest {
    final AdminHolidayDAO adminHolidayDAO;

    public AdminHolidayDAO_IT() {
        adminHolidayDAO = (AdminHolidayDAO) SpringUtils.getSpringBean("daoAdminHoliday");
    }

    @Test
    public void loadByIdShouldLoadAdminHoliday() {
        final int adminHolidayId = 1;

        final AdminHoliday adminHoliday = adminHolidayDAO.loadById(adminHolidayId);

        assertEquals("Test", adminHoliday.getUserComment());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullAdminHoliday() {
        final int adminHolidayId = Integer.MAX_VALUE;

        final AdminHoliday adminHoliday = adminHolidayDAO.loadById(adminHolidayId);

        assertNull(adminHoliday);
    }

    @Test
    public void getByIdShouldGetAdminHoliday() {
        final int adminHolidayId = 1;

        final AdminHoliday adminHoliday = adminHolidayDAO.getById(adminHolidayId);

        assertEquals("Test", adminHoliday.getUserComment());
    }

    @Test
    public void getByIdShouldGetNullAdminHoliday() {
        final int adminHolidayId = Integer.MAX_VALUE;

        final AdminHoliday adminHoliday = adminHolidayDAO.getById(adminHolidayId);

        assertNull(adminHoliday);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultAdminHoliday() {
        AdminHoliday adminHoliday = createAdminHoliday();
        adminHolidayDAO.insert(adminHoliday);

        List<AdminHoliday> adminHolidays = adminHolidayDAO.search(new SortCriteria());

        assert(adminHolidays.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedAdminHoliday() {
        AdminHoliday adminHoliday = createAdminHoliday();
        adminHolidayDAO.insert(adminHoliday);

        AdminHolidaySearch adminHolidaySearch = new AdminHolidaySearch();
        adminHolidaySearch.setUserComment(adminHoliday.getUserComment());
        List<AdminHoliday> adminHolidays = adminHolidayDAO.search(adminHolidaySearch, new SortCriteria());

        assert(adminHolidays.size() == 1);
    }

    @Test
    public void updateShouldChangeAdminHolidayName() {
        AdminHoliday adminHolidayToUpdate = adminHolidayDAO.getById(1);
        adminHolidayToUpdate.setUserComment("Update");

        adminHolidayDAO.update(adminHolidayToUpdate);

        AdminHoliday updatedAdminHoliday = adminHolidayDAO.getById(1);
        assertEquals("Update", updatedAdminHoliday.getUserComment());
    }

    @Test
    public void deleteShouldRemoveAdminHoliday() {
        AdminHoliday adminHolidayToDelete = adminHolidayDAO.getById(1);

        adminHolidayDAO.delete(adminHolidayToDelete);

        assertNull(adminHolidayDAO.getById(1));
    }

    private AdminHoliday createAdminHoliday() {
        UserForTesting user = new UserForTesting();
        user.setId(1);

        AdminHoliday adminHoliday = new AdminHoliday();
        adminHoliday.setBeginDate(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        adminHoliday.setFinalDate(calendar.getTime());

        adminHoliday.setState(HolidayState.PENDING);
        adminHoliday.setUserRequest(user);
        adminHoliday.setObservations("");
        adminHoliday.setUserComment("AdminHoliday");
        adminHoliday.setChargeYear(new Date());
        adminHoliday.setOwnerId(1);
        adminHoliday.setDepartmentId(1);

        return adminHoliday;
    }
}