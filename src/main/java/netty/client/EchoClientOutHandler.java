package netty.client;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * 
 * Netty客户端-出站事件Handler
 * 解释,ChannelOutboundHandlerAdapter本质上是对写数据的一个包装.这里可以做数据缓冲等包装.
 * 
 **/
public final class EchoClientOutHandler extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println("客户端出站事件! message: " + ((ByteBuf)msg).toString(Charset.forName("UTF-8")));
		ctx.writeAndFlush(msg,promise);
	}
	
}
