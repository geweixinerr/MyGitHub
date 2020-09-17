package util;

import java.util.concurrent.TimeUnit;

public class TimeUtils {

	public TimeUtils() {
		System.out.println("Hello");
	}

	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		long t2 = TimeUnit.MICROSECONDS.convert(t1, TimeUnit.MILLISECONDS);
		System.out.println("时间t1: " + t1);
		System.out.println("时间t2: " + t2);
		
		for (int i = 0; i < 100000; i++) {
			println();
		}
	}
	
	public static void println() {
		int i = 100 * 100;
	}
}
