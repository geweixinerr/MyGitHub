package task.business;

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

}
