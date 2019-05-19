package com.inspur.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * User: YANG
 * Date: 2019/4/27
 * Time: 21:16
 * Description: No Description
 */
public class NioTest14 {

    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio\\NioTest14_input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio\\NioTest14_output.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

        while(true){
            readBuffer.clear();
            int read = inputChannel.read(readBuffer);
            if(read < 0){
                break;
            }
            readBuffer.flip();

            Charset charset = Charset.forName("GBK");
            CharsetDecoder charsetDecoder = charset.newDecoder();
            CharsetEncoder charsetEncoder = charset.newEncoder();

            CharBuffer charBuffer = charsetDecoder.decode(readBuffer);
            ByteBuffer writeBuffer = charsetEncoder.encode(charBuffer);

            outputChannel.write(writeBuffer);
        }
        System.out.println("--------------------------------------------------------------");
        Charset.availableCharsets().forEach((key, value) -> {
            System.out.println(key + "----------------" + value);
        });
        System.out.println("--------------------------------------------------------------");

        fileInputStream.close();
        fileOutputStream.close();
    }
}
