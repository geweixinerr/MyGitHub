package task.business;

import org.apache.commons.lang3.StringUtils;

import task.TaskBeanDelayed;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gewx 店铺微信图文消息延迟推送Bean
 **/
@Slf4j
public class WxMsgPushTaskBeanDelayed extends TaskBeanDelayed {

	@Override
	public void run() {
		log.info("异步线程池任务执行中[延迟消息]===============" + this.getTaskName() + "===============");
		this.getTask().run();
	}

	@Override
	public boolean equals(Object obj) {
		WxMsgPushTaskBeanDelayed object = (WxMsgPushTaskBeanDelayed) obj;
		String taskId = object.getTaskId();
		String thisTaskId = getTaskId();
		if (StringUtils.isNoneBlank(taskId, thisTaskId)) {
			return thisTaskId.equals(taskId);
		} else {
			return super.equals(obj);
		}
	}

	@Override
	public int hashCode() {
		String taskId = getTaskId();
		if (StringUtils.isNotBlank(taskId)) {
			return taskId.hashCode();
		} else {
			return super.hashCode();
		}
	}
}
