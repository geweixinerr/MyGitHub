package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * @author gewx D2C 日期工具类,基于Joda API
 **/
public final class DateUtils {
		
	/**
	 * 初始化线程安全的日期格式对象
	 * **/
	private static final FastThreadLocal<SimpleDateFormat> DATE_INSTANCE = new FastThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() throws Exception {
			SimpleDateFormat format = new SimpleDateFormat();			
			format.setLenient(false);
			return format;
		}
	};
	
	/**
	 * @author gewx 验证日期格式正确性
	 * @param val 日期, format 格式
	 * @return boolean
	 * **/
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
	 * **/
	public static Integer timeDiffForMilliSecond(DateTime date1, DateTime date2, PeriodType type) {
	    Interval interval = new Interval(date1, date2);
	    //Period p2 = interval.toPeriod(); //等同于 Period p2 = new Period(date1, date2);
	    Period pDate = interval.toPeriod(type);
	    return pDate.getSeconds(); //默认返回差值秒
	}
}
