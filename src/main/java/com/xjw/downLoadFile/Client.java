package main.java.com.xjw.downLoadFile;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 基于TCP数据传输原生态文件下载（socket）
 */

public class Client {

    public static void main(String[] args) throws IOException {

        //1.接收用户输入，指定下载文件名
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入需要下载的文件名：");
        String downLoadFileName = scanner.next();

        //2.客户端连接服务端，准备发送数据
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        //3.获取和Socket关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(downLoadFileName.getBytes());
        //设置写入结束的标志
        socket.shutdownOutput();

        //4.读取服务端返回的文件（数据通道的文件字节数组）
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        //5.得到一个输出流，准备将bytes 写入到磁盘
        String filePath = "d:\\" + downLoadFileName + ".mp3";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        bos.write(bytes);

        //6.关闭相关资源
        bos.close();
        bis.close();
        outputStream.close();
        socket.close();
    }

}
