package com;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import proxy.ProxyByCglib;

public class ProxyByGglibTest {

	public ProxyByGglibTest() {

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProxy(){
		List<String> list = (List<String>) ProxyByCglib.getInstance(new ArrayList<String>());
		list.add("geweixin");
	}
}
