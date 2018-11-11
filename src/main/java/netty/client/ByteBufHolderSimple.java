package netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;

/**
 * ByteBufHolder实现示例
 * **/
public class ByteBufHolderSimple extends DefaultByteBufHolder {

	@SuppressWarnings("unused")
	private final String header; //消息体
	
	public ByteBufHolderSimple(ByteBuf data, String header) {
		super(data);
		this.header = header;
	}
 
}
