package com.autentia.tnt.manager.bill;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.businessobject.BillBreakDown;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.businessobject.ProjectCost;
import com.autentia.tnt.businessobject.ProjectRole;
import com.autentia.tnt.dao.hibernate.OrganizationDAO;
import com.autentia.tnt.dao.hibernate.ProjectCostDAO;
import com.autentia.tnt.dao.hibernate.ProjectDAO;
import com.autentia.tnt.manager.billing.BillManager;
import com.autentia.tnt.util.HibernateUtil;
import com.autentia.tnt.util.testing.SpringUtilsForTesting;

public class BillManagerTest {
	
	private static ProjectDAO projectDAO;
	
	private static BillManager billManager;
	
	private static SessionFactory sessionFactory;
	
	private static OrganizationDAO daoOrganization;
	
	private static ProjectCostDAO daoProjectCost;
	
	final Date dateFor16Iva = new GregorianCalendar(2009, 7, 21).getTime();
	
	final Date firstDateFor18Iva = new GregorianCalendar(2010, 7, 1).getTime();
	
	final Date lastDateFor18Iva = new GregorianCalendar(2012, 8, 31).getTime();
	
	private final BigDecimal IVA16 = new BigDecimal("16");
	
	private final BigDecimal IVA18 = new BigDecimal("18");
	
	private final BigDecimal IVA21 = new BigDecimal("21");
	
	@BeforeClass
    public static void init() throws Exception {
        SpringUtilsForTesting.configure(new ClassPathXmlApplicationContext("applicationContext-test.xml"));
        projectDAO = (ProjectDAO) SpringUtilsForTesting.getSpringBean("daoProject");
        billManager = (BillManager) SpringUtilsForTesting.getSpringBean("managerBill");
        daoOrganization = (OrganizationDAO) SpringUtilsForTesting.getSpringBean("daoOrganization");
        daoProjectCost = (ProjectCostDAO) SpringUtilsForTesting.getSpringBean("daoProjectCost");
        sessionFactory = HibernateUtil.getSessionFactory();
		sessionFactory.openSession();
    }
	
	@Before
	public void beginTransaction() throws Exception {
		sessionFactory.getCurrentSession().beginTransaction();
	}

	@AfterClass
    public static void terminate() throws Exception {
        
		if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		sessionFactory.getCurrentSession().close();
    }
	
	@Test
	public void getAllBitacoreBreakDownsHaveCorrectIvaForJune2009Test() {
		final Project project = insertProjectIntoContext(dateFor16Iva);
		ProjectCost projectCost = createProjectCost(project);
		final GregorianCalendar calendarEnd = new GregorianCalendar(2009, 10, 21);
		final List<BillBreakDown> billBreakDownList = billManager.getAllBitacoreBreakDowns(dateFor16Iva, calendarEnd.getTime(), project);
		assertEquals(IVA16, billBreakDownList.get(0).getIva());
		assertEquals(IVA16, billBreakDownList.get(1).getIva());
		deleteProjectCostFromContext(projectCost);
		deleteProjectFromContext(project);
	}
	
	@Test
	public void getAllBitacoreBreakDownsHaveCorrectIvaFirstDayOf18IvaTest() {
		final Project project = insertProjectIntoContext(firstDateFor18Iva);
		ProjectCost projectCost = createProjectCost(project);
		final GregorianCalendar calendarEnd = new GregorianCalendar(2010, 10, 1);
		final List<BillBreakDown> billBreakDownList = billManager.getAllBitacoreBreakDowns(firstDateFor18Iva, calendarEnd.getTime(), project);
		assertEquals(IVA18, billBreakDownList.get(0).getIva());
		assertEquals(IVA18, billBreakDownList.get(1).getIva());
		deleteProjectCostFromContext(projectCost);
		deleteProjectFromContext(project);
	}
	
	@Test
	public void getAllBitacoreBreakDownsHaveCorrectIvaForLastDayOf18IvaTest() {
		final Project project = insertProjectIntoContext(lastDateFor18Iva);
		ProjectCost projectCost = createProjectCost(project);
		final GregorianCalendar calendarEnd = new GregorianCalendar(2012, 12, 1);
		final List<BillBreakDown> billBreakDownList = billManager.getAllBitacoreBreakDowns(lastDateFor18Iva, calendarEnd.getTime(), project);
		assertEquals(IVA18, billBreakDownList.get(0).getIva());
		assertEquals(IVA18, billBreakDownList.get(1).getIva());
		deleteProjectCostFromContext(projectCost);
		deleteProjectFromContext(project);
	}

	private void deleteProjectCostFromContext(ProjectCost projectCost) {
		
		daoProjectCost.delete(projectCost);
	}

	private void deleteProjectFromContext(Project project) {
		
		projectDAO.delete(project);
	}

	private Project insertProjectIntoContext(Date date) {
		
		final Project project = new Project();
		project.setRoles(createRolesForContext(project, date));
		project.setClient(createOrganizationInContext());
		projectDAO.insert(project);
		return project;
	}

	private ProjectCost createProjectCost(Project project) {
		
		final ProjectCost projectCost = new ProjectCost();
		projectCost.setProject(project);
		projectCost.setName("Desarrollo");
		projectCost.setCost(new BigDecimal("100"));
		projectCost.setBillable(true);
		daoProjectCost.insert(projectCost);
		return projectCost;
	}

	private Organization createOrganizationInContext() {
		
		final Organization organization = new Organization();
		daoOrganization.insert(organization);
		return organization;
	}

	private Set<ProjectRole> createRolesForContext(Project project, Date date) {
		
		final Set<ProjectRole> projectRoleSet = new HashSet<ProjectRole>();
		final ProjectRole projectRole = new ProjectRole();
		projectRole.setName("Senior");
		projectRole.setCostPerHour(new BigDecimal("100"));
		projectRole.setExpectedHours(50);
		projectRole.setProject(project);
		projectRole.setActivities(createActivitiesForContext(projectRole, date));
		projectRoleSet.add(projectRole);
		return projectRoleSet;
	}

	private Set<Activity> createActivitiesForContext(ProjectRole role, Date date) {
		
		final Set<Activity> activities = new HashSet<Activity>();
		final Activity activity = new Activity();
		activity.setRole(role);
		activity.setUser(SpringUtilsForTesting.createUserInContextWithRoleAndDepartment());
		activity.setBillable(true);
		activity.setStartDate(date);
		activities.add(activity);
		return activities;
	}
}