package logging;

/**
 * Jdk logger
 * 
 * @author winflex
 */
public class JdkLoggerFactory extends LoggerFactory {

	@Override
	public Logger newInstance(String name) {
		return new JdkLogger(java.util.logging.Logger.getLogger(name));
	}
}
