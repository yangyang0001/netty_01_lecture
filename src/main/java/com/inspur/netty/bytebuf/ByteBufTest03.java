package com.inspur.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

/**
 * Netty中的ByteBuf有三种类型!
 * 1.heap ByteBuf
 * 2.direct ByteBuf
 * 3.composite ByteBuf  (混合Buffer)
 *
 *
 *
 * NIO中的ByteBuffer
 * 缺点:
 *  1.无法动态扩容!
 *  2.如果重复使用必须调用flip() 或 rewind()
 *
 * Netty中的 ByteBuf 可以动态扩容!
 *
 * ReferenceCounted : 在Netty中的ByteBuf 中的 引用计数 = 0 将会被垃圾回收!
 * Netty中对引用计数的方式采用了CAS的方式进行计数与管理的, CAS-->compareAndSet操作 一定能够保证引用的并发安全性!
 *
 * AtomicIntegerFiledUpdater
 * AtomicLongFiledUpdater
 * AtomicReferenceFiledUpdater
 */
public class ByteBufTest03 {

    public static void main(String[] args){

        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        ByteBuf heapBuffer = Unpooled.buffer(10);
        ByteBuf directBuffer = Unpooled.directBuffer(18);

        heapBuffer.setByte(0, 1);

        compositeByteBuf.addComponents(heapBuffer, directBuffer);
//        compositeByteBuf.removeComponent(0);

        Iterator<ByteBuf> iterator = compositeByteBuf.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println(heapBuffer.retain());

//        compositeByteBuf.forEach(System.out::println);


    }
}
