package concurrent.thread;

/**
 * @author gewx InheritableThreadLocal基本使用
 * **/
public final class InheritableThreadLocalSimple {
	
	public static ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();
	 
    public static void main(String args[]){
        threadLocal.set("Hello world.");
 
        Thread thread = new MyThread();
        thread.start();
 
        System.out.println("main = " + threadLocal.get());
    }
 
    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println("MyThread = " + threadLocal.get());
        }
    }

}
