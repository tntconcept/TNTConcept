package com.autentia.tnt.bean.billing;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.autentia.tnt.businessobject.BillBreakDown;
import com.autentia.tnt.util.testing.SpringUtilsForTesting;



public class BillBeanTest {
	
	@BeforeClass
    public static void init() throws Exception {
        SpringUtilsForTesting.configure(new ClassPathXmlApplicationContext("applicationContext-test.xml"));
    }

	
	@Test
	public void createBreakDownIn2009Test() {
		
		BillBean billBean = new BillBean();
		billBean.create();
		final GregorianCalendar calendar = new GregorianCalendar(2009, 8, 31);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(new BigDecimal("16"),billBreakDown.getIva());
		}
	}
	
	@Test
	public void createBreakDownInFirstDayOf18Test() {
		
		BillBean billBean = new BillBean();
		billBean.create();
		final GregorianCalendar calendar = new GregorianCalendar(2010, 6, 1);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(new BigDecimal("18"),billBreakDown.getIva());
		}
	}
	
	@Test
	public void createBreakDownInLastDayOf18Test() {
		
		BillBean billBean = new BillBean();
		billBean.create();
		final GregorianCalendar calendar = new GregorianCalendar(2012, 7, 31);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(new BigDecimal("18"),billBreakDown.getIva());
		}
	}
	
	@Test
	public void createBreakDownInFirstDayOf21Test() {
		
		BillBean billBean = new BillBean();
		billBean.create();
		// 1 de septiembre a la 1 de la madrugada
		final GregorianCalendar calendar = new GregorianCalendar(2012, 8, 1, 1, 0);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(new BigDecimal("21"),billBreakDown.getIva());
		}
	}
}