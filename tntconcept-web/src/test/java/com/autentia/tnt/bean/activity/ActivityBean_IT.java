package com.autentia.tnt.bean.activity;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import com.autentia.tnt.dao.search.HolidaySearch;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.autentia.tnt.businessobject.Holiday;
import com.autentia.tnt.businessobject.HolidayState;
import com.autentia.tnt.businessobject.RequestHoliday;
import com.autentia.tnt.manager.holiday.HolidayManager;
import com.autentia.tnt.manager.holiday.RequestHolidayManager;
import com.autentia.tnt.test.utils.SpringUtilsForTesting;
import com.autentia.tnt.util.HibernateUtil;

public class ActivityBean_IT {

	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void initDB() {
		Flyway flyway = new Flyway();
		flyway.setDataSource("jdbc:hsqldb:mem:tnt;DB_CLOSE_DELAY=-1;sql.syntax_mys=true", "sa", "");
		flyway.migrate();
	}

	@Before
	public void setup() {
		// test application context
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext-test.xml");
		SpringUtilsForTesting.configure(appCtx);

		// prepare hibernate
		sessionFactory = HibernateUtil.getSessionFactory();
		sessionFactory.openSession();
		sessionFactory.getCurrentSession().beginTransaction();

		// a principal is required in the security context
		SpringUtilsForTesting.loadPrincipalInSecurityContext("admin");
	}

	@After
	public void rollback() {
		sessionFactory.getCurrentSession().getTransaction().rollback();
	}

	@Test
	public void getMonthTotalHours_HolidayRequestOverMonths() {

		// create a holiday request over two months
		RequestHolidayManager rhManager = RequestHolidayManager.getDefault();
		RequestHoliday requestOverMonths = new RequestHoliday();
		requestOverMonths.setState(HolidayState.ACCEPT);

		LocalDate startRequest = LocalDate.of(2017, 8, 31);
		Date startRequestDate = Date.from(startRequest.atStartOfDay(ZoneId.systemDefault()).toInstant());
		requestOverMonths.setBeginDate(startRequestDate);
		LocalDate endRequest = LocalDate.of(2017, 9, 1);
		Date endRequestDate = Date.from(endRequest.atStartOfDay(ZoneId.systemDefault()).toInstant());
		requestOverMonths.setFinalDate(endRequestDate);

		rhManager.insertEntity(requestOverMonths);

		// verify total hours for the first month
		ActivityBeanNoJSF sut = new ActivityBeanNoJSF();
		LocalDate august = LocalDate.of(2017, 8, 1);
		Date augustDate = Date.from(august.atStartOfDay(ZoneId.systemDefault()).toInstant());
		sut.setSelectedDate(augustDate);
		double augustHours = sut.getMonthTotalHours();
		assertThat("First Month Hours", augustHours, closeTo(176.0, 0.1));

		// verify total hours for the second month
		LocalDate september = LocalDate.of(2017, 9, 1);
		Date septemberDate = Date.from(september.atStartOfDay(ZoneId.systemDefault()).toInstant());
		sut.setSelectedDate(septemberDate);
		double septHours = sut.getMonthTotalHours();
		assertThat("Second Month Hours", septHours, closeTo(160.0, 0.1));
	}

	@Test
	public void getMonthTotalHours_HolidayRequestWithinMonth() {

		// create a holiday request within a month
		RequestHolidayManager rhManager = RequestHolidayManager.getDefault();
		RequestHoliday requestWithinMonth = new RequestHoliday();
		requestWithinMonth.setState(HolidayState.ACCEPT);

		LocalDate startRequest = LocalDate.of(2017, 8, 25);
		Date startRequestDate = Date.from(startRequest.atStartOfDay(ZoneId.systemDefault()).toInstant());
		requestWithinMonth.setBeginDate(startRequestDate);
		LocalDate endRequest = LocalDate.of(2017, 8, 31);
		Date endRequestDate = Date.from(endRequest.atStartOfDay(ZoneId.systemDefault()).toInstant());
		requestWithinMonth.setFinalDate(endRequestDate);

		rhManager.insertEntity(requestWithinMonth);

		// verify total hours for the month
		ActivityBeanNoJSF sut = new ActivityBeanNoJSF();
		LocalDate august = LocalDate.of(2017, 8, 1);
		Date augustDate = Date.from(august.atStartOfDay(ZoneId.systemDefault()).toInstant());
		sut.setSelectedDate(augustDate);
		double augustHours = sut.getMonthTotalHours();
		assertThat(augustHours, closeTo(144.0, 0.1));
	}

	@Test
	public void getMonthTotalHours_GeneralHolidaysInWorkingDays() {

		// create a general holiday day
		HolidayManager holidayManager = HolidayManager.getDefault();
		Holiday holiday = new Holiday();
		LocalDate holidayDay = LocalDate.of(2017, 8, 15);
		Date holidayDate = Date.from(holidayDay .atStartOfDay(ZoneId.systemDefault()).toInstant());
		holiday.setDate(holidayDate);
		holiday.setDescription("Test Holiday");
		holidayManager.insertEntity(holiday);

		// verify total hours for the month where the holiday day is in
		ActivityBeanNoJSF sut = new ActivityBeanNoJSF();
		LocalDate august = LocalDate.of(2017, 8, 1);
		Date augustDate = Date.from(august.atStartOfDay(ZoneId.systemDefault()).toInstant());
		sut.setSelectedDate(augustDate);
		double augustHours = sut.getMonthTotalHours();
		assertThat(augustHours, closeTo(176.0, 0.1));
	}

