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

<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of projectRoles --%>
<t:dataTable id="roles" var="role" value="#{projectBean.roles}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rows="#{settingBean.mySettings.listSize}" rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListProjectRoleName,editListProjectRoleCostPerHour,editListProjectRoleExpectedHours">
    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <t:commandLink action="#{projectBean.createRoles}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>
        </f:facet>
        <t:commandLink action="#{projectBean.deleteRoles}">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>


    <h:column>
        <f:facet name="header">
            <h:outputText value="*#{msg['projectRole.name']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
            <h:inputText id="name" value="#{role.name}" size="40" required="true" styleClass="requiredFieldClass"/>
        </h:panelGroup>
    </h:column>

    <h:column>
        <f:facet name="header">
            <h:outputText value="*#{msg['projectRole.requireEvidence']}" styleClass="editListHeader"/>
        </f:facet>
        <h:panelGroup>
            <h:selectOneMenu id="client" value="#{role.requireEvidence}" required="true" styleClass="requiredFieldClass">
                <f:selectItems value="#{projectBean.requireEvidenceTypes}" />
            </h:selectOneMenu>
        </h:panelGroup>
    </h:column>


    <h:column>
        <f:facet name="header">
            <h:outputText value="*#{msg['projectRole.costPerHour']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="costPerHour"/>
            <h:inputText id="costPerHour" value="#{role.costPerHour}" size="10" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>
    </h:column>


    <h:column>
        <f:facet name="header">
            <h:outputText value="*#{msg['projectRole.expectedHours']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="expectedHours"/>
            <h:inputText id="expectedHours" value="#{role.expectedHours}" size="10" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>
    </h:column>

    <h:column>
        <f:facet name="header">
            <h:outputText value="*#{msg['projectRole.maxAllowed']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="maxAllowed"/>
            <h:inputText id="maxAllowed" value="#{role.maxAllowed}" size="4" required="true" styleClass="requiredFieldClass">
                <f:converter converterId="autentia.MinuteToHourConverter"/>
                <f:attribute name="allowDecimal" value="false"/>
                <f:validateLongRange minimum="0"/>
            </h:inputText>
        </h:panelGroup>
    </h:column>

    <h:column>
        <f:facet name="header">
            <h:outputText value="#{msg['projectRole.bar']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup rendered="#{role.percentageWorkedByRole<= 1}">

            <t:div styleClass="progressBar"
                   style="background-position: #{250 * (-2 + role.percentageWorkedByRole)}px center;z-index:0;"
                   rendered="#{(activityBean.scheduleModel.mode == 3)}">
                <h:outputFormat value="[{0}h. / {1}h.] - " styleClass="progressBarText">
                    <f:param value="#{role.workedHoursByRole}"/>
                    <f:param value="#{role.expectedHours}"/>
                </h:outputFormat>

                <h:outputText value="#{role.percentageWorkedByRole}" styleClass="progressBarText">
                    <f:convertNumber type="percent" maxFractionDigits="1"/>
                </h:outputText>
            </t:div>
        </h:panelGroup>

        <h:panelGroup rendered="#{role.percentageWorkedByRole > 1}">
            <%--Si el porcentaje es mayor de uno, es decir del 100% se pintará en rojo --%>
            <t:div styleClass="progressBarRed" style="background-position: #{-250}px center;z-index:0;"
                   rendered="#{(activityBean.scheduleModel.mode == 3)}">
                <h:outputFormat value="[{0}h. / {1}h.] - " styleClass="progressBarText">
                    <f:param value="#{role.workedHoursByRole}"/>
                    <f:param value="#{role.expectedHours}"/>
                </h:outputFormat>

                <h:outputText value="#{role.percentageWorkedByRole}" styleClass="progressBarText">
                    <f:convertNumber type="percent" maxFractionDigits="1"/>
                </h:outputText>
            </t:div>
        </h:panelGroup>


    </h:column>


    <%-- Ignored field: project --%>



    <%-- Ignored field: timeUnit --%>



    <%-- Ignored field: isWorkingTime --%>



    <%-- Ignored field: isApprovalRequired --%>

</t:dataTable>



















                
                

                

                

