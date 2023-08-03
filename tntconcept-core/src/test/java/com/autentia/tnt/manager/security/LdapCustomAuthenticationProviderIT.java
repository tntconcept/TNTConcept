package com.autentia.tnt.manager.security;

import com.autentia.tnt.util.SpringUtils;
import org.acegisecurity.Authentication;
import org.acegisecurity.providers.ProviderManager;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;


public class LdapCustomAuthenticationProviderIT {

    private static final String USERNAME = "user1";
    private static final String PASSWORD = "mehmeh";

    private ApplicationContext applicationContext;

    private ProviderManager sut;


    @Before
    public void setUp() throws Exception {

        applicationContext = new ClassPathXmlApplicationContext("applicationContext-test.xml");


        SpringUtils.configureTest(applicationContext);
//        sut = (ProviderManager) SpringUtils.getSpringBean("authenticationManager");

    }

    @Test
    @Ignore
    public void shouldHaveAnLDAPProvider() throws Exception {

        boolean hasLDAPProvider = false;
        for (Object provider : sut.getProviders()) {
            if (provider instanceof LdapCustomAuthenticationProvider) {
                hasLDAPProvider = true;
            }
        }

        assertTrue(hasLDAPProvider);
    }

    @Test
    @Ignore
    public void shouldLoadUserByUsername() {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(USERNAME, PASSWORD);

        Authentication resultingAuthentication = sut.doAuthentication(authenticationToken);

        assertTrue(resultingAuthentication.isAuthenticated());
    }

}