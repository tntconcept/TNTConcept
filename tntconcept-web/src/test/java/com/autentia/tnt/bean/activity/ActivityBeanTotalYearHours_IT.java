package com.autentia.tnt.bean.activity;

import com.autentia.tnt.businessobject.Holiday;
import com.autentia.tnt.businessobject.HolidayState;
import com.autentia.tnt.businessobject.RequestHoliday;
import com.autentia.tnt.dao.search.HolidaySearch;
import com.autentia.tnt.manager.holiday.HolidayManager;
import com.autentia.tnt.manager.holiday.RequestHolidayManager;
import com.autentia.tnt.test.utils.SpringUtilsForTesting;
import com.autentia.tnt.test.utils.TestContainer;
import com.autentia.tnt.util.HibernateUtil;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ActivityBeanTotalYearHours_IT extends TestContainer {

	private static SessionFactory sessionFactory;


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

	@Parameterized.Parameters(name = "{index}: {0} -> {1} working hours")
	public static Iterable data() {
		return Arrays.asList(new Object[][]{
				// 2021
				{"2021-01-01T00:00:00.00Z", 8},
				{"2021-01-02T00:00:00.00Z", 8},
				{"2021-01-03T00:00:00.00Z", 8},
				{"2021-01-04T00:00:00.00Z", 16},
				{"2021-01-08T00:00:00.00Z", 48},
				{"2021-01-31T00:00:00.00Z", 168},
				// 2022
				{"2022-01-01T00:00:00.00Z", 0},
				{"2022-01-02T00:00:00.00Z", 0},
				{"2022-01-03T00:00:00.00Z", 8},
				{"2022-01-04T00:00:00.00Z", 16},
				{"2022-01-07T00:00:00.00Z", 40},
				{"2022-01-31T00:00:00.00Z", 168},
				{"2022-02-28T00:00:00.00Z", 168 + 160},
				{"2022-03-01T00:00:00.00Z", 168 + 160 + 8},
				{"2022-03-02T00:00:00.00Z", 168 + 160 + 16},
				{"2022-03-31T00:00:00.00Z", 168 + 160 + 184},
				{"2022-04-30T00:00:00.00Z", 168 + 160 + 184 + 168},
				{"2022-05-31T00:00:00.00Z", 168 + 160 + 184 + 168 + 176},
				{"2022-06-30T00:00:00.00Z", 168 + 160 + 184 + 168 + 176 + 176},
				{"2022-07-31T00:00:00.00Z", 168 + 160 + 184 + 168 + 176 + 176 + 168},
				{"2022-08-31T00:00:00.00Z", 168 + 160 + 184 + 168 + 176 + 176 + 168 + 184},
				{"2022-09-30T00:00:00.00Z", 168 + 160 + 184 + 168 + 176 + 176 + 168 + 184 + 176},
				{"2022-10-05T00:00:00.00Z", 168 + 160 + 184 + 168 + 176 + 176 + 168 + 184 + 176 + 24},
				// 2023
				{"2023-01-01T00:00:00.00Z", 0},
				{"2023-01-02T00:00:00.00Z", 8},
				{"2023-01-03T00:00:00.00Z", 16},
				{"2023-01-06T00:00:00.00Z", 40},
				{"2023-01-13T00:00:00.00Z", 80},
				{"2023-01-31T00:00:00.00Z", 176},
				{"2023-02-28T00:00:00.00Z", 176 + 160},
				{"2023-03-31T00:00:00.00Z", 176 + 160 + 184},
		});
	}

	private final String workingDate;
	private final int totalWorkingHours;

	public ActivityBeanTotalYearHours_IT(String workingDate, int totalWorkingHours) {
		this.workingDate = workingDate;
		this.totalWorkingHours = totalWorkingHours;
	}

	@Test
	public void checkTotalWorkingHours() {
		Date dateTarget = Date.from(Instant.parse(workingDate));

		final ActivityBean_IT.ActivityBeanNoJSF sut = new ActivityBean_IT.ActivityBeanNoJSF();
		sut.setSelectedDate(dateTarget);

		assertEquals(totalWorkingHours, sut.getYearTotalHours());
	}
}
