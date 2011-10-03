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

import com.autentia.tnt.businessobject.ActivityFile;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.ActivityFileDAO;
import com.autentia.tnt.dao.search.ActivityFileSearch;
import com.autentia.tnt.util.SpringUtils;

/* ActivityFile - generated by stajanov (do not edit/delete) */


public class ActivityFileManager {


  /** Logger */
  private static final Log log = LogFactory.getLog(ActivityFileManager.class);

  /** ActivityFile DAO **/
  private ActivityFileDAO activityFileDAO;

  /**
   * Get default ActivityFileManager as defined in Spring's configuration file.
   * @return the default singleton ActivityFileManager
   */
  public static ActivityFileManager getDefault()
  {
    return (ActivityFileManager)SpringUtils.getSpringBean("managerActivityFile");
  }

  /** 
   * Empty constructor needed by CGLIB (Spring AOP)
   */
  protected ActivityFileManager()
  {
  }
	
  /** 
   * Default constructor 
   * @deprecated do not construct managers alone: use Spring's declared beans
   */
  public ActivityFileManager( ActivityFileDAO activityFileDAO )
  {
    this.activityFileDAO = activityFileDAO;
  }

  /**
   * List activityFiles. 
   * @param search search filter to apply
   * @param sort sorting criteria
   * @return the list of all activityFiles sorted by requested criterion
   */
  public List<ActivityFile> getAllEntities(ActivityFileSearch search, SortCriteria sort){
    return activityFileDAO.search( search, sort );
  }
  
  /**
   * Get activityFile by primary key.
   * @return activityFile selected by id.
   */
  public ActivityFile getEntityById(int id){
    return activityFileDAO.getById(id);	    
  }
	
  /**
   * Insert activityFile. 
   */
  public void insertEntity(ActivityFile activityFile) {
    activityFileDAO.insert(activityFile);
  }
	 
  /**
   * Update activityFile. 
   */
  public void updateEntity(ActivityFile activityFile) {
    activityFileDAO.update(activityFile);
  }

  /**
   * Delete activityFile. 
   */
  public void deleteEntity(ActivityFile activityFile) {
    activityFileDAO.delete(activityFile);
  }

/* ActivityFile - generated by stajanov (do not edit/delete) */
}