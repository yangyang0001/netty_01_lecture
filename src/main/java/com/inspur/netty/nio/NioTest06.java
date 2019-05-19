package com.inspur.netty.nio;

import java.nio.ByteBuffer;

/**
 * User: YANG
 * Date: 2019/4/26
 * Time: 14:29
 * Description: No Description
 * SliceBuffer 切片Buffer或叫镜像Buffer,新的SliceBuffer 和 原始的Buffer共用一块数据区!
 * slice() 方法返回一个buffer,但是和原始的buffer会共享同样的数据！
 */
public class NioTest06 {

    public static void main(String[] args){

        ByteBuffer buffer = ByteBuffer.allocate(10);

        for(int i= 0; i< buffer.capacity(); i++){
            buffer.put((byte) i);
        }

        buffer.position(2);
        buffer.limit(6);

        System.out.println("position:" + buffer.position() + ",limit:" + buffer.limit() + ",capacity:" + buffer.capacity());

        ByteBuffer sliceBuffer = buffer.slice();

        for(int i= 0; i < sliceBuffer.capacity() ;i++){
            byte b = sliceBuffer.get(i);
            sliceBuffer.put(i, (byte) (2 * b));
        }

        System.out.println("-----------------------------------------------------");

        //遍历原始的Buffer之前必须要办buffer的 position 和  limit 重新设置回来! 可以打出来看一下设置后的buffer的  position 和 limit
        buffer.position(0);
        buffer.limit(buffer.capacity());

        for(int i = 0; i < buffer.capacity(); i++){
            System.out.println(buffer.get());
        }

    }
}
