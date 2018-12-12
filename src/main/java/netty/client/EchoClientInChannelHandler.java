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
		for (int i = 0; i < 100; i++) {
			ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,中国["+i+"]" +System.getProperty("line.separator"),Charset.defaultCharset()));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String message = (String)msg;
		System.out.println("客户端读取到的信息:" + message);
	}

}
