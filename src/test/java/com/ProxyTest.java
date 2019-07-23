package com;

import org.junit.Test;

import proxy.ProxyByCglib;

public class ProxyTest {

	//proxy ok
	void Hello_0() {
		System.out.println("Hello_0");
	}
	
	//proxy ok
	public void Hello_1() {
		System.out.println("Hello_1");
	}
	
	//proxy ok
	protected void Hello_2() {
		System.out.println("Hello_2");
	}
	
	//私有方法无法代理
	@SuppressWarnings("unused")
	private void Hello_3() {
		System.out.println("Hello_3");
	}
	
	//final修饰的无法代理
	@SuppressWarnings("unused")
	private final void Hello_4() {
		System.out.println("Hello_4");
	}
	
	@Test
	public void testMain() {
		ProxyTest t = (ProxyTest) ProxyByCglib.getInstance(new ProxyTest());
		t.Hello_0();
	}
}
