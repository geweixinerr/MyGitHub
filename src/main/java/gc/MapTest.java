package gc;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < 20000; j++) {
				sb.append("2004年参演电视剧《与青春有关的日子》");
			}
			map.put("key" + i, sb);
			
			/**
			  *  常规方式清理GC
			 * **/
			map.remove("key" + i);
		}
		
		System.out.println("success~");
	}
}
