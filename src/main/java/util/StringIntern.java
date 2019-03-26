package util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author gewx String Intern方法使用范例.
 * **/
public final class StringIntern {
	
	private static final int MAX = 1000 * 10000;
	
	private static final String[] arr = new String[MAX];
	 
	public static void main(String[] args) throws Exception {
		TimeUnit.SECONDS.sleep(20); //这里暂停线程,挂载JProfiler分析器
		System.out.println("开始执行----------------->");
	    Integer[] DB_DATA = new Integer[10];
	    Random random = new Random(10 * 10000);
	    for (int i = 0; i < DB_DATA.length; i++) {
	        DB_DATA[i] = random.nextInt();
	    }
	    long t = System.currentTimeMillis();
	    for (int i = 0; i < MAX; i++) {
	        arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length]));
//	         arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length])).intern();
	    }
	 
	    System.out.println((System.currentTimeMillis() - t) + "ms");
	    TimeUnit.SECONDS.sleep(600); //这里暂停线程,挂载JProfiler分析器
	    //System.gc();
	}
}
