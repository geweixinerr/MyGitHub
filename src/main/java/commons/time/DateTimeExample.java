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
	 * ��ȡϵͳ��ǰʱ��
	 */
	private static void example_1() {

		// Ĭ�Ϸ�ʽ
		DateTime dateTime = new DateTime();

		// ָ������ʱ��
		dateTime = new DateTime(2012, // year
				12, // month
				21, // day
				0, // hour (midnight is zero)
				0, // minute
				0, // second
				0 // milliseconds
		);
		// ͨ��������
		dateTime = new DateTime(System.currentTimeMillis());

		DateTimeZone dateTimeZone = DateTimeZone.forID("America/New_York");

		// ָ���r�^
		dateTime = new DateTime(dateTimeZone);

		// ݔ��Ĭ�J��ʽ
		System.out.println(dateTime);

		// ���ָ����ʽ
		System.out.println(dateTime.toString("yyyy-MM-dd HH:ss:mm"));
		// �ض���ʽ
		System.out.println(dateTime.toString(DateTimeFormat.fullDateTime()));

		// �����ͬʱ����ʱ��
		DateTime dateTime3 = dateTime.toDateTime(dateTimeZone);
		System.out.println(dateTime3.toString("yyyy-MM-dd HH:ss:mm"));

	}

	/**
	 * ��JDK������
	 */
	private static void example_2() {

		// ͨ��jdkʱ�������
		Date date = new Date();
		DateTime dateTime = new DateTime(date);

		Calendar calendar = Calendar.getInstance();
		dateTime = new DateTime(calendar);

		// Joda-time ���ֲ���.....

		// ������ת����jdk ����
		Date date2 = dateTime.toDate();
		Calendar calendar2 = dateTime.toCalendar(Locale.CHINA);
	}

	/**
	 * ʱ����� <br>
	 * plusXxx ����xx����<br> 
	 * minusXxx ����xx����
	 */
	private static void example_3() {
		System.out.println("===================ʱ�����===================");
		DateTime dateTime = new DateTime();
		// ʱ�����
		DateTime diffDate = dateTime.plusDays(1) // ������
				.plusYears(1)// ������
				.plusMonths(1)// ������
				.plusWeeks(1)// ��������
				.minusMillis(1)// ������
				.minusHours(1)// ��Сʱ
				.minusSeconds(1);// ������

		System.out.println(diffDate.toString("yyyy-MM-dd HH:ss:mm"));
	}

	/**
	 * ���Բ���
	 */
	private static void example_4() {
		System.out.println("===================���Բ���===================");
		
		DateTime dateTime = new DateTime();
		
		//��ȡ��
		Property yearOfCentury = dateTime.yearOfEra();
		System.out.println(yearOfCentury.getAsString());
		
		//��ȡ�����ԡ�����dayOfWeek()��dayOfMonth�� xxOfxx.....
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
