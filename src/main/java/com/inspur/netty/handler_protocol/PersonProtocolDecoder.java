package com.inspur.netty.handler_protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * User: YANG
 * Date: 2019/5/6
 * Time: 9:55
 * Description: No Description
 */
public class PersonProtocolDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int length = byteBuf.readInt();
        byte[] content = new byte[length];
        byteBuf.readBytes(content);

        StudentProtocol studentProtocol = new StudentProtocol();
        studentProtocol.setLength(length);
        studentProtocol.setContent(content);

        list.add(studentProtocol);
    }
}
