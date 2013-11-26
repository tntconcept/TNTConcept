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

/*
 * BillReportBean.java
 */

package com.autentia.tnt.bean.reports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.manager.report.ReportManager;

public class BillReportBean extends ReportBean{
	
	private static final Log log = LogFactory.getLog(BillReportBean.class);

	
	@Override
	protected void init(){
		
		setMapDataPanels(Arrays.asList
			(
				USERS_ARGUMENTS,
				PROJECTS_ARGUMENTS,
				YEARS_ARGUMENTS,
				ACCOUNTS_ARGUMENTS
			)
		);
	}
	
	@Override protected void setListReports(){
		listReports = ReportManager.getReportManager().getReportListBill();
	}
	
	
	@Override
	public ArrayList<SelectItem> getYears() {
		final ArrayList<SelectItem> reto = new ArrayList<SelectItem>();
		int	  year = Calendar.getInstance().get(Calendar.YEAR);
		
		for (int i = year; i >= 2006; i--) {
			String b = Integer.toString(i);
			
			reto.add(new SelectItem(b, b));
		}
		return reto;
	}
}