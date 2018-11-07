package direct;

import java.nio.ByteBuffer;

import org.junit.Test;
import sun.nio.ch.DirectBuffer;
import sun.misc.Unsafe;

/**
 * Java堆外内存分配 Direct Buffer就好比是“内核缓冲区”上的缓存，不直接受GC管理,但堆外内存上创建的Java对象受GC堆管理，
 * 当堆外内存上的Java对象消亡后,内存即被回收,否则就算Full GC堆外内存也不会释放.
 **/
@SuppressWarnings({ "restriction", "unused" })
public final class DirectMemory {
	public static void sleep(long i) {
		try {
			Thread.sleep(i);
		} catch (Exception e) {
			/* skip */
		}
	}

	@Test
	public void testDirectByteBufferDeallocation() {

		/**
		 * DirectByteBuffer构造方法是包私有的，只能通过工具方法： public static ByteBuffer
		 * allocateDirect(int capacity)生成对象
		 */
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024);

		System.out.println(byteBuffer.isDirect());
		sleep(1000 * 60);

		System.out.println("start clean");
		// 可以通过操作系统命令查看堆外内存清理前后的内存占用
		clean(byteBuffer);
		System.out.println("end clean");

		sleep(1000 * 60);
	}

	// 通过反射,手工释放堆外内存
	public static void clean(final ByteBuffer byteBuffer) {
		if (byteBuffer.isDirect()) {
			((DirectBuffer) byteBuffer).cleaner().clean();
		}
	}

}
