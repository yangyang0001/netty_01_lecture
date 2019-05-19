package com.inspur.netty.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * User: YANG
 * Date: 2019/4/25
 * Time: 22:00
 * Description: No Description
 */
public class NioTest03 {

    public static void main(String[] args) throws Exception{

        FileOutputStream fileOutputStream = new FileOutputStream("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio\\NioTest3.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] data = "hello world niotest3".getBytes();

        for(byte b : data){
            buffer.put(b);
        }

        buffer.flip();
        fileChannel.write(buffer);

        fileOutputStream.close();

    }
}
