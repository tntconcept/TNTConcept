package com.autentia.tnt.bean;

import java.util.Date;

import com.autentia.tnt.businessobject.User;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.ldap.LdapDataAccessException;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.FacesUtils;

public abstract class AbstractPasswordBean extends BaseBean {

    protected static final Log log = LogFactory.getLog(AbstractPasswordBean.class);

    protected UserManager manager = UserManager.getDefault();

    protected AuthenticationManager authMgr = AuthenticationManager.getDefault();

    /**
     * Nueva password
     */
    protected String password;

    /**
     * Repetición de password
     */
    protected String passwordRepe;

    /**
     * Password antigua
     */
    protected String passwordOld;

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
     * @return Returns the passwordOld.
     */
    public String getPasswordOld() {
        return passwordOld;
    }

    /**
     * @param passwordOld The passwordOld to set.
     */
    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
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

    /**
     * Cambia la password
     */
    public String changePassword() {

        String result = null;
        try {
            // Recuperamos el usuario actual
            User user = authMgr.getCurrentPrincipal().getUser();
            Boolean isReset = user.isResetPassword();

            // Comprobamos que la password antigua introducida es correcta
            if (!authMgr.checkPassword(user, passwordOld)) {
                // Avisamos que la password introducida no es correcta
                addErrorMessage("error.invalidPassword");
            } else {
                if (!this.password.equals(passwordRepe)) {
                    // Avisamos que las password introducidas no son iguales
                    addErrorMessage("error.newPasswordsNotEquals");
                } else {
                    if (authMgr.checkPassword(user, password)) {
                        addErrorMessage("error.newPasswordEqualsOldPassword"); // Las contraseñas no coinciden
                    } else {

                        authMgr.changePassword(user, password);

                        if (!user.isLdapAuthentication()) {
                            Date expireDate = calcNextExpireDate();
                            user.setPasswordExpireDate(expireDate); // Establecemos la nueva fecha de expiración
                            manager.updateEntity(user, false);
                        }
                        if ((isReset != null) && isReset) {
                            result = NavigationResults.RESET_PASSWORD_OK;
                        } else {
                            result = NavigationResults.CHANGE_PASSWORD_OK;
                        }

                    }

                }
            }
        } catch (LdapDataAccessException ex) {


            String message = ex.getMessage();
            if (ex.getCause() != null) {
                message = ex.getCause().getMessage();
            }
            addErrorMessage("error.ppolicy", message);

        } catch (BadCredentialsException ex) {
            addErrorMessage("error.administrator.needed");

        } catch (Exception ex) {
            result = returnError(ex);
        }
        return result;
    }

    protected Date calcNextExpireDate() {

        return DateUtils.addDays(new Date(), ConfigurationUtil.getDefault().getDaysToExpirePassword());
    }

    protected void addErrorMessage(String messageKey, Object... args) {
        FacesUtils.addErrorMessage(null, messageKey, args);
    }

    public abstract String returnError(Exception ex);
}