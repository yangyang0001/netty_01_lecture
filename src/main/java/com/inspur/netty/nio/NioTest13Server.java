package com.inspur.netty.nio;

import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

/**
 * User: YANG
 * Date: 2019/4/27
 * Time: 17:11
 * Description: No Description
 * 创建一个 Server 单线程的Server   多个Client端
 */
public class NioTest13Server {

    private static Map<String, SocketChannel> clientMap = new HashMap<String, SocketChannel>();

    public static void main(String[] args) throws Exception{
        //下面的5行代码, NIO中的 Socket编程 是样板式的代码!
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocket.bind(address);

        Selector selector = Selector.open();
        //一旦Channel 注册给 selector 上的时候
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //进行相应的事件处理,服务器端就是进行一个死循环,死循环中进行相应的事件处理!
        while(true){
            int number = selector.select(); //阻塞 监听事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();//获取事件集合
            Iterator<SelectionKey> iterator = selectionKeys.iterator();//获取事件集合的迭代器!

            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                //通过获取的事件,判断事件是哪一种事件,根据不同的事件来处理不同的业务!
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();//仅仅是中转的过程!
                    //获取真正的socketChannel来 进行和客户端连接!
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    //服务端保存客户端链接的 SocketChannel 信息!
                    clientMap.put(UUID.randomUUID().toString(), socketChannel);
                    iterator.remove();
                } else if(selectionKey.isReadable()){

                    SocketChannel currentSocketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    currentSocketChannel.read(readBuffer);

                    readBuffer.flip();
                    //将 字节数组中 socetChannel传过来的数据转换成 数据!
                    Charset charset = CharsetUtil.UTF_8;
                    String currentChannelReadMessage = String.valueOf(charset.decode(readBuffer).array());
                    System.out.println("currentChannelReadMessage ---------:" + currentChannelReadMessage);

                    //写入到各个客户端的SocketChannel中去!
                    clientMap.forEach((String key, SocketChannel value) -> {
                        if(value == currentSocketChannel){
                            //不做任何事情!
                        } else {
                            String currentClientMapKey = "";
                            for(Map.Entry<String, SocketChannel> entry : clientMap.entrySet()){
                                if(currentSocketChannel == entry.getValue()){
                                    currentClientMapKey = entry.getKey();
                                }
                            }
                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            try {
                                writeBuffer.put(("[" + currentClientMapKey + "]---->" + currentChannelReadMessage).getBytes());
                                writeBuffer.flip();
                                value.write(writeBuffer);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    iterator.remove();
                }
            }
        }
    }
}
