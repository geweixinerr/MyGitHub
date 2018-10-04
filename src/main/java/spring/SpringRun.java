package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.factory.TransFactory;
import spring.intf.TransCashIntf;

public class SpringRun {

	public SpringRun() {

	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");

		TransFactory object = (TransFactory) context.getBean(TransFactory.class);
		TransCashIntf cashObject = object.getInstance();
		cashObject.moveMoney(null);
		System.out.println("执行完毕--->success!");
	}
}
