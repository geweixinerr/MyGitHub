package netty.client;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import netty.codec.SimpleMessageToByteDecode;
import netty.codec.SimpleMessageToByteEncoder;

/**
 * Netty Client
 **/
public final class NettyEchoClient {

	private final String host;

	private final int port;

	public NettyEchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port))
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new SimpleMessageToByteEncoder());
							ch.pipeline().addLast(new SimpleMessageToByteDecode());
							ch.pipeline().addLast(new EchoClientInboundHandler());
						}
					});
			ChannelFuture f = b.connect().sync();
			f.channel().closeFuture().sync();
						
		} finally {
			group.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) throws Exception {
		new NettyEchoClient("localhost", 8081).start();
	}
	
}
