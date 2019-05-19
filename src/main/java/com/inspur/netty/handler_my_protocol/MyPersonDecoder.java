package com.inspur.netty.handler_my_protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * User: YANG
 * Date: 2019/5/5
 * Time: 18:16
 * Description: No Description
 * 这是 自定义协议中重要的解码器的编写
 */
public class MyPersonDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MyPersonDecoder invoked");

        int length = byteBuf.readInt();
        //下面的这行代码又进一步验证了 byteBuf中 存在粘包的情况,这里只能读取 length长度的 才能进行正确的处理
        byte[] content = new byte[length];
        byteBuf.readBytes(content);

        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(length);
        personProtocol.setContent(content);

        list.add(personProtocol);
    }
}
