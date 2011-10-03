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

<%@page language="java" contentType="text/html; charset=UTF-8" import="com.autentia.tnt.util.*"%>

<%@include file="/inc/tlibs.jsp" %>

<html>
  <head>

    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    
    <title>
      ${msg['login.title']}
    </title>
    
  </head>
  <body>
    
    <f:view>
      <form name="theForm" action="<%=request.getContextPath()%>/j_acegi_security_check" method="post">
        
        <h:outputText value="#{msg['login.prompt']}"/>
        <br/>
        <br/>
        <h:outputText value="#{msg['login.lockReason']}"/>
        <h:outputText value="#{msg[consoleBean.lockReason]}"/>
        <br/>
        <br/>
        
        <table>
          <tr>
            <td>${msg['user.name']}</td>
            <td><input type="text" name="j_username" maxlength="30" size="20"/></td>
          </tr>
          <tr>
            <td>${msg['user.password']}</td>
            <td><input type="password" name="j_password" maxlength="30" size="20"/></td>
          </tr>
        </table>
        
        <br/>
        <input type="submit" value="${msg['login.enter']}"/>
        
      </form>
    </f:view>
    
  </body>
</html>
