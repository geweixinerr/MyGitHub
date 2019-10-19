package util;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;

/**
 * @author gewx 2019.10.19 cron表达式辅助工具类
 **/
public final class CronExpressionUtils {

	/**
	 * @author gewx 获取调度任务下一次触发时间
	 * @param cronExpression cron表达式
	 * @return Date 下一次触发时间
	 **/
	public static Date getNextFireTime(String cronExpression) {		
		CronTriggerImpl trigger = new CronTriggerImpl();
		try {
			trigger.setCronExpression(cronExpression);
			List<Date> listTriggerDate = TriggerUtils.computeFireTimes(trigger, null, 1);
			if (CollectionUtils.isNotEmpty(listTriggerDate)) {
				return listTriggerDate.get(0);
			} else {
				return null;
			}
		} catch (ParseException e) {
			return null;
		}
	}
}
