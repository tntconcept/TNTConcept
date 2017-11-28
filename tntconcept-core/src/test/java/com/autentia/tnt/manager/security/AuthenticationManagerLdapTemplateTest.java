package com.autentia.tnt.manager.security;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.security.providers.ldap.authenticator.BindAuthenticator;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class AuthenticationManagerLdapTemplateTest {

    private static final String PASSWORD = "password";

    private static final String NEW_PASSWORD = "newPassword";

    private AuthenticationManagerLdapTemplate sut;

    private BindAuthenticator bindAuthenticator = mock(BindAuthenticator.class);

    private DirContext dirContext = mock(DirContext.class);

    private ConfigurationUtil configurationUtil = mock(ConfigurationUtil.class);

    private ApplicationContext ctx = mock(ApplicationContext.class);

    private User user = new User();

    @Before
    public void init() throws NamingException {

        when(ctx.getBean("ldapBindAuthenticator")).thenReturn(bindAuthenticator);
        when(ctx.getBean("configuration")).thenReturn(configurationUtil);
        when(configurationUtil.isLdapProviderEnabled()).thenReturn(Boolean.TRUE);

        SpringUtils.configureTest(ctx);

        sut = spy(AuthenticationManagerLdapTemplate.class);

        user.setLdapPassword(PASSWORD);
        user.setPasswordExpired(Boolean.TRUE);
        user.setDn("dc=autentia,dc=com");
        user.setLdapName("user");

    }

    @Test
    @Ignore("Until AuthenticationManagerLdapTemplate will fixed")
    public void shouldChangePasswordTest() throws NamingException {


        doNothing().when(sut).modifyAttributes(any(DirContext.class), eq(user), any(ModificationItem[].class));

        sut.changePassword(user, NEW_PASSWORD);

        verify(sut).getTemplate(user);
        verify(sut).getCallback(user, NEW_PASSWORD);
        try {
            verify(sut).changeLdapUserPassword(any(DirContext.class), eq(NEW_PASSWORD), eq(user));
        } catch (NamingException e) {
            e.printStackTrace();
            throw e;
        }

    }

}
