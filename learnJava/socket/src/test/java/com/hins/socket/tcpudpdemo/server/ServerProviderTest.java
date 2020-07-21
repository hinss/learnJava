package com.hins.socket.tcpudpdemo.server;

import org.junit.Test;

import java.nio.ByteBuffer;

public class ServerProviderTest {


    @Test
    public void testByteBuffer(){
        final byte[] buffer = new byte[128];

        buffer[0] = 1;
        buffer[1] = 2;
        buffer[2] = 3;
        System.out.println("对原始byte[]数组写入1 2 3:");
        for(byte b : buffer){
            if(b != 0){
                System.out.println(b);
            }
        }

        System.out.println("模拟ByteBuffer开始操作byte[]数组,写入两个数字4 5");
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);

        byteBuffer.put((byte)4);
        byteBuffer.put((byte)5);

        for(byte b : byteBuffer.array()){

            if(b != 0){
                System.out.println(b);
            }
        }
        System.out.println("ByteBuffer的position:" + byteBuffer.position());

        System.out.println("模拟客户端收到该byte[] 然后用ByteBuffer通过position来操作数据:");
        byte[] data = byteBuffer.array();
        int position = byteBuffer.position();

        ByteBuffer clientByteBuffer = ByteBuffer.wrap(data);

        for(int i = 0; i < position; i++){
            System.out.println(clientByteBuffer.get(i));
        }
    }
}
