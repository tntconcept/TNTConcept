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

/**
 * 
 */
package com.autentia.tnt.bean.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.bean.contacts.InteractionBean;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.businessobject.ProjectRole;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.OrganizationDAO;
import com.autentia.tnt.dao.hibernate.ProjectDAO;
import com.autentia.tnt.dao.hibernate.ProjectRoleDAO;
import com.autentia.tnt.dao.hibernate.RoleDAO;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.dao.search.BillSearch;
import com.autentia.tnt.dao.search.OrganizationSearch;
import com.autentia.tnt.dao.search.ProjectRoleSearch;
import com.autentia.tnt.manager.admin.ProjectManager;
import com.autentia.tnt.manager.report.ReportManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.DateUtils;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.ReportUtil;
import com.autentia.tnt.xml.ParameterReport;



/**
 * @author cris
 * 
 */

public class ActivityReportBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(ActivityReportBean.class);

	private ArrayList<SelectItem> reports;

	private ArrayList<ReportParameterDefinition> reportParametersDefinitions;

	private static final OrganizationDAO organizationDAO = new OrganizationDAO();
	
	private static final ProjectDAO projectDAO = new ProjectDAO();

	private static final UserDAO userDAO = new UserDAO();
	
	private static final ProjectRoleDAO projectRoleDAO = new ProjectRoleDAO();

	private BillSearch search = new BillSearch();

	private String selectedReport = null;

	private String format;
	
	private Organization org;

	private StringBuffer parameters;
	
	private StringBuffer selectMany;
	
	/** Selected organization * */
	private String selectedOrganization	= Integer.toString(ConfigurationUtil.getDefault().getIdOurCompany());
	
	private String selectedProject;

	private boolean launch = false;

	public void run() {
		parameters = new StringBuffer();		
		selectMany = new StringBuffer();		
		ReportUtil.createRunParameters(reportParametersDefinitions, parameters, selectMany);		
		setLaunch(true);
		return;
	}

	public boolean isLaunch() {
		return launch;
	}

	public void setLaunch(boolean launch) {
		this.launch = launch;
	}
	
	public boolean getLaunch() {
		return launch;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}
	
	public void selectedReportChanged(ValueChangeEvent event) {
		this.setSelectedReport((String) event.getNewValue());
		FacesUtils.renderResponse();
	}
	public String getSelectedReport() {
		return selectedReport;
	}

	public void setSelectedReport(String selectedReport) {
		parameters = new StringBuffer();
		parameters.append("?");
		
		
		if (selectedReport.equals(this.selectedReport)) {
			return;
		}
		
		
		launch = false;
		this.selectedReport = selectedReport;
		reportParametersDefinitions = new ArrayList<ReportParameterDefinition>();
		
		int numReports = 0;
		numReports = ReportManager.getReportManager().getReportListActivity().size();
		for(int i=0;i<numReports;i++) {
			List reportData = new ArrayList<List>();
			String reportName = "";
			reportData = ReportManager.getReportManager().getReportListActivity().get(i);
			reportName = reportData.get(0).toString();
			if(reportName.equals(selectedReport)) {
				List<ParameterReport> parameters = new ArrayList<ParameterReport>();
				parameters = (List<ParameterReport>) reportData.get(1);				
				ReportUtil.createReportParameterDefinition(parameters, reportParametersDefinitions,getUsers(),getOrgs(),getProjects(),null);				
				reportParametersDefinitions.add(new ReportParameterDefinition("idOrg","hidden","idOrg",ConfigurationUtil.getDefault().getIdOurCompany()));
			}
		}
		this.selectedReport = selectedReport.replace(' ','.');
		selectedReport = this.selectedReport;
		
	}
	public ArrayList<SelectItem> getReports() {
		setLaunch(false);
		if (reports == null) {
			ArrayList<SelectItem> m = new ArrayList<SelectItem>();
			m.add(new SelectItem("", "- Elegir informe -"));
			int numReports = 0;
			numReports = ReportManager.getReportManager().getReportListActivity().size();
			for(int i=0;i<numReports;i++) {
				List reportData = new ArrayList();
				String name = "";
				String nameFile = "";
				reportData = ReportManager.getReportManager().getReportListActivity().get(i);
				nameFile = reportData.get(0).toString();
				name = nameFile.replace('.', ' ');
				m.add(new SelectItem(nameFile,name));
			}
			
			reports = m;
		}

		return reports;
	}
	
		public ArrayList<SelectItem> getFormats() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		ret.add(new SelectItem("pdf", "PDF"));
		ret.add(new SelectItem("csv", "CSV"));
		ret.add(new SelectItem("html", "HTML"));
		ret.add(new SelectItem("rtf", "RTF"));
		ret.add(new SelectItem("xls", "XLS"));
		ret.add(new SelectItem("odt", "ODT"));
		
		return ret;
	}

	public ArrayList<SelectItem> getOrgs() {
		ArrayList<SelectItem> reto = new ArrayList<SelectItem>();
		List<Organization> refs = organizationDAO.search(search, new SortCriteria("name"));
		for (Organization ref : refs) {
			reto.add(new SelectItem(ref.getId().toString(), ref.getName()));
		}
		return reto;
	}
	
	public ArrayList<SelectItem> getProjects() {
		ArrayList<SelectItem> reto = new ArrayList<SelectItem>();
						
		List<Project> refs = projectDAO.searchByOrganization(new SortCriteria("name"), organizationDAO.getById(Integer.parseInt(selectedOrganization)));
		for (Project ref : refs) {
			reto.add(new SelectItem(ref.getId().toString(), ref.getName()));
		}
		return reto;
	}
	
	public ArrayList<SelectItem> getRoles(){
		ArrayList<SelectItem> reto = new ArrayList<SelectItem>();
		ProjectRoleSearch prCriteria = new ProjectRoleSearch();
		Project project = projectDAO.getById(Integer.parseInt(selectedProject));
		if(project == null)
			return reto;
		prCriteria.setProject(project);
		List<ProjectRole> projectRoles = projectRoleDAO.search(prCriteria, new SortCriteria("name"));
		for (ProjectRole pRole : projectRoles) {
			reto.add(new SelectItem(pRole.getId().toString(), pRole.getName()));
		}
		return reto;
	}

	public ArrayList<SelectItem> getUsers() {
		ArrayList<SelectItem> reto = new ArrayList<SelectItem>();
		List<User> refs = userDAO.search(search, new SortCriteria("name"));
		for (User ref : refs) {
			reto.add(new SelectItem(ref.getId().toString(), ref.getName()));
		}
		return reto;
	}

	public ArrayList<ReportParameterDefinition> getReportParametersDefinitions() {
		return reportParametersDefinitions;

	}

	public StringBuffer getParameters() {
		return parameters;
	}
	
	public StringBuffer getSelectMany() {
		return selectMany;
	}

	public void setParameters(StringBuffer parameters) {
		if (parameters.equals("?")) {
			this.parameters = parameters;
		} else
			return;

	}
	
	public String getSelectedProject() {
		return selectedProject;
	}
	
	public void setSelectedProject(String selectedProject) {
		this.selectedProject = selectedProject;
	}

	public String getSelectedOrganization() {
		return selectedOrganization;
	}

	public void setSelectedOrganization(String selectedOrganization) {
		this.selectedOrganization = selectedOrganization;
	}
	
	public void selectedOrganizationChanged(ValueChangeEvent event) {
		
		setSelectedOrganization((String) event.getNewValue());
		
		for(ReportParameterDefinition param:reportParametersDefinitions) {
			if(param.isSelectOneSelectManyType()) {
				param.setItems2(getProjects());
			}			
		}
		
		FacesUtils.renderResponse();
	}
	
	public void selectedProjectChanged(ValueChangeEvent event) {
		
		setSelectedProject((String) event.getNewValue());
		
		for (ReportParameterDefinition param : reportParametersDefinitions) {
			if(param.getId().equals("ROL")) {
				param.setItems(getRoles());
			}
			if(param.getId().equalsIgnoreCase("Proyecto")) {
				param.setValue(selectedProject);
			}
		}
			
		FacesUtils.renderResponse();
	}
	
}
