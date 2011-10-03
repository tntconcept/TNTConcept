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

    
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    <i:location place="userHolidayState" msg="${msg}"/> 
    
    <f:view>
     <%@include file="/inc/header.jsp" %>
      <h:form id="userHolidayState">
        
        <%-- Header --%>
        <i:titleBar msg="${msg}">
         
        </i:titleBar>
        
        
        <h:panelGrid columns="3" cellpadding="0" cellspacing="0" styleClass="editTable" columnClasses="editLabelRW,editFieldCellGlobal,editFieldCellGlobal">
          <h:outputText value="#{msg['userHolidayState.chargeYear']}:" />
          <t:inputCalendar id="startDate" value="#{userHolidaysStateBean.chargeYear}" required="true" styleClass="requiredFieldClass"
	                       renderAsPopup="true" popupDateFormat="yyyy" renderPopupButtonAsImage="true" 
	                       popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}" onchange="submit()"/> 
		  
		  <h:commandButton action="#" title="#{msg['userHolidayState.show']}" value="#{msg['userHolidayState.show']}"/>
        </h:panelGrid>
        
        
                
        <t:dataTable id="list" var="userHolidaysState" value="#{userHolidaysStateBean.all}" preserveDataModel="false" 
            cellpadding="0" cellspacing="0" styleClass="listTable" 
            headerClass="listHeaderCell" footerClass="listFooter" 
             rowClasses="listRowO,listRowE" sortColumn="#{userHolidaysStateBean.sortColumn}" sortAscending="#{userHolidaysStateBean.sortAscending}"
	    		rowOnMouseOver="this.savedClassName=this.className;this.className='listRowSel';" 
	    rowOnMouseOut="this.className=this.savedClassName;">
        
   
    <h:column>
      <f:facet name="header">
            <t:commandSortHeader styleClass="listHeader" columnName="name">
                 <f:facet name="ascending">
            <t:graphicImage value="/img/ascending-arrow.gif" border="0" />
          </f:facet>
          <f:facet name="descending">
            <t:graphicImage value="/img/descending-arrow.gif" border="0" />
          </f:facet>
          <f:verbatim>${msg['userHolidayState.user']}</f:verbatim>
        </t:commandSortHeader>
      </f:facet>
      
      <h:outputText value="#{userHolidaysState.user.name}"/>

	

    </h:column>
  

   
    <h:column>
      <f:facet name="header">      	        
          <f:verbatim>${msg['userHolidaysState.totalAvailable']}</f:verbatim>
      </f:facet>	
	  <h:outputText value="#{userHolidaysState.user.agreement.holidays}"/>
    </h:column>
  
    <h:column>
      <f:facet name="header">      	        
          <f:verbatim>${msg['userHolidaysState.totalYear']}</f:verbatim>
      </f:facet>	
	  <h:outputText value="#{userHolidaysState.totalYear}"/>
    </h:column>
  
  
    <h:column>
      <f:facet name="header">      	        
          <f:verbatim>${msg['userHolidaysState.totalAccepted']}</f:verbatim>        
      </f:facet>	
	  <h:outputText value="#{userHolidaysState.totalAccepted}"/>


    </h:column>
    
    
    <h:column>
      <f:facet name="header">
            <f:verbatim>${msg['userHolidaysState.total']}</f:verbatim>      
      </f:facet>	
	  <h:outputText value="#{userHolidaysState.total}"/>
    </h:column>

      
        </t:dataTable>
          
       
        
      </h:form>
    </f:view>
    
  </body>
</html>  		

