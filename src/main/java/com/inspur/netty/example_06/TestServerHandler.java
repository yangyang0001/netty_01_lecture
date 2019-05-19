package com.inspur.netty.example_06;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * User: YANG
 * Date: 2019/4/23
 * Time: 20:33
 * Description: No Description
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyDataInfo.Person person) throws Exception {
        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.getGradename());
    }
}
