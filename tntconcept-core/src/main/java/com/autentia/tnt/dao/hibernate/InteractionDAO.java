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
 * DAO for Interaction objects.
 * @author stajanov code generator
 */
public class InteractionDAO extends HibernateManagerBase<Interaction> 
{
/* interaction - generated by stajanov (do not edit/delete) */
  /** Logger */
  private static final Log log = LogFactory.getLog(InteractionDAO.class);

  /**
   * Get default InteractionDAO as defined in Spring's configuration file.
   * @return the default singleton InteractionDAO
   */
  public static InteractionDAO getDefault()
  {
    return (InteractionDAO)SpringUtils.getSpringBean("daoInteraction");
  }

 /** 
   * Constructor
   * @deprecated do not construct DAOs alone: use Spring's declared beans
   */
  public InteractionDAO(){
    super(false);
  }

  /** 
   * Retrieve a Interaction object from database given its id
   * @param id primary key of Interaction object
   * @return the Interaction object identified by the id
   * @throws DataAccException on error
   */
  public Interaction getById( int id ) throws DataAccException {
    return super.getByPk(Interaction.class,id);
  }

  /** 
   * Get all Interaction objects from database sorted by the given criteria
   * @param crit the sorting criteria
   * @return a list with all existing Interaction objects
   * @throws DataAccException on error
   */
  public List<Interaction> search( SortCriteria crit ) throws DataAccException {
    return super.list(Interaction.class,crit);
  }

  /** 
   * Get specified Interaction objects from database sorted by the given criteria
   * @param search search criteria
   * @param sort the sorting criteria
   * @return a list with Interaction objects matching the search criteria
   * @throws DataAccException on error
   */
  public List<Interaction> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
    return super.search(Interaction.class,search,sort);
  }

  /** 
   * Insert a new Interaction object in database
   * @param dao the Interaction object to insert
   * @throws DataAccException on error
   */
  public void insert(Interaction dao) throws DataAccException {
    super.insert(dao);
  }

  /** 
   * Update an existing Interaction object in database
   * @param dao the Interaction object to update
   * @throws DataAccException on error
   */
  public void update(Interaction dao) throws DataAccException {
    super.update(dao,dao.getId());
  }

  /** 
   * Delete an existing Interaction object in database
   * @param dao the Interaction object to update
   * @throws DataAccException on error
   */
  public void delete(Interaction dao) throws DataAccException {
    super.delete(dao,dao.getId());
  }
/* interaction - generated by stajanov (do not edit/delete) */
}
