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
public class GcListMapTest {

	public static List<Map<String,Object>> mapList = new ArrayList<>();
	
	public static void main(String[] args) throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("测试GC....");
		for (int i = 0; i < 10000; i++) {
			Map<String,Object> map = new HashMap<>(2000);
			for (int j = 0; j < 1000; j++) {
				map.put("key" + j, "GC是非常痛苦的一件事情!");
			}
			mapList.add(map);
			/**
			 * 常规方式清理GC
			 * **/
			//mapList.clear(); //GC, 释放内存
			
			/**
			 * 等同于 mapList.clear();
			 * **/
			/*
			mapList.remove(i);
			i--;
			*/
			
			/**
			 * 反射方式清理GC
			 * **/
			Method m = mapList.getClass().getMethod("set", int.class,Object.class);
			m.invoke(mapList, i, null);
			
			/**
			 * 此种方式无效,因为强引用还存在. 必须要斩断GC ROOT强引用才能快速释放.
			 * **/
			/*
			Method _m = mapList.getClass().getMethod("get",int.class);
			Map<String,Object> _map = (Map<String, Object>) _m.invoke(mapList, i);
			_map.clear();
			*/
		}
		System.out.println("循环执行结束...");
	}
}
