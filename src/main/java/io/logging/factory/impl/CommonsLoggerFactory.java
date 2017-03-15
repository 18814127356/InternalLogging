package io.logging.factory.impl;

import io.logging.InternalLogger;
import io.logging.factory.InternalLoggerFactory;
import io.logging.impl.CommonsLogger;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.LogFactory;

public class CommonsLoggerFactory extends InternalLoggerFactory {

	Map<String, InternalLogger> loggerMap = new HashMap<String, InternalLogger>();

	@Override
	public InternalLogger newInstance(String name) {
		return new CommonsLogger(LogFactory.getLog(name), name);
	}
}
