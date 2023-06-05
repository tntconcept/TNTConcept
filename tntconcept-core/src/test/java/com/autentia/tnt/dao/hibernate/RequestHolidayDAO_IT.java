package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.HolidayState;
import com.autentia.tnt.businessobject.RequestHoliday;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.RequestHolidaySearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RequestHolidayDAO_IT extends IntegrationTest {
    private final RequestHolidayDAO requestHolidayDAO;
    private final String userCommentFirstRow = "Test";

    public RequestHolidayDAO_IT() {
        requestHolidayDAO = (RequestHolidayDAO) SpringUtils.getSpringBean("daoRequestHoliday");
    }

    @Test
    public void shouldGetById() {
        final RequestHoliday result = requestHolidayDAO.getById(1);

        assertEquals(userCommentFirstRow, result.getUserComment());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final RequestHoliday result = requestHolidayDAO.getById(Integer.MAX_VALUE);

        assertNull(result);
    }

    @Test
    public void shouldLoadById() {
        final RequestHoliday result = requestHolidayDAO.loadById(1);

        assertEquals(userCommentFirstRow, result.getUserComment());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnExceptionWhenIdDoesntExists() {
        final RequestHoliday result = requestHolidayDAO.loadById(Integer.MAX_VALUE);

        assertNull(result);
    }

    @Test
    public void searchShouldFindRequestHolidays() {
        final List<RequestHoliday> result = requestHolidayDAO.search(new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void searchShouldFindRequestHolidaysByCriteria() {
        final RequestHolidaySearch requestHolidaySearch = new RequestHolidaySearch();
        requestHolidaySearch.setUserComment(userCommentFirstRow);

        final List<RequestHoliday> result = requestHolidayDAO.search(new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void updateShouldChangeRequestHoliday() {
        final String expectedUserComment = "change";
        final RequestHoliday requestHoliday = requestHolidayDAO.getById(1);
        requestHoliday.setUserComment(expectedUserComment);

        requestHolidayDAO.update(requestHoliday);
        final RequestHoliday result = requestHolidayDAO.getById(1);

        assertEquals(expectedUserComment, result.getUserComment());
    }

    @Test
    public void shouldNotFindRequestHolidayAfterDelete() {
        final RequestHoliday requestHoliday = requestHolidayDAO.getById(1);

        requestHolidayDAO.delete(requestHoliday);
        final RequestHoliday result = requestHolidayDAO.getById(1);

        assertNull(result);
    }

    @Test
    public void insertShouldPersistRequestHoliday() {
        final String expectedUserComment = "new Comment";
        final RequestHoliday requestHoliday = createRequestHoliday(expectedUserComment);

        requestHolidayDAO.insert(requestHoliday);
        final List<RequestHoliday> result = requestHolidayDAO.search(new SortCriteria());


        assertEquals(expectedUserComment, result.get(result.size() - 1).getUserComment());
    }

    private RequestHoliday createRequestHoliday(String userComment) {
        final UserForTesting user = new UserForTesting();
        user.setId(1);

        final Date date = Date.from(Instant.now());
        final RequestHoliday requestHoliday = new RequestHoliday();
        requestHoliday.setBeginDate(date);
        requestHoliday.setFinalDate(date);
        requestHoliday.setState(HolidayState.ACCEPT);
        requestHoliday.setObservations("");
        requestHoliday.setUserComment(userComment);
        requestHoliday.setUserRequest(user);
        requestHoliday.setChargeYear(date);
        return requestHoliday;
    }


}
