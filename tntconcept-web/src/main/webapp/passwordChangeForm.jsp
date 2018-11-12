<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.autentia.tnt.util.*,com.autentia.tnt.manager.security.*"%>
<%@include file="/inc/tlibs.jsp" %>

<html>

<f:view>

<%@include file="/inc/publicHeader.jsp"%>

<head>
      <%@include file="/inc/uiCore.jsp"%>

</head>

<body>
	<f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
 	<i:location place="passwordChangeForm" msg="${msg}"/> 
	<div align="center" style="top:50px">
		<br>
		<h:panelGroup>
	   		<h3>Restablecer contraseña</h3>
   		</h:panelGroup><br/>
   		<p>Introduzca su nombre de usuario para restaurar la contraseña</p>
	   	<h:panelGroup>
	   	
		   	<h:form >
		   		<h:panelGrid columns="3" cellpadding="10" >
		   			<h:outputLabel for="name" value="Nombre de usuario"/>
			   		<h:inputText value="#{linkBean.name}" id="name" required="true" />
					<h:commandLink action="#{linkBean.passwordResetRequest}" onclick="if(!confirm('#{msg['resetpassword.sendmail']}')) return false;">
	            	<h:graphicImage value="/img/save.gif" styleClass="titleImg" />
	          		</h:commandLink>
			   	</h:panelGrid>    
			</h:form>
		</h:panelGroup>
	
	</div>
</body>
</f:view>

</html>