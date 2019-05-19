package com.inspur.netty.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * User: YANG
 * Date: 2019/4/28
 * Time: 22:44
 * Description: No Description
 */
public class NewIOServer {

    public static void main(String[] args) throws Exception{
        InetSocketAddress address  = new InetSocketAddress(8899);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true); //这句话一定要 在 bind(address)之前

        serverSocketChannel.bind(address);

        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);

        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);

            int readCount = 0;
            while(readCount != -1){
                try {
                    readCount = socketChannel.read(buffer);
                    buffer.rewind();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
