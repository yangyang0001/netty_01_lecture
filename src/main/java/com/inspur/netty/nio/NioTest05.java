package com.inspur.netty.nio;

import java.nio.ByteBuffer;

/**
 * User: YANG
 * Date: 2019/4/26
 * Time: 14:16
 * Description: No Description
 * 特定类型化的put和get必须要是同样的顺序才行,否则会抛出异常!
 */
public class NioTest05 {

    public static void main(String[] args){

        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(10);
        buffer.putLong(100000000L);
        buffer.putDouble(14.1234567d);
        buffer.putChar('你');
        buffer.putShort((short) 2);
        buffer.putChar('我');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());


    }
}
