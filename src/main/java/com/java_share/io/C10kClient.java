package com.java_share.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author yz
 * @date 2020-08-21 23:02
 * <p></p>
 **/
public class C10kClient {

    public static void main(String[] args) throws IOException {

        InetSocketAddress serverAddr = new InetSocketAddress("127.0.0.1", 9091);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            SocketChannel client = SocketChannel.open();
            while (true) {
                int port = (int) Math.random() * 60000;
                try {
                    client.bind(new InetSocketAddress("127.0.0.1", port));
                }catch (Exception e) {
                    continue;
                }
                break;
            }
            client.connect(serverAddr);
        }
        long end = System.currentTimeMillis();
        System.out.println("连接耗时"+(end-start));
    }
}
