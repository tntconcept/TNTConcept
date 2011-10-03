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

import java.util.HashMap;
import java.util.Map;

import org.acegisecurity.acls.Acl;
import org.acegisecurity.acls.AclService;
import org.acegisecurity.acls.NotFoundException;
import org.acegisecurity.acls.Permission;
import org.acegisecurity.acls.domain.AclImpl;
import org.acegisecurity.acls.domain.AuditLogger;
import org.acegisecurity.acls.domain.BasePermission;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.acls.sid.PrincipalSid;
import org.acegisecurity.acls.sid.Sid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.dao.IDataAccessObject;
import com.autentia.tnt.dao.ITransferObject;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.SpringUtils;

/**
 * This is the default implementation for AclService. This class creates ACLs
 * for different entity objects based on the object type, object owner,
 * current logged in user role, and the permissions hashed matrices.
 * @author Ivan Zaera Avellon
 */
public class DefaultAclService implements AclService
{
	private static final Log log = LogFactory.getLog( DefaultAclService.class );
	
	/** Default audit logger object */
	private static final AuditLogger DEFAULT_AUDIT_LOGGER = new DefaultAuditLogger();
	
	private boolean ignoreUnownedObjects = false;
	private ISecurityConfiguration secCfg;
	private Map<String,IDataAccessObject> daoMap;
	
	public DefaultAclService( ISecurityConfiguration secCfg, Map<String,IDataAccessObject> daoMap )
	{
		this.secCfg = secCfg;
		this.daoMap = daoMap;
	}

	public boolean isIgnoreUnownedObjects()
	{
		return ignoreUnownedObjects;
	}

	public void setIgnoreUnownedObjects(boolean ignoreUnownedObjects)
	{
		this.ignoreUnownedObjects = ignoreUnownedObjects;
	}
	
	public Acl readAclById( ObjectIdentity id ) throws NotFoundException
	{
		DefaultAclAuthorizationStrategy strategy = new DefaultAclAuthorizationStrategy();
		AclImpl acl = new AclImpl(id,id.getIdentifier(),strategy,DEFAULT_AUDIT_LOGGER);
		
		// Get data about saved object from database and then remove it from Hibernate cache if necessary
//		boolean isObjectLoaded = HibernateUtil.currentSession().contains( 
//			((TransferObjectIdentity)id).getTransferObject() 
//		);
		ITransferObject dto = loadObject(id);
		dto.getOwnerId(); 
		// this is to force fetch of entire object from database so that further getter calls don't fail
//		if( !isObjectLoaded )
//		{
//			HibernateUtil.evictFullObject(dto);
//		}
		
		// Add object levels to ACL
		addAclLevel( secCfg.getWriteMatrix(),  acl, id.getJavaType(), dto, BasePermission.WRITE  );
		addAclLevel( secCfg.getDeleteMatrix(), acl, id.getJavaType(), dto, BasePermission.DELETE );
		addAclLevel( secCfg.getReadMatrix(), acl, id.getJavaType(), dto, BasePermission.READ );
		
		// Lock ACL so that nobody can change it any more
		strategy.freeze();
		
		return acl;
	}

	private void addAclLevel( Map<AclMatrixKey,AclMatrixValue> matrix, AclImpl acl, Class type, ITransferObject dto, Permission perm )
	{
		Principal principal = SpringUtils.getPrincipal();
		Sid sid = new PrincipalSid(principal.getUsername());
		AclMatrixKey key = new AclMatrixKey(type,principal.getRoleId());
		AclMatrixValue level = matrix.get( key );
		
	    if (log.isDebugEnabled()){		
		log.debug("addAclLevel -"+
								" permission=["+perm.getPattern()+"]"+
								" type="+type.getSimpleName()+
								" id="+dto.getId()+
								" ownerId="+dto.getOwnerId()+
								" departmentId="+dto.getDepartmentId()+
								" userId="+principal.getId()+
								" roleId="+principal.getRoleId()+
								" level="+level );
	    }
	    
		if( level==null )
		{
			throw new UnsupportedOperationException("Write permission level for "+key+" not defined");
		}
		
		switch( level )
		{
			case ALL:
				acl.insertAce(null,perm,sid,true);
				break;

			case OWN:
				if( isIgnoreUnownedObjects() && (dto.getOwnerId()==null) )
				{
					acl.insertAce(null,perm,sid,true);
					log.warn("addAclLevel - allowing permission ["+perm.getPattern()+"] on object "+
										type.getSimpleName()+"["+dto.getId()+"] "+
										"because it is not owned by any user and ignoreUnknownedObjects=true in DefaultAclService");
				}
				else
				{
					if( dto.getOwnerId() == principal.getId() )
					{
						acl.insertAce(null,perm,sid,true);
					}
				}
				break;

			case AREA:
				if( isIgnoreUnownedObjects() && (dto.getDepartmentId()==null) )
				{
					acl.insertAce(null,perm,sid,true);
					log.warn("addAclLevel - allowing permission ["+perm.getPattern()+"] on object "+
										type.getSimpleName()+"["+dto.getId()+"] "+
										"because it is not owned by any department and ignoreUnknownedObjects=true in DefaultAclService");
				}
				else
				{
					if( dto.getDepartmentId() == principal.getDepartmentId() )
					{
						acl.insertAce(null,perm,sid,true);
					}
				}
				break;

			case DENY:
				// Do nothing
				break;
			
			case OWNERS:
			
				if (dto.getOwnersId() != null && dto.getOwnersId().contains(principal.getId())) {
					acl.insertAce(null, perm, sid, true);
				}
			break;

			default:
				throw new UnsupportedOperationException("AclMatrixValue("+level+") not supported by write permission in readAclById()");
		}
	}
	
	public Acl readAclById( ObjectIdentity id, Sid[] sids ) throws NotFoundException
	{
		return readAclById(id);
	}
	
	public Map readAclsById( ObjectIdentity[] ids ) throws NotFoundException
	{
		Map ret = new HashMap();
		for( ObjectIdentity id : ids )
		{
			ret.put(id,readAclById(id));
		}
		return ret;
	}
	
	public Map readAclsById( ObjectIdentity[] ids, Sid[] sids ) throws NotFoundException
	{
		return readAclsById(ids);
	}
	
	public ObjectIdentity[] findChildren( ObjectIdentity parentIdentity )
	{
		// Our hierachy of objects is flat, so don't return any parent
		return null;
	}
	
	
	/**
	 * Generically load a DTO from the database given its ObjectIdentity
	 * @param objid object id
	 * @return the DTO as read from Hibernate
	 */
	private ITransferObject loadObject(ObjectIdentity objid)
	{ 
		Class type = objid.getJavaType();
		Integer id = (Integer)objid.getIdentifier();
		IDataAccessObject dao = daoMap.get(type.getName());
		
		if( dao==null )
		{
			throw new IllegalStateException("No DAO for "+objid.getJavaType().getName()+" objects defined. "+
																			"Please fix your ACEGI configuration file.");
		}
		return dao.getById(id);
	}
}
