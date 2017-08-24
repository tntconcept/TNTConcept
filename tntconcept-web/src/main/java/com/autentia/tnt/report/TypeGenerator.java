package com.autentia.tnt.report;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

public abstract class TypeGenerator {
	abstract void generate(OutputStream outputStream, JasperReport report, Map args, Connection con) throws JRException;
}
