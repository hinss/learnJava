package com.hins.socket.udpdemo02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class UDPSearcher {

    private static final int LISENER_PORT = 30000;

    public static void main(String[] args) throws IOException {

        System.out.println("UDPSearcher Started!");

        Listener listener = listen();
        sendBroadcast();

        //读取任意信息
        System.in.read();
        List<Device> devices = listener.getDevicesAndClose();

        for(Device device : devices){
            System.out.println("Device:" + device.toString());
        }

        //完成
        System.out.println("UDPSearcher Finished!");

    }

    private static Listener listen(){
        System.out.println("UDPSearcher listener Started.");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Listener listener = new Listener(LISENER_PORT, countDownLatch);
        listener.start();

        countDownLatch.countDown();
        return listener;
    }


    private static void sendBroadcast() throws IOException {

        System.out.println("UDPSearcher sendBroadcast Started");

        // 作为发送者 无需指定端口 由系统自动分配
        DatagramSocket ds = new DatagramSocket();

        // 构建发送实体
        String requestData = MessageCreator.buildWithPort(LISENER_PORT);
        byte[] buf = requestData.getBytes();
        DatagramPacket requestPacket = new DatagramPacket(buf,buf.length);
        requestPacket.setAddress(InetAddress.getByName("255.255.255.255"));
        requestPacket.setPort(20000);

        // 发送
        ds.send(requestPacket);

        System.out.println("UDPSearcher Finished.");
    }

    private static class Device {
        final int port;
        final String ip;
        final String sn;

        private Device(int port, String ip, String sn) {
            this.port = port;
            this.ip = ip;
            this.sn = sn;
        }

        @Override
        public String toString() {
            return "Device{" +
                    "port=" + port +
                    ", ip='" + ip + '\'' +
                    ", sn='" + sn + '\'' +
                    '}';
        }
    }

    private static class Listener extends Thread{

        private final int listenPort;
        private final CountDownLatch countDownLatch;
        private final List<Device> deviceList = new ArrayList<>();
        private boolean done = false;
        private DatagramSocket datagramSocket = null;

        public Listener(int listenPort, CountDownLatch countDownLatch) {
            this.listenPort = listenPort;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            //  通知已启动
            countDownLatch.countDown();
            try{
                //监听回送端口
                datagramSocket = new DatagramSocket(listenPort);

                while(!done){

                    // 构建接收实体
                    final byte[] buf2 = new byte[512];
                    DatagramPacket receivePacket = new DatagramPacket(buf2,buf2.length);

                    //接收
                    datagramSocket.receive(receivePacket);

                    //打印接收到的信息与发送者的信息
                    String ip = receivePacket.getAddress().getHostAddress();
                    int port = receivePacket.getPort();
                    int dataLength = receivePacket.getLength();
                    String receiveMsg = new String(receivePacket.getData(),0,dataLength);

                    System.out.println("UDPSeacher receive from ip:" + ip
                            + "\tport:" + port + "\tdata:" + receiveMsg);

                    String sn = MessageCreator.parseSN(receiveMsg);
                    if(sn != null){
                        Device device = new Device(port,ip,sn);
                        deviceList.add(device);
                    }
                }

            } catch (Exception e){

            } finally {
                close();
            }

            System.out.println("UDPSearcher listener finished.");
        }

        private void close(){

            if(datagramSocket != null){
                datagramSocket.close();
                datagramSocket = null;
            }

        }

        List<Device> getDevicesAndClose(){
            done = true;
            close();
            return deviceList;
        }

    }


}
