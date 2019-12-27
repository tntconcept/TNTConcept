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
<%@include file="/inc/uiCore.jsp"%>

<%-- List of departments --%>
<t:dataTable id="departments" var="item"
	value="#{organizationBean.companyDepartments}"
	preserveDataModel="false" cellpadding="0" cellspacing="0"
	styleClass="editListTable" headerClass="editListHeaderCell"
	footerClass="editListFooter" rows="#{settingBean.mySettings.listSize}"
	rowClasses="editListRowO,editListRowE"
	columnClasses="listCmdCell,editListBillBreakDownConcept,editListBillBreakDownAmount">

	<h:column>
		<f:facet name="header">
			<h:panelGroup>
				<t:commandLink action="#{organizationBean.createDepartment}">
					<h:graphicImage title="#{msg.entityActions_new}"
						value="/img/new.gif" styleClass="cmdImg" />
				</t:commandLink>
			</h:panelGroup>
		</f:facet>
		<t:commandLink action="#{organizationBean.deleteDepartment}" immediate="true">
			<h:graphicImage title="#{msg.entityActions_delete}"
				value="/img/delete.gif" styleClass="cmdImg" />
		</t:commandLink>
	</h:column>
	<h:column>
		<f:facet name="header">
			<f:verbatim>*${msg['department.name']}</f:verbatim>
		</f:facet>
		<h:message styleClass="error" showSummary="true" showDetail="false" for="name" />
		<h:inputText id="name" value="#{item.name}" maxlength="50" size="51" required="true" styleClass="requiredFieldClass" />
	</h:column>
	<h:column>
		<f:facet name="header">
			<f:verbatim>${msg['department.description']}</f:verbatim>
		</f:facet>
		<h:inputTextarea id="description" value="#{item.description}" rows="3"
			cols="68" />
	</h:column>

</t:dataTable>

