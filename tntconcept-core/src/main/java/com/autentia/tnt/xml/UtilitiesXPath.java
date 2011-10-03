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

package com.autentia.tnt.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class UtilitiesXPath {
	
	private static final Log log = LogFactory.getLog(UtilitiesXPath.class);
	/* type of reports */
	private static List<List> reportListGeneral = null;
	private static List<List> reportListActivity = null;
	private static List<List> reportListBill = null;
	private static List<List> reportListProject = null;
	private static List<List> reportListOrganization = null;
	private static List<List> reportListInteraction = null;
	
	private static List<List> reportList = null;
	
	private static List<String> nameReport = null;
	private static String expression = "";
	private static NodeList nodes = null;
	private static List parametersName = null;
	private static List parametersDescription = null;
	private static List parametersDefaultValue = null;
	private static List nameClass = null;
	private static List filesList = null;
	private static List tmpSend = null;
	
	public UtilitiesXPath() {  
		
			log.debug("\n---- INICIO DEL PARSEADO -----\n");
		   	
		   	/*reportListActivity = ExtractReport("com/autentia/tnt/report/activity");
		   	reportListGeneral = ExtractReport("com/autentia/tnt/report/general");
		   	reportListBill = ExtractReport("com/autentia/tnt/report/bill");
		   	reportListProject = ExtractReport("com/autentia/tnt/report/project");
		   	reportListOrganization = ExtractReport("com/autentia/tnt/report/organization");*/
		   	reportListInteraction = ExtractReport("com/autentia/tnt/report/interaction");
		   	
		   	log.debug("\n---- FIN DEL PARSEADO -----\n");
		   	
			    	
	}
	
	public List<List> ExtractReport(String folderReport) {
	
	try {
	 	reportList = null;
	 	reportList = new ArrayList<List>();
		filesList = null;
		filesList = UtilitiesXML.filesFromFolder(folderReport);
		for(int i=0;i<filesList.size();i++) {
    		List<List> tmp = new ArrayList<List>();
    		Document doc = UtilitiesXML.file2Document(filesList.get(i).toString());
        	XPath xpath = XPathFactory.newInstance().newXPath();
            
        	if (log.isDebugEnabled()){
        		log.debug("\n>> Nombre del informe: ");
        	}
        	
        	expression = "/jasperReport[@name]";
            nodes = (NodeList) xpath.evaluate(expression, doc.getDocumentElement(), XPathConstants.NODESET);
            nameReport = UtilitiesXML.printAttribute("name",nodes);
            nameReport.add(UtilitiesXML.cleanReport(filesList.get(i).toString()));
            
            if (log.isDebugEnabled()){
            	log.debug(nameReport.toString());
            	log.debug("Parametros: ");
            }
            
            expression = "/jasperReport/parameter[@name]";
            nodes = (NodeList) xpath.evaluate(expression, doc.getDocumentElement(), XPathConstants.NODESET);
            parametersName = UtilitiesXML.printAttribute("name",nodes);
            
            if (log.isDebugEnabled()){
            	log.debug(parametersName.toString());
            	log.debug("Clases: ");
            }
            
            expression = "/jasperReport/paremeter[@class]";
            nameClass = UtilitiesXML.printAttribute("class",nodes);
            
            if (log.isDebugEnabled()){
	            log.debug(nameClass.toString());
	            log.debug("Descripcion de Parametros: ");
            }
            
            expression = "/jasperReport/parameter/parameterDescription";
            nodes = (NodeList) xpath.evaluate(expression, doc.getDocumentElement(), XPathConstants.NODESET);
            parametersDescription = UtilitiesXML.nodes2String(nodes);
            
            if (log.isDebugEnabled()){
            	log.debug(parametersDescription.toString());
            	log.debug("Valores por defectos: ");
            }
            
            expression = "/jasperReport/parameter/defaultValueExpression";
            nodes = (NodeList) xpath.evaluate(expression, doc.getDocumentElement(), XPathConstants.NODESET);
            parametersDefaultValue = UtilitiesXML.nodes2String(nodes);

            if (log.isDebugEnabled()){            
            	log.debug(parametersDefaultValue.toString()); 
            	log.debug("\n------------------------------------");
            }
            
            tmp.add(nameReport);
            tmp.add(parametersName);
            tmp.add(nameClass);
            tmp.add(parametersDescription);
            tmp.add(parametersDefaultValue);
            reportList.add(tmp);
            
    	}
    	log.debug("Lista de informes:" + reportList);
    	
	 } catch (Exception e) {            
         log.error(e);
     }
		return reportList;
		
	}

	public static List<List> getReportList() {
		return reportList;
	}

	public static void setReportList(List<List> reportList) {
		UtilitiesXPath.reportList = reportList;
	}

	public static List<List> getReportListActivity() {
		return reportListActivity;
	}

	public static void setReportListActivity(List<List> reportListActivity) {
		UtilitiesXPath.reportListActivity = reportListActivity;
	}

	public static List<List> getReportListBill() {
		return reportListBill;
	}

	public static void setReportListBill(List<List> reportListBill) {
		UtilitiesXPath.reportListBill = reportListBill;
	}

	public static List<List> getReportListGeneral() {
		return reportListGeneral;
	}

	public static void setReportListGeneral(List<List> reportListGeneral) {
		UtilitiesXPath.reportListGeneral = reportListGeneral;
	}

	public static List<List> getReportListInteraction() {
		return reportListInteraction;
	}

	public static void setReportListInteraction(List<List> reportListInteraction) {
		UtilitiesXPath.reportListInteraction = reportListInteraction;
	}

	public static List<List> getReportListOrganization() {
		return reportListOrganization;
	}

	public static void setReportListOrganization(List<List> reportListOrganization) {
		UtilitiesXPath.reportListOrganization = reportListOrganization;
	}

	public static List<List> getReportListProject() {
		return reportListProject;
	}

	public static void setReportListProject(List<List> reportListProject) {
		UtilitiesXPath.reportListProject = reportListProject;
	}

}

