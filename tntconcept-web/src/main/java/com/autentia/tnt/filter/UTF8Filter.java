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

package com.autentia.tnt.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * Filtro para que la aplicación acepte codificación en formato UTF-8
 *
 */

public class UTF8Filter implements Filter
{
  private static final Log log = LogFactory.getLog(UTF8Filter.class);
  
  private String encoding;
  private String contentType;
  
  /**
   * Recogemos el tipo de codificación definido en el web.xml Si no se hubiera
   * especificado ninguno se toma "UTF-8" por defecto
   */
  
  public void init(FilterConfig cfg) throws ServletException
  {
    encoding = cfg.getInitParameter("requestEncoding");
    if (encoding == null)
    {
      encoding = "UTF-8";
    }
    contentType = "text/html; charset=" + encoding;
    
    log.warn("init - forcing content type to '"+contentType+"' for resources filtered by UTF8Filter (see web.xml)" );
  }
  
  /**
   * Metemos en la request el formato de codificacion UTF-8
   */
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain fc) throws IOException, ServletException
  {
    response.setContentType(contentType);
    request.setCharacterEncoding(encoding);
    fc.doFilter(request, response);
  }
  
  public void destroy()
  {
  }
  
}