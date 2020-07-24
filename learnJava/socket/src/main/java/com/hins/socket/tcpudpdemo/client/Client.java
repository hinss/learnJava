package com.hins.socket.tcpudpdemo.client;

import com.hins.socket.tcpudpdemo.client.model.ServerInfo;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        ServerInfo serverInfo = ClientSearcher.searchServer(10000);

        System.out.println("Server:" + serverInfo);
    }
}
