package com.hins.socket.udpdemo02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.UUID;

/**
 * UDP 实现广播 -- 提供者
 */
public class UDPProvider {

    public static void main(String[] args) throws IOException {

        String sn = UUID.randomUUID().toString();
        Provider provider = new Provider(sn);
        provider.start();

        // 模拟发送停止信号
        System.in.read();
        provider.exit();
    }

    private static class Provider extends Thread{

        private final String sn;
        private boolean flag = false;
        private DatagramSocket datagramSocket;



        public Provider(String sn) {
            this.sn = sn;
        }

        @Override
        public void run() {

            System.out.println("UDPProvider Started");

            try {
                // 作为接收者，指定一个端口用于数据接收
                datagramSocket = new DatagramSocket(20000);

                while(!flag){
                    // 构建接收实体
                    final byte[] buf = new byte[512];
                    DatagramPacket receivePacket = new DatagramPacket(buf,buf.length);

                    // 接收
                    datagramSocket.receive(receivePacket);

                    // 打印接收到的信息与发送者的信息
                    // 发送者的IP地址
                    String ip = receivePacket.getAddress().getHostAddress();
                    int port = receivePacket.getPort();
                    int dataLength = receivePacket.getLength();
                    String receiveMsg = new String(receivePacket.getData(),0,dataLength);

                    System.out.println("UDPProvider receive from ip:" + ip
                            + "\tport:" + port + "\tdata:" + receiveMsg);

                    //解析端口号
                    int responsePort = MessageCreator.parsePort(receiveMsg);

                    // 构建一份回送数据
                    String responseData = MessageCreator.buildWithSN(sn);
                    DatagramPacket responsePacket = new DatagramPacket
                            (responseData.getBytes()
                                    ,responseData.getBytes().length
                                    ,receivePacket.getAddress()
                                    ,responsePort);

                    // 直接根据发送回送一份数据
                    datagramSocket.send(responsePacket);
                }

            } catch (IOException ignored) {

            } finally {
                close();
            }

            //完成
            System.out.println("UDPProvider Finished.");
            close();
        }


        private void close(){

            if(datagramSocket != null){
                datagramSocket.close();
                datagramSocket = null;
            }
        }

        private void exit() throws IOException{

            flag = true;
            datagramSocket.close();
        }
    }

}
