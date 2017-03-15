package io.logging.factory.impl;

import io.logging.InternalLogger;
import io.logging.factory.InternalLoggerFactory;
import io.logging.impl.Log4JLogger;

import org.apache.log4j.Logger;

public class Log4JLoggerFactory extends InternalLoggerFactory {

	@Override
	public InternalLogger newInstance(String name) {
		return new Log4JLogger(Logger.getLogger(name));
	}
}
