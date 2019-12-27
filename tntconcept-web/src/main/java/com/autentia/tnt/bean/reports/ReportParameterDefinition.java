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

package com.autentia.tnt.bean.reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.util.FacesUtils;


public class ReportParameterDefinition {


	private static final Log log = LogFactory.getLog(ReportParameterDefinition.class);

	
	private String label;
	private String type;
	private String id;
	private String value;
	private List valueMany = new ArrayList();
	private Date dateValue;
	private List items;
	private List items2;
	private boolean isRol = false;
	
	public boolean getIsRol() {
		return isRol;
	}
	
	public void setIsRol(boolean isRol) {
		this.isRol = isRol;
	}
	
	
	public void setValueMany(List valueMany) {
		this.valueMany = valueMany;
	}

	public List getValueMany() {
		return valueMany;
	}

	public String getValue() {
		//Si es el parametro descripcion del informe, debo cogerla del properties porque viene la key
		if (getLabel().equals("descripcion")){
			return getTraslateValue(value);
		}
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public ReportParameterDefinition() {
		
	}
	
	public ReportParameterDefinition(String id, String type, String label) {
		this.id = id;
		this.type = type;
		this.label = label;
	}
	
	public ReportParameterDefinition(String id, String type, String label, String value) {
		this(id, type, label);
		this.value = value;
	}
	
	public ReportParameterDefinition(String id, String type, String label, Date date) {
		this(id, type, label);
		this.dateValue = date;
	}
	
	public ReportParameterDefinition(String id, String type, String label, Integer value) {
		this(id, type, label);
		this.value = value.toString();
	}
	
	public ReportParameterDefinition(String id, String type, String label, Boolean value) {
		this(id, type, label);
		this.value = value.toString();
	}
	
	public ReportParameterDefinition(String id, String type, String label, List items) {
		this(id, type, label);
		this.items = items;	
	}
	
	public ReportParameterDefinition(String id, String type, String label, List items, List items2) {
		this(id, type, label,items);
		this.items2 = items2;
	}
	
	
	public boolean isTextType() {
		return type.equals("text");
	}
	public boolean isDateType () {
		return type.equals("date") || type.equals("timestamp");
	}
	
	public boolean isYearType () {
		return type.equals("year");
	}
	
	public boolean isSelectManyType () {
		return type.equals("selectMany");
	}

	public boolean isSelectOneType () {
		return type.equals("selectOne");
	}
	
	public boolean isSelectOneSelectManyType () {
		return type.equals("selectOne-selectMany");
	}
	
	public boolean isBooleanCheckboxType () {
		return type.equals("checkbox");
	}
	
	public boolean isInfoType () {
		return type.equals("info");
	}
	
	public boolean isHiddenType () {
		return type.equals("hidden");
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}
	
	public String formatDate() {
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(dateValue);
		
	}
	
	public String formatTimestamp() {
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(dateValue);
		
	}

	public List getItems2() {
		return items2;
	}

	public void setItems2(List items2) {
		this.items2 = items2;
	}
	
	private String getTraslateValue(String value){
		String traslateValue = "";
		try{
			ResourceBundle resource = ResourceBundle.getBundle("com.autentia.tnt.resources.report", FacesUtils.getViewLocale());
			traslateValue = resource.getString(value.trim());
		}catch (MissingResourceException ex){
			log.error(ex.getMessage());
		}
		return traslateValue;
	}
	
}
