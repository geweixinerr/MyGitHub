package commons.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		example_1();
		example_2();
		example_3();
		example_4();
	}

	/**
	 * 获取系统当前时间
	 */
	private static void example_1() {

		// 默认方式
		DateTime dateTime = new DateTime();

		// 指定日期时间
		dateTime = new DateTime(2012, // year
				12, // month
				21, // day
				0, // hour (midnight is zero)
				0, // minute
				0, // second
				0 // milliseconds
		);
		// 通过毫秒数
		dateTime = new DateTime(System.currentTimeMillis());

		DateTimeZone dateTimeZone = DateTimeZone.forID("America/New_York");

		// 指定时区
		dateTime = new DateTime(dateTimeZone);

		// 輸出默认格式
		System.out.println(dateTime);

		// 输出指定格式
		System.out.println(dateTime.toString("yyyy-MM-dd HH:ss:mm"));
		// 特定格式
		System.out.println(dateTime.toString(DateTimeFormat.fullDateTime()));

		// 输出不同时区的时间
		DateTime dateTime3 = dateTime.toDateTime(dateTimeZone);
		System.out.println(dateTime3.toString("yyyy-MM-dd HH:ss:mm"));

	}

	/**
	 * 与JDK互操作
	 */
	@SuppressWarnings("unused")
	private static void example_2() {

		// 通过jdk时间对象构造
		Date date = new Date();
		DateTime dateTime = new DateTime(date);

		Calendar calendar = Calendar.getInstance();
		dateTime = new DateTime(calendar);

		// Joda-time 各种操作.....

		// 计算完转换成jdk 对象
		Date date2 = dateTime.toDate();
		Calendar calendar2 = dateTime.toCalendar(Locale.CHINA);
	}

	/**
	 * 时间计算 <br>
	 * plusXxx 增加xx属性<br> 
	 * minusXxx 减少xx属性
	 */
	private static void example_3() {
		System.out.println("===================时间计算===================");
		DateTime dateTime = new DateTime();
		// 时间操作
		DateTime diffDate = dateTime.plusDays(1) // 增加天
				.plusYears(1)// 增加年
				.plusMonths(1)// 增加月
				.plusWeeks(1)// 增加星期
				.minusMillis(1)// 减分钟
				.minusHours(1)// 减小时
				.minusSeconds(1);// 减秒数

		System.out.println(diffDate.toString("yyyy-MM-dd HH:ss:mm"));
	}

	/**
	 * 属性操作
	 */
	private static void example_4() {
		System.out.println("===================属性操作===================");
		
		DateTime dateTime = new DateTime();
		
		//获取年
		Property yearOfCentury = dateTime.yearOfEra();
		System.out.println(yearOfCentury.getAsString());
		
		//获取月属性、星期dayOfWeek()、dayOfMonth、 xxOfxx.....
		DateTime.Property month = dateTime.monthOfYear();
		System.out.println("short = " + month.getAsShortText());
		System.out.println("short = " + month.getAsShortText(Locale.ITALIAN));
		
		System.out.println("string = " + month.getAsString());
		
		System.out.println("text = " + month.getAsText());
		System.out.println("text = " + month.getAsText(Locale.GERMAN));
		System.out.println("max = " + month.getMaximumValue());

		dateTime = month.withMaximumValue();
		final DateTimeFormatter pattern = DateTimeFormat.forStyle("M-");
		System.out.println("changedDate = " + dateTime.toString(pattern));
	}
}
