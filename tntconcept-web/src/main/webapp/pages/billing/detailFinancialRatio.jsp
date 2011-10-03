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
    
    <!-- editFinancialRatio.jsp: generated by stajanov code generator -->
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    <i:location place="detailFinancialRatio" msg="${msg}"/> 
    
    <f:view>
        <%@include file="/inc/header.jsp" %>
      <h:form id="financialRatio" enctype="multipart/form-data">
        
        <%-- Header --%>
        <i:titleBar name="${financialRatioBean.title}" msg="${msg}">
          <t:commandLink action="#{financialRatioBean.edit}" immediate="true" rendered="#{financialRatioBean.editAvailable}">
            <f:param name="rowid" value="#{financialRatioBean.id}" />
            <h:graphicImage title="#{msg.entityActions_edit}"  value="/img/edit.gif" styleClass="cmdImg" />
          </t:commandLink>
          <h:commandLink action="#{financialRatioBean.delete}" rendered="#{financialRatioBean.deleteAvailable}" onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}"  value="/img/delete.gif" styleClass="titleImg" />
          </h:commandLink>
          <h:commandLink action="#{financialRatioBean.list}" immediate="true">
            <h:graphicImage title="#{msg.entityActions_back}"  value="/img/back.gif" styleClass="titleImg" />
          </h:commandLink>
           <h:commandLink action="#{financialRatioBean.compareRatioForm}" immediate="true">
           <h:outputText value="#{msg['financialRatio.compare']}" />
          </h:commandLink>
        </i:titleBar>


        <%-- Detail form --%>
        <table class="detailTable" cellpadding="0" cellspacing="0">

            
  
      <%-- Ignored field: id --%>
  
                            
  
      <%-- Field: title --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.title']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.title}" />
 	      </td>
    </tr>
                            
  
      <%-- Field: ratioDate --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.ratioDate']}:</td>
	<td class="detailFieldCell">
         	    	<h:outputText value="#{financialRatioBean.ratioDate}" converter="autentia.dateConverter" />
 	          </td>
    </tr>
    
    <tr>
    <td valign="top">
    <table class="detailTable" cellpadding="0" cellspacing="0">
    
    <tr>
    	    		<td class="detailLabelRatiosTitle" colspan="2">${msg['financialRatio.asset']}</td>
    </tr>             
  
      <%-- Field: banksAccounts --%>
    <tr>
	<td class="detailLabelRatiosAsset">${msg['financialRatio.banksAccounts']}:</td>
	<td class="detailLabelRatiosAsset">
         	       <h:outputText value="#{financialRatioBean.banksAccounts}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: customers --%>
    <tr>
	<td class="detailLabelRatiosAsset">${msg['financialRatio.customers']}:</td>
	<td class="detailLabelRatiosAsset">
         	       <h:outputText value="#{financialRatioBean.customers}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: stocks --%>
    <tr>
	<td class="detailLabelRatiosAsset">${msg['financialRatio.stocks']}:</td>
	<td class="detailLabelRatiosAsset">
         	       <h:outputText value="#{financialRatioBean.stocks}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  <%-- Field: netAsset --%>
    <tr>
	<td class="detailLabelRatiosAsset">${msg['financialRatio.netAsset']}:</td>
	<td class="detailLabelRatiosAsset">
         	       <h:outputText value="#{financialRatioBean.netAsset}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
      <%-- Field: amortizations --%>
    <tr>
	<td class="detailLabelRatiosAsset">${msg['financialRatio.amortizations']}:</td>
	<td class="detailLabelRatiosAsset">
         	       <h:outputText value="#{financialRatioBean.amortizations}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: infrastructures --%>
    <tr>
	<td class="detailLabelRatiosAsset">${msg['financialRatio.infrastructures']}:</td>
	<td class="detailLabelRatiosAsset">
         	       <h:outputText value="#{financialRatioBean.infrastructures}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
    <%-- Field: totalAsset --%>
    <tr>
	<td class="detailLabelRatiosAsset">${msg['financialRatio.totalAsset']}:</td>
	<td class="detailLabelRatiosAsset">
         	       <h:outputText value="#{financialRatioBean.totalAsset}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>  

    
   
   </table>
    </td>
    
    <td valign="top">
    <table class="detailTable" cellpadding="0" cellspacing="0">    
  <tr>
    	    		<td class="detailLabelRatiosTitle" colspan="2">${msg['financialRatio.liability']}</td>
    </tr>
      <%-- Field: shortTermLiability --%>
    <tr>
	<td class="detailLabelRatiosLia">${msg['financialRatio.shortTermLiability']}:</td>
	<td class="detailLabelRatiosLia">
         	       <h:outputText value="#{financialRatioBean.shortTermLiability}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: obligationBond --%>
    <tr>
	<td class="detailLabelRatiosLia">${msg['financialRatio.obligationBond']}:</td>
	<td class="detailLabelRatiosLia">
         	       <h:outputText value="#{financialRatioBean.obligationBond}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
         
         
     <%-- Field: totalOtherResources --%>
    <tr>
	<td class="detailLabelRatiosLia">${msg['financialRatio.totalOtherResources']}:</td>
	<td class="detailLabelRatiosLia">
         	       <h:outputText value="#{financialRatioBean.totalOtherResources}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>                    
  
      <%-- Field: capital --%>
    <tr>
	<td class="detailLabelRatiosLia">${msg['financialRatio.capital']}:</td>
	<td class="detailLabelRatiosLia">
         	       <h:outputText value="#{financialRatioBean.capital}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: reserves --%>
    <tr>
	<td class="detailLabelRatiosLia">${msg['financialRatio.reserves']}:</td>
	<td class="detailLabelRatiosLia">
         	       <h:outputText value="#{financialRatioBean.reserves}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
    
    
    <%-- Field: totalOurResources --%>
    <tr>
	<td class="detailLabelRatiosLia">${msg['financialRatio.totalOurResources']}:</td>
	<td class="detailLabelRatiosLia">
         	       <h:outputText value="#{financialRatioBean.totalOurResources}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: totalLiability --%>
    <tr>
	<td class="detailLabelRatiosLia">${msg['financialRatio.totalLiability']}:</td>
	<td class="detailLabelRatiosLia">
         	       <h:outputText value="#{financialRatioBean.totalLiability}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
    
    </table>
    </td>
    </tr> 
      
     
  
                            
  <tr>
    	    		<td class="detailLabelRatiosTitle" colspan="4">${msg['financialRatio.results']}</td>
    </tr>
    <tr>
    <td>
    <table class="detailTable" cellpadding="0" cellspacing="0">   
    
      <%-- Field: incomes --%>
    <tr>
	<td class="detailLabelRatiosRes">${msg['financialRatio.incomes']}:</td>
	<td class="detailLabelRatiosRes">
         	       <h:outputText value="#{financialRatioBean.incomes}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: expenses --%>
    <tr>
	<td class="detailLabelRatiosRes">${msg['financialRatio.expenses']}:</td>
	<td class="detailLabelRatiosRes">
         	       <h:outputText value="#{financialRatioBean.expenses}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
     
     <%-- Field: margin --%>
    <tr>
	<td class="detailLabelRatiosRes">${msg['financialRatio.margin']}:</td>
	<td class="detailLabelRatiosRes">
         	       <h:outputText value="#{financialRatioBean.margin}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>    
  
      <%-- Field: otherExploitationExpenses --%>
    <tr>
	<td class="detailLabelRatiosRes">${msg['financialRatio.otherExploitationExpenses']}:</td>
	<td class="detailLabelRatiosRes">
         	       <h:outputText value="#{financialRatioBean.otherExploitationExpenses}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
     <%-- Field: bait --%>
    <tr>
	<td class="detailLabelRatiosRes">${msg['financialRatio.bait']}:</td>
	<td class="detailLabelRatiosRes">
         	       <h:outputText value="#{financialRatioBean.bait}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>                       
  
    
    </table>
    </td>
        
    <td>
    <table class="detailTable" cellpadding="0" cellspacing="0">             
  
    <%-- Field: financialExpenses --%>
    <tr>
	<td class="detailLabelRatiosRes">${msg['financialRatio.financialExpenses']}:</td>
	<td class="detailLabelRatiosRes">
         	       <h:outputText value="#{financialRatioBean.financialExpenses}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
  <tr>
	<td class="detailLabelRatiosRes">${msg['financialRatio.bat']}:</td>
	<td class="detailLabelRatiosRes">
         	       <h:outputText value="#{financialRatioBean.bat}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
      <%-- Field: taxes --%>
    <tr>
	<td class="detailLabelRatiosRes">${msg['financialRatio.taxes']}:</td>
	<td class="detailLabelRatiosRes">
         	       <h:outputText value="#{financialRatioBean.taxes}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
    <%-- Field: bdt --%>
    <tr>
	<td class="detailLabelRatiosRes">${msg['financialRatio.bdt']}:</td>
	<td class="detailLabelRatiosRes">
         	       <h:outputText value="#{financialRatioBean.bdt}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: baitd --%>
    <tr>
	<td class="detailLabelRatiosRes">${msg['financialRatio.baitd']}:</td>
	<td class="detailLabelRatiosRes">
         	       <h:outputText value="#{financialRatioBean.baitd}" >
         	       <f:convertNumber maxFractionDigits="2" type="currency" currencySymbol="€"/>
         	       </h:outputText>
 	      </td>
    </tr>                               
  </table>
  </td>
 </tr>
 <tr>
    <td valign="top">
    <table class="editTable" cellpadding="0" cellspacing="0"> 
    	<%@include file="inlineLiquidity.jsp"%>
    </table>
    </td>
    <td valign="top">
    <table class="editTable" cellpadding="0" cellspacing="0"> 
    	<%@include file="inlineSolvency.jsp"%>
    </table>
    </td>
    </tr>
 
 <tr>
    <td valign="top">
    <table class="editTable" cellpadding="0" cellspacing="0"> 
    	<%@include file="inlineEfficiency.jsp"%>
    </table>
    </td>
    <td valign="top">
    <table class="editTable" cellpadding="0" cellspacing="0"> 
    	<%@include file="inlineProfitability.jsp"%>
    </table>
    </td>
    </tr>              
  
      <%-- Ignored field: ownerId --%>
  
                            
  
      <%-- Ignored field: departmentId --%>
  
                            
  
      <%-- Ignored field: insertDate --%>
  
                            
  
      <%-- Ignored field: updateDate --%>
  
                                                  
        </table>

      </h:form>
    </f:view>
    
  </body>
</html>  		
<%-- FinancialRatio - generated by stajanov (do not edit/delete) --%>
