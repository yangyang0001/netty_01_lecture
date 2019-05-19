package com.inspur.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * User: YANG
 * Date: 2019/5/3
 * Time: 22:55
 * Description: No Description
 */
public class ByteBufTest01 {

    public static void main(String[] args){
        ByteBuf buffer = Unpooled.buffer(10);

        for(int i = 0; i < buffer.capacity(); i++ ){
            buffer.writeByte((byte) i);
            System.out.println("writerIndex --------:" + buffer.writerIndex());
        }

        for(int i = 0; i < buffer.capacity() ;i++) {
            System.out.println(buffer.readByte());
            System.out.println("readerIndex --------:" + buffer.readerIndex());
        }
    }
}
