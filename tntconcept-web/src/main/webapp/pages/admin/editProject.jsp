<%@ taglib prefix="p" uri="http://myfaces.apache.org/tomahawk" %>
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
    <link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bitacore.css">
    <script src="<%=request.getContextPath()%>/script/bitacore.js" language="Javascript" type="text/javascript" charset="UTF-8"></script>
  </head>	
  <body>
    
    <!-- editProject.jsp: generated by stajanov code generator -->
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    <i:location place="editProject" msg="${msg}"/> 
    
    <f:view>
       <%@include file="/inc/header.jsp" %>
      <h:form id="project" enctype="multipart/form-data">
        
        <%-- Header --%>
        <i:titleBar name="${projectBean.name}" msg="${msg}">
          <h:commandLink action="#{projectBean.save}"
          onclick="if(!askSave('#{projectBean.id}','#{msg['question.confirmSave']}')) return false;">
            <h:graphicImage value="/img/save.gif" styleClass="titleImg" />
          </h:commandLink>
          <h:commandLink action="#{projectBean.delete}" onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage value="/img/delete.gif" styleClass="titleImg" />
          </h:commandLink>
          <h:commandLink action="#{projectBean.list}" immediate="true">
            <h:graphicImage value="/img/back.gif" styleClass="titleImg" />
          </h:commandLink>
        </i:titleBar>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="project" />
        <%-- Edition form --%>
        <table class="editTable" cellpadding="0" cellspacing="0">

          <%-- Ignored field: id --%>

          <%-- Field: startDate --%>
          <tr>
            <td class="editLabelRW">*${msg['project.startDate']}:</td>
            <td class="editFieldCell">
              <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="startDate" />
                <t:inputCalendar id="startDate" value="#{projectBean.startDate}"
                                 required="true" styleClass="requiredFieldClass"
                                 renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true" 
                                 popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
	                             >
					<f:validator validatorId="autentia.dateValidator"/>
				</t:inputCalendar>
              </h:panelGroup>
            </td>
          </tr>

          <%-- Field: endDate --%>
          <tr>
            <td class="editLabelRW">${msg['project.endDate']}:</td>
            <td class="editFieldCell">
              <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="endDate" />
                <t:inputCalendar id="endDate" value="#{projectBean.endDate}"
                                 renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true" 
                                 popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
	                             >
					<f:validator validatorId="autentia.dateValidator"/>
				</t:inputCalendar>
              </h:panelGroup>
            </td>
          </tr>

		  <%-- Field: open --%>
   		  <tr>
    	    	<td class="editLabelRW">${msg['project.open']}:</td>
    	      	<td class="editFieldCell">
					<h:panelGroup>
            			<h:message styleClass="error" showSummary="true" showDetail="false" for="open" />
                		<h:selectBooleanCheckbox id="open" value="#{projectBean.open}" />
    		        </h:panelGroup>
             	</td>
    	  </tr>
    	
    	   <%-- Field: billable --%>
    <tr>
    	    		<td class="editLabelRW">${msg['project.billable']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            			<h:message styleClass="error" showSummary="true" showDetail="false" for="billable" />
                		<h:selectBooleanCheckbox id="billable" value="#{projectBean.billable}" />
    	              </h:panelGroup>

              </td>
    </tr>

          <%-- Field: billing type --%>
          <tr>
              <td class="editLabelRW">*${msg['project.billingType']}:</td>
              <td class="editFieldCell">
              <h:panelGroup>
                  <h:message styleClass="error" showSummary="true" showDetail="false" for="billingType" />
                        <h:selectOneMenu id="billingType" value="#{projectBean.billingType}" required="true" styleClass="requiredFieldClass">
                            <f:selectItems value="#{projectBean.billingTypes}" />
                        </h:selectOneMenu>
            </h:panelGroup>
            </td>
        </tr>

                <%-- Field: client --%>
          <tr>
              <td class="editLabelRW">*${msg['project.client']}:</td>
              <td class="editFieldCell">
                  <h:panelGroup>
                      <h:message styleClass="error" showSummary="true" showDetail="false" for="client" />
                      <h:selectOneMenu id="client" value="#{projectBean.client}" required="true" styleClass="requiredFieldClass">
                          <f:selectItems value="#{projectBean.clients}" />
                          <f:converter converterId="autentia.EntityConverter"/>
                      </h:selectOneMenu>
                  </h:panelGroup>
              </td>
          </tr>
          <%-- Field: name --%>
          <tr>
            <td class="editLabelRW">*${msg['project.name']}:</td>
            <td class="editFieldCell">
              <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="name" />
                <h:inputText id="name" value="#{projectBean.name}" size="70" maxlength="128" required="true" onchange="submit();" styleClass="requiredFieldClass"/>
              </h:panelGroup>
            </td>
          </tr>

          <%-- Field: description --%>
          <tr>
            <td class="editLabelRW">${msg['project.description']}:</td>
            <td class="editFieldCell">
              <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="description" />
                <h:inputTextarea id="description" value="#{projectBean.description}" rows="10" cols="95"/>
              </h:panelGroup>
            </td>
          </tr>
                  <%-- Field: offer --%>
          <tr>
              <td class="editLabelRW">${msg['project.offer']}:</td>
              <td class="editFieldCell">
                  <h:panelGroup>
                      <p:inputText disabled="#{empty projectBean.name}" id="offerNumberInput" immediate="true" binding="#{projectBean.offerNumberInput}" valueChangeListener="#{projectBean.refreshOfferList}"  onchange="submit();"/>
                      <h:selectOneMenu rendered="#{projectBean.getFilterOffer().size() > 1}" id="offerNumberInputOneMenu" immediate="true" valueChangeListener="#{projectBean.refreshOffer}"  onchange="submit();">
                          <f:selectItem itemLabel="-- Selecciona una oferta --" itemValue="0"/>
                          <f:selectItems value="#{projectBean.getFilterOffer()}" />
                      </h:selectOneMenu>
                  </h:panelGroup>
              </td>
          </tr>

          <%-- Field: roles --%>
          <tr>
            <td class="editLabelRW">${msg['project.roles']}:</td>
            <td class="editFieldCell">
              <%@include file="inlineEditProjectRole.jsp"%>
            </td>
          </tr>

          <%-- Field: costs --%>
          <tr>
            <td class="editLabelRW">${msg['project.costs']}:</td>
            <td class="editFieldCell">
              <%@include file="inlineEditProjectCost.jsp"%>
            </td>
          </tr>

          <%-- Customized: totalCost --%>
          <tr>
            <td class="editLabelRW">${msg['editProject.totalCost']}:</td>
            <td class="editFieldCell">
              <h:panelGroup>
                <h:outputText value="#{projectBean.totalCost}">
                  <f:convertNumber pattern="#,##0.00"/>
                </h:outputText>
              <h:commandLink action="#{billBean.reloadTotal}">
            		&nbsp;<h:graphicImage title="#{msg['bill.action.reload']}" value="/img/refresh.png" />
          		</h:commandLink>
              </h:panelGroup>
              
            </td>
          </tr>
          <%-- Field: Horas trabajadas --%>
          <tr>
            <td class="editLabelRW">${msg['project.workedHours']}</td>
            <td class="editFieldCell">
            
            <h:panelGroup rendered="#{projectBean.percentageWorked <= 1}">

			<t:div styleClass="progressBar" style="background-position: #{250 * (-2 + projectBean.percentageWorked)}px center;z-index:0;"
			rendered="#{(activityBean.scheduleModel.mode == 3)}">
			<h:outputFormat value="[{0}h. / {1}h.] - " styleClass="progressBarText">
				<f:param value="#{projectBean.workedHours}"/>
				<f:param value="#{projectBean.expectedHours}"/>
			</h:outputFormat>

			<h:outputText value="#{projectBean.percentageWorked}" styleClass="progressBarText">
				<f:convertNumber type="percent" maxFractionDigits="1"/>
			</h:outputText>	
		</t:div>      
      </h:panelGroup>
      
      <h:panelGroup rendered="#{projectBean.percentageWorked > 1}">
		<%--Si el porcentaje es mayor de uno, es decir del 100% se pintará en rojo --%>
		<t:div styleClass="progressBarRed" style="background-position: #{-250}px center;z-index:0;"
			rendered="#{(activityBean.scheduleModel.mode == 3)}">
			<h:outputFormat value="[{0}h. / {1}h.] - " styleClass="progressBarText">
				<f:param value="#{projectBean.workedHours}"/>
				<f:param value="#{projectBean.expectedHours}"/>
			</h:outputFormat>

			<h:outputText value="#{projectBean.percentageWorked}" styleClass="progressBarText">
				<f:convertNumber type="percent" maxFractionDigits="1"/>
			</h:outputText>	
		</t:div>      
      </h:panelGroup>  
                         </td>
          </tr>

 			          	
          	<tr>
            <td class="editLabelRW">${msg['project.costPerProject']}</td>
            <td class="editFieldCell">
            
            <h:panelGroup rendered="#{projectBean.percentageCosts <= 1}">

			<t:div styleClass="progressBar" style="background-position: #{250 * (-2 + projectBean.percentageCosts)}px center;z-index:0;"
			rendered="#{(activityBean.scheduleModel.mode == 3)}">
			<h:outputFormat value="[{0}€ / {1}€] - " styleClass="progressBarText">
				<f:param value="#{projectBean.costPerProject}" />				
				<f:param value="#{projectBean.totalCost}"/>
			</h:outputFormat>

			<h:outputText value="#{projectBean.percentageCosts}" styleClass="progressBarText">
				<f:convertNumber type="percent" maxFractionDigits="1"/>
			</h:outputText>	
		</t:div>      
      </h:panelGroup>
      
      <h:panelGroup rendered="#{projectBean.percentageCosts > 1}">
		<%--Si el porcentaje es mayor de uno, es decir del 100% se pintará en rojo --%>
		<t:div styleClass="progressBarRed" style="background-position: #{-250}px center;z-index:0;"
			rendered="#{(activityBean.scheduleModel.mode == 3)}">
			<h:outputFormat value="[{0}€ / {1}€] - " styleClass="progressBarText">
				<f:param value="#{projectBean.costPerProject}" />
				
				<f:param value="#{projectBean.totalCost}"/>
			</h:outputFormat>

			<h:outputText value="#{projectBean.percentageCosts}" styleClass="progressBarText">
				<f:convertNumber type="percent" maxFractionDigits="1"/>
			</h:outputText>	
		</t:div>      
      </h:panelGroup>  
                         </td>
          </tr>
          	


        </table>
        
      </h:form>
    </f:view>
  </body>
</html>


