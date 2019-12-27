package com.autentia.tnt.manager.security;

import org.acegisecurity.ldap.InitialDirContextFactory;
import org.acegisecurity.providers.ldap.authenticator.BindAuthenticator;

public class CustomBindAuthenticator extends BindAuthenticator {
    /**
     * Create an initialized instance to the {@link InitialDirContextFactory} provided.
     *
     * @param initialDirContextFactory
     */
    public CustomBindAuthenticator(InitialDirContextFactory initialDirContextFactory) {
        super(initialDirContextFactory);
    }

    @Override
    protected InitialDirContextFactory getInitialDirContextFactory() {
        return super.getInitialDirContextFactory();
    }

}
