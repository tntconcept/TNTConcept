package com.autentia.tnt.report;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import com.autentia.tnt.util.ConfigurationUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;

public class CSVGenerator extends TypeGenerator {

	@Override
	void generate(OutputStream outputStream, JasperReport report, Map args, Connection con) throws JRException {
		//debug("doGet - tipo csv");

		JasperPrint print = JasperFillManager.fillReport(report, args, con);
		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setParameter(JRCsvExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER,
				ConfigurationUtil.getDefault().getReportCSVDelimiter());
		exporter.exportReport();
	}
}
