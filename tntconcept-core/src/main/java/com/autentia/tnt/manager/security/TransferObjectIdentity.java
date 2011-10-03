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

import java.io.Serializable;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;

/**
 *
 * @author Ivan Zaera Avellon
 */
public class TransferObjectIdentity implements ObjectIdentity
{
	private ITransferObject dto;
	private Class type;
	
	public TransferObjectIdentity( ITransferObject dto )
	{
		this.dto = dto;
		
		// Find real ITransferObject class to avoid interferences with Hibernate proxies
		this.type = null;
		Class candidate = dto.getClass();
		while( this.type==null && !candidate.equals(Object.class) )
		{
			Class[] ifaces = candidate.getInterfaces();
			for( Class iface : ifaces )
			{
				if( iface.equals(ITransferObject.class) )
				{
					this.type = candidate;
				}
			}
			candidate = candidate.getSuperclass();
		}
	}

	public Serializable getIdentifier()
	{
		return dto.getId();
	}

	public Class getJavaType()
	{
		return type;
	}
	
	public ITransferObject getTransferObject()
	{
		return dto;
	}
}
