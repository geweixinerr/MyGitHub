package netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author gewx 自定义编码器(处理出站数据)
 **/
public class StringMessageToByteEncoder extends MessageToByteEncoder<String> {

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {	
         System.out.println("String出站编码器: " + msg.getClass().getName() + ",value: " + msg);
         out.writeBytes("Java String!".getBytes());
	}

}
