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

<%@include file="/inc/uiCore.jsp" %>

<%-- List of documentCategorys --%>
<t:dataTable id="entries" var="item" value="#{billBean.entries}" preserveDataModel="false" 
    cellpadding="0" cellspacing="0" styleClass="listTable" 
    headerClass="listHeaderCell" footerClass="listFooter" 
    rowClasses="listRowO,listRowE"
	columnClasses="listBillCreditTitleNumber,listBillCreditTitleProvider,listBillCreditTitleProject,listBillCreditTitleIssueDate,listBillCreditTitleStartDate,listBillCreditTitleEndDate">
	    
    <%-- Commands --%>
          <h:column>
            <f:facet name="header">
              <f:verbatim>-</f:verbatim>
            </f:facet>
            <t:commandLink action="#{billBean.unselect}" onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;" immediate="true">
              <f:param name="idEntry" value="#{item.id}" />              
              <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg" />
            </t:commandLink>
          </h:column>
    
    <h:column>
      <f:facet name="header">
        <h:outputText value="#{msg['accountEntry.date']}" styleClass="editListHeader"/>
      </f:facet>
              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="date" />
          <h:outputText id="date" value="#{item.date}" converter="autentia.dateConverter"/>
        </h:panelGroup>      
    </h:column>
    
    <h:column>

      <f:facet name="header">
        <h:outputText value="#{msg['accountEntry.amountDate']}" styleClass="editListHeader"/>
      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="amountDate" />
          <h:outputText id="amountDate" value="#{item.amountDate}" converter="autentia.dateConverter" />
        </h:panelGroup>

      
    </h:column>
    
    
    
    <h:column>

      <f:facet name="header">
        <h:outputText value="#{msg['accountEntry.concept']}" styleClass="editListHeader"/>
      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="concept" />
          <h:outputText id="concept" value="#{item.concept}" />
        </h:panelGroup>

      
    </h:column>
    
    <h:column>

      <f:facet name="header">
        <h:outputText value="#{msg['accountEntry.amount']}" styleClass="editListHeader"/>
      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="amount" />
          <h:outputText id="amount" value="#{item.amount}" />
        </h:panelGroup>

      
    </h:column>
	
	<h:column>

      <f:facet name="header">
        <h:outputText value="#{msg['accountEntry.account']}" styleClass="editListHeader"/>
      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="account" />
          <h:outputText id="account" value="#{item.account.name}" />
        </h:panelGroup>

      
    </h:column>
  <h:column>

      <f:facet name="header">
        <h:outputText value="#{msg['accountEntry.type']}" styleClass="editListHeader"/>
      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="type" />
          <h:outputText id="type" value="#{item.type.name}" />
        </h:panelGroup>

      
    </h:column>
  
  

</t:dataTable>
