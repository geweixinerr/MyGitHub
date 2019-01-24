package concurrent.alibaba;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

/**
 * @author gewx InheritableThreadLocal基本使用
 * **/
public final class InheritableThreadLocalSimple {
	
	public static final ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();
	
	//alibaba开源组件
	public static final TransmittableThreadLocal<String> t_threadLocal = new TransmittableThreadLocal<String>();
		
	//线程池
	public static final Executor execute = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1));
	
    public static void main(String args[]){
        threadLocal.set("Hello world.");
        t_threadLocal.set("Hello world.");
        
        execute.execute(new Runnable() {
			@Override
			public void run() {
	            System.out.println("threadLocal = " + threadLocal.get());
	            System.out.println("t_threadLocal = " + t_threadLocal.get());
			}
		});
        
        t_threadLocal.set("Hello world.Java");
        
        execute.execute(new Runnable() {
			@Override
			public void run() {
	            System.out.println("threadLocal = " + threadLocal.get());
	            System.out.println("t_threadLocal = " + t_threadLocal.get());
			}
		});
        
    }
 
    //Test thread
    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println("MyThread = " + threadLocal.get());
            System.out.println("MyThread = " + t_threadLocal.get());
        }
    }

}
