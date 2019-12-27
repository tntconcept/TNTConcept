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
    
    <!-- editInventary.jsp: generated by stajanov code generator -->
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    <i:location place="editInventary" msg="${msg}"/> 
    
    <f:view>
       <%@include file="/inc/header.jsp" %>
      <h:form id="inventary" enctype="multipart/form-data">
        
        <%-- Header --%>
        <i:titleBar name="${inventaryBean.description}" msg="${msg}">
          <h:commandLink action="#{inventaryBean.save}">
            <h:graphicImage value="/img/save.gif" styleClass="titleImg" />
          </h:commandLink>
          <h:commandLink action="#{inventaryBean.delete}" onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage value="/img/delete.gif" styleClass="titleImg" />
          </h:commandLink>
          <h:commandLink action="#{inventaryBean.list}" immediate="true">
            <h:graphicImage value="/img/back.gif" styleClass="titleImg" />
          </h:commandLink>
        </i:titleBar>
        
        <%-- Edition form --%>
        <table class="editTable" cellpadding="0" cellspacing="0">

<%-- inventary - generated by stajanov (do not edit/delete) --%>








                  
  
      <%-- Ignored field: id --%>
  
                                  
    
      <%-- Field: buyDate --%>
    <tr>
    	    		<td class="editLabelRW">*${msg['inventary.buyDate']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="buyDate" />
            <t:inputCalendar id="buyDate" value="#{inventaryBean.buyDate}"  required="true" styleClass="requiredFieldClass"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true" 
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             />
          </h:panelGroup>

              </td>
    </tr>
                                  
  
      <%-- Field: renting --%>
    <tr>
    	    		<td class="editLabelRW">${msg['inventary.renting']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="renting" />
                		<h:inputText id="renting" value="#{inventaryBean.renting}" />
    	              </h:panelGroup>

              </td>
    </tr>
                                  
    
      <%-- Field: cost --%>
    <tr>
    	    		<td class="editLabelRW">*${msg['inventary.cost']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="cost" />
                        	<h:inputText id="cost" value="#{inventaryBean.cost}" size="10" maxlength="11" required="true" styleClass="requiredFieldClass">
            		<f:validator validatorId="autentia.genericMoneyValidator"/>
            		<f:attribute name="scale" value="2"/>
	            	<f:attribute name="maxSize" value="10"/>
            	</h:inputText>
                      </h:panelGroup>

              </td>
    </tr>
                                  
    
      <%-- Field: amortizable --%>
    <tr>
    	    		<td class="editLabelRW">*${msg['inventary.amortizable']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="amortizable" />
                		<h:inputText id="amortizable" value="#{inventaryBean.amortizable}"  required="true" styleClass="requiredFieldClass"/>
    	              </h:panelGroup>

              </td>
    </tr>
                                  
    
      <%-- Field: serialNumber --%>
    <tr>
    	    		<td class="editLabelRW">*${msg['inventary.serialNumber']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="serialNumber" />
                		<h:inputText id="serialNumber" value="#{inventaryBean.serialNumber}" size="20" maxlength="30" required="true" styleClass="requiredFieldClass"/>
    	              </h:panelGroup>

              </td>
    </tr>
                                  
  
      <%-- Field: provider --%>
    <tr>
    	    		<td class="editLabelRW">${msg['inventary.provider']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="provider" />
                		<h:inputText id="provider" value="#{inventaryBean.provider}" size="70" maxlength="128"/>
    	              </h:panelGroup>

              </td>
    </tr>
                                  
  
      <%-- Field: trademark --%>
    <tr>
    	    		<td class="editLabelRW">${msg['inventary.trademark']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="trademark" />
                		<h:inputText id="trademark" value="#{inventaryBean.trademark}" size="70" maxlength="128"/>
    	              </h:panelGroup>

              </td>
    </tr>
                                  
  
      <%-- Field: model --%>
    <tr>
    	    		<td class="editLabelRW">${msg['inventary.model']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="model" />
                		<h:inputText id="model" value="#{inventaryBean.model}" size="70" maxlength="128"/>
    	              </h:panelGroup>

              </td>
    </tr>
                                  
  
      <%-- Field: speed --%>
    <tr>
    	    		<td class="editLabelRW">${msg['inventary.speed']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="speed" />
                		<h:inputText id="speed" value="#{inventaryBean.speed}" maxlength="10"/>
    	              </h:panelGroup>

              </td>
    </tr>
                                  
  
      <%-- Field: storage --%>
    <tr>
    	    		<td class="editLabelRW">${msg['inventary.storage']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="storage" />
                		<h:inputText id="storage" value="#{inventaryBean.storage}" maxlength="10"/>
    	              </h:panelGroup>

              </td>
    </tr>
                                  
  
      <%-- Field: ram --%>
    <tr>
    	    		<td class="editLabelRW">${msg['inventary.ram']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="ram" />
                		<h:inputText id="ram" value="#{inventaryBean.ram}" maxlength="10"/>
    	              </h:panelGroup>

              </td>
    </tr>
                                  
  
      <%-- Field: location --%>
    <tr>
    	    		<td class="editLabelRW">${msg['inventary.location']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="location" />
                		<h:inputText id="location" value="#{inventaryBean.location}" size="70" maxlength="128"/>
    	              </h:panelGroup>

              </td>
    </tr>
                                  
  
      <%-- Field: description --%>
    <tr>
    	    		<td class="editLabelRW">${msg['inventary.description']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="description" />
            <h:inputTextarea id="description" value="#{inventaryBean.description}" rows="5" cols="68"/>
          </h:panelGroup>

              </td>
    </tr>
                                  
    
      <%-- Field: type --%>
    <tr>
    	    		<td class="editLabelRW">*${msg['inventary.type']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="type" />
            <h:selectOneMenu id="type" value="#{inventaryBean.type}"  required="true" styleClass="requiredFieldClass">
              <f:converter converterId="autentia.EnumConverter"/>
              <f:selectItems value="#{inventaryBean.types}" />
            </h:selectOneMenu>
          </h:panelGroup>

              </td>
    </tr>
                                  
  
      <%-- Ignored field: ownerId --%>
  
                                  
  
      <%-- Ignored field: departmentId --%>
  
                                  
  
      <%-- Ignored field: insertDate --%>
  
                                  
  
      <%-- Ignored field: updateDate --%>
  
                                              
  
      <%-- Field: assignedTo --%>
    <tr>
    	    		<td class="editLabelRW">${msg['inventary.assignedTo']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="assignedTo" />
            <h:selectOneMenu id="assignedTo" value="#{inventaryBean.assignedTo}" >
              <f:selectItems value="#{inventaryBean.assignedTos}" />
              <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
          </h:panelGroup>

              </td>
    </tr>
                                      


<%-- inventary - generated by stajanov (do not edit/delete) --%>

        </table>
        
      </h:form>
    </f:view>
    
  </body>
</html>  		

