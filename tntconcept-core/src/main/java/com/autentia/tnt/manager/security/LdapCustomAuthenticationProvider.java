package com.autentia.tnt.manager.security;

import com.autentia.tnt.businessobject.User;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.ldap.LdapAuthenticationProvider;
import org.acegisecurity.providers.ldap.LdapAuthenticator;
import org.acegisecurity.providers.ldap.LdapAuthoritiesPopulator;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.directory.Attributes;

public class LdapCustomAuthenticationProvider extends LdapAuthenticationProvider {

    private UserDetailsService userDetailsService;

    private static final Log log = LogFactory.getLog(LdapCustomAuthenticationProvider.class);

    public LdapCustomAuthenticationProvider(LdapAuthenticator authenticator,
            LdapAuthoritiesPopulator authoritiesPopulator) {
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

        Principal principalFromDB = (Principal)userDetailsService.loadUserByUsername(username);

        return mergeUsers(ldapUser, principalFromDB, password);
    }

    private Principal mergeUsers(LdapUserDetails ldapUser, Principal principalFromDB, String password) {

        User user = principalFromDB.getUser();
        user.setPassword(password);
        user.setActive(ldapUser.isEnabled());
        user.setExpiredPassword(checkExpiredPassword(ldapUser.getAttributes()));

        return new Principal(user, principalFromDB.getAuthorities());
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        UserDetails userDetails = null;
        try {
            userDetails = super.retrieveUser(username, authentication);

        } catch (AuthenticationException a) {
            a.getCause();
        }
        return userDetails;
    }

    protected Boolean checkExpiredPassword(Attributes attributes) {

        return attributes.get("pwdGraceUseTime") != null;

    }

}
