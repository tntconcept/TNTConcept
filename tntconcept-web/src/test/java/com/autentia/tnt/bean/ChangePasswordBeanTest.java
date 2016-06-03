package com.autentia.tnt.bean;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class ChangePasswordBeanTest {

    private final static AuthenticationManager authHandler = mock(AuthenticationManager.class);

    private final static ApplicationContext applicationContext = mock(ApplicationContext.class);

    private final static UserManager manager = mock(UserManager.class);

    private final static User user = mock(User.class);

    private final static Principal principal = mock(Principal.class);

    private static final String OLD_PASSWORD = "oldPassword";

    private static final String NEW_PASSWORD = "newPassword";

    private static ChangePasswordBean sut;

    @Before
    public void init() {

        when(applicationContext.getBean("userDetailsService")).thenReturn(authHandler);
        when(applicationContext.getBean("managerUser")).thenReturn(manager);

        when(authHandler.getCurrentPrincipal()).thenReturn(principal);
        when(principal.getUser()).thenReturn(user);
        when(authHandler.checkPassword(user, OLD_PASSWORD)).thenReturn(Boolean.TRUE);

        when(user.getPassword()).thenReturn(DigestUtils.shaHex(OLD_PASSWORD));
        when(user.getLdapPassword()).thenReturn("");

        doNothing().when(manager).updateEntity(user, false);

        SpringUtils.configureTest(applicationContext);

        this.sut = spy(ChangePasswordBean.class);

        doNothing().when(sut).addErrorMessage(anyString());


    }

    @Test
    public void shouldChangePasswordTest() throws Exception {
        sut.setPassword(NEW_PASSWORD);
        sut.setPasswordRepe(NEW_PASSWORD);
        sut.setPasswordOld(OLD_PASSWORD);

        final String result = sut.changePassword();
        assertThat(result, is(NavigationResults.CHANGE_PASSWORD_OK));

    }

    @Test
    public void givenDifferentNewPasswordsShouldSendErrorMessageTest() throws Exception {
        sut.setPassword(NEW_PASSWORD);
        sut.setPasswordRepe(NEW_PASSWORD + "INVALID");
        sut.setPasswordOld(OLD_PASSWORD);
        final String result = sut.changePassword();
        assertThat(result, is(nullValue()));
        verify(sut).addErrorMessage("error.newPasswordsNotEquals");

    }

    @Test
    @Ignore("This is not implemented yet")
    public void givenSameNewPasswordsAndOldPasswordShouldSendErrorMessageTest() throws Exception {
        sut.setPassword(OLD_PASSWORD);
        sut.setPasswordRepe(OLD_PASSWORD);
        sut.setPasswordOld(OLD_PASSWORD);
        final String result = sut.changePassword();
        assertThat(result, is(nullValue()));
        verify(sut).addErrorMessage("error.newPasswordEqualsOldPassword");
    }

}