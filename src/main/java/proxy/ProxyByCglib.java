package proxy;

import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author gewx 动态反射代理模式,基于CGlib
 * **/
public final class ProxyByCglib {

	public ProxyByCglib() {

	}

	/**
	 * @author gewx 生成动态代理类
	 * 
	 * **/
	public static Object getInstance(Object obj) {
		Enhancer e = new Enhancer();
		e.setSuperclass(obj.getClass());
		e.setCallback(new MethodInterceptor() {
			/**
			 * 参数：Object为由CGLib动态生成的代理类实例,
			 *       Method为上文中实体类所调用的被代理的方法引用,
			 *       Object[]为参数值列表，MethodProxy为生成的代理类对方法的代理引用。
             * 返回：从代理实例的方法调用返回的值
			 * **/
			@Override
			public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("代理执行start...,methodName:" + method.getName());
//				Object result = proxy.invokeSuper(proxyObj, args);
				Object result = proxy.invoke(obj, args);
				System.out.println("参数:" + ArrayUtils.toString(args));
				System.out.println("代理执行end...");
				return result;
			}
		});
		
		return e.create();
	}
	
}
