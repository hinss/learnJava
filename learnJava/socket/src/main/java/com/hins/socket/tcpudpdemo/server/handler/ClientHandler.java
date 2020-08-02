package com.hins.socket.tcpudpdemo.server.handler;

import com.hins.socket.tcpudpdemo.utils.CloseUtils;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ClientHandler {


    private final Socket socket;
    private final ClientReadHandler clientReadHandler;
    private final ClientWriteHandler clientWriteHandler;
    private final CloseClientHandlerCallable closeClientHandlerCallable;

    public ClientHandler(Socket socket, CloseClientHandlerCallable closeClientHandlerCallable) throws IOException {
        this.socket = socket;
        this.clientReadHandler = new ClientReadHandler(socket.getInputStream());
        this.clientWriteHandler = new ClientWriteHandler(socket.getOutputStream());
        this.closeClientHandlerCallable = closeClientHandlerCallable;

        System.out.println("新客户端连接：" + socket.getInetAddress() +
                " P:" + socket.getPort());
    }

    public void send(String str) {

        clientWriteHandler.send(str);
    }

    public void readToPrint(){
        clientReadHandler.start();
    }

    public void exit(){
        clientReadHandler.exit();
        clientWriteHandler.exit();
        CloseUtils.close(socket);

        System.out.println("客户端已退出:" + socket.getInetAddress() + "P:" + socket.getPort());
    }

    private void exitBySelf() {
        exit();
        closeClientHandlerCallable.closeClientHandler(this);
    }

    public interface CloseClientHandlerCallable{

        void closeClientHandler(ClientHandler clientHandler);
    }


    /**
     * 读取数据线程
     */
    public class ClientReadHandler extends Thread{

        private boolean done = false;
        private final InputStream inputStream;

        ClientReadHandler(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {

            try {
                // BufferedReader 用于读取客户端的数据
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(inputStream));

                do {
                    // 客户端拿到一条数据(阻塞方法)
                    String str = socketInput.readLine();
                    if(str == null){
                        System.out.println("客户端已无法读取数据!");
                        // 退出当前客户端
                        ClientHandler.this.exitBySelf();
                        break;
                    }

                    //输出消息
                    System.out.println(str);

                } while (!done);

            } catch (IOException e) {
                if(!done) {
                    System.out.println("连接异常断开");
                    ClientHandler.this.exitBySelf();
                }
            } finally {
                CloseUtils.close(inputStream);
            }

        }

        void exit(){
            done = true;
            CloseUtils.close(inputStream);
        }

    }


    /**
     * 写数据线程
     */
    public class ClientWriteHandler{

        private boolean done = false;
        private final PrintStream printStream;
        private ExecutorService executorService = Executors.newSingleThreadExecutor();

        ClientWriteHandler(OutputStream outputStream) {
            this.printStream = new PrintStream(outputStream);
        }

        public void send(String str) {

            executorService.submit(new WriteRunnable(str));
        }

        void exit(){
            done = true;
            CloseUtils.close(printStream);
        }

        class WriteRunnable implements Runnable{

            final String message;

            WriteRunnable(String message) {
                this.message = message;
            }

            @Override
            public void run() {
                if(ClientWriteHandler.this.done){
                    return ;
                }

                ClientWriteHandler.this.printStream.println(message);
            }
        }

    }
}
