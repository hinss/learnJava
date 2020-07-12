package com.hins.socket.udpdemo01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSearcher {

    public static void main(String[] args) throws IOException {

        System.out.println("UDPSearcher Started");

        // 作为发送者 无需指定端口 由系统自动分配
        DatagramSocket ds = new DatagramSocket();

        // 构建发送实体
        String requestData = "Hello world!";
        byte[] buf = requestData.getBytes();
        DatagramPacket requestPacket = new DatagramPacket(buf,buf.length, InetAddress.getLocalHost(),20000);

        // 发送
        ds.send(requestPacket);

        // 构建接收实体
        final byte[] buf2 = new byte[512];
        DatagramPacket receivePacket = new DatagramPacket(buf2,buf2.length);

        //接收
        ds.receive(receivePacket);

        //打印接收到的信息与发送者的信息
        String ip = receivePacket.getAddress().getHostAddress();
        int port = receivePacket.getPort();
        int dataLength = receivePacket.getLength();
        String receiveMsg = new String(receivePacket.getData(),0,dataLength);

        System.out.println("UDPSeacher receive from ip:" + ip
                + "\tport:" + port + "\tdata:" + receiveMsg);

        System.out.println("UDPSearcher Finished.");
        ds.close();


    }
}
