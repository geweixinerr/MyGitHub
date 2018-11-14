package netty.server;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Netty服务器示例
 **/
public final class NettyEchoServer {

	private final Integer port;

	public NettyEchoServer(Integer port) {
		this.port = port;
	}

	public static void main(String[] args) throws InterruptedException {
		NettyEchoServer server = new NettyEchoServer(8081);
		server.startServer();
	}

	public void startServer() throws InterruptedException {
		final EchoServerInboundHandler echo = new EchoServerInboundHandler();
		
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
							ch.pipeline().addLast(new StringDecoder());
							ch.pipeline().addLast(echo);
						}
					});

			ChannelFuture f = b.bind().sync();
			f.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if (!future.isSuccess()) {
						System.out.println("服务启动失败!");
					} else {
						System.out.println("服务启动成功!");
					}
				}
			});
			
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}

}
