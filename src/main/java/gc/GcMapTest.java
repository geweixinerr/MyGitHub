package gc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * vm options: -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * **/
public class GcMapTest {

	public static List<Map<String,Object>> byteList = new ArrayList<>();
	
	public static void main(String[] args) throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("测试GC....");
		for (int i = 0; i < 10000; i++) {
			Map<String,Object> map = new HashMap<>(2000);
			for (int j = 0; j < 1000; j++) {
				map.put("key" + j, "GC是非常痛苦的一件事情!");
			}
			byteList.add(map);
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
