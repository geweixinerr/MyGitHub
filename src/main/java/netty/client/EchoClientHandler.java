package netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 
 * Netty客户端
 * 
 **/
public final class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		Channel channel = ctx.channel();
		for (int i = 0; i < 100; i++) {
			channel.writeAndFlush(Unpooled.copiedBuffer("Netty Hello World,中国欢迎您!"+System.getProperty("line.separator"), CharsetUtil.UTF_8));
		}
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
		System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
	}
	
}
