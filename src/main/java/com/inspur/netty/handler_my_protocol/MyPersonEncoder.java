package com.inspur.netty.handler_my_protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * User: YANG
 * Date: 2019/5/5
 * Time: 18:20
 * Description: No Description
 * 这是自定义协议中重要的编码器的编写
 */
public class MyPersonEncoder extends MessageToByteEncoder<PersonProtocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, PersonProtocol personProtocol, ByteBuf byteBuf) throws Exception {
        System.out.println("MyPersonEncoder invoked");
        byteBuf.writeInt(personProtocol.getLength());
        byteBuf.writeBytes(personProtocol.getContent());
    }
}
