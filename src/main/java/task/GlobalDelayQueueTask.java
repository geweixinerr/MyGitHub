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
	
	private static final DelayQueue<BaseTaskBeanDelayed> DELAY_QUEUE = new DelayQueue<BaseTaskBeanDelayed>();

	private static final ScheduledExecutorFactoryBean SCHEDULED_FACTORY = new ScheduledExecutorFactoryBean();

	static {
		ScheduledExecutorTask task = new ScheduledExecutorTask();
		task.setDelay(1000 * 60);
		task.setFixedRate(false);
		task.setPeriod(1000 * 15);
		task.setRunnable(new Runnable() {
			@Override
			public void run() {
				BaseTaskBeanDelayed taskBean = null;
				do {
					taskBean = DELAY_QUEUE.poll();
					if (taskBean != null) {
						THREAD_POOL.execute(taskBean);
					}
				} while (taskBean != null);				
			}
		});

		SCHEDULED_FACTORY.setScheduledExecutorTasks(task);
		SCHEDULED_FACTORY.setContinueScheduledExecutionAfterException(true); // 调度遇到异常后,调度计划继续执行.
		SCHEDULED_FACTORY.setThreadNamePrefix("SHOP_TASK_DELAY");
		//factory.setPoolSize(CORE_SIZE);
		SCHEDULED_FACTORY.initialize();
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
	public void compareAndSet(BaseTaskBeanDelayed taskBean) {
		if (DELAY_QUEUE.contains(taskBean)) {
			DELAY_QUEUE.remove(taskBean);
			DELAY_QUEUE.add(taskBean);
		} else {
			DELAY_QUEUE.add(taskBean);
		}
	}
}
