package com.inspur.netty.handler_decoder;

import com.inspur.netty.handler.MyLongToByteEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * User: YANG
 * Date: 2019/5/5
 * Time: 11:57
 * Description: No Description
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        channelPipeline.addLast(new MyByteToLongReplayingDecoder());
        channelPipeline.addLast(new MyLongToByteEncoder());

        channelPipeline.addLast(new MyClientHandler());
    }

}
