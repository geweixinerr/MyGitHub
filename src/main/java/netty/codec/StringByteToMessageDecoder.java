package netty.codec;

import java.nio.charset.Charset;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author gewx 自定义解码器(处理进站数据)
 **/

public class StringByteToMessageDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("进站解码器:" + in.toString(Charset.forName("UTF-8")));
//		out.add("Hello World Java! VeryGood!");  
//        in.skipBytes(in.readableBytes());
        in.skipBytes(in.readableBytes());
        out.add("Hello World Java! VeryGood!");
        
    
	}

}
