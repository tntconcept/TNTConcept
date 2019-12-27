package com.autentia.tnt.manager.holiday;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.mockito.Mockito.*;

import com.autentia.tnt.businessobject.Holiday;
import com.autentia.tnt.dao.search.HolidaySearch;
import com.autentia.tnt.util.SpringUtils;

public class CorrespondingHolidayManagerTest {

	final CorrespondingHolidayManager sut = new CorrespondingHolidayManager();
	
	private  HolidayManager holidayManager;	
	final static ApplicationContext ctx = mock(ApplicationContext.class);
	
	@Before
	public void setUp() {
		holidayManager = mock(HolidayManager.class);
		when(ctx.getBean("managerHoliday")).thenReturn(holidayManager);
		SpringUtils.configureTest(ctx);
	}
	
	@Test
	public void shouldRemoveCompensationDaysIfHiredLater() {
		List<Holiday> holidays = this.getListOfCompensationDays(1);
		Calendar calMin = new GregorianCalendar(2019, Calendar.JANUARY, 1);
		Calendar calMax = new GregorianCalendar(2019, Calendar.DECEMBER, 31);
		Date hiringDate = new GregorianCalendar(2019, Calendar.JANUARY, 10).getTime();
		Holiday newYear2019 = new Holiday();
		newYear2019.setCompensation(false);
		newYear2019.setDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
		List<Holiday> january2019 = Arrays.asList(newYear2019);
		doReturn(january2019).when(holidayManager).getAllEntities(any(), any());
		
		List<Holiday> result = sut.calculateCorrespondingHolidays(calMin, calMax, holidays, hiringDate);
		
		assertThat(result, is(empty()));
	}
	
	@Test
	public void shouldNotRemoveCompensationDaysIfHiredPreviously() {
		
		List<Holiday> holidays = this.getListOfCompensationDays(1);
		LocalDate hiringDate = new GregorianCalendar(2018, Calendar.JANUARY, 10).getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		List<Holiday> result = sut.getCorrespondingHolidays(holidays, hiringDate, 2019);
		
		assertThat(result, is(not(empty())));
	}
	
	@Test
	public void shouldConsiderWeekendsAndHolidaysForCalculation() {	
		List<Holiday> holidays = this.getListOfCompensationDays(5);
		Holiday newYear2019 = new Holiday();
		newYear2019.setCompensation(false);
		newYear2019.setDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
		List<Holiday> january2019 = Arrays.asList(newYear2019);
		doReturn(january2019).when(holidayManager).getAllEntities(any(), any());
		LocalDate hiringDate = new GregorianCalendar(2019, Calendar.JANUARY, 8).getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		List<Holiday> result = sut.getCorrespondingHolidays(holidays, hiringDate, 2019);
		
		assertThat(result.size(), is(1));
	}
	
	@Test
	public void shouldRemoveCompensationDaysInOrder() {
		List<Holiday> holidays = this.getListOfCompensationDays(2);
		LocalDate hiringDate = new GregorianCalendar(2019, Calendar.JANUARY, 3).getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Holiday newYear2019 = new Holiday();
		newYear2019.setId(6);
		newYear2019.setCompensation(false);
		newYear2019.setDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
		List<Holiday> january2019 = Arrays.asList(newYear2019);
		doReturn(january2019).when(holidayManager).getAllEntities(any(), any());
		
		List<Holiday> result = sut.getCorrespondingHolidays(holidays, hiringDate, 2019);
		
		assertThat(result, is(not(empty())));
		assertThat(result.get(0), is(holidays.get(holidays.size()-1)));
		assertThat(result.size(), is(1));
	}
	
	@Test
	public void shouldReturnHolidaysFromSeveralYears() {
		List<Holiday> holidays = this.getListOfCompensationDays(3);
		Holiday newYear2018 = new Holiday();
		newYear2018.setId(4);
		newYear2018.setCompensation(false);
		newYear2018.setDate(new GregorianCalendar(2018, Calendar.JANUARY, 1).getTime());
		List<Holiday> january2018 = Arrays.asList(newYear2018);
		doReturn(january2018).when(holidayManager).getAllEntities(any(), any());
		Date hiringDate = new GregorianCalendar(2018, Calendar.JANUARY, 2).getTime();
		Calendar calMin = new GregorianCalendar(2018, Calendar.DECEMBER, 1);
		Calendar calMax = new GregorianCalendar(2019, Calendar.FEBRUARY, 1);
		
		List<Holiday> result = sut.calculateCorrespondingHolidays(calMin, calMax, holidays, hiringDate);
		
		assertThat(result.size(), is(3));		
	}
	
	@Test
	public void shouldReturnHolidaysFromSeveralYearsConsideringHiring() {
		List<Holiday> holidays = this.getListOfCompensationDays(3);
		Holiday newYear2018 = new Holiday();
		newYear2018.setId(4);
		newYear2018.setCompensation(false);
		newYear2018.setDate(new GregorianCalendar(2018, Calendar.JANUARY, 1).getTime());
		List<Holiday> january2018 = Arrays.asList(newYear2018);
		doReturn(january2018).when(holidayManager).getAllEntities(any(), any());
		Date hiringDate = new GregorianCalendar(2019, Calendar.JANUARY, 3).getTime();
		Calendar calMin = new GregorianCalendar(2018, Calendar.DECEMBER, 1);
		Calendar calMax = new GregorianCalendar(2019, Calendar.FEBRUARY, 1);
		
		List<Holiday> result = sut.calculateCorrespondingHolidays(calMin, calMax, holidays, hiringDate);
		
		assertThat(result.size(), is(1));	
	}
	
	@Test
	public void shouldCheckFirstDaysOfJanuaryForHolidays() {
		LocalDate hiringDate = new GregorianCalendar(2019, Calendar.JANUARY, 10).getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Holiday newYear2019 = new Holiday();
		newYear2019.setId(2);
		newYear2019.setCompensation(false);
		newYear2019.setDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
		List<Holiday> january2019 = Arrays.asList(newYear2019);
		Date januaryMin = new GregorianCalendar(2019, Calendar.JANUARY, 01, 0, 0).getTime();
		Date januaryMax = new GregorianCalendar(2019, Calendar.JANUARY, 31, 23, 59).getTime();
		HolidaySearch holidaySearch = new HolidaySearch();
		holidaySearch.setStartDate(januaryMin);
		holidaySearch.setEndDate(januaryMax);
		
		List<Holiday> result = sut.getCorrespondingHolidays(january2019, hiringDate, 2019);
			
		
		verify(holidayManager).getAllEntities(holidaySearch, null);
	}
	
	private List<Holiday> getListOfCompensationDays(int numberOfDays) {
		
		List<Holiday> holidays = new ArrayList<Holiday>();
		
		for(int i = 0; i < numberOfDays; i++) {
			Holiday compensation = new Holiday();
			compensation.setId(i+1);
			compensation.setCompensation(true);
			compensation.setDate(new GregorianCalendar(2019, Calendar.JANUARY, 21+i).getTime());
			holidays.add(compensation);
		}
		return holidays;
	}
	
}