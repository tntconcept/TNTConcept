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
 

<html>
<head>
	<script type="text/javascript">
		function openMain()
		{
			opts = 'width='+screen.width + ',height=' + screen.height;
			try {
				var v = window.open('root.jsf', this.target, opts);
				v.focus();
			} catch(e) {
				alert("Se ha impedido abrir la ventana por algún programa de bloqueo de ventanas emergentes...");
			}
		}
		
	</script>
</head>
<body onLoad="openMain();">

	Si no se abre ninguna ventana pulse <a href="#" onclick="openMain();">aqui</a>
	
</body>
</html>