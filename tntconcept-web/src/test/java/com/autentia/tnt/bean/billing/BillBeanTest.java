package com.autentia.tnt.bean.billing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.model.SelectItem;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.autentia.tnt.businessobject.Bill;
import com.autentia.tnt.businessobject.BillBreakDown;
import com.autentia.tnt.businessobject.Offer;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.dao.hibernate.OfferDAO;
import com.autentia.tnt.dao.hibernate.ProjectDAO;
import com.autentia.tnt.manager.admin.ProjectManager;
import com.autentia.tnt.manager.contacts.OfferManager;
import com.autentia.tnt.test.utils.SpringUtilsForTesting;
import com.autentia.tnt.util.SpringUtils;





public class BillBeanTest {
	
	private BillBean billBean;
	
	@BeforeClass
    public static void init() throws Exception {
        SpringUtilsForTesting.configure(new ClassPathXmlApplicationContext("applicationContext-test.xml"));
    }
	
	@Before
	public  void initTest() throws Exception {
		billBean = new BillBean();
		billBean.create();
	}

	
	@Test
	public void createBreakDownIn2009Test() {
		
		final GregorianCalendar calendar = new GregorianCalendar(2009, 8, 31);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(new BigDecimal("16"),billBreakDown.getIva());
		}
	}
	
	@Test
	public void createBreakDownInFirstDayOf18Test() {
		
		final GregorianCalendar calendar = new GregorianCalendar(2010, 6, 1);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(new BigDecimal("18"),billBreakDown.getIva());
		}
	}
	
	@Test
	public void createBreakDownInLastDayOf18Test() {
		
		final GregorianCalendar calendar = new GregorianCalendar(2012, 7, 31);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(new BigDecimal("18"),billBreakDown.getIva());
		}
	}
	
	@Test
	public void createBreakDownInFirstDayOf21Test() {
		
		final GregorianCalendar calendar = new GregorianCalendar(2012, 8, 1);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(new BigDecimal("21"),billBreakDown.getIva());
		}
	}
	
	
}