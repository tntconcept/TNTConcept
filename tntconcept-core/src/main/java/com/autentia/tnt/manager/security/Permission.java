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

package com.autentia.tnt.manager.security;

import com.autentia.tnt.dao.ITransferObject;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;

/**
 * Permissions known to the application. These are not the only existing 
 * permissions, but the ones explicitly tested by the application code. 
 * For example: the MenuBean has to test if "Action_ChangePassword" permission
 * is given to current user to know if it has to render the link to the "Change
 * password page". On the other hand, a permission can be created to wrap the
 * method of a manager and the application code can be totally ignorant of its
 * existence, as it is checked by ACEGI using AOP.
 * @author Ivan Zaera Avellon
 */
public class Permission
{
	/** Role assigned to all authenticated users (it is used in managers' methods that can be called by anybody) **/
  public static final GrantedAuthority Authenticated = new GrantedAuthorityImpl("ROLE_Authenticated");
	
  /** Change users' (including own) passwords (secures AuthenticationManager.changePassword() method) */
  public static final GrantedAuthority Action_ChangePassword = new GrantedAuthorityImpl("ROLE_Action_ChangePassword");
  /** Reset users' passwords (secures AuthenticationManager.resetPassword() method) */
  public static final GrantedAuthority Action_ResetPassword = new GrantedAuthorityImpl("ROLE_Action_ResetPassword");
  /** Change users' roles (secures role form field in editUser.jsp) */
  public static final GrantedAuthority Action_ChangeRole = new GrantedAuthorityImpl("ROLE_Action_ChangeRole");
  /** Change users' categories (secures category form field in editUser.jsp) */
  public static final GrantedAuthority Action_ChangeCategory = new GrantedAuthorityImpl("ROLE_Action_ChangeCategory");
  /** List quality documents */
  public static final GrantedAuthority Action_ListQualityDocuments = new GrantedAuthorityImpl("ROLE_Action_ListQualityDocuments");
  /** Execute general reports */
  public static final GrantedAuthority Action_GeneralReports = new GrantedAuthorityImpl("ROLE_Action_GeneralReports");
  /** Execute bitacore reports */
  public static final GrantedAuthority Action_BitacoreReports = new GrantedAuthorityImpl("ROLE_Action_BitacoreReports");
  /** Execute bill reports */
  public static final GrantedAuthority Action_BillReports = new GrantedAuthorityImpl("ROLE_Action_BillReports");
  /** Execute project reports */
  public static final GrantedAuthority Action_ProjectReports = new GrantedAuthorityImpl("ROLE_Action_ProjectReports");
  /** Execute interaction reports */
  public static final GrantedAuthority Action_InteractionReports = new GrantedAuthorityImpl("ROLE_Action_InteractionReports");
  /** Execute organization reports */
  public static final GrantedAuthority Action_OrganizationReports = new GrantedAuthorityImpl("ROLE_Action_OrganizationReports");
  /** Execute personal reports */
  public static final GrantedAuthority Action_PersonalReports = new GrantedAuthorityImpl("ROLE_Action_PersonalReports");
  /** Execute commissioning reports */
  public static final GrantedAuthority Action_CommissioningReports = new GrantedAuthorityImpl("ROLE_Action_CommissioningReports");
  /** Execute offer reports */
  public static final GrantedAuthority Action_OfferReports = new GrantedAuthorityImpl("ROLE_Action_OfferReports");
  /** Execute own reports */
  public static final GrantedAuthority Action_OwnReports = new GrantedAuthorityImpl("ROLE_Action_OwnReports");
  
  /** Enter application console */
  public static final GrantedAuthority Action_Console = new GrantedAuthorityImpl("ROLE_Action_Console");
  /** Execute NOF option */
  public static final GrantedAuthority Action_NOF = new GrantedAuthorityImpl("ROLE_Action_NOF");
  
  /** Execute GlobalHourReport option */
  public static final GrantedAuthority Action_GlobalHoursReport = new GrantedAuthorityImpl("ROLE_Action_GlobalHoursReport");
  
  /** Execute UserHolidaysState option */
  public static final GrantedAuthority Action_UserHolidaysState = new GrantedAuthorityImpl("ROLE_Action_UserHolidaysState");
  
  
  /** Execute MyHolidays option */
  public static final GrantedAuthority Action_MyHolidays = new GrantedAuthorityImpl("ROLE_Action_MyHolidays");
  
  /**
   * List specific entity. This is only the generic role to allow calling
	 * get methods (whether list or get) on an entity: the result may be 
	 * post-filtered by an ACL provider.
   * @param entity entity to list
   * @return ROLE_Entity_List_<entity class name>
   */
  public static GrantedAuthority Entity_List( Class<? extends ITransferObject> entity )
  {
    return new GrantedAuthorityImpl("ROLE_Entity_List_"+entity.getSimpleName());
  }

  /**
   * Create objects of an specific entity.
   * @param entity entity objects to create
   * @return ROLE_Entity_Create_<entity class name>
   */
  public static GrantedAuthority Entity_Create( Class<? extends ITransferObject> entity )
  {
    return new GrantedAuthorityImpl("ROLE_Entity_Create_"+entity.getSimpleName());
  }
  
  
  /**
   * List specific entity. This is only the generic role to allow calling
	 * get methods (whether list or get) on an entity: the result may be 
	 * post-filtered by an ACL provider.
   * @param entity entity to list
   * @return ROLE_Entity_List_<entity class name>
   */
  public static GrantedAuthority Entity_Menu( Class<? extends ITransferObject> entity )
  {
    return new GrantedAuthorityImpl("ROLE_Entity_Menu_"+entity.getSimpleName());
  }
  
}
