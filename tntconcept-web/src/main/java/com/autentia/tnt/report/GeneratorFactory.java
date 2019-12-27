package com.autentia.tnt.report;

import java.util.HashMap;
import java.util.Map;

public class GeneratorFactory {

	Map<ReportFormat, TypeGenerator> generators;

	private static GeneratorFactory theSingleton;

	private GeneratorFactory() {
		super();
		this.generators = new HashMap<ReportFormat, TypeGenerator>(6);

		this.generators.put(ReportFormat.CSV, new CSVGenerator());
		this.generators.put(ReportFormat.RTF, new RTFGenerator());
		this.generators.put(ReportFormat.HTML, new HTMLGenerator());
		this.generators.put(ReportFormat.XLS, new XLSGenerator());
		this.generators.put(ReportFormat.ODT, new ODTGenerator());
		this.generators.put(ReportFormat.PDF, new PDFGenerator());
	}

	public static GeneratorFactory getFactory() {
		if (theSingleton == null) {
			initSingleton();
		}

		return theSingleton;

	}

	private synchronized static void initSingleton() {
		if (theSingleton == null) {
			theSingleton = new GeneratorFactory();
		}
	}

	public TypeGenerator getGeneratorByFormat(ReportFormat criteria) {
		return this.generators.get(criteria);
	}
}
