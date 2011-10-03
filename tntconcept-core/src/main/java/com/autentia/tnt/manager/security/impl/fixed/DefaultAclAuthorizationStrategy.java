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

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.acls.Acl;
import org.acegisecurity.acls.domain.AclAuthorizationStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is the default ACL authorization strategy which is is used by AclImpl to
 * check who can change ACLs. This implementation's ACLs are generated
 * on-the-fly and not read from any place, so this default implementation allows
 * everybody to change anything in the ACLs until freeze() method is called,
 * which turns the ACL read-only for security reasons.
 * @author Ivan Zaera Avellon
 */
public class DefaultAclAuthorizationStrategy implements AclAuthorizationStrategy
{
  
  private static final Log log = LogFactory.getLog( DefaultAclAuthorizationStrategy.class );
  
  private boolean readOnly = false;
  
  public void securityCheck(Acl acl, int changeType)
  {
    if( readOnly )
    {
      throw new AccessDeniedException("ACLs cannot be changed once they are frozen");
    }
  }
  
  /**
   * Freeze associated ACL (make it read-only). Once this method is called, any
   * attempt to change the ACLs associated to this AuthorizationStrategy will
   * result in an AccessDeniedException
   */
  public void freeze()
  {
    this.readOnly = true;
  }
}
