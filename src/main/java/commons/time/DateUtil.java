package commons.time;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Days;
import org.joda.time.Duration;
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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * 时间日期操作相关类
 */
public class DateUtil {

	/**
	 * 時間格式<br>
	 * <code>HH:mm:ss</code>
	 */
	public static final String TIME_FORMAT = "HH:mm:ss";

	/**
	 * 完整时间 <br>
	 * <code>yyyy-MM-dd HH:mm:ss</code>
	 */
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 完整时间(沒有秒)<br>
	 * <code>yyyy-MM-dd HH:mm</code>
	 */
	public static final String SHORT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

	/**
	 * 完整时间帶毫秒(无中划线) <br>
	 * <code>yyyyMMddHHmmssS</code>
	 */
	public static final String LONG_DATE_TIME_FORMAT_NO_SEPARATOR = "yyyyMMddHHmmssS";

	/**
	 * 年月日时分秒(无中划线)<br>
	 * <code>yyyyMMddHHmmss</code>
	 */
	public static final String DATE_TIME_FORMAT_NO_SEPARATOR = "yyyyMMddHHmmss";

	/**
	 * 年月日 <br>
	 * <code>yyyy-MM-dd</code>
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 年月日时分秒(无中划线)<br>
	 * <code>yyyyMMdd</code>
	 */
	public static final String DATE_FORMAT_NO_SEPARATOR = "yyyyMMdd";

	/**
	 * 中國年月日<br>
	 * <code>yyyy年MM月dd日</code>
	 */
	public static final String CHINESE_DATE_FORMAT = "yyyy年MM月dd日";

	/**
	 * 获取当前系统毫秒数
	 * 
	 * @return 当前系统毫秒数
	 * 
	 */
	public static long getCurrentTimeMillis() {
		return DateTimeUtils.currentTimeMillis();
	}

	/**
	 * 获取当前系统时间
	 * <p>
	 * ISO8601标准格式,eg: <code> Mon Dec 17 22:31:17 CST 2012</code>
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		DateTime dateTime = new DateTime();
		return dateTime.toDate();
	}

	/**
	 * 获取年份
	 * 
	 * @param date
	 *            日期
	 * 
	 * @return 年份数
	 */
	public static int getYear(Date date) {
		return new DateTime(date).getYear();
	}

	/**
	 * 获取月份
	 * 
	 * @param date
	 *            日期
	 * 
	 * @return 月份数
	 */
	public static int getMonth(Date date) {
		return new DateTime(date).getMonthOfYear();
	}

	/**
	 * 获取月份中的某天 <br>
	 * eg: 2012-12-17 -> 17
	 * 
	 * @param date
	 *            日期
	 * 
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		return new DateTime(date).getDayOfMonth();
	}

	/**
	 * 获取一年中的某天
	 * 
	 * <br>
	 * eg: 2012-12-17 -> 352
	 * 
	 * @param date
	 *            日期
	 * 
	 * @return
	 */
	public static int getDayOfYear(Date date) {
		return new DateTime(date).getDayOfYear();
	}

	/**
	 * 获取星期几
	 * <p>
	 * 1->星期一、2->星期二、3->星期三......
	 * 
	 * @param date
	 *            日期
	 * 
	 * @return 星期几
	 */
	public static int getDayOfWeek(Date date) {
		return new DateTime(date).getDayOfWeek();
	}

	/**
	 * 格式化時間
	 * 
	 * @param date
	 *            需要格式化的時間
	 * 
	 * @param pattern
	 *            時間格式
	 * 
	 * @return 格式化後的字符串plusWeeks
	 * 
	 */
	public static String formateDate(Date date, String pattern) {
		return new DateTime(date).toString(pattern);
	}

