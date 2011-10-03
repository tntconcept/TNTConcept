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

package com.autentia.tnt.dao.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.businessobject.ExternalActivity;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;

public class ExternalActivityDAO extends HibernateManagerBase<ExternalActivity> {

	/** Logger */
	private static final Log log = LogFactory.getLog(ExternalActivityDAO.class);
	
	/**
	 * This Get default ExternalActivityDAO as defined in Spring's configuration file.
 	 * @return the default singleton ExternalActivityDAO
	 */
	public static ExternalActivityDAO getDefault() {
		return (ExternalActivityDAO)SpringUtils.getSpringBean("daoExternalActivity");
	}
	
	/**
	 * deletes an existing ExternalActivity object in the database
	 * @param to the object to delete
	 * @throws DataAccExceptio on error
	 */
	public void delete(ExternalActivity to) throws DataAccException {
		super.delete(new ExternalActivity(),to.getId());
		
	}

	/**
	 * retrieves an ExternalActivity object from database with the especified 
	 * primary key
	 * @param id the primary key of the ExternalActivity
	 * @return the ExternalActivity object with the specified ID
	 * @throws DataAccExceptio on error 
	 */
	public ExternalActivity getById(int id) throws DataAccException {
		
		return super.getByPk(ExternalActivity.class,id);
	}

	/**
	 * retrieves all the ExternalActivity from database sorted by the given criteria
	 * @param crit sorting options
	 * @return a sorted List of externalActivity objects
	 */
	public List<ExternalActivity> search(SortCriteria crit)
			throws DataAccException {
		 return super.list(ExternalActivity.class,crit);
	}

	/**
	 * retrieves specified ExternalActivity objects from database sorted by 
	 * the given criteria
	 * @param search search criteria
	 * @param sort sort criteria
	 * @return a sorted List of externalActivity objects 
	 */
	public List<ExternalActivity> search(SearchCriteria search,
			SortCriteria sort) throws DataAccException {
		return super.search(ExternalActivity.class,search, sort);
	}

	/**
	 * Updates an existing ExternalActivity in database
	 * @param to the ExternalActivity to update
	 */
	public void update(ExternalActivity to) throws DataAccException {
		 super.update(to,to.getId());
		
	}

	/**
	 * inserts a new ExternalActivity in database
	 * @param dao the ExtenalActivity to insert
	 */
	public void insert(ExternalActivity dao) throws DataAccException {
		super.insert(dao);
	}
}
