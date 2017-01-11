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
 * ReportBean.java
 */

package com.autentia.tnt.bean.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.springframework.util.CollectionUtils;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.businessobject.Account;
import com.autentia.tnt.businessobject.Inventary;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.businessobject.ProjectRole;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.AccountDAO;
import com.autentia.tnt.dao.hibernate.InventaryDAO;
import com.autentia.tnt.dao.hibernate.OrganizationDAO;
import com.autentia.tnt.dao.hibernate.ProjectDAO;
import com.autentia.tnt.dao.hibernate.ProjectRoleDAO;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.dao.search.ProjectRoleSearch;
import com.autentia.tnt.dao.search.ProjectSearch;
import com.autentia.tnt.manager.admin.ProjectManager;
import com.autentia.tnt.manager.contacts.OrganizationManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.ReportUtil;
import com.autentia.tnt.xml.ParameterReport;

public abstract class ReportBean extends BaseBean {

	private ArrayList<SelectItem> reports;

	private ArrayList<ReportParameterDefinition> reportParametersDefinitions;
	
	private Map<String, List<SelectItem>> dataPanels = null; 
	
	private static final OrganizationDAO organizationDAO = new OrganizationDAO();
	private static final ProjectDAO projectDAO = new ProjectDAO();
	private static final UserDAO userDAO = new UserDAO();
	private static final ProjectRoleDAO projectRoleDAO = new ProjectRoleDAO();
	private static final AccountDAO accountDAO = new AccountDAO();
	private static final InventaryDAO inventoryDAO = new InventaryDAO();
	
	private String selectedReport = null;
	private Organization selectedOrganization = null;
	private Project selectedProject = null;

	private StringBuffer parameters;

	private StringBuffer selectMany;

	protected static List<List> listReports = null;

	private boolean launch = false;

	private String format;
	
	
	protected abstract void setListReports();
	
	protected void init(){
		
		dataPanels = new HashMap<String, List<SelectItem>>();
		
		dataPanels.put("users", getUsers());
		dataPanels.put("projects", getProjects());
		dataPanels.put("orgs", getOrganizations());
		dataPanels.put("roles", getRoles());
		dataPanels.put("years", getYears());
		dataPanels.put("accounts", getAccounts());
		dataPanels.put("models", getModels());
	}
	
	public void run() {
		parameters = new StringBuffer();
		selectMany = new StringBuffer();
		
		ReportUtil.createRunParameters(reportParametersDefinitions, parameters,
				selectMany);
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

	public void selectedReportChanged(ValueChangeEvent event) {
		this.setSelectedReport((String) event.getNewValue());
		//actualizar lista de reports cuando cambia entre categorias.
		setListReports();
		createReportParameters();
		for (ReportParameterDefinition param : reportParametersDefinitions) {
			 if (param.getId().equalsIgnoreCase("Proyecto")) {
				param.setValue(getSelectedProject().getId().toString());
			}
		}
		FacesUtils.renderResponse();
	}

	public String getSelectedReport() {
		return selectedReport;
	}
	
	private Map<String, List<SelectItem>> getMapDataPanels() {
		
		init();
		
		return dataPanels;
	}
	
	private void createReportParameters(){
		parameters = new StringBuffer();
		parameters.append("?");

		
		reportParametersDefinitions = new ArrayList<ReportParameterDefinition>();
		
		for (List reportsData : listReports) {
			// En el 0 esta el nombre
			if (reportsData.get(0).toString().equalsIgnoreCase(selectedReport)) {

				// reportsData.get(1) estan la lista de parametros
				ReportUtil.createReportParameterDefinition(
						(List<ParameterReport>) reportsData.get(1),
						reportParametersDefinitions, getMapDataPanels());
				reportParametersDefinitions.add(new ReportParameterDefinition(
						"idOrg", "hidden", "idOrg", ConfigurationUtil.getDefault().getIdOurCompany()));
			}
		}
	}

	public void setSelectedReport(String selectedReport) {
		this.selectedReport = selectedReport.replace(' ', '.');
	}

	public ArrayList<SelectItem> getReports() {

		setLaunch(false);
		if (reports == null) {
			setListReports();
			ArrayList<SelectItem> data = new ArrayList<SelectItem>();
			data.add(new SelectItem("", "- Elegir informe -"));
			int numReports = listReports.size();
			String name, nameFile;
			for (int i = 0; i < numReports; i++) {
				nameFile = listReports.get(i).get(0).toString();
				name = nameFile.replace('.', ' ');
				data.add(new SelectItem(nameFile, name));
			}

			reports = data;
		}

		return reports;
	}

	public ArrayList<SelectItem> getYears() {
		final ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		final Date date = new Date();
		for (int i = date.getYear(); i > date.getYear() - 5; i--) {
			ret.add(new SelectItem(Integer.toString(1900 + i), Integer
					.toString(1900 + i)));
		}
		return ret;
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

	public ArrayList<SelectItem> getModels() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<Inventary> refs = inventoryDAO.search(new SortCriteria("model"));
		for (Inventary ref : refs) {
			ret.add(new SelectItem(ref.getModel(), ref.getModel()));
		}
		
		ret.add(new SelectItem("all", "-"));
		
		return ret;
	}
	
	public ArrayList<SelectItem> getOrganizations() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<Organization> refs = organizationDAO.search(new SortCriteria("name"));
		for (Organization ref : refs) {
			ret.add(new SelectItem(ref.getId().toString(), ref.getName()));
		}
		return ret;
	}

