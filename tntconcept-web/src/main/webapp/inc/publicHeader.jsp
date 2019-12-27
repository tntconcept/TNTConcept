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

<f:verbatim>
	<form name="theForm"
		action="<%=request.getContextPath()%>/j_acegi_security_check"
		method="post">
</f:verbatim>
<h:panelGrid columns="3" styleClass="mainMenu"
	columnClasses="logoMenu,,loginMenu" cellpadding="0" cellspacing="0">
	<h:column>
		<h:outputLink value="http://tntconcept.sourceforge.net"
			target="_blank">
			<h:graphicImage value="/img/logo-grey.png" title="TNT Concept"
				alt="TNT Concept" />
		</h:outputLink>
	</h:column>

	<h:column>
		<%@include file="/publicIcons.jsp"%>
	</h:column>
	<h:column>
		<f:verbatim>
			<%@include file="/login.jsp"%>
		</f:verbatim>
	</h:column>

</h:panelGrid>
<f:verbatim>
	</form>
</f:verbatim>
