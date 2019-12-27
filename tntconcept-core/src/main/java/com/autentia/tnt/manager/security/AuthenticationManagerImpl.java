package com.autentia.tnt.manager.security;

import java.util.List;

import org.springframework.beans.factory.FactoryBean;

public class AuthenticationManagerImpl implements FactoryBean {

    private final boolean ldapProviderEnabled;

    private final List<AuthenticationManager> authenticationManagers;

    public AuthenticationManagerImpl(boolean ldapProviderEnabled, List<AuthenticationManager> authenticationManagers) {
        this.ldapProviderEnabled = ldapProviderEnabled;
        this.authenticationManagers = authenticationManagers;
    }

    public AuthenticationManager getObject() throws Exception {

        AuthenticationManager authenticationManager = null;

        for (AuthenticationManager authManager : this.authenticationManagers) {

            if (authManager instanceof AuthenticationManagerLdapImpl && ldapProviderEnabled) {
                authenticationManager = authManager;
            }
            if (authManager instanceof AuthenticationManagerDaoImpl && !ldapProviderEnabled) {
                authenticationManager = authManager;
            }
        }

        return authenticationManager;
    }

    public Class getObjectType() {
        return AuthenticationManager.class;
    }

    public boolean isSingleton() {
        return true;
    }

}
