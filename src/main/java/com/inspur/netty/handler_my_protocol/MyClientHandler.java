package com.inspur.netty.handler_my_protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * User: YANG
 * Date: 2019/5/5
 * Time: 18:29
 * Description: No Description
 */
public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();
        System.out.println("来自服务器端的消息: length:" + length + ",content:" + new String(content, CharsetUtil.UTF_8));
        System.out.println("来自服务器端消息的个数:" + (++count));
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0; i < 10; i++){
            String message = "client message";
            int messageLength = message.getBytes("UTF-8").length;
            byte[] messageContent = message.getBytes(CharsetUtil.UTF_8);

            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setLength(messageLength);
            personProtocol.setContent(messageContent);

            ctx.writeAndFlush(personProtocol);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
