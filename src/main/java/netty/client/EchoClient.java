package netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author gewx
 * Netty客户端
 * **/
public final class EchoClient {
	
	/**
	 * 启动客户端连接
	 * @throws InterruptedException 
	 * **/
	public static void start() throws InterruptedException {
		EventLoopGroup eventGroup = new NioEventLoopGroup();
		Bootstrap boot = new Bootstrap();
		try {
			boot.group(eventGroup).channel(NioSocketChannel.class).remoteAddress("localhost", 8081).handler(new ChannelInitializer<SocketChannel>() {
				 @Override
				protected void initChannel(SocketChannel ch) throws Exception {
					 ChannelPipeline piple = ch.pipeline();
	                 piple.addLast(new EchoClientInChannelHandler());
				}
			}); 
			
			ChannelFuture future = boot.connect().sync();
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if (future.isSuccess()) {
						System.out.println("连接成功!");
					} else {
					    System.out.println("连接失败! message:" + future.cause().getMessage());
					}
				}
			});
			future.channel().closeFuture().sync();
			
		} finally {
			eventGroup.shutdownGracefully().sync();
		}
 	}
	
	public static void main(String[] args) throws Exception {
		EchoClient.start();
	}
}
