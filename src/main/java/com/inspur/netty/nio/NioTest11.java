package com.inspur.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 关于Buffer的Scattering 和 Gathering
 * Scattering:将来自一个Channel中的数据,读入到不同的Buffer中
 * Gathering :将来自一个Buffer中的数据,传递到不同的Channel中
 */
public class NioTest11 {

    public static void main(String[] args) throws Exception {
        // Scattering : 同一个 Channel -> 不同的 Buffer 中
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        long messageLength = 2 + 3 + 4;

        while (true) {
            long readLength = 0;
            while (readLength < messageLength) {
                long r = socketChannel.read(buffers);
                readLength += r;
                Arrays.asList(buffers).stream().forEach(buffer ->
                        System.out.println("read --> position:" + buffer.position() + ",limit:" + buffer.limit() + ",capacity:" + buffer.capacity()));
                System.out.println("readLength:" + readLength);
            }

            //buffers 中的每个 buffer 进行 flip()
            Arrays.asList(buffers).stream().forEach(buffer -> {
                buffer.flip();
            });

            long writeLength = 0;
            while (writeLength < messageLength) {
                writeLength += socketChannel.write(buffers);
                Arrays.asList(buffers).stream().forEach(buffer ->
                        System.out.println("write --> position:" + buffer.position() + ",limit:" + buffer.limit() + ",capacity:" + buffer.capacity()));
            }

            Arrays.asList(buffers).stream().forEach(buffer -> {
                buffer.clear();
            });

            System.out.println("readLength:" + readLength + ",writeLength:" + writeLength);


        }

    }
}
