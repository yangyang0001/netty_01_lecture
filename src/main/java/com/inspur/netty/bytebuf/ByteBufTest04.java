package com.inspur.netty.bytebuf;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;

/**
 * User: YANG
 * Date: 2019/5/4
 * Time: 21:26
 * Description: No Description
 */
public class ByteBufTest04 {

    public static void main(String[] args){
        ByteBuf buffer1 = Unpooled.buffer(10);//非池化 ByteBuf
        System.out.println(buffer1.refCnt());
        System.out.println(buffer1.getClass());
        buffer1.release();
        System.out.println(buffer1.refCnt());
    }
}
