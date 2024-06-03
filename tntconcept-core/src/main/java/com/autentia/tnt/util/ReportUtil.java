package com.autentia.tnt.util;

public class ReportUtil {

    public static ReportUtil getDefault(){
        return new ReportUtil();
    }

    private String getReportProperty(String prefix, String suffix){
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com.autentia.tnt.resources.report");
        return bundle.getString(prefix + "." + suffix);

    }
    public String getProjectType(String type){
        return getReportProperty("BillingType",type);
    }
}
