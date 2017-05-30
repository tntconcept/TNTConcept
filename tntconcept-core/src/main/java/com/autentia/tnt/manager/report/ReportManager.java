/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L. Copyright (C) 2007 Autentia Real Bussiness
 * Solution S.L. This program is free software: you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation, either version 3 of the License. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.manager.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.xml.ParameterReport;

/**
 * @author cris
 */
public class ReportManager implements ContentHandler {

	private static final String COM_AUTENTIA_TNT_REPORT_OFFER = "com/autentia/tnt/report/offer";

	private static final String COM_AUTENTIA_TNT_REPORT_ORGANIZATION = "com/autentia/tnt/report/organization";

	private static final String COM_AUTENTIA_TNT_REPORT_INTERACTION = "com/autentia/tnt/report/interaction";

	private static final String COM_AUTENTIA_TNT_REPORT_PROJECT = "com/autentia/tnt/report/project";

	private static final String COM_AUTENTIA_TNT_REPORT_BILL = "com/autentia/tnt/report/bill";

	private static final String COM_AUTENTIA_TNT_REPORT_ACTIVITY = "com/autentia/tnt/report/activity";

	private static final String COM_AUTENTIA_TNT_REPORT_GENERAL = "com/autentia/tnt/report/general";

	private static final String COM_AUTENTIA_TNT_REPORT_COMMISSIONING = "com/autentia/tnt/report/commissioning";

	private static final Log log = LogFactory.getLog(ReportManager.class);

	/* type of reports */
	private List reportListGeneral = null;

	private List reportListActivity = null;

	private List reportListBill = null;

	private List reportListProject = null;

	private List reportListOrganization = null;

	private List reportListPersonal = null;

	private List reportListOffer = null;

	private List reportListCommissioning = null;

	private List reportListInteraction = null;

	private List reportListOwn = null;

	// private List filesList = null;
	private static ReportManager instancia = null;

	private List<ParameterReport> param = null;

	// private List reportList = null;
	private String tempVal;

	private ParameterReport tempParam;

	private long parsingStart;

	private ReportManager() {
		log.info("ReportManager - parsing reports");
		reportListGeneral = parserFolderReport(true, COM_AUTENTIA_TNT_REPORT_GENERAL);
		reportListActivity = parserFolderReport(true, COM_AUTENTIA_TNT_REPORT_ACTIVITY);
		reportListBill = parserFolderReport(true, COM_AUTENTIA_TNT_REPORT_BILL);
		reportListProject = parserFolderReport(true, COM_AUTENTIA_TNT_REPORT_PROJECT);
		reportListInteraction = parserFolderReport(true, COM_AUTENTIA_TNT_REPORT_INTERACTION);
		reportListOrganization = parserFolderReport(true, COM_AUTENTIA_TNT_REPORT_ORGANIZATION);
		reportListOffer = parserFolderReport(true, COM_AUTENTIA_TNT_REPORT_OFFER);
		reportListCommissioning = parserFolderReport(true, COM_AUTENTIA_TNT_REPORT_COMMISSIONING);
		reportListPersonal = parserFolderReport(false, ConfigurationUtil.getDefault().getReportPath() + "personal/");
		reportListOwn = parserOwnReport();
	}

	private static synchronized void init() {
		if (instancia == null) {
			instancia = new ReportManager();
		}
	}

	public static ReportManager getReportManager() {
		init();
		return instancia;
	}

	public List parserFolderReport(Boolean typeFile, String folder) {
		List reportList = new ArrayList<List>();
		List filesList = filesFromFolder(typeFile, folder);

		for (int i = 0; i < filesList.size(); i++) {
			param = new ArrayList<ParameterReport>();
			List tmp = new ArrayList<List>();
			final String normalizedFile = normalize(filesList.get(i).toString());
			if (normalizedFile != null) {
				parseDocument(typeFile, filesList.get(i).toString());
				tmp.add(normalizedFile);
				tmp.add(param);
				reportList.add(tmp);
			}
		}
		return reportList;
	}

