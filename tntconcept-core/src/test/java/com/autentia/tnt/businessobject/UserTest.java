package com.autentia.tnt.businessobject;

import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import static org.hamcrest.core.Is.is;
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
        assertThat(user.getLdapPassword(),is(OLD_PASSWORD));
    }

    @Test
    public void setLdapPassword() throws Exception {
        assertThat(user.getLdapPassword(),is(OLD_PASSWORD));
        user.setLdapPassword(NEW_PASSWORD);
        assertThat(user.getLdapPassword(),is(NEW_PASSWORD));
    }

    @Test
    public void isLdapAuthentication() throws Exception {
        when(configurationUtil.isLdapProviderEnabled()).thenReturn(Boolean.TRUE);
        assertThat(user.isLdapAuthentication(), is(Boolean.TRUE));
    }

    @Test
    public void isNotLdapAuthentication() throws Exception {
        when(configurationUtil.isLdapProviderEnabled()).thenReturn(Boolean.FALSE);
        assertThat(user.isLdapAuthentication(), is(Boolean.FALSE));
    }

    @Test
    public void setDn() throws Exception {
        assertThat(user.getDn(),is(OLD_DN));
    }

    @Test
    public void getDn() throws Exception {
        assertThat(user.getDn(),is(OLD_DN));
        user.setDn(NEW_DN);
        assertThat(user.getDn(),is(NEW_DN));
    }

    @Test
    public void setLdapName() throws Exception {
        assertThat(user.getLdapName(),is(OLD_LDAPNAME));
        user.setLdapName(NEW_LDAPNAME);
        assertThat(user.getLdapName(),is(NEW_LDAPNAME));
    }

    @Test
    public void getLdapName() throws Exception {
        assertThat(user.getLdapName(),is(OLD_LDAPNAME));
    }

    @Test
    public void buildLdapName() throws Exception {

        assertThat(user.buildLdapName(), is("uid=user,ou=People"));
    }

}