package netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author gewx Netty客户端
 **/
public final class EchoClient {

	/**
	 * 启动客户端连接
	 * 
	 * @throws InterruptedException
	 **/
	public static void start() throws InterruptedException {
		EventLoopGroup eventGroup = new NioEventLoopGroup();
		Bootstrap boot = new Bootstrap();
		try {
			boot.group(eventGroup).channel(NioSocketChannel.class).remoteAddress("localhost", 8081)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline pipline = ch.pipeline();
							//pipline.addLast(new LoggingHandler(LogLevel.INFO)); // 开启日志监控
							pipline.addLast(new LineBasedFrameDecoder(1024));
							pipline.addLast(new StringDecoder());
							pipline.addLast(new EchoClientInChannelHandler());
						}
					});

			ChannelFuture future = boot.connect().sync();
			future.channel().closeFuture().sync();

		} finally {
			eventGroup.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) throws Exception {
		EchoClient.start();
	}
}
