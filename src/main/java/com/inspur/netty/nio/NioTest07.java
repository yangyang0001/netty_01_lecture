package com.inspur.netty.nio;

import java.nio.ByteBuffer;

/**
 * User: YANG
 * Date: 2019/4/26
 * Time: 14:52
 * Description: No Description
 *
 * 针对ReadOnlyBuffer的理解:
 *      我们可以随时将一个Read/Write Buffer 调用asReadOnlyBuffer()转换成一个 read-only buffer
 *      反过来,我们不能把一个read-only buffer转换成 read/write buffer 这样 read-only 就没有任何意义了!
 */
public class NioTest07 {

    public static void main(String[] args){

        ByteBuffer buffer = ByteBuffer.allocate(10);

        for(int i = 0; i < buffer.capacity(); i++){
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

//        readOnlyBuffer.put(0, (byte) 10);//Exception in thread "main" java.nio.ReadOnlyBufferException


    }
}
