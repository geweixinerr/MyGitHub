package proxyImpl;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import spring.impl.TransCashImplTwo;
import spring.intf.TransCashIntf;

/**
 * @author gewx cglib代理测试类
 * **/
public final class ProxyTest {
	
	public static void main(String[] args) {
		TransCashIntf object = new TransCashImplTwo();
		ProxyTest test = new ProxyTest();
		TransCashIntf proxyObject = test.buildProxy(object);
		proxyObject.calculateMoney(null);
	}
	
	/**
	 * @author gewx  生成代理实例
	 * **/
	private TransCashIntf buildProxy(TransCashIntf transferLoan) {
		Enhancer proxy = new Enhancer();
		proxy.setSuperclass(transferLoan.getClass());
		proxy.setCallbacks(new Callback[] {
				new MethodInterceptor() {
					@Override
					public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
						Object result = proxy.invokeSuper(obj, args);
						return result;
					}
				},
				new MethodInterceptor() {
					//default proxy
					@Override
					public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
						return  proxy.invokeSuper(obj, args);
					}
				}
		});

		//proxy filter
		proxy.setCallbackFilter(new CallbackFilter() {
			@Override
			public int accept(Method method) {
                if (method.getName().equals("moveMoney")) {
    				return 0;
                } else {
                	return 1;
                }
			}
		});
		
		return (TransCashIntf) proxy.create();
	}
}
