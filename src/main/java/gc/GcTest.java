package gc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * vm options: -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * **/
public class GcTest {

	public static List<byte[]> byteList = new ArrayList<>();
	
	public static void main(String[] args) throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("测试GC....");
		for (int i = 0; i < 10000; i++) {
			byte [] b = new byte[1024 * 1024];
			byteList.add(b);
			/**
			 * 常规方式清理GC
			 * **/
			//byteList.clear(); //GC, 释放内存
			
			/**
			 * 反射方式清理GC
			 * **/
			Method m = byteList.getClass().getMethod("set", int.class,Object.class);
			m.invoke(byteList, i, null);
		}
		System.out.println("循环执行结束...");
	}
}
