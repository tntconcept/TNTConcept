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

<table class="loginMenu" cellpadding="0" cellspacing="0">
	<tr>
		<td class="headerUserLabel">${msg['entry.user']}:</td>
		<td>
			<input type="text" name="j_username" maxlength="30" size="20" class="headerUserInput" tabindex="1"/>
		</td>
		<td rowspan="2" class="headerLoginButton">			
			<input type="image" src="<%=request.getContextPath()%>/img/run.png" alt="Entrar" tabindex="3"/>
		</td>
	</tr>
	<tr>
		<td class="headerPwdLabel">
			${msg['entry.password']}:
		</td>
		<td>
			<input type="password" name="j_password" maxlength="30"	size="20" class="headerPwdInput" tabindex="2"/>
		</td>
	</tr>
</table>

