package com.autentia.tnt.report;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.iterators.IteratorEnumeration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.autentia.tnt.bean.activity.GlobalHoursReportBean;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.error.ReportException;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.servlet.ReportServlet;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.HibernateUtil;
import com.autentia.tnt.util.ReportUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportGeneratorStandardImpl implements ReportGenerator {
	private static final Log log = LogFactory.getLog(ReportServlet.class);

	private static final int GLOBAL_HOUR_REPORT_COLUMN_WIDTH = 109;
	private static final int GLOBAL_HOUR_REPORT_HEADER_HEIGHT = 62;
	private static final int GLOBAL_HOUR_REPORT_SPACE_BETWEEN_COLS = 4;
	private static final int GLOBAL_HOUR_REPORT_SUBHEADER_HEIGHT = 13;
	private static final int GLOBAL_HOUR_REPORT_SUBHEADER_WIDTH = 51;
	private static final int GLOBAL_HOUR_REPORT_HOURS_COL_SPACE = 7;
	private static final int GLOBAL_HOUR_REPORT_SUBREPORT_WIDTH = 72;
	private static final int GLOBAL_HOUR_REPORT_SUBREPORT_HEIGHT = 15;
	private static final int GLOBAL_HOUR_REPORT_INITIAL_X_POS = 224;
	private static final int GLOBAL_HOUR_REPORT_SUBREPORT_X_MARGIN = 20;
	private static final int GLOBAL_HOUR_REPORT_ALWAYS_PRESENT_COLS_NUM = 3;
	private static final String GLOBAL_HOUR_REPORT_HEADER_FIELD_KEY = "headerText";
	private static final String GLOBAL_HOUR_REPORT_HOURS_FIELD_KEY = "hoursText";
	private static final String GLOBAL_HOUR_REPORT_COST_FIELD_KEY = "costText";
	private static final String GLOBAL_HOUR_REPORT_SUBREPORT_KEY = "subreportAct";
	private static final String GLOBAL_HOUR_REPORT_TOTAL_COST_KEY = "totalCost";
	private static final String GLOBAL_HOUR_REPORT_TOTAL_HOURS_KEY = "totalHours";
	private static final String GLOBAL_HOUR_REPORT_TOTAL_HOURS_VAR = "totalHours";
	private static final String GLOBAL_HOUR_REPORT_TOTAL_COST_VAR = "totalCost";
	private static final int REPORT_A4_LANDSCAPE_MIN_WIDTH = 842;

	private static final int TIMES_SUBREPORT_CREATION_IS_WAITED = 100;
	private static final int SLEEP_TIME_IN_MILLIS_WHEN_CREATING_SUBREPORTS = 100;

	private void debug(String s) {
		if (log.isDebugEnabled()) {
			log.debug(s);
		}
	}
	
	private void throwError(String message) throws ReportException {
		throw new ReportException(message);
	}
	
	private void throwError(String message, Throwable exception) {
		throw new ReportException(message, exception);
	}

	public void generate(ReportInfo parameters, OutputStream outputStream) throws ReportException {
		JasperReport report = null;

		Session session = null;
		Connection con = null;

		try {
			report = initReport(parameters.name, parameters.category);

			debug("ReportGenerator - report: " + report);
			debug("ReportGenerator - iniciando lista de parametros");

			if (report == null) {
				debug("ReportGenerator - SC_NOT_FOUND");
				// response.sendError(HttpServletResponse.SC_NOT_FOUND);
				this.throwError("Null report");
				return;
			}

			Map args = getParametersAsMapAndSubReport(parameters.parameters, parameters.category);

			// establecemos el resource bundle correspondiente al locale actual
			// del usuario al JasperReport

			final Principal principal = AuthenticationManager.getDefault().getCurrentPrincipal();

			args.put(JRParameter.REPORT_RESOURCE_BUNDLE,
					ResourceBundle.getBundle("com.autentia.tnt.resources.report", principal.getLocale()));

			debug("ReportGenerator - argc " + args.size());
			debug("ReportGenerator - iniciando conexion and BD");

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			con = session.connection();

			debug("ReportGenerator - generación del informe en función del formato");
			GeneratorFactory.getFactory().getGeneratorByFormat(parameters.format).generate(outputStream, report, args, con);

			debug("ReportGenerator - finalización del informe");

		} catch (Exception e) {
			log.error("ReportGenerator - exception", e);
			this.throwError("Exception");
			/*
			 * try {
			 * response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			 * e.getMessage()); } catch (IOException e1) { // TODO
			 * Auto-generated catch block e1.printStackTrace(); }
			 */
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {
					log.error("ReportGenerator - exception cerrando conexion", e1);
				}
				con = null;
			}
		}
	}

	private Map getParametersAsMapAndSubReport(Map<String, Object> parameters, String reportCategory) throws Exception, ReportException {
		JasperReport subReport;
		Map args = new HashMap();
		Enumeration e = new IteratorEnumeration(parameters.keySet().iterator());
		while (e.hasMoreElements()) {
			String arg = (String) e.nextElement();
			final String value = (String) parameters.get(arg);
			Object obj = null;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date value_date = sdf.parse(value);
				java.sql.Timestamp ts = new Timestamp(value_date.getTime());

				obj = ts;
			} catch (Exception ex) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date value_date = sdf.parse(value);
					obj = value_date;
				} catch (Exception ex2) {
					try {
						Integer value_int = Integer.parseInt(value);
						obj = value_int;
					} catch (Exception ex3) {
						if ("true".equals(value) || "false".equals(value)) {
							obj = Boolean.parseBoolean(value);
						} else {
							obj = value;
						}
					}
				}
			}

			if (arg.startsWith("SUBREPORT")) {
				obj = loadSubreportByCategory(reportCategory, obj);
			}

			if (obj.equals("allItemsSelected")) {
				obj = "%";
			}

			args.put(arg, obj);
		}

		return args;
	}

	private Object loadSubreportByCategory(String reportCategory, Object obj) throws JRException, ReportException {
		JasperReport subReport;
		final String subreportPath = ConfigurationUtil.getDefault().getReportPath();

		String targetFilePath;
		String sourceFilePath;

		if (reportCategory.equals("personal/")) {
			sourceFilePath = subreportPath + ReportUtil.SUBREPORT_PREFIX + obj + ReportUtil.REPORT_DEFINITION_SUFFIX;
			targetFilePath = subreportPath + ReportUtil.SUBREPORT_PREFIX + obj + ReportUtil.REPORT_SUFFIX;
		} else {
			sourceFilePath = ReportUtil.REPORT_PREFIX + reportCategory + ReportUtil.SUBREPORT_PREFIX + obj
					+ ReportUtil.REPORT_DEFINITION_SUFFIX;
			targetFilePath = ReportUtil.REPORT_PREFIX + reportCategory + ReportUtil.SUBREPORT_PREFIX + obj
					+ ReportUtil.REPORT_SUFFIX;
		}

		if (null == getResourceAsURL(targetFilePath)) {
			if (null != getResourceAsString(sourceFilePath))
				JasperCompileManager.compileReportToFile(getResourceAsString(sourceFilePath));
		}

		int timeSpentWhile = 0;

		while (isNotSubreportAvailable(getResourceAsURL(targetFilePath), timeSpentWhile)) {
			try {
				Thread.currentThread().sleep(SLEEP_TIME_IN_MILLIS_WHEN_CREATING_SUBREPORTS);
				timeSpentWhile += SLEEP_TIME_IN_MILLIS_WHEN_CREATING_SUBREPORTS;
			} catch (Exception ex) {
				this.throwError("Subreport waiter sleep exception", ex);
			}
		}

		subReport = (JasperReport) JRLoader.loadObject(getResourceAsURL(targetFilePath));
		obj = targetFilePath;

		return obj;
	}

	private boolean isNotSubreportAvailable(URL locationOfCompiledSubreport, int timeSpentWhile) {
		if (locationOfCompiledSubreport == null
				&& timeSpentWhile < TIMES_SUBREPORT_CREATION_IS_WAITED * SLEEP_TIME_IN_MILLIS_WHEN_CREATING_SUBREPORTS)
			return true;
		return false;
	}

	private JasperReport initReport(final String reportName, String reportCategory) throws Exception {

		JasperReport report = null;

		debug("ReportGenerator initReport - report file: " + ReportUtil.REPORT_PREFIX + reportName + ReportUtil.REPORT_SUFFIX);
		debug("ReportGenerator initReport - report jrxml url: " + Thread.currentThread().getContextClassLoader()
				.getResource(ReportUtil.REPORT_PREFIX + reportName + ReportUtil.REPORT_DEFINITION_SUFFIX));
		debug("ReportGenerator initReport - comprobación de fichero .jasper");

		boolean overrideReport = false;

		// informe especial de horas globales que se genera dinamicamente
		if ((reportCategory + ReportUtil.GLOBAL_HOURS_REPORT_NAME).equals(reportName)) {
			byte[] ghReportBytes = buildGlobalHoursReportJrxml(reportCategory);
			report = JasperCompileManager.compileReport(new ByteArrayInputStream(ghReportBytes));
		} else if ("personal/".equals(reportCategory)) {
			String path = ConfigurationUtil.getDefault().getReportPath() + reportName;
			JasperCompileManager.compileReportToFile(path + ReportUtil.REPORT_DEFINITION_SUFFIX);
			report = (JasperReport) JRLoader.loadObject(path + ReportUtil.REPORT_SUFFIX);
		} else {

			final URL reportUrlDefinition = ReportUtil.getReportDefinitionUrl(reportName);
			final File reportFileCompiled = new File(reportUrlDefinition.getPath().replaceFirst("jrxml", "jasper"));
			String jasperCompiled = null;
			if (ConfigurationUtil.getDefault().isForceCompileReports() || !reportFileCompiled.exists()) {
				jasperCompiled = JasperCompileManager.compileReportToFile(reportUrlDefinition.getPath());
			} else {
				jasperCompiled = reportFileCompiled.getAbsolutePath();
			}
			report = (JasperReport) JRLoader.loadObject(jasperCompiled);

		}
		return report;

	}

	private byte[] buildGlobalHoursReportJrxml(String reportCategory) {
		String reportHeaderFileName = ReportUtil.REPORT_PREFIX + reportCategory + ReportUtil.PARTIAL_REPORT_PREFIX
				+ ReportUtil.GLOBAL_HOUR_REPORT_HEADER_FILE;
		String reportInterFileName = ReportUtil.REPORT_PREFIX + reportCategory + ReportUtil.PARTIAL_REPORT_PREFIX
				+ ReportUtil.GLOBAL_HOUR_REPORT_INTER_FILE;
		String reportFooterFileName = ReportUtil.REPORT_PREFIX + reportCategory + ReportUtil.PARTIAL_REPORT_PREFIX
				+ ReportUtil.GLOBAL_HOUR_REPORT_FOOTER_FILE;

		Reader jrxmlHeader = null;
		Reader jrxmlFooter = null;
		Reader jrxmlInter = null;
		ByteArrayOutputStream report = new ByteArrayOutputStream();
		byte[] output = null;

		int posX = GLOBAL_HOUR_REPORT_INITIAL_X_POS;
		int colNum = GLOBAL_HOUR_REPORT_ALWAYS_PRESENT_COLS_NUM;
		int pageWidth;

		StringBuilder columnHeader = new StringBuilder();
		StringBuilder detail = new StringBuilder();
		StringBuilder summary = new StringBuilder();
		StringBuilder vars = new StringBuilder();

		GlobalHoursReportBean ghReportBean = new GlobalHoursReportBean();

		List<User> users = ghReportBean.getUsers();

		colNum += users.size();
		pageWidth = colNum * (GLOBAL_HOUR_REPORT_COLUMN_WIDTH + GLOBAL_HOUR_REPORT_SPACE_BETWEEN_COLS);

		summary.append("<summary>");
		summary.append("<band height='25'  isSplitAllowed='true' >");

		for (int l = 0; l < users.size(); l++) {

			User user = users.get(l);

			columnHeader.append("<staticText><reportElement x='").append(posX).append("' y='5' width='")
					.append(GLOBAL_HOUR_REPORT_COLUMN_WIDTH).append("' height='")
					.append(GLOBAL_HOUR_REPORT_HEADER_HEIGHT).append("' ").append("key='")
					.append(GLOBAL_HOUR_REPORT_HEADER_FIELD_KEY + l).append("'/>");
			columnHeader.append("<box></box><textElement textAlignment='Center' verticalAlignment='Middle'>")
					.append("<font pdfFontName='Helvetica-Bold' size='14' isBold='true'/></textElement>");
			columnHeader.append("<text><![CDATA[").append(user.getName()).append("]]></text></staticText>");

			columnHeader.append("<staticText><reportElement x='").append(posX).append("' y='74' width='")
					.append(GLOBAL_HOUR_REPORT_SUBHEADER_WIDTH).append("' height='")
					.append(GLOBAL_HOUR_REPORT_SUBHEADER_HEIGHT).append("' ").append("key='")
					.append(GLOBAL_HOUR_REPORT_HOURS_FIELD_KEY + l).append("'/>");
			columnHeader.append("<box></box><textElement textAlignment='Center'>").append("<font /></textElement>");
			columnHeader.append("<text><![CDATA[Horas]]></text></staticText>");

			columnHeader.append("<staticText><reportElement x='")
					.append(posX + GLOBAL_HOUR_REPORT_SUBHEADER_WIDTH + GLOBAL_HOUR_REPORT_HOURS_COL_SPACE)
					.append("' y='74' width='").append(GLOBAL_HOUR_REPORT_SUBHEADER_WIDTH).append("' height='")
					.append(GLOBAL_HOUR_REPORT_SUBHEADER_HEIGHT).append("'").append(" key='")
					.append(GLOBAL_HOUR_REPORT_COST_FIELD_KEY + l).append("'/>");
			columnHeader.append("<box></box><textElement textAlignment='Center'>").append("<font /></textElement>");
			columnHeader.append("<text><![CDATA[Coste (€)]]></text></staticText>");

			detail.append("<subreport isUsingCache='true'>");
			detail.append("<reportElement x='").append(posX + GLOBAL_HOUR_REPORT_SUBREPORT_X_MARGIN).append("' y='8' ")
					.append("width='").append(GLOBAL_HOUR_REPORT_SUBREPORT_WIDTH).append("' ").append("height='")
					.append(GLOBAL_HOUR_REPORT_SUBREPORT_HEIGHT).append("' ").append("key='")
					.append(GLOBAL_HOUR_REPORT_SUBREPORT_KEY + l).append("'/>");
			detail.append("<subreportParameter  name='eurosPerHour'>")
					.append("<subreportParameterExpression><![CDATA[new java.lang.Double(")
					// .append(user.getSalaryPerHour())
					.append(new Double(0)).append(")]]></subreportParameterExpression>")
					.append("</subreportParameter>");
			detail.append("<subreportParameter  name='idUser'>")
					.append("<subreportParameterExpression><![CDATA[new java.lang.Integer(").append(user.getId())
					.append(")]]></subreportParameterExpression>").append("</subreportParameter>");
			detail.append("<subreportParameter  name='idProject'>")
					.append("<subreportParameterExpression><![CDATA[$F{project_id}]]></subreportParameterExpression>")
					.append("</subreportParameter>");
			detail.append("<subreportParameter  name='startDate'>")
					.append("<subreportParameterExpression><![CDATA[$P{Fecha inicio}]]></subreportParameterExpression>")
					.append("</subreportParameter>");
			detail.append("<subreportParameter  name='endDate'>")
					.append("<subreportParameterExpression><![CDATA[$P{Fecha fin}]]></subreportParameterExpression>")
					.append("</subreportParameter>");
			detail.append("<subreportParameter  name='billable'>")
					.append("<subreportParameterExpression><![CDATA[$V{billable}]]></subreportParameterExpression>")
					.append("</subreportParameter>");
			detail.append("<connectionExpression><![CDATA[$P{REPORT_CONNECTION}]]></connectionExpression>");
			detail.append("<returnValue subreportVariable='Horas' toVariable='")
					.append(GLOBAL_HOUR_REPORT_TOTAL_HOURS_KEY + l).append("' calculation='Sum'/>");
			detail.append("<returnValue subreportVariable='Coste' toVariable='")
					.append(GLOBAL_HOUR_REPORT_TOTAL_COST_KEY + l).append("' calculation='Sum'/>");
			detail.append(
					"<subreportExpression  class='java.lang.String'><![CDATA[$P{SUBREPORT_ACTIVITIES}]]></subreportExpression>");
			detail.append("</subreport>");

			summary.append(
					"<textField isStretchWithOverflow='false' pattern='#,##0.00' isBlankWhenNull='true' evaluationTime='Now' hyperlinkType='None'  hyperlinkTarget='Self' >");
			summary.append("<reportElement x='").append(posX + GLOBAL_HOUR_REPORT_SUBREPORT_X_MARGIN)
					.append("' y='5' width='").append(GLOBAL_HOUR_REPORT_SUBHEADER_WIDTH).append("' height='")
					.append(GLOBAL_HOUR_REPORT_SUBHEADER_HEIGHT).append("' ").append("key='")
					.append(GLOBAL_HOUR_REPORT_TOTAL_HOURS_KEY + l).append("'/><box></box>");
			summary.append("<textElement></textElement>");
			summary.append("<textFieldExpression   class='java.lang.Double'><![CDATA[$V{")
					.append(GLOBAL_HOUR_REPORT_TOTAL_HOURS_VAR + l).append("}]]></textFieldExpression></textField>");
			summary.append(
					"<textField isStretchWithOverflow='false' pattern='#,##0.00' isBlankWhenNull='true' evaluationTime='Now' hyperlinkType='None'  hyperlinkTarget='Self' >");
			summary.append("<reportElement x='")
					.append(posX + GLOBAL_HOUR_REPORT_SUBREPORT_X_MARGIN + GLOBAL_HOUR_REPORT_SUBHEADER_WIDTH
							+ GLOBAL_HOUR_REPORT_HOURS_COL_SPACE)
					.append("' y='5' width='").append(GLOBAL_HOUR_REPORT_SUBHEADER_WIDTH).append("' height='")
					.append(GLOBAL_HOUR_REPORT_SUBHEADER_HEIGHT).append("' ").append("key='")
					.append(GLOBAL_HOUR_REPORT_TOTAL_COST_KEY + l).append("'/><box></box>");
			summary.append("<textElement ></textElement>");
			summary.append("<textFieldExpression   class='java.lang.Double'><![CDATA[$V{")
					.append(GLOBAL_HOUR_REPORT_TOTAL_COST_VAR + l).append("}]]></textFieldExpression></textField>");

			vars.append("<variable name='").append(GLOBAL_HOUR_REPORT_TOTAL_HOURS_VAR + l)
					.append("' class='java.lang.Double' resetType='Report' calculation='Sum'></variable>");
			vars.append("<variable name='").append(GLOBAL_HOUR_REPORT_TOTAL_COST_VAR + l)
					.append("' class='java.lang.Double' resetType='Report' calculation='Sum'></variable>");

			posX = posX + GLOBAL_HOUR_REPORT_COLUMN_WIDTH + GLOBAL_HOUR_REPORT_SPACE_BETWEEN_COLS;

		}

		columnHeader.append("<staticText><reportElement y='5' x='").append(posX).append("' key='staticText-3' ");
		columnHeader.append("width='").append(GLOBAL_HOUR_REPORT_COLUMN_WIDTH).append("' ").append("height='")
				.append(GLOBAL_HOUR_REPORT_HEADER_HEIGHT).append("' />");
		columnHeader.append(
				"<box></box><textElement textAlignment='Center' verticalAlignment='Middle'><font pdfFontName='Helvetica-Bold' size='14' isBold='true'/></textElement>");
		columnHeader.append("<text><![CDATA[TOTAL ]]></text></staticText>");
		columnHeader.append("<staticText><reportElement x='").append(posX).append("' y='74' width='")
				.append(GLOBAL_HOUR_REPORT_SUBHEADER_WIDTH).append("' height='")
				.append(GLOBAL_HOUR_REPORT_SUBHEADER_HEIGHT).append("' ").append("key='")
				.append(GLOBAL_HOUR_REPORT_HOURS_FIELD_KEY + users.size()).append("'/>");
		columnHeader.append("<box></box><textElement textAlignment='Center'>").append("<font /></textElement>");
		columnHeader.append("<text><![CDATA[Horas]]></text></staticText>");

		columnHeader.append("<staticText><reportElement x='")
				.append(posX + GLOBAL_HOUR_REPORT_SUBHEADER_WIDTH + GLOBAL_HOUR_REPORT_HOURS_COL_SPACE)
				.append("' y='74' width='").append(GLOBAL_HOUR_REPORT_SUBHEADER_WIDTH).append("' height='")
				.append(GLOBAL_HOUR_REPORT_SUBHEADER_HEIGHT).append("'").append(" key='")
				.append(GLOBAL_HOUR_REPORT_COST_FIELD_KEY + users.size()).append("'/>");
		columnHeader.append("<box></box><textElement textAlignment='Center'>").append("<font /></textElement>");
		columnHeader.append("<text><![CDATA[Coste (€)]]></text></staticText>");

		detail.append("<subreport isUsingCache='true'>");
		detail.append("<reportElement x='").append(posX + GLOBAL_HOUR_REPORT_SUBREPORT_X_MARGIN).append("' y='8' ")
				.append("width='").append(GLOBAL_HOUR_REPORT_SUBREPORT_WIDTH).append("' ").append("height='")
				.append(GLOBAL_HOUR_REPORT_SUBREPORT_HEIGHT).append("' ").append("key='")
				.append(GLOBAL_HOUR_REPORT_SUBREPORT_KEY + users.size()).append("'/>");
		detail.append("<subreportParameter  name='idProject'>")
				.append("<subreportParameterExpression><![CDATA[$F{project_id}]]></subreportParameterExpression>")
				.append("</subreportParameter>");
		detail.append("<subreportParameter  name='billable'>")
				.append("<subreportParameterExpression><![CDATA[$V{billable}]]></subreportParameterExpression>")
				.append("</subreportParameter>");
		detail.append("<connectionExpression><![CDATA[$P{REPORT_CONNECTION}]]></connectionExpression>");
		detail.append("<returnValue subreportVariable='Horas' toVariable='")
				.append(GLOBAL_HOUR_REPORT_TOTAL_HOURS_KEY + users.size()).append("' calculation='Sum'/>");
		detail.append("<returnValue subreportVariable='Coste' toVariable='")
				.append(GLOBAL_HOUR_REPORT_TOTAL_COST_KEY + users.size()).append("' calculation='Sum'/>");
		detail.append(
				"<subreportExpression  class='java.lang.String'><![CDATA[$P{SUBREPORT_TOTAL_COST}]]></subreportExpression>");
		detail.append("</subreport>");

		detail.append("<line direction='TopDown'>");
		detail.append("<reportElement x='2' y='1' height='0' key='line-1' width='")
				.append((pageWidth - GLOBAL_HOUR_REPORT_SPACE_BETWEEN_COLS)).append("' />");
		detail.append("<graphicElement stretchType='NoStretch'/></line>");

		summary.append(
				"<textField isStretchWithOverflow='false' pattern='#,##0.00' isBlankWhenNull='true' evaluationTime='Now' hyperlinkType='None'  hyperlinkTarget='Self' >");
		summary.append("<reportElement x='").append(posX + GLOBAL_HOUR_REPORT_SUBREPORT_X_MARGIN)
				.append("' y='5' width='").append(GLOBAL_HOUR_REPORT_SUBHEADER_WIDTH).append("' height='")
				.append(GLOBAL_HOUR_REPORT_SUBHEADER_HEIGHT).append("' ").append("key='")
				.append(GLOBAL_HOUR_REPORT_TOTAL_HOURS_KEY + users.size()).append("'/><box></box>");
		summary.append("<textElement ></textElement>");
		summary.append("<textFieldExpression   class='java.lang.Double'><![CDATA[$V{")
				.append(GLOBAL_HOUR_REPORT_TOTAL_HOURS_VAR + users.size())
				.append("}]]></textFieldExpression></textField>");
		summary.append(
				"<textField isStretchWithOverflow='false' pattern='#,##0.00' isBlankWhenNull='true' evaluationTime='Now' hyperlinkType='None'  hyperlinkTarget='Self' >");
		summary.append("<reportElement x='")
				.append(posX + GLOBAL_HOUR_REPORT_SUBREPORT_X_MARGIN + GLOBAL_HOUR_REPORT_SUBHEADER_WIDTH
						+ GLOBAL_HOUR_REPORT_HOURS_COL_SPACE)
				.append("' y='5' width='").append(GLOBAL_HOUR_REPORT_SUBHEADER_WIDTH).append("' height='")
				.append(GLOBAL_HOUR_REPORT_SUBHEADER_HEIGHT).append("' ").append("key='")
				.append(GLOBAL_HOUR_REPORT_TOTAL_COST_KEY + users.size()).append("'/><box></box>");
		summary.append("<textElement ></textElement>");
		summary.append("<textFieldExpression   class='java.lang.Double'><![CDATA[$V{")
				.append(GLOBAL_HOUR_REPORT_TOTAL_COST_VAR + users.size())
				.append("}]]></textFieldExpression></textField>");

		summary.append("</band>");
		summary.append("</summary>");
		summary.append("</jasperReport>");

		vars.append("<variable name='").append(GLOBAL_HOUR_REPORT_TOTAL_HOURS_VAR + users.size())
				.append("' class='java.lang.Double' resetType='Report' calculation='Sum'></variable>");
		vars.append("<variable name='").append(GLOBAL_HOUR_REPORT_TOTAL_COST_VAR + users.size())
				.append("' class='java.lang.Double' resetType='Report' calculation='Sum'></variable>");

		try {

			pageWidth = (pageWidth < REPORT_A4_LANDSCAPE_MIN_WIDTH) ? REPORT_A4_LANDSCAPE_MIN_WIDTH : pageWidth;

			Hashtable<String, String> replacements = new Hashtable<String, String>();

			replacements.put("@PAGE_WIDTH@", String.valueOf(pageWidth));
			replacements.put("@COL_COUNT@", String.valueOf(colNum));
			replacements.put("@BG_WIDTH@", String.valueOf(pageWidth - 4));
			replacements.put("@DynamicVars@", vars.toString());

			reportHeaderFileName = getResourceAsURL(reportHeaderFileName).getFile();
			reportFooterFileName = getResourceAsURL(reportFooterFileName).getFile();
			reportInterFileName = getResourceAsURL(reportInterFileName).getFile();

			jrxmlHeader = new FileReader(reportHeaderFileName);
			jrxmlFooter = new FileReader(reportFooterFileName);
			jrxmlInter = new FileReader(reportInterFileName);

			// out = new PrintWriter(report);

			sendData(jrxmlHeader, report, replacements);
			sendText(columnHeader.toString(), report);
			sendData(jrxmlInter, report, replacements);
			sendText(detail.toString(), report);
			sendData(jrxmlFooter, report, replacements);
			sendText(summary.toString(), report);

			output = report.toByteArray();

		} catch (Exception t) {
			log.error("Error generando JRXML de parte de horas global", t);

		} finally {

			if (jrxmlHeader != null) {
				try {
					jrxmlHeader.close();
				} catch (IOException e) {
					log.error("Error cerrando fichero de cabecera", e);
				}
			}

			if (jrxmlFooter != null) {
				try {
					jrxmlFooter.close();
				} catch (IOException e) {
					log.error("Error cerrando fichero de pie", e);
				}
			}

			if (jrxmlInter != null) {
				try {
					jrxmlInter.close();
				} catch (IOException e) {
					log.error("Error cerrando fichero intermedio", e);
				}
			}

			if (report != null) {
				try {
					report.close();
				} catch (IOException e) {
					log.error("Error cerrando report", e);
				}
			}
		}

		return output;

	}

	private URI getResourceAsURI(String qualifiedName) {
		try {
			return getResourceAsURL(qualifiedName).toURI();
		} catch (URISyntaxException e) {
			log.error(e);
			return null;
		}
	}

	private URL getResourceAsURL(String qualifiedName) {
		return Thread.currentThread().getContextClassLoader().getResource(qualifiedName);
	}

	private String getResourceAsString(String qualifiedName) {
		return getResourceAsURI(qualifiedName).getPath();
	}

	/**
	 * Sends the data in an Input Stream to an OutputStream
	 * 
	 * @param in
	 * @param out
	 */
	private void sendData(Reader in, OutputStream out, Map<String, String> replacements) throws IOException {

		BufferedReader bufReader = new BufferedReader(in);
		String line;
		StringBuilder sb = new StringBuilder();

		try {
			while ((line = bufReader.readLine()) != null) {

				line = replaceTokens(line, replacements);
				sb.append(line + "\n");

			}

			sendText(sb.toString(), out);

		} catch (Exception e) {
			log.error("Error enviando contenido a salida", e);
		} finally {
			bufReader.close();
		}

	}

	private void sendText(String text, OutputStream out) throws IOException {

		out.write(text.getBytes("UTF-8"));

	}

	private String replaceTokens(String line, Map<String, String> replacements) {

		String result = line;
		Set<String> replacementKeys = replacements.keySet();

		for (String key : replacementKeys) {
			result = result.replaceAll(key, replacements.get(key)); // NOSONAR
		}

		return result;

	}
}
