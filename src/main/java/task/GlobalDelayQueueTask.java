package task;

import java.util.concurrent.DelayQueue;

import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;

/**
 * @author gewx 全局延迟任务异步处理
 **/
public final class GlobalDelayQueueTask {

	private GlobalDelayQueueTask() {
	}

	//private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors();
	
	private static final GlobalDelayQueueTask INSTANCE = new GlobalDelayQueueTask();

	private static final GlobalThreadPoolTaskExecutor THREAD_POOL = GlobalThreadPoolTaskExecutor.getInstance();
	
	private static final DelayQueue<TaskBeanDelayed> delayQueue = new DelayQueue<TaskBeanDelayed>();

	private static final ScheduledExecutorFactoryBean factory = new ScheduledExecutorFactoryBean();

	static {
		ScheduledExecutorTask task = new ScheduledExecutorTask();
		task.setDelay(1000 * 60);
		task.setFixedRate(false);
		task.setPeriod(1000 * 15);
		task.setRunnable(new Runnable() {
			@Override
			public void run() {
				TaskBeanDelayed taskBean = null;
				do {
					taskBean = delayQueue.poll();
					if (taskBean != null) {
						THREAD_POOL.execute(taskBean);
					}
				} while (taskBean != null);				
			}
		});

		factory.setScheduledExecutorTasks(task);
		factory.setContinueScheduledExecutionAfterException(true); // 调度遇到异常后,调度计划继续执行.
		factory.setThreadNamePrefix("SHOP_TASK_DELAY");
		//factory.setPoolSize(CORE_SIZE);
		factory.initialize();
	}
	
	/**
	 * @author gewx 获取单例对象
	 * **/
	public static GlobalDelayQueueTask getInstance() {
		return INSTANCE;
	}
	
	/**
	 * @author gewx 覆盖任务执行
	 * **/
	public void compareAndSet(TaskBeanDelayed taskBean) {
		if (delayQueue.contains(taskBean)) {
			delayQueue.remove(taskBean);
			delayQueue.add(taskBean);
		} else {
			delayQueue.add(taskBean);
		}
	}
}
