package task;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * 全局任务异步处理
 * 
 * @author gewx
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
		POOLTASKEXECUTOR.setThreadNamePrefix("DISPATCH_TASK_");
		// discard
		POOLTASKEXECUTOR.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

		POOLTASKEXECUTOR.initialize();
		POOLTASKEXECUTOR.getThreadPoolExecutor().prestartAllCoreThreads();
	}

	private static final GlobalThreadPoolTaskExecutor INSTANCE = new GlobalThreadPoolTaskExecutor();

	/**
	 * 获取单例线程池对象
	 * 
	 * @author gewx
	 * @return 单例对象
	 **/
	public static GlobalThreadPoolTaskExecutor getInstance() {
		return INSTANCE;
	}

	/**
	 * 提交异步任务
	 * 
	 * @author gewx
	 * @param taskBean 任务
	 * @return void
	 **/
	public void execute(BaseTaskBean taskBean) {
		POOLTASKEXECUTOR.execute(taskBean);
	}

	/**
	 * 提交延迟异步任务
	 * 
	 * @author gewx
	 * @param taskBean 任务
	 * @return void
	 **/
	public void execute(BaseTaskBeanDelayed taskBean) {
		POOLTASKEXECUTOR.execute(taskBean);
	}

	/**
	 * 提交异步任务
	 * 
	 * @author gewx
	 * @param runTask 任务
	 * @return void
	 **/
	public void execute(Runnable runTask) {
		POOLTASKEXECUTOR.execute(runTask);
	}

	/**
	 * 提交异步任务
	 * 
	 * @author gewx
	 * @param runTask 任务
	 * @return 返回异步任务执行结果
	 **/
	public Future<?> submit(Callable<?> runTask) {
		Future<?> future = POOLTASKEXECUTOR.submit(runTask);
		return future;
	}

	/**
	 * 提交异步任务
	 * 
	 * @author gewx
	 * @param runTask 任务
	 * @return 返回异步任务执行结果
	 **/
	public ListenableFuture<?> submitListenable(Callable<?> runTask) {
		ListenableFuture<?> futrue = POOLTASKEXECUTOR.submitListenable(runTask);
		return futrue;
	}
}
