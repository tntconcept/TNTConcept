package com.autentia.tnt.report;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import com.autentia.tnt.util.ConfigurationUtil;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;

public class CSVGenerator extends TypeGenerator {

	@Override
	void generate(OutputStream outputStream, JasperReport report, Map args, Connection con) throws JRException {

		args.put(JRParameter.IS_IGNORE_PAGINATION, true);
		report.setProperty("net.sf.jasperreports.export.csv.exclude.origin.band.title", "title");
		report.setProperty("net.sf.jasperreports.export.csv.exclude.origin.band.lastPageFooter", "lastPageFooter");
		report.setProperty("net.sf.jasperreports.export.csv.exclude.origin.band.orgsubreport", "detail");
		report.setProperty("net.sf.jasperreports.export.csv.exclude.origin.report.orgsubreport", "ourOrganization_subreport");
		report.setProperty("net.sf.jasperreports.export.csv.exclude.origin.band.orgnamesubreport", "detail");
		report.setProperty("net.sf.jasperreports.export.csv.exclude.origin.report.orgnamesubreport", "ourOrganizationName_subreport");
		report.setProperty("net.sf.jasperreports.export.csv.exclude.origin.band.columnheaders", "columnHeader");
		report.setProperty("net.sf.jasperreports.export.csv.exclude.origin.report.columnheaders", "*");
		report.setProperty("net.sf.jasperreports.export.csv.exclude.origin.band.pagefooters", "pageFooter");
		report.setProperty("net.sf.jasperreports.export.csv.exclude.origin.report.pagefooters", "*");

		JasperPrint print = JasperFillManager.fillReport(report, args, con);
		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setParameter(JRCsvExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER,
				ConfigurationUtil.getDefault().getReportCSVDelimiter());
		exporter.exportReport();
	}
}
