package util;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * 日期工具类 基于Joda-time
 * 
 * @author gewx
 **/
public final class DateUtils {

	/**
	 * 日期格式校验
	 * 
	 * @author gewx
	 * @param strDate   日期字符串
	 * @param strFormat 格式化字符
	 * @return true-验证通过, false-验证失败
	 **/
	public static boolean validDate(String strDate, String strFormat) {
		try {
			DateTimeFormatter format = DateTimeFormat.forPattern(strFormat);
			format.parseDateTime(strDate);
			return true;
		} catch (IllegalArgumentException ex) {
			return false;
		}
	}

	/**
	 * 日期解析
	 * 
	 * @author gewx
	 * @param strDate   日期字符串
	 * @param strFormat 格式化字符
	 * @return 日期对象
	 **/
	public static Date parseDate(String strDate, String strFormat) {
		DateTimeFormatter format = DateTimeFormat.forPattern(strFormat);
		return format.parseDateTime(strDate).toDate();
	}

	/**
	 * 日期格式化
	 *
	 * @param date    要格式化的日期字段
	 * @param hour    时
	 * @param minutes 分
	 * @param seconds 秒
	 * @return 格式化后的日期
	 */
	public static Date formatDate(Date date, int hour, int minutes, int seconds) {
		return new DateTime(date.getTime()).withHourOfDay(hour).withMinuteOfHour(minutes).withSecondOfMinute(seconds)
				.toDate();
	}

	/**
	 * 计算日期差,毫秒
	 * 
	 * @author gewx
	 * @param start 开始时间
	 * @param end   结束时间
	 * @return 日期差值
	 **/
	public static long timeDiffMillis(DateTime start, DateTime end) {
		return end.getMillis() - start.getMillis();
	}

	/**
	 * 计算日期差,秒
	 * 
	 * @author gewx
	 * @param start 开始时间
	 * @param end   结束时间
	 * @return 日期差值
	 **/
	public static int timeDiffSeconds(DateTime start, DateTime end) {
		return Seconds.secondsBetween(start, end).getSeconds();
	}

	/**
	 * 计算日期差,分钟
	 * 
	 * @author gewx
	 * @param start 开始时间
	 * @param end   结束时间
	 * @return 日期差值
	 **/
	public static int timeDiffMinutes(DateTime start, DateTime end) {
		return Minutes.minutesBetween(start, end).getMinutes();
	}

	/**
	 * 计算日期差,小时
	 * 
	 * @author gewx
	 * @param start 开始时间
	 * @param end   结束时间
	 * @return 日期差值
	 **/
	public static int timeDiffHours(DateTime start, DateTime end) {
		return Hours.hoursBetween(start, end).getHours();
	}

	/**
	 * 计算日期差,天
	 * 
	 * @author gewx
	 * @param start 开始时间
	 * @param end   结束时间
	 * @return 日期差值
	 **/
	public static int timeDiffDays(DateTime start, DateTime end) {
		return Days.daysBetween(start, end).getDays();
	}

	/**
	 * 计算日期差,月
	 * 
	 * @author gewx
	 * @param start 开始时间
	 * @param end   结束时间
	 * @return 日期差值
	 **/
	public static int timeDiffMonths(DateTime start, DateTime end) {
		return Months.monthsBetween(start, end).getMonths();
	}

	/**
	 * 计算日期差,年
	 * 
	 * @author gewx
	 * @param start 开始时间
	 * @param end   结束时间
	 * @return 日期差值
	 **/
	public static int timeDiffYears(DateTime start, DateTime end) {
		return Years.yearsBetween(start, end).getYears();
	}

	/**
	 * 计算日期差,年/月/日/时/分/秒
	 * 
	 * @author gewx
	 * @param start 开始时间
	 * @param end   结束时间
	 * @return 日期差值Period对象
	 **/
	public static Period timeDiffPeriod(DateTime start, DateTime end) {
		Interval time = new Interval(start, end);
		return time.toPeriod();
	}
}
