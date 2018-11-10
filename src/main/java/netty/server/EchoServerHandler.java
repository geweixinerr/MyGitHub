package netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;

/**
 * 示例Server Handler
 **/
@Sharable
public final class EchoServerHandler extends ChannelInboundHandlerAdapter {

	private static final AttributeKey<String> key = AttributeKey.valueOf("field");

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("第一个channelRead!");
		ByteBuf bf = (ByteBuf) msg;
		String message = bf.toString(CharsetUtil.UTF_8);
		ctx.channel().attr(key).set(message);
        System.out.println("是否是直接内存:" + bf.isDirect());
        if (bf.isDirect()) {
        	System.out.println("bf-toString:" + bf.toString()); 
        	int length = bf.readableBytes(); //可读取字节数
        	byte [] b = new byte[length];
        	int index = bf.readerIndex();
        	System.out.println("读取的字节index: " + index + ",length: " + length);
        	bf.getBytes(index, b);
        	String sb = new String(b);
        	System.out.println("读取的数据:" + sb);
        }
		ctx.write(bf);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelReadComplete:" + ctx.channel().attr(key).get());
		
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);//.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
         cause.printStackTrace();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("生命周期开始!");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("生命周期结束!");
	}

}
