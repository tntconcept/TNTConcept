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
	<td class="detailLabelRatiosTitle" colspan="2">${msg['financialRatio.efficiencyRatio']}:</td>
</tr>
<%-- Field: collectionPeriod --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.collectionPeriod']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.collectionPeriod}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: payPeriod --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.payPeriod']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.payPeriod}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: turnoverAsset --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.turnoverAsset']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.turnoverAsset}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
    <%-- Field: turnoverPermanentAsset --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.turnoverPermanentAsset']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.turnoverPermanentAsset}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
                            
  
      <%-- Field: turnoverFM --%>
    <tr>
	<td class="detailLabelRatios">${msg['financialRatio.turnoverFM']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{financialRatioBean.turnoverFM}" >
         	       <f:convertNumber maxFractionDigits="2"/>
         	       </h:outputText>
 	      </td>
    </tr>
                                
 