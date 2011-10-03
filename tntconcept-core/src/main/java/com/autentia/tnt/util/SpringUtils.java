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

package com.autentia.tnt.util;

import com.autentia.tnt.dao.ITransferObject;
import com.autentia.tnt.manager.security.Principal;

import java.util.Map;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.acls.Acl;
import org.acegisecurity.acls.AclService;
import org.acegisecurity.acls.NotFoundException;
import org.acegisecurity.acls.Permission;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.acls.objectidentity.ObjectIdentityRetrievalStrategy;
import org.acegisecurity.acls.objectidentity.ObjectIdentityRetrievalStrategyImpl;
import org.acegisecurity.acls.sid.Sid;
import org.acegisecurity.acls.sid.SidRetrievalStrategy;
import org.acegisecurity.acls.sid.SidRetrievalStrategyImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.context.ApplicationContext;

/**
 * Utility Spring class
 * @author ivan
 */
public class SpringUtils
{
	private static ApplicationContext appCtx;
	private static AclService aclService;
	private static ObjectIdentityRetrievalStrategy objectIdentityRetrievalStrategy;
	private static SidRetrievalStrategy sidRetrievalStrategy;
	
	/**
	 * Configure this class
	 * @param appCtx
	 */
	public synchronized static void configure( ApplicationContext ctx )
	{
		// Do not let configure more than once
		if( appCtx!=null )
		{
			throw new IllegalStateException("Spring's application context cannot be set more than once");
		}
		
		// Store application context
		appCtx = ctx;
		
		// Find AclService
		Map map = appCtx.getBeansOfType(AclService.class);
		if (map.size() != 1)
		{
			throw new IllegalStateException(
					"Found incorrect number of AclService instances in application context - you must have only have one!"
			);
		}
		aclService = (AclService) map.values().iterator().next();

		// Find SidRetrievalStrategy
		map = appCtx.getBeansOfType(SidRetrievalStrategy.class);
		if (map.size() == 0)
		{
			sidRetrievalStrategy = new SidRetrievalStrategyImpl();
		}
		else if (map.size() == 1)
		{
			sidRetrievalStrategy = (SidRetrievalStrategy) map.values().iterator().next();
		}
		else
		{
			throw new IllegalStateException(
					"Found incorrect number of SidRetrievalStrategy instances in application context - you must have only have one!"
			);
		}
		
		// Find ObjectIdentityRetrievalStrategy
		map = appCtx.getBeansOfType(ObjectIdentityRetrievalStrategy.class);
		if (map.size() == 0)
		{
			objectIdentityRetrievalStrategy = new ObjectIdentityRetrievalStrategyImpl();
		}
		else if (map.size() == 1)
		{
			objectIdentityRetrievalStrategy = (ObjectIdentityRetrievalStrategy) map.values().iterator().next();
		}
		else
		{
			throw new IllegalStateException(
					"Found incorrect number of ObjectIdentityRetrievalStrategy instances in application context - you must have only have one!"
			);
		}
	}
	
	/**
	 * This class cannot have instances
	 */
	private SpringUtils()
	{
	}
	
	/**
	 * Get a bean defined under Spring by its name.
	 * @param name name of bean
	 * @return the bean or null if it does not exist
	 */
	public static Object getSpringBean( String name )
	{
		return appCtx.getBean(name);
	}
	
	/**
	 * Get current principal
	 * @return the current principal as reported by ACEGI
	 */
	public static Principal getPrincipal()
	{
		return (Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	/**
	 * Get current principal, but more generic, as Object
	 * @return the current object as reported by ACEGI
	 */
	public static Object getPrincipalAsObject()
	{
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	/**
	 * Check if an ACL permission is granted for a given domain object
	 * @param dto domain object 
	 * @param perm permission to test
	 * @return true if permission is granted
	 */
	public static boolean isAclPermissionGranted( ITransferObject dto, Permission perm )
	{
		Sid[] sids = sidRetrievalStrategy.getSids(SecurityContextHolder.getContext().getAuthentication());
		ObjectIdentity oid = objectIdentityRetrievalStrategy.getObjectIdentity(dto);
		
		// Obtain aclEntrys applying to the current Authentication object
		try
		{
			Acl acl = aclService.readAclById(oid, sids);
			
			if (acl.isGranted( new Permission[] { perm }, sids, false ) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (NotFoundException nfe)
		{
			return false;
		}
	}

	/**
	 * Test if a given role permission is granted to current user.
	 * @param grantedAuthority the permission to test
	 * @return true if current user holds permission
	 */
	public static boolean isRolePermissionGranted( GrantedAuthority perm ) {
		boolean isGranted = true;
		
		try{
		    Principal principal = getPrincipal();
		    
		    isGranted = principal.hasAuthority( perm );
		} catch (Exception nfe){
			isGranted = false;
		}
		
		return isGranted;
	}
}
