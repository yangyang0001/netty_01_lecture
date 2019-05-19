package com.inspur.netty.handler_decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * User: YANG
 * Date: 2019/5/5
 * Time: 14:39
 * Description: No Description
 *
 * 特别注意：
 *      泛型Void 这里是状态的控制,因为这里没有状态的控制,所以这里是Void
 */
public class MyByteToLongReplayingDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MyByteToLongReplayingDecoder invoked");
        //这里和 handler 包中的 MyByteToLongDecoder 处理方式是不一样的, 这里没有进行 判断
        list.add(byteBuf.readLong());
    }
}
