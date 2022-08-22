package main.java.com.xjw.upLoad;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPFileUpLoadServer {

    public static void main(String[] args) throws IOException {

        //1.服务端在本机监听8888端口
        ServerSocket serverSocket = new ServerSocket(8888);
        //2.阻塞，等待连接
        Socket socket = serverSocket.accept();

        //3.读取客户端发送的数据
        //  通过Socket得到输入流
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bis);

        //4.将得到的数据 bytes数组，写入到指定的路径，就得到一个文件了
        String destFilePath = "src\\mm.jpg";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
        bos.write(bytes);
        bos.close();

        //5.向客户端回复“收到图片”
        //  通过socket 获取到输出流（字符流）
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("收到图片");
        bufferedWriter.flush();  //把内容刷新到数据通道
        socket.shutdownOutput();  //设置写入结束标记

        //关闭资源
        bufferedWriter.close();
        bis.close();
        socket.close();
        serverSocket.close();
    }

}
