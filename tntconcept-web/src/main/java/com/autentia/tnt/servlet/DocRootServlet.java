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

/**
 * 
 */
package com.autentia.tnt.servlet;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.logging.*;

import com.autentia.tnt.util.*;

/**
 * @author paco
 *
 */
public class DocRootServlet extends HttpServlet {

	/** */
	private static final long serialVersionUID = -6588318861276755992L;
	
	/** Logger */
	private static final Log log = LogFactory.getLog(DocRootServlet.class);

	public static final String ARG_MIME = "mime";
	public static final String URL_PREFIX = "/docroot/";
	
	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	{

		String uri = request.getRequestURI();
		log.debug("doGet - uri='"+uri+"'");
		
		int i = uri.indexOf(URL_PREFIX);
		if( i!=-1 )
		{
			String relPath = uri.substring( i+URL_PREFIX.length() );
			
			relPath = URLDecoder.decode(relPath,"UTF-8");
			log.debug("doGet - relPath='"+relPath+"'");
			
			File f = new File( ConfigurationUtil.getDefault().getDocumentRootPath()+relPath );
			if( f.exists() )
			{
				response.setContentLength((int) f.length());
			
				String mime = request.getParameter(ARG_MIME);
				if (mime!=null && !mime.equals("")) {
					response.setContentType(mime);
				}
				OutputStream out = response.getOutputStream();
				InputStream in = null;
				try {
					in = new FileInputStream(f);
					byte[] buffer = new byte[8192];
					int nr;
					while( (nr=in.read(buffer))!=-1 ){
						out.write(buffer,0,nr);
					}
				} finally{
					if(in!=null){
						in.close();
					}
				}
			}
			else
			{
				response.sendError( HttpServletResponse.SC_NOT_FOUND );
			}
		}
		else
		{
			response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
									"Bad URL prefix for servlet: check your web.xml file" );
		}
	}

	/*
	@Override
	protected long getLastModified(HttpServletRequest request) 
	{
		String uri = request.getRequestURI();
		int i = uri.indexOf(URL_PREFIX);
		if( i!=-1 )
		{
			String relPath = uri.substring( i+URL_PREFIX.length() );
			File f = new File( ConfigurationUtil.getUploadPath()+relPath );
			return f.lastModified();
		}
		return 0;
	}
	*/
}









