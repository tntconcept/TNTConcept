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
<%@page import="com.autentia.tnt.util.*,com.autentia.tnt.manager.security.*"%>

<%-- 
  This page redirects to the correct page depending on the state of the application
  and user authentication.
--%>
<%
  String context = request.getContextPath();
  boolean loggedIn = (AuthenticationManager.getDefault().getCurrentPrincipal()!=null);
  
  if( ApplicationLock.isLocked() )
  {
    if( loggedIn )
    {
      response.sendRedirect(context+"/single/console.jsf");
    }
    else
    {
      response.sendRedirect(context+"/consoleLogin.jsf");
    }
  }
  else
  {
    if( loggedIn )
    {
      response.sendRedirect(context+"/pages/control_panel.jsf");
    }
    else
    {
      response.sendRedirect(context+"/root.jsf");
    }
  }
%>