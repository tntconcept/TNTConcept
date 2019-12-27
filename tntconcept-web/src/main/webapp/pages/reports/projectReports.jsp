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

<%@include file="/inc/tlibs.jsp"%>

<html>
<head>
<%@include file="/inc/uiCore.jsp"%>
</head>
<body>

<f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
<i:location place="projectReports" msg="${msg}" />

<f:view>
  <%@include file="/inc/header.jsp" %>
	<c:if test="${projectReportBean.launch}">
		<script>
				openReportParameters("project/${projectReportBean.selectedReport}.${projectReportBean.format}","${projectReportBean.parameters}","${projectReportBean.selectMany}");
        	</script>
	</c:if>
	<%-- Report arguments user interface --%>
	<h:form id="projectReports">

		<%-- Header --%>
		<i:titleBar msg="${msg}">
			<h:commandLink action="#{projectReportBean.run}">
				<h:graphicImage value="/img/run.png" styleClass="titleImg" />
			</h:commandLink>
		</i:titleBar>

		<%-- Fixed Arguments --%>
		<h:panelGrid columns="2" cellpadding="0" cellspacing="0"
			styleClass="editTable" columnClasses="editLabelRW,editFieldCell">

			<h:outputText value="#{msg['projectReports.report']}:" />
			<h:selectOneMenu id="projectReport"
				value="#{projectReportBean.selectedReport}" required="true"
				immediate="true"
				valueChangeListener="#{projectReportBean.selectedReportChanged}"
				onchange="submit();">
				<f:selectItems value="#{projectReportBean.reports}" />
			</h:selectOneMenu>

			<h:outputText value="#{msg['projectReports.format']}:" />
			<h:selectOneMenu id="reportFormat"
				value="#{projectReportBean.format}" required="true">
				<f:selectItems value="#{projectReportBean.formats}" />
			</h:selectOneMenu>

		</h:panelGrid>

		<%-- Variable Arguments --%>
		<h:dataTable id="params" var="reportParameter"
			value="#{projectReportBean.reportParametersDefinitions}"
			columnClasses="editLabelRW,editFieldCell" styleClass="editTable"
			cellpadding="0" cellspacing="0">

			<%-- Label column --%>
			<h:column id="label">
				<h:outputText value="*#{reportParameter.label}:"
					rendered="#{reportParameter.hiddenType != true}"></h:outputText>
			</h:column>

			<%-- Data column --%>
			<h:column id="data">
				<h:panelGroup id="info"
					rendered="#{reportParameter.infoType == true}">
					<h:outputText value="#{reportParameter.value}"></h:outputText>
				</h:panelGroup>

				<h:panelGroup id="text"
					rendered="#{reportParameter.textType == true}">
					<h:inputText value="#{reportParameter.value}" size="20"
						required="true"></h:inputText>
				</h:panelGroup>
				<h:panelGroup id="hidden"
					rendered="#{reportParameter.hiddenType == true}">
					<h:inputHidden value="#{reportParameter.value}"></h:inputHidden>
				</h:panelGroup>
				
				<h:panelGroup id="selectOneSelectManys" rendered="#{reportParameter.selectOneSelectManyType == true}">
             <h:message styleClass="error" showSummary="true" showDetail="false" for="selectMany2" />
              <h:selectOneMenu id="selectOneOrg"  immediate="true" onchange="submit();"
                 value="#{projectReportBean.selectedOrganization.id}" required="true" 
                 valueChangeListener="#{projectReportBean.selectedOrganizationChanged}">
                <f:selectItems value="#{reportParameter.items}" />               
              </h:selectOneMenu>
              <f:verbatim><br/></f:verbatim>
              <h:selectManyListbox size="4" id="selectMany2" value="#{reportParameter.valueMany}" required="#{reportParameter.isRol == false}"
              rendered="#{reportParameter.isRol == false}"  disabled="#{reportParameter.isRol == true}">
                <f:selectItems value="#{reportParameter.items2}" />
              </h:selectManyListbox>
              <h:selectOneMenu id="selectOneProject" value="#{reportParameter.value}"  onchange="submit();"
              rendered="#{reportParameter.isRol == true}" required="#{reportParameter.isRol == true}" disabled="#{reportParameter.isRol == false}"
               valueChangeListener="#{projectReportBean.selectedProjectChanged}">
               <f:selectItems value="#{reportParameter.items2}"/>
               </h:selectOneMenu>
            </h:panelGroup>

				<h:panelGroup id="date"
					rendered="#{reportParameter.dateType == true}">
					               <h:message styleClass="error" showSummary="true" showDetail="false" for="paramDate" />
					<t:inputCalendar id="paramDate" renderPopupButtonAsImage="true"
						value="#{reportParameter.dateValue}" size="12"
						renderAsPopup="true" popupDateFormat="dd/MM/yyyy" required="true"
						popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
						 styleClass="requiredFieldClass">
					</t:inputCalendar>
				</h:panelGroup>
				            <h:panelGroup id="selectOnes" rendered="#{reportParameter.selectOneType == true}">
              <h:selectOneMenu id="selectOne" value="#{reportParameter.value}" required="true">
                <f:selectItems value="#{reportParameter.items}" />
              </h:selectOneMenu>
            </h:panelGroup>


				<h:panelGroup id="selectManys"
					rendered="#{reportParameter.selectManyType == true}">
					<h:selectManyListbox size="8" id="selectMany"
						value="#{reportParameter.valueMany}" required="true">
						<f:selectItems value="#{reportParameter.items}" />
					</h:selectManyListbox>
				</h:panelGroup>

			</h:column>

		</h:dataTable>

	</h:form>
</f:view>
</body>
</html>









