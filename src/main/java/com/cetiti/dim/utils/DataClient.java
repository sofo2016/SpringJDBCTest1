package com.cetiti.dim.utils;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class DataClient implements Runnable {

    private AsynchronousSocketChannel channel;

    public DataClient() throws IOException {
        channel = AsynchronousSocketChannel.open();
    }

    public void connect() {
        channel.connect(new InetSocketAddress(RetCode.INPUTDATASERVERIP, 8379));
    }

    public void write(String data) {
        try {
            channel.write(ByteBuffer.wrap(data.getBytes())).get();
            read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            channel.read(buffer).get();
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            String data = new String(bytes, "UTF-8").trim();
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {

        }
    }

    public static void dataInput(Object obj) {
        try {
            DataClient c1 = new DataClient();
            c1.connect();
            new Thread(c1).start();
            Thread.sleep(50);
            c1.write(JSON.toJSONString(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
