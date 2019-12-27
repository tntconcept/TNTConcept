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

import org.acegisecurity.acls.AccessControlEntry;
import org.acegisecurity.acls.domain.AuditLogger;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class implements TNTConcept's default audit logger. Current
 * implementation just dumps auditing to LOG4J console.
 * added using this class.
 * @author Ivan Zaera Avellon
 */
public class DefaultAuditLogger implements AuditLogger
{
  private static final Log log = LogFactory.getLog( DefaultAuditLogger.class );
  
  public void logIfNeeded(boolean granted, AccessControlEntry ace)
  {
    ObjectIdentity id = ace.getAcl().getObjectIdentity();
    
    if (log.isDebugEnabled()){
    	log.debug( (granted ? "Grant [" : "Deny [")+ace.getPermission().getPattern()+"]"+
	      " on "+id.getJavaType().getSimpleName()+"["+id.getIdentifier()+"]"+
	      " to "+ace.getSid());
    }
  }
}
