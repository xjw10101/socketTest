package main.java.com.xjw.upLoad;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TCPFileUpLoadClient {
    public static void main(String[] args) throws IOException {
        //客户端连接服务端8888，得到Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        //创建读取磁盘文件的输入流
        String filePath = "D:\\mm.jpg";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));

        //bytes 就是filePath对应的字节数组
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        //通过socket获取到输出流，将bytes数据发送到数据通道
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes);  //将文件对应的字节数组的内容，写入到数据通道
        bis.close();
        socket.shutdownOutput();  //设置写入数据的结束标记

        // 接收从服务端回复的消息
        InputStream inputStream = socket.getInputStream();
        // 将inputStream流读到的内容转化为string字符串
        String streamToString = StreamUtils.streamToString(inputStream);
        System.out.println(streamToString);

        //关闭相关资源
        inputStream.close();
        bos.close();
        socket.close();
    }
}


