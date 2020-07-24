package com.hins.socket.tcpudpdemo.client;

import com.hins.socket.tcpudpdemo.client.model.ServerInfo;
import com.hins.socket.tcpudpdemo.constants.UDPConstants;
import com.hins.socket.tcpudpdemo.utils.ByteUtils;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ClientSearcher {

    private static final int LISTEN_PORT = UDPConstants.PORT_SERVER;


    public static ServerInfo searchServer(long timeOut) throws InterruptedException {

        System.out.println("UDPSearcher started!");

        CountDownLatch receiveCountDownLatch = null;
        Listener listener = null;
        try {
            receiveCountDownLatch = new CountDownLatch(1);
            //开始监听服务端回送
            listener = listen(receiveCountDownLatch,timeOut);
            //发送广播搜索服务端UDP端口
            sendBroadcast();

            //等待监听器收到数据
            receiveCountDownLatch.await(timeOut,TimeUnit.MILLISECONDS);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        // 完成
        System.out.println("UDPSearcher Finished.");
        if (listener == null) {
            return null;
        }
        List<ServerInfo> devices = listener.getServerAndClose();
        if (devices.size() > 0) {
            return devices.get(0);
        }
        return null;
    }

    private static void sendBroadcast() throws IOException {
        System.out.println("UDPSearcher sendBroadcast started.");

        // 作为搜索方，让系统自动分配端口
        DatagramSocket ds = new DatagramSocket();

        // 构建一份请求数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        // 头部
        byteBuffer.put(UDPConstants.HEADER);
        // CMD命名
        byteBuffer.putShort((short) 1);
        // 回送端口信息
        byteBuffer.putInt(LISTEN_PORT);
        // 直接构建packet
        DatagramPacket requestPacket = new DatagramPacket(byteBuffer.array(),
                byteBuffer.position() + 1);
        // 广播地址
        requestPacket.setAddress(InetAddress.getByName("255.255.255.255"));
        // 设置服务器端口
        requestPacket.setPort(UDPConstants.PORT_SERVER);

        // 发送
        ds.send(requestPacket);
        ds.close();

        // 完成
        System.out.println("UDPSearcher sendBroadcast finished.");
    }

    public static Listener listen(CountDownLatch receiveCountDownLatch,long timeOut) throws InterruptedException {

        CountDownLatch startCountDownLatch = new CountDownLatch(1);

        Listener listener = new Listener(UDPConstants.PORT_CLIENT_RESPONSE,startCountDownLatch,receiveCountDownLatch);
        listener.start();

        startCountDownLatch.await(timeOut, TimeUnit.MILLISECONDS);

        return listener;
    }


    /**
     * 创建DatagramSocket监听UDP端口
     * 等待服务端回送数据
     */
    private static class Listener extends Thread{

        private final int listtenPort;
        private final CountDownLatch startCountDownLatch;
        private final CountDownLatch receiveCountDownLatch;
        private final byte[] buffer = new byte[128];
        private boolean flag = false;
        private DatagramSocket clientDs;
        private final List<ServerInfo> serverInfoList = new ArrayList<>();
        private final int minLen = UDPConstants.HEADER.length + 2 + 4;




        private Listener(int listtenPort, CountDownLatch startCountDownLatch, CountDownLatch receiveCountDownLatch) {
            this.listtenPort = listtenPort;
            this.startCountDownLatch = startCountDownLatch;
            this.receiveCountDownLatch = receiveCountDownLatch;
        }

        @Override
        public void run() {

            System.out.println("UDPSearcher started to listen listtenPort:" + listtenPort);

            try {
                //创建UDP socket ip为本地ip 端口为指定 客户端的UDP 端口
                clientDs = new DatagramSocket(listtenPort);

                //通知已启动监听
                startCountDownLatch.countDown();

                DatagramPacket datagramPacket = new DatagramPacket(buffer,0,buffer.length);

                while(!flag){
                    //阻塞接收数据
                    clientDs.receive(datagramPacket);

                    //打印接收到的信息和发送者的信息
                    String ip = datagramPacket.getAddress().getHostAddress();
                    int port = datagramPacket.getPort();
                    int dataLength = datagramPacket.getLength();
                    byte[] data = datagramPacket.getData();

                    //校验数据头部信息
                    boolean isValid = dataLength >= minLen
                            && ByteUtils.startsWith(data,UDPConstants.HEADER);

                    System.out.println("UDPSearcher receive form ip:" + ip
                            + "\tport:" + port + "\tdataValid:" + isValid);

                    if(!isValid){
                        continue;
                    }

                    //组装buteBuffer读取数据  跳过header校验头部
                    ByteBuffer byteBuffer = ByteBuffer.wrap(buffer, UDPConstants.HEADER.length, dataLength);
                    final short cmd = byteBuffer.getShort();
                    final int serverPort = byteBuffer.getInt();
                    if (cmd != 2 || serverPort <= 0) {
                        System.out.println("UDPSearcher receive cmd:" + cmd + "\tserverPort:" + serverPort);
                        continue;
                    }

                    String sn = new String(buffer, minLen, dataLength - minLen);
                    ServerInfo info = new ServerInfo(serverPort, ip, sn);
                    serverInfoList.add(info);

                    receiveCountDownLatch.countDown();

                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

        private void close(){
            if(clientDs != null){
                clientDs.close();
                clientDs = null;
            }
        }

        List<ServerInfo> getServerAndClose() {
            flag = true;
            close();
            return serverInfoList;
        }

    }




}
