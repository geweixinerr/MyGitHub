package concurrent.future;

import java.util.concurrent.CompletableFuture;

/**
 * @author gewx 异步任务 参考资料:
 *         https://www.cnblogs.com/happyliu/archive/2018/08/12/9462703.html
 **/
public final class CompletableFutureMain {

	public static void main(String[] args) {

	}

	public static void basic() {
		String result = CompletableFuture.supplyAsync(() -> {
			return "Hello ";
		}).thenApplyAsync(v -> v + "world").join();

		System.out.println(result);
	}

	public static void basicPlus() {
		String result = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello";
		}).thenCombine(CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "world";
		}), (s1, s2) -> {
			return s1 + " " + s2;
		}).join();

		System.out.println(result);
	}
}
