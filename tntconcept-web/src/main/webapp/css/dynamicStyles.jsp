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

<%--  
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.  
 *	
 * 	This program is free software; you can redistribute it and/or
 * 	modify it under the terms of the GNU General Public License
 * 	as published by the Free Software Foundation; either version 2
 * 	of the License, or (at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program; if not, write to the Free Software
 * 	Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 	Autentia Real Bussiness Solution S.L.
 * 	Tlf: +34 91 675 33 06, +34 655 99 11 72
 * 	Fax: +34 91 656 65 04
 * 	info@autentia.com																
--%>
<%@page language="java" contentType="text/css; charset=UTF-8"%>
/******************************************************************************/
/***************************** Standard styles ********************************/
/******************************************************************************/
body { 
font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
font-size: 10pt;
margin: 0 0 0 0;
color: black;
background-color: white;
}
frameset {
background-color: white;
}
form {
  border: 0px;
  margin: 0px 0px 0px 0px;
}
td { 
font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
font-size: 10pt;
}	
th { 
font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
font-size: 10pt;
}	
input { 
font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
font-size: 10pt;
}
textarea { 
font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
font-size: 10pt;
}
a {
font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
font-size: 10pt;
text-decoration: none;
color: #202020;
}
img {
border: 0px;
}
.posRatio {
	margin: 0pt;	
	position: absolute;	
	left: 0px;
	top: 90px;
	width: 470px;	
}
.posRatioVar1 {
	margin: 0pt;	
	position: absolute;	
	left: 475px;
	top: 90px;
	width: 80px;	
}
.posRatio1 {
	margin: 0pt;	
	position: absolute;	
	left: 560px;
	top: 90px;
	width: 200px;	
}
.posRatioVar2 {
	margin: 0pt;	
	position: absolute;	
	left: 765px;
	top: 90px;
	width: 80px;	
}
.posRatio2 {
	margin: 0pt;	
	position: absolute;	
	left: 855px;
	top: 90px;
	width: 200px;	
}
.editLabelRatiosW {
background-color: #AFBDC6;
text-align: left;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 11pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 50%;
border-bottom: 1px solid #C7D7DF;
}
.detailLabelRatiosRes {
background-color: #FFFFE0;
text-align: left;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 11pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 50%;
border-bottom: 1px solid #C7D7DF;
}
.detailLabelRatios {
background-color: #AFBDC6;
text-align: left;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 11pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 50%;
border-bottom: 1px solid #C7D7DF;
}
.detailLabelRatiosTitle {
background-color: #AFBDC6;
text-align: center;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 12pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 50%;
border-bottom: 1px solid #C7D7DF;
}
.detailLabelRatiosAsset {
background-color: #AAAAFF;
text-align: left;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 11pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 50%;
border-bottom: 1px solid #C7D7DF;
}
.detailLabelRatiosLia {
background-color: #FF7777;
text-align: left;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 11pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 50%;
border-bottom: 1px solid #C7D7DF;
}
.detailLabelRatiosResC {
background-color: #FFFFE0;
text-align: left;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 10pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 50%;
border-bottom: 1px solid #C7D7DF;
}
.detailLabelRatiosC {
background-color: #AFBDC6;
text-align: left;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 10pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 65%;
border-bottom: 1px solid #C7D7DF;
}
.detailLabelRatiosTitleC {
background-color: #AFBDC6;
text-align: center;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 11pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 50%;
border-bottom: 1px solid #C7D7DF;
}
.detailLabelRatiosAssetC {
background-color: #AAAAFF;
text-align: left;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 10pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 50%;
border-bottom: 1px solid #C7D7DF;
}
.detailLabelRatiosLiaC {
background-color: #FF7777;
text-align: left;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 10pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 50%;
border-bottom: 1px solid #C7D7DF;
}
/******************************************************************************/
/***************************** Generic styles *********************************/
/******************************************************************************/
/** Global styles */
#notAuthorized {
width: 100%;
height: 100%;
color: red;
font-size: 2em;
text-align: center;
padding-top: 4em;
}
/** Styles for messages */
.error {
color: red;
font-weight: bold;
padding: 2px;
background-color: #FFE0E0;
display: block;
}
.warning {
font-weight: bold;
padding: 2px;
background-color: #FFFFA0;
padding: 2px;
display: block;
}
.info {
font-weight: bold;
padding: 2px;
background-color: #E0E0FF;
padding: 2px;
display: block;
}
.warning2 {
font-weight: bold;
padding: 2px;
background-color: #FFFFA0;
padding: 2px;
}
/* Header styles */
.headerLogo { 
width: 530px;
height: 46px;
}
.headerIcons { 
height: 46px;
}
.headerTag {
background-color: #4A859A;
background-image: url('<%=request.getContextPath()%>/img/demo_cab2.gif');
background-position: top left;
background-repeat: no-repeat;
height: 6px;
}
.headerLocationCell { 
background-color: #4A859A;
vertical-align: middle;
}
.headerLocationTable {
height: 33px;
}
.headerLocationImage {
width: 42px;
background-image: url('<%=request.getContextPath()%>/img/organigram.gif');
background-repeat: no-repeat;
background-position: top left;
}
.headerLocationLabel { 
font-size: 12px;
color: #555555;
text-align: left;
}
#headerLocation { 
background-image: url('<%=request.getContextPath()%>/img/location.gif');
background-repeat: no-repeat;
background-position: top left;
font-size: 14px;
color: #D0E0E5;
text-align: left;
padding-left: 10px;
width: 350px;
}
.headerUserCell { 
background-color: #4A859A;
text-align: right;
height: 45px;
}
.headerUserTable { 
height: 100%;
vertical-align: bottom;
}
.headerUserLabel { 
color: #444444; 
font-size: 8pt;
}
.headerUser { 
color: #FFFFFF; 
font-size: 8pt;
padding-left: 1em;
}
.headerUserInput { 
margin: 0 0.5em 0 0.5em; 
font-size: 8pt;
color: #444444;
background: #CCCCCC;
border: 1px solid #FFFFFF
}
.headerPwdLabel, .headerRoleLabel { 
color: #444444;
font-size: 8pt;

}
.headerPwdInput { 
margin: 0 0.5em 0 0.5em; 
font-size: 8pt;
color: #444444;
background: #CCCCCC;
border: 1px solid #FFFFFF

}
.headerRole { 
color: #FFFFFF;
font-size: 8pt;
padding-left: 1em;
}
.headerRoleLabel { 
color: #444444;
font-size: 8pt;
}
.headerLoginButton {
padding: 0 0.5em 0.1em 0;
vertical-align: bottom;
}
.headerExitButton {
color: #FFFFFF;
padding: 0 0.5em 0.1em 1em;
width: 18px;
vertical-align: middle;
}
.headerSep {
background-color: #4A859A;
height: 1px;
}
#contentFrame {
width: 100%;
height: 100%;
border: 0;
}
/* JSF radio button styles */
.radioButton {
display: inline;
}
/* Icon styles */
.iconSet {
width: 100%;
height: 100%;
text-align: left;
margin: 0px 0.0em 0 0;
}
/* Icon styles */
.privateIconSet {
width: 100%;
height: 100%;
text-align: right;
margin: 0px 0.0em 0 0;
vertical-align: top;
}
.icon { 
color: #1E5990;
margin: 0 0.0em 0 0;
}
/* User styles */
.userPage {
background-color: #FFFFFF;
text-align: right;
}
/* Public zone styles */
.publicTable {
width: 100%;
height: 100%;
}
.publicImage {
width: 380px;
height: 215px;
background-image: url('<%=request.getContextPath()%>/img/computer.gif');
background-repeat: no-repeat;
background-position: center;
}
.publicMsgList {
height: 215px;
vertical-align: top;
padding: 5px 5px 0 0;
}
.publicMsgView {
padding: 5px 5px 5px 5px;
}
.publicVersion {
color: #1E5990;
font-size: 0.7em;
text-align: center;
}
a.publicVersionEnlace {
color: #1E5990;
font-size: 0.9em;
text-align: center;
}
/* Control panel home styles */
.Ru {
width: 100%;
height: 100%;
}
.controlHomeTable {
width: 100%;
height: 100%;
}
.controlHomeCatList {
height: 40%;
vertical-align: top;
padding: 5px 5px 0 0;
}
.controlHomeMsgList {
height: 60%;
vertical-align: top;
padding: 10px 5px 5px 0;
}
/* Bulletin board styles */
.bullTopLeft {
width: 1px; 
background-image: url('<%=request.getContextPath()%>/img/bull_side.gif'); 
background-repeat: repeat-x;
}
.bullTopCenterBack {
height: 35px; 
background-image: url('<%=request.getContextPath()%>/img/bull_back.gif'); 
background-repeat: repeat-x;
}
.bullTopCenterFore {
width: 100%; 
height: 100%; 
background-image: url('<%=request.getContextPath()%>/img/demo_paneltablon.gif'); 
background-repeat: no-repeat;
}
.bullTopCenterTitle {
position: relative; 
left: 40px; 
top: 8px;
color: white;
font-size: 13pt;
font-weight: bold;
}
.bullTopRight {
width: 1px; 
background-image: url('<%=request.getContextPath()%>/img/bull_side.gif'); 
background-repeat: repeat-x;
}
.bullLeft {
width: 1px; 
background-color: #FCA673;
}
.bullCenter {
vertical-align: top;
}
.bullRight {
width: 1px; 
background-color: #FCA673;
}
.bullBottom {
height: 1px; 
background-color: #FCA673;
}
.bullTable {
padding: 0;
width: 100%;
}
.bullTableHeader {
font-weight:bold;
background-color: #AFBDB6;
font-size: 11pt;
font-variant: small-caps;
padding: 0;
text-align: left;
border: none;
}
.bullTableRowO {
background-color: #FFFFE0;
border-bottom: #D9D7BB 1px solid;
}
.bullTableRowE {
background-color: #FFFFE0;
border-bottom: #D9D7BB 1px solid;
}
.bullTableColCategory {
text-align: left;
padding: 2px 0 0 2px;
}
.bullTableColTitle {
text-align: left;
width: 20%;
border-bottom: #D9D7BB 1px solid;
}
.bullTableColDate {
text-align: left;
width: 15%;
border-bottom: #D9D7BB 1px solid;
}
.bullTableColUser {
text-align: left;
width: 15%;
border-bottom: #D9D7BB 1px solid;
}
.bullTableColMessage {
text-align: left;
width: 50%;
border-bottom: #D9D7BB 1px solid;
}
.bullTableCatLink {
text-decoration: none;
color: #000000;
}
/* Message detail styles */
.msgMain {
width: 100%;
height: 100%;
}
.msgTopLeft {
width: 1px; 
background-image: url('<%=request.getContextPath()%>/img/bull_side.gif'); 
background-repeat: repeat-x;
}
.msgTopCenterBack {
height: 35px; 
background-image: url('<%=request.getContextPath()%>/img/bull_back.gif'); 
background-repeat: repeat-x;
}
.msgTopCenterFore {
width: 100%; 
height: 100%; 
background-image: url('<%=request.getContextPath()%>/img/demo_paneltablon.gif'); 
background-repeat: no-repeat;
}
.msgTopCenterTitle {
position: relative; 
left: 40px; 
top: 8px;
color: white;
font-size: 13pt;
font-weight: bold;
}
.msgTopRight {
width: 1px; 
background-image: url('<%=request.getContextPath()%>/img/bull_side.gif'); 
background-repeat: repeat-x;
}
.msgLeft {
width: 1px; 
background-color: #FCA673;
}
.msgCenter {
vertical-align: top;
padding: 5px 5px 5px 5px;
}
.msgRight {
width: 1px; 
background-color: #FCA673;
}
.msgBottom {
height: 1px; 
background-color: #FCA673;
}
.msgLabel {
color: #FF9C73; 
font-weight: bold;
width: 15%;
}
.msgValue {
font-weight: bold;
width: 85%;
}
.msgSep {
height: 2px; 
background-color: #FF9C73;
}
.msgContent {
width: 100%; 
height: 100%; 
overflow: auto;
padding: 5px 0 5px 0;
}
/** Tree menu styles */
.menuNodeFolder {
text-decoration: none
}
.menuNodeChildCount {
color: blue;
text-decoration: none
}
.menuNodeChild {
color: blue;
text-decoration: none
}
.menuNodeChildSel {
color: blue;
text-decoration: none;
font-weight: bold
}
/** Title styles */
.title {
background-color: #C7D7DF;
width: 100%;
font-size: 13pt; 
font-weight: bold;
font-variant: small-caps;
color: #707070;
}
.titleText {
padding: 2px 2px 1em 2px;
}
.titleImg {
border: 0;
}
/** Command cell styles */
.cmdImg {
vertical-align: top;
border: 0;
}
/** Generic list styles */
.listTable {
width: 100%;
border-bottom: 1px solid #C7D7DF;
border-left: 1px solid #C7D7DF;
border-right: 1px solid #C7D7DF;
}
.listHeaderCell {
background-color: #AFBDC6;
text-align: left;
padding: 2px;
}
.listHeader {
font-size: 11pt;
font-variant: small-caps;
color: #606060;
}
.listFooter {
}
.listCmdCell {
padding: 2px 0 0 2px;
white-space: nowrap;
vertical-align: top;
}
.listRowO {
background-color: #FFFFE0;
}
.listRowE {
background-color: #E6E6CA;
}
.listRowSel {
background-color: #C7D7DF;
}
/** Generic inline edit styles */
.editListTable {
width: 100%;
border-bottom: 1px solid #C7D7DF;
border-left: 1px solid #C7D7DF;
border-right: 1px solid #C7D7DF;
}
.editListHeaderCell {
background-color: #AFBDC6;
text-align: left;
padding: 2px;
}
.editListHeader {
font-size: 11pt;
font-variant: small-caps;
color: #606060;
}
.editListRowO {
background-color: #FFFFE0;
}
.editListRowE {
background-color: #FFFFE0;
}
/** Generic inline detail styles */
.detailListTable {
width: 100%;
border-bottom: 1px solid #C7D7DF;
border-left: 1px solid #C7D7DF;
border-right: 1px solid #C7D7DF;
}
.detailListHeaderCell {
background-color: #AFBDC6;
text-align: left;
padding: 2px;
}
.detailListHeader {
font-size: 11pt;
font-variant: small-caps;
color: #606060;
}
.detailListRowO {
background-color: #FFFFE0;
}
.detailListRowE {
background-color: #FFFFE0;
}
/** Generic search styles */
.searchTable {
border-left: 1px solid #C7D7DF;
border-right: 1px solid #C7D7DF;
width: 100%;
}
.searchLabel {
background-color: #AFBDC6;
text-align: left;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 11pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 20%;
border-bottom: 1px solid #C7D7DF;
}
.searchFieldCell {
border-bottom: 1px solid #C7D7DF;
background-color: #FFFFE0;
text-align: left;
padding: 2px 2px 2px 2px;
width: 80%;
}
.searchField {
}
/** Generic edit styles */
.editTable {
border-left: 1px solid #C7D7DF;
border-right: 1px solid #C7D7DF;
width: 100%;
}
.editLabelRO, .editLabelRW {
background-color: #AFBDC6;
text-align: left;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 11pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 20%;
border-bottom: 1px solid #C7D7DF;
}
.editLabelRO {
color: #808080;
}
.editFieldCell {
border-bottom: 1px solid #C7D7DF;
background-color: #FFFFE0;
text-align: left;
padding: 2px 2px 2px 2px;
width: 80%;
}
.editFieldRO {
color: #808080;
white-space: pre;
}
.editFieldRW {
}
/** Generic details styles */
.detailTable {
border-left: 1px solid #C7D7DF;
border-right: 1px solid #C7D7DF;
width: 100%;
}
.detailLabelRO, .detailLabelRW {
background-color: #AFBDC6;
text-align: left;
vertical-align: top;
padding: 2px 0.5em 2px 2px;
font-size: 11pt;
font-variant: small-caps;
font-weight: bold;
color: #606060;
width: 20%;
border-bottom: 1px solid #C7D7DF;
}
.detailLabelRO {
color: #808080;
}
.detailFieldCell {
border-bottom: 1px solid #C7D7DF;
background-color: #FFFFE0;
text-align: left;
padding: 2px 2px 2px 2px;
width: 80%;
}
.detailFieldRO {
color: #808080;
white-space: pre;
}
.detailFieldRW {
}
/** Generic paginator styles */
.scroller {
background-color: #AFBDC6;
border-bottom: 1px solid #C7D7DF;
border-left: 1px solid #C7D7DF;
border-right: 1px solid #C7D7DF;
}
.paginator {
border-spacing: 0px;
border-collapse: collapse;
}
.paginatorCell {
padding: 2px 0.5em 0 0.5em;
vertical-align: middle;
}
.paginatorActiveCell {
padding: 2px 0.5em 0 0.5em;
background-color: #C7D7DF;
vertical-align: middle;
}
/** Generic ABC pager styles */
.abcPager {
background-color: #C7D7DF;
border-bottom: 1px solid #C7D7DF;
border-left: 1px solid #C7D7DF;
border-right: 1px solid #C7D7DF;
border-top: 1px solid #AFBDC6;
padding: 3px 0.5em 3px 0.5em;
text-align: right;
}
.abcPagerTitle {
font-weight: bold;
font-variant: small-caps;
color: #707070;
}
.abcPagerLetter {
padding: 0 3px 0 3px;
color: #707070;
}
.abcPagerSelectedLetter {
padding: 0 3px 0 3px;
background-color: #FF9650;
font-weight: bold;
}
/** Generic calendar styles */
.calendarStyleClass {
  border: 1px solid #C7D7DF;
  border-spacing: 1px;
  background-color: #C7D7DF;
}
.calendarMonthYearRowClass {
  font-weight: bold;
  font-variant: small-caps;
  color: #606060;
  border: 1px solid blue;
  background-color: #AFBDC6;
  text-align: center;
}
.calendarWeekRowClass {
  font-weight: bold;
  font-variant: small-caps;
  color: #606060;
  border: 1px solid green;
  background-color: #E6E6CA;
  text-align: center;
}
.calendarDayCellClass {
  background-color: #FFFFE0;
  text-align: right;
}
.calendarCurrentDayCellClass {
  background-color: #E6E6CA;
  text-align: right;
  font-weight: bold;
}
.requiredFieldClass {
	background-color: #FFFFEE;
}
/******************************************************************************/
/******************** Specific (single page) styles ***************************/
/******************************************************************************/
/** Activitys page styles */
.activitysTable { /* Table to layout list of activities on top and activity detail on bottom */
width: 100%;
height: 40%;
border-spacing: 0px;
}
.activitysCalendarCell { /* Cell containing the calendar */
width: 1%;
height: 1%;
vertical-align: top;
}
.activitysListCell { /* Cell containing the activities list */
width: 99%;
height: 1%;
vertical-align: top;
}
/** userPasswordReset page styles */
.userPasswordResetPassword { /* New password value */
margin-left: 2em;
font-weight: bold;
border: 1px solid red;
font-variant: normal;
}
.mainMenu {
width: 100%;
height: 22px;
border-spacing: 0px;
border-collapse: collapse;
background-color: #A9A9A9;
}

