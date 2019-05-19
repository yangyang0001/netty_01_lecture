package com.inspur.netty.example_06_01;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * User: YANG
 * Date: 2019/4/23
 * Time: 20:33
 * Description: No Description
 */
public class TestServerHandler extends SimpleChannelInboundHandler<PersonDataInfo.MessageInfo> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonDataInfo.MessageInfo messageInfo) throws Exception {
        switch (messageInfo.getDataType()){
            case PersonType:
                System.out.println(messageInfo.getPerson().getName());
                System.out.println(messageInfo.getPerson().getAge());
                System.out.println(messageInfo.getPerson().getGradename());
                break;
            case DogType:
                System.out.println(messageInfo.getDog().getName());
                System.out.println(messageInfo.getDog().getAge());
                break;
            case CatType:
                System.out.println(messageInfo.getCat().getName());
                System.out.println(messageInfo.getCat().getCity());
                break;
        }
    }

}
