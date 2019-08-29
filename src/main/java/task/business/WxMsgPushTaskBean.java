package task.business;


import lombok.extern.slf4j.Slf4j;
import task.TaskBean;

/**
 * @author gewx 店铺微信图文消息推送Bean
 **/
@Slf4j
public class WxMsgPushTaskBean extends TaskBean {

	@Override
	public void run() {
		log.info("异步线程池任务执行中===============" + this.getTaskName() + "===============");
		this.getTask().run();
	}

}
