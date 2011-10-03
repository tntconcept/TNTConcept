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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import com.autentia.tnt.bean.reports.ReportParameterDefinition;
import com.autentia.tnt.xml.ParameterReport;

public final class ReportUtil {

	private static final Log log = LogFactory.getLog(ReportUtil.class);

	public static final String URL_PREFIX = "/report/";

	public static final String REPORT_PREFIX = "com/autentia/tnt/report/";

	public static final String SUBREPORT_PREFIX = "subreports/";

	public static final String REPORT_SUFFIX = ".jasper";

	public static final String REPORT_DEFINITION_SUFFIX = ".jrxml";

	public static final String GLOBAL_HOURS_REPORT_NAME = "Informe.de.parte.de.horas.global";
	public static final String GLOBAL_HOUR_REPORT_HEADER_FILE = "GlobalHoursReportHead.xml";
	public static final String GLOBAL_HOUR_REPORT_FOOTER_FILE = "GlobalHoursReportFoot.xml";
	public static final String GLOBAL_HOUR_REPORT_INTER_FILE = "GlobalHoursReportMed.xml";
	public static final String PARTIAL_REPORT_PREFIX = "partialReports/";

	/**
	 * Generates a report as an output stream from the name of the file
	 * 
	 * @param reportName
	 *            file with the report
	 * @return an outputStream with the content of the report
	 * @throws RuntimeException
	 *             if the report cannot be created
	 */
	public static InputStream getReportWithoutDatabaseConnectionAsInputStream(
			String reportName, Map<String, Object> parameters) {

		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		try {
			final InputStream is = getReportDefinitionUrl(reportName).openStream();
			final JasperReport report = JasperCompileManager.compileReport(is);
			final JasperPrint print = JasperFillManager.fillReport(report,
					parameters, new JREmptyDataSource());
			final JRPdfExporter exporter = new JRPdfExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
			exporter.exportReport();
		} catch (JRException e) {
			log.warn("The report '" + reportName + "' cannot be created", e);
			return null;
		} catch (IOException ioe) {
			log.warn("The report '" + reportName + "' cannot be recovered", ioe);
			return null;
		}

		return new ByteArrayInputStream(os.toByteArray());
	}

