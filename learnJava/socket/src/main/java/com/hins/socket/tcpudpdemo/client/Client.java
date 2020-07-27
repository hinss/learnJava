package com.hins.socket.tcpudpdemo.client;

import com.hins.socket.tcpudpdemo.client.model.ServerInfo;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        ServerInfo serverInfo = ClientSearcher.searchServer(10000);
        System.out.println("Server:" + serverInfo);

        if(serverInfo != null){
            //客户端创建TCP连接
            try {
                TCPClient.linkWith(serverInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
