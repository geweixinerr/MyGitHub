package quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author gewx 2019.10.19 调度任务管理器
 **/
@Component
public final class QuartzManager {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private Scheduler scheduler;

	/**
	 * @author gewx 调度平台提交任务
	 * @param job 任务模型
	 * @return void
	 **/
	public void submit(JobModel job) throws SchedulerException {
		String taskId = String.valueOf(job.getTaskId());
		JobDetail jobDetail = JobBuilder.newJob(TaskJob.class).withIdentity(taskId, job.getGroupId()).build();
		jobDetail.getJobDataMap().put("jobModel", job);
		jobDetail.getJobDataMap().put("applicationContext", applicationContext);

		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(taskId, job.getGroupId())
				.withSchedule(cronScheduleBuilder).build();

		scheduler.scheduleJob(jobDetail, cronTrigger);
	}

	/**
	 * @author gex 调度平台删除任务
	 * @param job 任务模型
	 * @throws SchedulerException
	 **/
	public void deleteJob(String taskId, String groupId) throws SchedulerException {
		JobKey jobKey = new JobKey(taskId, groupId);
		boolean isJob = scheduler.checkExists(jobKey);
		if (isJob) {
			boolean isDel = scheduler.deleteJob(jobKey);
			if (!isDel) {
				throw new SchedulerException("job删除失败, taskId: " + taskId + ", groupId: " + groupId);
			} 
		}
	}
}