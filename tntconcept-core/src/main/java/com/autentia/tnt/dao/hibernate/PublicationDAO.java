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
 * DAO for Publication objects.
 * @author stajanov code generator
 */
public class PublicationDAO extends HibernateManagerBase<Publication> 
{
/* publication - generated by stajanov (do not edit/delete) */



  /** Logger */
  private static final Log log = LogFactory.getLog(PublicationDAO.class);

  /**
   * Get default PublicationDAO as defined in Spring's configuration file.
   * @return the default singleton PublicationDAO
   */
  public static PublicationDAO getDefault()
  {
    return (PublicationDAO)SpringUtils.getSpringBean("daoPublication");
  }

 /** 
   * Constructor
   * @deprecated do not construct DAOs alone: use Spring's declared beans
   */
  public PublicationDAO(){
    super(false);
  }

  /** 
   * Retrieve a Publication object from database given its id
   * @param id primary key of Publication object
   * @return the Publication object identified by the id
   * @throws DataAccException on error
   */
  public Publication getById( int id ) throws DataAccException {
    return super.getByPk(Publication.class,id);
  }

  /** 
   * Get all Publication objects from database sorted by the given criteria
   * @param crit the sorting criteria
   * @return a list with all existing Publication objects
   * @throws DataAccException on error
   */
  public List<Publication> search( SortCriteria crit ) throws DataAccException {
    return super.list(Publication.class,crit);
  }

  /** 
   * Get specified Publication objects from database sorted by the given criteria
   * @param search search criteria
   * @param sort the sorting criteria
   * @return a list with Publication objects matching the search criteria
   * @throws DataAccException on error
   */
  public List<Publication> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
    return super.search(Publication.class,search,sort);
  }

  /** 
   * Insert a new Publication object in database
   * @param dao the Publication object to insert
   * @throws DataAccException on error
   */
  public void insert(Publication dao) throws DataAccException {
    super.insert(dao);
  }

  /** 
   * Update an existing Publication object in database
   * @param dao the Publication object to update
   * @throws DataAccException on error
   */
  public void update(Publication dao) throws DataAccException {
    super.update(dao,dao.getId());
  }

  /** 
   * Delete an existing Publication object in database
   * @param dao the Publication object to update
   * @throws DataAccException on error
   */
  public void delete(Publication dao) throws DataAccException {
    super.delete(dao,dao.getId());
  }

/* publication - generated by stajanov (do not edit/delete) */
}
