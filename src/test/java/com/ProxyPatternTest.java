package com;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import spring.intf.TransCashIntf;
import spring.pattern.ProxyPattern;
import spring.pattern.ProxyPatternByJDK;

/**
 * @author gewx 动态代理测试类
 * **/
public class ProxyPatternTest {

	public ProxyPatternTest() {
		
	}

	public static void main(String[] args) {
		//CGLIB
		List<String> list = new ArrayList<String>();
		List<String> listProxy = (List<String>) ProxyPattern.getInstance(list);
		//listProxy.add("Java");
		
		//JDK
		TransCashIntf proxyTest = new TransCashIntf() {

			@Override
			public BigDecimal moveMoney(BigDecimal money) {
				System.out.println("代理实现---->moveMoney");
				return null;
			}

			@Override
			public BigDecimal calculateMoney(BigDecimal money) {
				System.out.println("代理实现---->calculateMoney");
				return null;
			}
			
		};
		
		TransCashIntf proxyImpl = ProxyPatternByJDK.getInstance(proxyTest);
		proxyImpl.moveMoney(null);	
	}
}
