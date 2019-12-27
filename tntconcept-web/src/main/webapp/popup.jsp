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
<%@page import="com.autentia.tnt.util.FacesUtils"%>

<html>
<head>
	<script type="text/javascript">
		function openMain()
		{
			opts = 'location=no,menubar=no,toolbars=no,scrollbars=yes,status=no,titlebar=no,resizable=yes';
			try {
				var v = window.open('entryPoint.jsf', this.target,opts);			
				v.focus();
			} catch (e) {
				var msgBlockPopup='<%=FacesUtils.getMessage("msg.blockPopup")%>';
				alert(msgBlockPopup);
			}
		}
		
	</script>
</head>
<body onLoad="openMain();">
	<%=FacesUtils.getMessage("msg.index")%> <a href="#" onclick="openMain();"><%=FacesUtils.getMessage("msg.index.here")%></a>
</body>
</html>