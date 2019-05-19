package com.inspur.netty.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * User: YANG
 * Date: 2019/4/28
 * Time: 23:18
 * Description: No Description
 */
public class NewIOClient {

    public static void main(String[] args) throws Exception{
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", 8899));
            socketChannel.configureBlocking(true);

            String fileName = "D:\\软件安装包\\mysql-connector-odbc-5.2.7-win32.msi";
            FileChannel fileChannel = new FileInputStream(fileName).getChannel();

            long startTime = System.currentTimeMillis();
            System.out.println(fileChannel.size());
            //下面这一行代码 直接 就完成了传递!
            long total = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
            System.out.println("发送总字节数 total:" + total + ",耗时毫秒数:" + (System.currentTimeMillis() - startTime));
            fileChannel.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
    }
}
