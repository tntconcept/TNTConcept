package com.autentia.tnt.manager.holiday;


import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.hibernate.WorkingAgreementDAO;
import com.autentia.tnt.dao.search.HolidaySearch;
import com.autentia.tnt.dao.search.RequestHolidaySearch;
import com.autentia.tnt.util.DateMother;
import com.autentia.tnt.util.DateUtils;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.context.ApplicationContext;

import java.time.Duration;
import java.time.Month;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class UserHolidaysStateManagerTest {


    private final ApplicationContext context = mock(ApplicationContext.class);
    private final WorkingAgreementDAO workingAgreementDAO = mock(WorkingAgreementDAO.class);
    private final HolidayManager holidayManager = mock(HolidayManager.class);
    private final RequestHolidayManager requestHolidayManager = mock(RequestHolidayManager.class);

    private final UserHolidaysStateManager sut = new UserHolidaysStateManager();

    @Before
    public void setUp() {
        SpringUtils.configureTest(context);
        when(context.getBean("daoWorkingAgreement")).thenReturn(workingAgreementDAO);
        when(context.getBean("managerHoliday")).thenReturn(holidayManager);
        when(context.getBean("managerRequestHoliday")).thenReturn(requestHolidayManager);
    }

    @Test
    public void shouldCalculateAcceptedVacationsWithHolidaysAndOldAgreement() {
        final ArgumentCaptor<RequestHolidaySearch> requestHolidaySearchArgumentCaptor = ArgumentCaptor.forClass(RequestHolidaySearch.class);
        final ArgumentCaptor<HolidaySearch> holidaySearchArgumentCaptor = ArgumentCaptor.forClass(HolidaySearch.class);
        final Date now = DateMother.from(2021, 1, 1);

        when(workingAgreementDAO.loadById(user.getAgreement().getId())).thenReturn(WorkingAgreementMother.random(Duration.ofHours(1765).toMinutes(), 22));
        when(holidayManager.getAllEntities(holidaySearchArgumentCaptor.capture(), any())).thenReturn(holidays);
        when(requestHolidayManager.getAllEntities(requestHolidaySearchArgumentCaptor.capture(), any())).thenReturn(requestHolidays);

        final UserHolidaysState result = sut.calculateUserHolidaysState(user, now);

        verifyRequestHolidays(requestHolidaySearchArgumentCaptor.getValue(), expectedRequestHolidaySearch);
        verifyHolidaysSearch(holidaySearchArgumentCaptor.getValue(), buildHolidaySearch(now));
        assertThat(result.getTotalAccepted(), is(4));
        assertThat(result.getTotalYear(), is(22));
        assertThat(result.getYearAgreementHolidays(), is(22));
    }

    @Test
    public void shouldCalculateAcceptedVacationsWithHolidays() {
        final ArgumentCaptor<RequestHolidaySearch> requestHolidaySearchArgumentCaptor = ArgumentCaptor.forClass(RequestHolidaySearch.class);
        final ArgumentCaptor<HolidaySearch> holidaySearchArgumentCaptor = ArgumentCaptor.forClass(HolidaySearch.class);

        when(workingAgreementDAO.loadById(user.getAgreement().getId())).thenReturn(workingAgreement);
        when(holidayManager.getAllEntities(holidaySearchArgumentCaptor.capture(), any())).thenReturn(holidays);
        when(requestHolidayManager.getAllEntities(requestHolidaySearchArgumentCaptor.capture(), any())).thenReturn(requestHolidays);

        final UserHolidaysState result = sut.calculateUserHolidaysState(user, now);

        verifyRequestHolidays(requestHolidaySearchArgumentCaptor.getValue(), expectedRequestHolidaySearch);
        verifyHolidaysSearch(holidaySearchArgumentCaptor.getValue(), expectedHolidaySearch);
        assertThat(result.getTotalAccepted(), is(4));
        assertThat(result.getTotalYear(), is(23));
        assertThat(result.getYearAgreementHolidays(), is(23));
    }

    @Test
    public void shouldNotCalculateAcceptedVacationsWhenYearIsNull() {
        final ArgumentCaptor<RequestHolidaySearch> requestHolidaySearchArgumentCaptor = ArgumentCaptor.forClass(RequestHolidaySearch.class);
        final ArgumentCaptor<HolidaySearch> holidaySearchArgumentCaptor = ArgumentCaptor.forClass(HolidaySearch.class);

        when(workingAgreementDAO.loadById(user.getAgreement().getId())).thenReturn(workingAgreement);
        when(holidayManager.getAllEntities(holidaySearchArgumentCaptor.capture(), any())).thenReturn(holidays);
        when(requestHolidayManager.getAllEntities(requestHolidaySearchArgumentCaptor.capture(), any())).thenReturn(requestHolidays);

        final UserHolidaysState result = sut.calculateUserHolidaysState(user, null);

        assertThat(result.getTotalAccepted(), is(0));
        assertThat(result.getTotalYear(), is(0));
        assertThat(result.getYearAgreementHolidays(), is(0));
    }

    @Test
    public void shouldCalculateAcceptedVacationsOnSameContractYear() {
        final ArgumentCaptor<RequestHolidaySearch> requestHolidaySearchArgumentCaptor = ArgumentCaptor.forClass(RequestHolidaySearch.class);
        final ArgumentCaptor<HolidaySearch> holidaySearchArgumentCaptor = ArgumentCaptor.forClass(HolidaySearch.class);

        final User user = UserMother.random(workingAgreement, DateMother.from(2022, 3, 1));

        when(workingAgreementDAO.loadById(user.getAgreement().getId())).thenReturn(workingAgreement);
        when(holidayManager.getAllEntities(holidaySearchArgumentCaptor.capture(), any())).thenReturn(Collections.emptyList());
        when(requestHolidayManager.getAllEntities(requestHolidaySearchArgumentCaptor.capture(), any())).thenReturn(Collections.emptyList());

        final UserHolidaysState result = sut.calculateUserHolidaysState(user, now);

        verifyRequestHolidays(requestHolidaySearchArgumentCaptor.getValue(), expectedRequestHolidaySearch);
        verifyHolidaysSearch(holidaySearchArgumentCaptor.getValue(), expectedHolidaySearch);
        assertThat(result.getTotalAccepted(), is(0));
        assertThat(result.getTotalYear(), is(19));
        assertThat(result.getYearAgreementHolidays(), is(23));
    }

    @Test
    public void shouldCalculateAcceptedVacationsOnSameContractYearAndRequestedHolidays() {
        final ArgumentCaptor<RequestHolidaySearch> requestHolidaySearchArgumentCaptor = ArgumentCaptor.forClass(RequestHolidaySearch.class);
        final ArgumentCaptor<HolidaySearch> holidaySearchArgumentCaptor = ArgumentCaptor.forClass(HolidaySearch.class);

        final User user = UserMother.random(workingAgreement, DateMother.from(2022, 3, 1));

        when(workingAgreementDAO.loadById(user.getAgreement().getId())).thenReturn(workingAgreement);
        when(holidayManager.getAllEntities(holidaySearchArgumentCaptor.capture(), any())).thenReturn(Collections.emptyList());
        when(requestHolidayManager.getAllEntities(requestHolidaySearchArgumentCaptor.capture(), any())).thenReturn(Collections.singletonList(RequestHolidayMother.random(DateMother.from(2022, Month.AUGUST.getValue(), 1), DateMother.from(2022, Month.AUGUST.getValue(), 10))));

        final UserHolidaysState result = sut.calculateUserHolidaysState(user, now);

        verifyRequestHolidays(requestHolidaySearchArgumentCaptor.getValue(), expectedRequestHolidaySearch);
        verifyHolidaysSearch(holidaySearchArgumentCaptor.getValue(), expectedHolidaySearch);
        assertThat(result.getTotalAccepted(), is(8));
        assertThat(result.getTotalYear(), is(19));
        assertThat(result.getYearAgreementHolidays(), is(23));
    }

    @Test
    public void shouldCalculateAcceptedVacationsWithoutHolidays() {
        final ArgumentCaptor<RequestHolidaySearch> requestHolidaySearchArgumentCaptor = ArgumentCaptor.forClass(RequestHolidaySearch.class);
        final ArgumentCaptor<HolidaySearch> holidaySearchArgumentCaptor = ArgumentCaptor.forClass(HolidaySearch.class);

        when(workingAgreementDAO.loadById(user.getAgreement().getId())).thenReturn(workingAgreement);
        when(holidayManager.getAllEntities(holidaySearchArgumentCaptor.capture(), any())).thenReturn(Collections.emptyList());
        when(requestHolidayManager.getAllEntities(requestHolidaySearchArgumentCaptor.capture(), any())).thenReturn(requestHolidays);

        final UserHolidaysState result = sut.calculateUserHolidaysState(user, now);

        verifyRequestHolidays(requestHolidaySearchArgumentCaptor.getValue(), expectedRequestHolidaySearch);
        verifyHolidaysSearch(holidaySearchArgumentCaptor.getValue(), expectedHolidaySearch);
        assertThat(result.getTotalAccepted(), is(6));
        assertThat(result.getTotalYear(), is(23));
        assertThat(result.getYearAgreementHolidays(), is(23));
    }

    @Test
    public void shouldCalculateAcceptedVacationsWithoutHolidaysAndWithoutRequestedHolidays() {
        final ArgumentCaptor<RequestHolidaySearch> requestHolidaySearchArgumentCaptor = ArgumentCaptor.forClass(RequestHolidaySearch.class);
        final ArgumentCaptor<HolidaySearch> holidaySearchArgumentCaptor = ArgumentCaptor.forClass(HolidaySearch.class);

        when(workingAgreementDAO.loadById(user.getAgreement().getId())).thenReturn(workingAgreement);
        when(holidayManager.getAllEntities(holidaySearchArgumentCaptor.capture(), any())).thenReturn(Collections.emptyList());
        when(requestHolidayManager.getAllEntities(requestHolidaySearchArgumentCaptor.capture(), any())).thenReturn(Collections.emptyList());

        final UserHolidaysState result = sut.calculateUserHolidaysState(user, now);

        verifyRequestHolidays(requestHolidaySearchArgumentCaptor.getValue(), expectedRequestHolidaySearch);
        verifyHolidaysSearch(holidaySearchArgumentCaptor.getValue(), expectedHolidaySearch);
        assertThat(result.getTotalAccepted(), is(0));
        assertThat(result.getTotalYear(), is(23));
        assertThat(result.getYearAgreementHolidays(), is(23));
    }

    private static void verifyRequestHolidays(RequestHolidaySearch requestHolidaySearch, RequestHolidaySearch expectedRequestHolidaySearch) {
        assertThat(requestHolidaySearch.getState(), equalTo(expectedRequestHolidaySearch.getState()));
        assertThat(requestHolidaySearch.getStartBeginDate(), equalTo(expectedRequestHolidaySearch.getStartBeginDate()));
        assertThat(requestHolidaySearch.getEndFinalDate(), equalTo(expectedRequestHolidaySearch.getEndFinalDate()));
    }

    private static void verifyHolidaysSearch(HolidaySearch holidaySearch, HolidaySearch expectedHolidaySearch) {
        assertThat(holidaySearch.getStartDate(), equalTo(expectedHolidaySearch.getStartDate()));
        assertThat(holidaySearch.getEndDate(), equalTo(expectedHolidaySearch.getEndDate()));
    }

    private static final Date startDate = DateMother.from(2019,  Month.JANUARY.getValue(), 1, 1, 0);
    private static final Date now = DateMother.from(2022,  Month.JANUARY.getValue(), 1, 0, 0);
    private static final WorkingAgreement workingAgreement = WorkingAgreementMother.random(Duration.ofHours(1765).toMinutes(), 23);
    private static final User user = UserMother.random(workingAgreement, startDate);
    private static final HolidaySearch expectedHolidaySearch = buildHolidaySearch(now);
    private static final RequestHolidaySearch expectedRequestHolidaySearch = buildRequestHolidays(now);
    private static final List<Holiday> holidays = Arrays.asList(HolidayMother.random(DateMother.from(2022, Month.JANUARY.getValue(), 3)), HolidayMother.random(DateMother.from(2022,  Month.JANUARY.getValue(), 4)));
    private static final List<RequestHoliday> requestHolidays = Collections.singletonList(RequestHolidayMother.random(DateMother.from(2022, Month.JANUARY.getValue(), 1), DateMother.from(2022, Month.JANUARY.getValue(), 10)));

    private static RequestHolidaySearch buildRequestHolidays(Date date) {
        RequestHolidaySearch requestHolidaySearch = new RequestHolidaySearch();
        requestHolidaySearch.setUserRequest(user);
        requestHolidaySearch.setState(HolidayState.ACCEPT);
        requestHolidaySearch.setStartChargeYear(DateUtils.getFirstDayOfYear(date));
        requestHolidaySearch.setEndChargeYear(DateUtils.getLastDayOfYear(date));
        return requestHolidaySearch;
    }

    private static HolidaySearch buildHolidaySearch(Date date) {
        HolidaySearch holidaySearch = new HolidaySearch();
        holidaySearch.setStartDate(plusYears(DateUtils.getFirstDayOfYear(date), -1));
        holidaySearch.setEndDate(plusYears(DateUtils.getLastDayOfYear(date), 1));
        return holidaySearch;
    }

    public static Date plusYears(Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }
}