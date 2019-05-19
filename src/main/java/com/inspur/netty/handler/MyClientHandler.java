package com.inspur.netty.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * User: YANG
 * Date: 2019/5/5
 * Time: 11:58
 * Description: No Description
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("来自服务器" + channel.remoteAddress() + "的消息:" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
//        ctx.writeAndFlush(123456L);
//        ctx.writeAndFlush(1L);
//        ctx.writeAndFlush(2L);
//        ctx.writeAndFlush(3L);
//        ctx.writeAndFlush(4L);

        ctx.writeAndFlush(Unpooled.copiedBuffer("helloworld", Charset.forName("UTF-8")));
    }
}
