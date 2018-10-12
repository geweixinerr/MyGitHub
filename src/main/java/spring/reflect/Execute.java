package spring.reflect;

public class Execute {

	public void before() {
		System.out.println("before method run...");
		throw new java.lang.IllegalArgumentException("参数异常!");
	}
	
	public void after() {
		System.out.println("after method run...");
	}
}
