package logging;

/**
 * {@link Logger}工厂类, 提供:
 * <ul>
 * <li>创建{@link Logger}实例的便捷方法</li>
 * <li>用{@link LoggerFactory}.setDefaultFactory(new
 * {@link Log4JLoggerFactory}())设置使用的日志实现</li>
 * </ul>
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

	/**
	 * 获取默认的日志实现工厂实现
	 */
	public static LoggerFactory getDefaultFactory() {
		return defaultFactory;
	}

	/**
	 * 设置默认的日志实现工厂实现
	 */
	public static void setDefaultFactory(LoggerFactory defaultFactory) {
		if (defaultFactory == null) {
			throw new NullPointerException("defaultFactory");
		}
		LoggerFactory.defaultFactory = defaultFactory;
	}

	/**
	 * 获取Logger实例
	 */
	public static Logger getInstance(Class<?> clazz) {
		return getInstance(clazz.getName());
	}

	/**
	 * 获取Logger实例
	 */
	public static Logger getInstance(String name) {
		return getDefaultFactory().newInstance(name);
	}

	protected abstract Logger newInstance(String name);
}
