package lambda.group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class GroupMain {

	public static void main(String[] args) {
		GroupData data1 = new GroupData();
		data1.setAddress("江苏镇江_1");
		data1.setCustName("geweixin_1");
		data1.setGroupId("groupId_1");

		GroupData _data1 = new GroupData();
		_data1.setAddress("江苏镇江_11");
		_data1.setCustName("geweixin_11");
		_data1.setGroupId("groupId_1");

		GroupData data2 = new GroupData();
		data2.setAddress("江苏镇江_2");
		data2.setCustName("geweixin_2");
		data2.setGroupId("groupId_2");

		List<GroupData> list = new ArrayList<>();
		list.add(data1);
		list.add(data2);
		list.add(_data1);

		//Guava 单Key 多Value
		Multimap<String, GroupData> myMultimap = ArrayListMultimap.create();  
		list.stream().forEach(val -> {
			myMultimap.put(val.getGroupId(), val);
		});
		
		System.out.println("groupId_1: " + myMultimap.get("groupId_1"));
		
		//stream
		Map<String,List<GroupData>> map = list.stream().collect(Collectors.groupingBy(val -> val.getGroupId()));
		System.out.println(map);
	}
}
