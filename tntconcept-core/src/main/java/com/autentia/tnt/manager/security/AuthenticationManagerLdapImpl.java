package com.autentia.tnt.manager.security;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;

public class AuthenticationManagerLdapImpl extends AuthenticationManager {

    private final AuthenticationManagerLdapTemplate authenticationManagerLdapTemplate;

    public AuthenticationManagerLdapImpl(ConfigurationUtil cfg, IUserRolesService userRolesService,
            AuthenticationManagerLdapTemplate authenticationManagerLdapTemplate) {
        super(cfg, userRolesService);
        this.authenticationManagerLdapTemplate = authenticationManagerLdapTemplate;
    }

    public static AuthenticationManager getDefault() {
        return (AuthenticationManager)SpringUtils.getSpringBean("userDetailsServiceLdap");
    }

    public boolean checkPassword(User user, String password) {
        return password.equalsIgnoreCase(user.getLdapPassword());
    }

    public String resetPassword(User user, String[] rnd0, String[] rnd1, String[] rnd2, String[] rnd3, String[] rnd4) {

        String changedPassword = generateRandomPassword(rnd0, rnd1, rnd2, rnd3, rnd4);
        final User userAdmin = AuthenticationManager.getDefault().getCurrentPrincipal().getUser();
        changePassword(user, changedPassword, userAdmin);
        activateLdapUserPasswordResetFlag(user, userAdmin);
        return changedPassword;
    }

    private void activateLdapUserPasswordResetFlag(final User user,final User userAdmin) {
        authenticationManagerLdapTemplate.activateLdapUserPasswordResetFlag(user, userAdmin);

    }

    public void changePassword(final User user, final String password) {

        authenticationManagerLdapTemplate.changePassword(user, password);

    }

    protected void changePassword(final User user, final String password, User userAdmin) {

        user.setLdapName(user.buildLdapName());
        authenticationManagerLdapTemplate.changePassword(user, password, userAdmin);

    }

}
