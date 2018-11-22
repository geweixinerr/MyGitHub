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
 * ʱ�����ڲ��������
 */
public class DateUtil {

	/**
	 * �r�g��ʽ<br>
	 * <code>HH:mm:ss</code>
	 */
	public static final String TIME_FORMAT = "HH:mm:ss";

	/**
	 * ����ʱ�� <br>
	 * <code>yyyy-MM-dd HH:mm:ss</code>
	 */
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * ����ʱ��(�]����)<br>
	 * <code>yyyy-MM-dd HH:mm</code>
	 */
	public static final String SHORT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

	/**
	 * ����ʱ�䎧����(���л���) <br>
	 * <code>yyyyMMddHHmmssS</code>
	 */
	public static final String LONG_DATE_TIME_FORMAT_NO_SEPARATOR = "yyyyMMddHHmmssS";

	/**
	 * ������ʱ����(���л���)<br>
	 * <code>yyyyMMddHHmmss</code>
	 */
	public static final String DATE_TIME_FORMAT_NO_SEPARATOR = "yyyyMMddHHmmss";

	/**
	 * ������ <br>
	 * <code>yyyy-MM-dd</code>
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * ������ʱ����(���л���)<br>
	 * <code>yyyyMMdd</code>
	 */
	public static final String DATE_FORMAT_NO_SEPARATOR = "yyyyMMdd";

	/**
	 * �Ї�������<br>
	 * <code>yyyy��MM��dd��</code>
	 */
	public static final String CHINESE_DATE_FORMAT = "yyyy��MM��dd��";

	/**
	 * ��ȡ��ǰϵͳ������
	 * 
	 * @return ��ǰϵͳ������
	 * 
	 */
	public static long getCurrentTimeMillis() {
		return DateTimeUtils.currentTimeMillis();
	}

	/**
	 * ��ȡ��ǰϵͳʱ��
	 * <p>
	 * ISO8601��׼��ʽ,eg: <code> Mon Dec 17 22:31:17 CST 2012</code>
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		DateTime dateTime = new DateTime();
		return dateTime.toDate();
	}

	/**
	 * ��ȡ���
	 * 
	 * @param date
	 *            ����
	 * 
	 * @return �����
	 */
	public static int getYear(Date date) {
		return new DateTime(date).getYear();
	}

	/**
	 * ��ȡ�·�
	 * 
	 * @param date
	 *            ����
	 * 
	 * @return �·���
	 */
	public static int getMonth(Date date) {
		return new DateTime(date).getMonthOfYear();
	}

	/**
	 * ��ȡ�·��е�ĳ�� <br>
	 * eg: 2012-12-17 -> 17
	 * 
	 * @param date
	 *            ����
	 * 
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		return new DateTime(date).getDayOfMonth();
	}

	/**
	 * ��ȡһ���е�ĳ��
	 * 
	 * <br>
	 * eg: 2012-12-17 -> 352
	 * 
	 * @param date
	 *            ����
	 * 
	 * @return
	 */
	public static int getDayOfYear(Date date) {
		return new DateTime(date).getDayOfYear();
	}

	/**
	 * ��ȡ���ڼ�
	 * <p>
	 * 1->����һ��2->���ڶ���3->������......
	 * 
	 * @param date
	 *            ����
	 * 
	 * @return ���ڼ�
	 */
	public static int getDayOfWeek(Date date) {
		return new DateTime(date).getDayOfWeek();
	}

	/**
	 * ��ʽ���r�g
	 * 
	 * @param date
	 *            ��Ҫ��ʽ���ĕr�g
	 * 
	 * @param pattern
	 *            �r�g��ʽ
	 * 
	 * @return ��ʽ������ַ���plusWeeks
	 * 
	 */
	public static String formateDate(Date date, String pattern) {
		return new DateTime(date).toString(pattern);
	}

	/**
	 * �����r�g�ַ���
	 * 
	 * @param dateStr
	 *            �r�g�ַ���
	 * @param pattern
	 *            �����ĸ�ʽ
	 * 
	 * @return �������Date
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
	 * ��������
	 * 
	 * @param date
	 *            ����
	 * @param days
	 *            ����
	 * 
	 * @return ����֮�������
	 */
	public static Date plusDays(Date date, int days) {
		return new DateTime(date).plusDays(days).toDate();
	}

	/**
	 * ����������
	 * 
	 * @param date
	 *            ����
	 * @param weeks
	 *            ������
	 * 
	 * @return ����֮�������
	 */
	public static Date plusWeeks(Date date, int weeks) {
		return new DateTime(date).plusWeeks(weeks).toDate();
	}

	/**
	 * ��������
	 * 
	 * @param date
	 *            ����
	 * @param months
	 *            ����
	 * 
	 * @return ����֮�������
	 */
	public static Date plusMonths(Date date, int months) {
		return new DateTime(date).plusMonths(months).toDate();
	}

	/**
	 * ��������
	 * 
	 * @param date
	 *            ����
	 * @param years
	 *            ����
	 * 
	 * @return ����֮�������
	 */
	public static Date plusYears(Date date, int years) {
		return new DateTime(date).plusYears(years).toDate();
	}

	/**
	 * ����Сʱ
	 * 
	 * @param date
	 *            ����
	 * @param hours
	 *            Сʱ
	 * 
	 * 
	 * @return ����֮�������
	 */
	public static Date plusHours(Date date, int hours) {
		return new DateTime(date).plusHours(hours).toDate();
	}

	/**
	 * ���ӷ���
	 * 
	 * @param date
	 *            ����
	 * @param years
	 *            ����
	 * 
	 * @return ����֮�������
	 */
	public static Date plusMinutes(Date date, int minutes) {
		return new DateTime(date).plusMinutes(minutes).toDate();
	}

	/**
	 * ��������
	 * 
	 * @param date
	 *            ����
	 * @param years
	 *            ����
	 * 
	 * @return ����֮�������
	 */
	public static Date plusSeconds(Date date, int seconds) {
		return new DateTime(date).plusSeconds(seconds).toDate();
	}

	/**
	 * ���Ӻ�����
	 * 
	 * @param date
	 *            ����
	 * @param years
	 *            ����
	 * 
	 * @return ����֮�������
	 */
	public static Date plusMilliseconds(Date date, int millis) {
		return new DateTime(date).plusMillis(millis).toDate();
	}

	/**
	 * ��������
	 * 
	 * @param date
	 *            ����
	 * @param days
	 *            ����
	 * 
	 * @return ����֮�������
	 */
	public static Date minusDays(Date date, int days) {
		return new DateTime(date).minusDays(days).toDate();
	}

	/**
	 * ����������
	 * 
	 * @param date
	 *            ����
	 * @param weeks
	 *            ������
	 * 
	 * @return ����֮�������
	 */
	public static Date minusWeeks(Date date, int weeks) {
		return new DateTime(date).minusWeeks(weeks).toDate();
	}

	/**
	 * ��������
	 * 
	 * @param date
	 *            ����
	 * @param months
	 *            ����
	 * 
	 * @return ����֮�������
	 */
	public static Date minusMonths(Date date, int months) {
		return new DateTime(date).minusMonths(months).toDate();
	}

	/**
	 * ��������
	 * 
	 * @param date
	 *            ����
	 * @param years
	 *            ����
	 * 
	 * @return ����֮�������
	 */
	public static Date minusYears(Date date, int years) {
		return new DateTime(date).minusYears(years).toDate();
	}

	/**
	 * ����Сʱ
	 * 
	 * @param date
	 *            ����
	 * @param hours
	 *            Сʱ
	 * 
	 * 
	 * @return ����֮�������
	 */
	public static Date minusHours(Date date, int hours) {
		return new DateTime(date).minusHours(hours).toDate();
	}

	/**
	 * ���ٷ���
	 * 
	 * @param date
	 *            ����
	 * @param years
	 *            ����
	 * 
	 * @return ����֮�������
	 */
	public static Date minusMinutes(Date date, int minutes) {
		return new DateTime(date).minusMinutes(minutes).toDate();
	}

	/**
	 * ��������
	 * 
	 * @param date
	 *            ����
	 * @param years
	 *            ����
	 * 
	 * @return ����֮�������
	 */
	public static Date minusSeconds(Date date, int seconds) {
		return new DateTime(date).minusSeconds(seconds).toDate();
	}

	/**
	 * ���ٺ�����
	 * 
	 * @param date
	 *            ����
	 * @param years
	 *            ����
	 * 
	 * @return ����֮�������
	 */
	public static Date minusMilliseconds(Date date, int millis) {
		return new DateTime(date).minusMillis(millis).toDate();
	}

	/**
	 * �Ƚ��������ڴ�С
	 * 
	 * @param date1
	 * @param date2
	 * 
	 * @return date1С��date2�����ظ���<br>
	 *         date1����date2������0<br>
	 *         date1����date2����������<br>
	 */
	public static int compare(Date date1, Date date2) {
		DateTime dateTime1 = new DateTime(date1);
		DateTime dateTime2 = new DateTime(date2);
		return dateTime1.compareTo(dateTime2);
	}

	/**
	 * ��ȡ�������������
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return �������
	 */
	public static int diffYears(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Years.yearsBetween(dateTime1, dateTime2).getYears();
	}

	/**
	 * ��ȡ�������������
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return �������
	 */
	public static int diffMonths(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Months.monthsBetween(dateTime1, dateTime2).getMonths();
	}

	/**
	 * ��ȡ�������������
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return �������
	 */
	public static int diffDays(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Days.daysBetween(dateTime1, dateTime2).getDays();
	}

	/**
	 * ��ȡ���������������
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return ���������
	 */
	public static int diffWeeks(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
	}

	/**
	 * ��ȡ��������������
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return ��������
	 */
	public static long diffMilliSeconds(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return new Duration(dateTime1, dateTime2).getMillis();
	}

	/**
	 * ��ȡ���������Сʱ��
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return ���Сʱ��
	 */
	public static int diffHours(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Hours.hoursBetween(dateTime1, dateTime2).getHours();
	}

	/**
	 * ��ȡ��������������
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return ��������
	 */
	public static int diffMinutes(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Minutes.minutesBetween(dateTime1, dateTime2).getMinutes();
	}

	/**
	 * ��ȡ�������������
	 * 
	 * @param start
	 * @param end
	 * 
	 * @return �������
	 */
	public static int diffSeconds(Date start, Date end) {
		DateTime dateTime1 = new DateTime(start);
		DateTime dateTime2 = new DateTime(end);
		return Seconds.secondsBetween(dateTime1, dateTime2).getSeconds();
	}

	/**
	 * ��ȡ������������������ʱ�����ַ���
	 * <p>
	 * eg1:<br>
	 * start="2011-11-25 21:59:58"<br>
	 * end="2012-12-25 22:59:59"<br>
	 * diffString(start, end) ==> 1��1��1Сʱ1��<br>
	 * <p>
	 * eg2:<br>
	 * start="2012-11-25 21:59:58"<br>
	 * end="2012-11-25 22:59:59"<br>
	 * diffString(start, end) ==> 1Сʱ1��<br>
	 * <p>
	 * <b>ע�⣺</b> startһ��Ҫ���ڻ����end,��������쳣
	 * <p>
	 * 
	 * @param start
	 * @param end
	 * @return ��������֮�����ʱ���ַ���
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
				.appendSeparatorIfFieldsBefore("��").appendMonths()
				.appendSeparatorIfFieldsBefore("��").appendDays()
				.appendSeparatorIfFieldsBefore("��").appendHours()
				.appendSeparatorIfFieldsBefore("Сʱ").appendMinutes()
				.appendSeparatorIfFieldsBefore("��").appendSeconds()
				.appendSeparatorIfFieldsBefore("��").toFormatter();
		return period.toString(formatter);
	}
}
