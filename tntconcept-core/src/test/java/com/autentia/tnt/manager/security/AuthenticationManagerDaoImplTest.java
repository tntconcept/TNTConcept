package com.autentia.tnt.manager.security;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.util.ConfigurationUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthenticationManagerDaoImplTest {

    private static final String CRYPT_PASSWORD = "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8";

    private static final String CRYPT_NEW_PASSWORD = "283d47a9338ed1100b5fe2a5aff2d1f7c799bfd0";

    private static final String NEW_PASSWORD = "newPassword";

    private static final String PASSWORD = "password";

    private static final String[] RANDOM_STRING = new String[] { "random" };

    private AuthenticationManager sut;

    private ConfigurationUtil cfg = mock(ConfigurationUtil.class);

    private IUserRolesService userRolesService = mock(IUserRolesService.class);

    private User user = new User();


    @Before
    public void init() {


        sut = new AuthenticationManagerDaoImpl(cfg, userRolesService);

        user.setPassword(CRYPT_PASSWORD);
    }

    @Test
    public void givenCorrectPasswordChecksPassword() throws Exception {


        assertTrue(sut.checkPassword(user, PASSWORD));

    }

    @Test
    public void givenIncorrectPasswordChecksPassword() throws Exception {

        assertFalse(sut.checkPassword(user, "incorrect-password"));

    }

    @Test
    public void shouldUpdateUserPasswordWithNewPassword() throws Exception {

        sut.changePassword(user, NEW_PASSWORD);
        assertEquals(CRYPT_NEW_PASSWORD, user.getPassword());

    }

    @Test
    public void shouldResetUserPassword() throws Exception {

        sut.resetPassword(user, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING);
        assertEquals("b5599d4896bb77df9f597e34004eff6fb55e148e", user.getPassword());

    }

}