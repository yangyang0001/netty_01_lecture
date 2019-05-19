package com.inspur.netty.handler_my_protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * User: YANG
 * Date: 2019/5/5
 * Time: 18:23
 * Description: No Description
 */
public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();
        System.out.println("来自客户端的消息: length:" + length + ",content:" + new String(content, CharsetUtil.UTF_8));
        System.out.println("来自客户端消息的个数:" + (++count));

        String responseMessage = UUID.randomUUID().toString();
        int responseMessageLength = responseMessage.getBytes(CharsetUtil.UTF_8).length;
        byte[] responseMessageContent = responseMessage.getBytes(CharsetUtil.UTF_8);

        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(responseMessageLength);
        personProtocol.setContent(responseMessageContent);

        ctx.writeAndFlush(personProtocol);
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
