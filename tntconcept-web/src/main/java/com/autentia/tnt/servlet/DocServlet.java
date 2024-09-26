/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 */
package com.autentia.tnt.servlet;

import com.autentia.tnt.util.ConfigurationUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author ivan
 */
public class DocServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -6588318861276755992L;

    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(DocServlet.class);

    public static final String ARG_MIME = "mime";
    public static final String URL_PREFIX = "/doc/";


    private String removeServletPrefixFromPath(String requestedUri) throws UnsupportedEncodingException {
        String relPath = requestedUri.substring(requestedUri.indexOf(URL_PREFIX) + URL_PREFIX.length());
        return URLDecoder.decode(relPath, StandardCharsets.UTF_8);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String requestURI = request.getRequestURI();
        String relPath = removeServletPrefixFromPath(requestURI);

        log.debug("doGet - relPath='" + relPath + "'");

        DocumentFinder.RequestedDocumentFile requestedDocumentFile = DocumentFinder.getDocumentFinder().search(ConfigurationUtil.getDefault().getUploadPath() + relPath);

        if (requestedDocumentFile.requestedFile().exists()) {
            if (requestedDocumentFile.original()) {
                writeFile(request, response, requestedDocumentFile.requestedFile());
            } else {
                final String uriParent = requestURI.substring(0, requestURI.lastIndexOf("/"));
                response.sendRedirect(uriParent + "/" + requestedDocumentFile.requestedFile().getName());
            }

        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private static void writeFile(HttpServletRequest request, HttpServletResponse response, File theRequestedFile) throws IOException {
        response.setContentLength((int) theRequestedFile.length());
        String mime = request.getParameter(ARG_MIME);
        if (mime != null && !mime.isEmpty()) {
            response.setContentType(mime);
        }
        FileUtils.copyFile(theRequestedFile, response.getOutputStream());
    }

}
