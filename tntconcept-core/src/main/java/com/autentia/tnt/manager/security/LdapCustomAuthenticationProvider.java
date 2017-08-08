package com.autentia.tnt.manager.security;

import com.autentia.tnt.businessobject.User;
import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.ldap.LdapAuthenticationProvider;
import org.acegisecurity.providers.ldap.LdapAuthenticator;
import org.acegisecurity.providers.ldap.LdapAuthoritiesPopulator;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

public class LdapCustomAuthenticationProvider extends LdapAuthenticationProvider {

    private UserDetailsService userDetailsService;

      private static final Log log = LogFactory.getLog(LdapCustomAuthenticationProvider.class);

    public LdapCustomAuthenticationProvider(LdapAuthenticator authenticator,
                                            LdapAuthoritiesPopulator authoritiesPopulator) {
        super(authenticator, authoritiesPopulator);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected UserDetails createUserDetails(LdapUserDetails ldapUser, String username, String password) {

        Principal principalFromDB = (Principal) userDetailsService.loadUserByUsername(username);

        return mergeUsers(ldapUser, principalFromDB, password);
    }

    protected Principal mergeUsers(LdapUserDetails ldapUser, Principal principalFromDB, String password) {
    	User user;
    	Boolean console = false;
    	
    	if(principalFromDB.getUser() == null){
    		user = new User();
    		console = true;
    	}else {
    		user = principalFromDB.getUser();
    	}
    	
        user.setDn(ldapUser.getDn());
        user.setLdapName(ldapUser.getDn().replace(",dc=autentia,dc=com", ""));
        user.setLdapPassword(password);
        user.setActive(ldapUser.isEnabled());
        user.setPasswordExpired(checkExpiredPassword(ldapUser.getAttributes()));
        user.setResetPassword(checkResetPassword(ldapUser.getAttributes()));
        
        Principal ret;
        if(console){
        	ret = new Principal(999, 999, user.getLdapName(), user.getLdapPassword(),
        			 user.isActive(),user.getName(), 999, principalFromDB.getAuthorities());
        }else{
        	ret = new Principal(user, user.getLdapPassword(), principalFromDB.getAuthorities());
        }
        return ret;
    }

    protected Boolean checkExpiredPassword(Attributes attributes) {

        return attributes.get(LdapAttributes.PWD_GRACE_LOGIN) != null;

    }

    protected Boolean checkResetPassword(Attributes attributes) {

        Boolean result = Boolean.FALSE;

        final Attribute pwdReset = attributes.get(LdapAttributes.PASSWORD_RESET);

        if (pwdReset != null) {
            try {
                result = Boolean.valueOf((String) pwdReset.get());
            } catch (NamingException e) {
                log.error("Incorrect value - " + pwdReset);
            }
        }
        return result;
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) {
        UserDetails userDetails = null;

        try {
            userDetails = super.retrieveUser(username, authentication);

        } catch (AuthenticationServiceException authenticationServiceException) {
            if (authenticationServiceException.getMessage().contains("LDAP: error code 50 - Operations are restricted to bind/unbind/abandon/StartTLS/modify password")) {
                userDetails = new PrincipalResetPassword(username, authentication.getCredentials().toString());
            } else {
                throw authenticationServiceException;
            }
        }
        return userDetails;
    }

}
