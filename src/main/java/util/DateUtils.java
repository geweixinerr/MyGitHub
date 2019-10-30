package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Seconds;

/**
 * @author gewx 日期工具类,基于Joda API
 **/
public final class DateUtils {

	/**
	 * 初始化线程安全的日期格式对象
	 **/
	@SuppressWarnings("static-access")
	private static final ThreadLocal<SimpleDateFormat> DATE_INSTANCE = new ThreadLocal<SimpleDateFormat>()
			.withInitial(() -> {
				SimpleDateFormat format = new SimpleDateFormat();
				format.setLenient(false);
				return format;
			});

	/**
	 * @author gewx 验证日期格式正确性
	 * @param val 日期, format 格式
	 * @return boolean
	 **/
	public static boolean validDate(String val, String format) {
		boolean bool = true;
		SimpleDateFormat _format = DATE_INSTANCE.get();
		try {
			_format.applyPattern(format);
			_format.parse(val);
		} catch (ParseException e) {
			bool = false;
		}
		return bool;
	}

	/**
	 * @author gewx 计算时间差[同位之间的差异]
	 **/
	public static Integer timeDiffForMilliSecond(DateTime date1, DateTime date2) {
		Period p2 = new Period(date1, date2);
		int seconds = p2.getSeconds(); // 相差的秒
		return seconds * 1000 + p2.getMillis();
	}

	/**
	 * @author gewx 计算时间差[累计单位的时间差值]
	 * @throws ParseException
	 **/
	public static void timeDiffForMilliSecond() throws ParseException {
		Date begin = org.apache.commons.lang3.time.DateUtils.parseDate("2019-09-29 10:00:00", "yyyy-MM-dd HH:mm:ss");
		Date end = org.apache.commons.lang3.time.DateUtils.parseDate("2019-09-29 11:02:01", "yyyy-MM-dd HH:mm:ss");
		Interval interval = new Interval(new DateTime(begin), new DateTime(end));
		Period p = interval.toPeriod(PeriodType.years());
		System.out.println("年: " + p.getYears());

		p = interval.toPeriod(PeriodType.months());
		System.out.println("月: " + p.getHours());

		p = interval.toPeriod(PeriodType.days());
		System.out.println("日: " + p.getDays());

		p = interval.toPeriod(PeriodType.hours());
		System.out.println("时: " + p.getHours());

		p = interval.toPeriod(PeriodType.minutes());
		System.out.println("分: " + p.getMinutes());

		p = interval.toPeriod(PeriodType.seconds());
		System.out.println("秒: " + p.getSeconds());

		Seconds val = Seconds.secondsBetween(new DateTime(begin), new DateTime(end));
		System.out.println("相差的秒: " + val.getSeconds());

		boolean bool = interval.contains(new DateTime(begin));
		System.out.println("是否包含在范围内: " + bool);
	}

	public static void main(String[] args) throws ParseException {
		timeDiffForMilliSecond();
	}
}
