package com.inspur.netty.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * User: YANG
 * Date: 2019/4/25
 * Time: 21:53
 * Description: No Description
 */
public class NioTest02 {

    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio\\NioTest2.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fileChannel.read(buffer);
        //一定要调用 flip()
        buffer.flip();

        while(buffer.remaining() > 0){
            byte b = buffer.get();
            System.out.println("char is :" + (char) b);
        }

        fileInputStream.close();

    }
}
