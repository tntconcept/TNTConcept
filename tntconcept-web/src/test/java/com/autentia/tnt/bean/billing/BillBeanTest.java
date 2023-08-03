package com.autentia.tnt.bean.billing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.model.SelectItem;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.autentia.tnt.businessobject.Bill;
import com.autentia.tnt.businessobject.BillBreakDown;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.manager.admin.ProjectManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;



public class BillBeanTest {
	
	private BillBean billBean;
	final static ConfigurationUtil configurationUtil = mock(ConfigurationUtil.class);
	final static ApplicationContext ctx = mock(ApplicationContext.class);
	final static ProjectManager projectManager = mock(ProjectManager.class);
	

	final static Organization org = mock(Organization.class);
	
	final Bill bill = mock(Bill.class);
	final List<Project> expectedProjects = prepareReturnOpenProjects();

	private final static float IVA_UNTIL_JUL_2010 = 3;
	private final static float IVA_UNTIL_SEPT_2012 = 2;
	private final static float IVA_ACTUAL=1;
	
	@BeforeClass
    public static void init() throws Exception {
		when(ctx.getBean("managerProject")).thenReturn(projectManager);
		when(ctx.getBean("configuration")).thenReturn(configurationUtil);
		
		SpringUtils.configureTest(ctx);
	}
	

	
	@Before
	public  void initTest() throws Exception {
		billBean = new BillBean();
		billBean.create();
		Bill bill = new Bill();
		billBean.setBill(bill);
		billBean.setSelectedOrganization(org);
		
		reset(projectManager);
		
	}


	private void prepareTestsIVA(){
		when(configurationUtil.getActualIva()).thenReturn(IVA_ACTUAL);
		when(configurationUtil.getIvaUntilSeptember2012()).thenReturn(IVA_UNTIL_SEPT_2012);
		when(configurationUtil.getIvaUntilJuly2010()).thenReturn(IVA_UNTIL_JUL_2010);
	}
	
	@Test
	public void createBreakDownIn2009Test() {
	
		this.prepareTestsIVA();
		
		final GregorianCalendar calendar = new GregorianCalendar(2009, 8, 31);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(new BigDecimal(IVA_UNTIL_JUL_2010), billBreakDown.getIva());
		}
	}
	
	@Test
	public void createBreakDownInFirstDayOf18Test() {
		
		this.prepareTestsIVA();
		
		final GregorianCalendar calendar = new GregorianCalendar(2010, 6, 1);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(new BigDecimal(IVA_UNTIL_SEPT_2012), billBreakDown.getIva());
		}
	}
	
	@Test
	public void createBreakDownInLastDayOf18Test() {
		
		this.prepareTestsIVA();
		
		final GregorianCalendar calendar = new GregorianCalendar(2012, 7, 31);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(new BigDecimal(IVA_UNTIL_SEPT_2012), billBreakDown.getIva());
		}
	}
	
	@Test
	public void createBreakDownInFirstDayOf21Test() {
		
		this.prepareTestsIVA();
		
		final GregorianCalendar calendar = new GregorianCalendar(2012, 8, 1);
		billBean.setCreationDate(calendar.getTime());
		billBean.createBreakDown();
		for (BillBreakDown billBreakDown:billBean.getBill().getBreakDown()) {
			assertEquals(new BigDecimal(IVA_ACTUAL), billBreakDown.getIva());
		}
	}
	
	private List<Project> prepareReturnOpenProjects() {
		List<Project> list = new ArrayList<Project>();
		Project p1 = new Project();
		p1.setId(1);
		p1.setOpen(true);
		p1.setName("Proyecto Abierto");
		list.add(p1);
		Project p2 = new Project();
		p2.setId(2);
		p2.setOpen(false);
		p2.setName("Proyecto Cerrado");
		list.add(p2);

		return list;
	}

	@Test
	public void shouldCallGetProjectsOpenBySelectedOrganizationWhenIsANewBill() {

		when(bill.getId()).thenReturn(null);
		billBean.setBill(bill);

		when(projectManager.getOpenProjectsByOrganization(org)).thenReturn(expectedProjects);
		final List<SelectItem> result = billBean.getProjectsOpenBySelectedOrganization();

		verify(projectManager).getOpenProjectsByOrganization(org);
		assertProjectsResult(expectedProjects, result);
	}

	@Test
	public void shouldCallGetProjectsByOrganizationWhenEditABill() {

		when(bill.getId()).thenReturn(1);
		billBean.setBill(bill);
		billBean.setSelectedOrganization(org);

		when(projectManager.getProjectsByOrganization(org)).thenReturn(
				expectedProjects);
		final List<SelectItem> result = billBean
				.getProjectsOpenBySelectedOrganization();

		verify(projectManager).getProjectsByOrganization(org);
		assertProjectsResult(expectedProjects, result);

	}

	private void assertProjectsResult(List<Project> expectedProjects,
			List<SelectItem> result) {
		assertNotNull(result);
		assertEquals(expectedProjects.size(), result.size());

		for (SelectItem selectItem : result) {
			assertTrue(expectedProjects.contains((Project) selectItem.getValue()));
		}
	}
	
}