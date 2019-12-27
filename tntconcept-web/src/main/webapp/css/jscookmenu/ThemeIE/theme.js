/*
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

var cmThemeIEBase = '/JSCookMenu/ThemeIE/';

// the follow block allows user to re-define theme base directory
// before it is loaded.
try
{
	if (myThemeIEBase)
	{
		cmThemeIEBase = myThemeIEBase;
	}
}
catch (e)
{
}

var cmThemeIE =
{
	prefix:	'ThemeIE',
  	// main menu display attributes
  	//
  	// Note.  When the menu bar is horizontal,
  	// mainFolderLeft and mainFolderRight are
  	// put in <span></span>.  When the menu
  	// bar is vertical, they would be put in
  	// a separate TD cell.

  	// HTML code to the left of the folder item
  	mainFolderLeft: '',
  	// HTML code to the right of the folder item
  	mainFolderRight: '',
	// HTML code to the left of the regular item
	mainItemLeft: '',
	// HTML code to the right of the regular item
	mainItemRight: '',

	// sub menu display attributes
	// HTML code to the left of the folder item
	folderLeft: '<img alt="" src="' + cmThemeIEBase + 'folder.gif">',
	// HTML code to the right of the folder item
	folderRight: '<img alt="" src="' + cmThemeIEBase + 'arrow.gif" align="top">&nbsp;',
	// HTML code to the left of the regular item
	itemLeft: '<img alt="" src="' + cmThemeIEBase + 'link.gif">',
	// HTML code to the right of the regular item
	itemRight: '',
	// cell spacing for main menu
	mainSpacing: 0,
	// cell spacing for sub menus
	subSpacing: 0,
	// auto dispear time for submenus in milli-seconds
	delay: 100,

	subMenuHeader: '<div class="ThemeIESubMenuShadow"></div><div class="ThemeIESubMenuBorder">',
	subMenuFooter: '</div>',

	// adjust sub menu positions
	offsetSubAdjust:	[-4, -3]
	// rest use default settins
};

// horizontal split, used only in sub menus
var cmThemeIEHSplit = [_cmNoClick, '<td colspan="3" class="ThemeIEMenuSplit"><div class="ThemeIEMenuSplit"></div></td>'];
// horizontal split, used only in main menu
var cmThemeIEMainHSplit = [_cmNoClick, '<td colspan="3" class="ThemeIEMenuSplit"><div class="ThemeIEMenuSplit"></div></td>'];
// vertical split, used only in main menu
var cmThemeIEMainVSplit = [_cmNoClick, '<div class="ThemeIEMenuVSplit">|</div>'];

/* IE can't do negative margin on tables */
/*@cc_on
	cmThemeIE.subMenuHeader = '<div class="ThemeIESubMenuShadow" style="background-color: #7f7f7f;filter: alpha(opacity=50);"></div><div class="ThemeIESubMenuBorder">';
@*/
