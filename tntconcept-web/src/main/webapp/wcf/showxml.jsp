<%--

    TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
    Copyright (C) 2007 Autentia Real Bussiness Solution S.L.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>

<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE ROOT [
<!ENTITY nbsp "&#160;">
<!ENTITY Uuml "&#220;">
<!ENTITY uuml "&#252;">
<!ENTITY Auml "&#196;">
<!ENTITY auml "&#228;">
<!ENTITY Ouml "&#214;">
<!ENTITY ouml "&#246;">
<!ENTITY szlig "&#223;">
]>
<%@ page
  session="true"
  contentType="text/xml; charset=UTF-8" pageEncoding="ISO-8859-1"
  import="javax.servlet.http.*" %>

<%@ taglib prefix="wcf" uri="http://www.tonbeller.com/wcf" %>

<%--
  Displays XML of WCF Components in the Browser. Place a link
  like <a href="../wcf/showxml.jsp?render=myComponentId&amp;iehack=file.xml">Show XML of myComponentId</a>
  on the HTML Page
--%>

<%
  HttpServletRequest hsr = (HttpServletRequest)pageContext.getRequest();
  String id = hsr.getParameter("render");
  Object o = pageContext.getSession().getAttribute(id);
  pageContext.setAttribute("renderXML", o, PageContext.REQUEST_SCOPE);
%>

<ROOT>
  <wcf:render id="renderXML" ref="renderXML" xslUri="/WEB-INF/wcf/showxml.xsl" xslCache="true"/>
</ROOT>
