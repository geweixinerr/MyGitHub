package scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author gewx 调度任务
 **/
public final class ScheduledExecutorServiceMain {

	private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors();

	private static final ScheduledExecutorService SCHEDULED = Executors.newScheduledThreadPool(CORE_SIZE,
			Executors.defaultThreadFactory());

	public static void main(String[] args) {
		whileTask();
	}

	/**
	 * @author gewx 简单定时任务/单次
	 **/
	public static void simpleTask() {
		SCHEDULED.schedule(() -> System.out.println("Hello World~"), 10, TimeUnit.SECONDS);
	}

	/**
	 * @author gewx 循环定时任务
	 **/
	public static void whileTask() {
		SCHEDULED.scheduleWithFixedDelay(() -> System.out.println("Hello World~"), 10, 10, TimeUnit.SECONDS);
	}
}
