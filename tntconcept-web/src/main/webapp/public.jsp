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

     <h:form id="idMessages">
        <f:verbatim>
        <table class="publicTable" cellspacing="0" cellpadding="0">
          <tr>
            <td class="publicImage">
            </td>
            <td class="publicMsgList">
              
              <table width="100%" height="100%" cellspacing="0" cellpadding="0">
                <tr>
                  <td class="bullTopLeft"></td>
                  <td class="bullTopCenterBack">
                    <div class="bullTopCenterFore">
                      <span class="bullTopCenterTitle">
                        <h:outputText value="#{msg['entry.publicBoard']}" />
                      </span>
                    </div>
                  </td>
                  <td class="bullTopRight"></td>
                </tr>
                <tr>
                  <td class="bullLeft"></td>
                  <td class="bullCenter">
                  </f:verbatim>
                    <t:dataTable id="dataMensajes" styleClass="bullTable"
                                  headerClass="bullTableHeader"
                                  footerClass="bullTableHeader"
                                  rowClasses="bullTableRowO,bullTableRowE"
                                  columnClasses="bullTableColTitle,bullTableColDate,bullTableColUser"
                                  rowOnMouseOver="this.style.backgroundColor='#DBDBD1'"
                                  rowOnMouseOut="this.style.backgroundColor='#FFFFE0'"
                                  rowOnClick="this.style.backgroundColor='#DBDBD1'"
                                  rowOnDblClick="this.style.backgroundColor='#DBDBD1'" 
                                  var="message"	value="#{bulletinBoardBean['public']}"
                                  preserveDataModel="true" rows="15" cellpadding="0" cellspacing="0">
                      
                      <h:column>
                        <f:facet name="header">
                          &nbsp;&nbsp;<h:outputText value="#{mensajes['bulletinBoard.title']}" />
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
                          &nbsp;&nbsp;<h:outputText value="#{mensajes['bulletinBoard.creationDate']}" />
                        </f:facet>
                        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                        <h:outputText value="#{message.creationDate}">
                          <f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+1" />
                        </h:outputText>
                      </h:column>
                      
                      <h:column>
                        <f:facet name="header">
                          <h:outputText value="#{mensajes['bulletinBoard.user']}" />
                        </f:facet>
                        <h:outputText value="#{message.user.login}" />
                        
                        
                       
                        
                      </h:column>
                      
                    </t:dataTable>
                    <f:verbatim>
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
            <td colspan="2" class="publicMsgView">
              
              <table class="msgMain" cellspacing="0" cellpadding="0">
                <tr>
                  <td class="msgTopLeft"></td>
                  <td class="msgTopCenterBack">
                    <div class="msgTopCenterFore">
                      <span class="msgTopCenterTitle">
                        <h:outputText value="#{msg['entry.messageDetail']}" />
                      </span>
                    </div>
                  </td>
                  <td class="msgTopRight"></td>
                </tr>
                <tr>
                  <td class="msgLeft"></td>
                  <td class="msgCenter">
                    <table style="width: 100%;" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="msgLabel"><h:outputText value="#{msg['entry.messageCreatedBy']}:" /></td>
                        <td class="msgValue">
                        </f:verbatim>
                        <h:outputText value="#{bulletinBoardBean.title}" rendered="#{bulletinBoardBean.bulletinBoardSelected}"/>
                        <f:verbatim>
                        </td>
                      </tr>
                      <tr>
                        <td class="msgLabel"><h:outputText value="#{msg['entry.messageTitle']}:" /></td>
                        <td class="msgValue">
                      </f:verbatim>  
                        <h:outputText value="#{bulletinBoardBean.user.name}" rendered="#{bulletinBoardBean.bulletinBoardSelected}" />
                      <f:verbatim>  
                        </td>
                      </tr>
                      <tr>
                        <td class="msgLabel"><h:outputText value="#{msg['entry.messageDate']}:" /></td>
                        <td class="msgValue">
                      </f:verbatim>  
                        <h:outputText value="#{bulletinBoardBean.creationDate}" rendered="#{bulletinBoardBean.bulletinBoardSelected}" >
                        <f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+1" />
                        </h:outputText>
                      <f:verbatim>
                        </td>
                      </tr>
                      <tr>
                        <td class="msgSep" colspan="2"></td>
                      </tr>
                      <tr>
                        <td class="msgValue" colspan="2" >
                        </f:verbatim>
                        
                        <h:outputText value="#{bulletinBoardBean.message}"  rendered="#{bulletinBoardBean.bulletinBoardSelected}" />
                        <f:verbatim>
                        </td>
                      </tr>
                    </table>                          
                                 
                  </td>
                  <td class="msgRight"></td>
                </tr>
                <tr>
                  <td colspan="3" class="msgBottom"></td>
                </tr>
              </table>
              
            </td>
          </tr>
          <tr>
            <td colspan="2" class="publicVersion">            
              <h:outputText value="#{msg['entry.optimizeMessage']}" /><br>            
              TNTConcept version </f:verbatim><h:outputText value="#{applicationBean.build}" /><f:verbatim> Copyright (C) 2007 Autentia Real Business Solution S.L.
              TNTConcept comes with ABSOLUTELY NO WARRANTY;
              <a href="gpl.txt" target="_blank" class="publicVersionEnlace">click here for details.</a> 
              <br>
              This is free software, and you are welcome to redistribute it under certain conditions;
              <a href="gpl.txt" target="_blank" class="publicVersionEnlace">click here for details.</a> 
          	</td>
          </tr>
        </table>
        </f:verbatim>
      </h:form>
