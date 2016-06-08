package com.autentia.tnt.bean;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class ChangePasswordBeanTest {

    private final static AuthenticationManager authMgr = mock(AuthenticationManager.class);

    private final static ApplicationContext applicationContext = mock(ApplicationContext.class);

    private final static ConfigurationUtil configurationUtil = mock(ConfigurationUtil.class);

    private final static UserManager manager = mock(UserManager.class);

    private final static User user = mock(User.class);

    private final static Principal principal = mock(Principal.class);

    private static final String OLD_PASSWORD = "oldPassword";

    private static final String NEW_PASSWORD = "newPassword";

    private static ChangePasswordBean sut;

    @Before
    public void init() {

        when(applicationContext.getBean("userDetailsService")).thenReturn(authMgr);
        when(applicationContext.getBean("managerUser")).thenReturn(manager);
        when(applicationContext.getBean("configuration")).thenReturn(configurationUtil);

        when(authMgr.getCurrentPrincipal()).thenReturn(principal);
        when(principal.getUser()).thenReturn(user);
        when(authMgr.checkPassword(user, OLD_PASSWORD)).thenReturn(Boolean.TRUE);

        when(user.getPassword()).thenReturn(OLD_PASSWORD);
        when(user.getLdapPassword()).thenReturn(OLD_PASSWORD);
        when(user.isLdapAuthentication()).thenReturn(true);

        doNothing().when(manager).updateEntity(user, false);

        SpringUtils.configureTest(applicationContext);

        this.sut = spy(ChangePasswordBean.class);

        doNothing().when(sut).addErrorMessage(anyString(), anyString());
        doNothing().when(sut).addErrorMessage(anyString());

    }

    @Test
    public void shouldChangePasswordTest() throws Exception {
        sut.setPassword(NEW_PASSWORD);
        sut.setPasswordRepe(NEW_PASSWORD);
        sut.setPasswordOld(OLD_PASSWORD);
        this.user.setExpiredPassword(true);

        final String result = sut.changePassword();
        assertThat(result, is(NavigationResults.CHANGE_PASSWORD_OK));
        assertThat(this.user.isPasswordExpired(),is(false));

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
    public void givenSameNewPasswordsAndOldPasswordShouldSendErrorMessageTest() throws Exception {
        sut.setPassword(OLD_PASSWORD);
        sut.setPasswordRepe(OLD_PASSWORD);
        sut.setPasswordOld(OLD_PASSWORD);
        final String result = sut.changePassword();
        assertThat(result, is(nullValue()));
        verify(sut).addErrorMessage("error.newPasswordEqualsOldPassword");
    }

    @Test
    public void givenDifferentActualPasswordShouldSendErrorMessageTest() {

        sut.setPassword(NEW_PASSWORD);
        sut.setPasswordRepe(NEW_PASSWORD);
        sut.setPasswordOld(OLD_PASSWORD + "INVALID");
        final String result = sut.changePassword();
        assertThat(result, is(nullValue()));
        verify(sut).addErrorMessage("error.invalidPassword");

    }

    @Test
    public void shouldModifyPasswordInDbTest(){

        doReturn(false).when(user).isLdapAuthentication();
        doReturn(new Integer(365)).when(configurationUtil).getDaysToExpirePassword();
        doReturn(new Date()).when(sut).calcNextExpireDate();

        doNothing().when(user).setPasswordExpireDate((Date) any());

        sut.setPassword(NEW_PASSWORD);
        sut.setPasswordRepe(NEW_PASSWORD);
        sut.setPasswordOld(OLD_PASSWORD);

        final String result = sut.changePassword();
        assertThat(result, is(NavigationResults.CHANGE_PASSWORD_OK));
        verify(sut).calcNextExpireDate();

    }

}