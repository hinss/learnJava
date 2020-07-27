package com.hins.socket.tcpudpdemo.server;

import com.hins.socket.tcpudpdemo.constants.UDPConstants;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UDPProviderTest {


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

    @Test
    public void testByteBuffer2(){

        final byte[] buffer = new byte[128];

        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);

        byte[] header = UDPConstants.HEADER;
        short command = 2;
        int port = 21812;
        String sn = UUID.randomUUID().toString();

        System.out.println("===模拟服务端写入数据到bytebuffer中===");

        byteBuffer.put(header);
        byteBuffer.putShort(command);
        byteBuffer.putInt(port);
        byteBuffer.put(sn.getBytes());

        int position = byteBuffer.position();
        System.out.println("postion: "+position);

        System.out.println("===模拟客户端从bytebuffer中读取数据===");

        ByteBuffer clientByteBuffer = ByteBuffer.wrap(buffer);

        byte b = clientByteBuffer.get(UDPConstants.HEADER.length);


    }
}
