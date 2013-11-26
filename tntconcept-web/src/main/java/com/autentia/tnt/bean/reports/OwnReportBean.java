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

/*
 * OwnReportBean.java
 */

package com.autentia.tnt.bean.reports;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.manager.report.ReportManager;

public class OwnReportBean extends ReportBean {
	
	private static final Log log = LogFactory.getLog(OwnReportBean.class);
	
	@Override
	protected void init(){
		
		setMapDataPanels(Arrays.asList
			(
				USERS_ARGUMENTS,
				PROJECTS_ARGUMENTS,
				ORGANIZATIONS_ARGUMENTS
			)
		);
	}
	
	@Override protected void setListReports(){
		listReports = ReportManager.getReportManager().getReportListOwn();
	}
	

}
