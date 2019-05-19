package com.inspur.netty.example_03;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * User: YANG
 * Date: 2019/4/22
 * Time: 23:35
 * Description: No Description
 */
public class MyChatClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            for(; ;){
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
