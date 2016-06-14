package com.autentia.tnt.manager.security;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.util.ConfigurationUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
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


        assertThat(sut.checkPassword(user, PASSWORD), is(Boolean.TRUE));

    }

    @Test
    public void givenIncorrectPasswordChecksPassword() throws Exception {

        assertThat(sut.checkPassword(user, "incorrect-password"), is(Boolean.FALSE));

    }

    @Test
    public void shouldUpdateUserPasswordWithNewPassword() throws Exception {

        sut.changePassword(user, NEW_PASSWORD);
        assertThat(user.getPassword(), is(CRYPT_NEW_PASSWORD));

    }

    @Test
    public void shouldResetUserPassword() throws Exception {

        sut.resetPassword(user, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING, RANDOM_STRING);
        assertThat(user.getPassword(), is ("b5599d4896bb77df9f597e34004eff6fb55e148e"));

    }

}