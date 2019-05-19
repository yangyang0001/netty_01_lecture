package com.inspur.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * User: YANG
 * Date: 2019/4/26
 * Time: 13:03
 * Description: No Description
 */
public class NioTest04 {

    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio\\input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio\\output.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(10);

        while(true){
            /**
             * 如果注释掉当前的行，
             * first         position: 0   limit: 10   capacity: 10
             *
             * read before   position: 10  limit: 10   capacity: 10
             * read  after   position: 10  limit: 10   capacity: 10     read=0
             * flip          position: 0   limit: 10   capacity: 10
             * write before  position: 0   limit: 10   capacity: 10
             * write after   postioin: 10  limit: 10   capacity: 10
             *
             * 会发生死循环!
             */
//            buffer.clear();
            System.out.println("read before position:" + buffer.position() + ",limit:" + buffer.limit() + ",capacity:" + buffer.capacity());
            int read = inputChannel.read(buffer);
            System.out.println("read after  position:" + buffer.position() + ",limit:" + buffer.limit() + ",capacity:" + buffer.capacity());
            System.out.println("read :" + read);
            if(read == -1){
                break;
            }
            buffer.flip();
            System.out.println("write before position:" + buffer.position() + ",limit:" + buffer.limit() + ",capacity:" + buffer.capacity());
            outputChannel.write(buffer);
            System.out.println("write after  position:" + buffer.position() + ",limit:" + buffer.limit() + ",capacity:" + buffer.capacity());

            System.out.println("--------------------------------------------------------------------------------------");
        }

        fileInputStream.close();
        fileOutputStream.flush();
        fileInputStream.close();

    }
}
