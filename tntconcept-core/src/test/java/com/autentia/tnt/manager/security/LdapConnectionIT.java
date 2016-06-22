package com.autentia.tnt.manager.security;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.LdapName;

import org.acegisecurity.ldap.DefaultInitialDirContextFactory;
import org.acegisecurity.providers.ldap.LdapAuthenticationProvider;
import org.acegisecurity.providers.ldap.LdapAuthoritiesPopulator;
import org.acegisecurity.providers.ldap.authenticator.BindAuthenticator;
import org.acegisecurity.providers.ldap.populator.DefaultLdapAuthoritiesPopulator;
import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.junit.Before;
import org.junit.Test;


public class LdapConnectionIT {

    private static final String USERNAME = "user1";

    private static final String PASSWORD = "P4ssword";

    private static final String NEW_PASSWORD = "P4ssword";

    public static final String DN = "uid=user1,ou=People";

    private LdapAuthenticationProvider ldapAuthenticationProvider;

    private BindAuthenticator authenticator;

    private LdapAuthoritiesPopulator ldapAuthoritiesPopulator;

    private DefaultInitialDirContextFactory defaultInitialDirContextFactory;

    @Before
    public void init() {

        defaultInitialDirContextFactory = new DefaultInitialDirContextFactory(
                "ldap://openldap-dev:389/dc=autentia,dc=com");

        defaultInitialDirContextFactory.setUseLdapContext(true);
        this.authenticator = new BindAuthenticator(defaultInitialDirContextFactory);
        this.authenticator.setUserAttributes(new String[]{"+", "*"});
        String[] pattern = { "cn={0}", "uid={0},ou=People" };
        this.authenticator.setUserDnPatterns(pattern);
        this.ldapAuthoritiesPopulator = new DefaultLdapAuthoritiesPopulator(defaultInitialDirContextFactory, "");
        this.ldapAuthenticationProvider = new LdapAuthenticationProvider(this.authenticator,
                this.ldapAuthoritiesPopulator);
    }

    @Test
    // @Ignore
    public void shouldEstablishConnectionWithLdapServer() {

        LdapUserDetails userDetails = this.authenticator.authenticate(USERNAME, PASSWORD);
        userDetails.getAttributes().getAll();
        assertThat(userDetails.getUsername(), is(USERNAME));
        assertThat(userDetails.isCredentialsNonExpired(), is(false));

    }

    @Test
    public void shouldModifyUserPassword() throws NamingException {

        String user = "uid=user1,ou=People,dc=autentia,dc=com";

        ModificationItem[] mods = new ModificationItem[1];

        Attribute newPasswordAttribute = new BasicAttribute("userPassword", NEW_PASSWORD);

        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, newPasswordAttribute);

        LdapName ldapName = new LdapName("uid=user1,ou=People");

        DirContext dirContext = this.defaultInitialDirContextFactory.newInitialDirContext(user, PASSWORD);
        dirContext.modifyAttributes(ldapName, mods);

    }

    @Test
    public void getSchemaTest() throws NamingException {

        String user = "uid=user1,ou=People,dc=autentia,dc=com";

        DirContext ctx = this.defaultInitialDirContextFactory.newInitialDirContext(user, PASSWORD);

        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope( SearchControls.OBJECT_SCOPE );
        searchControls.setReturningAttributes( new String[]
                {"+"} );

        NamingEnumeration<SearchResult> results = ctx.search( "uid=user1,ou=People", "(ObjectClass=*)", searchControls );

        SearchResult result = results.next();
        Attributes entry = result.getAttributes();

        Attribute objectClasses = entry.get( "objectClass" );
        Attribute graceTime = entry.get("pwdGraceUseTime");

    }


}
