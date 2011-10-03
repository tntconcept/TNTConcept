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

import com.autentia.tnt.dao.ITransferObject;

/**
 *
 * @author Ivan Zaera Avellon
 */
class AclMatrixKey
{
  private Class<? extends ITransferObject> type;
  private int roleId;
  
  public AclMatrixKey( Class<? extends ITransferObject> type, int roleId )
  {
    this.type = type;
    this.roleId = roleId;
  }
  
  public Class<? extends ITransferObject> getType()
  {
    return type;
  }
  
  public int getRoleId()
  {
    return roleId;
  }

	public boolean equals(Object obj)
	{
		if( obj instanceof AclMatrixKey )
		{
			AclMatrixKey that = (AclMatrixKey)obj;
			return this.roleId==that.roleId && this.type.equals(that.type);
		}
		else
		{
			return false;
		}
	}

	public int hashCode()
	{
		return type.hashCode()+roleId;
	}

	public String toString()
	{
		return "AclMatrixKey("+type.getSimpleName()+","+roleId+")";
	}
}
