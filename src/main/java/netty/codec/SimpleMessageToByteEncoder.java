package netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author gewx 自定义编码器(处理出站数据)
 **/
public class SimpleMessageToByteEncoder extends MessageToByteEncoder<String> {

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {	
         System.out.println("出站数据类型: " + msg.getClass().getName());
         out.writeBytes(Unpooled.copiedBuffer("出站拦截器".getBytes()));
	}

}
