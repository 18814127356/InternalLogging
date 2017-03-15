package io.logging.factory.impl;

import io.logging.InternalLogger;
import io.logging.factory.InternalLoggerFactory;
import io.logging.impl.JdkLogger;

import java.util.logging.Logger;

public class JdkLoggerFactory extends InternalLoggerFactory {

	@Override
	public InternalLogger newInstance(String name) {
		return new JdkLogger(Logger.getLogger(name));
	}
}
