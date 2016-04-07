package com.autentia.tnt.manager.security;

import com.autentia.tnt.businessobject.Role;
import com.autentia.tnt.businessobject.User;
import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.providers.ldap.LdapAuthenticator;
import org.acegisecurity.providers.ldap.LdapAuthoritiesPopulator;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.junit.Before;
import org.junit.Test;

import java.net.Authenticator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LdapCustomAuthenticationProviderTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final int ID = 1;

    private LdapCustomAuthenticationProvider sut;

    private UserDetailsService userDetailsService = mock(UserDetailsService.class);

    private LdapUserDetails ldapUserDetails = mock(LdapUserDetails.class);

    private LdapAuthenticator authenticator = mock(LdapAuthenticator.class);

    private LdapAuthoritiesPopulator ldapAuthoritiesPopulator = mock(LdapAuthoritiesPopulator.class);


    @Before
    public void init(){
        sut = new LdapCustomAuthenticationProvider(authenticator, ldapAuthoritiesPopulator);
        sut.setUserDetailsService(userDetailsService);
    }

    @Test
    public void shouldCallLoadUserFromServiceWhenCreateUserDetailsIsCalled(){

        User user = getUserForTest();
        Principal principal = mock(Principal.class);

        when(userDetailsService.loadUserByUsername(USERNAME)).thenReturn(principal);
        when(principal.getUser()).thenReturn(user);
        when(principal.getAuthorities()).thenReturn(new GrantedAuthority[1]);

        sut.createUserDetails(ldapUserDetails, USERNAME, PASSWORD);

        verify(userDetailsService).loadUserByUsername(USERNAME);
        verify(ldapUserDetails).isEnabled();
    }

    private User getUserForTest() {

        Role role = mock(Role.class);
        when(role.getId()).thenReturn(1);

        User user = mock(User.class);
        when(user.getId()).thenReturn(1);
        when(user.getDepartmentId()).thenReturn(1);
        when(user.getLogin()).thenReturn("login");
        when(user.getPassword()).thenReturn("passowrd");
        when(user.isActive()).thenReturn(true);
        when(user.getName()).thenReturn("name");
        when(user.getRole()).thenReturn(role);

        return user;
    }

}