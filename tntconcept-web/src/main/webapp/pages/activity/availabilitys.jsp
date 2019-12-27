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

<%-- ¡¡¡¡¡ADVICE!!!!! this page is not (yet) under stajanov --%>

<%@page language="java" contentType="text/html; charset=UTF-8"%>

<%@include file="/inc/tlibs.jsp"%>
<%@ taglib uri="com.autentia.jsf.components.ocupation" prefix="oc"%>
<html>
<head>
<%@include file="/inc/uiCore.jsp"%>

</head>
<body>

<f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
<i:location place="availabilitys" msg="${msg}" />

<f:view>
	<%@include file="/inc/header.jsp"%>
	<h:form id="availabilitys">
		<%-- Header --%>
		<i:titleBar msg="${msg}">
			
		</i:titleBar>
		
		 <h:panelGrid columns="2" cellpadding="2" cellspacing="2" styleClass="editTable" 
		 columnClasses="editLabelRW,editFieldCell">
          <h:outputText value="#{msg['availabilitys.selectedDate']}:" />
          <t:inputCalendar id="startDate" value="#{availabilityBean.selectedDate}" required="true" styleClass="requiredFieldClass"
	                       renderAsPopup="true" popupDateFormat="MM/yyyy"  renderPopupButtonAsImage="true" 
	                       popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}" onchange="submit()"/>
	      
		   <h:commandButton action="#" title="#{msg['globalHoursReport.show']}" value="#{msg['globalHoursReport.show']}"/>
		   <h:panelGroup>
	      	<h:outputText value="#{msg['availabilitys.vacations']}" styleClass="vacationDay"/>
	      	<h:outputText value="#{msg['availabilitys.holidays']}" styleClass="weekendDay"/>
	      	<h:outputText value="#{msg['availabilitys.fullDays']}" styleClass="fullDay"/>
	      	<h:outputText value="#{msg['availabilitys.halfDays']}" styleClass="halfDay"/>
	      	
	      </h:panelGroup>
		   
		</h:panelGrid>
		
		
		<t:dataTable id="list" var="availability" value="#{availabilityBean.all}"
			preserveDataModel="false" cellpadding="2" cellspacing="2"
			styleClass="listTable" headerClass="listHeaderCell"
			footerClass="listFooter" rowClasses="listRowO,listRowE"
			columnClasses="listAvailabilityAction,listAvailabilityUser,listAvailabilityModel"
			rowOnMouseOver="this.savedClassName=this.className;this.className='listRowSel';"
			rowOnMouseOut="this.className=this.savedClassName;">


			<%-- Commands --%>
			<h:column>				
				<t:commandLink action="#{occupationBean.insertOccupation}" immediate="true">
              		<f:param name="rowid" value="#{availability.user.id}" />
              		<h:graphicImage title="#{msg.entityActions_create}"  value="/img/new.gif" styleClass="cmdImg" />
            	</t:commandLink>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="#{msg['availabilitys.user']}"/>
				</f:facet>
				<h:outputText value="#{availability.user.name}"/>				
			</h:column>

			<%-- Field: description --%>
			<h:column>
				<f:facet name="header">
					<h:outputText value="#{msg['availabilitys.model']}"/>
				</f:facet>
				<oc:ocupationCalendar currentDayCellClass="freeDay" extraDaysCellClass="extraDay" dayCellClass="freeDay" weekEndCellClass="weekendDay" vacancesCellClass="vacationDay" 
				    partialWorkCellClass="halfDay" fullWorkCellClass="fullDay"
					extraDays="5" workingDayHours="#{availability.user.dayDuration}" ocupationModel="#{availability.model}" 
					value="#{availabilityBean.selectedDate}" />
			</h:column>
		</t:dataTable>
		        	
		        
	</h:form>
</f:view>
</body>
</html>