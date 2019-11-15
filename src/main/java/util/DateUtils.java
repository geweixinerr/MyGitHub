package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author gewx 日期工具类
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
	 * @param val 日期, strFormat 格式
	 * @return false 验证失败, true 验证成功
	 **/
	public static boolean validDate(String val, String strFormat) {
		boolean bool = true;
		SimpleDateFormat dateFormat = DATE_INSTANCE.get();
		try {
			dateFormat.applyPattern(strFormat);
			dateFormat.parse(val);
		} catch (ParseException e) {
			bool = false;
		}
		return bool;
	}
}