	public ArrayList<SelectItem> getProjects() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();

		ProjectSearch projectCriteria = new ProjectSearch();
		projectCriteria.setClient(getSelectedOrganization());
		List<Project> projects = projectDAO.getDefault().searchByOrganization(new SortCriteria("name"), getSelectedOrganization());
		
		if(!projects.isEmpty()){
			setSelectedProject(projects.get(0));
		}

		for (Project project : projects) {
			ret.add(new SelectItem(project.getId().toString(), project
					.getName()));
		}
		return ret;
	}

	public ArrayList<SelectItem> getRoles() {

		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		
		ProjectRoleSearch projectRoleSearch = new ProjectRoleSearch();
		projectRoleSearch.setProject(getSelectedProject());
		List<ProjectRole> projectRoles = projectRoleDAO.getDefault().search(
				projectRoleSearch, new SortCriteria("name"));

		for (ProjectRole projectRole : projectRoles) {
			ret.add(new SelectItem(projectRole.getId().toString(), projectRole
					.getName()));
		}

		return ret;
	}

	public ArrayList<SelectItem> getUsers() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<User> refs = userDAO.search(new SortCriteria("name"));
		for (User ref : refs) {
			ret.add(new SelectItem(ref.getId().toString(), ref.getName()));
		}
		return ret;
	}

	public ArrayList<SelectItem> getAccounts() {
		final ArrayList<SelectItem> reto = new ArrayList<SelectItem>();

		final List<Account> refs = accountDAO.search(new SortCriteria(
				"name"));

		for (Account ref : refs) {
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
		if (parameters.toString().equals("?")) {
			this.parameters = parameters;
		} else {
			return;
		}
	}

	public Project getSelectedProject() {
		if (selectedProject == null) {
			List<Project> projects = ProjectManager.getDefault()
					.getProjectsByOrganization(getSelectedOrganization());
			if (!CollectionUtils.isEmpty(projects)) {
				setSelectedProject(projects.get(0));
			}
		}
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}


	public Organization getSelectedOrganization() {
		if (selectedOrganization == null) {
			selectedOrganization = OrganizationManager.getDefault()
					.getEntityById(ConfigurationUtil.getDefault().getIdOurCompany());
		}
		return selectedOrganization;
	}

	public void setSelectedOrganization(Organization selectedOrganization) {
		this.selectedOrganization = selectedOrganization;
	}

	public void selectedOrganizationChanged(ValueChangeEvent event) {
		
		setSelectedOrganization(OrganizationManager.getDefault().getEntityById(
				(Integer) event.getNewValue()));

		for (ReportParameterDefinition param : reportParametersDefinitions) {
			if (param.isSelectOneSelectManyType()) {
				param.setItems2(getProjects());
			}
			if (param.getId().equalsIgnoreCase("ROL")) {
				param.setItems(getRoles());
			}
			else if (param.getId().equalsIgnoreCase("Proyecto")) {
				param.setValue(getSelectedProject().getId().toString());
			}
		}

		FacesUtils.renderResponse();
	}

	public void selectedProjectChanged(ValueChangeEvent event) {

		setSelectedProject(ProjectManager.getDefault().getEntityById(
				Integer.parseInt(event.getNewValue().toString())));

		for (ReportParameterDefinition param : reportParametersDefinitions) {
			if (param.getId().equalsIgnoreCase("ROL")) {
				param.setItems(getRoles());
			}
			else if (param.getId().equalsIgnoreCase("Proyecto")) {
				param.setValue(getSelectedProject().getId().toString());
			}
		}

		FacesUtils.renderResponse();
	}

	public List<List> getListReports() {
		return listReports;
	}

}
