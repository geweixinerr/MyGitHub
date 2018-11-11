package logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gewx
 * **/
public final class LoggerTest {

	//日志工厂实例
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerTest.class);
	
	public LoggerTest() {}
	
	public static void main(String[] args) {
		System.out.println("isDebug: " + LOGGER.isDebugEnabled());
		System.out.println("isInfo: " + LOGGER.isInfoEnabled());
		System.out.println("isWarn: " + LOGGER.isWarnEnabled());
		System.out.println("isError: " + LOGGER.isErrorEnabled());
		
		LOGGER.debug("Hello");
		LOGGER.info("Hello");
		LOGGER.warn("Hello");
		LOGGER.error("Hello");
	}
}
