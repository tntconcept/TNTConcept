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

<h:panelGrid styleClass="mainMenu"
	rowClasses="greyMenu,greyMenu" columns="1">
	<h:column>
		<h:panelGroup>
			<h:form>
			<input type="hidden" name="jscook_action" />
				<t:jscookMenu layout="hbr" theme="ThemeOffice"
					styleLocation="/css/jscookmenu">
					<t:navigationMenuItems id="navigationBar"
						value="#{dynMenuBean.navItems}" />
				</t:jscookMenu>
			</h:form>
		</h:panelGroup>
	</h:column>

	<h:column>
		<h:panelGroup>
			<h:panelGrid cellpadding="0" cellspacing="0" styleClass="mainMenu"
				columnClasses="alignLeft,userInfo" columns="1">
				<h:column>
						<%@include file="/privateIcons.jsp"%>					
				</h:column>
				<%-- <h:column>
					<f:verbatim>
						<%@include file="/pages/userInfo.jsp"%>
					</f:verbatim>
				</h:column> --%>
			</h:panelGrid>
		</h:panelGroup>
	</h:column>
	
</h:panelGrid>
