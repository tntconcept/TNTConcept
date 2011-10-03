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
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.OrganizationDAO;
import com.autentia.tnt.dao.hibernate.ProjectDAO;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.dao.search.BillSearch;
import com.autentia.tnt.dao.search.ProjectSearch;
import com.autentia.tnt.manager.admin.ProjectManager;
import com.autentia.tnt.manager.report.ReportManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.DateUtils;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.ReportUtil;
import com.autentia.tnt.xml.ParameterReport;
import com.autentia.tnt.manager.security.AuthenticationManager;

public class OwnReportBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(OwnReportBean.class);

	private ArrayList<SelectItem> reports;

	private ArrayList<ReportParameterDefinition> reportParametersDefinitions;

	private static final OrganizationDAO organizationDAO = new OrganizationDAO();
	
	private static final ProjectDAO projectDAO = new ProjectDAO();

	private static final UserDAO userDAO = new UserDAO();

	private BillSearch search = new BillSearch();

	private String selectedReport = null;

	private String format;
	
	private Organization org;

	private StringBuffer parameters;
	
	private StringBuffer selectMany;
	
	/** Selected organization * */
	private String selectedOrganization	= Integer.toString(ConfigurationUtil.getDefault().getIdOurCompany());
	

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
		numReports = ReportManager.getReportManager().getReportListOwn().size();
		for(int i=0;i<numReports;i++) {
			List reportData = new ArrayList<List>();
			String reportName = "";
			reportData = ReportManager.getReportManager().getReportListOwn().get(i);
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
			numReports = ReportManager.getReportManager().getReportListOwn().size();
			for(int i=0;i<numReports;i++) {
				List reportData = new ArrayList();
				String name = "";
				String nameFile = "";
				reportData = ReportManager.getReportManager().getReportListOwn().get(i);
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
						
		List<Project> refs = projectDAO.searchOpenByOrganization(new SortCriteria("name"), organizationDAO.getById(Integer.parseInt(selectedOrganization)));
		for (Project ref : refs) {
			reto.add(new SelectItem(ref.getId().toString(), ref.getName()));
		}
		return reto;
	}

	public ArrayList<SelectItem> getUsers() {
		final ArrayList<SelectItem> reto = new ArrayList<SelectItem>();		
		final User currentUser = AuthenticationManager.getDefault().getCurrentPrincipal().getUser();
		reto.add(new SelectItem(currentUser.getId().toString(), currentUser.getName()));
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
	
}
