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
	<td class="detailLabelRatiosTitle" colspan="2">${msg['financialRatio.liquidityRatio']}:</td>
</tr>
 <%-- Field: liquidity --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.liquidity']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.liquidity}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: exchequer --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.exchequer']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.exchequer}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
    <%-- Field: availability --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.availability']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.availability}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: expensesCoverage --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.expensesCoverage']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.expensesCoverage}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: workingCapital --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.workingCapital']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.workingCapital}" >
         	       <f:convertNumber maxFractionDigits="2" type="percent"/>
         	       </h:outputText>
 	      </td>
    </tr>
                                
 