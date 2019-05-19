package com.inspur.netty.reactor;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *  1. SetUp
 */
public class Reactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocketChannel;

    Reactor(int port) throws Exception {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);

        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
//        selectionKey.attachment(new MyAcceptor());
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                selector.select();
                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keySet.iterator();
                while(iterator.hasNext()){
                    dispatch(iterator.next());
                    iterator.remove();
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
    }

    private void dispatch(SelectionKey selectionKey) {
        Runnable r = (Runnable) (selectionKey.attachment());
        if(r != null){
            r.run();
        }
    }

    class MyAcceptor implements Runnable{
        @Override
        public void run() {
            try {

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {

            }

        }
    }

}
