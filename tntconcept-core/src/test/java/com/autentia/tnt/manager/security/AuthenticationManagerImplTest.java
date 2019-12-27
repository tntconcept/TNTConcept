package com.autentia.tnt.manager.security;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.autentia.tnt.businessobject.User;

public class AuthenticationManagerImplTest {

    private static final String PASSWORD = "password";

    private static final String[] RANDOM_STRING = new String[] { "random" };

    private static AuthenticationManager sut;

    private User user = mock(User.class);

    private List<AuthenticationManager> list;

    private AuthenticationManager authenticationManagerLdap = mock(AuthenticationManagerLdapImpl.class);

    private AuthenticationManager authenticationManagerDao = mock(AuthenticationManagerDaoImpl.class);

    @Before
    public void init() {

        list = new ArrayList<AuthenticationManager>();
        list.add(authenticationManagerDao);
        list.add(authenticationManagerLdap);

    }

    @Test
    public void shouldReturnAuthenticationManagerLdapTest() throws Exception {

        sut = new AuthenticationManagerImpl(true, list).getObject();
        assertThat(sut, is(instanceOf(AuthenticationManagerLdapImpl.class)));
    }

    @Test
    public void shouldReturnAuthenticationManagerDaoTest() throws Exception {

        sut = new AuthenticationManagerImpl(false, list).getObject();
        assertThat(sut, is(instanceOf(AuthenticationManagerDaoImpl.class)));
    }

    @Test
    public void checkPasswordLdapTest() throws Exception {

        sut = new AuthenticationManagerImpl(true, list).getObject();
        sut.checkPassword(user, PASSWORD);
        verify(authenticationManagerLdap).checkPassword(user, PASSWORD);
    }

    @Test
    public void checkPasswordDaoTest() throws Exception {

        sut = new AuthenticationManagerImpl(false, list).getObject();
        sut.checkPassword(user, PASSWORD);
        verify(authenticationManagerDao).checkPassword(user, PASSWORD);
    }

    @Test
    public void changePasswordLdapTest() throws Exception {

        sut = new AuthenticationManagerImpl(true, list).getObject();
        sut.changePassword(user, PASSWORD);
        verify(authenticationManagerLdap).changePassword(user, PASSWORD);
    }

    @Test
    public void changePasswordDaoTest() throws Exception {

        sut = new AuthenticationManagerImpl(false, list).getObject();
        sut.changePassword(user, PASSWORD);
        verify(authenticationManagerDao).changePassword(user, PASSWORD);
    }

    @Test
    public void resetPasswordLdapTest() throws Exception {

        sut = new AuthenticationManagerImpl(true, list).getObject();
        sut.resetPassword(user, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING);
        verify(authenticationManagerLdap).resetPassword(user, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING,
                RANDOM_STRING, RANDOM_STRING);
    }

    @Test
    public void resetPasswordDaoTest() throws Exception {

        sut = new AuthenticationManagerImpl(false, list).getObject();
        sut.resetPassword(user, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING);
        verify(authenticationManagerDao).resetPassword(user, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING,
                RANDOM_STRING);
    }

}