.greyMenu {
background-color: #A9A9A9;
border-bottom-style: solid;
border-bottom-color: #858585;
border-bottom-width: 1px;
border-top-style: solid;
border-top-color: #CCCCCC;
border-top-width: 1px;
}
.dumpMenu {
}
.whiteMenu {
background-color: #FFFFFF;
}
.logoMenu {
width: 105px;
}
.genericIconsMenu {
text-align: right;
vertical-align: top;
}
.loginMenu {
width: 20%;
border-spacing: 0px;
border-collapse: collapse;
}
.userInfo {
width: 20%;
text-align: right;
}
.textToolbar {
text-align: left;
font-size: 8px;
font-weight: bold;
vertical-align: top;
color: white;
width: 200px;
}

.mainWindow {
width: 100%;
height: 100%;
border-spacing: 0px;
border-collapse: collapse;
}

.betweenIcons {
width: 10px;
}

.tableImg {
border-spacing: 0px;
border-collapse: collapse;
background-color: #A9A9A9;
height: 50px;
width: 40px;
}

.alignTop {
vertical-align: top;
}

.iconsToolbar {
height: 22px;
border-spacing: 0px;
border-collapse: collapse;
align: left;
}

.listFooterGlobal {
font-size: 11pt;
font-weight: bold;
font-variant: small-caps;
background-color: #ACACAC;
}
.editFieldCellGlobal {
border-bottom: 1px solid #C7D7DF;
background-color: #FFFFE0;
text-align: left;
padding: 2px 2px 2px 2px;
width: 20%;
}


.tabPanel {
width: 100%;
background-color: #c0c0c0;
}


.tabPanel .activeTab, .tabPanel .activeTab {
	margin-left: 10px;
}

.activeTab {
	width: 15%;
	height: 4%;
	background-color: #c7d7df;
	margin-left: 3px;
	margin-right: 3px;
}

.activeTab input {
	
	background-color: #c7d7df;
}

.inactiveTab {
	width: 15%;
	height: 4%;
	background-color: #AFBDC6;
	margin-left: 3px;
	margin-right: 3px;
}

.inactiveTab input {
	
	background-color: #AFBDC6;
}

.activeSubcells, .inactiveSubcells {
	display:none;
}

#tabActivity .title, #tabExternalActivity .title {
	margin-top : 0;
}

.noPadding {
	padding: 0px;
}