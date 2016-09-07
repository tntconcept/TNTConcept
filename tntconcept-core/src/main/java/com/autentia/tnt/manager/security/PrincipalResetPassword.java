package com.autentia.tnt.manager.security;

import com.autentia.tnt.businessobject.User;

public class PrincipalResetPassword extends Principal {


    public PrincipalResetPassword(String username, String password) {
        super();

        this.username = username;
        this.password = password;
        this.enabled = Boolean.TRUE;

        this.dto = new User();
        this.dto.setActive(Boolean.TRUE);
        this.dto.setLogin(username);
        this.dto.setLdapName(getUser().buildLdapName());
        this.dto.setLdapPassword(password);
        this.dto.setDn("uid="+username+",ou=People,dc=autentia,dc=com");
        this.dto.setResetPassword(Boolean.TRUE);

    }
}
