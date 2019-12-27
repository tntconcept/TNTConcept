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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.olap4j.OlapConnection;
import org.olap4j.OlapStatement;
import org.olap4j.OlapWrapper;
import org.xml.sax.SAXException;

import com.tonbeller.jpivot.mondrian.MondrianModel;
import com.tonbeller.jpivot.olap.model.OlapException;
import com.tonbeller.jpivot.olap.model.OlapModel;
import com.tonbeller.jpivot.tags.MondrianModelFactory;
import com.tonbeller.wcf.controller.RequestContext;

/**
 * Clases de utilidades relacionadas con JPivot, Mondrian y OLAP4J
 * 
 * @author <a href="www.autentia.com">AUTENTIA</a>
 */
public class JPivotUtils {

	private static final Log log = LogFactory.getLog(JPivotUtils.class);

	// nombres de los componentes de JPivot que van a sesión y se referencian en
	// "jpivot.jsp". Se dejan los nombres de los ejemplos de jpivot ya que al
	// cambiarlos dejó de funcionar la exportación a PDF y Excel, debido a que el
	// servlet propio de JPivot compone el nombre de la tabla, gráfica e impresión
	// como "table","chart","print" más el sufijo pasado en el parámetro "cube".
	// Por eso no se pueden cambiar los nombres.
	public static final String QUERY_SESSION_NAME = "query01";

	private static final String TABLE_SESSION_NAME = "table01";

	private static final String NAVI_SESSION_NAME = "navi01";

	private static final String SORTFORM_SESSION_NAME = "sortform01";

	private static final String PRINT_SESSION_NAME = "print01";

	private static final String PRINTFORM_SESSION_NAME = "printform01";

	private static final String CHART_SESSION_NAME = "chart01";

	private static final String CHARTFORM_SESSION_NAME = "chartform01";

	private static final String TOOLBAR_SESSION_NAME = "toolbar01";

	/** Data source de la aplicación */
	private static final String DATA_SOURCE = "jdbc/TNTConcept";

	/**
	 * Ejecuta una query MDX sobre un cubo OLAP utilizando el datasource por defecto. <br>
	 * El resultado lo almacena en el contexto de sesión para que pueda ser recogido y pintado por las etiquetas de JPivot.
	 * 
	 * @param mdxQuery query a ejecutar
	 * @param cubeSchema URL del que representa el cubo OLAP
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws OlapException
	 */
	public static OlapModel executeOlapQuery(String mdxQuery, URL schema, HttpSession session) throws SAXException,
			IOException, OlapException {

		final RequestContext context = RequestContext.instance();

		// final URL schema = JpivotUtils.class.getResource(cubeSchema);//new URL("file:///"+cubeSchema);//

		final MondrianModelFactory.Config cfg = new MondrianModelFactory.Config();
		cfg.setMdxQuery(mdxQuery);
		cfg.setSchemaUrl(schema.toExternalForm());
		cfg.setDataSource(DATA_SOURCE);

		final MondrianModel mondrianModel = MondrianModelFactory.instance(cfg);
		final OlapModel olapModel = (OlapModel)mondrianModel.getTopDecorator();
		olapModel.setLocale(context.getLocale());
		// olapModel.setLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale());
		olapModel.setServletContext(context.getSession().getServletContext());
		// olapModel.setServletContext((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext());

		// olapModel.
		olapModel.initialize();

		if (log.isDebugEnabled()) {
			java.util.Enumeration attributeNames = session.getAttributeNames();
			while (attributeNames.hasMoreElements()) {
				String name = (String)attributeNames.nextElement();
				log.debug("--- name: " + name + "; type:" + session.getAttribute(name).getClass() + " ---");
			}
		}

		// borramos de la sesion los datos que ha metido JPivot
		// y el model de la consulta que volveremos a meter
		// Esto es necesario porque los tags de JPivot no machacan los
		// valores si ya están en sesión; por lo que al cambiar de informe
		// sigue mostrando el informe selecionado previamente
		session.removeAttribute(JPivotUtils.QUERY_SESSION_NAME);
		session.removeAttribute(JPivotUtils.TABLE_SESSION_NAME);
		session.removeAttribute(JPivotUtils.NAVI_SESSION_NAME);
		session.removeAttribute(JPivotUtils.SORTFORM_SESSION_NAME);
		session.removeAttribute(JPivotUtils.PRINT_SESSION_NAME);
		session.removeAttribute(JPivotUtils.PRINTFORM_SESSION_NAME);
		session.removeAttribute(JPivotUtils.CHART_SESSION_NAME);
		session.removeAttribute(JPivotUtils.CHARTFORM_SESSION_NAME);
		session.removeAttribute(JPivotUtils.TOOLBAR_SESSION_NAME);

		session.setAttribute(JPivotUtils.QUERY_SESSION_NAME, olapModel);
		return olapModel;
	}

	/**
	 * Ejecuta una query MDX sobre un cubo OLAP utilizando el datasource por defecto. <br>
	 * El resultado lo almacena en el contexto de sesión para que pueda ser recogido y pintado por las etiquetas de JPivot.
	 * 
	 * @param mdxQuery query a ejecutar
	 * @param cubeSchema esquema situado en src/main/resources que representa el cubo OLAP
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws OlapException
	 */
	public static OlapModel executeOlapQuery(String mdxQuery, String cubeSchema, HttpSession session)
			throws SAXException, IOException, OlapException {
		final URL schema = JPivotUtils.class.getResource(cubeSchema);
		return executeOlapQuery(mdxQuery, schema, session);
	}

	/**
	 * Crea una conexión con el datasource por defecto sobre el cubo OLAP y ejecuta la query devolviendo el resultado como
	 * <b>ResultSet</b>
	 * 
	 * @param mdxQuery query a ejecutar
	 * @param cubeSchema esquema situado en src/main/resources que representa el cubo OLAP
	 * @return un objeto ResultSet con la consulta realizada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet getResultSet(String mdxQuery, String cubeSchema) throws ClassNotFoundException,
			SQLException {
		Class.forName("mondrian.olap4j.MondrianOlap4jDriver");
		final URL url = JPivotUtils.class.getResource(cubeSchema);
		final String catalog = url.toExternalForm();

		final Connection conn = DriverManager.getConnection("jdbc:mondrian:DataSource=java:comp/" + DATA_SOURCE
				+ "/galileoDS;Catalog=" + catalog + ";");
		final OlapWrapper wrapper = (OlapWrapper)conn;
		final OlapConnection olConn = wrapper.unwrap(OlapConnection.class);
		final OlapStatement statement = olConn.createStatement();

		return statement.executeOlapQuery(mdxQuery);
	}
}
