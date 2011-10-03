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

<tr>
	<td class="detailLabelRatiosTitle" colspan="2">${msg['financialRatio.solvencyRatio']}:</td>
</tr>
<%-- Field: solvency --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.solvency']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.solvency}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: debtRatio --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.debtRatio']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.debtRatio}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
    <%-- Field: debtTotalLiability --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.debtTotalLiability']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.debtTotalLiability}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: guarantee --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.guarantee']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.guarantee}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: financeCoverage --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.financeCoverage']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.financeCoverage}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>                      
 