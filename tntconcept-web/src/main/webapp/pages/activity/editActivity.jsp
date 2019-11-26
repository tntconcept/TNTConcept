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

<%@page import="com.autentia.tnt.util.FacesUtils"%>
<html>
  <head>
    <%@include file="/inc/uiCore.jsp" %>
	<script src="<%=request.getContextPath()%>/script/bitacore.js" language="Javascript" type="text/javascript" charset="UTF-8"></script>
	<script type="text/javascript">
		var msgNO='<%=FacesUtils.getMessage("warning.billableNO")%>';
		var msgYES='<%=FacesUtils.getMessage("warning.billableYES")%>';
		function checkBillable() {		
			var billable = document.getElementById('activity:billable');
			var defaultBillable = document.getElementById('activity:defaultBillable');
			
			if(billable.checked && defaultBillable.value=="false") {
				alert(msgNO);
			} else if (!billable.checked && defaultBillable.value=="true") {
				alert(msgYES);
			}
		}
		
	</script>
  </head>	
  <body>    
    
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    <i:location place="editActivity" msg="${msg}"/> 
    
    <f:view>
    	
       <%@include file="/inc/header.jsp" %>
       <h:form id="activity" enctype="multipart/form-data">
       <t:panelTabbedPane activeTabStyleClass="activeTab" 
       					  inactiveTabStyleClass="inactiveTab"
       					  activeSubStyleClass="activeSubcells"
       					  inactiveSubStyleClass="inactiveSubcells"
       					  tabContentStyleClass="noPadding"
       					  styleClass="tabPanel"
       					  selectedIndex="#{activityBean.selectedTabIndex}"
       					  serverSideTabSwitch="true" >
       		<t:tabChangeListener type="#{activityBean.listener}"/>
       					  

			<t:panelTab id="tabActivity" label="#{msg['activity.scheduleTitle']}" rendered="#{activityBean.activityTabRendered}" >
    
	
	<%-- Header --%>
		<i:titleBar msg="${msg}" >
			<t:commandLink action="#{activityBean.list}"
				title="#{msg['activitys.alt.back']}" immediate="true">
				<t:graphicImage value="/img/back.gif" styleClass="titleImg" />
			</t:commandLink>
			
			<c:if test="${! activityBean.selectedProject.finished}">			
				<h:commandLink action="#{activityBean.save}">
					<h:graphicImage value="/img/save.gif" styleClass="titleImg" />
				</h:commandLink>
				<h:commandLink action="#{activityBean.delete}" rendered="#{activityBean.deleteAvailable}"
					onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
					<h:graphicImage value="/img/delete.gif" styleClass="titleImg" />
				</h:commandLink>
			</c:if>
		</i:titleBar>
	

    <%-- Edition form --%>
    <h:panelGrid styleClass="editTable" cellpadding="0" cellspacing="0" columns="2" columnClasses="editLabelRW,editFieldCell" rendered="#{activityBean.activitySelected}">

	  <%-- Non Editable Message --%>
		<c:if test="${activityBean.selectedProject.finished}">
				<h:outputText value=""/>
				<h:outputText styleClass="warning" value="#{msg['editActivity.nonEditableActivityClosedProject']}"/>
        </c:if>
	  <%-- Field: startDateS --%>
	  	<h:outputText value="#{msg['activity.startDate']}:" />
	      <h:outputText value="#{activityBean.startDate}">
	      	<f:convertDateTime pattern="dd/MM/yy" />
	      </h:outputText>

	  <%-- Field: startTime --%>
	  	<h:outputText value="*#{msg['activity.startTime']}:" />
	      <h:panelGroup>
	        <h:message styleClass="error" showSummary="true" showDetail="true" for="startTimeHour" />
	        <t:inputText id="startTimeHour" value="#{activityBean.startTimeHour}" size="2" maxlength="2" validator="#{activityBean.validateHours}">
	        	<t:jsValueChangeListener for="endTimeHour" property="value" expressionValue="actualizeTimeFields($srcElem, 'tabActivity');" />
	        	<f:validateDoubleRange minimum="0" maximum="23"/>
	        </t:inputText>
	        <h:outputText value=":" />
	        <t:inputText id="startTimeMinute" value="#{activityBean.startTimeMinute}" size="2" maxlength="2" validator="#{activityBean.validateHours}">
	        	<t:jsValueChangeListener for="endTimeMinute" property="value" expressionValue="actualizeTimeFields($srcElem, 'tabActivity');" />
	        	<f:validateDoubleRange minimum="0" maximum="59"/>
	        </t:inputText>
	      </h:panelGroup>
	  <%-- Field: endTime --%>
	  	<h:outputText value="*#{msg['activity.finishTime']}:" />
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="true" for="endTimeHour" />
            <t:inputText id="endTimeHour" value="#{activityBean.endTimeHour}" size="2" maxlength="2" validator="#{activityBean.validateHours}">
	        	<t:jsValueChangeListener for="duration" property="value" expressionValue="actualizeTimeFields($srcElem, 'tabActivity');" />
	        	<f:validateDoubleRange minimum="0" maximum="23"/>
	        </t:inputText>
	        <h:outputText value=":" />
	        <t:inputText id="endTimeMinute" value="#{activityBean.endTimeMinute}" size="2" maxlength="2" validator="#{activityBean.validateHours}">
	        	<t:jsValueChangeListener for="duration" property="value" expressionValue="actualizeTimeFields($srcElem, 'tabActivity');" />
	        	<f:validateDoubleRange minimum="0" maximum="59"/>
	        </t:inputText>
          </h:panelGroup>
      
      <%-- Field: duration --%>
        <h:outputText value="*#{msg['activity.duration']}"/>
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="duration" />
            <h:inputText id="duration" value="#{activityBean.duration}" required="true" styleClass="requiredFieldClass" size="3" validator="#{activityBean.validateHours}">
              <t:jsValueChangeListener for="endTimeHour" property="value" expressionValue="actualizeTimeFields($srcElem, 'tabActivity');" />
              <f:converter converterId="autentia.MinuteToHourConverter"/>
              <f:validateDoubleRange minimum="0" />
            </h:inputText>
          </h:panelGroup>

      <%-- Field: description --%>
        <h:outputText value="*#{msg['activity.description']}:"/>
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="description" />
            <h:inputTextarea id="description" value="#{activityBean.description}" required="true" styleClass="requiredFieldClass" rows="5" cols="68" immediate="true"/>
          </h:panelGroup>

      
          <%-- Field: billable --%>
        <h:outputText value="#{msg['activity.billable']}:"/>
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="billable" />
            <h:selectBooleanCheckbox id="billable" value="#{activityBean.billable}" onchange="checkBillable()" />            
            
          </h:panelGroup>
          
 	  
 	 <%-- Field: organization --%>       
      	<h:outputText value="*#{msg['editActivity.organization']}:"/>
	      <h:panelGroup>
	            <h:message styleClass="error" showSummary="true" showDetail="false" for="organization" />
	            <t:selectOneMenu id="organization" value="#{activityBean.selectedOrganization}" onchange="submit()"	
	            	styleClass="requiredFieldClass" valueChangeListener="#{activityBean.onSelectedOrganizationChanged}" >
	              	<f:selectItems value="#{activityBean.organizations}" />
	              	<f:converter converterId="autentia.EntityConverter"/>
	            </t:selectOneMenu>
	      </h:panelGroup>
    
      <%-- Ignored field: user --%>

      <%-- Customized: project list --%>
      	<h:outputText value="*#{msg['editActivity.projects']}:"/>
          <h:panelGroup>
          	
            <h:message styleClass="error" showSummary="true" showDetail="false" for="projects" />
            <t:selectOneListbox id="projects" value="#{activityBean.selectedProject}"  onchange="submit()" size="5"
            				 required="activityBean.projectsVisiblesBySelectedOrganization.size > 0" styleClass="requiredFieldClass" 
            				 valueChangeListener="#{activityBean.onSelectedProjectChanged}" immediate="true" rendered="#{activityBean.projectsRendered}">
              		<f:selectItems value="#{activityBean.projectsVisiblesBySelectedOrganization}" />
              	<f:converter converterId="autentia.EntityConverter"/>
            </t:selectOneListbox>
            
          </h:panelGroup>
          
 	  
      <%-- Field: role --%>
      	<h:outputText value="*#{msg['activity.role']}:"/>
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="role" />
            <t:selectOneRadio layout="pageDirection" id="role" value="#{activityBean.role}" required="true" immediate="true" styleClass="requiredFieldClass" rendered="#{activityBean.rolesRendered}">
              	<f:selectItems value="#{activityBean.rolesBySelectedProject}" />
              	<f:converter converterId="autentia.EntityConverter"/>
            </t:selectOneRadio>
          </h:panelGroup>

		<%-- Field: image --%>
		<h:outputText value="*#{msg['activity.image']}:"/>
		<h:panelGroup>
			<i:activityImageFileLink insertionDate="${activityBean.insertDate}" fileName="${activityBean.imageFileName}"/>
			<h:commandLink action="#{activityBean.deleteImageFile}" rendered="#{activityBean.deleteImageAvailable}"
						   onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
				<h:graphicImage value="/img/delete.gif" styleClass="image-inline" />
			</h:commandLink>
			<h:message styleClass="error" showSummary="true" showDetail="false" for="activityImage" />
			<t:inputFileUpload id="activityImage" size="58" value="#{activityBean.uploadedImage}" storage="file" accept="image/*"/>
			<div class="detailForm">
				<i:imgActivity insertionDate="${activityBean.insertDate}" fileName="${activityBean.imageFileName}"/>
			</div>
		</h:panelGroup>

          
  </h:panelGrid>
  
  	<h:inputHidden id="defaultBillable" value="#{activityBean.defaultBillable}" />

	</t:panelTab>

	<t:panelTab id="tabExternalActivity" label="#{msg['externalActivities.scheduleTitle']}" rendered="#{activityBean.externalActivityTabRendered}" >
	<%-- Header --%>
		<i:titleBar msg="${msg}">
			<h:commandLink action="#{activityBean.list}"
				title="#{msg['activitys.alt.back']}" immediate="true">
				<t:graphicImage value="/img/back.gif" styleClass="titleImg" />
			</h:commandLink>
						
			<h:commandLink action="#{activityBean.saveExternalActivity}">
				<h:graphicImage value="/img/save.gif" styleClass="titleImg" />
			</h:commandLink>
			<h:commandLink action="#{activityBean.deleteExternalActivity}" rendered="#{activityBean.deleteExternalActivityAvailable}"
				onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
				<h:graphicImage value="/img/delete.gif" styleClass="titleImg" />
			</h:commandLink>
		</i:titleBar>
	
    <%-- Edition form --%>
    <h:panelGrid styleClass="editTable" cellpadding="0" cellspacing="0" columns="2" columnClasses="editLabelRW,editFieldCell" rendered="#{activityBean.externalActivitySelected}">

	  <%-- Field: startDateS --%>
	  	<h:outputText value="#{msg['externalActivity.startDate']}:" />
	      <h:outputText value="#{activityBean.externalActivityStartDate}">
	      	<f:convertDateTime pattern="dd/MM/yy" />
	      </h:outputText>

	  
	  <%-- Field: startTime --%>
	  	<h:outputText value="*#{msg['externalActivity.startTime']}:" />
	      <h:panelGroup>
	        <h:message styleClass="error" showSummary="true" showDetail="true" for="startTimeHour" />
	        <t:inputText id="startTimeHour" value="#{activityBean.externalActivityStartTimeHour}" size="2" maxlength="2" validator="#{activityBean.validateHours}" required="#{activityBean.selectedTab == 1}" immediate="true">
	        	<t:jsValueChangeListener for="endTimeHour" property="value" expressionValue="actualizeTimeFields($srcElem, 'tabExternalActivity');" />
	        	<f:validateDoubleRange minimum="0" maximum="23"/>
	        </t:inputText>
	        <h:outputText value=":" />
	        <t:inputText id="startTimeMinute" value="#{activityBean.externalActivityStartTimeMinute}" size="2" maxlength="2" validator="#{activityBean.validateHours}" required="#{activityBean.selectedTab == 1}" immediate="true">
	        	<t:jsValueChangeListener for="endTimeMinute" property="value" expressionValue="actualizeTimeFields($srcElem, 'tabExternalActivity');" />
	        	<f:validateDoubleRange minimum="0" maximum="59"/>
	        </t:inputText>
	      </h:panelGroup>
	  <%-- Field: endTime --%>
	  	<h:outputText value="*#{msg['externalActivity.finishTime']}:" />
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="true" for="endTimeHour" />
            <t:inputText id="endTimeHour" value="#{activityBean.externalActivityEndTimeHour}" size="2" maxlength="2" validator="#{activityBean.validateHours}" required="#{activityBean.selectedTab == 1}" immediate="true">
            	<t:jsValueChangeListener for="duration" property="value" expressionValue="actualizeTimeFields($srcElem, 'tabExternalActivity');" />
	        	<f:validateDoubleRange minimum="0" maximum="23"/>
	        </t:inputText>
	        <h:outputText value=":" />
	        <t:inputText id="endTimeMinute" value="#{activityBean.externalActivityEndTimeMinute}" size="2" maxlength="2" validator="#{activityBean.validateHours}" required="#{activityBean.selectedTab == 1}" immediate="true">
	        	<t:jsValueChangeListener for="duration" property="value" expressionValue="actualizeTimeFields($srcElem, 'tabExternalActivity');" />
	        	<f:validateDoubleRange minimum="0" maximum="59"/>
	        </t:inputText>
          </h:panelGroup>
     <%-- Field: duration --%>
        <h:outputText value="#{msg['externalActivity.duration']}"/>
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="duration" />
            <h:inputText id="duration" value="#{activityBean.externalActivityDuration}" required="false" size="3" validator="#{activityBean.validateHours}"  >
              <t:jsValueChangeListener for="endTimeHour" property="value" expressionValue="actualizeTimeFields($srcElem, 'tabExternalActivity');" />
              <f:converter converterId="autentia.MinuteToHourConverter"/>
              <f:validateDoubleRange minimum="0" />
            </h:inputText>
          </h:panelGroup>
     <%-- Field: name --%>
     	<h:outputText value="*#{msg['externalActivity.name']}:" />
     	<h:panelGroup>
     		<h:message styleClass="error" showSummary="true" showDetail="true" for="externalActivityName" />
     		<t:inputText id="externalActivityName" value="#{activityBean.name}" size="68" maxlength="256" required="#{activityBean.selectedTab == 1}" immediate="true">
	        </t:inputText>
	    </h:panelGroup>
	 
	  <%-- Field: category --%>       
	 <h:outputText value="*#{msg['externalActivity.category']}:" />
	 <h:panelGroup>
     		<h:message styleClass="error" showSummary="true" showDetail="true" for="externalActivityCategory" />
     		<t:inputText id="externalActivityCategory" value="#{activityBean.category}" size="68" maxlength="256" required="#{activityBean.selectedTab == 1}" immediate="true">
	        </t:inputText>
	 </h:panelGroup>
	        
	  <%-- Field: organizer --%>              
	 <h:outputText value="*#{msg['externalActivity.organizer']}:" />
	 <h:panelGroup>
     		<h:message styleClass="error" showSummary="true" showDetail="true" for="externalActivityOrganizer" />
     		<t:inputText id="externalActivityOrganizer" value="#{activityBean.organizer}" size="68" maxlength="256" required="#{activityBean.selectedTab == 1}" immediate="true">
	        </t:inputText>
	 </h:panelGroup>
	 
	 <%-- Field: location --%>              
	 <h:outputText value="*#{msg['externalActivity.location']}:" />
	 <h:panelGroup>
     		<h:message styleClass="error" showSummary="true" showDetail="true" for="externalActivityLocation" />
     		<t:inputText id="externalActivityLocation" value="#{activityBean.location}" size="68" maxlength="256" required="#{activityBean.selectedTab == 1}" immediate="true">
	        </t:inputText>
     </h:panelGroup>
          
     <%-- Field: description --%>
        <h:outputText value="*#{msg['externalActivity.comments']}:"/>
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="comments" />
            <h:inputTextarea id="comments" value="#{activityBean.comments}" styleClass="requiredFieldClass" rows="5" cols="68" immediate="true" required="#{activityBean.selectedTab == 1}"/>
          </h:panelGroup>
        
        <%-- Field: file --%>
          
            <h:outputText value="#{msg['commissioning.files']}:"/>
            
          	<h:panelGroup>  
			   <t:inputFileUpload id="file" size="58" maxlength="400" value="#{activityBean.uploadFile}" storage="file" />
     			<h:message styleClass="error" showSummary="true" showDetail="false" for="file" />
            	<t:dataTable id="list" var="file"
					value="#{activityBean.files}"
					preserveDataModel="false" cellpadding="0" cellspacing="0"
					styleClass="listTable" headerClass="listHeaderCell"
					footerClass="listFooter" rows="#{settingBean.mySettings.listSize}"
					rowClasses="listRowO,listRowE"
					columnClasses="listCmdCell,listCommissioningId,listCommissioningRequestDate,listCommissioningName,listCommissioningScope,listCommissioningContent,listCommissioningProducts,listCommissioningDeliveryDate,listCommissioningBudget,listCommissioningPaymentMode,listCommissioningNotes,listCommissioningAuthorSignature,listCommissioningReviserSignature,listCommissioningAdminSignature,listCommissioningJustifyInformation,listCommissioningDevelopedActivities,listCommissioningDifficultiesAppeared,listCommissioningResults,listCommissioningConclusions,listCommissioningEvaluation,listCommissioningStatus,listCommissioningInsertDate,listCommissioningUpdateDate,listCommissioningReviser,listCommissioningProject"
					rowOnMouseOver="this.savedClassName=this.className;this.className='listRowSel';"
					rowOnMouseOut="this.className=this.savedClassName;">
		
					<h:column>
						<f:facet name="header">
							<f:verbatim>-</f:verbatim>
						</f:facet>
						<h:commandLink action="#{activityBean.deleteFile}">
							<h:graphicImage value="/img/delete.gif" styleClass="titleImg" />
						</h:commandLink>
					</h:column>
		
					<h:column>
						<f:facet name="header">
							<f:verbatim>${msg['commissioningFile.insertDate']}</f:verbatim>
						</f:facet>
		
						<h:outputText value="#{file.insertDate}"
							converter="autentia.dateConverter" />
		
					</h:column>
		
					<h:column>
						<f:facet name="header">
							<f:verbatim>${msg['commissioningFile.file']}</f:verbatim>
						</f:facet>
				 
				 		<h:commandLink onclick="openFile('externalActivity', #{activityBean.externalActivityId}, '#{file.file}','#{file.fileMime}'); return false;">
							<h:graphicImage value="/img/yellow-folder-open.png" style="border:0; vertical-align:middle;"/>
							<h:outputText value="#{file.file}"/>
						</h:commandLink>
		
					</h:column>
					
				</t:dataTable>
              
            </h:panelGroup>

						
   
  </h:panelGrid>
	</t:panelTab>
</t:panelTabbedPane>
	</h:form>
</f:view>

</body>
</html>
