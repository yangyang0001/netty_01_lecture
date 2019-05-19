package com.inspur.netty.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * User: YANG
 * Date: 2019/4/28
 * Time: 22:12
 * Description: No Description
 */
public class OldServer {

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8899);

        while(true){
            Socket socket = serverSocket.accept();      //阻塞的方法
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try {
                byte[] buffer = new byte[4096];

                while(true){
                    int readCount = dataInputStream.read(buffer, 0, buffer.length);
                    if(readCount == -1){
                        break;
                    }
                }
            } catch (Exception e) {

            }
        }
    }

}
