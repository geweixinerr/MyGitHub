package commons.time;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class FormatExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		example_1();
	}

	/**
	 * parse and format
	 */
	private static void example_1() {

		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

		DateTime dateTime = DateTime.parse("2012-12-21 23:22:45", format);

		String string_u = dateTime.toString("yyyy/MM/dd HH:mm:ss EE");
		System.out.println(string_u);

		String string_c = dateTime.toString("yyyy年MM月dd日 HH:mm:ss EE", Locale.CHINESE);
		System.out.println(string_c);
	}
}