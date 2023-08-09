package com.autentia.tnt.manager.holiday;


import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.hibernate.WorkingAgreementDAO;
import com.autentia.tnt.dao.search.HolidaySearch;
import com.autentia.tnt.dao.search.RequestHolidaySearch;
import com.autentia.tnt.test.utils.DateMother;
import com.autentia.tnt.util.DateUtils;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.context.ApplicationContext;

import java.time.Duration;
import java.time.Month;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserHolidaysStateManagerTest {


    private static final Date now = DateMother.from(2022, Month.JANUARY.getValue(), 1, 0, 0);
    private static final Long annualWorkingTime = Duration.ofHours(1765).toMinutes();
    private static final Set<WorkingAgreementTerms> terms = new HashSet<>(
            Arrays.asList(
                    WorkingAgreementTermsMother.random(annualWorkingTime, 22, DateMother.from(1970, 1, 1)),
                    WorkingAgreementTermsMother.random(annualWorkingTime, 21, DateMother.from(1999, 8, 1)),
                    WorkingAgreementTermsMother.random(annualWorkingTime, 20, DateMother.from(1999, 10, 1)),
                    WorkingAgreementTermsMother.random(annualWorkingTime, 22, DateMother.from(2001, 2, 1)),
                    WorkingAgreementTermsMother.random(annualWorkingTime, 23, DateMother.from(2022, 7, 1))
            )
    );
    private static final WorkingAgreement workingAgreement = WorkingAgreementMother.random(terms);
    private static final User user = UserMother.random(workingAgreement, DateMother.from(2019, Month.JANUARY.getValue(), 1, 1, 0));
    private static final RequestHolidaySearch expectedRequestHolidaySearch = buildRequestHolidays(now);
    private static final HolidaySearch expectedHolidaySearch = buildHolidaySearch(now);
    private static final List<Holiday> holidays = Arrays.asList(HolidayMother.random(DateMother.from(2022, Month.JANUARY.getValue(), 3)), HolidayMother.random(DateMother.from(2022, Month.JANUARY.getValue(), 4)));
    private static final List<RequestHoliday> requestHolidays = Collections.singletonList(RequestHolidayMother.random(DateMother.from(2022, Month.JANUARY.getValue(), 1), DateMother.from(2022, Month.JANUARY.getValue(), 10)));
    private final ApplicationContext context = mock(ApplicationContext.class);
    private final WorkingAgreementDAO workingAgreementDAO = mock(WorkingAgreementDAO.class);
    private final HolidayManager holidayManager = mock(HolidayManager.class);
    private final RequestHolidayManager requestHolidayManager = mock(RequestHolidayManager.class);
    private final UserHolidaysStateManager sut = new UserHolidaysStateManager();

    private static void verifyRequestHolidays(RequestHolidaySearch requestHolidaySearch, RequestHolidaySearch expectedRequestHolidaySearch) {
        assertEquals(expectedRequestHolidaySearch.getState(), requestHolidaySearch.getState());
        assertEquals(requestHolidaySearch.getStartBeginDate(), expectedRequestHolidaySearch.getStartBeginDate());
        assertEquals(requestHolidaySearch.getEndFinalDate(), expectedRequestHolidaySearch.getEndFinalDate());
    }

    private static void verifyHolidaysSearch(HolidaySearch holidaySearch, HolidaySearch expectedHolidaySearch) {
        assertEquals(expectedHolidaySearch.getStartDate(), holidaySearch.getStartDate());
        assertEquals(expectedHolidaySearch.getEndDate(), holidaySearch.getEndDate());
    }

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

        when(workingAgreementDAO.loadById(user.getAgreement().getId())).thenReturn(WorkingAgreementMother.random(terms));
        when(holidayManager.getAllEntities(holidaySearchArgumentCaptor.capture(), any())).thenReturn(holidays);
        when(requestHolidayManager.getAllEntities(requestHolidaySearchArgumentCaptor.capture(), any())).thenReturn(requestHolidays);

        final UserHolidaysState result = sut.calculateUserHolidaysState(user, now);

        verifyRequestHolidays(requestHolidaySearchArgumentCaptor.getValue(), expectedRequestHolidaySearch);
        verifyHolidaysSearch(holidaySearchArgumentCaptor.getValue(), buildHolidaySearch(now));
        assertEquals(4, result.getTotalAccepted());
        assertEquals(22, result.getTotalYear());
        assertEquals(22, result.getYearAgreementHolidays());
    }

    @Test
    public void shouldCalculateAcceptedVacationsWithHolidaysAndSelectEffectiveAgreementWhenThereAreMoreThanOneChangeInTheSameYear() {
        final ArgumentCaptor<RequestHolidaySearch> requestHolidaySearchArgumentCaptor = ArgumentCaptor.forClass(RequestHolidaySearch.class);
        final ArgumentCaptor<HolidaySearch> holidaySearchArgumentCaptor = ArgumentCaptor.forClass(HolidaySearch.class);
        final Date now = DateMother.from(1999, 1, 1);
        final User user = UserMother.random(workingAgreement, DateMother.from(1998, 1, 1));

        when(workingAgreementDAO.loadById(user.getAgreement().getId())).thenReturn(WorkingAgreementMother.random(terms));
        when(holidayManager.getAllEntities(holidaySearchArgumentCaptor.capture(), any())).thenReturn(Collections.singletonList(HolidayMother.random(DateMother.from(1999, Month.JANUARY.getValue(), 3))));
        when(requestHolidayManager.getAllEntities(requestHolidaySearchArgumentCaptor.capture(), any())).thenReturn(Collections.singletonList(RequestHolidayMother.random(DateMother.from(1999, Month.JANUARY.getValue(), 1), DateMother.from(1999, Month.JANUARY.getValue(), 5))));

        final UserHolidaysState result = sut.calculateUserHolidaysState(user, now);

        verifyRequestHolidays(requestHolidaySearchArgumentCaptor.getValue(), expectedRequestHolidaySearch);
        verifyHolidaysSearch(holidaySearchArgumentCaptor.getValue(), buildHolidaySearch(now));
        assertEquals(3, result.getTotalAccepted());
        assertEquals(20, result.getTotalYear());
        assertEquals(20, result.getYearAgreementHolidays());
    }

    @Test
    public void shouldCalculateAcceptedVacationsWithHolidaysAndSelectCurrentEffectiveAgreement() {
        final ArgumentCaptor<RequestHolidaySearch> requestHolidaySearchArgumentCaptor = ArgumentCaptor.forClass(RequestHolidaySearch.class);
        final ArgumentCaptor<HolidaySearch> holidaySearchArgumentCaptor = ArgumentCaptor.forClass(HolidaySearch.class);
        final Date now = DateMother.from(2025, 1, 1);
        final User user = UserMother.random(workingAgreement, DateMother.from(2000, 1, 1));

        when(workingAgreementDAO.loadById(user.getAgreement().getId())).thenReturn(WorkingAgreementMother.random(terms));
        when(holidayManager.getAllEntities(holidaySearchArgumentCaptor.capture(), any())).thenReturn(Collections.singletonList(HolidayMother.random(DateMother.from(2025, Month.JANUARY.getValue(), 3))));
        when(requestHolidayManager.getAllEntities(requestHolidaySearchArgumentCaptor.capture(), any())).thenReturn(Collections.singletonList(RequestHolidayMother.random(DateMother.from(2025, Month.JANUARY.getValue(), 1), DateMother.from(2025, Month.JANUARY.getValue(), 5))));

        final UserHolidaysState result = sut.calculateUserHolidaysState(user, now);

        verifyRequestHolidays(requestHolidaySearchArgumentCaptor.getValue(), expectedRequestHolidaySearch);
        verifyHolidaysSearch(holidaySearchArgumentCaptor.getValue(), buildHolidaySearch(now));
        assertEquals(2, result.getTotalAccepted());
        assertEquals(23, result.getTotalYear());
        assertEquals(23, result.getYearAgreementHolidays());
    }

    @Test
    public void shouldReturnZeroVacationsWhenAgreementDoesNotExist() {
        final ArgumentCaptor<RequestHolidaySearch> requestHolidaySearchArgumentCaptor = ArgumentCaptor.forClass(RequestHolidaySearch.class);
        final ArgumentCaptor<HolidaySearch> holidaySearchArgumentCaptor = ArgumentCaptor.forClass(HolidaySearch.class);
        final Date now = DateMother.from(1960, 1, 1);
        final User user = UserMother.random(workingAgreement, DateMother.from(1998, 1, 1));

        when(workingAgreementDAO.loadById(user.getAgreement().getId())).thenReturn(WorkingAgreementMother.random(terms));
        when(holidayManager.getAllEntities(holidaySearchArgumentCaptor.capture(), any())).thenReturn(Collections.emptyList());
        when(requestHolidayManager.getAllEntities(requestHolidaySearchArgumentCaptor.capture(), any())).thenReturn(Collections.emptyList());

        final UserHolidaysState result = sut.calculateUserHolidaysState(user, now);

        verifyRequestHolidays(requestHolidaySearchArgumentCaptor.getValue(), expectedRequestHolidaySearch);
        verifyHolidaysSearch(holidaySearchArgumentCaptor.getValue(), buildHolidaySearch(now));
        assertEquals(0, result.getTotalAccepted());
        assertEquals(0, result.getTotalYear());
        assertEquals(0, result.getYearAgreementHolidays());
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
        assertEquals(4, result.getTotalAccepted());
        assertEquals(23, result.getTotalYear());
        assertEquals(23, result.getYearAgreementHolidays());
    }

    @Test
    public void shouldNotCalculateAcceptedVacationsWhenYearIsNull() {
        final ArgumentCaptor<RequestHolidaySearch> requestHolidaySearchArgumentCaptor = ArgumentCaptor.forClass(RequestHolidaySearch.class);
        final ArgumentCaptor<HolidaySearch> holidaySearchArgumentCaptor = ArgumentCaptor.forClass(HolidaySearch.class);

        when(workingAgreementDAO.loadById(user.getAgreement().getId())).thenReturn(workingAgreement);
        when(holidayManager.getAllEntities(holidaySearchArgumentCaptor.capture(), any())).thenReturn(holidays);
        when(requestHolidayManager.getAllEntities(requestHolidaySearchArgumentCaptor.capture(), any())).thenReturn(requestHolidays);

        final UserHolidaysState result = sut.calculateUserHolidaysState(user, null);

        assertEquals(0, result.getTotalAccepted());
        assertEquals(0, result.getTotalYear());
        assertEquals(0, result.getYearAgreementHolidays());
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
        assertEquals(0, result.getTotalAccepted());
        assertEquals(19, result.getTotalYear());
        assertEquals(23, result.getYearAgreementHolidays());
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
        assertEquals(8, result.getTotalAccepted());
        assertEquals(19, result.getTotalYear());
        assertEquals(23, result.getYearAgreementHolidays());
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
        assertEquals(6, result.getTotalAccepted());
        assertEquals(23, result.getTotalYear());
        assertEquals(23, result.getYearAgreementHolidays());
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
        assertEquals(0, result.getTotalAccepted());
        assertEquals(23, result.getTotalYear());
        assertEquals(23, result.getYearAgreementHolidays());
    }
}