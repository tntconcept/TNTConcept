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
package com.autentia.tnt.tag;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * Tag to render a standard page header. Relies on title, titleText and titleImg
 * styles. Relies on showHelp() javascript function (defined in uiCore.jsp for
 * convenience).
 * 
 * @author ivan
 */
public class TitleBarTag extends BodyTagSupport {
	/** */
	private static final long serialVersionUID = 4428343123317969703L;

	/** Maximum length of name string */
	private static final int MAX_NAME_CHARS = 50;

	/**
	 * Logical location in application. If this attribute is not specified, it
	 * is taken from the request, in a bean under name "location". This bean is
	 * usually created by i:location tag.
	 */
	private String location;

	/** Name of object to be displayed in the title */
	private String name;

	/** Resource bundle with localized messages */
	private Map msg;

	@Override
	public void release() {
		super.release();
		location = null;
		name = null;
		msg = null;
	}

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		JspWriter out = pageContext.getOut();

		// Init location if not provided
		if (location == null) {
			location = (String) request.getAttribute(LocationTag.LOCATION_ATTR);
			if (location == null) {
				throw new JspException(
						"titleBarTag: no explicit location provided nor location found in request");
			}
		}

		// Render tag
		try {
			out.println("<div class='title'>");

			out.print("<span class='titleText'>");
			out.print(msg.get(location + ".title"));
			if (name != null) {
				out.print(" [");
				out.print(name.length() > MAX_NAME_CHARS ? (name.substring(0,
						MAX_NAME_CHARS) + "...") : name);
				out.print("]");
			}
			out.print("</span>");
		} catch (IOException e) {
			throw new JspException("Error rendering titleBar", e);
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	@Override
	public int doAfterBody() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		JspWriter out = pageContext.getOut();
		String pathHelp = "";

		try {
			pathHelp = request.getContextPath() + "/help/help.jsf#" + location;
			out.println("<a href='javascript:showHelp(\"" + pathHelp + "\")'>");
			out.println("<img src='" + request.getContextPath()
					+ "/img/help.gif' class='titleImg' title='"
					+ msg.get("entityActions_help") + "'/>");
			out.println("</a>");
			out.println("</div>");
		} catch (IOException e) {
			throw new JspException("Error rendering titleBar", e);
		}

		return Tag.SKIP_BODY;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Map getMsg() {
		return msg;
	}

	public void setMsg(Map msg) {
		this.msg = msg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
