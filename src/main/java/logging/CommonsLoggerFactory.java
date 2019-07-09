package logging;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.LogFactory;

/**
 * apache commons logger
 * 
 * @author winflex
 */
public class CommonsLoggerFactory extends LoggerFactory {

	Map<String, Logger> loggerMap = new HashMap<String, Logger>();

	@Override
	public Logger newInstance(String name) {
		return new CommonsLogger(LogFactory.getLog(name), name);
	}
}
