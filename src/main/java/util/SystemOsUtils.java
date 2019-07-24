package util;

/**
 * @author gewx 获取系统参数
 * **/
public final class SystemOsUtils {

	public static void getSystemArgs() {
		//-Djvmargs=123456 
		String jvmargs = System.getProperty("jvmargs");
		System.out.println("jvmargs: " + jvmargs);

		//获取环境变量
		String val = System.getenv("home");
		System.out.println("val: " + val);
		
		String javaHome = System.getenv("JAVA_HOME");
		System.out.println("javaHome: " + javaHome);
	}
	
	public static void main(String[] args) {
		getSystemArgs();
	}
}
