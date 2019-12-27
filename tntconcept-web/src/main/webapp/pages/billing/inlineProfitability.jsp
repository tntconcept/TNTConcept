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
	<td class="detailLabelRatiosTitle" colspan="2">${msg['financialRatio.profitabilityRatio']}:</td>
</tr>
                                
 <%-- Field: grossSpread --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.grossSpread']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.grossSpread}" >
         	       <f:convertNumber maxFractionDigits="2" type="percent"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: baitSpread --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.baitSpread']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.baitSpread}" >
         	       <f:convertNumber maxFractionDigits="2" type="percent"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: bdtSpread --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.bdtSpread']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.bdtSpread}" >
         	       <f:convertNumber maxFractionDigits="2" type="percent"/>
         	       </h:outputText>
 	      </td>
    </tr>
    <%-- Field: baitROA --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.baitROA']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.baitROA}" >
         	       <f:convertNumber maxFractionDigits="2" type="percent"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: baitdROA --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.baitdROA']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.baitdROA}" >
         	       <f:convertNumber maxFractionDigits="2" type="percent"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: ROE --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.ROE']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.ROE}" >
         	       <f:convertNumber maxFractionDigits="2" type="percent"/>
         	       </h:outputText>
 	      </td>
    </tr>
    
    <%-- Field: leveraging --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.leveraging']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.leveraging}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: ROCE --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.ROCE']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.ROCE}" >
         	       <f:convertNumber maxFractionDigits="2" type="percent"/>
         	       </h:outputText>
 	      </td>
    </tr>