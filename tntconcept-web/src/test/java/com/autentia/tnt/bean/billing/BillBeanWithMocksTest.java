package com.autentia.tnt.bean.billing;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.autentia.tnt.businessobject.Bill;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.manager.admin.ProjectManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;

public class BillBeanWithMocksTest {

	final static Organization org = mock(Organization.class);
	final static ProjectManager projectManager = mock(ProjectManager.class);// spy(new
																		// ProjectManager(projectDAO));
	final static ConfigurationUtil configurationUtil = mock(ConfigurationUtil.class);
	final static ApplicationContext ctx = mock(ApplicationContext.class);
	BillBean billBean;
	
	final Bill bill = mock(Bill.class);
	final List<Project> expectedProjects = prepareReturnOpenProjects();

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


	@BeforeClass
	public static  void  prepareSpringContext() {
		when(ctx.getBean("managerProject")).thenReturn(projectManager);
		when(ctx.getBean("configuration")).thenReturn(configurationUtil);
		SpringUtils.configureTest(ctx);
	}
	
	@Before
	public void setUp(){
		billBean = new BillBean();
		billBean.create();
		billBean.setSelectedOrganization(org);
		
		reset(projectManager);
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
		assertThat(result, is(notNullValue()));
		assertThat(result.size(), is(expectedProjects.size()));

		for (SelectItem selectItem : result) {
			assertThat((Project) selectItem.getValue(), isIn(expectedProjects));
		}
	}

}