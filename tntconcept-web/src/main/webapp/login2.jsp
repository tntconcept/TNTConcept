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

<%@include file="/inc/tlibs.jsp" %>

<html>
  <head>
    <%@include file="/inc/uiCore.jsp"%>
  </head>
  <body class="userPage">
    
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    
    <f:view>
<table align="center" width="300px" bgcolor="#4A859A">
<tr><td>
      <form name="theForm" target="_top" action="<%=request.getContextPath()%>/j_acegi_security_check" method="post">
        <table cellpadding="0" cellspacing="0" align="center">
          <tr>
            <td rowspan="2">
              <img src="<%=request.getContextPath()%>/img/johny.gif">
            </td>
            <td class="headerUserLabel">${msg['entry.user']}:</td>								
            <td>
              <input type="text" name="j_username" maxlength="30" size="20" class="headerUserInput"/>
            </td>
            <td rowspan="2" class="headerLoginButton">
              <img src="<%=request.getContextPath()%>/img/demo_btn_login.gif" 
                   onclick="document.forms['theForm'].submit()" style="cursor: pointer"></img>
              <%-- HACK: put a hidden submit button so that RETURN key makes form be submitted --%>
              <input type="submit" style="display:none"/>
            </td>
          </tr>
          <tr>
            <td class="headerPwdLabel">${msg['entry.password']}:</td>								
            <td>
              <input type="password" name="j_password" maxlength="30" size="20" class="headerPwdInput"/>
            </td>
          </tr>
        </table>
      </form>
</td></tr>
</table>
    </f:view>
    
  </body>
</html>
