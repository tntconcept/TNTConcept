package com.autentia.tnt.report;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.autentia.tnt.report.ReportInfo.ReportInfoBuilder;

public class ReportInfoTest {
	private static final String TEST_REPORT_VALUE = "Value01";
	private static final String TEST_REPORT_KEY = "Title01";
	private static final String TEST_REPORT_NAME = "Test Report";
	private static final String TEST_REPORT_CATEGORY = "Activity";

	@Test
	public void builderNameShouldSetReportName() {
		ReportInfoBuilder builder = new ReportInfoBuilder();
		builder.name(TEST_REPORT_NAME);

		ReportInfo result = builder.build();

		assertEquals(TEST_REPORT_NAME, result.name);
	}

	@Test
	public void builderAndParameterShouldAddAKeyValueElementToParameters() {
		ReportInfoBuilder builder = new ReportInfoBuilder();
		builder.andParameter(TEST_REPORT_KEY, TEST_REPORT_VALUE);

		ReportInfo result = builder.build();

		assertEquals(TEST_REPORT_VALUE, result.parameters.get(TEST_REPORT_KEY));
	}

	@Test
	public void builderInShouldSetReportFormat() {
		ReportInfoBuilder builder = new ReportInfoBuilder();
		builder.in(ReportFormat.PDF);

		ReportInfo result = builder.build();

		assertEquals(ReportFormat.PDF, result.format);
	}

	@Test
	public void builderWithCategoryShouldSetReportCategory() {
		ReportInfoBuilder builder = new ReportInfoBuilder();
		builder.withCategory(TEST_REPORT_CATEGORY);

		ReportInfo result = builder.build();

		assertEquals(TEST_REPORT_CATEGORY, result.category);
	}
}
