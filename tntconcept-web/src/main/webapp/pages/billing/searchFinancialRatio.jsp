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
    
    <!-- searchFinancialRatio.jsp: generated by stajanov code generator -->
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    <i:location place="searchFinancialRatio" msg="${msg}"/> 
    
    <f:view>
      <%@include file="/inc/header.jsp" %>
      <h:form id="search" enctype="multipart/form-data">
        
        <%-- Header --%>
        <i:titleBar msg="${msg}">
          <h:commandLink action="#{financialRatioBean.list}">
            <h:graphicImage title="#{msg.entityActions_run}"  value="/img/run.png" styleClass="titleImg" />
          </h:commandLink>
        </i:titleBar>
        
        <%-- Search form --%>
        <table class="searchTable" cellpadding="0" cellspacing="0">

          
      <%-- Ignored field: id --%>

                        
      <%-- Field: title --%>
    <tr>
      <td class="searchLabel">${msg['financialRatio.title']}:</td>
      <td class="searchFieldCell">

        
          
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="title" />
            <h:selectBooleanCheckbox id="titleValid" value="#{financialRatioBean.searchTitleValid}" 
                                      onclick="setEnabled('search:title',this.checked)"/>
            <h:inputText id="title" value="#{financialRatioBean.searchTitle}" size="70"/>
          </h:panelGroup>
          <script>
            setEnabled( 'search:title', ${financialRatioBean.searchTitleValid} )
          </script>

        
      </td>
    </tr>
                        
      <%-- Field: ratioDate --%>
    <tr>
      <td class="searchLabel">${msg['financialRatio.ratioDate']}:</td>
      <td class="searchFieldCell">

        
                    
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="startRatioDate" />
            <h:selectBooleanCheckbox id="startRatioDateValid" value="#{financialRatioBean.searchStartRatioDateValid}" 
                                      onclick="setEnabled('search:startRatioDate',this.checked)"/>
            <t:inputCalendar id="startRatioDate" value="#{financialRatioBean.searchStartRatioDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true" 
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="setEnabled('search:startRatioDate',true);setChecked('search:startRatioDateValid',true);"
                             >
				<f:validator validatorId="autentia.dateValidator"/>
			</t:inputCalendar>
          </h:panelGroup>
          -
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="endRatioDate" />
            <h:selectBooleanCheckbox id="endRatioDateValid" value="#{financialRatioBean.searchEndRatioDateValid}" 
                                      onclick="setEnabled('search:endRatioDate',this.checked)"/>
            <t:inputCalendar id="endRatioDate" value="#{financialRatioBean.searchEndRatioDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true" 
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="setEnabled('search:endRatioDate',true);setChecked('search:endRatioDateValid',true);"
                             >
				<f:validator validatorId="autentia.dateValidator"/>
			</t:inputCalendar>
          </h:panelGroup>
          <script>
            setEnabled( 'search:startRatioDate', ${financialRatioBean.searchStartRatioDateValid} )
            setEnabled( 'search:endRatioDate', ${financialRatioBean.searchEndRatioDateValid} )
          </script>

        
      </td>
    </tr>
                        
      <%-- Ignored field: banksAccounts --%>

                        
      <%-- Ignored field: customers --%>

                        
      <%-- Ignored field: stocks --%>

                        
      <%-- Ignored field: amortizations --%>

                        
      <%-- Ignored field: infrastructures --%>

                        
      <%-- Ignored field: shortTermLiability --%>

                        
      <%-- Ignored field: obligationBond --%>

                        
      <%-- Ignored field: capital --%>

                        
      <%-- Ignored field: reserves --%>

                        
      <%-- Ignored field: incomes --%>

                        
      <%-- Ignored field: expenses --%>

                        
      <%-- Ignored field: otherExploitationExpenses --%>

                        
      <%-- Ignored field: financialExpenses --%>

                        
      <%-- Ignored field: taxes --%>

                        
      <%-- Ignored field: netAsset --%>

                        
      <%-- Ignored field: totalAsset --%>

                        
      <%-- Ignored field: totalOtherResources --%>

                        
      <%-- Ignored field: totalOurResources --%>

                        
      <%-- Ignored field: totalLiability --%>

                        
      <%-- Ignored field: margin --%>

                        
      <%-- Ignored field: bait --%>

                        
      <%-- Ignored field: bat --%>

                        
      <%-- Ignored field: bdt --%>

                        
      <%-- Ignored field: baitd --%>

                        
      <%-- Ignored field: liquidity --%>

                        
      <%-- Ignored field: exchequer --%>

                        
      <%-- Ignored field: availability --%>

                        
      <%-- Ignored field: expensesCoverage --%>

                        
      <%-- Ignored field: workingCapital --%>

                        
      <%-- Ignored field: solvency --%>

                        
      <%-- Ignored field: debtRatio --%>

                        
      <%-- Ignored field: debtTotalLiability --%>

                        
      <%-- Ignored field: guarantee --%>

                        
      <%-- Ignored field: financeCoverage --%>

                        
      <%-- Ignored field: collectionPeriod --%>

                        
      <%-- Ignored field: payPeriod --%>

                        
      <%-- Ignored field: turnoverAsset --%>

                        
      <%-- Ignored field: turnoverPermanentAsset --%>

                        
      <%-- Ignored field: turnoverFM --%>

                        
      <%-- Ignored field: grossSpread --%>

                        
      <%-- Ignored field: baitSpread --%>

                        
      <%-- Ignored field: bdtSpread --%>

                        
      <%-- Ignored field: baitROA --%>

                        
      <%-- Ignored field: baitdROA --%>

                        
      <%-- Ignored field: ROE --%>

                        
      <%-- Ignored field: leveraging --%>

                        
      <%-- Ignored field: ROCE --%>

                        
      <%-- Ignored field: ownerId --%>

                        
      <%-- Ignored field: departmentId --%>

                        
      <%-- Ignored field: insertDate --%>

                        
      <%-- Ignored field: updateDate --%>

                                          
        </table>
        
      </h:form>
    </f:view>
    
  </body>
</html>  		
