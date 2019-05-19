package com.inspur.netty.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: YANG
 * Date: 2019/4/27
 * Time: 19:27
 * Description: No Description
 */
public class NioTest13Client {

    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            InetSocketAddress address = new InetSocketAddress("localhost", 8899);
            socketChannel.connect(address);

            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            while (true) {
                selector.select();//阻塞, 等待客户端的 发起的各种事件!
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                if(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    //根据不同的事件, 进行不同的业务处理!
                    if(selectionKey.isConnectable()){
                        SocketChannel socketChannel1 = (SocketChannel) selectionKey.channel();
                        if(socketChannel1.isConnectionPending()){
                            socketChannel1.finishConnect();

                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                            writeBuffer.put((LocalDateTime.now() + " 连接成功").getBytes());
                            writeBuffer.flip();
                            socketChannel1.write(writeBuffer);

                            ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                            executorService.submit(() -> {
                                while(true){
                                    try {
                                        writeBuffer.clear();
                                        InputStreamReader reader = new InputStreamReader(System.in);
                                        BufferedReader bufferedReader = new BufferedReader(reader);

                                        String sendMessage = bufferedReader.readLine();
                                        writeBuffer.put(sendMessage.getBytes());
                                        writeBuffer.flip();
                                        socketChannel1.write(writeBuffer);

                                    } catch (Exception e) {

                                    }
                                }
                            });
                        }
                        socketChannel1.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()){
                        SocketChannel socketChannel1 = (SocketChannel) selectionKey.channel();

                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int count =  socketChannel1.read(readBuffer);
                        if(count > 0){
                            String receiveMessage = new String(readBuffer.array(),0 ,count);
                            System.out.println("receiveMessage ---------------------------:" + receiveMessage);
                        }
                    }
                }
                selectionKeys.clear();
            }
        } catch (Exception e) {

        }
    }
}
