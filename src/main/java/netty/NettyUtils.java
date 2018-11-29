package netty;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;

/**
 * Netty常用API示例
 **/
public class NettyUtils {
	public NettyUtils() {
	}

	public static void main(String[] args) {
		Unpooled();
	}

	/**
	 * Unpooled 缓冲区,创建未池化的ByteBuf
	 **/
	public static void Unpooled() {
		// 包装,改变此Buf,源也会改变
		byte[] byteArray = "Hello World".getBytes();
		ByteBuf buf = Unpooled.wrappedBuffer(byteArray);
		buf.setByte(0, 'G');
		System.out.println("buf-->: " + buf.toString(CharsetUtil.UTF_8) + ", byteArray-->: " + new String(byteArray));

		// 复制,改变此Buf,源不会变
		byte[] byteArray2 = "Hello World".getBytes();
		ByteBuf buf2 = Unpooled.copiedBuffer(byteArray2);
		buf2.setByte(0, 'G');
		System.out.println("buf-->: " + buf2.toString(CharsetUtil.UTF_8) + ", byteArray-->: " + new String(byteArray2));

	}

	/**
	 * Unpooled 缓冲区,ByteBuf检索字节
	 **/
	public static int searchByteIndex() {
		ByteBuf bf = Unpooled.copiedBuffer("Hello World", Charset.defaultCharset());

		ByteBufUtil.writeUtf8(bf, ",Java!");

		System.out.println(bf.toString(Charset.defaultCharset()));
		//常量NULL
		//ByteProcessor.FIND_NUL 
		// bf.readerIndex(6); //设置读索引下标
		int index = bf.forEachByte(new ByteProcessor() {
			@Override
			public boolean process(byte value) throws Exception {
				if (value == 'W') {
					return false;
				} else {
					return true;
				}
			}
		});

		System.out.println("index: " + index);
		return index;
	}
}
