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
 * ProjectReportBean.java
 */

package com.autentia.tnt.bean.reports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.ProjectDAO;
import com.autentia.tnt.manager.report.ReportManager;

public class ProjectReportBean extends ReportBean {
	
	private static final Log log = LogFactory.getLog(ProjectReportBean.class);
	
	@Override
	protected void init(){
		
		setMapDataPanels(Arrays.asList
			(
				USERS_ARGUMENTS,
				ORGANIZATIONS_ARGUMENTS,
				PROJECTS_ARGUMENTS
			)
		);
	}
	
	@Override
	public ArrayList<SelectItem> getProjects() {
		ProjectDAO projectDAO = new ProjectDAO();
		ArrayList<SelectItem> reto = new ArrayList<SelectItem>();
						
		List<Project> refs = projectDAO.search(new SortCriteria("name"));
		for (Project ref : refs) {
			reto.add(new SelectItem(ref.getId().toString(), ref.getName()));
		}
		return reto;
	}
	
	@Override 
	protected void setListReports(){
		listReports = ReportManager.getReportManager().getReportListProject();
	}
	

}
