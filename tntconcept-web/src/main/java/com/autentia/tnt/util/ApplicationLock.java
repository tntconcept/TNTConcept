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

package com.autentia.tnt.util;

import com.autentia.tnt.version.Version;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to represent application state
 * 
 * @author Ivan Zaera Avellon
 */
public class ApplicationLock {
	private static final Log log = LogFactory.getLog(ApplicationLock.class);

	private static boolean locked = true;
	private static String reason = "msg.appLockNotInit";

	/** No instances of this class can be created */
	private ApplicationLock() {
	}

	public static boolean isLocked() {
		return locked;
	}

	public static String getReason() {
		return reason;
	}

	public static void refresh() {
		try {
			Version db = Version.getDatabaseVersion();
			Version code = Version.getApplicationVersion();

			int cmp = db.compareTo(code, Version.MINOR);
			if (cmp == 0) {
				locked = false;
				reason = "msg.notLocked";
				log
						.info("refresh - database version correct: application unlocked");
			} else if (cmp < 0) {
				locked = true;
				reason = "msg.dbNeedsUpdate";
				log
						.info("refresh - database version not correct: database needs update");
			} else {
				locked = true;
				reason = "msg.appNeedsUpdate";
				log
						.info("refresh - database version not correct: application code needs update");
			}
		} catch (Exception e) {
			log
					.fatal(
							"refresh - exception initializing ApplicationLock: application will not be available",
							e);
			locked = true;
			reason = e.getMessage() + " (" + e.getClass().getSimpleName() + ")";
		}
	}
}
