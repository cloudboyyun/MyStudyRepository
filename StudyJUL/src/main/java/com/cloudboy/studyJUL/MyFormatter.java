package com.cloudboy.studyJUL;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[").append(record.getLevel()).append("]")
				.append(record.getSourceClassName()).append("#")
				.append(record.getSourceMethodName()).append(" ")
				.append(record.getThreadID())
				.append(" ")
				.append(record.getMessage());

		return buffer.toString();
	}

}
