package com.inspur.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * User: YANG
 * Date: 2019/5/5
 * Time: 12:29
 * Description: No Description
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder invoked");
        System.out.println(in.readableBytes());

        //下面的判断必须要有
        if(in.readableBytes() >= 8){
            out.add(in.readLong());
        }
    }
}
