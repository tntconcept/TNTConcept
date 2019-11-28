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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Tag to render a link to an attached file. If the file is empty nothing is rendered.
 * This tag relies on a JS function with signature openFile(type,id,file,mime) that 
 * must be added by hand to the HTML page (for user's convenience, there is a default
 * implementation of this function in uiCore.jsp when inside the intraweb project).
 * @author ivan
 */
public class ActivityImageFileLinkTag extends TagSupport {

	private Date insertionDate;

	private String fileName;

	private String mime;

	private boolean rendered;
	
	@Override
	public void release() {
		super.release();
		insertionDate = null;
		fileName = null;
		mime = null;
		rendered = false;
	}

	@Override
	public int doStartTag() throws JspException 
	{
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		JspWriter out = pageContext.getOut();
		
		try
		{
			if(rendered && fileName!=null && !fileName.equals(""))
			{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(insertionDate);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;

				out.print("<a href=\"#\" onclick=\"openFile(");
				out.print("'"+year+"',");
				out.print(month+",");
				out.print("'"+fileName + ".jpg"+"',");
				out.println("'"+mime+"');return false;\">");
				
				out.print("<img src='"+request.getContextPath()+"/img/yellow-folder-open.png'"); 
				out.println(" style='border:0; vertical-align:middle;'>");

				out.print("Imagen.jpg");
				out.print("</a>");
				out.print("<br/>"); 
			}
		}
		catch( IOException e )
		{
			throw new JspException("Error rendering fileLink",e);
		}

		return EVAL_PAGE;
	}

	public Date getInsertionDate() {
		return insertionDate;
	}

	public void setInsertionDate(Date insertionDate) {
		this.insertionDate = insertionDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}
}
