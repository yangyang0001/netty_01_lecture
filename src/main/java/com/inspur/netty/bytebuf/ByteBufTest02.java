package com.inspur.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * Unpooled 创建的ByteBuf 就和引用计数就没有关系了!
 */
public class ByteBufTest02 {

    public static void main(String[] args){
        ByteBuf buffer = Unpooled.copiedBuffer("张hello world", Charset.forName("UTF-8"));

        if(buffer.hasArray()){
            byte[] bytes = buffer.array();
            System.out.println(bytes.length);

            System.out.println(buffer.getCharSequence(0, buffer.readableBytes(), Charset.forName("UTF-8")));

            System.out.println("arrayOffset ------------:" + buffer.arrayOffset());
            System.out.println("readerIndex ------------:" + buffer.readerIndex());
            System.out.println("writerIndex ------------:" + buffer.writerIndex());
            System.out.println("capacity    ------------:" + buffer.capacity());
            System.out.println("readableBytes ----------:" + buffer.readableBytes());


            for(int i= 0; i<buffer.readableBytes(); i++){
                System.out.println((char) buffer.getByte(i));
            }

        }

    }
}