	/**
	 * 解析時間字符串
	 * 
	 * @param dateStr
	 *            時間字符串
	 * @param pattern
	 *            相應的格式
	 * 
	 * @return 解析後的Date
	 * 
	 * @throws UnsupportedOperationException
	 *             if parsing is not supported
	 * @throws IllegalArgumentException
	 *             if the pattern to parse is invalid
	 */
	public static Date parseDate(String dateStr, String pattern) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat
				.forPattern(pattern);
		return DateTime.parse(dateStr, dateTimeFormatter).toDate();
	}

	/**
	 * 增加天数
	 * 
	 * @param date
	 *            日期
	 * @param days
	 *            天数
	 * 
	 * @return 计算之后的日期
	 */
	public static Date plusDays(Date date, int days) {
		return new DateTime(date).plusDays(days).toDate();
	}

	/**
	 * 增加星期数
	 * 
	 * @param date
	 *            日期
	 * @param weeks
	 *            星期数
	 * 
	 * @return 计算之后的日期
	 */
	public static Date plusWeeks(Date date, int weeks) {
		return new DateTime(date).plusWeeks(weeks).toDate();
	}

	/**
	 * 增加月数
	 * 
	 * @param date
	 *            日期
	 * @param months
	 *            月数
	 * 
	 * @return 计算之后的日期
	 */
	public static Date plusMonths(Date date, int months) {
		return new DateTime(date).plusMonths(months).toDate();
	}

	/**
	 * 增加年数
	 * 
	 * @param date
	 *            日期
	 * @param years
	 *            年数
	 * 
	 * @return 计算之后的日期
	 */
	public static Date plusYears(Date date, int years) {
		return new DateTime(date).plusYears(years).toDate();
	}

	/**
	 * 增加小时
	 * 
	 * @param date
	 *            日期
	 * @param hours
	 *            小时
	 * 
	 * 
	 * @return 计算之后的日期
	 */
	public static Date plusHours(Date date, int hours) {
		return new DateTime(date).plusHours(hours).toDate();
	}

	/**
	 * 增加分钟
	 * 
	 * @param date
	 *            日期
	 * @param years
	 *            分钟
	 * 
	 * @return 计算之后的日期
	 */
	public static Date plusMinutes(Date date, int minutes) {
		return new DateTime(date).plusMinutes(minutes).toDate();
	}

	/**
	 * 增加秒数
	 * 
	 * @param date
	 *            日期
	 * @param years
	 *            年数
	 * 
	 * @return 计算之后的日期
	 */
	public static Date plusSeconds(Date date, int seconds) {
		return new DateTime(date).plusSeconds(seconds).toDate();
	}

	/**
	 * 增加毫秒数
	 * 
	 * @param date
	 *            日期
	 * @param years
	 *            毫秒
	 * 
	 * @return 计算之后的日期
	 */
	public static Date plusMilliseconds(Date date, int millis) {
		return new DateTime(date).plusMillis(millis).toDate();
	}

	/**
	 * 减少天数
	 * 
	 * @param date
	 *            日期
	 * @param days
	 *            天数
	 * 
	 * @return 计算之后的日期
	 */
	public static Date minusDays(Date date, int days) {
		return new DateTime(date).minusDays(days).toDate();
	}

	/**
	 * 减少星期数
	 * 
	 * @param date
	 *            日期
	 * @param weeks
	 *            星期数
	 * 
	 * @return 计算之后的日期
	 */
	public static Date minusWeeks(Date date, int weeks) {
		return new DateTime(date).minusWeeks(weeks).toDate();
	}

	/**
	 * 减少月数
	 * 
	 * @param date
	 *            日期
	 * @param months
	 *            月数
	 * 
	 * @return 计算之后的日期
	 */
	public static Date minusMonths(Date date, int months) {
		return new DateTime(date).minusMonths(months).toDate();
	}

	/**
	 * 减少年数
	 * 
	 * @param date
	 *            日期
	 * @param years
	 *            年数
	 * 
	 * @return 计算之后的日期
	 */
	public static Date minusYears(Date date, int years) {
		return new DateTime(date).minusYears(years).toDate();
	}

	/**
	 * 减少小时
	 * 
	 * @param date
	 *            日期
	 * @param hours
	 *            小时
	 * 
	 * 
	 * @return 计算之后的日期
	 */
	public static Date minusHours(Date date, int hours) {
		return new DateTime(date).minusHours(hours).toDate();
	}

	/**
	 * 减少分钟
	 * 
	 * @param date
	 *            日期
	 * @param years
	 *            分钟
	 * 
	 * @return 计算之后的日期
	 */
	public static Date minusMinutes(Date date, int minutes) {
		return new DateTime(date).minusMinutes(minutes).toDate();
	}

	/**
	 * 减少秒数
	 * 
	 * @param date
	 *            日期
	 * @param years
	 *            年数
	 * 
	 * @return 计算之后的日期
	 */
	public static Date minusSeconds(Date date, int seconds) {
		return new DateTime(date).minusSeconds(seconds).toDate();
	}

	/**
	 * 减少毫秒数
	 * 
	 * @param date
	 *            日期
	 * @param years
	 *            毫秒
	 * 
	 * @return 计算之后的日期
	 */
	public static Date minusMilliseconds(Date date, int millis) {
		return new DateTime(date).minusMillis(millis).toDate();
	}

	/**
	 * 比较两个日期大小
	 * 
	 * @param date1
	 * @param date2
	 * 
	 * @return date1小于date2，返回负数<br>
	 *         date1等于date2，返回0<br>
	 *         date1大于date2，返回正数<br>
	 */
	public static int compare(Date date1, Date date2) {
		DateTime dateTime1 = new DateTime(date1);
		DateTime dateTime2 = new DateTime(date2);
		return dateTime1.compareTo(dateTime2);
	}

	/**
	 * 获取两日期相差年数
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return 相差年数
	 */
	public static int diffYears(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Years.yearsBetween(dateTime1, dateTime2).getYears();
	}

	/**
	 * 获取两日期相差月数
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return 相差月数
	 */
	public static int diffMonths(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Months.monthsBetween(dateTime1, dateTime2).getMonths();
	}

	/**
	 * 获取两日期相差天数
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return 相差天数
	 */
	public static int diffDays(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Days.daysBetween(dateTime1, dateTime2).getDays();
	}

	/**
	 * 获取两日期相差星期数
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return 相差星期数
	 */
	public static int diffWeeks(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
	}

	/**
	 * 获取两日期相差毫秒数
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return 相差毫秒数
	 */
	public static long diffMilliSeconds(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return new Duration(dateTime1, dateTime2).getMillis();
	}

	/**
	 * 获取两日期相差小时数
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return 相差小时数
	 */
	public static int diffHours(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Hours.hoursBetween(dateTime1, dateTime2).getHours();
	}

	/**
	 * 获取两日期相差分钟数
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return 相差分钟数
	 */
	public static int diffMinutes(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Minutes.minutesBetween(dateTime1, dateTime2).getMinutes();
	}

	/**
	 * 获取两日期相差秒数
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return 相差秒数
	 */
	public static int diffSeconds(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Seconds.secondsBetween(dateTime1, dateTime2).getSeconds();
	}

	/**
	 * 获取两个日期相差的年月日时分秒字符串
	 * <p>
	 * eg1:<br>
	 * start="2011-11-25 21:59:58"<br>
	 * end="2012-12-25 22:59:59"<br>
	 * diffString(start, end) ==> 1年1月1小时1秒<br>
	 * <p>
	 * eg2:<br>
	 * start="2012-11-25 21:59:58"<br>
	 * end="2012-11-25 22:59:59"<br>
	 * diffString(start, end) ==> 1小时1秒<br>
	 * <p>
	 * <b>注意：</b> start一定要大于或等于end,否则会抛异常
	 * <p>
	 * 
	 * @param start
	 * @param end
	 * @return 两个日期之间相差时间字符串
	 * @throws IllegalArgumentException
	 *             if the end is before the start
	 */
	public static String diffString(Date start, Date end) {

		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		DurationFieldType[] types = { DurationFieldType.years(),
				DurationFieldType.months(), DurationFieldType.days(),
				DurationFieldType.hours(), DurationFieldType.minutes(),
				DurationFieldType.seconds() };
		PeriodType periodType = PeriodType.forFields(types);
		Period period = new Interval(dateTime1, dateTime2).toPeriod(periodType);
		PeriodFormatter formatter = new PeriodFormatterBuilder().appendYears()
				.appendSeparatorIfFieldsBefore("年").appendMonths()
				.appendSeparatorIfFieldsBefore("月").appendDays()
				.appendSeparatorIfFieldsBefore("日").appendHours()
				.appendSeparatorIfFieldsBefore("小时").appendMinutes()
				.appendSeparatorIfFieldsBefore("分").appendSeconds()
				.appendSeparatorIfFieldsBefore("秒").toFormatter();
		return period.toString(formatter);
	}
}
