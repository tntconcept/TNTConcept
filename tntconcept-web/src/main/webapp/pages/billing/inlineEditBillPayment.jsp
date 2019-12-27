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

	<%-- Commands --%>
	<h:column>
		<f:facet name="header">
			<h:panelGroup>
				<t:commandLink disabled="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}" action="#{billBean.createBillPayment}">
					<h:graphicImage title="#{msg.entityActions_new}"
						value="/img/new.gif" styleClass="cmdImg" />
				</t:commandLink>
			</h:panelGroup>
		</f:facet>
		<t:commandLink disabled="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}" action="#{billBean.deleteBillPayment}" immediate="true">
			<h:graphicImage title="#{msg.entityActions_delete}"
				value="/img/delete.gif" styleClass="cmdImg" />
		</t:commandLink>
	</h:column>


	<h:column>
		<f:facet name="header">
			<h:outputText value="*#{msg['bill.billPayment.expirationDate']}"
				styleClass="editListHeader" />
		</f:facet>
		<h:panelGroup>
			<h:message styleClass="error" showSummary="true" showDetail="false"
				for="paymentDate" />
            <t:inputCalendar id="paymentDate" value="#{item.expirationDate}"
			                 required="true" styleClass="requiredFieldClass"
							 renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true"
							 popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
							 readonly="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}" disabled="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}">
			  <f:validator validatorId="autentia.dateValidator"/>				
			</t:inputCalendar>
		</h:panelGroup>
	</h:column>

	<h:column>
		<f:facet name="header">
			<h:outputText value="*#{msg['bill.billPayment.amount']}"
				styleClass="editListHeader" />
		</f:facet>
		<h:panelGroup>
			<h:message styleClass="error" showSummary="true" showDetail="false"
				for="paymentAmount" />
			<h:inputText id="paymentAmount" value="#{item.amount}" maxlength="11"
				size="12" required="true" styleClass="requiredFieldClass" readonly="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}"/>
		</h:panelGroup>
	</h:column>
</t:dataTable>
