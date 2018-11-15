package netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ChannelHandler.Sharable;

/**
 * 
 * Netty服务端-出站事件Handler
 * 解释,ChannelOutboundHandlerAdapter本质上是对写数据的一个包装.这里可以做数据缓冲等包装.
 * 
 **/
@Sharable
public final class EchoServerOutboundHandler extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println("服务器出站事件! message: " + msg);
		ctx.writeAndFlush(msg, promise);
	}

}
