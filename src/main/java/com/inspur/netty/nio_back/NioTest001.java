package com.inspur.netty.nio_back;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * User: YANG
 * Date: 2019/5/3
 * Time: 22:07
 * Description: No Description
 */
public class NioTest001 {

    public static void main(String[] args) throws Exception{

        FileInputStream fileInputStream = new FileInputStream("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio_back\\NioTest001_input.txt");
        FileChannel inputChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio_back\\NioTest001_output.txt");
        FileChannel outputChannel = fileOutputStream.getChannel();

        //0 <= mark <= positon <= limit < capacity
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while(true){
            buffer.clear();

            int readCount = inputChannel.read(buffer);
            if (readCount == -1){
                break;
            }

            buffer.flip();

            buffer.compact();
            outputChannel.write(buffer);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}
