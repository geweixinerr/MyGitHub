package spring.factory;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import spring.intf.TransCashIntf;

@Service("transFactory")
public class TransFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransFactory.class);

	@Autowired
	@Qualifier("impl2")
	private TransCashIntf cash;

	/**
	 * @author Administrator 返回实例对象
	 **/
	public TransCashIntf getInstance() {
		LOGGER.debug("启动获取实例!");
		TransCashIntf instance = getProxyInstance(cash);
		System.out.println("实例:" + instance.getClass().getName());
		return instance;
	}

	// 生成代理实例
	private TransCashIntf getProxyInstance(TransCashIntf object) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(object.getClass());
		enhancer.setCallbacks(new Callback[] { new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("before method run..." + method.getName());
				// 目标对象为cglib动态生成的类对象(本种方式,在Spring中会出现生成的代理子类Spring注入失效,具体原因未知.)
				// Object result = proxy.invokeSuper(obj, args);
				// 目标对象为传递进来的TransCashIntf对象(本种方式,完全支持Spring的IOC,DI依赖注入)
				Object result = proxy.invoke(object, args);
				// 目标对象为传递进来的TransCashIntf对象(最原始的方式...)
				// Object result = method.invoke(object, args);
				System.out.println("after method run...");
				return result;
			}
		}, new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				Object result = proxy.invokeSuper(object, args);
				return result;
			}
		} });

		// proxy filter
		enhancer.setCallbackFilter(new CallbackFilter() {
			@Override
			public int accept(Method method) {
				if (method.getName().equals("calculateMoney")) {
					return 0;
				} else {
					return 1;
				}
			}
		});

		TransCashIntf instance = (TransCashIntf) enhancer.create();
		return instance;
	}
}
