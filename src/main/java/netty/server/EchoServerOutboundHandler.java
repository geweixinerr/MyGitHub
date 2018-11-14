package netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.AttributeKey;

/**
 * 示例Server Handler
 **/
@Sharable
public final class EchoServerOutboundHandler extends ChannelOutboundHandlerAdapter {

	private static final AttributeKey<String> key = AttributeKey.valueOf("field");
 
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("write!");
		super.write(ctx, msg, promise);
	}

	@Override
	public void flush(ChannelHandlerContext ctx) throws Exception {
        System.out.println("flush!");
		super.flush(ctx);
	}

	
}
