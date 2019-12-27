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
    <i:location place="nof" msg="${msg}"/> 
    
    <f:view>
      <%@include file="/inc/header.jsp" %>
      <h:form id="nof">
      
        <%-- Header --%>
        <i:titleBar msg="${msg}">
        </i:titleBar>
        
        <%-- Dates --%>
        <h:panelGrid columns="2" cellpadding="0" cellspacing="0" styleClass="editTable" columnClasses="editLabelRW,editFieldCell">
          <h:outputText value="#{msg['nof.actualDate']}:" />
          <h:outputText value="#{nofBean.actualDate }">
        		<f:convertDateTime pattern="dd/MM/yyyy" type="date"/>
          </h:outputText>
         
          <h:outputText value="#{msg['nof.endDate']}:" />
          <t:inputCalendar id="endDate" value="#{nofBean.endDate}" required="true" styleClass="requiredFieldClass"
	                       renderAsPopup="true" popupDateFormat="dd/MM/yyyy" renderPopupButtonAsImage="true" onchange="submit()"
	                       popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                           >
			<f:validator validatorId="autentia.dateValidator"/>
 		  </t:inputCalendar>	                       
        </h:panelGrid>

       	<t:dataTable id="list" var="genericNOF" value="#{nofBean.allPrevisionIncomes}"
	            cellpadding="0" cellspacing="0" styleClass="listTable" 
	            headerClass="listHeaderCell" footerClass="listFooter" 
	            rows="#{settingBean.mySettings.listSize}" rowClasses="listRowO,listRowE" preserveDataModel="falses"
	            columnClasses="listBillNOFExpirationDate,listBillNOFBillType,listBillNOFNumber,listBillNOFProvider,listBillNOFName,listBillNOFAmount,listBillNOFExpirated">
	          	
	          	  <%-- Commands
		          <h:column>
		            <f:facet name="header">
		              <f:verbatim>-</f:verbatim>
		            </f:facet>
		            <t:commandLink action="#{billBean.detail}" immediate="true">
		              <f:param name="rowid" value="#{genericNOF.id}" />
		              <h:graphicImage title="#{msg.entityActions_detail}"  value="/img/detail.gif" styleClass="cmdImg" />
		            </t:commandLink>
		          </h:column>
		           --%>
	          	  <%-- Field: expirationDate --%>
		          <h:column>
		            <f:facet name="header">
		              <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
		                <f:verbatim>${msg['nof.expirationDate']}</f:verbatim>
		              </t:commandSortHeader>
		            </f:facet>
		            <%-- Date field --%>
		            <h:outputText value="#{genericNOF.endDate}" converter="autentia.dateConverter"/>
		         </h:column>
		         
		         
		         <%-- Field: type --%>
	          	 <h:column>
		            <f:facet name="header">
		              <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
		                <f:verbatim>${msg['setting.type']}</f:verbatim>
		              </t:commandSortHeader>
		            </f:facet>		            
					<h:outputText value="#{genericNOF.type}">
              			<f:converter converterId="autentia.EnumTranslator"/>
            		</h:outputText>
		         </h:column>
		         
		         
				  <%-- Field: number --%>
		          <h:column>
		            <f:facet name="header">
		              <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
		                <f:verbatim>${msg['nof.number']}</f:verbatim>
		              </t:commandSortHeader>
		            </f:facet>
		            <%-- String field --%>
		            <h:outputText value="#{genericNOF.number}" />
		          </h:column>
		          
		          <%-- Field: provider --%>
			      <h:column>
				      <f:facet name="header">
				        <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
				          <f:verbatim>${msg['nof.company']}</f:verbatim>
				        </t:commandSortHeader>
				      </f:facet>
		      		 <%-- Referenced field --%>
			        <h:outputText value="#{genericNOF.organization.name}" />
			      </h:column>  			  
			      
			      <%-- Field: name --%>
		          <h:column>
		            <f:facet name="header">
		              <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
		                <f:verbatim>${msg['bill.name']}</f:verbatim>
		              </t:commandSortHeader>
		            </f:facet>
		            <%-- String field --%>
		            <h:outputText value="#{genericNOF.description}" />
		          </h:column>
	          
			      <%-- Field: pending --%>
			      <h:column>
		            <f:facet name="header">
		              <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
		                <f:verbatim>${msg['nof.pending']}</f:verbatim>
		              </t:commandSortHeader>
		            </f:facet>
		            <%-- String field --%>
		            <h:outputText value="#{genericNOF.total}" styleClass="#{(genericNOF.total) > 0 ? 'b' : 'r'}" />
		          </h:column>
		          
		          
		          <h:column>
	          	  	 <f:facet name="header">
		              <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
		                <f:verbatim>${msg['nof.expired']}</f:verbatim>
		              </t:commandSortHeader>
		            </f:facet>
		            <t:graphicImage value="#{(genericNOF.expired)?'/img/true.png':'/img/false.png'}" border="0" />
	          	  </h:column>
		          
	     </t:dataTable>
	          
          <%-- Total incomes --%>
	      <t:panelGrid styleClass="listTable" columnClasses="listHeader">
	        	<h:panelGroup >
			       	<f:verbatim>&nbsp;${msg['nof.totalPrevisionIncomes']}:</f:verbatim>
			       	<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			       	<h:outputText value="#{nofBean.totalPrevisionIncomes} €" styleClass="#{(nofBean.totalPrevisionIncomes) > 0 ? 'b' : 'r'}">
			       	</h:outputText>
	           </h:panelGroup>
	      </t:panelGrid>

      	<%-- Paginator control --%>
       	<%@include file="/inc/paginator.jsp" %>
		
		<t:dataTable id="list2" var="genericNOF" value="#{nofBean.allPeriodicalAccountEntry}" preserveDataModel="false" 
	            cellpadding="0" cellspacing="0" styleClass="listTable" 
	            headerClass="listHeaderCell" footerClass="listFooter" 
	            rows="#{settingBean.mySettings.listSize}" rowClasses="listRowO,listRowE"
	            columnClasses="listBillNOFExpirationDate,listBillNOFBillType,listBillNOFNumber,listBillNOFProvider,listBillNOFName,listPeriodicalAccountNOFFrecuency,listPeriodicalAccountNOFType,listBillNOFAmount,listBillNOFExpirated">

	          	  <%-- Field: expirationDate --%>
		          <h:column>
		            <f:facet name="header">
		              <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
		                <f:verbatim>${msg['nof.periodicalAccountDate']}</f:verbatim>
		              </t:commandSortHeader>
		            </f:facet>
		            <%-- Date field --%>
		            <h:outputText value="#{genericNOF.endDate}" converter="autentia.dateConverter"/>
		         </h:column>
		         
		         
		         <%-- Field: type --%>
	          	 <h:column>
		            <f:facet name="header">
		              <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
		                <f:verbatim>${msg['setting.type']}</f:verbatim>
		              </t:commandSortHeader>
		            </f:facet>
                    <h:outputText value="#{genericNOF.type}">
              			<f:converter converterId="autentia.EnumTranslator"/>
            		</h:outputText>
		         </h:column>
 



		          <%-- Field: Empresa --%>
			      <h:column>
				      <f:facet name="header">
				        <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
				          <f:verbatim>${msg['nof.company']}</f:verbatim>
				        </t:commandSortHeader>
				      </f:facet>
		      		 <%-- Referenced field --%>
			        <h:outputText value="#{genericNOF.organization.name}" />
			      </h:column> 
			      			      
			      <%-- Field: name --%>
		          <h:column>
		            <f:facet name="header">
		              <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
		                <f:verbatim>${msg['bill.name']}</f:verbatim>
		              </t:commandSortHeader>
		            </f:facet>
		            <%-- String field --%>
		            <h:outputText value="#{genericNOF.description}" />
		          </h:column>
	          
		        <%-- Field: frequency --%>
		        <h:column>
		            <f:facet name="header">
		              <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
		                <f:verbatim>${msg['periodicalAccountEntry.frequency']}</f:verbatim>
		              </t:commandSortHeader>
		            </f:facet>
		            <%-- Referenced field --%>
		            <h:outputText value="#{genericNOF.frecuency}" />
		        </h:column>	          
	          		         
		        <%-- Field: type --%>
		        <h:column>
		            <f:facet name="header">
		              <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
		                <f:verbatim>${msg['periodicalAccountEntry.type']}</f:verbatim>
		              </t:commandSortHeader>
		            </f:facet>
		            <%-- Referenced field --%>
		            <h:outputText value="#{genericNOF.periodicalTypeDescription}" />
		        </h:column>
	          
			      <%-- Field: pending --%>
			      <h:column>
		            <f:facet name="header">
		              <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
		                <f:verbatim>${msg['nof.pending']}</f:verbatim>
		              </t:commandSortHeader>
		            </f:facet>
		            <%-- String field --%>
		            <h:outputText value="#{genericNOF.total}" styleClass="#{(genericNOF.total) > 0 ? 'b' : 'r'}" />
		          </h:column>
		          
		          
		          <h:column>
	          	  	 <f:facet name="header">
		              <t:commandSortHeader styleClass="listHeader" columnName="insertDate">
		                <f:verbatim>${msg['nof.expired']}</f:verbatim>
		              </t:commandSortHeader>
		            </f:facet>
		            <t:graphicImage value="#{(genericNOF.expired)?'/img/true.png':'/img/false.png'}" border="0" />
	          	  </h:column>
	          	  
	        </t:dataTable>
	          
	        <%-- Total periodical account --%>
		    <t:panelGrid styleClass="listTable" columnClasses="listHeader">
	        	<h:panelGroup >
			       	<f:verbatim>&nbsp;${msg['nof.totalPeriodicalAccount']}:</f:verbatim>
			       	<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			       	<h:outputText value="#{nofBean.totalPeriodicalAccount} €"  styleClass="#{(nofBean.totalPeriodicalAccount) > 0 ? 'b' : 'r'}">
			       	</h:outputText>
	           </h:panelGroup>
		    </t:panelGrid>
		    
		    <%-- Paginator control --%>
			<t:dataScroller for="list2" fastStep="10" paginator="true"
				paginatorMaxPages="10" renderFacetsIfSinglePage="false"
				styleClass="scroller" paginatorTableClass="paginator"
				paginatorColumnClass="paginatorCell" 
				paginatorActiveColumnClass="paginatorActiveCell">
					
				<f:facet name="first">
					<t:graphicImage value="/img/arrow-first.gif" border="0" />
				</f:facet>
				<f:facet name="last">
					<t:graphicImage value="/img/arrow-last.gif" border="0" />
				</f:facet>
				<f:facet name="previous">
					<t:graphicImage value="/img/arrow-previous.gif" border="0" />
				</f:facet>
				<f:facet name="next">
					<t:graphicImage value="/img/arrow-next.gif" border="0" />
				</f:facet>
				<f:facet name="fastrewind">
					<t:graphicImage value="/img/arrow-fr.gif" border="0" />
				</f:facet>
				<f:facet name="fastforward">
					<t:graphicImage value="/img/arrow-ff.gif" border="0" />
				</f:facet>
			</t:dataScroller>
		
		<%-- Dates --%>
        <h:panelGrid columns="2" cellpadding="0" cellspacing="0" styleClass="editTable" columnClasses="editLabelRW,editFieldCell">
	      <h:outputText value="#{msg['nof.difference']}:" />
	      <h:outputText id="difference" value="#{nofBean.totalPrevisionIncomes + nofBean.totalPeriodicalAccount} €" 
	      				styleClass="#{(nofBean.totalPrevisionIncomes + nofBean.totalPeriodicalAccount) > 0 ? 'b' : 'r'}">
	      </h:outputText>
        </h:panelGrid>
        
      </h:form>
    </f:view>
  </body>
</html>  		