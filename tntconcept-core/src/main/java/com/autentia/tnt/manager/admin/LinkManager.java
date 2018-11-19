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

package com.autentia.tnt.manager.admin;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Link;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.LinkDAO;
import com.autentia.tnt.dao.search.LinkSearch;
import com.autentia.tnt.manager.admin.LinkManager;
import com.autentia.tnt.util.SpringUtils;

public class LinkManager {
	
	/** Logger */
	  private static final Log log = LogFactory.getLog(LinkManager.class);

	  /** Link DAO **/
	  private LinkDAO linkDAO;

	  /**
	   * Get default LinkManager as defined in Spring's configuration file.
	   * @return the default singleton LinkManager
	   */
	  public static LinkManager getDefault()
	  {
	    return (LinkManager)SpringUtils.getSpringBean("linkManager");
	  }
	  
	  /** 
	   * Default constructor 
	   * @deprecated do not construct managers alone: use Spring's declared beans
	   */
	  public LinkManager(LinkDAO linkDAO )
	  {
	    this.linkDAO = linkDAO;
	  }

	  /**
	   * List links. 
	   * @param search search filter to apply
	   * @param sort sorting criteria
	   * @return the list of all links sorted by requested criterion
	   */
	  public List<Link> getAllEntities(LinkSearch search, SortCriteria sort){
	    return linkDAO.search( search, sort );
	  }
	  
	  /**
	   * Get link by primary key.
	   * @return link selected by id.
	   */
	  public Link getEntityById(int id){
	    return linkDAO.getById(id);	    
	  }
		
	  /**
	   * Insert link. 
	   */
	  public void insertEntity(Link link) {
	    linkDAO.insert(link);
	  }
		
	  /**
	   * Insert link. 
	   */
	  public void insertEntityWithoutUser(Link link) {
	    linkDAO.insertWithoutUser(link);
	  }
		 
	  /**
	   * Update link. 
	   */
	  public void updateEntity(Link link) {
	    linkDAO.update(link);
	  }

	  /**
	   * Delete link. 
	   */
	  public void deleteEntity(Link link) {
	    linkDAO.delete(link);
	  }


}
