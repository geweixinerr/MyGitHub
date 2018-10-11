package spring.reflect;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
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

	//代理单例对象缓存Map,构造为线程安全的容器
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
		CglibSimple simple = LAZYMAP.get("Java");
		simple.tragetMethod("Java");
		CglibSimple simple2 = LAZYMAP.get("Java");
		simple.tragetMethod(".NET");
		System.out.println(simple.equals(simple2));
	}
	
	//生成代理实例
	public static CglibSimple getInstance() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(CglibSimple.class);
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				if (method.getName().equals("test")) {
					System.out.println("before method run..."+method.getName());
					Object result = proxy.invokeSuper(obj, args);
					System.out.println("after method run...");
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