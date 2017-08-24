package com.autentia.tnt.report;

import java.io.OutputStream;

import com.autentia.tnt.error.ReportException;

public interface ReportGenerator{
	public void generate(ReportInfo parameters, OutputStream outputStream) throws ReportException;
}
