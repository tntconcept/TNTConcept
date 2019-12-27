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

<%-- 
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.  
 *	Copyright (C) 2007 Autentia Real Bussiness Solution S.L.					   
 *	
 * 	This program is free software; you can redistribute it and/or
 * 	modify it under the terms of the GNU General Public License
 * 	as published by the Free Software Foundation; either version 2
 * 	of the License, or (at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program; if not, write to the Free Software
 * 	Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 	Autentia Real Bussiness Solution S.L.
 * 	Tlf: +34 91 675 33 06, +34 655 99 11 72
 * 	Fax: +34 91 656 65 04
 * 	info@autentia.com																
 *
 --%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of contacts --%>
<t:dataTable id="contacts" var="item" value="#{organizationBean.contacts}" preserveDataModel="false" 
    cellpadding="0" cellspacing="0" styleClass="editListTable" 
    headerClass="editListHeaderCell" footerClass="editListFooter" 
    rows="#{settingBean.mySettings.listSize}" rowClasses="editListRowO,editListRowE"
    columnClasses="listCmdCell,editListContactName,editListContactEmail,editListContactPhone,editListContactMobile,editListContactNotified,editListContactPosition,editListContactOwnerId,editListContactDepartmentId,editListContactInsertDate,editListContactUpdateDate">

  <%-- Commands --%>
  <h:column>
    <f:facet name="header">
      <t:commandLink action="#{organizationBean.createContacts}">
        <h:graphicImage title="#{msg.entityActions_new}"  value="/img/new.gif" styleClass="cmdImg" />
      </t:commandLink>
    </f:facet>
    <t:commandLink action="#{organizationBean.deleteContacts}">
      <h:graphicImage title="#{msg.entityActions_delete}"  value="/img/delete.gif" styleClass="cmdImg" />
    </t:commandLink>
  </h:column>


    
  
      <%-- Ignored field: id --%>
  
  
    
    
      
    <h:column>

      <f:facet name="header">
      	      		<h:outputText value="*#{msg['contact.name']}" styleClass="editListHeader"/>
      	      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="name" />
          <h:inputText id="name" value="#{item.name}" size="70" maxlength="150" required="true" styleClass="requiredFieldClass"/>
        </h:panelGroup>

      
    </h:column>

    
    
  
      
    <h:column>

      <f:facet name="header">
      		        <h:outputText value="#{msg['contact.email']}" styleClass="editListHeader"/>
	          </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="email" />
          <h:inputText id="email" value="#{item.email}" size="70" maxlength="128"/>
        </h:panelGroup>

      
    </h:column>

    
    
  
      
    <h:column>

      <f:facet name="header">
      		        <h:outputText value="#{msg['contact.phone']}" styleClass="editListHeader"/>
	          </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="phone" />
          <h:inputText id="phone" value="#{item.phone}" maxlength="15"/>
        </h:panelGroup>

      
    </h:column>

    
    
  
      
    <h:column>

      <f:facet name="header">
      		        <h:outputText value="#{msg['contact.mobile']}" styleClass="editListHeader"/>
	          </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="mobile" />
          <h:inputText id="mobile" value="#{item.mobile}" maxlength="15"/>
        </h:panelGroup>

      
    </h:column>

    
    
    
      
    <h:column>

      <f:facet name="header">
      	      		<h:outputText value="*#{msg['contact.notified']}" styleClass="editListHeader"/>
      	      </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="notified" />
          <h:selectBooleanCheckbox id="notified" value="#{item.notified}"  required="true" styleClass="requiredFieldClass"/>
        </h:panelGroup>

      
    </h:column>

    
    
  
      
    <h:column>

      <f:facet name="header">
      		        <h:outputText value="#{msg['contact.position']}" styleClass="editListHeader"/>
	          </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="position" />
          <h:inputText id="position" value="#{item.position}" size="70" maxlength="128"/>
        </h:panelGroup>

      
    </h:column>

    
    
  
      
    <h:column>

      <f:facet name="header">
      		        <h:outputText value="#{msg['contact.ownerId']}" styleClass="editListHeader"/>
	          </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="ownerId" />
          <h:inputText id="ownerId" value="#{item.ownerId}" size="10" />
        </h:panelGroup>

      
    </h:column>

    
    
  
      
    <h:column>

      <f:facet name="header">
      		        <h:outputText value="#{msg['contact.departmentId']}" styleClass="editListHeader"/>
	          </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="departmentId" />
          <h:inputText id="departmentId" value="#{item.departmentId}" size="10" />
        </h:panelGroup>

      
    </h:column>

    
    
  
      
    <h:column>

      <f:facet name="header">
      		        <h:outputText value="#{msg['contact.insertDate']}" styleClass="editListHeader"/>
	          </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="insertDate" />
          <t:inputCalendar id="insertDate" value="#{item.insertDate}" 
                           renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true" 
                           popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}" />
        </h:panelGroup>

      
    </h:column>

    
    
  
      
    <h:column>

      <f:facet name="header">
      		        <h:outputText value="#{msg['contact.updateDate']}" styleClass="editListHeader"/>
	          </f:facet>

              <h:panelGroup>
          <h:message styleClass="error" showSummary="true" showDetail="false" for="updateDate" />
          <t:inputCalendar id="updateDate" value="#{item.updateDate}" 
                           renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true" 
                           popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}" />
        </h:panelGroup>

      
    </h:column>

  
  
    
    
      <%-- Ignored field: organization --%>
  

</t:dataTable>



















                
                

                

                

