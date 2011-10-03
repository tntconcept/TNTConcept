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
    
    <!-- editIdea.jsp: generated by stajanov code generator -->
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    <i:location place="detailIdea" msg="${msg}"/> 
    
    <f:view>
      <%@include file="/inc/header.jsp" %>
      <h:form id="idea" enctype="multipart/form-data">
        
        <%-- Header --%>
        <i:titleBar name="${ideaBean.name}" msg="${msg}">
          <t:commandLink action="#{ideaBean.edit}" immediate="true" rendered="#{ideaBean.editAvailable}">
            <f:param name="rowid" value="#{ideaBean.id}" />
            <h:graphicImage title="#{msg.entityActions_edit}"  value="/img/edit.gif" styleClass="cmdImg" />
          </t:commandLink>
          <h:commandLink action="#{ideaBean.delete}" rendered="#{ideaBean.deleteAvailable}" onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}"  value="/img/delete.gif" styleClass="titleImg" />
          </h:commandLink>
          <h:commandLink action="#{ideaBean.list}" immediate="true">
            <h:graphicImage title="#{msg.entityActions_back}"  value="/img/back.gif" styleClass="titleImg" />
          </h:commandLink>
        </i:titleBar>


        <%-- Detail form --%>
        <table class="detailTable" cellpadding="0" cellspacing="0">

            
    
  
  
      <%-- Ignored field: id --%>
  
                            
    
  
  
      <%-- Field: creationDate --%>
    <tr>
	<td class="detailLabelRW">${msg['idea.creationDate']}:</td>
	<td class="detailFieldCell">
         	    	<h:outputText value="#{ideaBean.creationDate}" converter="autentia.dateConverter" />
 	          </td>
    </tr>
                            
    
  
  
      <%-- Field: name --%>
    <tr>
	<td class="detailLabelRW">${msg['idea.name']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{ideaBean.name}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: description --%>
    <tr>
	<td class="detailLabelRW">${msg['idea.description']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{ideaBean.description}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: benefits --%>
    <tr>
	<td class="detailLabelRW">${msg['idea.benefits']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{ideaBean.benefits}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: cost --%>
    <tr>
	<td class="detailLabelRW">${msg['idea.cost']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{ideaBean.cost}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Ignored field: departmentId --%>
  
                            
    
  
  
      <%-- Ignored field: insertDate --%>
  
                            
    
  
  
      <%-- Ignored field: updateDate --%>
  
                                        
    
  
  
      <%-- Field: user --%>
    <tr>
	<td class="detailLabelRW">${msg['idea.user']}:</td>
	<td class="detailFieldCell">
         	    	<h:outputText value="#{ideaBean.user.name}"/>
 	          </td>
    </tr>
                                      
        </table>

      </h:form>
    </f:view>
    
  </body>
</html>  		

<%-- idea - generated by stajanov (do not edit/delete) --%>
