package com.autentia.tnt.businessobject;

import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTest {


    private final User user = new User();

    private static final String OLD_PASSWORD = "oldPassword";
    private static final String NEW_PASSWORD = "newPassword";

    private static final String OLD_DN = "newPassword";
    private static final String NEW_DN = "newPassword";

    private static final String OLD_LDAPNAME = "newPassword";
    private static final String NEW_LDAPNAME = "newPassword";

    private ApplicationContext applicationContext = mock(ApplicationContext.class);
    private ConfigurationUtil configurationUtil = mock(ConfigurationUtil.class);

    @Before
    public void init() {

        when(applicationContext.getBean("configuration")).thenReturn(configurationUtil);
        SpringUtils.configureTest(applicationContext);

        user.setLdapPassword(OLD_PASSWORD);
        user.setDn(OLD_DN);
        user.setLdapName(OLD_LDAPNAME);
        user.setLogin("user");
    }

    @Test
    public void getLdapPassword() {
        assertThat(user.getLdapPassword(), is(OLD_PASSWORD));
    }

    @Test
    public void setLdapPassword() {
        user.setLdapPassword(NEW_PASSWORD);
        assertThat(user.getLdapPassword(), is(NEW_PASSWORD));
    }

    @Test
    public void isLdapAuthentication() {
        when(configurationUtil.isLdapProviderEnabled()).thenReturn(true);
        assertThat(user.isLdapAuthentication(), is(true));
    }

    @Test
    public void isNotLdapAuthentication() {
        when(configurationUtil.isLdapProviderEnabled()).thenReturn(false);
        assertThat(user.isLdapAuthentication(), is(false));
    }

    @Test
    public void when_isLdapAuthentication_isPasswordExpired() {
        when(configurationUtil.isLdapProviderEnabled()).thenReturn(true);
        user.setPasswordExpired(true);

        assertThat(user.isPasswordExpired(), is(true));
    }

    @Test
    public void when_isNotLdapAuthentication_isPasswordExpired() {
        when(configurationUtil.isLdapProviderEnabled()).thenReturn(false);
        user.setPasswordExpired(false);
        user.setPasswordExpireDate(new Date(java.lang.System.currentTimeMillis() - 1));

        assertThat(user.isPasswordExpired(), is(true));
    }

    @Test
    public void setDn() {
        assertThat(user.getDn(), is(OLD_DN));
    }

    @Test
    public void getDn() {
        user.setDn(NEW_DN);
        assertThat(user.getDn(), is(NEW_DN));
    }

    @Test
    public void setLdapName() {
        user.setLdapName(NEW_LDAPNAME);
        assertThat(user.getLdapName(), is(NEW_LDAPNAME));
    }

    @Test
    public void buildLdapName() {
        assertThat(user.buildLdapName(), is("uid=user,ou=People"));
    }

}
