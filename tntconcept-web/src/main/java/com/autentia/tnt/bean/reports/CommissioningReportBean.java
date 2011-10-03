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
import com.autentia.tnt.businessobject.Account;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.AccountDAO;
import com.autentia.tnt.dao.hibernate.CommissioningDAO;
import com.autentia.tnt.dao.hibernate.OrganizationDAO;
import com.autentia.tnt.dao.hibernate.ProjectDAO;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.dao.search.CommissioningSearch;
import com.autentia.tnt.dao.search.ProjectSearch;
import com.autentia.tnt.manager.report.ReportManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.ReportUtil;
import com.autentia.tnt.xml.ParameterReport;



/**
 * @author cris
 * 
 */

public class CommissioningReportBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(CommissioningReportBean.class);

	private List<SelectItem> reports;

	private ArrayList<ReportParameterDefinition> reportParametersDefinitions;

	private static final OrganizationDAO organizationDAO = OrganizationDAO.getDefault();
	
	private static final ProjectDAO projectDAO = new ProjectDAO();

	private static final UserDAO userDAO = UserDAO.getDefault();

	private static final AccountDAO accountDAO = AccountDAO.getDefault();

	private CommissioningSearch search = new CommissioningSearch();

	private String selectedReport = null;

	private String format;
	
	private Organization org;

	private Account account;
	
	private StringBuffer parameters;
	
	private StringBuffer selectMany;	

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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
		numReports = ReportManager.getReportManager().getReportListCommissioning().size();
		for(int i=0;i<numReports;i++) {
			List reportData = new ArrayList<List>();
			String reportName = "";
			reportData = ReportManager.getReportManager().getReportListCommissioning().get(i);
			reportName = reportData.get(0).toString();
			if(reportName.equals(selectedReport)) {
				List<ParameterReport> parameters = new ArrayList<ParameterReport>();
				parameters = (List<ParameterReport>) reportData.get(1);
				ReportUtil.createReportParameterDefinition(parameters, reportParametersDefinitions,getUsers(),
						getProjects(),getAccounts(),getYears());
				reportParametersDefinitions.add(new ReportParameterDefinition("idOrg","hidden","idOrg",
											    ConfigurationUtil.getDefault().getIdOurCompany()));
			}
		}
		this.selectedReport = selectedReport.replace(' ','.');
		selectedReport = this.selectedReport;
		
	}
	
	public List<SelectItem> getReports() {
		setLaunch(false);
		final List<List> reportListCommissioning = ReportManager.getReportManager().getReportListCommissioning();
		
		if (reports == null) {
			final List<SelectItem> selectItems = new ArrayList<SelectItem>();
			selectItems.add(new SelectItem("", FacesUtils.getMessage("select.selectReport")));
			int numReports = 0;
			numReports = reportListCommissioning.size();
			List reportData = new ArrayList();
			
			for(int i=0;i<numReports;i++) {
				String name = "";
				String nameFile = "";
				reportData = reportListCommissioning.get(i);
				nameFile = reportData.get(0).toString();
				name = nameFile.replace('.', ' ');
				selectItems.add(new SelectItem(nameFile,name));
			}
		
			reports = selectItems;
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

	public ArrayList<SelectItem> getProjects() {
		ArrayList<SelectItem> reto = new ArrayList<SelectItem>();
						
		List<Project> refs = projectDAO.searchOpenByOrganization(new SortCriteria("name"), organizationDAO.getById(1));
		for (Project ref : refs) {
			reto.add(new SelectItem(ref.getId().toString(), ref.getName()));
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
	
	public ArrayList<SelectItem> getAccounts() {
		final ArrayList<SelectItem> reto = new ArrayList<SelectItem>();
		
		final List<Account> refs = accountDAO.search(search, new SortCriteria("name"));

		for (Account ref : refs) {
			reto.add(new SelectItem(ref.getId(), ref.getName()));
		}
		
		return reto;
	}

	public ArrayList<SelectItem> getYears() {
		final ArrayList<SelectItem> reto = new ArrayList<SelectItem>();
		final Date date = new Date();
		for (int i = date.getYear(); i > date.getYear() - 5; i--) {
			reto.add(new SelectItem(Integer.toString(1900 + i), Integer.toString(1900 + i)));
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

	
	

}
