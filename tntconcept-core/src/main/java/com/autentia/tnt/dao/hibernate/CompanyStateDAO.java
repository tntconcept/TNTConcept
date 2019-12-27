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

import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.*;
import com.autentia.tnt.util.SpringUtils;

import java.util.*;
import org.apache.commons.logging.*;

/**
 * DAO for CompanyState objects.
 * @author stajanov code generator
 */
public class CompanyStateDAO extends HibernateManagerBase<CompanyState> 
{
/* companyState - generated by stajanov (do not edit/delete) */



  /** Logger */
  private static final Log log = LogFactory.getLog(CompanyStateDAO.class);

  /**
   * Get default CompanyStateDAO as defined in Spring's configuration file.
   * @return the default singleton CompanyStateDAO
   */
  public static CompanyStateDAO getDefault()
  {
    return (CompanyStateDAO)SpringUtils.getSpringBean("daoCompanyState");
  }

 /** 
   * Constructor
   * @deprecated do not construct DAOs alone: use Spring's declared beans
   */
  public CompanyStateDAO(){
    super(false);
  }

  /** 
   * Retrieve a CompanyState object from database given its id
   * @param id primary key of CompanyState object
   * @return the CompanyState object identified by the id
   * @throws DataAccException on error
   */
  public CompanyState loadById(int id ) throws DataAccException {
    return super.loadByPk(CompanyState.class,id);
  }

    public CompanyState getById(int id ) throws DataAccException {
        return super.getByPk(CompanyState.class,id);
    }

  /** 
   * Get all CompanyState objects from database sorted by the given criteria
   * @param crit the sorting criteria
   * @return a list with all existing CompanyState objects
   * @throws DataAccException on error
   */
  public List<CompanyState> search( SortCriteria crit ) throws DataAccException {
    return super.list(CompanyState.class,crit);
  }

  /** 
   * Get specified CompanyState objects from database sorted by the given criteria
   * @param search search criteria
   * @param sort the sorting criteria
   * @return a list with CompanyState objects matching the search criteria
   * @throws DataAccException on error
   */
  public List<CompanyState> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
    return super.search(CompanyState.class,search,sort);
  }

  /** 
   * Insert a new CompanyState object in database
   * @param dao the CompanyState object to insert
   * @throws DataAccException on error
   */
  public void insert(CompanyState dao) throws DataAccException {
    super.insert(dao);
  }

  /** 
   * Update an existing CompanyState object in database
   * @param dao the CompanyState object to update
   * @throws DataAccException on error
   */
  public void update(CompanyState dao) throws DataAccException {
    super.update(dao,dao.getId());
  }

  /** 
   * Delete an existing CompanyState object in database
   * @param dao the CompanyState object to update
   * @throws DataAccException on error
   */
  public void delete(CompanyState dao) throws DataAccException {
    super.delete(dao,dao.getId());
  }

/* companyState - generated by stajanov (do not edit/delete) */
}
