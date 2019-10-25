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

package com.autentia.tnt.manager.activity;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.ExternalActivity;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.ExternalActivityDAO;
import com.autentia.tnt.dao.search.ExternalActivitySearch;
import com.autentia.tnt.util.SpringUtils;

public class ExternalActivityManager {

	/** Logger */
	private static final Log log = LogFactory.getLog(ExternalActivityManager.class);
	
	private ExternalActivityDAO externalActivityDAO;
	
	/**
	 * returns the Spring Bean with the singleton instance of the manager
	 * @return an instance of ExternalActivityManager
	 */
	public static ExternalActivityManager getDefault() {
		return (ExternalActivityManager) SpringUtils.getSpringBean("managerExternalActivity");
	}
	
	/**
	 * Empty constructor needed by CGLIB (Spring AOP)
	 */
	public ExternalActivityManager() {
		
	}
	
	/**
	 * @deprecated
	 */
	 public ExternalActivityManager(ExternalActivityDAO externalActivityDAO) {
		
		 this.externalActivityDAO = externalActivityDAO;
	 }
	
	/**
	   * List externalActivitys. 
	   * @param search search filter to apply
	   * @param sort sorting criteria
	   * @return the list of all externalActivitys sorted by requested criterion
	   */
	  public List<ExternalActivity> getAllEntities(ExternalActivitySearch search, SortCriteria sort){
	    return externalActivityDAO.search( search, sort );
	  }
	  
	 

	/**
	   * Get externalActivity by primary key.
	   * @return externalActivity selected by id.
	   */
	  public ExternalActivity getEntityById(int id){
	    return externalActivityDAO.loadById(id);
	  }
		
	  /**
	   * Insert externalActivity. 
	   */
	  public void insertEntity(ExternalActivity externalActivity) {
	    externalActivityDAO.insert(externalActivity);
	  }
		 
	  /**
	   * Update externalActivity. 
	   */
	  public void updateEntity(ExternalActivity externalActivity) {
	    externalActivityDAO.update(externalActivity);
	  }

	  /**
	   * Delete externalActivity. 
	   */
	  public void deleteEntity(ExternalActivity externalActivity) {
	    externalActivityDAO.delete(externalActivity);
	  }

}
