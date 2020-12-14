/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * ActivityReportBean.java
 */

package com.autentia.tnt.bean.reports;

import com.autentia.tnt.manager.report.ReportManager;
import com.autentia.tnt.util.ConfigurationUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.context.FacesContext;
import java.util.Calendar;
import java.util.Date;

public class ActivityReportBean extends ReportBean {

    private static final Log log = LogFactory.getLog(ActivityReportBean.class);
    private static final String ACTIVITY_IMAGES_PATH = "/doc/activity/images/";
    private static final String EXTENSION = ".jpg";

    @Override
    protected void setListReports() {
        listReports = ReportManager.getReportManager().getReportListActivity();
    }

    public static String getActivityImageUrl(Integer id, Date date) {
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        StringBuilder path = new StringBuilder(ConfigurationUtil.getDefault().getDomain());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        path.append(contextPath).append(ACTIVITY_IMAGES_PATH)
                .append(year).append("/").append(month).append("/").append(id).append(EXTENSION);

        return path.toString();
    }

}
