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
<%@page import="com.autentia.tnt.util.*,com.autentia.tnt.manager.security.*, com.autentia.tnt.manager.admin.SettingManager, 
				com.autentia.tnt.businessobject.Setting, java.util.Locale"%>
<%@include file="/inc/tlibs.jsp" %>
 <%   
    	final Principal principal = AuthenticationManager.getDefault().getCurrentPrincipal();

    	Boolean isResetPassword = principal.getUser().isResetPassword();
        if ( (isResetPassword != null) && isResetPassword){
            request.getRequestDispatcher("/pages/passwordExpired.jsp").forward(request, response);
            return;
        }

		//Establecemos el Locale del usuario
		final SettingManager settingManager = SettingManager.getDefault();
		final Setting setting = settingManager.get(SettingPath.GENERAL_PREFERRED_LOCALE, false);
		final Locale locale = new Locale(SettingManager.getString(setting, FacesUtils.getViewLocale().getLanguage()));
		principal.setLocale(locale);
		
		
	   if (principal.getUser().isPasswordExpired()){
		   request.getRequestDispatcher("/pages/passwordExpired.jsp").forward(request, response);
		   return;
	   }
 %>
<html>
  <head>
    <%@include file="/inc/uiCore.jsp" %>
  </head>	
  <body>
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
     <f:view>
     <%@include file="/inc/header.jsp" %>  
    <%   
	    if(principal.getRoleId()!=ConfigurationUtil.getDefault().getRoleClientId()) {
    %>
    
    
      <h:form id="idLogin">
        <table class="controlHomeTable" cellspacing="0" cellpadding="0">
          <tr>
            <td class="controlHomeCatList">
              
              <table width="100%" height="100%" cellspacing="0" cellpadding="0">
                <tr>
                  <td class="bullTopLeft"></td>
                  <td class="bullTopCenterBack">
                    <div class="bullTopCenterFore">
                      <span class="bullTopCenterTitle">
                        <h:outputText value="#{msg['entry.boardCategories']}:" />
                      </span>
                    </div>
                  </td>
                  <td class="bullTopRight"></td>
                </tr>
                <tr>
                  <td class="bullLeft"></td>
                  <td class="bullCenter">
                    
                    <t:dataTable id="dataCategories" styleClass="bullTable"
                                 headerClass="bullTableHeader"
                                 footerClass="bullTableHeader"
                                 columnClasses="bullTableColCategory"
                                 var="category"	value="#{bulletinBoardCategoryBean.all}"
                                 preserveDataModel="true" rows="15" cellpadding="0" cellspacing="0"
                                 >
                      <h:column>
                        <h:panelGroup>
                          <t:commandLink action="#{bulletinBoardBean.selectCategory}" styleClass="bullTableCatLink" immediate="true">
                            <f:param name="rowid" value="#{category.id}" />
                            <f:verbatim><img src="<%=request.getContextPath()%>/img/arrow.gif" border="0"/>&nbsp;</f:verbatim>
                            <h:outputText value="#{category.name}" />
                            <f:verbatim>&nbsp;&nbsp;(</f:verbatim>
                            <h:outputText value="#{category.numMessages}" />
                            <f:verbatim><h:outputText value="#{msg['entry.boardMessage']}:" />)</f:verbatim>
                          </t:commandLink>
                        </h:panelGroup>
                      </h:column>
                    </t:dataTable>
                    
                  </td>
                  <td class="bullRight"></td>
                </tr>
                <tr>
                  <td colspan="3" class="bullBottom"></td>
                </tr>
              </table>
              
            </td>
          </tr>
          <tr>
            <td class="controlHomeMsgList">
              
              <table width="100%" height="100%" cellspacing="0" cellpadding="0">
                <tr>
                  <td class="bullTopLeft"></td>
                  <td class="bullTopCenterBack">
                    <div class="bullTopCenterFore">
                      <span class="bullTopCenterTitle">
                        <h:outputText value="#{msg['entry.messagesList']}:" />
                      </span>
                    </div>
                  </td>
                  <td class="bullTopRight"></td>
                </tr>
                <tr>
                  <td class="bullLeft"></td>
                  <td class="bullCenter">
                    <t:dataTable id="dataMensajes" styleClass="bullTable"
                                 headerClass="bullTableHeader"
                                 footerClass="bullTableHeader"
                                 rowClasses="bullTableRowO,bullTableRowE"
                                 columnClasses="bullTableColTitle,bullTableColDate,bullTableColUser"
                                 rowOnMouseOver="this.style.backgroundColor='#DBDBD1'"
                                 rowOnMouseOut="this.style.backgroundColor='#FFFFE0'"
                                 rowOnClick="this.style.backgroundColor='#DBDBD1'"
                                 rowOnDblClick="this.style.backgroundColor='#DBDBD1'" 
                                 var="message"	value="#{bulletinBoardBean.all}"
                                 preserveDataModel="true" rows="15" cellpadding="0" cellspacing="0"
                                 rendered="#{bulletinBoardBean.searchCategory != null}">
                      
                      <h:column>
                        <f:facet name="header">
                          &nbsp;&nbsp;<h:outputText value="#{msg['entry.messageTitle']}" />
                        </f:facet>
                        <h:panelGroup>
                          <t:commandLink action="#{bulletinBoardBean.detail}" immediate="true">
                            <h:outputText value="#{message.title}" />
                            <f:param name="rowid" value="#{message.id}" />
                          </t:commandLink>
                          <h:graphicImage url="/img/paperclip.gif" rendered="#{! empty message.documentPath}" />
                        </h:panelGroup>
                      </h:column>
                      
                      <h:column>
                        <f:facet name="header">
                          &nbsp;&nbsp;<h:outputText value="#{msg['bulletinBoard.creationDate']}" />
                        </f:facet>
                        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                        <h:outputText value="#{message.creationDate}" converter="autentia.dateConverter"/>
                      </h:column>
                      
                      <h:column>
                        <f:facet name="header">
                          <h:outputText value="#{msg['entry.user']}" />
                        </f:facet>
                        <h:outputText value="#{message.user.name}" />
                      </h:column>
                      
                    </t:dataTable>
                  </td>
                  <td class="bullRight"></td>
                </tr>
                <tr>
                  <td colspan="3" class="bullBottom"></td>
                </tr>
              </table>
              
            </td>
          </tr>
        </table>
        
      </h:form>
      
    
    <% } else { %>
    <h:form id="idLogin">
        <table class="controlHomeTable" cellspacing="0" cellpadding="0">
          <tr>
            <td class="controlHomeMsgList">
              
              <table width="100%" height="100%" cellspacing="0" cellpadding="0">
                <tr>
                  <td class="bullTopLeft"></td>
                  <td class="bullTopCenterBack">
                    <div class="bullTopCenterFore">
                      <span class="bullTopCenterTitle">
                        <h:outputText value="#{msg['entry.messagesPublicList']}" />
                      </span>
                    </div>
                  </td>
                  <td class="bullTopRight"></td>
                </tr>
                <tr>
                  <td class="bullLeft"></td>
                  <td class="bullCenter">
                    <t:dataTable id="dataMensajes" styleClass="bullTable"
                                 headerClass="bullTableHeader"
                                 footerClass="bullTableHeader"
                                 rowClasses="bullTableRowO,bullTableRowE"
                                 columnClasses="bullTableColTitle,bullTableColDate,bullTableColUser,bullTableColMessage"
                                 rowOnMouseOver="this.style.backgroundColor='#DBDBD1'"
                                 rowOnMouseOut="this.style.backgroundColor='#FFFFE0'"
                                 rowOnClick="this.style.backgroundColor='#DBDBD1'"
                                 rowOnDblClick="this.style.backgroundColor='#DBDBD1'" 
                                 var="message"	value="#{bulletinBoardBean.public}"
                                 preserveDataModel="true" rows="15" cellpadding="0" cellspacing="0">
                      
                      <h:column>
                        <f:facet name="header">
                          &nbsp;&nbsp;<h:outputText value="#{msg['bulletinBoard.title']}" />
                        </f:facet>
                        <h:panelGroup>
                          <t:commandLink action="#{bulletinBoardBean.detailPublic}" immediate="true">
                            <h:outputText value="#{message.title}" />
                            <f:param name="rowid" value="#{message.id}" />
                          </t:commandLink>
                          <h:graphicImage url="/img/paperclip.gif" rendered="#{! empty message.documentPath}" />
                        </h:panelGroup>
                      </h:column>
                      
                      <h:column>
                        <f:facet name="header">
                          &nbsp;&nbsp;<h:outputText value="#{msg['bulletinBoard.creationDate']}" />
                        </f:facet>
                        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                        <h:outputText value="#{message.creationDate}" converter="autentia.dateConverter"/>
                      </h:column>
                      
                      <h:column>
                        <f:facet name="header">
                          <h:outputText value="#{msg['bulletinBoard.user']}" />
                        </f:facet>
                        <h:outputText value="#{message.user.name}" />
                      </h:column>
                      
                      <h:column>
                        <f:facet name="header">
                          <h:outputText value="#{msg['bulletinBoard.message']}" />
                        </f:facet>
                        <h:outputText value="#{message.message}" />
                      </h:column>
                      
                    </t:dataTable>
                  </td>
                  <td class="bullRight"></td>
                </tr>
                <tr>
                  <td colspan="4" class="bullBottom"></td>
                </tr>
              </table>
              
            </td>
          </tr>
        </table>
        
      </h:form>
      
    
    <% } %>
    
   
    </f:view>
    
    
  </body>
</html>  		

