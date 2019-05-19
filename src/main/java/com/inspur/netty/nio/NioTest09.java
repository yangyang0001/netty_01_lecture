package com.inspur.netty.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * User: YANG
 * Date: 2019/4/26
 * Time: 19:54
 * Description: No Description
 * 本程序主要演示可以通过程序来修改 文件中的内容, 不用关注其他的
 * 但是展现出来的结果:   通过IDEA无法直接展现出来,可以通过UE打开查看
 */
public class NioTest09 {

    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio\\NioTest09.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        buffer.put(1, (byte) 'a');
        buffer.put(3, (byte) 'b');

        randomAccessFile.close();
    }
}
