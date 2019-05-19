package com.inspur.netty.example_02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * User: YANG
 * Date: 2019/4/22
 * Time: 17:43
 * Description: No Description
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "," + s);
        ctx.channel().writeAndFlush("from server uuid is " + UUID.randomUUID().toString());
        System.out.println(ctx.getClass());
        System.out.println(ctx.channel().getClass());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
