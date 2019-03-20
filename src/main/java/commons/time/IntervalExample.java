package commons.time;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Seconds;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class IntervalExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		example_1();
		example_2();
	}

	/**
	 * 日期相差
	 */
	private static void example_1() {

		DateTime start = new DateTime(2011, 01, 21, 18, 40, 20, 500);
		DateTime end = new DateTime(2012, 12, 21, 15, 30, 45, 100);

		// Years
		Years years = Years.yearsBetween(start, end);
		System.out.println("years = " + years.getYears());

		// Months
		Months months = Months.monthsBetween(start, end);
		System.out.println("months = " + months.getMonths());

		// Weeks
		Weeks weeks = Weeks.weeksBetween(start, end);
		System.out.println("weeks = " + weeks.getWeeks());

		// Days
		Days days = Days.daysBetween(start, end);
		System.out.println("days = " + days.getDays());

		// Hours
		Hours hours = Hours.hoursBetween(start, end);
		System.out.println("hours = " + hours.getHours());

		// Minutes
		Minutes minutes = Minutes.minutesBetween(start, end);
		System.out.println("minutes = " + minutes.getMinutes());

		// Seconds
		Seconds seconds = Seconds.secondsBetween(start, end);
		System.out.println("seconds = " + seconds.getSeconds());
	}

	/**
	 * 多属性相差
	 * 
	 */
	private static void example_2() {

		// 相差时间输出字符串：1年1月1小时1秒
		DateTime start = new DateTime(2011, 11, 25, 21, 59, 58);
		DateTime end = new DateTime(2012, 12, 25, 22, 59, 59);

		// 设置开始时间为：2012-12-25 21:59:58 ===> 1小时1秒
		//start = start.withYear(2012).withMonthOfYear(12);

		DurationFieldType[] types = { DurationFieldType.years(),
				DurationFieldType.months(), DurationFieldType.days(),
				DurationFieldType.hours(), DurationFieldType.minutes(),
				DurationFieldType.seconds() };

		PeriodType periodType = PeriodType.forFields(types);

		Period period = new Interval(start, end).toPeriod(periodType);

		PeriodFormatter formatter = new PeriodFormatterBuilder().appendYears()
				.appendSeparatorIfFieldsBefore("年").appendMonths()
				.appendSeparatorIfFieldsBefore("月").appendDays()
				.appendSeparatorIfFieldsBefore("日").appendHours()
				.appendSeparatorIfFieldsBefore("小时").appendMinutes()
				.appendSeparatorIfFieldsBefore("分").appendSeconds()
				.appendSeparatorIfFieldsBefore("秒").toFormatter();

		String string = period.toString(formatter);
		System.out.println(string);
	}
}
