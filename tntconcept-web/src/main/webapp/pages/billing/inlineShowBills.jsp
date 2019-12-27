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
<t:dataTable id="entries" var="item" value="#{creditTitleBean.bills}" preserveDataModel="false" 
    cellpadding="0" cellspacing="0" styleClass="listTable" 
    headerClass="listHeaderCell" footerClass="listFooter" 
    rowClasses="listRowO,listRowE"
    columnClasses="listBillCreditTitleNumber,listBillCreditTitleProvider,listBillCreditTitleProject,listBillCreditTitleIssueDate,listBillCreditTitleStartDate,listBillCreditTitleEndDate">

          <h:column>
            <f:facet name="header">
              <f:verbatim>-</f:verbatim>
            </f:facet>
            <t:commandLink action="#{creditTitleBean.unselect}" onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;" immediate="true">
              <f:param name="idEntry" value="#{item.id}" />              
              <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg" />
            </t:commandLink>
          </h:column>

	<%-- numero --%>
    <h:column>
      <f:facet name="header">
        <h:outputText value="#{msg['bill.number']}" styleClass="editListHeader"/>
      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="concept" />
          <h:outputText id="concept" value="#{item.number}" />
        </h:panelGroup>
    </h:column>    

	<%-- tipo --%>
    <h:column>
      <f:facet name="header">
        <h:outputText value="#{msg['bill.billType']}" styleClass="editListHeader"/>
      </f:facet>

		<h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="concept" />
          <h:outputText id="billType" value="#{item.billType}" >
	    		<f:converter converterId="autentia.EnumTranslator"/>
	  		</h:outputText>
          
          
        </h:panelGroup>
    </h:column>    

	<%-- empresa --%>
    <h:column>
      <f:facet name="header">
        <h:outputText value="#{msg['bill.provider']}" styleClass="editListHeader"/>
      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="concept" />
          <h:outputText id="provider" value="#{item.provider.name}" />
        </h:panelGroup>
    </h:column>    

	<%-- proyecto --%>
    <h:column>
      <f:facet name="header">
        <h:outputText value="#{msg['bill.project']}" styleClass="editListHeader"/>
      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="concept" />
          <h:outputText id="project" value="#{item.project.name}" />
        </h:panelGroup>
    </h:column>    
    
    <%-- fecha creacion --%>
    <h:column>
      <f:facet name="header">
        <h:outputText value="#{msg['bill.creationDate']}" styleClass="editListHeader"/>
      </f:facet>
              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="creationDate" />
          <h:outputText id="creationDate" value="#{item.creationDate}" converter="autentia.dateConverter"/>
        </h:panelGroup>      
    </h:column>

	<%-- inicio facturacion --%>
    <h:column>
      <f:facet name="header">
        <h:outputText value="#{msg['bill.startBillDate']}" styleClass="editListHeader"/>
      </f:facet>
              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="startBillDate" />
          <h:outputText id="startBillDate" value="#{item.startBillDate}" converter="autentia.dateConverter"/>
        </h:panelGroup>      
    </h:column>
    
    <%-- fin facturacion --%>
    <h:column>
      <f:facet name="header">
        <h:outputText value="#{msg['bill.endBillDate']}" styleClass="editListHeader"/>
      </f:facet>
              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="endBillDate" />
          <h:outputText id="endBillDate" value="#{item.endBillDate}" converter="autentia.dateConverter" />
        </h:panelGroup>
    </h:column>
    
</t:dataTable>