	public List parserOwnReport() {
		List reportList = new ArrayList<List>();

		// reports that the user can see
		List filesList = new ArrayList<String>();
		filesList.add("com/autentia/tnt/report/activity/Informe.de.actividad.externa.por.usuario.jrxml");
		filesList.add("com/autentia/tnt/report/activity/Informe.de.actividad.por.usuario.jrxml");
		filesList.add("com/autentia/tnt/report/activity/Informe.de.actividad.por.usuario.y.organizacion.jrxml");
		filesList.add("com/autentia/tnt/report/activity/Informe.de.actividad.por.usuario.y.horas.jrxml");

		for (int i = 0; i < filesList.size(); i++) {
			param = new ArrayList<ParameterReport>();
			List tmp = new ArrayList<List>();
			final String normalizedFile = normalize(filesList.get(i).toString());
			if (normalizedFile != null) {
				parseDocument(true, filesList.get(i).toString());
				tmp.add(normalizedFile);
				tmp.add(param);
				reportList.add(tmp);
			}
		}
		return reportList;
	}

	private void parseDocument(Boolean typeFile, String reportName) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		final InputStream jasperreportDtd = loader
				.getResourceAsStream("net/sf/jasperreports/engine/dtds/jasperreport.dtd");
		InputStream xmlSource = null;

		parsingStart = System.currentTimeMillis();
		log.debug("parseDocument - [start] " + reportName);
		try {
			SAXParser sp = new SAXParser();
			log.debug("parseDocument -   newSAXParser=" + (System.currentTimeMillis() - parsingStart) + " ms.");

			File f = null;
			try {
				if (typeFile == true)
					f = new File(loader.getResource(reportName).toURI());
				else
					f = new File(reportName);
			} catch (URISyntaxException e) {
				log.error("Error en ParseDocument", e);
			}
			log.debug("parseDocument -   getResource=" + (System.currentTimeMillis() - parsingStart) + " ms.");

			xmlSource = new FileInputStream(f);

			sp.setContentHandler(this);
			sp.setEntityResolver(new EntityResolver() {

				public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
					if (publicId.equals("//JasperReports//DTD Report Design//EN")
							|| systemId.equals("http://jasperreports.sourceforge.net/dtds/jasperreport.dtd")) {
						return new InputSource(jasperreportDtd);
					} else {
						String msg = "DTD (" + publicId + " " + systemId + ") cannot be resolved by ReportManager: "
								+ "please change TNTConcept to add the new DTD or change your JasperReport's JRXML file "
								+ "to use the standard DTD";
						log.error("parseDocument - " + msg);
						throw new IllegalArgumentException(msg);
					}
				}
			});

