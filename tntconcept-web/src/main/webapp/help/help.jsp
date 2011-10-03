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
<%@include file="/inc/tlibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
	<title>TNT Concept - Autentia</title>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<style type="text/css" media="all">
		@import url(../css/help.css);
	</style>
</head>

<body>

<div id="contenedor">

	
	<f:view><f:loadBundle basename="com.autentia.tnt.resources.help" var="msg" />
	
		<!-- ··································································· -->
		<!--                                                                     -->
		<!--                       CABECERA DE LA PAGINA                         -->
		<!--                                                                     -->
		<!-- ··································································· -->
		
		<div id="cabecera">
			<h1>${msg['help.helpTitle']} <img width="105" height="50" src="help_files/logo.gif"/></h1> 
		</div>
			
	
		<!-- ··································································· -->
		<!--                                                                     -->
		<!--                      CUERPO DE LA PAGINA                            -->
		<!--                                                                     -->
		<!-- ··································································· -->
	
		<div id="centro">
	
			<!-- ··································································· -->
			<!--                          MENU PRINCIPAL                             -->
			<!-- ··································································· -->
	
			<div id="menu">
				<ul class="menu_principal">
					<li>
						<a href="#commons">${msg['menu.commons']}</a>
						<a href="#admin">${msg['menu.admin']}</a>
						<ul>
							<li><a href="#users">${msg['users.title']}</a></li>
							<li><a href="#editUsers">${msg['editUsers.title']}</a></li>
							<li><a href="#userCategorys">${msg['userCategorys.title']}</a></li>
							<li><a href="#changePassword">${msg['changePassword.title']}</a></li>
							<li><a href="#workingAgreements">${msg['workingAgreements.title']}</a></li>
						</ul>
						<a href="#masterTables">${msg['menu.masterTables']}</a>
						<ul>
							<li><a href="#accountEntryTypes">${msg['accountEntryTypes.title']}</a></li>
							<li><a href="#editAccountEntryType">${msg['editAccountEntryType.title']}</a></li>
							<li><a href="#organizationTypes">${msg['organizationTypes.title']}</a></li>
							<li><a href="#interactionTypes">${msg['interactionTypes.title']}</a></li>
							<li><a href="#organizationISOCategorys">${msg['organizationISOCategorys.title']}</a></li>
							<li><a href="#magazines">${msg['magazines.title']}</a></li>
						</ul>
						<a href="#accounting">${msg['menu.accounting']}</a>
						<ul>
							<li><a href="#bills">${msg['bills.title']}</a></li>
							<li><a href="#editBill">${msg['editBill.title']}</a></li>
							<li><a href="#accounts">${msg['accounts.title']}</a></li>
							<li><a href="#accountEntrys">${msg['accountEntrys.title']}</a></li>
							<li><a href="#editAccountEntry">${msg['editAccountEntry.title']}</a></li>
							<li><a href="#periodicalAccountEntrys">${msg['periodicalAccountEntrys.title']}</a></li>
						</ul>
						<a href="#menuContacts">${msg['menu.contacts']}</a>
						<ul>
							<li><a href="#organizations">${msg['organizations.title']}</a></li>
							<li><a href="#editOrganizations">${msg['editOrganizations.title']}</a></li>
							<li><a href="#interactions">${msg['interactions.title']}</a></li>
							<li><a href="#contacts">${msg['contacts.title']}</a></li>
							<li><a href="#editContact">${msg['editContact.title']}</a></li>
							<li><a href="#offers">${msg['offers.title']}</a></li>
							<li><a href="#projects">${msg['projects.title']}</a></li>
							<li><a href="#editProject">${msg['editProject.title']}</a></li>
						</ul>
						<a href="#quality">${msg['menu.quality']}</a>
						<ul>
							<li><a href="#documentCategorys">${msg['documentCategorys.title']}</a></li>
						</ul>
						<a href="#bulletin">${msg['menu.bulletin']}</a>
						<ul>
							<li><a href="#bulletinBoards">${msg['bulletinBoards.title']}</a></li>
							<li><a href="#companyStates">${msg['companyStates.title']}</a></li>
							<li><a href="#bulletinBoardCategorys">${msg['bulletinBoardCategorys.title']}</a></li>
							<li><a href="#ideas">${msg['ideas.title']}</a></li>
						</ul>
						<a href="#activity">${msg['menu.activity']}</a>
						<ul>
							<li><a href="#activitys">${msg['activitys.title']}</a></li>
							<li><a href="#objectives">${msg['objectives.title']}</a></li>
							
						</ul>
						<a href="#reports">${msg['menu.reports']}</a>
						<ul>
							<li><a href="#generalReports">${msg['generalReports.title']}</a></li>
						</ul>
						<a href="#publications">${msg['menu.publications']}</a>
						<ul>
							<li><a href="#tutorials">${msg['tutorials.title']}</a></li>
							<li><a href="#publications">${msg['publications.title']}</a></li>
						</ul>
						<a href="#menu.holidays">${msg['menu.holidays']}</a>
						<ul>
							<li><a href="#holidays">${msg['holidays.title']}</a></li>
							<li><a href="#requestHolidays">${msg['requestHolidays.title']}</a></li>
                                                        <li><a href="#adminHolidays">${msg['adminHolidays.title']}</a></li>
							
						</ul>
						<a href="#utils">${msg['menu.utils']}</a>
						<ul>
							<li><a href="#books">${msg['books.title']}</a></li>
							<li><a href="#inventarys">${msg['inventarys.title']}</a></li>
						</ul>				
					</li>
				</ul>
			</div>
			
			<!-- ··································································· -->
			<!--                             CONTENIDO                               -->
			<!-- ··································································· -->
	
			<div id="columna_contenido">
	
				<div id="contenido">
		
					<div class="textos">
			
						<h2><a name=commons></a>${msg['commons.title']}</h2>
						<p>${msg['commons.text']}</p>
						<h4>${msg['commons.text0']}</h4>
						<table>
							<tr>
								<td><img src="help_files/home.png"/></td>
								<td>${msg['commons.text1']}</td>
							</tr>
							<tr>
								<td><img src="help_files/reload.png"/></td>
								<td>${msg['commons.text2']}</td>
							</tr>
							<tr>
								<td><img src="help_files/printer.png"/></td>
								<td>${msg['commons.text3']}</td>
							</tr>
							<tr>
								<td><img src="help_files/showLogsDisabled.gif"/></td>
								<td>${msg['commons.text4']}</td>
							</tr>
							<tr>
								<td><img src="help_files/showLogsEnabled.gif"/></td>
								<td>${msg['commons.text5']}</td>
							</tr>
						</table><br/>
						<h4>${msg['commons.text6']}</h4>
						<table>
							<tr>
								<td><img src="help_files/arrow-first.gif"/></td>
								<td>${msg['commons.text7']}</td>
							</tr>
							<tr>
								<td><img src="help_files/arrow-fr.gif"/></td>
								<td>${msg['commons.text8']}</td>
							</tr>
							<tr>
								<td><img src="help_files/arrow-previous.gif"/></td>
								<td>${msg['commons.text9']}</td>
							</tr>
							<tr>
								<td><img src="help_files/arrow-next.gif"/></td>
								<td>${msg['commons.text10']}</td>
							</tr>
							<tr>
								<td><img src="help_files/arrow-ff.gif"/></td>
								<td>${msg['commons.text11']}</td>
							</tr>
							<tr>
								<td><img src="help_files/arrow-last.gif"/></td>
								<td>${msg['commons.text12']}</td>
							</tr>
							<tr>
								<td><img src="help_files/selectPage.jpg"/></td>
								<td>${msg['commons.text13']}</td>
							</tr>
						</table><br/>
						<h4>${msg['commons.text14']}</h4>
						<table>
							<tr>
								<td><img src="help_files/new.gif"/></td>
								<td>${msg['commons.text15']}</td>
							</tr>
							<tr>
								<td><img src="help_files/save.gif"/></td>
								<td>${msg['commons.text16']}</td>
							</tr>
							<tr>
								<td><img src="help_files/delete.gif"/></td>
								<td>${msg['commons.text17']}</td>
							</tr>
							<tr>
								<td><img src="help_files/back.gif"/></td>
								<td>${msg['commons.text18']}</td>
							</tr>
							<tr>
								<td><img src="help_files/help.gif"/></td>
								<td>${msg['commons.text19']}</td>
							</tr>
							<tr>
								<td><img src="help_files/detail.gif"/></td>
								<td>${msg['commons.text20']}</td>
							</tr>
							<tr>
								<td><img src="help_files/errorValidation.jpg"/></td>
								<td>${msg['commons.text21']}</td>
							</tr>
						</table><br/>
						<h4>${msg['commons.text22']}</h4>
						<table>
							<tr>
								<td><img src="help_files/search.gif"/></td>
								<td>${msg['commons.text23']}</td>
							</tr>
							<tr>
								<td><img src="help_files/search_applied.gif"/></td>
								<td>${msg['commons.text24']}</td>
							</tr>
							<tr>
								<td><img src="help_files/eraser.png"/></td>
								<td>${msg['commons.text25']}</td>
							</tr>
							<tr>
								<td><img src="help_files/letters.jpg"/></td>
								<td>${msg['commons.text26']}</td>
							</tr>
						</table>
					</div>
					<!-- Admin -->
					<h2><a name=admin></a>${msg['menu.admin']}</h2>
					<div class="textos">
						<h3><a name=users></a>${msg['users.title']}</h3>
						<p>${msg['users.text']}</p>
						<table>
							<tr>
								<td><img src="help_files/true.png"/></td>
								<td>${msg['users.text0']}</td>
							</tr>
							<tr>
								<td><img src="help_files/false.png"/></td>
								<td>${msg['users.text1']}</td>
							</tr>
						</table>
					</div>
					<div class="textos">
						<h3><a name=editUsers></a><a name=detailUsers></a>${msg['editUsers.title']}</h3>
						<p>${msg['editUsers.text']}</p>
						<h3>${msg['editUsers.text0']}</h3>
						<table>
							<tr>
								<td>${msg['editUsers.text1']}</td>
							</tr>
							<tr>
								<td>${msg['editUsers.text2']}</td>
							</tr>
							<tr>
								<td>${msg['editUsers.text3']}</td>
							</tr>
						</table>
						<p>${msg['editUsers.text4']}</p>
						<table>
							<tr>
								<td><img src="help_files/userDocuments.jpg"/></td>
								<td>${msg['editUsers.text5']}</td>
							</tr>
							<tr>
								<td><img src="help_files/resetPassword.gif"/></td>
								<td>${msg['editUsers.text6']}</td>
							</tr>
						</table>
					</div>
					<div class="textos">
						<h3><a name=userCategorys></a>${msg['userCategorys.title']}</h3>
						<p>${msg['userCategorys.text']}</p>
						<table>
							<tr>
								<td>${msg['userCategorys.text0']}</td>
							</tr>
							<tr>
								<td>${msg['userCategorys.text1']}</td>
							</tr>
							<tr>
								<td>${msg['userCategorys.text2']}</td>
							</tr>
							<tr>
								<td>${msg['userCategorys.text3']}</td>
							</tr>
							<tr>
								<td>${msg['userCategorys.text4']}</td>
							</tr>
							<tr>
								<td>${msg['userCategorys.text5']}</td>
							</tr>
							<tr>
								<td>${msg['userCategorys.text6']}</td>
							</tr>
						</table>
					</div>
					<div class="textos">
						<h3><a name=changePassword></a>${msg['changePassword.title']}</h3>
						<p>${msg['changePassword.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=workingAgreements></a>${msg['workingAgreements.title']}</h3>
						<p>${msg['workingAgreements.text']}</p>
					</div>
					
					<!-- Tablas maestras -->
					<h2><a name=masterTables></a>${msg['menu.masterTables']}</h2>
					<div class="textos">
						<h3><a name=accountEntryTypes></a>${msg['accountEntryTypes.title']}</h3>
						<p>${msg['accountEntryTypes.text']}</p>
						<table>
							<tr>
								<td>${msg['accountEntryTypes.text0']}</td>
							</tr>
							<tr>
								<td>${msg['accountEntryTypes.text1']}</td>
							</tr>
							<tr>
								<td>${msg['accountEntryTypes.text2']}</td>
							</tr>
							<tr>
								<td>${msg['accountEntryTypes.text3']}</td>
							</tr>
						</table>
					</div>
					<div class="textos">
						<h3><a name=editAccountEntryType></a>${msg['editAccountEntryType.title']}</h3>
						<p>${msg['editAccountEntryType.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=organizationTypes></a>${msg['organizationTypes.title']}</h3>
						<p>${msg['organizationTypes.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=interactionTypes></a>${msg['interactionTypes.title']}</h3>
						<p>${msg['interactionTypes.text']}</p>
						<p>${msg['interactionTypes.text0']}</p>
						<table>
							<tr>
								<td>${msg['interactionTypes.text1']}</td>
							</tr>
							<tr>
								<td>${msg['interactionTypes.text2']}</td>
							</tr>
							<tr>
								<td>${msg['interactionTypes.text3']}</td>
							</tr>
							<tr>
								<td>${msg['interactionTypes.text4']}</td>
							</tr>
							<tr>
								<td>${msg['interactionTypes.text5']}</td>
							</tr>
							<tr>
								<td>${msg['interactionTypes.text6']}</td>
							</tr>
						</table>
					</div>
					<div class="textos">
						<h3><a name=organizationISOCategorys></a>${msg['organizationISOCategorys.title']}</h3>
						<p>${msg['organizationISOCategorys.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=magazines></a>${msg['magazines.title']}</h3>
						<p>${msg['magazines.text']}</p>
					</div>
					
					<!-- Contabilidad -->
					<h2><a name=accounting></a>${msg['menu.accounting']}</h2>
					<div class="textos">
						<h3><a name=bills></a>${msg['bills.title']}</h3>
						<p>${msg['bills.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=editBill></a>${msg['editBill.title']}</h3>
						<p>${msg['editBill.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=accounts></a>${msg['accounts.title']}</h3>
						<p>${msg['accounts.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=accountEntrys></a>${msg['accountEntrys.title']}</h3>
						<p>${msg['accountEntrys.text']}</p>
						<table>
							<tr>
								<td><img src="help_files/year.jpg"/></td>
								<td>${msg['accountEntrys.text0']}</td>
							</tr>
							<tr>
								<td><img src="help_files/entrance.png"/></td>
								<td>${msg['accountEntrys.text1']}</td>
							</tr>
							<tr>
								<td><img src="help_files/cost.png"/></td>
								<td>${msg['accountEntrys.text2']}</td>
							</tr>
							<tr>
								<td><img src="help_files/hideMovements.png"/></td>
								<td>${msg['accountEntrys.text3']}</td>
							</tr>
						</table>
					</div>
					<div class="textos">
						<h3><a name=editAccountEntry></a>${msg['editAccountEntry.title']}</h3>
						<p>${msg['editAccountEntry.text']}</p>
						<table>
							<tr>
								<td><img src="help_files/traslatePeriodical.gif"/></td>
								<td>${msg['editAccountEntry.text0']}</td>
							</tr>
						</table>
						<p>${msg['editAccountEntry.text1']}</p>
					</div>
					<div class="textos">
						<h3><a name=periodicalAccountEntrys></a>${msg['periodicalAccountEntrys.title']}</h3>
						<p>${msg['periodicalAccountEntrys.text']}</p>
						<table>
							<tr>
								<td>${msg['periodicalAccountEntrys.text0']}</td>
							</tr>
							<tr>
								<td>${msg['periodicalAccountEntrys.text1']}</td>
							</tr>
							<tr>
								<td>${msg['periodicalAccountEntrys.text2']}</td>
							</tr>
							<tr>
								<td>${msg['periodicalAccountEntrys.text3']}</td>
							</tr>
							<tr>
								<td>${msg['periodicalAccountEntrys.text4']}</td>
							</tr>
						</table>
					</div>
					
					<!-- Contactos -->
					<h2><a name=menuContacts></a>${msg['menu.contacts']}</h2>
					<div class="textos">
						<h3><a name=organizations></a>${msg['organizations.title']}</h3>
						<p>${msg['organizations.text']}</p>
						<p>${msg['organizations.text0']}</p>
						<table>
							<tr>
								<td><img src="help_files/contacts.gif"/></td>
								<td>${msg['organizations.text1']}</td>
							</tr>
						</table>
					</div>
					<div class="textos">
						<h3><a name=editOrganizations></a>${msg['editOrganizations.title']}</h3>
						<p>${msg['editOrganizations.text']}</p>
						<p>${msg['editOrganizations.text0']}</p>
						<table>
							<tr>
								<td>${msg['editOrganizations.text1']}</td>
							</tr>
							<tr>
								<td>${msg['editOrganizations.text2']}</td>
							</tr>
							<tr>
								<td>${msg['editOrganizations.text3']}</td>
							</tr>
							<tr>
								<td>${msg['editOrganizations.text4']}</td>
							</tr>
						</table>
					</div>
					<div class="textos">
						<h3><a name=interactions></a>${msg['interactions.title']}</h3>
						<p>${msg['interactions.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=contacts></a>${msg['contacts.title']}</h3>
						<p>${msg['contacts.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=editContact></a>${msg['editContact.title']}</h3>
						<p>${msg['editContact.text']}</p>
						<img src="help_files/lopd.jpg"/>
					</div>
					<div class="textos">
						<h3><a name=offers></a>${msg['offers.title']}</h3>
						<p>${msg['offers.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=projects></a>${msg['projects.title']}</h3>
						<p>${msg['projects.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=editProject></a>${msg['editProject.title']}</h3>
						<p>${msg['editProject.text']}</p>
						<img src="help_files/roles.png"/>
						<p>${msg['editProject.text0']}</p>
						<img src="help_files/costs.png"/>
						<p>${msg['editProject.text1']}</p>
						<p>${msg['editProject.text2']}</p>
						<p>${msg['editProject.text3']}</p>
					</div>
					
					<!-- Calidad -->
					<h2><a name=quality></a>${msg['menu.quality']}</h2>
					<div class="textos">
						<h3><a name=documentCategorys></a>${msg['documentCategorys.title']}</h3>
						<p>${msg['documentCategorys.text']}</p>
						<table>
							<tr>
								<td><img src="help_files/accessDocument.jpg"/></td>
								<td>${msg['documentCategorys.text0']}</td>
							</tr>
						</table>
					</div>
					
					<!-- Tablón -->
					<h2><a name=bulletin></a>${msg['menu.bulletin']}</h2>
					<div class="textos">
						<h3><a name=bulletinBoards></a>${msg['bulletinBoards.title']}</h3>
						<p>${msg['bulletinBoards.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=companyStates></a>${msg['companyStates.title']}</h3>
						<p>${msg['companyStates.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=bulletinBoardCategorys></a>${msg['bulletinBoardCategorys.title']}</h3>
						<p>${msg['bulletinBoardCategorys.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=ideas></a>${msg['ideas.title']}</h3>
						<p>${msg['ideas.text']}</p>
					</div>
					
					<!-- Seguimiento -->
					<h2><a name=activity></a>${msg['menu.activity']}</h2>
					<div class="textos">
						<h3><a name=activitys></a>${msg['activitys.title']}</h3>
						<p>${msg['activitys.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=objectives></a>${msg['objectives.title']}</h3>
						<p>${msg['objectives.text']}</p>
						<h4>${msg['objectives.text0']}</h4>
						<p>${msg['objectives.text1']}</p>
						<table>
							<tr>
								<td><img src="help_files/propagate.png"/></td>
								<td>${msg['objectives.text2']}</td>
							</tr>
							<tr>
								<td><img src="help_files/complete.png"/></td>
								<td>${msg['objectives.text3']}</td>
							</tr>
						</table>
						<h4>${msg['objectives.text4']}</h4>
						<p>${msg['objectives.text5']}</p>
						<table>
							<tr>
								<td><img src="help_files/complete.png"/></td>
								<td>${msg['objectives.text6']}</td>
							</tr>
						</table>
					</div>
					
                                        <!-- Informes -->
                                        <h2><a name=reports></a>${msg['menu.reports']}</h2>
                                        <div class="textos">
						<h3><a name=generalReports></a>${msg['generalReports.title']}</h3>
						<p>${msg['generalReports.text']}</p>
					</div>
					
                                        <!-- Publicaciones -->
                                        <h2><a name=publications></a>${msg['menu.publications']}</h2>
					<div class="textos">
						<h3><a name=tutorials></a>${msg['tutorials.title']}</h3>
						<p>${msg['tutorials.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=publications></a>${msg['publications.title']}</h3>
						<p>${msg['publications.text']}</p>
					</div>
					
                                        <!-- Vacaciones -->
                                        <h2><a name=menu.holidays></a>${msg['menu.holidays']}</h2>
					<div class="textos">
						<h3><a name=holidays></a>${msg['holidays.title']}</h3>
						<p>${msg['holidays.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=requestHolidays></a>${msg['requestHolidays.title']}</h3>
						<p>${msg['requestHolidays.text']}</p>
					</div>
                                         <div class="textos">
						<h3><a name=adminHolidays></a>${msg['adminHolidays.title']}</h3>
						<p>${msg['adminHolidays.text']}</p>
					</div>
                                         
                                       <!-- Utilidades -->
                                       <h2><a name=utils></a>${msg['menu.utils']}</h2>
					<div class="textos">
						<h3><a name=books></a>${msg['books.title']}</h3>
						<p>${msg['books.text']}</p>
					</div>
					<div class="textos">
						<h3><a name=inventarys></a>${msg['inventarys.title']}</h3>
						<p>${msg['inventarys.text']}</p>
					</div>                                         
				</div>		
		</div>
	
	</div>
		
	<!-- ··································································· -->
	<!--                                                                     -->
	<!--                           PIE DE PAGINA                             -->
	<!--                                                                     -->
	<!-- ··································································· -->

	<div id="pie">

	</div>
	
	</f:view>

</div>

</body>

</html>