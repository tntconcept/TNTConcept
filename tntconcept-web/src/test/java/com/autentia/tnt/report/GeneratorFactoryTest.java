package com.autentia.tnt.report;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GeneratorFactoryTest {
	@Test
	public void getGeneratorByFormatCSVShouldReturnACSVGenerator() {
		TypeGenerator generator = GeneratorFactory.getFactory().getGeneratorByFormat(ReportFormat.CSV);
		assertEquals(CSVGenerator.class, generator.getClass());
	}
	
	@Test
	public void getGeneratorByFormatHTMLShouldReturnAHTMLGenerator() {
		TypeGenerator generator = GeneratorFactory.getFactory().getGeneratorByFormat(ReportFormat.HTML);
		assertEquals(HTMLGenerator.class, generator.getClass());
	}
	
	@Test
	public void getGeneratorByFormatODTShouldReturnAODTGenerator() {
		TypeGenerator generator = GeneratorFactory.getFactory().getGeneratorByFormat(ReportFormat.ODT);
		assertEquals(ODTGenerator.class, generator.getClass());
	}
	
	@Test
	public void getGeneratorByFormatPDFShouldReturnAPDFGenerator() {
		TypeGenerator generator = GeneratorFactory.getFactory().getGeneratorByFormat(ReportFormat.PDF);
		assertEquals(PDFGenerator.class, generator.getClass());
	}
	
	@Test
	public void getGeneratorByFormatRTFShouldReturnARTFGenerator() {
		TypeGenerator generator = GeneratorFactory.getFactory().getGeneratorByFormat(ReportFormat.RTF);
		assertEquals(RTFGenerator.class, generator.getClass());
	}
	
	@Test
	public void getGeneratorByFormatXLSShouldReturnAXLSGenerator() {
		TypeGenerator generator = GeneratorFactory.getFactory().getGeneratorByFormat(ReportFormat.XLS);
		assertEquals(XLSGenerator.class, generator.getClass());
	}
}
