package io.logging.factory;

import io.logging.InternalLogger;
import io.logging.factory.impl.JdkLoggerFactory;
import io.logging.factory.impl.Log4JLoggerFactory;
import io.logging.factory.impl.Slf4JLoggerFactory;

/**
 * {@link InternalLogger}工厂类, 提供:
 * <ul>
 * <li>创建{@link InternalLogger}实例的便捷方法</li>
 * <li>用{@link InternalLoggerFactory}.setDefaultFactory(new
 * {@link Log4JLoggerFactory}())设置使用的日志实现</li>
 * </ul>
 * 
 * @author lixiaohui
 * @date 2017年3月15日
 */
public abstract class InternalLoggerFactory {
	private static volatile InternalLoggerFactory defaultFactory;

	static {
		final String name = InternalLoggerFactory.class.getName();
		InternalLoggerFactory f;
		try {
			// 优先使用Slf4J, 如果classpath下没有引入slf4j的jar包, 那么此处将会抛异常
			f = new Slf4JLoggerFactory(true);
			f.newInstance(name).debug("Using SLF4J as the default logging framework");
		} catch (Throwable t1) {
			try {
				f = new Log4JLoggerFactory();
				f.newInstance(name).debug("Using Log4J as the default logging framework");
			} catch (Throwable t2) {
				f = new JdkLoggerFactory();
				f.newInstance(name).debug("Using java.util.logging as the default logging framework");
			}
		}

		defaultFactory = f;
	}

	/**
	 * 获取默认的日志实现工厂实现
	 */
	public static InternalLoggerFactory getDefaultFactory() {
		return defaultFactory;
	}

	/**
	 * 设置默认的日志实现工厂实现
	 */
	public static void setDefaultFactory(InternalLoggerFactory defaultFactory) {
		if (defaultFactory == null) {
			throw new NullPointerException("defaultFactory");
		}
		InternalLoggerFactory.defaultFactory = defaultFactory;
	}

	/**
	 * 获取Logger实例
	 */
	public static InternalLogger getInstance(Class<?> clazz) {
		return getInstance(clazz.getName());
	}

	/**
	 * 获取Logger实例
	 */
	public static InternalLogger getInstance(String name) {
		return getDefaultFactory().newInstance(name);
	}

	protected abstract InternalLogger newInstance(String name);
}
