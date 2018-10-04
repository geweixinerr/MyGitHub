package apache.collection;

import java.util.Map;

import org.apache.commons.collections4.map.LRUMap;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @author gewx Apache commons 工具包辅助类
 * **/
public final class CollectionUtils {

	public CollectionUtils() {
	
	}

	public static void main(String[] args) {
		CollectionUtils.executeLRUMap();
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
	
	/**
	 * @author gewx LRU算法Map 
	 * LRUMap，可以用来做缓存，它采用了LRU(least recently used)算法实现，当Map达到最大容量后，会优先删除掉最不经常被用到的项
	 * **/
	public static void executeLRUMap() {
        Map<String,String> map = new LRUMap<String, String>(3); 
        map.put("geweixin_01", "geweixin");
        map.put("geweixin_02", "geweixin");
        map.put("geweixin_03", "geweixin");
        map.get("geweixin_01");
        map.put("geweixin_04", "geweixin");
        System.out.println("LRU-Map队列:" + map);
	}
	
}
