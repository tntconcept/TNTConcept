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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.businessobject.Account;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.AccountDAO;
import com.autentia.tnt.dao.hibernate.BillDAO;
import com.autentia.tnt.dao.hibernate.OrganizationDAO;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.dao.search.BillSearch;
import com.autentia.tnt.manager.report.ReportManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.ReportUtil;
import com.autentia.tnt.xml.ParameterReport;



/**
 * @author cris
 * 
 */

public class BillReportBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(BillReportBean.class);

	private List<SelectItem> reports;

	private ArrayList<ReportParameterDefinition> reportParametersDefinitions;

	private static final OrganizationDAO organizationDAO = OrganizationDAO.getDefault();

	private static final UserDAO userDAO = UserDAO.getDefault();

	private static final AccountDAO accountDAO = AccountDAO.getDefault();

	private BillSearch search = new BillSearch();

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

		if ((selectedReport == null) || selectedReport.equals(this.selectedReport)) {
			return;
		}
		
		launch = false;
		this.selectedReport = selectedReport;
		reportParametersDefinitions = new ArrayList<ReportParameterDefinition>();
		
		int numReports = 0;
		numReports = ReportManager.getReportManager().getReportListBill().size();
		for(int i = 0; i< numReports; i++) {
			try {
				List   reportData = ReportManager.getReportManager().getReportListBill().get(i);
				String reportName = reportData.get(0).toString();
				
				if (selectedReport.equals(reportName)) {
					List<ParameterReport> parameters = (List<ParameterReport>) reportData.get(1);
					ReportUtil.createReportParameterDefinition(parameters, reportParametersDefinitions,getUsers(), getOrgs(),getAccounts(),getYears());
					reportParametersDefinitions.add(new ReportParameterDefinition("idOrg","hidden","idOrg", ConfigurationUtil.getDefault().getIdOurCompany()));
				}
			} catch (Exception ex){
				log.error(ex);
			}
		}
		this.selectedReport = selectedReport.replace(' ','.');
		selectedReport = this.selectedReport;
		
	}
	
	public List<SelectItem> getReports() {
		setLaunch(false);
		final List<List> reportListBill = ReportManager.getReportManager().getReportListBill();
		
		if (reports == null) {
			List<SelectItem> selectItems = new ArrayList<SelectItem>();
			selectItems.add(new SelectItem("", FacesUtils.getMessage("select.selectReport")));
			
			for (int i = 0, numReports = reportListBill.size(); i < numReports; i++) {
				try {
					String nameFile    = reportListBill.get(i).get(0).toString();
					String name        = nameFile.replace('.', ' ');
					
					selectItems.add(new SelectItem(nameFile,name));
				} catch (Exception ex){
					log.error(ex);
				}
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

	public ArrayList<SelectItem> getOrgs() {
		ArrayList<SelectItem> reto = new ArrayList<SelectItem>();
		List<Organization> refs = organizationDAO.search(search, new SortCriteria("name"));
		for (Organization ref : refs) {
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
		int	  year = Calendar.getInstance().get(Calendar.YEAR);
		
		for (int i = year; i >= 2006; i--) {
			String b = Integer.toString(i);
			
			reto.add(new SelectItem(b, b));
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
		}
	}
}
