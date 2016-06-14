package com.autentia.tnt.manager.security;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.acegisecurity.ldap.InitialDirContextFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;

public class AuthenticationManagerLdapTemplateTest {

    private static final String PASSWORD = "password";

    private static final String NEW_PASSWORD = "newPassword";

    private AuthenticationManagerLdapTemplate sut;

    private CustomBindAuthenticator customBindAuthenticator = mock(CustomBindAuthenticator.class);

    private InitialDirContextFactory initialDirContextFactory = mock(InitialDirContextFactory.class);

    private DirContext dirContext = mock(DirContext.class);

    private ConfigurationUtil configurationUtil = mock(ConfigurationUtil.class);

    private ApplicationContext ctx = mock(ApplicationContext.class);

    private User user = new User();

    @Before
    public void init() throws NamingException {

        when(ctx.getBean("ldapBindAuthenticator")).thenReturn(customBindAuthenticator);
        when(customBindAuthenticator.getInitialDirContextFactory()).thenReturn(initialDirContextFactory);

        when(ctx.getBean("configuration")).thenReturn(configurationUtil);
        when(configurationUtil.isLdapProviderEnabled()).thenReturn(Boolean.TRUE);

        SpringUtils.configureTest(ctx);

        sut = spy(AuthenticationManagerLdapTemplate.class);

        user.setLdapPassword(PASSWORD);
        user.setExpiredPassword(Boolean.TRUE);
        user.setDn("dc=autentia,dc=com");
        user.setLdapName("user");

    }

    @Test
    public void shouldApplyUpdate() throws NamingException {

        assertThat(user.isPasswordExpired(), is(Boolean.TRUE));
        assertThat(user.getLdapPassword(), is(PASSWORD));
        Attribute newPasswordAttribute = new BasicAttribute("userPassword", NEW_PASSWORD);
        ModificationItem[] mods = new ModificationItem[1];
        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, newPasswordAttribute);
        try {
            dirContext.modifyAttributes(user.getLdapName(), mods);
        } catch (NamingException e) {
            AuthenticationManagerLdapImpl.log.error(e);
            throw e;
        }
        user.setExpiredPassword(Boolean.FALSE);
        user.setLdapPassword(NEW_PASSWORD);
        assertThat(user.isPasswordExpired(), is(Boolean.FALSE));
        assertThat(user.getLdapPassword(), is(NEW_PASSWORD));

    }

    @Test(expected = NamingException.class)
    public void shouldThrowNamingExceptionWhenApplyUpdate() throws NamingException {

        user.setExpiredPassword(Boolean.FALSE);

        doThrow(new NamingException()).when(dirContext).modifyAttributes(eq(user.getLdapName()),
                any(ModificationItem[].class));

        assertThat(user.isPasswordExpired(), is(Boolean.FALSE));
        assertThat(user.getLdapPassword(), is(PASSWORD));

        try {
            Attribute newPasswordAttribute = new BasicAttribute("userPassword", NEW_PASSWORD);
            ModificationItem[] mods = new ModificationItem[1];
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, newPasswordAttribute);
            try {
                dirContext.modifyAttributes(user.getLdapName(), mods);
            } catch (NamingException e) {
                AuthenticationManagerLdapImpl.log.error(e);
                throw e;
            }
            user.setExpiredPassword(Boolean.FALSE);
            user.setLdapPassword(NEW_PASSWORD);
        } catch (NamingException e) {
            assertThat(user.isPasswordExpired(), is(Boolean.FALSE));
            assertThat(user.getLdapPassword(), is(PASSWORD));
            throw e;
        }
    }

    @Test
    public void shouldChangePasswordTest() throws NamingException {


        doNothing().when(sut).modifyAttributes(any(DirContext.class), eq(user), any(ModificationItem[].class));

        sut.changePassword(user, NEW_PASSWORD);

        verify(sut).getTemplate(user);
        verify(sut).getCallback(user, NEW_PASSWORD);
        try {
            verify(sut).applyUpdate(any(DirContext.class), eq(NEW_PASSWORD), eq(user));
        } catch (NamingException e) {
            e.printStackTrace();
            throw e;
        }

    }

}