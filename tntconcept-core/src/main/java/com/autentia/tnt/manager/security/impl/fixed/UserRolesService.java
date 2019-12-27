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

package com.autentia.tnt.manager.security.impl.fixed;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.manager.security.IUserRolesService;
import com.autentia.tnt.manager.security.exception.SecException;
import com.autentia.tnt.util.ConfigurationUtil;

import org.acegisecurity.GrantedAuthority;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class retrieves user roles based on their role id. The set of roles is 
 * fixed for each role but in the future it could be read from the database or
 * other places.
 * @author Ivan Zaera Avellon
 */
public class UserRolesService implements IUserRolesService
{
  private static final Log log = LogFactory.getLog( UserRolesService.class );
  
	private ISecurityConfiguration secCfg;

  public UserRolesService( ISecurityConfiguration secCfg )
  {
		this.secCfg = secCfg;
  }
  
  public GrantedAuthority[] getAuthorities(User user) throws SecException
  {
		int roleId = user.getRole().getId();
		GrantedAuthority[] ret = secCfg.getRolesMatrix().get(roleId);
		if( ret==null )
		{
			throw new UnsupportedOperationException("Role id "+roleId+" not knwon to UserRolesService");
		}
		return ret;
  }
}