			sp.parse(new InputSource(xmlSource));
		} catch (FinalizeParsingException fpe) {
			// ignore this exception as it is thrown as an optimization
		} catch (SAXException se) {
			log.error("parseDocument - exception", se);
		} catch (IOException ie) {
			log.error("parseDocument - exception", ie);
		}

		finally {
			if (xmlSource != null) {
				try {
					xmlSource.close();
				} catch (IOException e) {
					// ignored
				}
			}
			try {
				jasperreportDtd.close();
			} catch (IOException e) {
				// ignored
			}
			log.info("parseDocument - " + reportName + " (" + (System.currentTimeMillis() - parsingStart) + " ms.)");
		}
	}

	public static List<String> filesFromFolder(Boolean typeFile, String path) {
		File[] filesList = null;
		List<String> list = new ArrayList<String>();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		File f = null;
		try {
			if (typeFile == true)
				f = new File(loader.getResource(path).toURI());
			else
				f = new File(path);
		} catch (Exception e) {
			log.error("Error en filesFromFolder", e);
		}
		if (f != null && f.isDirectory()) {
			filesList = f.listFiles();
			for (File file : filesList) {
				int i = file.getAbsolutePath().lastIndexOf(".");
				String format = file.getAbsolutePath().substring(i + 1);
				if (file.isFile() && (format.equals("jrxml"))) {
					list.add(path + "/" + file.getName());
				}
			}
		}
		Collections.sort(list);
		return list;
	}

	public static String normalize(String path) {
		String pathCleaned = null;
		final int reportIndex = path.indexOf("Informe");
		if (reportIndex >= 0) {
			pathCleaned = path.substring(reportIndex);
			pathCleaned = pathCleaned.replaceFirst(".jrxml", "");
		}
		return pathCleaned;
	}

	public List<List> getReportListActivity() {
		return reportListActivity;
	}

	public void setReportListActivity(List<List> reportListActivity) {
		this.reportListActivity = reportListActivity;
	}

	public List<List> getReportListBill() {
		return reportListBill;
	}

	public void setReportListBill(List<List> reportListBill) {
		this.reportListBill = reportListBill;
	}

	public List<List> getReportListGeneral() {
		return reportListGeneral;
	}

	public void setReportListGeneral(List<List> reportListGeneral) {
		this.reportListGeneral = reportListGeneral;
	}

	public List<List> getReportListInteraction() {
		return reportListInteraction;
	}

	public void setReportListInteraction(List<List> reportListInteraction) {
		this.reportListInteraction = reportListInteraction;
	}

	public List<List> getReportListOrganization() {
		return reportListOrganization;
	}

	public List<List> getReportListPersonal() {
		return reportListPersonal;
	}

	public List<List> getReportListOffer() {
		return reportListOffer;
	}

	public void setReportListOrganization(List<List> reportListOrganization) {
		this.reportListOrganization = reportListOrganization;
	}

	public void setReportListPersonal(List<List> reportListPersonal) {
		this.reportListPersonal = reportListPersonal;
	}

	public void setReportListOffer(List<List> reportListOffer) {
		this.reportListOffer = reportListOffer;
	}

	public List<List> getReportListProject() {
		return reportListProject;
	}

	public List<List> getReportListOwn() {
		return reportListOwn;
	}

	public void setReportListProject(List<List> reportListProject) {
		this.reportListProject = reportListProject;
	}

	// ContentHandler
	public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException {
		log.debug("parseDocument -   startElement(" + tagName + ")=" + (System.currentTimeMillis() - parsingStart)
				+ " ms.");
		tempVal = "";
		if (tagName.equals("parameter")) {
			tempParam = new ParameterReport();
			tempParam.setName(attributes.getValue("name"));
		} else if ((tagName.equals("queryString")) || (tagName.equals("background"))) {
			throw new FinalizeParsingException("Finalize parsing jrxml");
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch, start, length);
	}

	public void endElement(String uri, String localName, String tagName) throws SAXException {
		log.debug("parseDocument -   endElement(" + tagName + ")=" + (System.currentTimeMillis() - parsingStart)
				+ " ms.");
		if (tagName.equals("parameter")) {
			param.add(tempParam);
		} else if (tagName.equals("parameterDescription")) {
			tempParam.setDescription(tempVal);
		} else if (tagName.equals("defaultValueExpression")) {
			tempParam.setDefaultValue(tempVal);
		}
	}

	public void setDocumentLocator(Locator locator) {
	}

	public void startDocument() throws SAXException {
	}

	public void endDocument() throws SAXException {
	}

	public void startPrefixMapping(String prefix, String uri) throws SAXException {
	}

	public void endPrefixMapping(String prefix) throws SAXException {
	}

	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
	}

	public void processingInstruction(String target, String data) throws SAXException {
	}

	public void skippedEntity(String name) throws SAXException {
	}

	/**
	 * @return the reportListCommissioning
	 */
	public List<List> getReportListCommissioning() {
		return reportListCommissioning;
	}

}
