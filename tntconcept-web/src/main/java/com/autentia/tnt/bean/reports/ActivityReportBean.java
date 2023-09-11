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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActivityReportBean extends ReportBean {

    private static final String ATTACHMENTS_BASE_PATH = "/doc/attachments";
    private static final DateTimeFormatter  TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    private static final DateTimeFormatter DATE_FORMAT =  DateTimeFormatter.ofPattern("dd/MM/yy");

    private static final String TIME_UNIT_MINUTES = "MINUTES";



    @Override
    protected void setListReports() {
        listReports = ReportManager.getReportManager().getReportListActivity();
    }

    public static String generateEvidenceURL (String attachmentPath) {
        String urls = generateEvidencesURL(attachmentPath);
        if(urls.isEmpty()) {
           return "";
        }

        return urls.split(",")[0];
    }

    public static String generateEvidencesURL(String attachmentPath) {
        StringBuilder sbPath = new StringBuilder();

        String urlBase = ConfigurationUtil.getDefault().getTntconceptUrl() + ATTACHMENTS_BASE_PATH;
        if (!attachmentPath.startsWith("/")) {
            urlBase += "/";
        }

        for(String attPath: attachmentPath.split(",") ) {
            if (sbPath.length() > 0) {
                sbPath.append(",");
            }
            sbPath.append(urlBase).append(attPath);
        }

        return sbPath.toString();
    }

    public static String getFormattedDate(java.sql.Timestamp date, String timeUnit) {

        LocalDateTime localDateTime = date.toLocalDateTime();
        if ( timeUnit.equalsIgnoreCase(TIME_UNIT_MINUTES)) {
            return localDateTime.format(TIMESTAMP_FORMAT);
        } else {
            return localDateTime.format(DATE_FORMAT);
        }
    }

}
