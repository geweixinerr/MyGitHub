package quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.context.ApplicationContext;

import lombok.Setter;

/**
 * @author gewx 2019.10.18 调度平台基础组件:task
 **/

@Setter
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public final class TaskJob implements Job {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	@SuppressWarnings("unused")
	private JobExecutionContext context;

	@SuppressWarnings("unused")
	private JobModel jobModel;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
	}
}