	public static void createRunParameters(List<ReportParameterDefinition> reportParametersDefinitions, StringBuffer parameters, StringBuffer selectMany) {
		parameters.append("?");
		if (reportParametersDefinitions != null){
			for (ReportParameterDefinition definition : reportParametersDefinitions) {
				if (definition.getType().equals("text")) {
					parameters.append(definition.getId() + "=" + definition.getValue() + "&");
				} else if (definition.getType().equals("year")) {
					parameters.append(definition.getId() + "=" + definition.getValue() + "&");
				} else if (definition.getType().equals("date")) {
					parameters.append(definition.getId() + "=" + definition.formatDate() + "&");
				} else if (definition.getType().equals("timestamp")) {
					if ("Fecha fin".equalsIgnoreCase(definition.getId())) {
						definition.setDateValue(DateUtils.maxHourInDate(definition.getDateValue()));
					} else {
						definition.setDateValue(DateUtils.minHourInDate(definition.getDateValue()));
					}
					parameters.append(definition.getId() + "=" + definition.formatTimestamp() + "&");
				} else if (definition.getType().equals("selectOne")) {
					parameters.append(definition.getId() + "=" + definition.getValue() + "&");
				} else if (definition.getType().equals("hidden")) {
					parameters.append(definition.getId() + "=" + definition.getValue() + "&");
				} else if (definition.getType().equals("selectMany") || definition.getType().equals("selectOne-selectMany")) {
					selectMany.append(definition.getId() + "=" + definition.getValueMany() + "&");
				} else if (definition.getType().equals("checkbox")) {
					parameters.append(definition.getId() + "=" + definition.getValue() + "&");
				}
			}
		}
	}


	
	public static void createReportParameterDefinition(List<ParameterReport> parameters, ArrayList<ReportParameterDefinition> reportParametersDefinitions, ArrayList<SelectItem> users, ArrayList<SelectItem> orgs, ArrayList<SelectItem> projs, ArrayList<SelectItem> years) {
		String name;
		String desc;
		String def;
		ReportParameterDefinition pdef;
		
		if (parameters != null) {
			for (ParameterReport parameter : parameters){
				name = parameter.getName();
				desc = parameter.getDescription();
				def  = parameter.getDefaultValue();
				
				if (desc != null){
					desc = desc.toUpperCase();
					
					if (desc.equals("DATE")) {
						pdef = new ReportParameterDefinition(name, "date", name, new Date());
					} else if (desc.equals("YEAR")) {
						pdef = new ReportParameterDefinition(name, "year", name, years);
					} else if (desc.equals("USER")) {
						pdef = new ReportParameterDefinition(name, "selectOne", name, users);
					} else if (desc.equals("ORGANIZATION")) {
						pdef = new ReportParameterDefinition(name, "selectMany", name, orgs);
					} else if (desc.equals("PROJECT")) {
						pdef = new ReportParameterDefinition(name, "selectOne-selectMany", name, orgs, projs);
					} else if (desc.equals("PROJECT_OUR_COMPANY")) {
						pdef = new ReportParameterDefinition(name, "selectOne", name, orgs, projs);
					} else if (desc.equals("SUBREPORT")) {
						pdef = new ReportParameterDefinition(name, "hidden", name, def.replace('"', ' ').trim());
					} else if (desc.equals("DESCRIPTION")) {
						pdef = new ReportParameterDefinition(name, "info", name, def.replace('"', ' '));
					} else if (desc.equals("TIMESTAMP")) {
						pdef = new ReportParameterDefinition(name, "timestamp", name, new Date());
					} else if (desc.equals("ACCOUNT")) {
						pdef = new ReportParameterDefinition(name, "selectOne", name, projs);
					} else if (desc.equals("BILLABLE")) {
						pdef = new ReportParameterDefinition(name, "checkbox", name, new Boolean(false));
					} else {
						continue;
					}
					reportParametersDefinitions.add(pdef);
				}
			}
		}
	}
	
	/**
	 * Obtiene un report de la aplicación. segun el parametro canBeOverrided el
	 * report se toamra del classpath, o bien se puede sobreescribir de la ruta
	 * externa de usuario
	 * 
	 * @param reportName
	 *            nombre del report, sin extension (document/ficha.de.pedido);
	 *            (billing/Informe.de.facturas.recibidas)
	 * @param canBeOverrided
	 *            si se puede sobreescribir el informe o bien se toma siempre
	 *            del classpath
	 * @return
	 */
	public static URL getReportStream(String reportName, String postfix, boolean canBeOverrided) {

		URL reportUrl = null;

		if (canBeOverrided) {
			String overridePath = ConfigurationUtil.getDefault().getReportPath() + "/override/" + reportName;
			try {
				final File file = new File(overridePath + postfix);
				if (file.exists()) {
					reportUrl = file.toURI().toURL();
				}
			} catch (MalformedURLException e) {
				log.info("report: " + reportName + " is not override. i'll try to find in classpath");
			}

		}

		// aun no hemos encontrado el report
		if (reportUrl == null) {
			reportUrl = Thread.currentThread().getContextClassLoader().getResource(REPORT_PREFIX + reportName + postfix);
		}

		return reportUrl;
	}

	/**
	 * Obtiene un report de la aplicación, si esta sobreescrito, o bien el de
	 * por defecto del classpth.<br/>
	 * <br/>
	 * NO PONER EL .jrxml DEL FINAL DEL FICHERO!!!
	 * 
	 * @param reportName
	 *            nombre del report, sin extension (document/ficha.de.pedido);
	 *            (billing/Informe.de.facturas.recibidas)
	 * @return
	 */
	public static URL getReportDefinitionUrl(String reportName) {
		return getReportStream(reportName, ReportUtil.REPORT_DEFINITION_SUFFIX, true);
	}

	public static URL getReportCompiledUrl(String reportName) {
		return getReportStream(reportName, ReportUtil.REPORT_SUFFIX, true);
	}
}
