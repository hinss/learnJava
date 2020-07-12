package com.hins.socket.udpdemo01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP 提供者,用于提供服务
 */
public class UDPProvider {

    public static void main(String[] args) throws IOException {

        System.out.println("UDPProvider Started");

        // 作为接收者，指定一个端口用于数据接收
        DatagramSocket ds = new DatagramSocket(20000);

        // 构建接收实体
        final byte[] buf = new byte[512];
        DatagramPacket receivePacket = new DatagramPacket(buf,buf.length);

        // 接收
        ds.receive(receivePacket);

        // 打印接收到的信息与发送者的信息
        // 发送者的IP地址
        String ip = receivePacket.getAddress().getHostAddress();
        int port = receivePacket.getPort();
        int dataLength = receivePacket.getLength();
        String receiveMsg = new String(receivePacket.getData(),0,dataLength);

        System.out.println("UDPProvider receive from ip:" + ip
        + "\tport:" + port + "\tdata:" + receiveMsg);

        // 构建一份回送数据
        String responseData = "一份回送的数据:" + receiveMsg.length();
        DatagramPacket responsePacket = new DatagramPacket
                (responseData.getBytes()
                        ,responseData.getBytes().length
                        ,receivePacket.getAddress()
                        ,receivePacket.getPort());

        // 直接根据发送回送一份数据
        ds.send(responsePacket);

        //完成
        System.out.println("UDPProvider Finished.");
        ds.close();
    }
}
