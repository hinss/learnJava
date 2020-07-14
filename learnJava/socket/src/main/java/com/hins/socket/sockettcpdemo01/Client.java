package com.hins.socket.sockettcpdemo01;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket();
        //设置获取流的超时时间
        socket.setSoTimeout(3000);

        //与服务端socket建立连接，超时时间为3000ms
        socket.connect(new InetSocketAddress(InetAddress.getLocalHost(),2000), 3000);

        System.out.println("已发起服务器连接, 并进入后续流程...");
        System.out.println("客户端信息: " + socket.getLocalAddress() + " P:" + socket.getLocalPort());
        System.out.println("服务端信息: " + socket.getInetAddress() + " P:" + socket.getPort());


        try {
            requestAndResponse(socket);
        } catch (IOException e) {
            System.out.println("异常关闭");
        }

        socket.close();

    }

    private static void requestAndResponse(Socket client) throws IOException {

        //获取键盘输入流
        InputStream in = System.in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        //得到Socket输出流，并转换成打印流
        OutputStream outputStream = client.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        //得到Socket 输入流
        InputStream inputStream = client.getInputStream();
        BufferedReader socketBufferReader = new BufferedReader(new InputStreamReader(inputStream));

        boolean flag = true;

        do {
            //从键盘读取一行
            String msg = bufferedReader.readLine();

            //发送到服务器
            printStream.println(msg);

            //从服务器读取一行
            String echo = socketBufferReader.readLine();
            if ("bye".equalsIgnoreCase(echo)) {
                //退出
                flag = false;
            }else{
                //直接打印服务器的返回
                System.out.println(echo);
            }
        }while(flag);

        //资源释放
        printStream.close();
        socketBufferReader.close();




    }

}
