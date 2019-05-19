package com.inspur.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * User: YANG
 * Date: 2019/4/25
 * Time: 21:15
 * Description: No Description
 *
 *
 * 经过验证:
 *
 *      position 就是当前存储的个数
 *      limit    初始值为 capacity值, flip后 为 position的值
 *      capacity 就是Buffer的申请的长度
 *      在 Buffer 类中 mark <= position <= limit <= capacity
 */
public class NioTest01 {

    public static void main(String[] args){

        //经过javaDoc中的方法可以看出调用的是new HeapIntBuffer(capacity, capacity);
        IntBuffer intBuffer = IntBuffer.allocate(10);

        for(int i = 0; i < 5; i++){
            int randomNum = new SecureRandom().nextInt(20);
            intBuffer.put(randomNum);
        }
        //分析 limit:10 position:5 capacity:10
        System.out.println("before flip -->limit:" + intBuffer.limit() + ",position:" + intBuffer.position() + ",capacity:" + intBuffer.capacity());

        intBuffer.flip();

        //分析 limit:5 position:0 capacity:10
        System.out.println("after  flip -->limit:" + intBuffer.limit() + ",position:" + intBuffer.position() + ",capacity:" + intBuffer.capacity());

        System.out.println("------------------------------------------------------------------------------------------");

        while(intBuffer.hasRemaining()){
            //分析 limit:5 position:0 capacity:10
            //分析 limit:5 position:1 capacity:10
            //分析 limit:5 position:2 capacity:10
            //分析 limit:5 position:3 capacity:10
            //分析 limit:5 position:4 capacity:10
            System.out.println("limit:" + intBuffer.limit() + ",position:" + intBuffer.position() + ",capacity:" + intBuffer.capacity());
            System.out.println(intBuffer.get());
        }

    }
}
