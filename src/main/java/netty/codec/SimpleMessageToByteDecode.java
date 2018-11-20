package netty.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author gewx 自定义解码器(处理进站数据)
 **/

public class SimpleMessageToByteDecode extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		ByteBuf bb = Unpooled.copiedBuffer("Hello".getBytes());
        out.add("Hello World Java!");  
        in.skipBytes(in.readableBytes());
	}

}
