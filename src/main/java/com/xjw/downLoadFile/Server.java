package main.java.com.xjw.downLoadFile;

import main.java.com.xjw.upLoad.StreamUtils;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        //1.监听9999端口
        ServerSocket serverSocket = new ServerSocket(9999);
        //2.等待客户端连接
        Socket socket = serverSocket.accept();
        //3.读取客户端发送的下载请求
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        String receive = "";
        while ((len = inputStream.read(bytes)) != -1){
            receive += new String(bytes,0,len);
        }
        System.out.println(receive);

        //判断客户端需要下载的资源
        String resFileName = "";
        if ("我们的天空".equals(receive)){
            resFileName = "src\\我们的天空.mp3";
        }else {
            resFileName ="src\\我的楼兰.mp3";
        }

        //4.创建一个输入流，读取文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(resFileName));
        //5.使用工具类StreamUtils，读取文件到一个字节数组
        byte[] bytes1 = StreamUtils.streamToByteArray(bis);
        //6.得到Socket关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        //7.将数据（文件字节数组）写入到数据通道
        bos.write(bytes1);
        socket.shutdownOutput();  //很关键，如果不设置传输结束标记，可能会造成客户端无法判断文件是否已经下载完成，造成阻塞

        //8.关闭相关数据
        bos.close();
        outputStream.close();
        bis.close();
        inputStream.close();
        socket.close();
    }

}
