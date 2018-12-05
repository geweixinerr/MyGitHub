package com;

import java.math.BigDecimal;

import org.junit.Test;

import proxy.ProxyByJDK;
import spring.intf.TransCashIntf;


/**
 * @author gewx 动态代理测试类
 **/
public class ProxyByJDKTest {

	public ProxyByJDKTest() {

	}

	/**
         * 测试用例	
	 **/
	@Test
	public void testProxy() {
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

		//执行测试用例
		TransCashIntf proxyImpl = ProxyByJDK.getInstance(proxyTest);
		proxyImpl.moveMoney(null);
	}
	
}
