package com.autentia.tnt.manager.security;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.acegisecurity.ldap.InitialDirContextFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;

public class AuthenticationManagerLdapImplTest {

    private static final String PASSWORD = "password";

    private static final String NEW_PASSWORD = "newPassword";

    private static final String[] RANDOM = new String[] { "random" };

    private AuthenticationManagerLdapImpl sut;

    private ConfigurationUtil cfg = mock(ConfigurationUtil.class);

    private IUserRolesService userRolesService = mock(IUserRolesService.class);

    private CustomBindAuthenticator customBindAuthenticator = mock(CustomBindAuthenticator.class);

    private InitialDirContextFactory initialDirContextFactory = mock(InitialDirContextFactory.class);

    private AuthenticationManagerLdapTemplate authManagerLdapTemplate;

    private ConfigurationUtil configurationUtil = mock(ConfigurationUtil.class);

    private ApplicationContext ctx = mock(ApplicationContext.class);

    private AuthenticationManager authenticationManager = mock(AuthenticationManager.class);

    private User user;

    @Before
    public void init() throws NamingException {

        when(ctx.getBean("userDetailsService")).thenReturn(authenticationManager);
        when(ctx.getBean("ldapBindAuthenticator")).thenReturn(customBindAuthenticator);
        when(ctx.getBean("authenticationMangerLdapTemplate")).thenReturn(authManagerLdapTemplate);
        when(customBindAuthenticator.getInitialDirContextFactory()).thenReturn(initialDirContextFactory);

        when(ctx.getBean("configuration")).thenReturn(configurationUtil);
        when(configurationUtil.isLdapProviderEnabled()).thenReturn(Boolean.TRUE);

        SpringUtils.configureTest(ctx);

        authManagerLdapTemplate = spy(AuthenticationManagerLdapTemplate.class);

        sut = new AuthenticationManagerLdapImpl(cfg, userRolesService, authManagerLdapTemplate);

        user = getUser();

    }


    @Test
    public void givenCorrectPasswordChecksPassword() throws Exception {

        assertThat(sut.checkPassword(user, PASSWORD), is(Boolean.TRUE));

    }

    @Test
    public void givenIncorrectPasswordChecksPassword() throws Exception {

        assertThat(sut.checkPassword(user, "incorrect-password"), is(Boolean.FALSE));

    }

    @Test
    public void changeAsUserPassword() throws Exception {

        doNothing().when(authManagerLdapTemplate).modifyAttributes(any(DirContext.class), eq(user), any(ModificationItem[].class));

        sut.changePassword(user, NEW_PASSWORD);

        verify(authManagerLdapTemplate).changePassword(user, NEW_PASSWORD);
        verify(authManagerLdapTemplate).getTemplate(user);
        verify(authManagerLdapTemplate).getCallback(user, NEW_PASSWORD);
        verify(authManagerLdapTemplate).changeLdapUserPassword(any(DirContext.class), eq(NEW_PASSWORD), eq(user));

    }

    @Test
    public void changeAsAdminPassword() throws Exception {

        User userAdmin = getUserAdmin();

        doNothing().when(authManagerLdapTemplate).modifyAttributes(any(DirContext.class), eq(user), any(ModificationItem[].class));

        sut.changePassword(user, NEW_PASSWORD, userAdmin);

        verify(authManagerLdapTemplate).changePassword(user, NEW_PASSWORD, userAdmin);
        verify(authManagerLdapTemplate).getTemplate(userAdmin);
        verify(authManagerLdapTemplate).getCallback(user, NEW_PASSWORD);
        verify(authManagerLdapTemplate).changeLdapUserPassword(any(DirContext.class), eq(NEW_PASSWORD), eq(user));

    }


    @Test
    public void resetPassword() throws Exception {

        User userAdmin = getUserAdmin();

        Principal principal = mock(Principal.class);
        when(principal.getUser()).thenReturn(userAdmin);

        when(authenticationManager.getCurrentPrincipal()).thenReturn(principal);

        final String randomPassword = sut.generateRandomPassword(RANDOM, RANDOM, RANDOM, RANDOM, RANDOM);

        doNothing().when(authManagerLdapTemplate).modifyAttributes(any(DirContext.class), eq(user), any(ModificationItem[].class));

        sut.resetPassword(user, RANDOM, RANDOM, RANDOM, RANDOM, RANDOM);
        verify(authManagerLdapTemplate).changePassword(user, randomPassword, userAdmin);
        verify(authManagerLdapTemplate, times(2)).getTemplate(userAdmin);
        verify(authManagerLdapTemplate).getCallback(user, randomPassword);
        verify(authManagerLdapTemplate).getCallback(user);
        verify(authManagerLdapTemplate).changeLdapUserPassword(any(DirContext.class), eq(randomPassword), eq(user));
        verify(authManagerLdapTemplate).setLdapUserPasswordResetFlag(any(DirContext.class), eq(user));

    }

    private User getUserAdmin() {
        User userAdmin = new User();
        userAdmin.setDn("admin");
        userAdmin.setLdapPassword("adminPassword");
        return userAdmin;
    }


    private User getUser() {
        User user = new User();
        user.setLdapPassword(PASSWORD);
        user.setPasswordExpired(Boolean.TRUE);
        user.setDn("dc=autentia,dc=com");
        user.setLdapName("uid=user");
        user.setLogin("user");
        return user;
    }
}