package main.java.com.xjw.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws IOException {
        System.out.println("hello");
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,world".getBytes());
        outputStream.close();
        socket.close();
    }
}
