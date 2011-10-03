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

<%-- List of payments --%>
<t:dataTable id="payment" var="item" value="#{billBean.billPayment}"
	preserveDataModel="false" cellpadding="0" cellspacing="0"
	styleClass="editListTable" headerClass="editListHeaderCell"
	footerClass="editListFooter" rows="#{settingBean.mySettings.listSize}"
	rowClasses="editListRowO,editListRowE"
	columnClasses="listCmdCell,editListBillBreakDownConcept,editListBillBreakDownUnits">

	<h:column>
		<f:facet name="header">
			<h:outputText value="#{msg['bill.billPayment.expirationDate']}"
				styleClass="editListHeader" />
		</f:facet>
		<h:outputText value="#{item.expirationDate}" converter="autentia.dateConverter" />
	</h:column>

	<h:column>
		<f:facet name="header">
			<h:outputText value="#{msg['bill.billPayment.amount']}"
				styleClass="editListHeader" />
		</f:facet>
		<h:outputText value="#{item.amount}" />
	</h:column>
</t:dataTable>
