package com.autentia.tnt.bean;

import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.businessobject.ProjectRole;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.hibernate.ActivityDAO;
import com.autentia.tnt.dao.hibernate.ProjectRoleDAO;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.mail.MailService;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.test.utils.SpringUtilsForTesting;
import com.autentia.tnt.test.utils.TestContainer;
import com.autentia.tnt.util.HibernateUtil;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.MessagingException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class ActivityEvidenceNotificationBean_IT extends TestContainer {
    private static SessionFactory sessionFactory;

    private final MailService mailService = Mockito.mock(MailService.class);

    @Before
    public void setup() throws MessagingException {
        // load test application context including security configuration
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext-test.xml");
        SpringUtilsForTesting.configure(appCtx);

        // each test starts its own transaction
        sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.openSession();
        sessionFactory.getCurrentSession().beginTransaction();

        // a user is required in the database to load the security context
        SpringUtilsForTesting.loadPrincipalInSecurityContext("admin");
        doNothing().when(mailService).send(anyString(), anyString(), anyString());
    }

    @After
    public void rollback() {
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }

    @Test
    public void should_send_no_evidence_email() throws MessagingException {
        User user = testUser();

        insertActivityWithRequiredEvidence();

        ActivityEvidenceNotificationBean bean = new ActivityEvidenceNotificationBean(mailService);
        bean.checkActivitiesWithNoEvidence();
        verify(mailService, never()).send(eq(user.getEmail()), anyString(), anyString());
    }

    @Test
    public void should_not_send_no_evidence_email() throws MessagingException {
        User user = testUser();

        insertActivityWithNonRequiredEvidence();

        ActivityEvidenceNotificationBean bean = new ActivityEvidenceNotificationBean(mailService);
        bean.checkActivitiesWithNoEvidence();
        verify(mailService, times(0)).send(eq(user.getEmail()), anyString(), anyString());
    }

    private User testUser() {
        UserManager userManager = (UserManager) SpringUtils.getSpringBean("managerUser");
        User user = SpringUtilsForTesting.createUser("user");
        UserDAO userDAO = (UserDAO) SpringUtils.getSpringBean("daoUser");
        userDAO.insert(user);

        return userManager.getUserByLogin("user");
    }

    private void insertActivityWithRequiredEvidence() {
        ProjectRole role = ((ProjectRoleDAO) SpringUtils.getSpringBean("daoProjectRole")).getById(3);
        Activity activity = new Activity();
        activity.setDescription("Test activity");
        activity.setHasEvidences(false);
        activity.setStart(Date.from(LocalDate.now().plusDays(-4).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        activity.setRole(role);
        activity.setDuration(32);
        activity.setBillable(true);
        activity.setDescription("");
        activity.setHasEvidences(true);

        ActivityDAO activityDAO = (ActivityDAO) SpringUtils.getSpringBean("daoActivity");
        activityDAO.insert(activity);
    }

    private void insertActivityWithNonRequiredEvidence() {
        ProjectRole role = ((ProjectRoleDAO) SpringUtils.getSpringBean("daoProjectRole")).getById(1);

        Activity activity = new Activity();
        activity.setDescription("Test activity 2");
        activity.setHasEvidences(true);
        activity.setStart(Date.from(LocalDate.now().plusDays(-4).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        activity.setRole(role);
        activity.setDuration(32);
        activity.setBillable(true);
        activity.setDescription("");
        activity.setHasEvidences(true);


        ActivityDAO activityDAO = (ActivityDAO) SpringUtils.getSpringBean("daoActivity");
        activityDAO.insertWithoutUser(activity);
    }
}
