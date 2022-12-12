package com.autentia.tnt.bean;

import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.hibernate.*;
import com.autentia.tnt.mail.MailService;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.test.utils.SpringUtilsForTesting;
import com.autentia.tnt.util.HibernateUtil;
import com.autentia.tnt.util.SpringUtils;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ActivityEvidenceNotificationBean_IT {
    private static SessionFactory sessionFactory;

    private MailService mailService;

    @BeforeClass
    public static void initDB() {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:hsqldb:mem:tnt;DB_CLOSE_DELAY=-1;sql.syntax_mys=true", "sa", "");
        flyway.migrate();
    }

    @Before
    public void setup() {
        // load test application context including security configuration
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext-test.xml");
        SpringUtilsForTesting.configure(appCtx);

        // each test starts its own transaction
        sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.openSession();
        sessionFactory.getCurrentSession().beginTransaction();

        // a user is required in the database to load the security context
        SpringUtilsForTesting.loadPrincipalInSecurityContext("admin");

        mailService = Mockito.mock(MailService.class);
    }

    @After
    public void rollback() {
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }

    @Test
    public void should_send_no_evidence_email() throws MessagingException {
        User user = testUser();

        insertActivityWithRequiredEvidence(user);

        ActivityEvidenceNotificationBean bean = new ActivityEvidenceNotificationBean(mailService);
        bean.checkActivitiesWithNoEvidence();
        verify(mailService).send(eq(user.getEmail()),anyString(),anyString());
    }

    @Test
    public void should_not_send_no_evidence_email() throws MessagingException {
        User user = testUser();

        insertActivityWithNonRequiredEvidence(user);

        ActivityEvidenceNotificationBean bean = new ActivityEvidenceNotificationBean(mailService);
        bean.checkActivitiesWithNoEvidence();
        verify(mailService, times(0)).send(eq(user.getEmail()),anyString(),anyString());
    }

    private User testUser() {
        UserManager userManager = (UserManager) SpringUtils.getSpringBean("managerUser");
        return userManager.getUserByLogin("user");
    }

    private void insertActivityWithRequiredEvidence(User user) {
        ProjectRole role = ((ProjectRoleDAO) SpringUtils.getSpringBean("daoProjectRole")).getById(3);

        Activity activity = new Activity();
        activity.setDescription("Test activity");
        activity.setHasImage(false);
        activity.setStartDate(Date.from(LocalDate.now().plusDays(-4).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        activity.setRole(role);

        // Even if user is set on activity, if completely ignores it, so...
        final Principal principal = (Principal) AuthenticationManager.getDefault().loadUserByUsername(user.getLogin());
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, principal.getUser().getPassword(),principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        ActivityDAO activityDAO = (ActivityDAO) SpringUtils.getSpringBean("daoActivity");
        activityDAO.insert(activity);
    }

    private void insertActivityWithNonRequiredEvidence(User user) {
        ProjectRole role = ((ProjectRoleDAO) SpringUtils.getSpringBean("daoProjectRole")).getById(1);

        Activity activity = new Activity();
        activity.setDescription("Test activity 2");
        activity.setHasImage(true);
        activity.setStartDate(Date.from(LocalDate.now().plusDays(-4).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        activity.setRole(role);

        // Even if user is set on activity, if completely ignores it, so...
        final Principal principal = (Principal) AuthenticationManager.getDefault().loadUserByUsername(user.getLogin());
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, principal.getUser().getPassword(),principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        ActivityDAO activityDAO = (ActivityDAO) SpringUtils.getSpringBean("daoActivity");
        activityDAO.insertWithoutUser(activity);
    }
}
