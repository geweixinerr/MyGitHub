package concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gewx 显示锁案例
 **/
public final class ReentrantLockSimple {

	public ReentrantLockSimple() {
	}

	private final ReentrantLock lock = new ReentrantLock();

	public void syncOne() throws InterruptedException {
		System.out.println("One进入锁区!");
		try {
			lock.lock();
			TimeUnit.SECONDS.sleep(10);
			System.out.println("SyncOne锁区执行!");
		} finally {
			lock.unlock();
		}
		System.out.println("One退出锁区!");
	}

	public void syncTwo() throws InterruptedException {
		System.out.println("Two进入锁区!");
		try {
			lock.lock();
			TimeUnit.SECONDS.sleep(10);
			System.out.println("SyncTwo锁区执行!");
		} finally {
			lock.unlock();
		}
		System.out.println("Two退出锁区!");
	}

	public void syncOne_1() throws InterruptedException {
		System.out.println("One进入锁区!");
		if (lock.tryLock()) {
			try {
				TimeUnit.SECONDS.sleep(10);
				System.out.println("SyncOne锁区执行!");
			} finally {
				lock.unlock();
			}
		}
		System.out.println("One退出锁区!");
	}

	public void syncTwo_1() throws InterruptedException {
		System.out.println("Two进入锁区!");
		if (lock.tryLock()) {
			try {
				TimeUnit.SECONDS.sleep(10);
				System.out.println("SyncTwo锁区执行!");
			} finally {
				lock.unlock();
			}
		}
		System.out.println("Two退出锁区!");
	}
	
	public static void main(String[] args) {
		final ReentrantLockSimple simple = new ReentrantLockSimple();
		Executor executor = Executors.newFixedThreadPool(2);
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					simple.syncOne_1();
				} catch (InterruptedException e) {
				}
			}
		});
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					simple.syncTwo_1();
				} catch (InterruptedException e) {
				}
			}
		});
	}
}
