package com.inspur.netty.example_01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

    public static void main(String[] args) throws InterruptedException {
        //EventLoopGroup 似于死循环 EventLoopGroup   (事件循环组)
        EventLoopGroup bossGroup = new NioEventLoopGroup();     //接收连接发送给worker
        EventLoopGroup workerGroup = new NioEventLoopGroup();   //worker来处理各种连接

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();    //简化服务端启动的一个辅助Class
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());         //子处理器

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();    //绑定
            channelFuture.channel().closeFuture().sync();

        } finally {
            //netty 中优雅关闭的方式
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
