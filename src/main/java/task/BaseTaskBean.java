package task;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gewx 任务列表
 **/
@Setter
@Getter
public abstract class BaseTaskBean implements Runnable {

	/**
	 * 任务Id
	 **/
	private String taskId;

	/**
	 * 任务名称
	 **/
	private String taskName;

	/**
	 * 任务主体
	 **/
	private Runnable task;

}
