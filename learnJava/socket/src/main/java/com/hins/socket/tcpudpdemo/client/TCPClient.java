package com.hins.socket.tcpudpdemo.client;

import com.hins.socket.tcpudpdemo.client.model.ServerInfo;
import com.hins.socket.tcpudpdemo.server.handler.ClientHandler;
import com.hins.socket.tcpudpdemo.utils.CloseUtils;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPClient {

    public static void linkWith(ServerInfo info) throws IOException {

        //要建立TCP连接 需要创建Socket对象
        Socket socket = new Socket();
        //超时时间
        socket.setSoTimeout(3000);

        //连接TCPServer
        socket.connect(new InetSocketAddress(Inet4Address.getByName(info.getAddress()),info.getPort()),3000);

        System.out.println("已发起服务器连接，并进入后续流程～");
        System.out.println("客户端信息：" + socket.getLocalAddress() + " P:" + socket.getLocalPort());
        System.out.println("服务器信息：" + socket.getInetAddress() + " P:" + socket.getPort());


        try {
            ReadHandler readHandler = new ReadHandler(socket.getInputStream());
            readHandler.start();

            //发送接收数据
            sendData(socket);

            // 退出操作
            readHandler.exit();
        } catch (Exception e) {
            System.out.println("异常关闭");
        }

        //关闭释放资源
        socket.close();
        System.out.println("客户端已退出");

    }

    private static void sendData(Socket socket) throws IOException {

        // 获得键盘输入流
        InputStream inputStream = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));

        //获得客户端socket的输入输出流
        OutputStream outputStream = socket.getOutputStream();
        PrintStream output = new PrintStream(outputStream);

        do {
            //阻塞方法  读取键盘一行
            String str = input.readLine();
            output.println(str);

            if("00bye00".equalsIgnoreCase(str)){
                break;
            }

        }while(true);

        //资源释放
        output.close();
    }


    public static class ReadHandler extends Thread{

        private boolean done = false;
        private final InputStream inputStream;

        ReadHandler(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {

            try {
                // BufferedReader 用于读取客户端的数据
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(this.inputStream));

                do {
                    // 客户端拿到一条数据(阻塞方法)
                    String str;
                    try {
                        str = socketInput.readLine();
                        if(str == null){
                            System.out.println("连接已关闭,无法读取数据");
                            break;
                        }
                    } catch (SocketTimeoutException e) {
                        //可能会出现读取服务端的消息超时异常，
                        //如果异常则继续等待
                        continue;
                    }

                    //输出消息
                    System.out.println(str);

                } while (!done);
            } catch (IOException e) {
                if(!done) {
                    System.out.println("连接异常断开" + e.getMessage());
                }
            } finally {
                CloseUtils.close(inputStream);
            }

        }

        void exit(){
            done = true;
            CloseUtils.close(inputStream);
        }

    }

}
