package com.autentia.tnt.report;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class XLSGenerator extends TypeGenerator{

	@Override
	void generate(OutputStream outputStream, JasperReport report, Map args, Connection con) throws JRException {
		//debug("doGet - tipo xls");
		JasperPrint print = JasperFillManager.fillReport(report, args, con);
		JRXlsExporter exporter = new JRXlsExporter();

		exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.exportReport();
	}
	
}
