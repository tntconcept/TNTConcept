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

package com.autentia.tnt.manager.report;

import java.util.List;

import com.autentia.tnt.xml.ParameterReport;

public class ReportData {

	
	/** Report name */
	private String name;
	
	/** Report params */
	private List<ParameterReport> params;
	
	/**
	 * Default constructor
	 */
	public ReportData() {}
	
	/**
	 * @param name
	 * @param params
	 */
	public ReportData(String name, List<ParameterReport> params) {
		super();
		this.name = name;
		this.params = params;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the params
	 */
	public List<ParameterReport> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(List<ParameterReport> params) {
		this.params = params;
	}
	
}
