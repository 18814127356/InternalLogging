# InternalLogging

用于项目内部使用的日志框架, 不强制依赖于第三方日志实现, 但会自动检测classpath下的日志实现, 并按一定的优先级使用

#### 优先级
Slf4J > Log4J > Commons-Logging > Jdk Logging

#### 使用示例
``` java
public class LogTester {
	
	private static final InternalLogger logger = InternalLoggerFactory.getInstance(LogTester.class);
	
	public static void main(String[] args) {
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}
}
```

#### 日志映射级别

| Level | Slf4J |    Log4J    | Commons-logging | Jdk logging |
| ----- | ----- | ----------- | --------------- | ----------- |
| `TRACE` | `TRACE` | `TRACE/DEBUG` | `TRACE`           | `FINEST`      |
| `DEBUG` | `DEBUG` |    `DEBUG`    | `DEBUG`           | `FINE`        |
| `INFO`  | `INFO`  |    `INFO`     | `INFO`            | `INFO`        |
| `WARN`  | `WARN`  |    `WARN`     | `WARN`            | `WARNING`     |
| `ERROR` | `ERROR` |    `ERROR`    | `ERROR`           | `SEVERE`      |

