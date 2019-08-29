package task;

import java.util.concurrent.DelayQueue;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import task.business.WxMsgPushTaskBeanDelayed;

public class DelayQueueTest {

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final DateTimeFormatter DATE_FORMAT_INSTANCE = DateTimeFormat.forPattern(DATE_FORMAT);

	public static DelayQueue<TaskBeanDelayed> delayQueue = new DelayQueue<TaskBeanDelayed>();

	public static void main(String[] args) {
		DateTime thisDate = new DateTime();
		System.out.println("当前时间:  " + thisDate.toString(DATE_FORMAT_INSTANCE));
		DateTime delayedDate = thisDate.plusSeconds(10);
		System.out.println("延迟时间:  " + delayedDate.toString(DATE_FORMAT_INSTANCE));

		WxMsgPushTaskBeanDelayed test = new WxMsgPushTaskBeanDelayed();
		test.setTaskName("延迟任务队列~");
		test.setExpire(delayedDate.toDate().getTime());
		test.setTask(() -> {
			System.out.println("延迟任务队列执行[1]~");
		});

		WxMsgPushTaskBeanDelayed test2 = new WxMsgPushTaskBeanDelayed();
		test2.setTaskName("延迟任务队列~");
		test2.setExpire(thisDate.plusSeconds(20).toDate().getTime());
		test2.setTask(() -> {
			System.out.println("延迟任务队列执行[2]~");
		});

		delayQueue.add(test);
		delayQueue.add(test);
		delayQueue.add(test2);

		while (true) {
			TaskBeanDelayed object = delayQueue.poll();
			if (object != null) {
				System.out.println(object.getTaskName());
				System.out.println(new DateTime().toString(DATE_FORMAT_INSTANCE));
			}
		}
	}
}
