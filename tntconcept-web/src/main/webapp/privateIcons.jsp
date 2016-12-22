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

<h:form id="private"> 
<h:panelGrid styleClass="iconsToolbar" columns="9" cellpadding="0"
	cellspacing="0"
	columnClasses="betweenIcons,alignTop,betweenIcons,alignTop,betweenIcons,alignTop,betweenIcons,alignTop">
	<h:column></h:column>
	<h:column>
		<f:verbatim>
			<a href="<%=request.getContextPath()%>/pages/control_panel.jsf"><img
				src="<%=request.getContextPath()%>/img/home.png" class="titleImg"></a>
		</f:verbatim>
	</h:column>
	<h:column></h:column>
	<h:column>
		<h:outputLink onclick="refresh()" styleClass="icon">
			<t:graphicImage value="/img/reload.png" styleClass="titleImg"
				title="Actualizar" border="0" />
		</h:outputLink>
	</h:column>
	<h:column></h:column>
	<h:column>
		<t:graphicImage onclick="printScreen();" value="/img/printer.png"
			styleClass="titleImg" title="Imprimir" border="0"
			style="cursor: pointer" />
	</h:column>
	<h:column></h:column>
	<h:column>
		<t:commandLink action="#{errorBean.changeLogMode}" immediate="true">
			<t:graphicImage value="#{errorBean.urlLogo}" styleClass="titleImg"
				title="#{errorBean.title}" border="0" />
		</t:commandLink>
	</h:column>
</h:panelGrid>
</h:form>
