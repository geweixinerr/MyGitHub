package quartz;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.context.ApplicationContext;

import lombok.Setter;
import task.GlobalThreadPoolTaskExecutor;

/**
 * @author gewx 2019.10.18 原子task任务
 **/
@Setter
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public final class TaskJob implements Job {

	private static final GlobalThreadPoolTaskExecutor TASK_POOL = GlobalThreadPoolTaskExecutor.getInstance();

	private ApplicationContext applicationContext;
	
	@SuppressWarnings("unused")
	private JobExecutionContext context;
	
	@SuppressWarnings("unused")
	private JobModel jobModel;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		this.context = context;
	}

	/**
	 * @author gewx 执行本地任务
	 * @param job 任务主体
	 * @return void
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private void executeJob(JobModel job) {
		TASK_POOL.execute(() -> {
			try {
				System.out.println("Hello,执行中...");
				Class classType = Class.forName(job.getServiceContract());
				Object targetBean = applicationContext.getBean(classType);
				MethodUtils.invokeMethod(targetBean, job.getServiceName());
			} catch (Exception ex) {
			}
		});
	}
}
