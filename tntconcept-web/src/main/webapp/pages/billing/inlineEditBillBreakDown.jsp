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

<%-- List of billBreakDowns --%>
<t:dataTable id="breakDown" var="itemBreakDown" value="#{billBean.breakDown}" preserveDataModel="false"
    cellpadding="0" cellspacing="0" styleClass="editListTable"
    headerClass="editListHeaderCell" footerClass="editListFooter"
    rows="#{settingBean.mySettings.listSize}" rowClasses="editListRowO,editListRowE"
    columnClasses="listCmdCell,listCmdCell, editListBillBreakDownConcept,editListBillBreakDownUnits,editListBillBreakDownAmount,editListBillBreakDownIva">

  <%-- Commands --%>
  <h:column>
    <f:facet name="header">
    <h:panelGroup>
      <t:commandLink disabled="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}" action="#{billBean.createBreakDown}">
        <h:graphicImage title="#{msg.entityActions_new}"  value="/img/new.gif" styleClass="cmdImg" />
      </t:commandLink>
      <t:commandLink disabled="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}" action="#{billBean.searchInBitacore}" immediate="true" rendered="#{billBean.puedoBuscarBitacore}">
        <h:graphicImage title="#{msg['bill.useBitacore']}"  value="/img/calendar_on.gif" styleClass="cmdImg" />
      </t:commandLink>
      </h:panelGroup>
    </f:facet>
    <t:commandLink disabled="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}" action="#{billBean.deleteBreakDown}" immediate="true">
      <h:graphicImage title="#{msg.entityActions_delete}"  value="/img/delete.gif" styleClass="cmdImg" />
    </t:commandLink>
  </h:column>




      <%-- Ignored field: id --%>

    <h:column>

		<f:facet name="header">
      	  <h:outputText value="#{msg['billBreakDown.position']}" styleClass="editListHeader"/>
      	</f:facet>

		<h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="place" />
          <h:inputText id="place" value="#{itemBreakDown.place}" maxlength="3" size="3"  required="false" styleClass="requiredFieldClass"
                       readonly="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}"/>
        </h:panelGroup>


    </h:column>



    <h:column>

      <f:facet name="header">
      	      		<h:outputText value="*#{msg['billBreakDown.concept']}" styleClass="editListHeader"/>
      	      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="concept" />
          <h:inputTextarea id="concept" value="#{itemBreakDown.concept}" rows="3" cols="68" required="true" readonly="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}"/>
        </h:panelGroup>


    </h:column>





    <h:column>

      <f:facet name="header">
      	      		<h:outputText value="*#{msg['billBreakDown.units']}" styleClass="editListHeader"/>
      	      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="units" />
          <h:inputText id="units" value="#{itemBreakDown.units}" maxlength="11" size="11"  required="true" styleClass="requiredFieldClass"
                       readonly="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}"/>
        </h:panelGroup>


    </h:column>





    <h:column>

      <f:facet name="header">
      	      		<h:outputText value="*#{msg['billBreakDown.amount']}" styleClass="editListHeader"/>
      	      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="amount" />
          <h:inputText id="amount" value="#{itemBreakDown.amount}" maxlength="11" size="12"  required="true" styleClass="requiredFieldClass"
                       readonly="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['billBreakDown.iva']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup style="display: flex">
            <h:message styleClass="error" showSummary="true" showDetail="false" for="iva"/>
            <h:selectOneMenu id="iva" value="#{itemBreakDown.iva}" required="true"
                             styleClass="requiredFieldClass"
                             onchange="submit();"
                             readonly="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}">
                <f:selectItems value="#{billBean.IVAType}"/>
            </h:selectOneMenu>

            <h:selectOneMenu rendered="#{billBean.renderTaxFreeReasonsList(itemBreakDown.iva)}" id="taxFree" value="#{itemBreakDown.taxFreeReason}" required="false"
                             readonly="#{billBean.id != null && billBean.readOnlyBill && billBean.bill.submitted == 1}">
                <f:selectItems value="#{billBean.taxFreeReasons}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>

    </h:column>

    <h:column>

      <f:facet name="header">
      	      		<h:outputText value="#{msg['billBreakDown.total']}" styleClass="editListHeader"/>
      	      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="total" />
          <h:outputText id="total" value="#{itemBreakDown.total}"    styleClass="requiredFieldClass"/>
        </h:panelGroup>

      
    </h:column>
      <%-- Ignored field: ownerId --%>
    
    
  
      <%-- Ignored field: departmentId --%>
    
    
  
      <%-- Ignored field: insertDate --%>
    
    
  
      <%-- Ignored field: updateDate --%>
  
  
    
    
      <%-- Ignored field: bill --%>
  

</t:dataTable>



















                
                

                

                

