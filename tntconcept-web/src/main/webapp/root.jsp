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
<%@page	import="com.autentia.tnt.util.*,com.autentia.tnt.manager.security.*"%>
	

<%@include file="/inc/tlibs.jsp"%>

<html>
<head>
<%@include file="/inc/uiCore.jsp"%>
</head>
<body>

<f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />


<f:view>
	<h:panelGrid cellpadding="0" cellspacing="0" styleClass="mainWindow"
		columns="1" rowClasses="alignTop,alignTop">
		<h:column>
			<%@include file="/inc/publicHeader.jsp"%>
		</h:column>
		<h:column>
			
			<%@include file="/public.jsp"%>
			
			
			
		</h:column>
	</h:panelGrid>
	
	
	
	
</f:view>
</body>
</html>
