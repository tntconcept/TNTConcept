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

<%-- List of documentCategorys --%>
<t:dataTable id="categories" var="item" value="#{documentBean.categories}" preserveDataModel="false" 
    cellpadding="0" cellspacing="0" styleClass="detailListTable" 
    headerClass="detailListHeaderCell" footerClass="detailListFooter" 
    rows="#{settingBean.mySettings.listSize}" rowClasses="detailListRowO,detailListRowE"
    columnClasses="listCmdCell,detailListDocumentCategoryName,detailListDocumentCategoryDescription,detailListDocumentCategoryCode,detailListDocumentCategoryOwnerId,detailListDocumentCategoryDepartmentId,detailListDocumentCategoryInsertDate,detailListDocumentCategoryUpdateDate">


  
      <%-- Ignored field: id --%>
  
  
  
  
    	
  	<h:column>

      <f:facet name="header">
        <h:outputText value="#{msg['documentCategory.name']}" styleClass="detailListHeader"/>
      </f:facet>
  	
    	<%-- Field: name --%>
        
         	       <h:outputText value="#{item.name}" />
 	    
    </h:column>
    
  
    	
  	<h:column>

      <f:facet name="header">
        <h:outputText value="#{msg['documentCategory.description']}" styleClass="detailListHeader"/>
      </f:facet>
  	
    	<%-- Field: description --%>
        
         	       <h:outputText value="#{item.description}" />
 	    
    </h:column>
    
  
    	
  	<h:column>

      <f:facet name="header">
        <h:outputText value="#{msg['documentCategory.code']}" styleClass="detailListHeader"/>
      </f:facet>
  	
    	<%-- Field: code --%>
        
         	       <h:outputText value="#{item.code}" />
 	    
    </h:column>
    
  
      <%-- Ignored field: documentsLastUpdate --%>
  
    
  
    	
  	<h:column>

      <f:facet name="header">
        <h:outputText value="#{msg['documentCategory.ownerId']}" styleClass="detailListHeader"/>
      </f:facet>
  	
    	<%-- Field: ownerId --%>
        
         	       <h:outputText value="#{item.ownerId}" />
 	    
    </h:column>
    
  
    	
  	<h:column>

      <f:facet name="header">
        <h:outputText value="#{msg['documentCategory.departmentId']}" styleClass="detailListHeader"/>
      </f:facet>
  	
    	<%-- Field: departmentId --%>
        
         	       <h:outputText value="#{item.departmentId}" />
 	    
    </h:column>
    
  
    	
  	<h:column>

      <f:facet name="header">
        <h:outputText value="#{msg['documentCategory.insertDate']}" styleClass="detailListHeader"/>
      </f:facet>
  	
    	<%-- Field: insertDate --%>
        
         	    	<h:outputText value="#{item.insertDate}" />
 	    
    </h:column>
    
  
    	
  	<h:column>

      <f:facet name="header">
        <h:outputText value="#{msg['documentCategory.updateDate']}" styleClass="detailListHeader"/>
      </f:facet>
  	
    	<%-- Field: updateDate --%>
        
         	    	<h:outputText value="#{item.updateDate}" />
 	    
    </h:column>
  
  
  
      <%-- Ignored field: padre --%>
  
  

</t:dataTable>