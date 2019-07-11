package logging;

/**
 * 
 * @author winflex
 */
public abstract class LoggerFactory {
	private static volatile LoggerFactory defaultFactory;

	static {
		final String name = LoggerFactory.class.getName();
		LoggerFactory f;
		try {
			// 1. check for slf4j
			f = new Slf4JLoggerFactory(true);
			f.newInstance(name).debug("Using SLF4J as the default logging framework");
		} catch (Throwable t1) {
			try {
				// 2. check for lof4j
				f = new Log4JLoggerFactory();
				f.newInstance(name).debug("Using Log4J as the default logging framework");
			} catch (Throwable t2) {
				try {
					// 3. check for commons-logging
					f = new CommonsLoggerFactory();
					f.newInstance(name).debug("Using commons-logging as the default logging framework");
				} catch (Throwable t3) {
					// 4. use jdk-logging
					f = new JdkLoggerFactory();
					f.newInstance(name).debug("Using java.util.logging as the default logging framework");
				}
			}
		}

		defaultFactory = f;
	}

	public static LoggerFactory getDefaultFactory() {
		return defaultFactory;
	}

	public static void setDefaultFactory(LoggerFactory defaultFactory) {
		if (defaultFactory == null) {
			throw new NullPointerException("defaultFactory");
		}
		LoggerFactory.defaultFactory = defaultFactory;
	}

	public static Logger getLogger(Class<?> clazz) {
		return getLogger(clazz.getName());
	}

	public static Logger getLogger(String name) {
		return getDefaultFactory().newInstance(name);
	}

	protected abstract Logger newInstance(String name);
}
