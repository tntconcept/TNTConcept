package com.autentia.tnt.businessobject;

import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTest {


    private User user;

    private static final String OLD_PASSWORD = "oldPassword";
    private static final String NEW_PASSWORD = "newPassword";

    private static final String OLD_DN = "newPassword";
    private static final String NEW_DN = "newPassword";

    private static final String OLD_LDAPNAME = "newPassword";
    private static final String NEW_LDAPNAME = "newPassword";

    private ApplicationContext applicationContext = mock(ApplicationContext.class);
    private ConfigurationUtil configurationUtil = mock(ConfigurationUtil.class);

    @Before
    public void init(){

        when(applicationContext.getBean("configuration")).thenReturn(configurationUtil);
        SpringUtils.configureTest(applicationContext);


        user = new User();
        user.setLdapPassword(OLD_PASSWORD);
        user.setDn(OLD_DN);
        user.setLdapName(OLD_LDAPNAME);
        user.setLogin("user");
    }

    @Test
    public void getLdapPassword() throws Exception {
        assertEquals(OLD_PASSWORD, user.getLdapPassword());
    }

    @Test
    public void setLdapPassword() throws Exception {
        assertEquals(OLD_PASSWORD, user.getLdapPassword());
        user.setLdapPassword(NEW_PASSWORD);
        assertEquals(NEW_PASSWORD, user.getLdapPassword());
    }

    @Test
    public void isLdapAuthentication() throws Exception {
        when(configurationUtil.isLdapProviderEnabled()).thenReturn(Boolean.TRUE);
        assertTrue(user.isLdapAuthentication());
    }

    @Test
    public void isNotLdapAuthentication() throws Exception {
        when(configurationUtil.isLdapProviderEnabled()).thenReturn(Boolean.FALSE);
        assertFalse(user.isLdapAuthentication());
    }

    @Test
    public void setDn() throws Exception {
        assertEquals(OLD_DN, user.getDn());
    }

    @Test
    public void getDn() throws Exception {
        assertEquals(OLD_DN, user.getDn());
        user.setDn(NEW_DN);
        assertEquals(NEW_DN, user.getDn());
    }

    @Test
    public void setLdapName() throws Exception {
        assertEquals(OLD_LDAPNAME, user.getLdapName());
        user.setLdapName(NEW_LDAPNAME);
        assertEquals(NEW_LDAPNAME, user.getLdapName());
    }

    @Test
    public void getLdapName() throws Exception {
        assertEquals(OLD_LDAPNAME, user.getLdapName());
    }

    @Test
    public void buildLdapName() throws Exception {

        assertEquals("uid=user,ou=People", user.buildLdapName());
    }
    @Test
    public void shouldGetYearDurationByYearFromUser() {
        user.setAgreementYearDuration(1000);

        assertEquals(1000, user.getYearDurationByYear(2020));
    }
    @Test
    public void shouldGetYearDurationByYearFromAgreement() {
        final WorkingAgreement workingAgreement = WorkingAgreementMother.sample();
        user.setAgreement(workingAgreement);

        assertEquals(100000, user.getYearDurationByYear(2020));
    }

    @Test
    public void shouldGetSalaryPerHourByUserAgreement() {
        user.setAgreementYearDuration(105900);
        user.setSalary(BigDecimal.valueOf(10000));

        assertEquals(5.66, user.getSalaryPerHour(), 2);
    }

    @Test
    public void shouldGetSalaryPerHourByAgreement() {
        final WorkingAgreement workingAgreement = WorkingAgreementMother.sample();
        user.setAgreement(workingAgreement);
        user.setSalary(BigDecimal.valueOf(10000));

        assertEquals(5.66, user.getSalaryPerHour(), 2);
    }
}