package com.hins.socket.sockettcpdemo01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(2000);

        System.out.println("服务端准备就绪");
        System.out.println("服务器信息: " + serverSocket.getInetAddress() + " P:" + serverSocket.getLocalPort());

        //等待客户端连接
        for(;;){
            //获取客户端连接
            Socket accept = serverSocket.accept();
            //客户端构建异步线程
            ClientHandler clientHandler = new ClientHandler(accept);
            //启动线程
            clientHandler.start();
        }



    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private boolean flag = true;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("新客户端连接: " + socket.getInetAddress() + " P:" + socket.getPort());

            PrintStream socketOutput = null;
            BufferedReader socketInput = null;
            try {
                //得到打印流, 用于数据输出; 服务器回送数据使用
                socketOutput = new PrintStream(socket.getOutputStream());
                //得到输入流，用于接收数据
                socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                do{
                    String clientMsg = socketInput.readLine();
                    if("bye".equalsIgnoreCase(clientMsg)){
                        socketOutput.println("bye");
                        flag = false;
                    }else{
                        System.out.println(clientMsg);
                        socketOutput.println("回送: " + clientMsg.length());
                    }
                }while(flag);

                socketOutput.close();
                socketInput.close();

            } catch (IOException e) {
                System.out.println("异常关闭");
            }finally {
                // 连接关闭
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("客户端已退出: " + socket.getInetAddress() + " :P " + socket.getPort());

        }
    }
}
