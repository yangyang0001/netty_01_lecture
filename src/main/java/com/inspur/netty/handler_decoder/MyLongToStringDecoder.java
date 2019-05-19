package com.inspur.netty.handler_decoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * User: YANG
 * Date: 2019/5/5
 * Time: 16:23
 * Description: No Description
 */
public class MyLongToStringDecoder extends MessageToMessageDecoder<Long> {
    @Override
    protected void decode(ChannelHandlerContext ctx, Long msg, List<Object> out) throws Exception {
        System.out.println("MyLongToStringDecoder invoked");
        out.add(String.valueOf(msg));
    }
}
