package task;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gewx 任务列表
 * **/
@Setter
@Getter
public abstract class BaseTaskBean implements Runnable {
	
	private String taskId; //任务Id
	
	private String taskName;
	
	private Runnable task;
	
}
