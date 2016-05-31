/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L. Copyright (C) 2007 Autentia Real Bussiness
 * Solution S.L. This program is free software: you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation, either version 3 of the License. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * ChangePasswordBean.java
 */

package com.autentia.tnt.bean;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.FacesUtils;

/**
 * Bean que se ocupa de la funcionalidad de cambio de password por expiración
 * 
 * @author <a href="www.autentia.com">AUTENTIA</a>
 */
public class ExpiredPasswordBean extends BaseBean {

    private static final Log log = LogFactory.getLog(ExpiredPasswordBean.class);

    /** */
    private static final long serialVersionUID = 4873802999327338762L;

    private static final AuthenticationManager authHandler = AuthenticationManager.getDefault();

    private static UserManager manager = UserManager.getDefault();

    private static AuthenticationManager authMgr = AuthenticationManager.getDefault();

    /** Nueva password */
    private String password;

    /** Repetición de password */
    private String passwordRepe;

    /**
     * Cambia la password
     */
    public String changePassword() {
        String result = NavigationResults.CHANGE_PASSWORD_OK;

        try {
            User user = authMgr.getCurrentPrincipal().getUser();
            if (this.password.equals(passwordRepe)) {
                // Alquien por AOP No nos concede privilegios para acceder
                // manager.changeExpiredPasswordExpired(user, password);

                AuthenticationManager authHandler = AuthenticationManager.getDefault();

                if (authHandler.checkPassword(user, password)) {
                    result = null;
                    FacesUtils.addErrorMessage(null, "error.newPasswordEqualsOldPassword"); // Las contraseñas no coinciden
                } else {
                    Date expireDate = calcNextExpireDate(user.getPasswordExpireDate());
                    if (!ConfigurationUtil.getDefault().isLdapAuthentication()) {
                        user.setPasswordExpireDate(expireDate); // Establecemos la nueva fecha de expiración
                    }
                    authHandler.changePassword(user, password);
                    manager.updateEntity(user, false);
                }
            } else {
                result = null;
                FacesUtils.addErrorMessage(null, "error.newPasswordsNotEquals"); // Las contraseñas no coinciden
            }
            return result;
        } catch (Exception ex) {
            return super.getReturnError("ExpiredPasswordBean.changePassword", ex);
        }
    }

    private Date calcNextExpireDate(Date expireDate) {
        int daysToExpirePassword = ConfigurationUtil.getDefault().getDaysToExpirePassword();

        if (expireDate == null) {
            expireDate = new Date();
        }

        return DateUtils.addDays(expireDate, daysToExpirePassword);
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Returns the passwordRepe.
     */
    public String getPasswordRepe() {
        return passwordRepe;
    }

    /**
     * @param passwordRepe The passwordRepe to set.
     */
    public void setPasswordRepe(String passwordRepe) {
        this.passwordRepe = passwordRepe;
    }
}
