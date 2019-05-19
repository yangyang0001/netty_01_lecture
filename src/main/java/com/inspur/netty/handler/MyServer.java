package com.inspur.netty.handler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * User: YANG
 * Date: 2019/5/5
 * Time: 11:45
 * Description: No Description
 */
public class MyServer {

     public static void main(String[] args){
         EventLoopGroup bossGroup  = new NioEventLoopGroup(1);
         EventLoopGroup workerGroup = new NioEventLoopGroup();

         try {
             ServerBootstrap serverBootstrap = new ServerBootstrap();
             serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                     .childHandler(new MyServerInitializer());

             ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
             channelFuture.channel().closeFuture().sync();

         } catch (Exception ex) {
             ex.printStackTrace();
         } finally {
             bossGroup.shutdownGracefully();
             workerGroup.shutdownGracefully();
         }

     }

}
