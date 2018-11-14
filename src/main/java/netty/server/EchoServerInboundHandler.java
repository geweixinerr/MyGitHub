package netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 示例Server Handler
 **/
@Sharable
public final class EchoServerInboundHandler extends ChannelInboundHandlerAdapter {

	private static final AttributeKey<String> key = AttributeKey.valueOf("field");

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("第一个channelRead!");
		ByteBuf bf = (ByteBuf) msg;
		ctx.writeAndFlush(bf);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
         cause.printStackTrace();
	}

}
