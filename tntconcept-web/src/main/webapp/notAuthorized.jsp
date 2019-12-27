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

<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.autentia.tnt.util.*,com.autentia.tnt.manager.security.*"%>
<%@include file="/inc/tlibs.jsp" %>

<f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />

<html>
  <f:view>
  <%
  boolean loggedIn = (AuthenticationManager.getDefault().getCurrentPrincipal()!=null);  
  
  if(!loggedIn) {
  %>  
   <%@include file="/inc/publicHeader.jsp"%>
  <% } else { %>
   <%@include file="/privateIcons.jsp"%>
  <% } %>
  
    <head>
      <%@include file="/inc/uiCore.jsp"%>
      <title>
	<h:outputText value="#{msg['error.title']}" />
      </title>
    </head>
    <body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"	marginwidth="0" marginheight="0">
      <div align="center" style="top:50px">
	  <h:panelGrid cellpadding="5" cellspacing="20">
	    <h:panelGrid columns="2" columnClasses="msgLabel,msgValue">
	      <h:graphicImage value="/img/error.gif" styleClass="titleImg"/>
	      <h:outputText value="#{msg['error.notAuthorized']}" styleClass="errorPage"/>
	    </h:panelGrid>	    
	  </h:panelGrid>
	</div> 
	
	<div align="center" style="top:50px">
			<h:form>
		   		<h:commandButton id="link" action="#{linkBean.goPasswordChange}" value="¿Quieres restablecer la contraseña?"/>
		    </h:form>
   	</div>

    </body>
  </f:view>
</html>


