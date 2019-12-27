
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.autentia.tnt.util.*,com.autentia.tnt.manager.security.*"%>
<%@include file="/inc/tlibs.jsp" %>
<%@page import="com.autentia.tnt.bean.LinkBean"%>


<f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
<html>

<f:view>

<%@include file="/inc/publicHeader.jsp"%>

<head>
      <%@include file="/inc/uiCore.jsp"%>

</head>
<html>
    <head>
      <title>     
      </title>
    </head>   
    <body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"	marginwidth="0" marginheight="0">
    <% 
    String link = request.getParameter("link");
    LinkBean linkBean = new LinkBean();
    String password = linkBean.checkLinkAndResetPassword(link);
    %>
    
    <div align="center" style="top:50px">
    	<br>
		<p><%= password %></p>
	</div>
	
    </body>
    	</f:view>
    
</html>


