package com.inspur.netty.handler_tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * User: YANG
 * Date: 2019/5/5
 * Time: 17:35
 * Description: No Description
 */
public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    //模拟 服务器端 来自服务器端的消息的个数
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        Channel channel = ctx.channel();
        //处理 来自 服务器端的 消息
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);

        String message = new String(buffer, CharsetUtil.UTF_8);
        System.out.println("来自服务器端的消息---------------------------:" + message);
        System.out.println("来一条消息计数器++,消息到来后,客户端计数器值为--:" + ++count);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0; i < 10; i++){
            ByteBuf buffer = Unpooled.copiedBuffer("client message", Charset.forName("UTF-8"));
            ctx.writeAndFlush(buffer);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
