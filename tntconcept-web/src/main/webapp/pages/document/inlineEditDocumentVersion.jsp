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

<%-- List of documentVersions --%>
<t:dataTable id="versions" var="item" value="#{documentBean.versions}" preserveDataModel="false" 
    cellpadding="0" cellspacing="0" styleClass="editListTable" 
    headerClass="editListHeaderCell" footerClass="editListFooter" 
    rows="#{settingBean.mySettings.listSize}" rowClasses="editListRowO,editListRowE"
    columnClasses="listCmdCell,editListDocumentVersionDocumentPath,editListDocumentVersionCreationDate,editListDocumentVersionVersion">

  <%-- Commands --%>
  <h:column>
    <f:facet name="header">
      <t:commandLink action="#{documentBean.createVersions}">
        <h:graphicImage title="#{msg.entityActions_new}"  value="/img/new.gif" styleClass="cmdImg" />
      </t:commandLink>
    </f:facet>
    <t:commandLink action="#{documentBean.deleteVersions}">
      <h:graphicImage title="#{msg.entityActions_delete}"  value="/img/delete.gif" styleClass="cmdImg" />
    </t:commandLink>
  </h:column>


    
  
      <%-- Ignored field: id --%>
  
  
    
    
      
    <h:column>

      <f:facet name="header">
      	      		<h:outputText value="*#{msg['documentVersion.documentPath']}" styleClass="editListHeader"/>
      	      </f:facet>

      		<f:verbatim>
			<h:outputText value="<a href=\"#\" onclick=\"openDocumentFile('#{item.documentPath}');return false;\"><img src='../../img/yellow-folder-open.png' style='border:0; vertical-align:middle;'>&nbsp;#{item.documentPath}</a>" escape="false"/>
		</f:verbatim>
	    <h:panelGroup>
	      <h:message styleClass="error" showSummary="true" showDetail="true" for="uploadDocument" />
	      <t:inputFileUpload id="uploadDocument" size="40" value="#{item.uploadDocument}" storage="file" rendered="#{item.documentPath==null}" required="true" styleClass="requiredFieldClass"  />
	    </h:panelGroup>

      
    </h:column>

    
    
    
      
    <h:column>

      <f:facet name="header">
      	      		<h:outputText value="*#{msg['documentVersion.creationDate']}" styleClass="editListHeader"/>
      	      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="creationDate" />
          <h:outputText id="creationDate" value="#{item.creationDate}" />
        </h:panelGroup>

      
    </h:column>

    
    
    
      
    <h:column>

      <f:facet name="header">
      	      		<h:outputText value="*#{msg['documentVersion.version']}" styleClass="editListHeader"/>
      	      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="version" />
          <h:inputText id="version" value="#{item.version}" maxlength="255" required="true" styleClass="requiredFieldClass"/>
        </h:panelGroup>

      
    </h:column>

    
    
  
      <%-- Ignored field: ownerId --%>
    
    
  
      <%-- Ignored field: departmentId --%>
    
    
  
      <%-- Ignored field: insertDate --%>
    
    
  
      <%-- Ignored field: updateDate --%>
  
  
    
  
      <%-- Ignored field: document --%>
  

</t:dataTable>



















                
                

                

                

