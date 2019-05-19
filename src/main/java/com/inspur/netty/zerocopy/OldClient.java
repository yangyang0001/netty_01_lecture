package com.inspur.netty.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * User: YANG
 * Date: 2019/4/28
 * Time: 22:20
 * Description: No Description
 */
public class OldClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8899);

        String fileName = "F:\\BaiduYunDownload\\深入JVM内核\\深入JVM内核\\[www.javazx.com]5.GC参数.mp4";
        InputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];

        long readCount = 0;
        long total = 0;

        long startTime = System.currentTimeMillis();

        while((readCount = inputStream.read(buffer)) >= 0){
            total += readCount;
            dataOutputStream.write(buffer);
        }

        System.out.println("写出总字节数 total:" + total + ",耗时毫秒数:" + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        socket.close();
        inputStream.close();

    }
}
