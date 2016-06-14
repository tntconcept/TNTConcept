package com.autentia.tnt.manager.security;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.codec.digest.DigestUtils;

public class AuthenticationManagerDaoImpl extends AuthenticationManager {

    /**
     * Constructor
     *
     * @param cfg configuration object
     * @param userRolesService delegate for getting user's roles
     */
    public AuthenticationManagerDaoImpl(ConfigurationUtil cfg, IUserRolesService userRolesService) {
        super(cfg, userRolesService);
    }

    public static AuthenticationManager getDefault() {
        return (AuthenticationManager)SpringUtils.getSpringBean("userDetailsServiceDao");
    }

    public boolean checkPassword(User user, String passwd) {

        return DigestUtils.shaHex(passwd).equalsIgnoreCase(user.getPassword());
    }

    @Override
    public void changePassword(User user, String password) {
        user.setPassword(DigestUtils.shaHex(password));
    }

    public String resetPassword(User user, String[] rnd0, String[] rnd1, String[] rnd2, String[] rnd3, String[] rnd4) {
        String changedPassword = generateRandomPassword(rnd0, rnd1, rnd2, rnd3, rnd4);
        changePassword(user, changedPassword);
        return changedPassword;
    }

}
