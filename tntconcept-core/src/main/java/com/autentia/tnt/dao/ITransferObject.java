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

package com.autentia.tnt.dao;

import java.util.Date;
import java.util.List;

/**
 * Interface implemented by all DAO transfer objects.
 * @author ivan
 */
public interface ITransferObject {
  
  /**
   * Get transfer object's id
   * @return transfer object's id
   */
  public Integer getId();
  
  /** 
   * Get transfer object's owner
   */
  public Integer getOwnerId();
  
  /** 
   * Get transfer object's department
   */
  public Integer getDepartmentId();
  
  /** 
   * set transfer object's owner
   */
  public void setOwnerId(Integer ownerId);
  
  /** 
   * set transfer object's department
   */
  public void setDepartmentId(Integer departmentId);
  
  
  /**
   * Get transfer object's Creation Date
   * @return transfer object's Creation Date
   */
  public Date getInsertDate();
  
  /** 
   * Get transfer object's Modification Date
   */
  public Date getUpdateDate();
  
  /** 
   * set transfer object's Creation
   */
  public void setInsertDate(Date insertDate);
  
  /** 
   * set transfer object's Modification
   */
  public void setUpdateDate(Date updateDate);

  
  public List<Integer> getOwnersId();
  
}
