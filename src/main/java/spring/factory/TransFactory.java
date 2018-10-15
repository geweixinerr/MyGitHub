package spring.factory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.map.LazyMap;
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
import proxyIntf.BusinessBeforeAndAfterIntf;
import spring.intf.TransCashIntf;

@Service("transFactory")
public class TransFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransFactory.class);

	/**
	 * 1.代理单例对象缓存Map,构造为线程安全的容器
	 * 2.缓存Cglib反射生成的代理类实例,避免每次重新生成新的代理类与实例,减少JVM永久代的堆积压力以及减少JAVA堆的内存开销!
	 * 3.将代理类实例单例化!仅初始化一次!
	 * **/
	@SuppressWarnings("rawtypes")
	private static final Map<Class,TransCashIntf> MAP = new ConcurrentHashMap<Class,TransCashIntf>();
	
	@Autowired
	@Qualifier("impl2")
	private TransCashIntf cash;

	@Autowired
	private BusinessBeforeAndAfterIntf<Object> beforeAndAfter;
	
	/**
	 * @author Administrator 返回实例对象
	 **/
	public TransCashIntf getInstance() {
		LOGGER.debug("启动获取实例!");
		//构造代理懒加载容器,实现代理类单例化!
		@SuppressWarnings("rawtypes")
		LazyMap<Class, TransCashIntf> LAZYMAP = LazyMap.lazyMap(MAP,new Factory<TransCashIntf>() {
			@Override
			public TransCashIntf create() {
				TransCashIntf instance = getProxyInstance(cash);
				return instance;
			}
		});
		
		TransCashIntf instance = LAZYMAP.get(cash.getClass()); 
		return instance;
	}

	// 生成代理实例
	private TransCashIntf getProxyInstance(TransCashIntf targetObject) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(targetObject.getClass());
		enhancer.setCallbacks(new Callback[] { new MethodInterceptor() {
			@Override
			public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				Object result = null;
				try {
					beforeAndAfter.before(args);
					// 目标对象为cglib动态生成的类对象(本种方式,在Spring中会出现生成的代理子类Spring注入失效,具体原因未知.)
					// Object result = proxy.invokeSuper(obj, args);
					// 目标对象为传递进来的TransCashIntf对象(本种方式,完全支持Spring的IOC,DI依赖注入)
					result = proxy.invoke(targetObject, args);
					// 目标对象为传递进来的TransCashIntf对象(最原始的方式...)
					// Object result = method.invoke(object, args);
				} finally {
					beforeAndAfter.after(args);
				}
				return result;
			}
		}, new MethodInterceptor() {
			@Override
			public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				Object result = proxy.invoke(targetObject, args);
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
