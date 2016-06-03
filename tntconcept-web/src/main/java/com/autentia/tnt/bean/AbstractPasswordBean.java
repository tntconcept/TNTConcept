package com.autentia.tnt.bean;

import java.util.Date;

import com.autentia.tnt.businessobject.User;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.ldap.LdapDataAccessException;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.FacesUtils;

public abstract class AbstractPasswordBean extends BaseBean {

    protected static final Log log = LogFactory.getLog(AbstractPasswordBean.class);

    protected static UserManager manager = UserManager.getDefault();

    protected static AuthenticationManager authMgr = AuthenticationManager.getDefault();

    /** Nueva password */
    protected String password;

    /** Repetición de password */
    protected String passwordRepe;
    /** Password antigua */
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
    public String getPasswordOld()
    {
        return passwordOld;
    }

    /**
     * @param passwordOld The passwordOld to set.
     */
    public void setPasswordOld(String passwordOld)
    {
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
    public String changePassword()
    {
        try
        {
            // Recuperamos el usuario actual
            User user = authMgr.getCurrentPrincipal().getUser();

            // Comprobamos que la password antigua introducida es correcta
            if( authMgr.checkPassword(user,passwordOld) )
            {
                if (this.password.equals(passwordRepe))
                {
                    authMgr.changePassword(user,password);

                    if (!user.isLdapAuthentication()){
                        Date expireDate = calcNextExpireDate();
                        user.setPasswordExpireDate(expireDate);				 // Establecemos la nueva fecha de expiración
                        manager.updateEntity(user, false);
                    }
                    return NavigationResults.CHANGE_PASSWORD_OK;
                }
                else
                {
                    // Avisamos que las password introducidas no son iguales
                    addErrorMessage("error.newPasswordsNotEquals");
                    return null;
                }
            }
            else
            {
                // Avisamos que la password introducida no es correcta
                addErrorMessage("error.invalidPassword");
                return null;
            }
        } catch (LdapDataAccessException ex) {

            String message = ex.getCause().getMessage();
            addErrorMessage("error.ppolicy", message);
            return null;

        } catch (BadCredentialsException ex) {
            addErrorMessage("error.administrator.needed");
            return null;

        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    protected Date calcNextExpireDate() {

        return DateUtils.addDays(new Date(), ConfigurationUtil.getDefault().getDaysToExpirePassword());
    }

    protected void addErrorMessage(String messageKey,Object ... args){
        FacesUtils.addErrorMessage(null, messageKey, args);
    }


    public abstract String returnError(Exception ex);

}