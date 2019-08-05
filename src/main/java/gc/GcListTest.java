package gc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 基于反射清理复杂对象的GC内存数据.
 * **/
public class GcListTest {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {		
		SimpleList list = new SimpleList();
		for (int i = 0; i < 10; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < 20000; j++) {
				sb.append("2004年参演电视剧《与青春有关的日子》");
			}
			
			list.setValue(i,sb); //纳入对象数据

			Field _char = sb.getClass().getSuperclass().getDeclaredField("value");
			_char.setAccessible(true);  
			char [] t = new char[0];
			_char.set(sb,t);
			
			Field _count = sb.getClass().getSuperclass().getDeclaredField("count");
			_count.setAccessible(true);
			_count.set(sb, 0);
		}
		
		System.out.println("执行success.");
	}
}
