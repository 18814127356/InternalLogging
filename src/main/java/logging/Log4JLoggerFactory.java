package logging;

/**
 * Log4j logger
 * 
 * @author winflex
 */
public class Log4JLoggerFactory extends LoggerFactory {

	@Override
	public Logger newInstance(String name) {
		return new Log4JLogger(org.apache.log4j.Logger.getLogger(name));
	}
}
