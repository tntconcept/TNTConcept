package com.autentia.tnt.test.utils;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.HibernateUtil;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.MountableFile;

import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntegrationTest {

    protected SessionFactory sessionFactory;

    static final public MySQLContainer<?> mysql = new MySQLContainer<>(
            "mysql:8.0.32")
            .withDatabaseName("tntconcept")
            .withUsername("tntconcept")
            .withPassword("tntconcept")
            .withCopyFileToContainer(MountableFile.forClasspathResource("testcontainers/mysql/extra.cnf"), "/etc/mysql/conf.d/extra.cnf")
            .withExposedPorts(3306)
            .waitingFor(Wait.forHttp("/").forPort(3306));

    @BeforeClass
    public static void initDB() {
        // Init container
        mysql.setPortBindings(List.of("50400:3306"));
        mysql.start();
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(mysql.getJdbcUrl());
        dataSource.setUser(mysql.getUsername());
        dataSource.setPassword(mysql.getPassword());

        // migrate the database
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        // Security context
        final AuthenticationManager authManager = mock(AuthenticationManager.class);
        final UserForTesting user = new UserForTesting();
        user.setId(1);
        final Principal principal = mock(Principal.class);

        SecurityContext securityCtx = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(securityCtx.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(principal);
        when(authManager.getCurrentPrincipal()).thenReturn(principal);
        when(principal.getUser()).thenReturn(user);
        when(principal.getId()).thenReturn(1);
        SecurityContextHolder.setContext(securityCtx);

        // App context
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext-test.xml");
        SpringUtilsForTesting.configure(appCtx);

    }

    @Before
    public void setUpTransaction() {
        sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.openSession();
        sessionFactory.getCurrentSession().beginTransaction();
    }

    @After
    public void rollback() throws SQLException {
        sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }
}