package task;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author gewx 全局任务异步处理
 **/
public final class GlobalThreadPoolTaskExecutor {

	private GlobalThreadPoolTaskExecutor() {
	}

	private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors();

	private static final ThreadPoolTaskExecutor POOLTASKEXECUTOR = new ThreadPoolTaskExecutor();

	static {
		// 队列深度.
		POOLTASKEXECUTOR.setQueueCapacity(Integer.MAX_VALUE);
		// 核心线程数.
		POOLTASKEXECUTOR.setCorePoolSize(CORE_SIZE);
		// 最大线程数.
		POOLTASKEXECUTOR.setMaxPoolSize(CORE_SIZE);
		// 线程名前缀.
		POOLTASKEXECUTOR.setThreadNamePrefix("SHOP_TASK_");
		// discard
		POOLTASKEXECUTOR.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
		POOLTASKEXECUTOR.initialize();

		POOLTASKEXECUTOR.getThreadPoolExecutor().prestartAllCoreThreads();
	}

	private static final GlobalThreadPoolTaskExecutor INSTANCE = new GlobalThreadPoolTaskExecutor();

	public static GlobalThreadPoolTaskExecutor getInstance() {
		return INSTANCE;
	}

	public void execute(BaseTaskBean taskBean) {
		POOLTASKEXECUTOR.execute(taskBean);
	}

	public void execute(BaseTaskBeanDelayed taskBean) {
		POOLTASKEXECUTOR.execute(taskBean);
	}

	public void execute(Runnable runTask) {
		POOLTASKEXECUTOR.execute(runTask);
	}

	public Future<?> execute(Callable<?> runTask) {
		Future<?> future = POOLTASKEXECUTOR.submit(runTask);
		return future;
	}
}
