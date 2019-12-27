package com.autentia.tnt.report;

public enum ReportFormat {
	CSV("csv","application/vnd.ms-excel"), PDF("pdf","application/pdf"), HTML("html",""), RTF("rtf","application/rtf"), XLS("xls","application/vnd.ms-excel"), ODT("odt","application/odt");
	
	private String extension;
	private String responseType;
	
	private ReportFormat(String extension, String responseType) {
		this.extension = extension;
		this.responseType = responseType;
	}
	
	public String getExtension() {
		return this.extension;
	}
	
	public String getResponseType() {
		return this.responseType;
	}
}
