package com.autentia.tnt.manager.holiday;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.util.SpringUtils;

public class CorrespondingHolidayManagerTest {

	final CorrespondingHolidayManager sut = new CorrespondingHolidayManager();
	
	final static HolidayManager holidayManager = mock(HolidayManager.class);	
	final static ApplicationContext ctx = mock(ApplicationContext.class);
	
	@BeforeClass
	public static void setUp() {
		when(ctx.getBean("managerHoliday")).thenReturn(holidayManager);
		SpringUtils.configureTest(ctx);
	}
	
	@Test
	public void shouldRemoveCompensationDaysIfNotHiredEarly() {
		
		Holiday compensation = new Holiday();
		compensation.setId(1);
		compensation.setCompensation(true);
		compensation.setDate(new GregorianCalendar(2019,1, 31).getTime());
		List<Holiday> holidays = new ArrayList<Holiday>();
		holidays.add(compensation);
		User user = new User();
		user.setStartDate(new GregorianCalendar(2019, 0, 2).getTime());
		doReturn(holidays).when(holidayManager).getAllEntities(any(), any());
		
		List<Holiday> result = sut.calcCorrespondingHolidays(holidays, user, 2019);
		
		assertThat(result, is(empty()));
	}
	
	@Test
	public void shouldTakeHolidayIntoAccount() {
		
		Holiday holiday = new Holiday();
		holiday.setId(1);
		holiday.setCompensation(false);
		holiday.setDate(new GregorianCalendar(2019,1,1).getTime());
		Holiday compensation = new Holiday();
		compensation.setId(2);
		compensation.setCompensation(true);
		compensation.setDate(new GregorianCalendar(2019,1, 31).getTime());
		List<Holiday> holidays = new ArrayList<Holiday>();
		holidays.add(holiday);
		holidays.add(compensation);
		User user = new User();
		user.setStartDate(new GregorianCalendar(2019, 0, 2).getTime());
		doReturn(holidays).when(holidayManager).getAllEntities(any(), any());
		
		List<Holiday> result = sut.calcCorrespondingHolidays(holidays, user, 2019);
		
		assertThat(result, is(not(empty())));
	}
	
	@Test
	public void shouldTakeWeekendsIntoAccount() {
		Holiday compensation1 = new Holiday();
		compensation1.setCompensation(true);
		compensation1.setId(1);
		compensation1.setDate(new GregorianCalendar(2019,0, 21).getTime());
		Holiday compensation2 = new Holiday();
		compensation2.setId(2);
		compensation2.setCompensation(true);
		compensation2.setDate(new GregorianCalendar(2019,0, 22).getTime());
		Holiday compensation3 = new Holiday();
		compensation3.setId(3);
		compensation3.setCompensation(true);
		compensation3.setDate(new GregorianCalendar(2019,0, 23).getTime());
		Holiday compensation4 = new Holiday();
		compensation4.setId(4);
		compensation4.setCompensation(true);
		compensation4.setDate(new GregorianCalendar(2019,0, 24).getTime());
		Holiday compensation5 = new Holiday();
		compensation5.setId(5);
		compensation5.setCompensation(true);
		compensation5.setDate(new GregorianCalendar(2019,0, 25).getTime());		
		List<Holiday> holidays = new ArrayList<Holiday>();
		holidays.add(compensation1);
		holidays.add(compensation2);
		holidays.add(compensation3);
		holidays.add(compensation4);
		holidays.add(compensation5);
		User user = new User();
		user.setStartDate(new GregorianCalendar(2019, 0, 7).getTime());
		doReturn(holidays).when(holidayManager).getAllEntities(any(), any());
		
		List<Holiday> result = sut.calcCorrespondingHolidays(holidays, user, 2019);
		
		assertThat(result, is(not(empty())));
	}
	
	@Test
	public void shouldRemoveCompensationInOrder() {
		
		Date lastCompensation = new GregorianCalendar(2019,0, 22).getTime();
		Holiday compensation1 = new Holiday();
		compensation1.setCompensation(true);
		compensation1.setId(1);
		compensation1.setDate(new GregorianCalendar(2019,0, 21).getTime());
		Holiday compensation2 = new Holiday();
		compensation2.setId(2);
		compensation2.setCompensation(true);
		compensation2.setDate(lastCompensation);
		List<Holiday> holidays = new ArrayList<Holiday>();
		holidays.add(compensation1);
		holidays.add(compensation2);
		User user = new User();
		user.setStartDate(new GregorianCalendar(2019, 0, 2).getTime());
		doReturn(holidays).when(holidayManager).getAllEntities(any(), any());
		
		List<Holiday> result = sut.calcCorrespondingHolidays(holidays, user, 2019);
		
		assertThat(result, is(not(empty())));
		assertThat(result.get(0).getDate().compareTo(new GregorianCalendar(2019, 0, 22).getTime()), is(0));
		
	}

	@Test
	public void shouldNotRemoveAnyCompesation() {
		Holiday compensation1 = new Holiday();
		compensation1.setCompensation(true);
		compensation1.setId(1);
		compensation1.setDate(new GregorianCalendar(2019,0, 21).getTime());
		List<Holiday> holidays = new ArrayList<Holiday>();
		holidays.add(compensation1);
		User user = new User();
		user.setStartDate(new GregorianCalendar(2019, 0, 1).getTime());
		
		List<Holiday> result = sut.calcCorrespondingHolidays(holidays, user, 2019);
		
		assertThat(result, is(not(empty())));
	}
	
}
