package com.autentia.tnt.bean;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.Date;

import static com.autentia.tnt.bean.NavigationResults.CHANGE_PASSWORD_OK;
import static org.junit.Assert.*;
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
    private static final int DAYS_TO_EXPIRE_PASSWORD = 365;

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

        sut = spy(ChangePasswordBean.class);

        doNothing().when(sut).addErrorMessage(anyString(), anyString());
        doNothing().when(sut).addErrorMessage(anyString());

    }

    @Test
    public void shouldChangePasswordTest() {
        sut.setPassword(NEW_PASSWORD);
        sut.setPasswordRepe(NEW_PASSWORD);
        sut.setPasswordOld(OLD_PASSWORD);
        user.setPasswordExpired(true);

        final String result = sut.changePassword();
        assertEquals(CHANGE_PASSWORD_OK, result);
        assertFalse(user.isPasswordExpired());

    }

    @Test
    public void givenDifferentNewPasswordsShouldSendErrorMessageTest() {
        sut.setPassword(NEW_PASSWORD);
        sut.setPasswordRepe(NEW_PASSWORD + "INVALID");
        sut.setPasswordOld(OLD_PASSWORD);
        final String result = sut.changePassword();
        assertNull(result);
        verify(sut).addErrorMessage("error.newPasswordsNotEquals");

    }

    @Test
    public void givenSameNewPasswordsAndOldPasswordShouldSendErrorMessageTest() {
        sut.setPassword(OLD_PASSWORD);
        sut.setPasswordRepe(OLD_PASSWORD);
        sut.setPasswordOld(OLD_PASSWORD);
        final String result = sut.changePassword();
        assertNull(result);
        verify(sut).addErrorMessage("error.newPasswordEqualsOldPassword");
    }

    @Test
    public void givenDifferentActualPasswordShouldSendErrorMessageTest() {

        sut.setPassword(NEW_PASSWORD);
        sut.setPasswordRepe(NEW_PASSWORD);
        sut.setPasswordOld(OLD_PASSWORD + "INVALID");
        final String result = sut.changePassword();
        assertNull(result);
        verify(sut).addErrorMessage("error.invalidPassword");

    }

    @Test
    public void shouldModifyPasswordInDbTest(){

        doReturn(false).when(user).isLdapAuthentication();
        doReturn(DAYS_TO_EXPIRE_PASSWORD).when(configurationUtil).getDaysToExpirePassword();
        doReturn(new Date()).when(sut).calcNextExpireDate();

        doNothing().when(user).setPasswordExpireDate(any());

        sut.setPassword(NEW_PASSWORD);
        sut.setPasswordRepe(NEW_PASSWORD);
        sut.setPasswordOld(OLD_PASSWORD);

        final String result = sut.changePassword();
        assertEquals(CHANGE_PASSWORD_OK, result);
        verify(sut).calcNextExpireDate();

    }

}