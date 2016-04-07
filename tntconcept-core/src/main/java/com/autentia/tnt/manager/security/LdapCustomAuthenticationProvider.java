package com.autentia.tnt.manager.security;

import com.autentia.tnt.businessobject.User;
import org.acegisecurity.providers.ldap.LdapAuthenticationProvider;
import org.acegisecurity.providers.ldap.LdapAuthenticator;
import org.acegisecurity.providers.ldap.LdapAuthoritiesPopulator;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.ldap.LdapUserDetails;

public class LdapCustomAuthenticationProvider extends LdapAuthenticationProvider {

    private UserDetailsService userDetailsService;

    public LdapCustomAuthenticationProvider(LdapAuthenticator authenticator, LdapAuthoritiesPopulator authoritiesPopulator) {
        super(authenticator, authoritiesPopulator);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected UserDetails createUserDetails(LdapUserDetails ldapUser, String username, String password) {

        Principal principalFromDB = (Principal) userDetailsService.loadUserByUsername(username);

        return mergeUsers(ldapUser, principalFromDB, password);
    }

    private Principal mergeUsers(LdapUserDetails ldapUser, Principal principalFromDB, String password) {

        User user = principalFromDB.getUser();
        user.setPassword(password);
        user.setActive(ldapUser.isEnabled());

        return new Principal(user, principalFromDB.getAuthorities());
    }
}
