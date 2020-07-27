package com.hins.socket.tcpudpdemo.server;

import com.hins.socket.tcpudpdemo.constants.TCPConstants;

public class Server {

    public static void main(String[] args) {

        TCPServer tcpServer = new TCPServer(TCPConstants.PORT_SERVER);
        boolean isSucceed = tcpServer.start();
        if(!isSucceed){
            System.out.println("Start TCP server failed!");
            return ;
        }

        UDPProvider.start(TCPConstants.PORT_SERVER);
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("TCPServer 关闭");
        }

        UDPProvider.stop();
        tcpServer.stop();

    }
}
