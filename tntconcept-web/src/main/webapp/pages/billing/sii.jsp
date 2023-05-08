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
<i:location place="sii" msg="${msg}" />

<f:view>
    <%@include file="/inc/header.jsp" %>
    <i:titleBar msg="${msg}">
        <h:form id="sii">
            <h:panelGrid columns="6" cellpadding="5" cellspacing="5" >

                <h:outputText styleClass="info" value="#{msg['bill.selectType']}" />
                <h:selectOneMenu id="allTypes" title="#{msg['bill.selectType']}" value="#{siiBean.selectedType}">
                    <f:selectItems value="#{siiBean.billTypes}" />
                    <f:converter converterId="autentia.EnumConverter"/>
                </h:selectOneMenu>

                <h:panelGroup>
                    <h:message styleClass="error" showSummary="true" showDetail="false" for="startDate" />
                    <t:inputCalendar id="startDate" styleClass="requiredFieldClass" value="#{siiBean.startDate}"  required="true"
                                     renderAsPopup="true" popupDateFormat="dd/MM/yyyy" renderPopupButtonAsImage="true"
                                     popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"/>
                </h:panelGroup>

                <h:panelGroup>
                    <h:message styleClass="error" showSummary="true" showDetail="false" for="endDate" />
                    <t:inputCalendar id="endDate" styleClass="requiredFieldClass" value="#{siiBean.endDate}"  required="true"
                                     renderAsPopup="true" popupDateFormat="dd/MM/yyyy" renderPopupButtonAsImage="true"
                                     popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"/>
                </h:panelGroup>

            </h:panelGrid>

            <h:panelGrid columns="3" cellpadding="5" cellspacing="5" >
                <h:outputText styleClass="info" value="Destinatario/s"/>
                <h:inputText style="width: 312px" value="#{siiBean.to}"/>

                <h:commandButton actionListener="#{siiBean.sendReport()}" title="Enviar informe por correo"
                                 value="Enviar informe por correo" />
            </h:panelGrid>

            <h:panelGrid columns="2" cellpadding="5" cellspacing="5" >
                <h:outputText styleClass="info" value="Descargar informe para comprobar que todo está correcto"/>

                <h:commandButton actionListener="#{siiBean.downloadReport()}"
                                 title="Comprobar informe" value="Comprobar informe" />
            </h:panelGrid>

        </h:form>
    </i:titleBar>
    <h:messages style="color:red" globalOnly="true" />
</f:view>

</body>
</html>

