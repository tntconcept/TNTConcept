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

<form action="<%=request.getContextPath()%>/j_acegi_logout" method="post">
<table class="headerUserTable" cellpadding="0" cellspacing="0">
	<tr>
		<td class="headerUser"><authz:authentication operation="username" /></td>
		<td class="headerExitButton"><img
			src="<%=request.getContextPath()%>/img/exit.png" alt="Salir"
			onclick="if( !confirm('¿Desea salir realmente de la aplicación TNTConcept?') ) return false; else submit();"
			style="cursor: pointer"></img> <input type="submit"
			style="display:none" /></td>
	</tr>
</table>
</form>
