package com.inspur.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * User: YANG
 * Date: 2019/4/26
 * Time: 23:06
 * Description: No Description
 */
public class MyNioTest02 {

    public static void main(String[] args) throws Exception{

        FileInputStream fileInputStream = new FileInputStream("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio\\MyNioTest02_input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio\\MyNioTest02_output.txt");
        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(10);

        while(true){
            int readLength = inputChannel.read(buffer);
            if(readLength == -1){
                break;
            }
            buffer.flip();
            outputChannel.write(buffer);
            buffer.clear();
        }
    }
}
