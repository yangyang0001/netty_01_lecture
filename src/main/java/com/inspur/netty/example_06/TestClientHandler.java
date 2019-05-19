package com.inspur.netty.example_06;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * User: YANG
 * Date: 2019/4/23
 * Time: 21:11
 * Description: No Description
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person>{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyDataInfo.Person person) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        MyDataInfo.Person person = MyDataInfo.Person.newBuilder().setName("张三").setAge(30).setGradename("高三八班").build();
        channel.writeAndFlush(person);
    }
}
