package io.logging.factory.impl;

import io.logging.Logger;
import io.logging.factory.LoggerFactory;
import io.logging.impl.CommonsLogger;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.LogFactory;

public class CommonsLoggerFactory extends LoggerFactory {

	Map<String, Logger> loggerMap = new HashMap<String, Logger>();

	@Override
	public Logger newInstance(String name) {
		return new CommonsLogger(LogFactory.getLog(name), name);
	}
}
