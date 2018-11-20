package netty.codec;

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
         System.out.println("Map出站编码器: " + msg.getClass().getName() + ",value: " + msg);
         out.writeBytes("Java Map!".getBytes());
	}

}
