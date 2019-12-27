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

import com.autentia.tnt.businessobject.WorkingAgreement;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;

public class WorkingAgreementDAO extends HibernateManagerBase<WorkingAgreement>
{

/* WorkingAgreement - generated by stajanov (do not edit/delete) */



  /** Logger */
  private static final Log log = LogFactory.getLog(WorkingAgreementDAO.class);

  /**
   * Get default WorkingAgreementDAO as defined in Spring's configuration file.
   * @return the default singleton WorkingAgreementDAO
   */
  public static WorkingAgreementDAO getDefault()
  {
    return (WorkingAgreementDAO)SpringUtils.getSpringBean("daoWorkingAgreement");
  }

 /** 
   * Constructor
   * @deprecated do not construct DAOs alone: use Spring's declared beans
   */
  public WorkingAgreementDAO(){
    super(false);
  }

  /** 
   * Retrieve a WorkingAgreement object from database given its id
   * @param id primary key of WorkingAgreement object
   * @return the WorkingAgreement object identified by the id
   * @throws DataAccException on error
   */
  public WorkingAgreement loadById(int id ) throws DataAccException {
    return super.loadByPk(WorkingAgreement.class,id);
  }

  public WorkingAgreement getById(int id ) throws DataAccException {
      return super.getByPk(WorkingAgreement.class,id);
  }

  /** 
   * Get all WorkingAgreement objects from database sorted by the given criteria
   * @param crit the sorting criteria
   * @return a list with all existing WorkingAgreement objects
   * @throws DataAccException on error
   */
  public List<WorkingAgreement> search( SortCriteria crit ) throws DataAccException {
    return super.list(WorkingAgreement.class,crit);
  }

  /** 
   * Get specified WorkingAgreement objects from database sorted by the given criteria
   * @param search search criteria
   * @param sort the sorting criteria
   * @return a list with WorkingAgreement objects matching the search criteria
   * @throws DataAccException on error
   */
  public List<WorkingAgreement> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
    return super.search(WorkingAgreement.class,search,sort);
  }

  /** 
   * Insert a new WorkingAgreement object in database
   * @param dao the WorkingAgreement object to insert
   * @throws DataAccException on error
   */
  public void insert(WorkingAgreement dao) throws DataAccException {
    super.insert(dao);
  }

  /** 
   * Update an existing WorkingAgreement object in database
   * @param dao the WorkingAgreement object to update
   * @throws DataAccException on error
   */
  public void update(WorkingAgreement dao) throws DataAccException {
    super.update(dao,dao.getId());
  }

  /** 
   * Delete an existing WorkingAgreement object in database
   * @param dao the WorkingAgreement object to update
   * @throws DataAccException on error
   */
  public void delete(WorkingAgreement dao) throws DataAccException {
    super.delete(dao,dao.getId());
  }

/* WorkingAgreement - generated by stajanov (do not edit/delete) */
}
