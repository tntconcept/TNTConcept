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

import java.text.SimpleDateFormat;

public class ActivityReportBean extends ReportBean {

    private static final Log log = LogFactory.getLog(ActivityReportBean.class);
    private static final String ATTACHMENTS_PATH = "/doc/attachments";
    private final static SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("dd/MM/yy HH:mm");
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");


    @Override
    protected void setListReports() {
        listReports = ReportManager.getReportManager().getReportListActivity();
    }

    public static String genEvidenceURL(String attachmentId, String path, String mimeType) {
        String extension = mimeType.substring(mimeType.lastIndexOf("/") + 1);
        StringBuilder sbPath = new StringBuilder(ConfigurationUtil.getDefault().getTntconceptUrl());
        sbPath.append(ATTACHMENTS_PATH);
        if(!ATTACHMENTS_PATH.endsWith("/") && !path.startsWith("/")) {
            sbPath.append("/");
        }
        sbPath.append(path);
        if(!path.endsWith("/")) {
            sbPath.append("/");
        }
        return sbPath.append(attachmentId).append(".").append(extension).toString();
    }

    public static String getFormattedDate(java.sql.Timestamp date) {

        if ( (date.getHours() == 0 && date.getMinutes() == 0) || (date.getHours() == 23 && date.getMinutes() == 59) ) {
            return DATE_FORMAT.format(date);
        } else {
            return TIMESTAMP_FORMAT.format(date);
        }
    }

}
