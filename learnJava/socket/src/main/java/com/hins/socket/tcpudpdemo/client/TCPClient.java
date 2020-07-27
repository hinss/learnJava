package com.hins.socket.tcpudpdemo.client;

import com.hins.socket.tcpudpdemo.client.model.ServerInfo;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

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
            //发送接收数据
            sendData(socket);
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

        InputStream responseInputStream = socket.getInputStream();
        BufferedReader responseReader = new BufferedReader(new InputStreamReader(responseInputStream));

        boolean flag = true;
        do {
            //阻塞方法  读取键盘一行
            String str = input.readLine();

            //发送到tcp server
            output.println(str);

            //获得回送
            String retrunMessage = responseReader.readLine();
            if("bye".equalsIgnoreCase(retrunMessage)){
                flag = false;
            }else{
                System.out.println(retrunMessage);
            }

        }while(flag);

        //资源释放
        input.close();
        output.close();
    }
}
