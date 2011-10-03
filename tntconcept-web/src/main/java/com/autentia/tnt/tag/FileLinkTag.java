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

import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * Tag to render a link to an attached file. If the file is empty nothing is rendered.
 * This tag relies on a JS function with signature openFile(type,id,file,mime) that 
 * must be added by hand to the HTML page (for user's convenience, there is a default
 * implementation of this function in uiCore.jsp when inside the intraweb project).
 * @author ivan
 */
public class FileLinkTag extends TagSupport 
{
	/** */
	private static final long serialVersionUID = -8676144583207734318L;

	/** Type of object the file is attached to */
	private String type;
	
	/** Id of object the file is attached to */
	private int objectId;
	
	/** File name */
	private String file;
	
	/** File mime type */
	private String mime;
	
	@Override
	public void release() {
		super.release();
		type = null;
		objectId = 0;
		file = null;
		mime = null;
	}

	@Override
	public int doStartTag() throws JspException 
	{
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		JspWriter out = pageContext.getOut();
		
		try
		{
			if( file!=null && !file.equals("") )
			{
				out.print("<a href=\"#\" onclick=\"openFile(");
				out.print("'"+type+"',");
				out.print(objectId+",");
				out.print("'"+file+"',");
				out.println("'"+mime+"');return false;\">");
				
				out.print("<img src='"+request.getContextPath()+"/img/yellow-folder-open.png'"); 
				out.println(" style='border:0; vertical-align:middle;'>");

				out.print(" "+file);
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

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
