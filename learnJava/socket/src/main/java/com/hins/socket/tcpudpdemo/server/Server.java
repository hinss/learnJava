package com.hins.socket.tcpudpdemo.server;

import com.hins.socket.tcpudpdemo.constants.TCPConstants;

public class Server {

    public static void main(String[] args) {

        ServerProvider.start(TCPConstants.PORT_SERVER);


        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ServerProvider.stop();




    }
}
