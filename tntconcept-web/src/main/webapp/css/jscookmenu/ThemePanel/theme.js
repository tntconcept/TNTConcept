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
var cmThemePanelBase = '/JSCookMenu/ThemePanel/';

// the follow block allows user to re-define theme base directory
// before it is loaded.
try
{
	if (myThemePanelBase)
	{
		cmThemePanelBase = myThemePanelBase;
	}
}
catch (e)
{
}

var cmThemePanel =
{
	prefix:	'ThemePanel',
  	// main menu display attributes
  	//
  	// Note.  When the menu bar is horizontal,
  	// mainFolderLeft and mainFolderRight are
  	// put in <span></span>.  When the menu
  	// bar is vertical, they would be put in
  	// a separate TD cell.

  	// HTML code to the left of the folder item
  	mainFolderLeft: '<img alt="" src="' + cmThemePanelBase + 'blank.gif">',
  	// HTML code to the right of the folder item
  	mainFolderRight: '<img alt="" src="' + cmThemePanelBase + 'arrow.gif">',
	// HTML code to the left of the regular item
	mainItemLeft: '<img alt="" src="' + cmThemePanelBase + 'blank.gif">',
	// HTML code to the right of the regular item
	mainItemRight: '<img alt="" src="' + cmThemePanelBase + 'blank.gif">',

	// sub menu display attributes

	// HTML code to the left of the folder item
	folderLeft: '<img alt="" src="' + cmThemePanelBase + 'blank.gif">',
	// HTML code to the right of the folder item
	folderRight: '<span style="border: 0; width: 24px;"><img alt="" src="' + cmThemePanelBase + 'arrow.gif"></span>',
	// HTML code to the left of the regular item
	itemLeft: '<img alt="" src="' + cmThemePanelBase + 'blank.gif">',
	// HTML code to the right of the regular item
	itemRight: '<img alt="" src="' + cmThemePanelBase + 'blank.gif">',
	// cell spacing for main menu
	mainSpacing: 0,
	// cell spacing for sub menus
	subSpacing: 0,

	subMenuHeader: '<div class="ThemePanelSubMenuShadow"></div><div class="ThemePanelSubMenuBorder">',
	subMenuFooter: '</div>',

	// move the first lvl of vbr submenu up a bit
	offsetVMainAdjust:	[0, -2],
	// also for the other lvls
	offsetSubAdjust:	[0, -2]

	// rest use default settings
};

// for sub menu horizontal split
var cmThemePanelHSplit = [_cmNoClick, '<td colspan="3" class="ThemePanelMenuSplit"><div class="ThemePanelMenuSplit"></div></td>'];
// for vertical main menu horizontal split
var cmThemePanelMainHSplit = [_cmNoClick, '<td colspan="3" class="ThemePanelMenuSplit"><div class="ThemePanelMenuSplit"></div></td>'];
// for horizontal main menu vertical split
var cmThemePanelMainVSplit = [_cmNoClick, '|'];

/* IE can't do negative margin on tables */
/*@cc_on
	cmThemePanel.subMenuHeader = '<div class="ThemePanelSubMenuShadow" style="background-color: #aaaaaa;filter: alpha(opacity=50);"></div><div class="ThemePanelSubMenuBorder">';
@*/
