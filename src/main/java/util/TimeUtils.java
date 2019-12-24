package util;

import java.util.concurrent.TimeUnit;

public class TimeUtils {

	public TimeUtils() {

	}

	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		long t2 = TimeUnit.MICROSECONDS.convert(t1, TimeUnit.MILLISECONDS);
		System.out.println("时间t1: " + t1);
		System.out.println("时间t2: " + t2);
		System.out.println("时间t3: " + t2);
	}
}
