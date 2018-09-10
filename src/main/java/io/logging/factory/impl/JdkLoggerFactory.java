package io.logging.factory.impl;

import io.logging.Logger;
import io.logging.factory.LoggerFactory;
import io.logging.impl.JdkLogger;


public class JdkLoggerFactory extends LoggerFactory {

	@Override
	public Logger newInstance(String name) {
		return new JdkLogger(java.util.logging.Logger.getLogger(name));
	}
}
