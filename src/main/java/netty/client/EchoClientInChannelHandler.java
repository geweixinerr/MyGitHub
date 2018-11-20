package netty.client;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author gewx
 * 客户端入站处理器
 * **/
public class EchoClientInChannelHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("连接已经建立!");
		ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,中国",Charset.defaultCharset()));
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String message = ((ByteBuf)msg).toString(Charset.defaultCharset());
		System.out.println("读取到的信息:" + message);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("数据读取成功!");
	}

}
