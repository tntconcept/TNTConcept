package com.autentia.tnt.bean.contacts;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.autentia.tnt.bean.billing.BillBean;
import com.autentia.tnt.businessobject.BillBreakDown;
import com.autentia.tnt.businessobject.OfferCost;
import com.autentia.tnt.businessobject.OfferRole;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.util.HibernateUtil;
import com.autentia.tnt.util.testing.SpringUtilsForTesting;

public class OfferBeanTest {
	
	private static User userInContext;
	
	private static SessionFactory sessionFactory;
	
	private final BigDecimal IVA16 = new BigDecimal("16");
	
	private final BigDecimal IVA18 = new BigDecimal("18");
	
	private final BigDecimal IVA21 = new BigDecimal("21");
	
	@BeforeClass
    public static void init() throws Exception {
        SpringUtilsForTesting.configure(new ClassPathXmlApplicationContext("applicationContext-test.xml"));
        sessionFactory = HibernateUtil.getSessionFactory();
		sessionFactory.openSession();
		sessionFactory.getCurrentSession().beginTransaction();
        userInContext = SpringUtilsForTesting.createUserInContextWithRoleAndDepartment();
        sessionFactory.getCurrentSession().close();
    }
	
	@Before
	public void beginTransaction() throws Exception {
		sessionFactory.getCurrentSession().beginTransaction();
	}

	@AfterClass
    public static void terminate() throws Exception {
        
		SpringUtilsForTesting.removeUserFromContext();
		SpringUtilsForTesting.deleteUserInContext(userInContext);
		if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		sessionFactory.getCurrentSession().close();
    }

	@Test
	public void createRolesIn2009Test() {
		
		final OfferBean offerBean = new OfferBean();
		offerBean.create();
		final GregorianCalendar calendar = new GregorianCalendar(2009, 8, 31);
		offerBean.setCreationDate(calendar.getTime());
		offerBean.createRoles();
		for (OfferRole offerRole:offerBean.getRoles()) {
			assertEquals(IVA16,offerRole.getIva());
		}
	}
	
	@Test
	public void createRolesInFirstDayOf18Test() {
		
		OfferBean offerBean = new OfferBean();
		offerBean.create();
		final GregorianCalendar calendar = new GregorianCalendar(2010, 7, 1);
		offerBean.setCreationDate(calendar.getTime());
		offerBean.createRoles();
		for (OfferRole offerRole:offerBean.getRoles()) {
			assertEquals(IVA18,offerRole.getIva());
		}
	}
	
	@Test
	public void createRolesInLastDayOf18Test() {
		
		OfferBean offerBean = new OfferBean();
		offerBean.create();
		final GregorianCalendar calendar = new GregorianCalendar(2012, 8, 31);
		offerBean.setCreationDate(calendar.getTime());
		offerBean.createRoles();
		for (OfferRole offerRole:offerBean.getRoles()) {
			assertEquals(IVA18,offerRole.getIva());
		}
	}
	
	@Test
	public void createCostsIn2009Test() {
		
		final OfferBean offerBean = new OfferBean();
		offerBean.create();
		final GregorianCalendar calendar = new GregorianCalendar(2009, 8, 31);
		offerBean.setCreationDate(calendar.getTime());
		offerBean.createCosts();
		for (OfferCost offerCost:offerBean.getCosts()) {
			assertEquals(IVA16,offerCost.getIva());
		}
	}
	
	@Test
	public void createCostsInFirstDayOf18Test() {
		
		OfferBean offerBean = new OfferBean();
		offerBean.create();
		final GregorianCalendar calendar = new GregorianCalendar(2010, 7, 1);
		offerBean.setCreationDate(calendar.getTime());
		offerBean.createCosts();
		for (OfferCost offerCost:offerBean.getCosts()) {
			assertEquals(IVA18,offerCost.getIva());
		}
	}
	
	@Test
	public void createCostsInLastDayOf18Test() {
		
		OfferBean offerBean = new OfferBean();
		offerBean.create();
		final GregorianCalendar calendar = new GregorianCalendar(2012, 8, 31);
		offerBean.setCreationDate(calendar.getTime());
		offerBean.createCosts();
		for (OfferCost offerCost:offerBean.getCosts()) {
			assertEquals(IVA18,offerCost.getIva());
		}
	}
	
	@Ignore
	@Test
	public void createRolesInFirstDayOf21Test() {
		
		BillBean billBean = new BillBean();
		billBean.create();
		final GregorianCalendar calendar = new GregorianCalendar(2012, 9, 1);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(IVA21,billBreakDown.getIva());
		}
	}
	@Ignore
	@Test
	public void createCostsInFirstDayOf21Test() {
		
		BillBean billBean = new BillBean();
		billBean.create();
		final GregorianCalendar calendar = new GregorianCalendar(2012, 9, 1);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(IVA21,billBreakDown.getIva());
		}
	}
}