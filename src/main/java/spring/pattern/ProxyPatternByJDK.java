package spring.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import spring.intf.TransCashIntf;

/**
 * @author gewx 动态反射代理模式,基于JDK
 * **/
public class ProxyPatternByJDK {

	public ProxyPatternByJDK() {

	}

	/**
	 * @author gewx 动态生成对象
	 * **/
	public static TransCashIntf getInstance(TransCashIntf intf) {
		TransCashIntf instance = (TransCashIntf) Proxy.newProxyInstance(ProxyPatternByJDK.class.getClassLoader(), new Class[]{ TransCashIntf.class }, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("JDK动态代理start...,methodName:" + method.getName());
				Object obj = method.invoke(intf, args);
				System.out.println("JDK动态代理end...");
				return obj;
			}
		});
		
		return instance;
	}
}
