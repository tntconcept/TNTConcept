package com.autentia.tnt.report;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;

public class ODTGenerator extends TypeGenerator{

	@Override
	void generate(OutputStream outputStream, JasperReport report, Map args, Connection con) throws JRException {
		//debug("doGet - tipo odt");

		JasperPrint print = JasperFillManager.fillReport(report, args, con);
		JROdtExporter exporter = new JROdtExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.exportReport();
	}

}
