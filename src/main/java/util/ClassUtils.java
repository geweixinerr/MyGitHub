package util;

/**
 * @author gewx 2019.10.18 Class辅助类
 **/
public final class ClassUtils {

	/**
	 * @author gewx 判断类是否存在
	 * @param className 类名
	 * @return class对象
	 **/
	@SuppressWarnings("rawtypes")
	public static Class hasClass(String className) {
		try {
			Class object = Class.forName(className);
			return object;
		} catch (Throwable ex) {
			return null;
		}
	}
}
