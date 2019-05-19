package com.inspur.netty.handler_protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * User: YANG
 * Date: 2019/5/6
 * Time: 9:58
 * Description: No Description
 */
public class PersonProtocolEncoder extends MessageToByteEncoder<StudentProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, StudentProtocol msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
