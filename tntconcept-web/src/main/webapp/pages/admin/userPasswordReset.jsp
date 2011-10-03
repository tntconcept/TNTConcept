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

<%@include file="/inc/tlibs.jsp" %>

<html>
  <head>
    <%@include file="/inc/uiCore.jsp" %>
  </head>	
  <body>
    
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    <i:location place="editUser" msg="${msg}" /> 
    
    <f:view>
     <%@include file="/inc/header.jsp" %>
      <h:form>
      
      <%-- Header --%>
      <i:titleBar name="${userBean.name}" msg="${msg}">
        <h:commandLink action="#{userBean.list}" immediate="true">
          <h:graphicImage title="#{msg.entityActions_back}"  value="/img/back.gif" styleClass="titleImg" />
        </h:commandLink>
      </i:titleBar>

        <h:panelGrid columns="1" styleClass="listTable" columnClasses="listHeader">
          <h:outputText value="#{msg['userPasswordReset.preMessage']}"/>
          <h:outputText value="#{userBean.changedPassword}" styleClass="userPasswordResetPassword"/>
          <h:outputText value="#{msg['userPasswordReset.postMessage']}"/>
        </h:panelGrid>
        
      </h:form>
    </f:view>
    
  </body>
</html>  		

