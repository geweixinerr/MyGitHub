package lambda.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gewx lambda表达式filter
 * **/
public class FilterTest {

	public static void main(String[] args) {
		List<Map<String,Object>> list = new ArrayList<>();
		
		Map<String,Object> map = new HashMap<>();
		map.put("key", "key1");
		
		Map<String,Object> map1 = new HashMap<>();
		map1.put("key", "key2");
		
		list.add(map);
		list.add(map1);
		
		list = list.stream().filter(val -> val.get("key").equals("key1")).collect(Collectors.toList());
		System.out.println("list---> " + list);
	}
}
