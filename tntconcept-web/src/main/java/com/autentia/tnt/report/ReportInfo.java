package com.autentia.tnt.report;

import java.util.HashMap;
import java.util.Map;

public class ReportInfo {
	Map<String, Object> parameters;
	String name;
	ReportFormat format;
	String category;

	private ReportInfo(Map<String, Object> parameters, String name, ReportFormat format, String category) {
		super();
		this.parameters = parameters;
		this.name = name;
		this.format = format;
		this.category = category;
	}

	public static class ReportInfoBuilder {
		private String name;
		private final Map<String, Object> parameters;
		private String category;
		private ReportFormat format;

		public ReportInfoBuilder() {
			this.parameters = new HashMap<String, Object>();
		}

		public ReportInfoBuilder name(final String name) {
			this.name = name;
			return this;
		}

		public ReportInfoBuilder andParameter(final String name, final Object value) {
			this.parameters.put(name, value);
			return this;
		}

		public ReportInfoBuilder in(final ReportFormat format) {
			this.format = format;
			return this;
		}

		public ReportInfoBuilder withCategory(final String category) {
			this.category = category;
			return this;
		}
		
		
		public ReportInfo build() {
			return new ReportInfo(this.parameters, this.name, this.format, this.category);
		}
		
	}

}
