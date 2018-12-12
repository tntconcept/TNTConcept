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
	    <head>
	        <%@include file="/inc/uiCore.jsp" %>
	        <meta HTTP-EQUIV="Content-Type" CONTENT="text/html;charset=iso-8859-1">
	        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilos.css" />
	        <%@include file="/inc/publicHeader.jsp"%>
	    </head>
    	<body>    
        
			<div id="expirePassword" align="center">
				<h:panelGrid cellpadding="5" cellspacing="20">
			      <h:outputText value="#{msg['passwordUpdatedOk']}" />
			    </h:panelGrid>
				<h:panelGrid cellpadding="5" cellspacing="20">
			      <h:form>
	        		  <h:commandLink action="ok" title="#{msg.bot_aceptar}"><h:outputText value="#{msg['bot_aceptar']}" /></h:commandLink>
	          	  </h:form>
	        	</h:panelGrid>
			</div>
        
		</body>
	</f:view>
</html>  