	@Test
	public void getMonthTotalHours_GeneralHolidaysInWeekend() {

		// create a general holiday day in a weekend
		HolidayManager holidayManager = HolidayManager.getDefault();
		Holiday holiday = new Holiday();
		LocalDate holidayInWeekendDay = LocalDate.of(2017, 8, 13);
		Date holidayDate = Date.from(holidayInWeekendDay .atStartOfDay(ZoneId.systemDefault()).toInstant());
		holiday.setDate(holidayDate);
		holiday.setDescription("Test Holiday");
		holidayManager.insertEntity(holiday);

		// verify total hours for the month where the holiday is in
		ActivityBeanNoJSF sut = new ActivityBeanNoJSF();
		LocalDate august = LocalDate.of(2017, 8, 1);
		Date augustDate = Date.from(august.atStartOfDay(ZoneId.systemDefault()).toInstant());
		sut.setSelectedDate(augustDate);
		double augustHours = sut.getMonthTotalHours();
		assertThat(augustHours, closeTo(184.0, 0.1));
	}

	@Test
	public void shouldGetYearTotalHours() {
		String strTarget = "2022-10-05T22:00:00.00Z";
		Date dateTarget = Date.from(Instant.parse(strTarget));
		LocalDate localDateTarget = LocalDate.ofInstant(Instant.parse(strTarget), ZoneId.systemDefault());

		final ActivityBeanNoJSF sut = new ActivityBeanNoJSF();

		List<Holiday> holidays = sut.listHolidays(localDateTarget);
		for( Holiday holiday : holidays) {
			System.out.println( "Holidays: " + holiday.toString() );
		}

		sut.setSelectedDate(dateTarget);

		int workingHours = Math.round(sut.getTotalWorkingHoursFor(localDateTarget.getYear()));
		int hoursByAgreement = sut.findWorkingAgreementHoursByYear(localDateTarget.getYear());
		Map<Integer, Integer> weekends = sut.alternativeGetWeekendsInMonth(localDateTarget.getYear());

		assertEquals(0, holidays.size() );
		assertEquals( 10, weekends.size() );
		assertEquals( 10, weekends.getOrDefault(0, -1).intValue() );
		assertEquals( 8, weekends.getOrDefault(1, -1).intValue() );
		assertEquals( 8, weekends.getOrDefault(2, -1).intValue() );
		assertEquals( 9, weekends.getOrDefault(3, -1).intValue() );
		assertEquals( 9, weekends.getOrDefault(4, -1).intValue() );
		assertEquals( 8, weekends.getOrDefault(5, -1).intValue() );
		assertEquals( 10, weekends.getOrDefault(6, -1).intValue() );
		assertEquals( 8, weekends.getOrDefault(7, -1).intValue() );
		assertEquals( 8, weekends.getOrDefault(8, -1).intValue() );
		assertEquals( 10, weekends.getOrDefault(9, -1).intValue() );
		assertEquals( 1592, workingHours );
		assertEquals( 1765, hoursByAgreement );

		final int result = sut.getYearTotalHours();
		assertEquals( 1592, result);
	}

	/**
	 * Seam to avoid that JSF context is loaded for the ActivityBean managed bean
	 *
	 * @author jalonso
	 *
	 */
	private class ActivityBeanNoJSF extends ActivityBean {
		@Override
		public float getHoursPerDay() {
			return 8;
		}

		@Override
		protected Calendar getToday() {
			Calendar cal = Calendar.getInstance();
			cal.setTime(getSelectedDate());
			return cal;
		}

		public List<Holiday> listHolidays(LocalDate endDate) {
			LocalDate firstDaySelectedMonth = LocalDate.of( endDate.getYear(), 1, 1 );
			LocalDate lastDaySelectedMonth = endDate;
			Date beginOfMonth = Date.from(firstDaySelectedMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date endOfMonth = Date.from(lastDaySelectedMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

			HolidaySearch monthSearch = new HolidaySearch();
			monthSearch.setStartDate(beginOfMonth);
			monthSearch.setEndDate(endOfMonth);

			return holidayManager.getAllEntities(monthSearch, null);
		}

		public Map<Integer, Integer> alternativeGetWeekendsInMonth(int year) {

			Map<Integer, Integer> result = new HashMap<>();

			Calendar cal = getToday();
			LocalDate firstDay = LocalDate.of(year, 1, 1);
			Date date = java.sql.Date.valueOf(firstDay);
			cal.setTime(date);

			for( int month = 0; month <= getSelectedDate().getMonth() ; month++ ) {

				int weekendsInMonth = 0;
				int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

				for(int i = 1; i <= daysInMonth; i++){
					cal.set(Calendar.DAY_OF_MONTH, i);
					if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
							|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
						weekendsInMonth++;
					}
				}

				result.put(month, weekendsInMonth);
				cal.add(Calendar.MONTH, 1);
			}

			return result;
		}

	}

}
