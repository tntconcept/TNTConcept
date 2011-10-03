/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * ChangePasswordBean.java
 */

package com.autentia.tnt.bean;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.util.FacesUtils;

/**
 * Bean que se ocupa de la funcionalidad de cambio de password
 *
 * @author <a href="www.autentia.com">AUTENTIA</a>
 *
 */
public class ChangePasswordBean extends BaseBean
{
  /** */
  private static final long serialVersionUID = 4873802999327338762L;
  
  private static final AuthenticationManager authHandler = AuthenticationManager.getDefault();
  
  private static UserManager manager = UserManager.getDefault();
  
  private static AuthenticationManager authMgr = AuthenticationManager.getDefault();
  
  /** Password antigua */
  private String passwordOld;
  
  /** Nueva password */
  private String password;
  
  /** Repetici�n de password */
  private String passwordRepe;
  
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
      if( authHandler.checkPassword(user,passwordOld) )
      {
        if (this.password.equals(passwordRepe))
        {
          authHandler.changePassword(user,password);
          manager.updateEntity(user, false);
          return NavigationResults.CHANGE_PASSWORD_OK;
        }
        else
        {
          // Avisamos que las password introducidas no son iguales
          FacesUtils.addErrorMessage(null,"error.newPasswordsNotEquals");
          return null;
        }
      }
      else
      {
        // Avisamos que la password introducida no es correcta
        FacesUtils.addErrorMessage(null,"error.invalidPassword");
        return null;
      }
    }
    catch(Throwable ex)
    {
      return super.getReturnError("ChangePasswordBean.changePassword. ",ex);
    }
  }
  
  /**
   * @return Returns the password.
   */
  public String getPassword()
  {
    return password;
  }
  /**
   * @param password The password to set.
   */
  public void setPassword(String password)
  {
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
  public String getPasswordRepe()
  {
    return passwordRepe;
  }
  /**
   * @param passwordRepe The passwordRepe to set.
   */
  public void setPasswordRepe(String passwordRepe)
  {
    this.passwordRepe = passwordRepe;
  }
}
