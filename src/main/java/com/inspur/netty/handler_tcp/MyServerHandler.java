package com.inspur.netty.handler_tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * User: YANG
 * Date: 2019/5/5
 * Time: 17:29
 * Description: No Description
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    //模仿 TCP的 粘包的 过程 定义一个计数器!
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        Channel channel = ctx.channel();
        //处理 客户端 过来的消息
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        String message = new String(buffer, Charset.forName("UTf-8"));

        System.out.println("来自客户端" + channel.remoteAddress() + "的消息:" + message);
        System.out.println("来一条消息计数器++,消息到来后,服务器端计数器值为--:" + ++count);

        //向客户端 返回一条消息
        ByteBuf returnBuf = Unpooled.copiedBuffer(UUID.randomUUID().toString(), Charset.forName("UTF-8"));
        channel.writeAndFlush(returnBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
