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

package com.autentia.tnt.tracking.hibernate.listener;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.def.DefaultPostLoadEventListener;

import com.autentia.tnt.dao.ITransferObject;
import com.autentia.tnt.tracking.Tracking;


public class TrackChangesLoadListener extends DefaultPostLoadEventListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7380937175239749072L;
	private static final Log log = LogFactory.getLog(TrackChangesLoadListener.class);

	@Override
	public void onPostLoad(PostLoadEvent event) {
		super.onPostLoad(event);
		try {			
			ITransferObject transferObject = (ITransferObject)event.getEntity();
			if (transferObject instanceof Tracking) {
				Tracking tracking = (Tracking)transferObject;
				tracking.beginChangesTracking();
			}
		} catch (SecurityException e) {
			log.error(e);
		} catch(ClassCastException e){
			//lo ignoramos al no tratarse de una entidad bajo el control de cambios
		}
	}
}
