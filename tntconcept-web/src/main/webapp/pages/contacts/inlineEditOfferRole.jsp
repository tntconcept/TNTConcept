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

<%-- List of offerRoles --%>
<t:dataTable id="roles" var="item" value="#{offerBean.roles}" preserveDataModel="false" 
    cellpadding="0" cellspacing="0" styleClass="editListTable" 
    headerClass="editListHeaderCell" footerClass="editListFooter" 
    rows="#{settingBean.mySettings.listSize}" rowClasses="editListRowO,editListRowE"
    columnClasses="listCmdCell,listCmdCell,editListOfferRoleName,editListOfferRoleCostPerHour,editListOfferRoleIva,editListOfferRoleExpectedHours">

  <%-- Commands --%>
  <h:column>
    <f:facet name="header">
      <t:commandLink action="#{offerBean.createRoles}">
        <h:graphicImage title="#{msg.entityActions_new}"  value="/img/new.gif" styleClass="cmdImg" />
      </t:commandLink>
    </f:facet>
    <t:commandLink action="#{offerBean.deleteRoles}" immediate="true">
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
          <h:inputText id="place" value="#{item.place}" maxlength="3" size="3"  required="false" styleClass="requiredFieldClass"/>
        </h:panelGroup>

      
    </h:column>    
    
    
      
    <h:column>

      <f:facet name="header">
      	      		<h:outputText value="*#{msg['offerRole.name']}" styleClass="editListHeader"/>
      	      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="name" />
		  <h:inputTextarea id="name" value="#{item.name}" rows="2" cols="68" required="true" styleClass="requiredFieldClass" />          
        </h:panelGroup>

      
    </h:column>

    <!-- Coste por hora -->
    <h:column>
      <f:facet name="header">
      	      		<h:outputText value="*#{msg['offerRole.costPerHour']}" styleClass="editListHeader"/>
      	      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="costPerHour" />
          <h:inputText id="costPerHour" value="#{item.costPerHour}" size="10"  required="true" styleClass="requiredFieldClass"/>
        </h:panelGroup>

      
    </h:column>

    <!-- Horas presupuestadas -->
    <h:column>
      <f:facet name="header">
    	<h:outputText value="*#{msg['offerRole.expectedHours']}" styleClass="editListHeader"/>
      </f:facet>

       <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="expectedHours" />
          <h:inputText id="expectedHours" value="#{item.expectedHours}" size="10"  required="true" styleClass="requiredFieldClass"/>
        </h:panelGroup>
    </h:column>
    
    <!-- Subtotal (Base Imponible) -->
    <%--<h:column>
      <f:facet name="header">
    		<h:outputText value="*#{msg['offerRole.expectedHours']}" styleClass="editListHeader"/>
      </f:facet>
      <h:panelGroup>
         <h:message styleClass="error" showSummary="true" showDetail="false" for="partialCost" />
         <h:inputText id="expectedHours" value="#{item.expectedHours}" size="10"  required="true" styleClass="requiredFieldClass"/>
       </h:panelGroup>
    </h:column>--%>
    
    <!-- IVA -->
    <h:column>
      <f:facet name="header">
      	 <h:outputText value="*#{msg['offerRole.iva']}" styleClass="editListHeader"/>
      </f:facet>

      <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="iva" />
          <h:inputText id="iva" value="#{item.iva}" size="10"  required="true" styleClass="requiredFieldClass"/>
      </h:panelGroup>
    </h:column>


    
    
  
      <%-- Ignored field: ownerId --%>
    
    
  
      <%-- Ignored field: departmentId --%>
    
    
  
      <%-- Ignored field: insertDate --%>
    
    
  
      <%-- Ignored field: updateDate --%>
  
  
    
    
      <%-- Ignored field: offer --%>
  

</t:dataTable>