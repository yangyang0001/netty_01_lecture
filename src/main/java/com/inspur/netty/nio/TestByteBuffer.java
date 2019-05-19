package com.inspur.netty.nio;

import java.nio.ByteBuffer;

/**
 * User: YANG
 * Date: 2019/5/10-15:15
 * Description: No Description
 * 在 Buffer 类中 mark <= position <= limit <= capacity
 */
public class TestByteBuffer {

    public static void main(String[] args){

        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println("put before  position:" + buffer.position() + ",limit:" + buffer.limit() + ",capacity:" + buffer.capacity());
        buffer.putLong(1L);
        System.out.println("put after   position:" + buffer.position() + ",limit:" + buffer.limit() + ",capacity:" + buffer.capacity());


    }
}
