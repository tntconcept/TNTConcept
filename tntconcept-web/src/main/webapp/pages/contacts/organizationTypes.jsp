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

<html>
  <head>
    <%@include file="/inc/uiCore.jsp" %>
  </head>	
  <body>

    <!-- organizationTypes.jsp: generated by stajanov code generator -->
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    <i:location place="organizationTypes" msg="${msg}"/> 
    
    <f:view>
     <%@include file="/inc/header.jsp" %>
      <h:form id="organizationTypes">
        
        <%-- Header --%>
        <i:titleBar msg="${msg}">
          <h:commandLink action="#{organizationTypeBean.search}">
            <h:graphicImage rendered="#{ organizationTypeBean.search.searchActive}" title="#{msg.entityActions_filtered}"  value="/img/search_applied.gif" styleClass="titleImg" />
            <h:graphicImage rendered="#{!organizationTypeBean.search.searchActive}" title="#{msg.entityActions_search}"    value="/img/search.gif"         styleClass="titleImg" />
          </h:commandLink>
           <h:commandLink action="#{organizationTypeBean.reset}">
            <h:graphicImage rendered="#{organizationTypeBean.search.searchActive}" title="#{msg.entityActions_reset}"  value="/img/eraser.png" styleClass="titleImg" />           
          </h:commandLink>
          <h:commandLink action="#{organizationTypeBean.create}" rendered="#{organizationTypeBean.createAvailable}">
            <h:graphicImage title="#{msg.entityActions_create}"  value="/img/new.gif" styleClass="titleImg" />
          </h:commandLink>
        </i:titleBar>
        
        
          <%-- ABC quick pager --%>
          <div class="abcPager">
            <h:outputText value="#{msg['abcPager.title']}" styleClass="abcPagerTitle"/>
            <a:abcPager styleClass="abcPagerLetter" selectedStyleClass="abcPagerSelectedLetter" 
                        value="#{organizationTypeBean.letter}" action="#{organizationTypeBean.letterClicked}"
                        allowUnselect="true" unselectImage="/img/no_funnel.png"/>
          </div>

        
        <%-- List of organizationTypes --%>
        <t:dataTable id="list" var="organizationType" value="#{organizationTypeBean.all}" preserveDataModel="false" 
            cellpadding="0" cellspacing="0" styleClass="listTable" 
            headerClass="listHeaderCell" footerClass="listFooter" 
            rows="#{settingBean.mySettings.listSize}" rowClasses="listRowO,listRowE"
            columnClasses="listCmdCell,listOrganizationTypeName,listOrganizationTypeDescription"
            sortColumn="#{organizationTypeBean.sortColumn}" sortAscending="#{organizationTypeBean.sortAscending}"
	    rowOnMouseOver="this.savedClassName=this.className;this.className='listRowSel';" 
	    rowOnMouseOut="this.className=this.savedClassName;">
          
          <%-- Commands --%>
          <h:column>
            <f:facet name="header">
              <f:verbatim>-</f:verbatim>
            </f:facet>
            <t:commandLink action="#{organizationTypeBean.detail}" immediate="true">
              <f:param name="rowid" value="#{organizationType.id}" />
              <h:graphicImage title="#{msg.entityActions_detail}"  value="/img/detail.gif" styleClass="cmdImg" />
            </t:commandLink>
          </h:column>


      <%-- Ignored field: id --%>
  

      <%-- Field: name --%>
    <h:column>
      <f:facet name="header">
      	        <t:commandSortHeader styleClass="listHeader" columnName="name">
                 <f:facet name="ascending">
            <t:graphicImage value="/img/ascending-arrow.gif" border="0" />
          </f:facet>
          <f:facet name="descending">
            <t:graphicImage value="/img/descending-arrow.gif" border="0" />
          </f:facet>
          <f:verbatim>${msg['organizationType.name']}</f:verbatim>
        </t:commandSortHeader>
      </f:facet>

      <t:commandLink action="#{organizationTypeBean.detail}" immediate="true">
	<f:param name="rowid" value="#{organizationType.id}" />
		  <%-- String field --%>
	  <h:outputText value="#{organizationType.name}" />
	      </t:commandLink>

    </h:column>
  

      <%-- Field: description --%>
    <h:column>
      <f:facet name="header">
      	        <t:commandSortHeader styleClass="listHeader" columnName="description">
                 <f:facet name="ascending">
            <t:graphicImage value="/img/ascending-arrow.gif" border="0" />
          </f:facet>
          <f:facet name="descending">
            <t:graphicImage value="/img/descending-arrow.gif" border="0" />
          </f:facet>
          <f:verbatim>${msg['organizationType.description']}</f:verbatim>
        </t:commandSortHeader>
      </f:facet>

      <t:commandLink action="#{organizationTypeBean.detail}" immediate="true">
	<f:param name="rowid" value="#{organizationType.id}" />
		  <%-- String field --%>
	  <h:outputText value="#{organizationType.description}" />
	      </t:commandLink>

    </h:column>
  

      <%-- Ignored field: ownerId --%>
  

      <%-- Ignored field: departmentId --%>
  

      <%-- Ignored field: insertDate --%>
  

      <%-- Ignored field: updateDate --%>
  
        </t:dataTable>
          
        <%-- Paginator control --%>
        <%@include file="/inc/paginator.jsp" %>
        
      </h:form>
    </f:view>
    
  </body>
</html>  		

