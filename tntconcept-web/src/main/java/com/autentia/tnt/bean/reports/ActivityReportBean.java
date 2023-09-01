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

import com.autentia.tnt.bean.DateTimeBean;
import com.autentia.tnt.manager.report.ReportManager;
import com.autentia.tnt.util.ConfigurationUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActivityReportBean extends ReportBean {

    private static final String ATTACHMENTS_PATH = "/doc/attachments";
    private static final DateTimeFormatter  TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    private static final DateTimeFormatter DATE_FORMAT =  DateTimeFormatter.ofPattern("dd/MM/yy");

    private static final int MINUTES_OF_ONE_WORKING_DAY = 8*60;



    @Override
    protected void setListReports() {
        listReports = ReportManager.getReportManager().getReportListActivity();
    }

    public static String genEvidenceURL(String attachmentId, String path, String mimeType) {
        String extension = mimeType.substring(mimeType.lastIndexOf("/") + 1);
        StringBuilder sbPath = new StringBuilder(ConfigurationUtil.getDefault().getTntconceptUrl());
        sbPath.append(ATTACHMENTS_PATH);
        if(!path.startsWith("/")) {
            sbPath.append("/");
        }
        sbPath.append(path);
        if(!path.endsWith("/")) {
            sbPath.append("/");
        }
        return sbPath.append(attachmentId).append(".").append(extension).toString();
    }

    public static String getFormattedDate(java.sql.Timestamp date, int duration) {

        LocalDateTime localDateTime = date.toLocalDateTime();
        if ( duration > MINUTES_OF_ONE_WORKING_DAY) {
            return localDateTime.format(DATE_FORMAT);

        } else {
            return localDateTime.format(TIMESTAMP_FORMAT);
        }
    }

}
