package concurrent.thread;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author gewx InheritableThreadLocal基本使用
 * **/
public final class InheritableThreadLocalSimple {
	
	public static ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();
	
	public static TransmittableThreadLocal<String> threadLocal_2 = new TransmittableThreadLocal<String>();
	
    public static void main(String args[]){
        threadLocal.set("Hello world.");
        threadLocal_2.set("Hello world.");
 
        Thread thread = new MyThread();
        thread.start();
 
        System.out.println("main = " + threadLocal.get());
        System.out.println("main = " + threadLocal_2.get());
    }
 
    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println("MyThread = " + threadLocal.get());
            System.out.println("MyThread = " + threadLocal_2.get());
        }
    }

}
