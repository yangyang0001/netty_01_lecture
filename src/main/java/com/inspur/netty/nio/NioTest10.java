package com.inspur.netty.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * User: YANG
 * Date: 2019/4/26
 * Time: 20:03
 * Description: No Description
 * 关于文件锁的概念!本例不是特别重要这简单介绍一下!
 */
public class NioTest10 {

    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\study_workspace\\netty_01_lecture\\src\\main\\java\\com\\inspur\\netty\\nio\\NioTest09.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        FileLock fileLock = fileChannel.lock(0, 3, true);

        System.out.println("valid ----:" + fileLock.isValid());
        System.out.println("isShared -:" + fileLock.isShared());

        fileLock.release();
        randomAccessFile.close();
    }
}
