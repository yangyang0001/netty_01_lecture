package com.inspur.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * User: YANG
 * Date: 2019/4/26
 * Time: 15:12
 * Description: No Description
 *
 * 采用 DirectByteBuffer来进行文件的复制操作!
 */
public class NioTest08 {

    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio\\niotest08_input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio\\niotest08_output.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer.getClass());

        while(true){
            buffer.clear();
            int count = inputChannel.read(buffer);
            System.out.println(count);
            if(count == -1){
                break;
            }
            buffer.flip();
            outputChannel.write(buffer);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }

}
