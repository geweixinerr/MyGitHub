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
		ChannelFuture future = channel.writeAndFlush(Unpooled.copiedBuffer("Netty Hello World,中国欢迎您!", CharsetUtil.UTF_8));
		future.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					System.out.println("write success!");
				}
			}
		});
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
		System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
	}
	
}
