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

package com.autentia.tnt.bean.single;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.manager.data.MigrationManager;
import com.autentia.tnt.manager.data.exception.DataException;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.exception.SecException;
import com.autentia.tnt.util.ApplicationLock;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.version.Version;

import javax.faces.application.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ivan
 */
public class ConsoleBean extends BaseBean
{
  private static final Log log = LogFactory.getLog(ConsoleBean.class);

  public String getLockReason()
  {
    return ApplicationLock.getReason();
  }
  
  public String migrateDB()
  {
    MigrationManager mig = MigrationManager.getDefault();
    
    try
    {
      // Migrate database 
      Version oldVersion = mig.upgradeDatabase();
      
      // Tell user
      FacesUtils.addInfoMessage( null, "msg.migrationSuccess", oldVersion, Version.getApplicationVersion() );
      
      // Refresh lock filter state
      ApplicationLock.refresh();
    } 
    catch (DataException e)
    {
      FacesUtils.addErrorMessage( null, "error.migration" );
      log.error("migrateDB - exception",e);
    }
    
    return null;
  }
  
}
