package com.hz.yisheng.nio.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hz.yisheng.nio.conf.NettyRFIDConstant;



/**
 * RFID接收数据服务端，使用UDP广播通讯协议
 * 
 * @author WeiSky
 *
 */
public class NettyUDPRFIDServer{
	
	private static Logger logger = LoggerFactory.getLogger(NettyUDPRFIDServer.class);
	
	public static void run(NettyRFIDConstant nettyRFIDConstant){
		//配置服务端NIO线程组
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioDatagramChannel.class)
			 .option(ChannelOption.SO_BROADCAST, true)
			 .handler(new NettyUDPRFIDServerHandler());
			
			//邦定ip地址和端口，并启动监听
			b.bind(new InetSocketAddress(nettyRFIDConstant.getNioIp(), nettyRFIDConstant.getNioPort())).sync().channel().closeFuture().await();
		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.error("NettyUDPRFIDServer run.InterruptedException", e);
		}finally{
			//释放资源
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		NettyRFIDConstant cons = new NettyRFIDConstant();
		cons.setNioPort(8886);
		cons.setNioIp("192.168.10.103");
		run(cons);
	}
	
}
