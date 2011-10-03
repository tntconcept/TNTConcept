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

package com.autentia.tnt.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ImgTag extends TagSupport {
	/** Type of object the file is attached to */
	private String type;
	
	/** Id of object the file is attached to */
	private int objectId;
	
	/** File name */
	private String file;
	
	
	
	@Override
	public void release() {
		super.release();
		type = null;
		objectId = 0;
		file = null;
		
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
				out.print("<img src=\""+request.getContextPath()+"/doc/"+type+"/"+objectId+"/"+file+"\">");				
			}
		}
		catch( IOException e )
		{
			throw new JspException("Error rendering imgTag",e);
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
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
