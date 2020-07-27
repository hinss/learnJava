package com.hins.socket.tcpudpdemo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    //TCP port
    private final int port;
    private ClientListener clientListener;

    public TCPServer(int port) {
        this.port = port;
    }

    /**
     * 服务器 启动TCP Server会
     */
    public boolean start(){

        try {
            ClientListener listener = new ClientListener(port);
            clientListener = listener;
            listener.start();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void stop(){

        if(clientListener != null){
            clientListener.exit();
            clientListener = null;
        }

    }


    private static class ClientListener extends Thread{
        private ServerSocket serverSocket;
        private boolean done;

        public ClientListener(int tcpPort) throws IOException {

            serverSocket = new ServerSocket(tcpPort);
            System.out.println("服务器信息：" + serverSocket.getInetAddress() + " P:" + serverSocket.getLocalPort());
        }


        @Override
        public void run() {

            System.out.println("TCPServer has started...");

            //server socket负责监听客户端的连接，所以要用一个死循环
            // 只有Server发出停止命令才会停止对客户端TCP连接的监听。
            do {
                //等待客户端连接
                Socket client;
                try {
                    //阻塞等待连接
                    client = serverSocket.accept();

                } catch (IOException e) {
                    continue;
                }
                //得到连接后用单独的线程处理
                ClientHandler clientHandler = new ClientHandler(client);
                //启动线程
                clientHandler.start();

            }while(!done);

            System.out.println("TCPServer is closed...");

        }

        void exit() {
            done = true;
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 客户端处理消息
     * 每次TCP Server监听到一个客户端连接都会创建一个线程去处理数据
     */
    private static class ClientHandler extends Thread{

        private Socket socket;
        private boolean flag = true;

        ClientHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {

            System.out.println("新客户端连接：" + socket.getInetAddress() +
                    " P:" + socket.getPort());

            //从客户端的socket中获取输入 输出流
            try {
                //PrintStream流用于回送数据
                PrintStream socketOutput = new PrintStream(socket.getOutputStream());

                // BufferedReader 用于读取客户端的数据
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                do {
                    // 客户端拿到一条数据(阻塞方法)
                    String str = socketInput.readLine();
                    if ("bye".equalsIgnoreCase(str)) {
                        flag = false;
                        // 回送
                        socketOutput.println("bye");
                    } else {
                        // 打印到屏幕。并回送数据长度
                        System.out.println(str);
                        socketOutput.println("回送：" + str.length());
                    }

                } while (flag);

                socketInput.close();
                socketOutput.close();

            } catch (IOException e) {
                System.out.println("连接异常断开");
            } finally {
                // 连接关闭
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("客户端已退出：" + socket.getInetAddress() +
                    " P:" + socket.getPort());

        }
    }

}
