package com.hins.socket.tcpudpdemo.server;

import com.hins.socket.tcpudpdemo.constants.UDPConstants;
import com.hins.socket.tcpudpdemo.utils.ByteUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.util.UUID;

public class ServerProvider {

   private static Provider INSTANCE;

   public static void start(int tcpPort){
        stop();

        String sn = UUID.randomUUID().toString();
        Provider provider = new Provider(sn.getBytes(),tcpPort);
        provider.start();

        // 静态内部类的方式完成单例
        INSTANCE = provider;
   }

   public static void stop(){
       if(INSTANCE != null){
           INSTANCE.exit();
           INSTANCE = null;
       }
   }


    /**
     * 静态内部类实现单例
     * UDP 搜索服务提供者
     */
    private static class Provider extends Thread{

        //唯一的sn序列号
        private final byte[] sn;
        //tcp 连接端口
        private final int port;
        private boolean flag = false;
        private DatagramSocket ds = null;

        // 存储消息的Buffer
        final byte[] buffer = new byte[128];

        Provider(byte[] sn, int port) {
            this.sn = sn;
            this.port = port;
        }


        @Override
        public void run() {

            System.out.println("UDPProvider is started.");

            try {
                ds = new DatagramSocket(UDPConstants.PORT_SERVER);

                //构建接收数据用的package包
                DatagramPacket receivePacket = new DatagramPacket(buffer,buffer.length);

                while(!flag) {

                    //阻塞接收数据
                    ds.receive(receivePacket);

                    //解析数据包
                    String clientIp = receivePacket.getAddress().getHostAddress();
                    int clientPort = receivePacket.getPort();
                    int clientDataLength = receivePacket.getLength();
                    byte[] clientData = receivePacket.getData();

                    //校验
                    //符合条件的数据包必须大于 约定得到UDP头部长度 + 2字节的命令长度 + 4字节的客户端端口长度
                    boolean isValid = clientData.length >= UDPConstants.HEADER.length + 2 + 4
                            && ByteUtils.startsWith(clientData, UDPConstants.HEADER);

                    System.out.println("ServerProvider receive form ip:" + clientIp
                            + "\tport:" + clientPort + "\tdataValid:" + isValid);

                    if (!isValid) {
                        continue;
                    }

                    System.out.println("UDPProvider successed receive data package");

                    //解析data 得到command 与 回送端口
                    int index = UDPConstants.HEADER.length;
                    short cmd = (short) ((clientData[index++] << 8) | (clientData[index++] & 0xff));
                    int responsePort = (((clientData[index++]) << 24) |
                            ((clientData[index++] & 0xff) << 16) |
                            ((clientData[index++] & 0xff) << 8) |
                            ((clientData[index] & 0xff)));

                    if(cmd == 1 && responsePort > 0){
                        //构建一份回送数据
                        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
                        byteBuffer.put(UDPConstants.HEADER);
                        byteBuffer.putShort((short)2);
                        byteBuffer.putInt(port);
                        byteBuffer.put(sn);
                        int len = byteBuffer.position();
                        //构建一份回送的DatagramPackage
                        DatagramPacket responsePackage = new DatagramPacket(buffer,
                                len,
                                receivePacket.getAddress(),
                                receivePacket.getPort());

                        ds.send(responsePackage);
                        System.out.println("ServerProvider response to:" + clientIp + "\tport:" + responsePort + "\tdataLen:" + len);
                    }else{
                        System.out.println("ServerProvider receive cmd not support; cmd:" + cmd + "\tport:" + port);
                    }
                }

                System.out.println("ServerProvider has already closed!");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

        /**
         * 关闭datagramsocket流的方法
         */
        private void close(){
            if(ds != null){
                ds.close();
                ds = null;
            }
        }

        /**
         * 退出方法
         * 停止接收数据并且关闭流
         */
        void exit(){
            flag = true;
            close();
        }


    }

}
