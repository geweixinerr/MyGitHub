package task;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gewx 延迟任务队列
 **/
@Setter
@Getter
public abstract class TaskBeanDelayed implements Delayed, Runnable {

	private String taskName; // 任务名称

	private Runnable task; // 任务

	private long expire; // 延迟时间

	/**
	 * 优先队列里面优先级规则 TimeUnit .MILLISECONDS 获取单位 为毫秒的时间戳
	 **/
	@Override
	public int compareTo(Delayed o) {
		return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
	}

	/**
	 * 剩余时间=到期时间-当前时间 convert: 将给定单元的时间段转换到此单元。
	 **/
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

}
