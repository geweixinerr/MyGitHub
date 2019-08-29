package task;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gewx 任务列表
 * **/
@Setter
@Getter
public abstract class TaskBean implements Runnable {
	
	private String taskName;
	
	private Runnable task;
	
}
