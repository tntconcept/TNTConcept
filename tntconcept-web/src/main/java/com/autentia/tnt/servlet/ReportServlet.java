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
package com.autentia.tnt.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.error.ReportException;
import com.autentia.tnt.report.ReportFormat;
import com.autentia.tnt.report.ReportGeneratorStandardImpl;
import com.autentia.tnt.report.ReportInfo.ReportInfoBuilder;
import com.autentia.tnt.util.ReportUtil;

/**
 * @author ivan
 * 
 */
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = -5605017166013496429L;

	private static final Log log = LogFactory.getLog(ReportServlet.class);

	private void debug(String s) {
		if (log.isDebugEnabled()) {
			log.debug(s);
		}
	}

	private class DataObtainer {
		int lastDotPosition;
		int lastSlashPosition;
		String reportName;
		String reportExtension;
		String reportCategory;
		String initialReportName;

		public DataObtainer(String reportName) {
			this.initialReportName = reportName;
			this.calculateParameters();
		}

		private void calculateParameters() {
			this.lastDotPosition = initialReportName.lastIndexOf(".");
			this.lastSlashPosition = initialReportName.lastIndexOf("/");

			if (this.arePositionsCorrect()) {
				this.reportExtension = initialReportName.substring(lastDotPosition + 1).toUpperCase();
				this.reportCategory = initialReportName.substring(0, lastSlashPosition + 1);
				this.reportName = initialReportName.substring(0, lastDotPosition);
			}
		}

		private boolean arePositionsCorrect() {
			if (!((lastDotPosition != -1) || (lastSlashPosition != -1))) {
				debug("arePositionsCorrect - SC_INTERNAL_SERVER_ERROR");
				return false;
			}
			return true;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		debug("doGet - inicio");
		prepareAndGenerateReport(request, response);
	}

	private void prepareAndGenerateReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uri = request.getRequestURI();
		int urlPrefixPosition = uri.indexOf(ReportUtil.URL_PREFIX);
		if (isNotCorrectURI(urlPrefixPosition, response))
			return;

		String initialReportName = uri.substring(urlPrefixPosition + ReportUtil.URL_PREFIX.length());

		DataObtainer obtainer = new DataObtainer(initialReportName);

		debug("prepareAndGenerateReport - report name solicitado: " + obtainer.reportName);

		generateReport(request, response, obtainer.reportName, obtainer.reportExtension, obtainer.reportCategory);
	}

	private boolean isNotCorrectURI(int i, HttpServletResponse response) throws IOException {
		if (i == -1) {
			debug("isNotCorrectURI - SC_INTERNAL_SERVER_ERROR");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Bad URL prefix for servlet: check your web.xml file");
			return true;
		}
		return false;
	}

	private void generateReport(HttpServletRequest request, HttpServletResponse response, String reportName, String ext,
			String reportCategory) throws IOException {
		ReportInfoBuilder builder = new ReportInfoBuilder();
		builder.name(reportName).in(ReportFormat.valueOf(ext)).withCategory(reportCategory);

		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String arg = (String) e.nextElement();
			final String value = request.getParameter(arg);
			builder.andParameter(arg, value);
		}

		ReportGeneratorStandardImpl generator = new ReportGeneratorStandardImpl();
		try {
			generator.generate(builder.build(), response.getOutputStream());
		} catch (ReportException e1) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 e1.getMessage());
		}
		
		response.setContentType(ReportFormat.valueOf(ext).getResponseType());
	}
}
