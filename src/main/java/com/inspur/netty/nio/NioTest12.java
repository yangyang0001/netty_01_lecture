package com.inspur.netty.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * User: YANG
 * Date: 2019/4/27
 * Time: 12:12
 * Description: No Description
 */
public class NioTest12 {

    public static void main(String[] args) throws Exception{

        int[] ports = new int[5];
        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();

        for(int i = 0; i<ports.length; i++){
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();//打开一个 SelectableChannel
            serverSocketChannel.configureBlocking(false);   //配置 非阻塞
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            serverSocket.bind(address);

            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口 ----------------------:" + ports[i]);
        }

        while(true){
            int number = selector.select();     //阻塞的!这句话非常重要,表示 服务端正在等待着
            System.out.println("number -------------:" + number);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                if(selectionKey.isAcceptable()){
                    System.out.println("1111-----------------------------------------------");
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept(); //真正的链接的操作!
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("1111 remove before selector.selectedKeys().size :" + selector.selectedKeys().size());
                    iterator.remove();//非常重要
                    System.out.println("1111 remove after  selector.selectedKeys().size :" + selector.selectedKeys().size());
                } else if(selectionKey.isReadable()){
                    System.out.println("2222-----------------------------------------------start");
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    socketChannel.configureBlocking(false);

                    while(true){
                        ByteBuffer buffer = ByteBuffer.allocate(512);   //最好拿到 while 内部来,因为连接的是不同的客户端! 另外 buffer 不是线程安全的!
                        buffer.clear();
                        int read = socketChannel.read(buffer);
                        if(read <= 0){  //这里话和前面不同的是 这里是 socketChannel 是个长连接, 只要一直连接着就 不会出现 read = -1的情况,充其量是0
                                        //前面用的是 FileChannel 从 FileInputStream 和 FileOutputStream 中获取的值!
                            break;
                        }
                        buffer.flip();
                        socketChannel.write(buffer);
                        System.out.println("2222----------------------------------------------- while");
                    }
                    System.out.println("2222 remove before selector.selectedKeys().size :" + selector.selectedKeys().size());
                    iterator.remove();//非常重要
                    System.out.println("2222 remove after  selector.selectedKeys().size :" + selector.selectedKeys().size());
                }
            }
        }
    }
}
