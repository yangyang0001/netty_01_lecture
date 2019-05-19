package com.inspur.netty.nio;

import java.nio.IntBuffer;

/**
 * User: YANG
 * Date: 2019/4/26
 * Time: 23:03
 * Description: No Description
 *
 * Buffer中的 flip() 方法中非常重要的 三个标识 position limit capacity
 */
public class MyNioTest01 {

    public static void main(String[] args){
        IntBuffer buffer = IntBuffer.allocate(10);

        for(int i = 0;i < buffer.capacity(); i++){
            buffer.put(i);
        }

        buffer.flip();

        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
