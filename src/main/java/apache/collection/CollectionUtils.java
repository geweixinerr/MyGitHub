package apache.collection;

import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @author gewx Apache commons 工具包辅助类
 * **/
public final class CollectionUtils {

	public CollectionUtils() {
	
	}

	public static void main(String[] args) {
		CollectionUtils.executeMultikeyMap();
	}
	
	/**
	 * @author gewx 多key-Map
	 * **/
	public static void executeMultikeyMap() {
		MultiKeyMap<String,String> map = new MultiKeyMap<String,String>();
	    map.put("葛伟新", "江苏镇江", "镇江新区大港镇,在南京工作!");
	    String value = map.get("葛伟新", "江苏镇江");
	    System.out.println("多Key-Map:" + value);	    
	}
}
