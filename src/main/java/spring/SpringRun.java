package spring;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.map.LazyMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.factory.TransFactory;
import spring.intf.TransCashIntf;

/**
 * @author gewx ,2018.10.4
 * Spring测试运行入口,基于Spring4.x
 * **/
public class SpringRun {

	public SpringRun() {

	}
	
	@SuppressWarnings({ "resource"})
	public static void main(String[] args) {
		//加载Spring配置文件
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");//classpath:Spring.xml

//		TransFactory object = (TransFactory) context.getBean(TransFactory.class);
		TransFactory object = (TransFactory) context.getBean("transFactory");
		TransCashIntf cashObject = object.getInstance();
		cashObject.moveMoney(null);
		cashObject.calculateMoney(null);
		System.out.println("执行完毕--->success!");
//		Map<String,String> lazyMap = new LazyMap
		
		Map<String,Object> map = Collections.synchronizedMap(new HashMap<String,Object>());
		@SuppressWarnings("unchecked")
		LazyMap<String, Object> lazyMap = LazyMap.lazyMap(map,new Factory() {
			@Override
			public Object create() {
				return null;
			}
		});
	}
}
