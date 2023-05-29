package com.autentia.tnt.bean;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.mail.MailService;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ContractExpirationNotificationBean_IT extends TestContainer {

    private static SessionFactory sessionFactory;

    private MailService mailService = Mockito.mock(MailService.class);

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
    }

    @After
    public void rollback() {
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }

    @Test
    public void should_send_email_when_contract_expires_in_3_months() throws MessagingException {
        // create a user with a contract ending in 3 months from now
        User user = SpringUtilsForTesting.createUser("contract-3m");
        user.setEndContractDate(
                Date.from(LocalDate.now().plusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        UserDAO userDAO = (UserDAO) SpringUtils.getSpringBean("daoUser");
        userDAO.insert(user);

        ContractExpirationNotificationBean sut = new ContractExpirationNotificationBean(mailService);
        int userCount = sut.checkExpirationDate();
        assertThat(userCount, is(1));
    }

    @Test
    public void should_send_email_when_contract_expires_in_1_month() throws MessagingException {
        // create a user with a contract ending in 1 month from now
        User user = SpringUtilsForTesting.createUser("contract-1m");
        user.setEndContractDate(
                Date.from(LocalDate.now().plusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        UserDAO userDAO = (UserDAO) SpringUtils.getSpringBean("daoUser");
        userDAO.insert(user);

        ContractExpirationNotificationBean sut = new ContractExpirationNotificationBean(mailService);
        int userCount = sut.checkExpirationDate();
        assertThat(userCount, is(1));
    }

    @Test
    public void should_send_email_when_contract_expires_in_1_day() throws MessagingException {
        // create a user with a contract ending tomorrow
        User user = SpringUtilsForTesting.createUser("contract-1d");
        user.setEndContractDate(
                Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        UserDAO userDAO = (UserDAO) SpringUtils.getSpringBean("daoUser");
        userDAO.insert(user);

        ContractExpirationNotificationBean sut = new ContractExpirationNotificationBean(mailService);
        int userCount = sut.checkExpirationDate();
        assertThat(userCount, is(1));
    }

    @Test
    public void should_send_email_when_probation_period_expires_in_3_months() throws MessagingException {
        // create a user with a probation of 3 months from now
        User user = SpringUtilsForTesting.createUser("probation-3m");
        user.setEndTestPeriodDate(
                Date.from(LocalDate.now().plusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        UserDAO userDAO = (UserDAO) SpringUtils.getSpringBean("daoUser");
        userDAO.insert(user);

        ContractExpirationNotificationBean sut = new ContractExpirationNotificationBean(mailService);
        int userCount = sut.checkExpirationDate();
        assertThat(userCount, is(1));
    }

    @Test
    public void should_send_email_when_probation_period_expires_in_1_month() throws MessagingException {
        // create a user with a probation of 1 month from now
        User user = SpringUtilsForTesting.createUser("probation-1m");
        user.setEndTestPeriodDate(
                Date.from(LocalDate.now().plusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        UserDAO userDAO = (UserDAO) SpringUtils.getSpringBean("daoUser");
        userDAO.insert(user);

        ContractExpirationNotificationBean sut = new ContractExpirationNotificationBean(mailService);
        int userCount = sut.checkExpirationDate();
        assertThat(userCount, is(1));
    }

    @Test
    public void should_send_email_when_probation_period_expires_in_1_day() throws MessagingException {
        // create a user with a probation ending tomorrow
        User user = SpringUtilsForTesting.createUser("probation-1d");
        user.setEndTestPeriodDate(
                Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        UserDAO userDAO = (UserDAO) SpringUtils.getSpringBean("daoUser");
        userDAO.insert(user);

        ContractExpirationNotificationBean sut = new ContractExpirationNotificationBean(mailService);
        int userCount = sut.checkExpirationDate();
        assertThat(userCount, is(1));
    }

    @Test
    public void should_not_send_any_email_when_there_are_no_users() throws MessagingException {
        ContractExpirationNotificationBean sut = new ContractExpirationNotificationBean(mailService);
        int userCount = sut.checkExpirationDate();
        assertThat(userCount, is(0));
    }

    @Test
    public void should_not_send_email_when_contract_expires_in_3_months_and_1_day() throws MessagingException {
        // create a user with a contract ending in 3 months and 1 day
        User user = SpringUtilsForTesting.createUser("contract-3m-1d");
        user.setEndContractDate(
                Date.from(LocalDate.now().plusMonths(3).plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        UserDAO userDAO = (UserDAO) SpringUtils.getSpringBean("daoUser");
        userDAO.insert(user);

        ContractExpirationNotificationBean sut = new ContractExpirationNotificationBean(mailService);
        int userCount = sut.checkExpirationDate();
        assertThat(userCount, is(0));
    }

    @Test
    public void should_not_send_email_when_probation_expires_in_3_months_and_1_day() throws MessagingException {
        // create a user with a probation ending in 3 months and 1 day
        User user = SpringUtilsForTesting.createUser("probation-3m-1d");
        user.setEndTestPeriodDate(
                Date.from(LocalDate.now().plusMonths(3).plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        UserDAO userDAO = (UserDAO) SpringUtils.getSpringBean("daoUser");
        userDAO.insert(user);

        ContractExpirationNotificationBean sut = new ContractExpirationNotificationBean(mailService);
        int userCount = sut.checkExpirationDate();
        assertThat(userCount, is(0));
    }

    @Test
    public void should_not_send_email_when_user_is_inactive() throws MessagingException {
        // create an inactive user with a contract ending in 1 month
        User user = SpringUtilsForTesting.createUser("inactive");
        user.setActive(false);
        user.setEndContractDate(
                Date.from(LocalDate.now().plusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        UserDAO userDAO = (UserDAO) SpringUtils.getSpringBean("daoUser");
        userDAO.insert(user);

        ContractExpirationNotificationBean sut = new ContractExpirationNotificationBean(mailService);
        int userCount = sut.checkExpirationDate();
        assertThat(userCount, is(0));
    }
}
