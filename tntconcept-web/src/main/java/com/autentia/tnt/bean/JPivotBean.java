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

package com.autentia.tnt.bean;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.manager.report.ReportManager;
import com.autentia.tnt.util.JPivotUtils;
import com.tonbeller.jpivot.olap.model.OlapModel;

public class JPivotBean {

	private static final Log log = LogFactory.getLog(JPivotBean.class);
	
	private OlapModel olapModel;

	private URL cubeURL;
	
	public JPivotBean () {
		showTable();
	}
	
	public OlapModel getOlapModel() {
		return olapModel;
	}

	public void setOlapModel(OlapModel olapModel) {
		this.olapModel = olapModel;
	}
	
	public String showTable() {
		try 
		{
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			File file = new File(loader.getResource("com/autentia/tnt/jpivot/jpivot_test.xml").toURI());
			cubeURL = file.toURL();
			
			String mdxQuery = "select {[Measures].[Muestras]} on columns , {([Contacto])} on rows from [Informe]";
			
			this.olapModel = JPivotUtils.executeOlapQuery(mdxQuery, cubeURL, (HttpSession)FacesContext
					.getCurrentInstance().getExternalContext().getSession(false));
		} 
		catch (Exception e) 
		{
			log.error("cannot generate the jpivot cube: ", e);
			return "error";
		}

		return "jpivot";
	}	
}
