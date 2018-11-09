package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * Netty常用API示例
 * **/
public class NettyTest {
	public NettyTest() {}
	
	public static void main(String[] args) {
		Unpooled();
	}

	/**
	 * Unpooled 缓冲区,创建未池化的ByteBuf
	 * **/
	public static void Unpooled() {
		//包装,改变此Buf,源也会改变
		byte [] byteArray = "Hello World".getBytes();
		ByteBuf buf = Unpooled.wrappedBuffer(byteArray); 
		buf.setByte(0, 'G');
		System.out.println("buf-->: " + buf.toString(CharsetUtil.UTF_8) + ", byteArray-->: " + new String(byteArray));
		
		//复制,改变此Buf,源不会变
		byte [] byteArray2 = "Hello World".getBytes();
		ByteBuf buf2 = Unpooled.copiedBuffer(byteArray2); 
		buf2.setByte(0, 'G');
		System.out.println("buf-->: " + buf2.toString(CharsetUtil.UTF_8) + ", byteArray-->: " + new String(byteArray2));
		
	}
}
