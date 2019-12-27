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
 * DAO for Setting objects.
 * 
 * @author stajanov code generator
 */
public class TagDAO extends HibernateManagerBase<Tag> {

/* Tag - generated by stajanov (do not edit/delete) */



  /** Logger */
  private static final Log log = LogFactory.getLog(TagDAO.class);

  /**
   * Get default TagDAO as defined in Spring's configuration file.
   * @return the default singleton TagDAO
   */
  public static TagDAO getDefault()
  {
    return (TagDAO)SpringUtils.getSpringBean("daoTag");
  }

 /** 
   * Constructor
   * @deprecated do not construct DAOs alone: use Spring's declared beans
   */
  public TagDAO(){
    super(false);
  }

  /** 
   * Retrieve a Tag object from database given its id
   * @param id primary key of Tag object
   * @return the Tag object identified by the id
   * @throws DataAccException on error
   */
  public Tag loadById(int id ) throws DataAccException {
    return super.getByPk(Tag.class,id);
  }

    public Tag getById(int id) throws DataAccException {
        return super.loadByPk(Tag.class,id);
    }

    /**
   * Get all Tag objects from database sorted by the given criteria
   * @param crit the sorting criteria
   * @return a list with all existing Tag objects
   * @throws DataAccException on error
   */
  public List<Tag> search( SortCriteria crit ) throws DataAccException {
    return super.list(Tag.class,crit);
  }

  /** 
   * Get specified Tag objects from database sorted by the given criteria
   * @param search search criteria
   * @param sort the sorting criteria
   * @return a list with Tag objects matching the search criteria
   * @throws DataAccException on error
   */
  public List<Tag> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
    return super.search(Tag.class,search,sort);
  }

  /** 
   * Insert a new Tag object in database
   * @param dao the Tag object to insert
   * @throws DataAccException on error
   */
  public void insert(Tag dao) throws DataAccException {
    super.insert(dao);
  }

  /** 
   * Update an existing Tag object in database
   * @param dao the Tag object to update
   * @throws DataAccException on error
   */
  public void update(Tag dao) throws DataAccException {
    super.update(dao,dao.getId());
  }

  /** 
   * Delete an existing Tag object in database
   * @param dao the Tag object to update
   * @throws DataAccException on error
   */
  public void delete(Tag dao) throws DataAccException {
    super.delete(dao,dao.getId());
  }

/* Tag - generated by stajanov (do not edit/delete) */
}
