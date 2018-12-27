package spring.reflect;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.ClassLoaderException;
import util.StringUtil;

public final class SpringReflect {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringReflect.class);

	private static Class WebApplicationContextUtils;
	
	//初始化
	static {
		try {
			Class.forName("org.springframework.web.context.support.WebApplicationContextUtils");
		} catch (ClassNotFoundException e) {
			throw new ClassLoaderException("类不存在,message: " + StringUtil.getErrorText(e));
		}
	}
	
	public static void main(String[] args) {
		/**
		 * 加载WebApplicationContextUtils的Class源数据对象.
		 **/
		/*
		Class WebApplicationContextUtils = Class
				.forName("org.springframework.web.context.support.WebApplicationContextUtils");

		// 反射获取WebApplicationContext对象.
		Method webApplicationContext = WebApplicationContextUtils.getMethod("getWebApplicationContext",
				ServletContext.class);
		Object appContext = webApplicationContext.invoke(null, servletContext); // 静态反射调用,不需要传递第一个参数,默认给null.

		// 反射调用getBean方法
		Method getBeanMethod = appContext.getClass().getMethod("getBean", String.class);
		Object bean = getBeanMethod.invoke(appContext, "TestBean");

		// 反射执行.
		Method method = bean.getClass().getMethod("TestmethodName");
		method.invoke(bean);
       */ 
	}
	
	/**
	 * @author gewx 执行Spring Bean方法
	 * @param beanName Spring Bean名称, methodName 要执行的方法, args 方法参数
	 * @return Object 执行结果.
	 * **/
	public static Object invoke(String beanName ,String methodName,Object... args) {
		/*
		Method webApplicationContext = WebApplicationContextUtils.getMethod("getWebApplicationContext",
				ServletContext.class);
		Object appContext = webApplicationContext.invoke(null, servletContext); // 静态反射调用,不需要传递第一个参数,默认给null.

		// 反射调用getBean方法
		Method getBeanMethod = appContext.getClass().getMethod("getBean", String.class);
		Object bean = getBeanMethod.invoke(appContext, "TestBean");

		// 反射执行.
		Method method = bean.getClass().getMethod("TestmethodName");
		method.invoke(bean);
		*/
		return null;
	}
}
