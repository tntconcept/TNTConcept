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

<!-- objectives.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
<i:location place="externalActivities" msg="${msg}" />

<f:view>
  <%@include file="/inc/header.jsp" %>
	<h:form id="externalActivities">

		<%-- Header --%>
		<i:titleBar msg="${msg}">
			<h:commandLink action="#{externalActivityBean.create}">
				<h:graphicImage value="/img/new.gif" styleClass="titleImg" />
			</h:commandLink>
		</i:titleBar>
		<h:panelGrid columns="2" cellpadding="0" cellspacing="0" styleClass="editTable" 
		 columnClasses="editLabelRW,editFieldCell" rendered="#{objectiveBean.filtrableUser}">
		
		<h:outputText value="#{msg['objectives.filterByUser']}:" />
		<t:selectOneMenu id="filtrable" value="#{objectiveBean.userSelected}" 
					valueChangeListener="#{objectiveBean.onSelectedUserChanged}" onchange="submit()" immediate="true" styleClass="requiredFieldClass">
	              	<f:selectItems value="#{objectiveBean.filtrableUsers}"></f:selectItems>	              	
	     </t:selectOneMenu>
		</h:panelGrid>
		<%-- List of past non-complete objectives --%>
		<br />
		<div class='title'><span class='titleText'>
		${msg['objectives.pastNotCompleted']} </span></div>
		<t:dataTable id="listPastNotCompleted" var="objective"
			value="#{objectiveBean.pastNotCompleted}" preserveDataModel="false"
			cellpadding="0" cellspacing="0" styleClass="listTable"
			headerClass="listHeaderCell" footerClass="listFooter" rows="#{settingBean.mySettings.listSize}"
			rowClasses="listRowO,listRowE"
			columnClasses="listCmdCell,listObjectiveState,listObjectiveUser,listObjectiveProject,listObjectiveProject,listObjectiveName,listObjectiveStartDate,listObjectiveEndDate"
			sortColumn="#{objectiveBean.sortColumn}"
			sortAscending="#{objectiveBean.sortAscending}">

			<%-- Commands --%>
			<h:column>
				<f:facet name="header">
					<f:verbatim>-</f:verbatim>
				</f:facet>
				<t:commandLink action="#{objectiveBean.propagate}" immediate="true">
					<f:param name="rowid" value="#{objective.id}" />
					<h:graphicImage value="/img/propagate.png" styleClass="cmdImg" />
				</t:commandLink>
				<t:commandLink action="#{objectiveBean.complete}" immediate="true"
					rendered="#{objective.pending}">
					<f:param name="rowid" value="#{objective.id}" />
					<h:graphicImage value="/img/complete.png" styleClass="cmdImg" />
				</t:commandLink>
				<t:commandLink action="#{objectiveBean.detail}" immediate="true">
              		<f:param name="rowid" value="#{objective.id}" />
              		<h:graphicImage title="#{msg.entityActions_detail}"  value="/img/detail.gif" styleClass="cmdImg" />
           		 </t:commandLink>
			</h:column>

			<%-- Ignored field: id --%>

			<%-- Field: state --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="state">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.state']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>
				<%-- Enumerated field --%>
				<h:outputText value="#{objective.state}">
					<f:converter converterId="autentia.EnumTranslator" />
				</h:outputText>
			</h:column>

			
			<%-- Field: user --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="user.name">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.user']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>

				<%-- Referenced field --%>
				<h:outputText value="#{objective.user.name}" />


			</h:column>
			
			
			<%-- Field: organization --%>
			<h:column>
				<f:facet name="header">
				<t:commandSortHeader styleClass="listHeader" columnName="project.client.name">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.organization']}</f:verbatim>
					</t:commandSortHeader>
							</f:facet>
				<%-- Referenced field --%>
				<h:outputText value="#{objective.project.client.name}" />
			</h:column>
			
			
			<%-- Field: project --%>
			
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="project.name">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.project']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>
				<%-- Referenced field --%>
				<h:outputText value="#{objective.project.name}" />
			</h:column>
			
			
			
			
			<%-- Field: name --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="name">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.name']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>
				<%-- String field --%>
				<h:outputText value="#{objective.name}">
					<f:converter converterId="autentia.CutStringTranslator"/>
				</h:outputText>
			</h:column>

			<%-- Field: startDate --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="startDate">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.startDate']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>
				<%-- Date field --%>
				<h:outputText value="#{objective.startDate}"
					converter="autentia.dateConverter" />
			</h:column>

			<%-- Field: endDate --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="endDate">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.endDate']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>
				<%-- Date field --%>
				<h:outputText value="#{objective.endDate}"
					converter="autentia.dateConverter" />
			</h:column>
			
			
			
		</t:dataTable>
		
		<%-- Paginator control --%>
		<t:dataScroller for="listPastNotCompleted" fastStep="10" paginator="true"
			paginatorMaxPages="10" renderFacetsIfSinglePage="false"
			styleClass="scroller" paginatorTableClass="paginator"
			paginatorColumnClass="paginatorCell"
			paginatorActiveColumnClass="paginatorActiveCell">
			<f:facet name="first">
				<t:graphicImage value="/img/arrow-first.gif" border="0" />
			</f:facet>
			<f:facet name="last">
				<t:graphicImage value="/img/arrow-last.gif" border="0" />
			</f:facet>
			<f:facet name="previous">
				<t:graphicImage value="/img/arrow-previous.gif" border="0" />
			</f:facet>
			<f:facet name="next">
				<t:graphicImage value="/img/arrow-next.gif" border="0" />
			</f:facet>
			<f:facet name="fastrewind">
				<t:graphicImage value="/img/arrow-fr.gif" border="0" />
			</f:facet>
			<f:facet name="fastforward">
				<t:graphicImage value="/img/arrow-ff.gif" border="0" />
			</f:facet>
		</t:dataScroller>

		<%-- List of current week objectives --%>
		<br />
		<div class='title'><span class='titleText'>
		${msg['objectives.current']} </span></div>
		<t:dataTable id="listThisWeek" var="objective"
			value="#{objectiveBean.current}" preserveDataModel="false"
			cellpadding="0" cellspacing="0" styleClass="listTable"
			headerClass="listHeaderCell" footerClass="listFooter" rows="#{settingBean.mySettings.listSize}"
			rowClasses="listRowO,listRowE"
			columnClasses="listCmdCell,listObjectiveState,listObjectiveUser,listObjectiveProject,listObjectiveProject,listObjectiveName,listObjectiveStartDate,listObjectiveEndDate"
			sortColumn="#{objectiveBean.sortColumn}"
			sortAscending="#{objectiveBean.sortAscending}">

			<%@page language="java" contentType="text/html; charset=UTF-8"%>

			<%@include file="/inc/tlibs.jsp"%>

			<%-- Commands --%>
			<h:column>
				<f:facet name="header">
					<f:verbatim>-</f:verbatim>
				</f:facet>
				<t:commandLink action="#{objectiveBean.detail}" immediate="true">
              <f:param name="rowid" value="#{objective.id}" />
              <h:graphicImage title="#{msg.entityActions_detail}"  value="/img/detail.gif" styleClass="cmdImg" />
            </t:commandLink>
				<t:commandLink action="#{objectiveBean.complete}" immediate="true"
					rendered="#{objective.pending}">
					<f:param name="rowid" value="#{objective.id}" />
					<h:graphicImage value="/img/complete.png" styleClass="cmdImg" />
				</t:commandLink>
			</h:column>

			<%-- Ignored field: id --%>

			<%-- Field: state --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="state">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.state']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>
				<%-- Enumerated field --%>
				<h:outputText value="#{objective.state}">
					<f:converter converterId="autentia.EnumTranslator" />
				</h:outputText>
			</h:column>

			

			<%-- Field: user --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="user.name">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.user']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>

				<%-- Referenced field --%>
				<h:outputText value="#{objective.user.name}" />


			</h:column>
			
			<%-- Field: organization --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="project.client.name">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.organization']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>
				<%-- Referenced field --%>
				<h:outputText value="#{objective.project.client.name}" />
			</h:column>
			
			<%-- Field: project --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="project.name">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.project']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>
				<%-- Referenced field --%>
				<h:outputText value="#{objective.project.name}" />
			</h:column>
			
			<%-- Field: name --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="name">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.name']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>
				<%-- String field --%>
				<h:outputText value="#{objective.name}">
					<f:converter converterId="autentia.CutStringTranslator" />
				</h:outputText>
			</h:column>

			<%-- Field: startDate --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="startDate">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.startDate']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>
				<%-- Date field --%>
				<h:outputText value="#{objective.startDate}"
					converter="autentia.dateConverter" />
			</h:column>

			<%-- Field: endDate --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="endDate">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['objective.endDate']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>
				<%-- Date field --%>
				<h:outputText value="#{objective.endDate}"
					converter="autentia.dateConverter" />
			</h:column>

		</t:dataTable>

		<%-- Paginator control --%>
		<t:dataScroller for="listThisWeek" fastStep="10" paginator="true"
			paginatorMaxPages="10" renderFacetsIfSinglePage="false"
			styleClass="scroller" paginatorTableClass="paginator"
			paginatorColumnClass="paginatorCell"
			paginatorActiveColumnClass="paginatorActiveCell">
			<f:facet name="first">
				<t:graphicImage value="/img/arrow-first.gif" border="0" />
			</f:facet>
			<f:facet name="last">
				<t:graphicImage value="/img/arrow-last.gif" border="0" />
			</f:facet>
			<f:facet name="previous">
				<t:graphicImage value="/img/arrow-previous.gif" border="0" />
			</f:facet>
			<f:facet name="next">
				<t:graphicImage value="/img/arrow-next.gif" border="0" />
			</f:facet>
			<f:facet name="fastrewind">
				<t:graphicImage value="/img/arrow-fr.gif" border="0" />
			</f:facet>
			<f:facet name="fastforward">
				<t:graphicImage value="/img/arrow-ff.gif" border="0" />
			</f:facet>
		</t:dataScroller>

	</h:form>
</f:view>

</body>
</html>

