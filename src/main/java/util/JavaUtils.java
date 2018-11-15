package util;

/**
 * @author gewx
 * Java常用辅助工具方法
 * **/
public final class JavaUtils {

	public JavaUtils() {
		
	}

	/**
	 * 打印系统默认换行符
	 * **/
	public void printLineSeparator() {
		String lineSeparator = System.getProperty("line.separator", "\n");
		System.out.println("系统默认换行符: " + lineSeparator);
	}
}
