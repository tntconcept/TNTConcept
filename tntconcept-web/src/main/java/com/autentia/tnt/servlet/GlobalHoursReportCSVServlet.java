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

package com.autentia.tnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;

import com.autentia.tnt.bean.activity.GlobalHoursReportBean;
import com.autentia.tnt.businessobject.GlobalHourReport;
import com.autentia.tnt.businessobject.User;


public class GlobalHoursReportCSVServlet extends HttpServlet {
	
	private static String CSV_SEPARATOR = ";";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String pStartDate 		= req.getParameter("startDate");
		String pEndDate 		= req.getParameter("endDate");
		String pBillable		= req.getParameter("billable"); 
		
		SimpleDateFormat sdf	= new SimpleDateFormat("dd/MM/yyyy");
		
		StringBuilder csvReport	= new StringBuilder();
		
		GlobalHoursReportBean ghReportBean = (GlobalHoursReportBean) req.getSession().getAttribute("globalHoursReportBean");
		
		PrintWriter writer = resp.getWriter();
		
		try {
			Date startDate			= sdf.parse(pStartDate);
			Date endDate			= sdf.parse(pEndDate);
			boolean billable		= Boolean.valueOf(pBillable);
			
			ghReportBean.setEndDate(endDate);
			ghReportBean.setStartDate(startDate);
			ghReportBean.setBillable(billable);
			
			List<User> users = ghReportBean.getUsers();
			List<GlobalHourReport> globalHourReports = ghReportBean.getAll();
			
			csvReport.append(getCSVHeader(users));
			csvReport.append(getCSVBody(globalHourReports));
			csvReport.append(getCSVFooter(users, globalHourReports));
			
			resp.setContentType("text/csv");
			resp.setContentLength(csvReport.length());
			
			writer.append(csvReport.toString());
			
			
		} catch (ParseException e) {
			Log.error("Error en el parseo ", e);
		} finally {
			writer.close();
		}
		
		
		
		
	}
	
	

	private StringBuilder getCSVHeader(List<User> users) {
		
		StringBuilder sbHeader = new StringBuilder();
		StringBuilder sbSubHeader = new StringBuilder();
		
		sbHeader.append("\"Organización\"").append(CSV_SEPARATOR).append("\"Proyecto\"");
		sbSubHeader.append("").append(CSV_SEPARATOR).append("");
		
		for (User user : users) {
			
			sbHeader.append(CSV_SEPARATOR).append("\""+ user.getName() +"\"").append(CSV_SEPARATOR).append("");
			sbSubHeader.append(CSV_SEPARATOR).append("\"Horas\"").append(CSV_SEPARATOR).append("\"Coste\"");
		}
		
		sbHeader.append(CSV_SEPARATOR).append("\"Total\"").append(CSV_SEPARATOR).append("").append("\r\n");
		sbSubHeader.append(CSV_SEPARATOR).append("\"Horas\"").append(CSV_SEPARATOR).append("\"Coste\"").append("\r\n");
		
		return sbHeader.append(sbSubHeader);
		
	}

	private StringBuilder getCSVBody(List<GlobalHourReport> globalHourReports) {
		
		StringBuilder sbBody = new StringBuilder();

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		
		DecimalFormat formatter = new DecimalFormat("#0.00", symbols);
		
		for (GlobalHourReport report : globalHourReports) {
			
			String organization = report.getProject().getClient().getName();
			String project = report.getProject().getName();
			java.util.Iterator<User> users = report.getIterator();
			double totalHours=0;
			double totalCost=0;
			
			sbBody.append("\"").append(organization).append("\"").append(CSV_SEPARATOR).append("\"").append(project).append("\"");
			while (users.hasNext()) {
				User user = users.next();
				double userProjectCost = report.getUserHours(user)*user.getSalaryPerHour();
				
				totalHours += report.getUserHours(user);
				totalCost += userProjectCost;
				
				sbBody.append(CSV_SEPARATOR).append("\"").append(formatter.format(report.getUserHours(user))).append("\"").append(CSV_SEPARATOR)
					.append("\"").append(formatter.format(userProjectCost)).append("\"");
				
			}
			
			sbBody.append(CSV_SEPARATOR).append("\"").append(formatter.format(totalHours)).append("\"")
				.append(CSV_SEPARATOR).append("\"").append(formatter.format(totalCost)).append("\"").append("\r\n");
			
		}
		
		
		return sbBody;
	}

		
	private StringBuilder getCSVFooter(List<User> users,
			List<GlobalHourReport> globalHourReports) {
		StringBuilder sbFooter = new StringBuilder();
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		
		DecimalFormat formatter = new DecimalFormat("#0.00", symbols);
		
		sbFooter.append("\"\"").append(CSV_SEPARATOR).append("\"TOTAL\"");
		double totalHours=0;
		double totalCost = 0;
		for (User user : users) {
			
			double userTotalHours = 0;
			
			for (GlobalHourReport report : globalHourReports) {
				userTotalHours += report.getUserHours(user);
			}
			
			sbFooter.append(CSV_SEPARATOR).append("\"").append(formatter.format(userTotalHours)).append("\"").append(CSV_SEPARATOR).append("\"")
					.append(formatter.format(userTotalHours*user.getSalaryPerHour())).append("\"");
			
			totalHours += userTotalHours;
			totalCost += userTotalHours*user.getSalaryPerHour();
			
		}
		
		sbFooter.append(CSV_SEPARATOR).append("\"").append(formatter.format(totalHours)).append("\"").append(CSV_SEPARATOR).append("\"")
				.append(formatter.format(totalCost)).append("\"").append("\r\n");
		
		return sbFooter;
	}

}
