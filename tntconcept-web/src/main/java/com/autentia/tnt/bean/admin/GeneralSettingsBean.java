/**
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

package com.autentia.tnt.bean.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.myfaces.custom.schedule.model.ScheduleModel;
import org.apache.myfaces.custom.schedule.renderer.AbstractScheduleRenderer;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.businessobject.Setting;
import com.autentia.tnt.manager.admin.SettingManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.SettingPath;

/**
 * Settings bean for bitacore
 * 
 * @author german
 */
public class GeneralSettingsBean extends BaseBean {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private final SelectItem[] themeItems = new SelectItem[3];
	
	private final SelectItem[] modeItems = new SelectItem[4];
	
	private final SelectItem[] localeItems = new SelectItem[3];
	
	/** Settings manager */
	private static final SettingManager			settings				= SettingManager.getDefault();
	
	/** preferred theme of schedule */
	private String	theme = SettingManager.getString(settings.get(SettingPath.BITACORE_PREFERRED_THEME, false), AbstractScheduleRenderer.OUTLOOK_THEME); 
		
	
	/** preferred mode of schedule */
	private int		mode = SettingManager.getInt(settings.get(SettingPath.BITACORE_PREFERRED_MODE, false), ScheduleModel.MONTH);
		
	
	/** preferred display hour from */
	private int		displayHourFrom = SettingManager.getInt(settings.get(SettingPath.BITACORE_PREFERRED_DISPLAY_HOUR_FROM,false),8);
		
	
	/** preferred display hour until */
	private int		displayHourUntil = SettingManager.getInt(settings.get(SettingPath.BITACORE_PREFERRED_DISPLAY_HOUR_UNTIL, false),19);		
	
	/** preferred working hour day start */
	private int		workingDayHourStarts = SettingManager.getInt(settings.get(SettingPath.BITACORE_PREFERRED_DAY_HOUR_START, false), 9);
	
	/** preferred working hours for day */
	private int	workingHours = SettingManager.getInt(settings.get(SettingPath.BITACORE_PREFERRED_DAY_HOURS, false), 8);
		
					
	/** preferred header format */
	private String	headerFormat = SettingManager.getString(settings.get(SettingPath.BITACORE_PREFERRED_HEADER_FORMAT, false), "dd/MM/yy");
		
	/** preferred locale */
	private String	locale = SettingManager.getString(settings.get(SettingPath.GENERAL_PREFERRED_LOCALE, false), FacesUtils.getViewLocale().getLanguage());
	
	private boolean loadExtraDays = true;
	
	
	/** Number of items in list */
	private int	listSize = SettingManager.getInt(settings.get(SettingPath.GENERAL_PREFERRED_LIST_SIZE, false), 12);
	
	
	public GeneralSettingsBean() {
		generateSelectItems();
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getDisplayHourFrom() {
		return displayHourFrom;
	}

	public void setDisplayHourFrom(int displayHourFrom) {
		this.displayHourFrom = displayHourFrom;
	}

	public int getDisplayHourUntil() {
		return displayHourUntil;
	}

	public void setDisplayHourUntil(int displayHourUntil) {
		this.displayHourUntil = displayHourUntil;
	}

	public int getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(int workingHours) {
		this.workingHours = workingHours;
	}

	public String getHeaderFormat() {
		return headerFormat;
	}

	public void setLoadExtraDays(boolean loadExtraDays) {
		this.loadExtraDays = loadExtraDays;
	}

	public boolean getLoadExtraDays() {
		return loadExtraDays;
	}

	public void setHeaderFormat(String headerFormat) {
		this.headerFormat = headerFormat;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public SelectItem[] getThemeItems() {
		return themeItems;
	}

	public SelectItem[] getModeItems() {
		return modeItems;
	}

	public SelectItem[] getLocaleItems() {
		return localeItems;
	}

	public int getWorkingDayHourStarts() {
		return workingDayHourStarts;
	}

	public void setWorkingDayHourStarts(int workingDayHourStarts) {
		this.workingDayHourStarts = workingDayHourStarts;
	}
	
	public int getWorkingDayHourEnds() {
		return workingDayHourStarts + workingHours;
	}
	

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public void save() {
		Setting val = settings.get(SettingPath.BITACORE_PREFERRED_THEME, true);		
		SettingManager.setValue(val, theme);
		settings.save(val);
		
		val = settings.get(SettingPath.BITACORE_PREFERRED_MODE, true);		
		SettingManager.setValue(val, mode);
		settings.save(val);
		
		val = settings.get(SettingPath.BITACORE_PREFERRED_DISPLAY_HOUR_FROM, true);		
		SettingManager.setValue(val, displayHourFrom);
		settings.save(val);
		
		val = settings.get(SettingPath.BITACORE_PREFERRED_DISPLAY_HOUR_UNTIL, true);		
		SettingManager.setValue(val, displayHourUntil);
		settings.save(val);
		
		val = settings.get(SettingPath.BITACORE_PREFERRED_DAY_HOUR_START, true);		
		SettingManager.setValue(val, workingDayHourStarts);
		settings.save(val);
		
		val = settings.get(SettingPath.BITACORE_PREFERRED_DAY_HOURS, true);		
		SettingManager.setValue(val, workingHours);
		settings.save(val);
		
		val = settings.get(SettingPath.BITACORE_PREFERRED_HEADER_FORMAT, true);		
		SettingManager.setValue(val, headerFormat);
		settings.save(val);
		
		
		val = settings.get(SettingPath.GENERAL_PREFERRED_LIST_SIZE, true);		
		SettingManager.setValue(val, listSize);
		settings.save(val);
		
		val = settings.get(SettingPath.GENERAL_PREFERRED_LOCALE, true);		
		SettingManager.setValue(val, locale);
		settings.save(val);
		
		Locale local = new Locale(locale);
		AuthenticationManager.getDefault().getCurrentPrincipal().setLocale(local);
		FacesUtils.setViewLocale(local);
		
		generateSelectItems();
	}
	
	/**
	 * Rellena los combos de Idioma, Tema y Modo
	 */
	private void generateSelectItems() {
		themeItems[0] = new SelectItem(AbstractScheduleRenderer.EVOLUTION_THEME, FacesUtils.formatMessage("activitys.settings.theme.evolution"));
		themeItems[1] = new SelectItem(AbstractScheduleRenderer.OUTLOOK_THEME, FacesUtils.formatMessage("activitys.settings.theme.outlookxp"));
		themeItems[2] = new SelectItem(AbstractScheduleRenderer.DEFAULT_THEME, FacesUtils.formatMessage("activitys.settings.theme.default"));
		
		modeItems[0] = new SelectItem(ScheduleModel.MONTH, FacesUtils.formatMessage("activitys.settings.mode.month"));
		modeItems[1] = new SelectItem(ScheduleModel.WEEK , FacesUtils.formatMessage("activitys.settings.mode.week"));
		modeItems[2] = new SelectItem(ScheduleModel.WORKWEEK, FacesUtils.formatMessage("activitys.settings.mode.workweek"));
		modeItems[3] = new SelectItem(ScheduleModel.DAY, FacesUtils.formatMessage("activitys.settings.mode.day"));
		
		localeItems[0] = new SelectItem("es", FacesUtils.formatMessage("activitys.settings.language.spanish"));
		localeItems[1] = new SelectItem("pt", FacesUtils.formatMessage("activitys.settings.language.portuguese"));
        localeItems[2] = new SelectItem("en", FacesUtils.formatMessage("activitys.settings.language.english"));
	}
	
}
