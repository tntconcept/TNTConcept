package com.autentia.tnt.bean.activity;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import com.autentia.tnt.dao.search.HolidaySearch;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.MountableFile;

import javax.sql.DataSource;

public class ActivityBean_IT {

	private static SessionFactory sessionFactory;

	static final public MySQLContainer<?> mysql = new MySQLContainer<>(
			"mysql:8.0.32")
			.withDatabaseName("tntconcept")
			.withUsername("tntconcept")
			.withPassword("tntconcept")
			.withCopyFileToContainer(MountableFile.forClasspathResource("testcontainers/mysql/extra.cnf"), "/etc/mysql/conf.d/extra.cnf")
			.withExposedPorts(3306)
			.waitingFor(Wait.forHttp("/").forPort(3306));


	@Before
	public void setup() {
		mysql.start();
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL(mysql.getJdbcUrl());
		dataSource.setUser(mysql.getUsername());
		dataSource.setPassword(mysql.getPassword());
		// migrate the database
		Flyway flyway = Flyway.configure().dataSource(dataSource).load();
		flyway.migrate();

		// test application context
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext-test.xml");
		SpringUtilsForTesting.configure(appCtx);


		Configuration cfg = new Configuration();
		cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		cfg.setProperty("connection.url",mysql.getJdbcUrl() );
		cfg.setProperty("connection.username", mysql.getUsername());
		cfg.setProperty("connection.password", mysql.getPassword());
		cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");




		// prepare hibernate
		sessionFactory = cfg.buildSessionFactory();
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
		String strTarget = "2022-10-05T00:00:00.00Z";
		Date dateTarget = Date.from(Instant.parse(strTarget));

		final ActivityBeanNoJSF sut = new ActivityBeanNoJSF();
		sut.setSelectedDate(dateTarget);

		assertEquals( 1584, sut.getYearTotalHours());
	}

	/**
	 * Seam to avoid that JSF context is loaded for the ActivityBean managed bean
	 *
	 * @author jalonso
	 *
	 */
	public static class ActivityBeanNoJSF extends ActivityBean {
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
	}

}
