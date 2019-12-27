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

<%@page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="org.acegisecurity.AccessDeniedException, javax.servlet.ServletException"%>
<%@page import="com.autentia.tnt.util.*,com.autentia.tnt.manager.security.*"%>

<%
// Hack to redirect org.acegisecurity.AccessDeniedException's to other page. This should be done
// by ACEGI filter but this page is invoked first by container.
boolean isAccessDenied = false;
Throwable cause = exception;

while( cause!=null && !isAccessDenied )
{
  isAccessDenied = cause instanceof AccessDeniedException;
  if( cause instanceof ServletException )
  {
    cause = ((ServletException)cause).getRootCause();
  }
  else
  {
    cause = cause.getCause();
  }
}

if( isAccessDenied )
{
  response.sendRedirect(request.getContextPath()+"/notAuthorized.jsf");
}
%>

<%@include file="/inc/tlibs.jsp" %>
<f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />

<html>
  <f:view>
  <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
  <%@include file="/inc/publicHeader.jsp"%>

    <head>
      <%@include file="/inc/uiCore.jsp"%>
      <title>
	<h:outputText value="#{msg['error.title']}" />
      </title>
    </head>
    <body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"	marginwidth="0" marginheight="0">
  <%@include file="/inc/header.jsp" %>
      <h:form>
	<div align="center" style="top:50px">
	  <h:panelGrid  cellpadding="5" cellspacing="20">
	    <h:panelGrid columns="2" columnClasses="msgLabel,msgValue">
	      <h:graphicImage value="/img/error.gif" styleClass="titleImg"/>
	      <h:outputText value="#{msg['error.exceptionProduced']}" styleClass="errorPage"/>
	    </h:panelGrid>
	    
	    <h:panelGrid>
	      <h:commandLink onclick="redirectToBugs();">
		<h:outputText value="#{msg['error.infoBug']}" styleClass="publicVersion"/>
		<h:graphicImage value="/img/inform-bug.gif" styleClass="titleImg" />
	      </h:commandLink>
	    </h:panelGrid>
	    
	    <c:if test="${errorBean.showLogs == true}">
	      <h:inputTextarea style="width:100%" rows="10" cols="130" readonly="true" value="#{errorBean.stackTrace}" />
	    </c:if>
	  </h:panelGrid>
	</div>
      </h:form>
    </body>
  </f:view>
</html>