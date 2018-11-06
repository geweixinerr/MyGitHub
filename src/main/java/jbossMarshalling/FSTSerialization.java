package jbossMarshalling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nustaq.serialization.FSTConfiguration;

/**
 * FST快速序列化框架
 **/
public final class FSTSerialization {

	static FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();

	public static byte[] serialize(Object obj) {
		return configuration.asByteArray(obj);
	}

	public static Object unserialize(byte[] sec) {
		return configuration.asObject(sec);
	}

	public static void main(String[] args) {
		List<String> xyList = new ArrayList<String>();
		xyList.add("Java");
		xyList.add(".NET");
		xyList.add("C++");
		xyList.add("Hello World!");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "葛伟新");
		map.put("address", "江苏镇江大港镇");
		map.put("age", 32);
		map.put("xyList", xyList);

		Student student = new Student();
		student.setAddress("江苏镇江市大港镇");
		student.setAge(31);
		student.setUserName("葛伟新");
		
		byte[] serByteArray = serialize(student);
		Student student_ = (Student) unserialize(serByteArray);
		
        System.out.println("反序列化:" + student_);
	}
}
