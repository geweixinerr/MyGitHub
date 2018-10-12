package spring.reflect;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.map.LazyMap;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author gewx Cglib增强反射测试类
 **/
public class CglibSimple {

	/**
	 * 1.代理单例对象缓存Map,构造为线程安全的容器
	 * 2.缓存Cglib反射生成的代理类实例,避免每次重新生成新的代理类与实例,减少JVM永久代的堆积压力以及减少JAVA堆的内存开销!
	 * 3.将代理类实例单例化!仅初始化一次!
	 * **/
	private static final Map<String,CglibSimple> MAP = new ConcurrentHashMap<String,CglibSimple>();
	
	//构造代理懒加载容器,实现代理类单例化!
	private static final LazyMap<String, CglibSimple> LAZYMAP = LazyMap.lazyMap(MAP,new Factory<CglibSimple>() {
		@Override
		public CglibSimple create() {
			System.out.println("懒加载...");
			return getInstance();
		}
	});
	
	public String tragetMethod(String str) {
		System.out.println("执行目标方法!execute:" + str);
		return "success";
	}

	public static void main(String[] args) {
		CglibSimple simple = LAZYMAP.get(CglibSimple.class.getClass());
		simple.tragetMethod("Java");
	}
	
	//生成代理实例
	public static CglibSimple getInstance() {
		Execute execute = new Execute();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(CglibSimple.class);
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				if (method.getName().equals("tragetMethod")) {
					Object result = null;
					try {
						execute.before();
						result =proxy.invokeSuper(obj, args);
					} finally {
						execute.after();					
					}
					return result;
				} else {
					Object result = proxy.invokeSuper(obj, args);
					return result;
				}
			}
		});

		CglibSimple simple = (CglibSimple) enhancer.create();
		return simple;
	}
}