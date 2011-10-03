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
    <i:location place="detailInventary" msg="${msg}"/> 
    
    <f:view>
      <%@include file="/inc/header.jsp" %>
      <h:form id="inventary" enctype="multipart/form-data">
        
        <%-- Header --%>
        <i:titleBar name="${inventaryBean.description}" msg="${msg}">
          <t:commandLink action="#{inventaryBean.edit}" immediate="true" rendered="#{inventaryBean.editAvailable}">
            <f:param name="rowid" value="#{inventaryBean.id}" />
            <h:graphicImage title="#{msg.entityActions_edit}"  value="/img/edit.gif" styleClass="cmdImg" />
          </t:commandLink>
          <h:commandLink action="#{inventaryBean.delete}" rendered="#{inventaryBean.deleteAvailable}" onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}"  value="/img/delete.gif" styleClass="titleImg" />
          </h:commandLink>
          <h:commandLink action="#{inventaryBean.list}" immediate="true">
            <h:graphicImage title="#{msg.entityActions_back}"  value="/img/back.gif" styleClass="titleImg" />
          </h:commandLink>
        </i:titleBar>


        <%-- Detail form --%>
        <table class="detailTable" cellpadding="0" cellspacing="0">

            
    
  
  
      <%-- Ignored field: id --%>
  
                            
    
  
  
      <%-- Field: buyDate --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.buyDate']}:</td>
	<td class="detailFieldCell">
         	    	<h:outputText value="#{inventaryBean.buyDate}" converter="autentia.dateConverter" />
 	          </td>
    </tr>
                            
    
  
  
      <%-- Field: renting --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.renting']}:</td>
	<td class="detailFieldCell">
        		<h:selectBooleanCheckbox id="renting" value="#{inventaryBean.renting}" disabled="true"/>
              </td>
    </tr>
                            
    
  
  
      <%-- Field: cost --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.cost']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{inventaryBean.cost}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: amortizable --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.amortizable']}:</td>
	<td class="detailFieldCell">
        		<h:selectBooleanCheckbox id="amortizable" value="#{inventaryBean.amortizable}" disabled="true"/>
              </td>
    </tr>
                            
    
  
  
      <%-- Field: serialNumber --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.serialNumber']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{inventaryBean.serialNumber}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: provider --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.provider']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{inventaryBean.provider}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: trademark --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.trademark']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{inventaryBean.trademark}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: model --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.model']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{inventaryBean.model}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: speed --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.speed']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{inventaryBean.speed}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: storage --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.storage']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{inventaryBean.storage}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: ram --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.ram']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{inventaryBean.ram}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: location --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.location']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{inventaryBean.location}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: description --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.description']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{inventaryBean.description}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: type --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.type']}:</td>
	<td class="detailFieldCell">
                	<h:outputText value="#{inventaryBean.typeFormatted}" />
              </td>
    </tr>
                            
    
  
  
      <%-- Ignored field: ownerId --%>
  
                            
    
  
  
      <%-- Ignored field: departmentId --%>
  
                            
    
  
  
      <%-- Ignored field: insertDate --%>
  
                            
    
  
  
      <%-- Ignored field: updateDate --%>
  
                                        
    
  
  
      <%-- Field: assignedTo --%>
    <tr>
	<td class="detailLabelRW">${msg['inventary.assignedTo']}:</td>
	<td class="detailFieldCell">
         	    	<h:outputText value="#{inventaryBean.assignedTo.name}"/>
 	          </td>
    </tr>
                                      
        </table>

      </h:form>
    </f:view>
    
  </body>
</html>  		

<%-- inventary - generated by stajanov (do not edit/delete) --%>
