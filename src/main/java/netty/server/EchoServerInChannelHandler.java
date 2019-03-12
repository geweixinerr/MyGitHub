package netty.server;

import java.nio.charset.Charset;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author gewx
 * 服务端入站处理器
 * **/
public class EchoServerInChannelHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("连接已经建立!");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String message = (String)msg;
		System.out.println("服务器读取到的信息:" + message);
		ctx.writeAndFlush(Unpooled.copiedBuffer(message + System.getProperty("line.separator"),Charset.defaultCharset()));
	}

}
