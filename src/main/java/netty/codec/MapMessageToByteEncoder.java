package netty.codec;

import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author gewx 自定义编码器(处理出站数据)
 **/
public class MapMessageToByteEncoder extends MessageToByteEncoder<Map<String,Object>> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Map msg, ByteBuf out) throws Exception {	
         System.out.println("SimpleMessageToByteEncoder: " + msg.getClass().getName());
         out.writeBytes(Unpooled.copiedBuffer("出站拦截器".getBytes()));
	}

}
