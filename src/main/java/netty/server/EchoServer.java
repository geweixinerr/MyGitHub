package netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import netty.client.EchoClientInChannelHandler;

/**
 * @author gewx Netty服务端
 **/
public final class EchoServer {

	public static void start() throws InterruptedException {
		EventLoopGroup groupWork = new NioEventLoopGroup();
		EventLoopGroup groupIO = new NioEventLoopGroup();

		ServerBootstrap boot = new ServerBootstrap();
		try {
			boot.group(groupIO, groupWork).channel(NioServerSocketChannel.class).localAddress("localhost", 8081)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline pipline = ch.pipeline();
							pipline.addLast(new EchoServerInChannelHandler());
						}
					});
			ChannelFuture future = boot.bind().sync();
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if (future.isSuccess()) {
						System.out.println("启动成功!");
					} else {
						System.out.println("启动失败!");
					}
				}
			});
			future.channel().closeFuture().sync();
		} finally {
			groupIO.shutdownGracefully().sync();
			groupWork.shutdownGracefully().sync();
		}

	}

	public static void main(String[] args) throws Exception {
		EchoServer.start();
	}
}
