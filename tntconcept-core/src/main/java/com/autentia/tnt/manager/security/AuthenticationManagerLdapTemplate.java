package com.autentia.tnt.manager.security;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.util.SpringUtils;
import org.acegisecurity.ldap.LdapCallback;
import org.acegisecurity.ldap.LdapTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

public class AuthenticationManagerLdapTemplate {

    private static final Log log = LogFactory.getLog(AuthenticationManager.class);

    protected LdapTemplate getTemplate(User loggedUser) {
        return new LdapTemplate(((CustomBindAuthenticator)SpringUtils.getSpringBean("ldapBindAuthenticator"))
                .getInitialDirContextFactory(), loggedUser.getDn(), loggedUser.getLdapPassword());
    }

    protected LdapCallback getCallback(final User user, final String password) {
        return new LdapCallback() {

            public User doInDirContext(DirContext dirContext) throws NamingException {

                return applyUpdate(dirContext, password, user);
            }
        };
    }

    protected User applyUpdate(DirContext dirContext, String password, User user) throws NamingException {
        Attribute newPasswordAttribute = new BasicAttribute("userPassword", password);
        ModificationItem[] mods = new ModificationItem[1];
        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, newPasswordAttribute);
        try {
            modifyAttributes(dirContext, user, mods);
        } catch (NamingException e) {
            log.error(e);
            throw e;
        }
        user.setExpiredPassword(Boolean.FALSE);
        user.setLdapPassword(password);
        return user;
    }

    protected void modifyAttributes(DirContext dirContext, User user, ModificationItem[] mods) throws NamingException {
        dirContext.modifyAttributes(user.getLdapName(), mods);
    }

    public void changePassword(final User user, final String password) {

        getTemplate(user).execute(getCallback(user, password));

    }

    protected void changePassword(final User user, final String password, User userAdmin) {

        getTemplate(userAdmin).execute(getCallback(user, password));

    }

}