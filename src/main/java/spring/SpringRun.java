package spring;

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
		//cashObject.moveMoney(null);
		cashObject.calculateMoney(null);
		System.out.println("执行完毕--->success!");
	}
}
