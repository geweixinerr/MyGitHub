package com;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

//模拟线程死锁
public class LockClass {

	public LockClass() {}
	
	private Vector<String> v = new Vector<String>();
	
	public void lock1() {
		synchronized (v) {
			System.out.println("进入Lock1方法区!");
	         try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        lock2();
		}
	}
	
	public synchronized void lock2() {
		System.out.println("进入Lock2方法区!");
		v.add("葛维新!"); //死锁!
	}
	
	public static void main(String[] args) throws InterruptedException {
		TimeUnit.SECONDS.sleep(10);
		LockClass lock = new LockClass();
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.lock1();
			}
		});
		
		t.start();
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				lock.lock2();
			}
		});
		
		t2.start();
	}
}
