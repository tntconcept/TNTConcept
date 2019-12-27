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
    <i:location place="editDepartment" msg="${msg}"/> 
    
    <f:view>
       <%@include file="/inc/header.jsp" %>
      <h:form id="department" enctype="multipart/form-data">
        
        <%-- Header --%>
        <i:titleBar name="${departmentBean.name}" msg="${msg}">
          <h:commandLink action="#{departmentBean.save}" onclick="if(!askSave('#{departmentBean.id}','#{msg['question.confirmSave']}')) return false;">
            <h:graphicImage value="/img/save.gif" styleClass="titleImg" />
          </h:commandLink>
          <h:commandLink action="#{departmentBean.delete}" 
          	rendered="#{departmentBean.deleteAvailable}"
          	onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage value="/img/delete.gif" styleClass="titleImg" />
          </h:commandLink>
          <h:commandLink action="#{departmentBean.list}" immediate="true">
            <h:graphicImage value="/img/back.gif" styleClass="titleImg" />
          </h:commandLink>
        </i:titleBar>
         <h:message styleClass="error" showSummary="true" showDetail="false" for="department" />
        <%-- Edition form --%>
        <table class="editTable" cellpadding="0" cellspacing="0">
          
    
      <%-- Field: name --%>
    <tr>
    	    		<td class="editLabelRW">*${msg['department.name']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="name" />
                		<h:inputText id="name" value="#{departmentBean.name}" size="70" maxlength="128" required="true" styleClass="requiredFieldClass"/>
    	              </h:panelGroup>

              </td>
    </tr>
                                  
  
      <%-- Field: description --%>
    <tr>
    	    		<td class="editLabelRW">${msg['department.description']}:</td>
    	      
      <td class="editFieldCell">

                  <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="description" />
            <h:inputTextarea id="description" value="#{departmentBean.description}" rows="5" cols="68"/>
          </h:panelGroup>

              </td>
    </tr>

	<%-- Field: parent --%>
    <tr>
		<td class="editLabelRW">${msg['department.parent']}:</td>
		<td class="editFieldCell">
			<h:panelGroup>
            	<h:message styleClass="error" showSummary="true" showDetail="false" for="parent" />
            	<h:selectOneMenu id="parent" value="#{departmentBean.parent}" >
              		<f:selectItems value="#{departmentBean.parents}" />
              		<f:converter converterId="autentia.EntityConverter"/>
            	</h:selectOneMenu>
			</h:panelGroup>
		</td>
    </tr>
                                      
	<%-- Field: positions --%>
    <tr>
		<td class="editLabelRW">${msg['department.positions']}:</td>
		<td class="editFieldCell" colspan="2">
			<h:panelGroup>

				<h:panelGroup rendered="#{departmentBean.arePositionsToSelect}">
					<h:selectOneMenu id="positionsList" value="#{departmentBean.selectedPosition}">
						<f:selectItems value="#{departmentBean.allPositions}" />
						<f:converter converterId="autentia.EntityConverter"/>
						<f:attribute name="com.autentia.tnt.converter.EntityConverter.valueClass" 
	                        	  value="com.autentia.tnt.businessobject.Position"/> 
					</h:selectOneMenu>
					<h:commandLink action="#{departmentBean.addPosition}">
						<h:graphicImage value="/img/add.png" styleClass="titleImg" />
					</h:commandLink>
				</h:panelGroup>
				<h:panelGroup rendered="#{!departmentBean.arePositionsToSelect}">
					<h:graphicImage value="/img/updated.gif" styleClass="titleImg" />
					<h:outputText value="#{msg['position.warn.noTags']}"  />
				</h:panelGroup>		
	
				<h:panelGroup rendered="#{departmentBean.arePositionsSelected}">
					<h:message styleClass="error" showSummary="true" showDetail="false"
						for="positions" />
					<t:dataTable id="positions" var="positionData"
						value="#{departmentBean.departmentPositions}"
						preserveDataModel="false" cellpadding="0" cellspacing="0"
						styleClass="listTable" headerClass="listHeaderCell"
						footerClass="listFooter" rows="#{settingBean.mySettings.listSize}"
						rowClasses="listRowO,listRowE"
						columnClasses="listCmdCell"
						rowOnMouseOver="this.savedClassName=this.className;this.className='listRowSel';"
						rowOnMouseOut="this.className=this.savedClassName;">					
						<h:column rendered="#{!positionData.deleted}">
							<f:facet name="header">
								<f:verbatim>-</f:verbatim>
							</f:facet>
							<h:commandLink action="#{departmentBean.deletePosition}">
								<h:graphicImage value="/img/delete.gif" styleClass="titleImg" />
							</h:commandLink>
						</h:column>						
						<h:column rendered="#{!positionData.deleted}">
							<f:facet name="header">
								<f:verbatim>${msg['position.name']}</f:verbatim>
							</f:facet>
							<h:outputText value="#{positionData.name}"  />
						</h:column>
						<h:column rendered="#{!positionData.deleted}">
							<f:facet name="header">
								<f:verbatim>${msg['position.description']}</f:verbatim>
							</f:facet>
							<h:outputText value="#{positionData.description}"  />
						</h:column>							
					</t:dataTable>
				</h:panelGroup>
				
			</h:panelGroup>
		</td>
	</tr>
	
	<%-- Field: tags --%>
    <tr>
		<td class="editLabelRW">${msg['department.tags']}:</td>
		<td class="editFieldCell" colspan="2">
			<h:panelGroup>
				<h:panelGroup rendered="#{departmentBean.areTagsToSelect}">
					<h:selectOneMenu id="tagsList" value="#{departmentBean.selectedTag}">
						<f:selectItems value="#{departmentBean.allTags}" />
						<f:converter converterId="autentia.EntityConverter"/>
						<f:attribute name="com.autentia.tnt.converter.EntityConverter.valueClass" 
	                        	  value="com.autentia.tnt.businessobject.Tag"/> 
					</h:selectOneMenu>
					<h:commandLink action="#{departmentBean.addTag}">
						<h:graphicImage value="/img/add.png" styleClass="titleImg" />
					</h:commandLink>
				</h:panelGroup>
				<h:panelGroup rendered="#{!departmentBean.areTagsToSelect}">
					<h:graphicImage value="/img/updated.gif" styleClass="titleImg" />
					<h:outputText value="#{msg['position.warn.noTags']}"  />
				</h:panelGroup>	

				<h:panelGroup rendered="#{departmentBean.areTagsSelected}">
					<h:message styleClass="error" showSummary="true" showDetail="false"
						for="tags" />
					<t:dataTable id="tags" var="tagData"
						value="#{departmentBean.departmentTags}"
						preserveDataModel="false" cellpadding="0" cellspacing="0"
						styleClass="listTable" headerClass="listHeaderCell"
						footerClass="listFooter" rows="#{settingBean.mySettings.listSize}"
						rowClasses="listRowO,listRowE"
						columnClasses="listCmdCell"
						rowOnMouseOver="this.savedClassName=this.className;this.className='listRowSel';"
						rowOnMouseOut="this.className=this.savedClassName;">						
						<h:column>
							<f:facet name="header">
								<f:verbatim>-</f:verbatim>
							</f:facet>
							<h:commandLink action="#{departmentBean.deleteTag}">
								<h:graphicImage value="/img/delete.gif" styleClass="titleImg" />
							</h:commandLink>
						</h:column>						
						<h:column>
							<f:facet name="header">
								<f:verbatim>${msg['tag.name']}</f:verbatim>
							</f:facet>
							<h:outputText value="#{tagData.name}"  />
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>${msg['tag.description']}</f:verbatim>
							</f:facet>
							<h:outputText value="#{tagData.description}"  />
						</h:column>							
					</t:dataTable>
				</h:panelGroup>
			</h:panelGroup>
		</td>
	</tr>	
	
</table>
        
      </h:form>
    </f:view>
    
  </body>
</html>  		
