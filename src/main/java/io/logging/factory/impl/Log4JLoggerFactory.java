package io.logging.factory.impl;

import io.logging.Logger;
import io.logging.factory.LoggerFactory;
import io.logging.impl.Log4JLogger;

public class Log4JLoggerFactory extends LoggerFactory {

	@Override
	public Logger newInstance(String name) {
		return new Log4JLogger(org.apache.log4j.Logger.getLogger(name));
	}
}
