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

// directory of where all the images are
var cmThemeOfficeBase = '/JSCookMenu/ThemeOffice/';

// the follow block allows user to re-define theme base directory
// before it is loaded.
try
{
	if (myThemeOfficeBase)
	{
		cmThemeOfficeBase = myThemeOfficeBase;
	}
}
catch (e)
{
}

var cmThemeOffice =
{
	prefix:	'ThemeOffice',
  	// main menu display attributes
  	//
  	// Note.  When the menu bar is horizontal,
  	// mainFolderLeft and mainFolderRight are
  	// put in <span></span>.  When the menu
  	// bar is vertical, they would be put in
  	// a separate TD cell.

  	// HTML code to the left of the folder item
  	mainFolderLeft: '&nbsp;',
  	// HTML code to the right of the folder item
  	mainFolderRight: '&nbsp;',
	// HTML code to the left of the regular item
	mainItemLeft: '&nbsp;',
	// HTML code to the right of the regular item
	mainItemRight: '&nbsp;',

	// sub menu display attributes

	// HTML code to the left of the folder item
	folderLeft: '<img alt="" src="' + cmThemeOfficeBase + 'spacer.gif">',
	// HTML code to the right of the folder item
	folderRight: '<img alt="" src="' + cmThemeOfficeBase + 'arrow.gif">',
	// HTML code to the left of the regular item
	itemLeft: '<img alt="" src="' + cmThemeOfficeBase + 'spacer.gif">',
	// HTML code to the right of the regular item
	itemRight: '<img alt="" src="' + cmThemeOfficeBase + 'blank.gif">',
	// cell spacing for main menu
	mainSpacing: 0,
	// cell spacing for sub menus
	subSpacing: 0,

	subMenuHeader: '<div class="ThemeOfficeSubMenuShadow"></div>',

	offsetHMainAdjust:	[-1, 0],
	offsetSubAdjust:	[-1, -1]

	// rest use default settings
};

// for horizontal menu split
var cmThemeOfficeHSplit = [_cmNoClick, '<td class="ThemeOfficeMenuItemLeft"></td><td colspan="2" class="ThemeOfficeMenuSplit"><div class="ThemeOfficeMenuSplit"></div></td>'];
var cmThemeOfficeMainHSplit = [_cmNoClick, '<td class="ThemeOfficeMainItemLeft"></td><td colspan="2" class="ThemeOfficeMenuSplit"><div class="ThemeOfficeMenuSplit"></div></td>'];
var cmThemeOfficeMainVSplit = [_cmNoClick, '|'];

/* IE can't do negative margin on tables */
/*@cc_on
	cmThemeOffice.subMenuHeader = '<div class="ThemeOfficeSubMenuShadow" style="background-color: #cccccc; filter: alpha(opacity=35);"></div>';
@*/
