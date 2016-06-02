package com.autentia.tnt.manager.security;

import org.acegisecurity.providers.ProviderManager;
import org.acegisecurity.providers.dao.DaoAuthenticationProvider;
import org.acegisecurity.providers.ldap.LdapAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

public class CustomProviderManager extends ProviderManager {

    private final Boolean ldapEnabled;

    public CustomProviderManager(Boolean ldapProviderEnabled) {
        super();
        ldapEnabled = ldapProviderEnabled;
    }

    @Override
    public void setProviders(List newList) {
        List cloneList = new ArrayList(newList);
        for (Object provider : newList) {
            if (provider instanceof DaoAuthenticationProvider && ldapEnabled) {
                cloneList.remove(provider);
            }
            if (provider instanceof LdapAuthenticationProvider && !ldapEnabled) {
                cloneList.remove(provider);
            }
        }
        super.setProviders(cloneList);
    }

}
