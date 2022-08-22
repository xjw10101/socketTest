package main.java.com.xjw.udp;

import java.io.IOException;
import java.net.*;

public class UDPSendB {

    public static void main(String[] args) throws IOException {

        //1.创建DatagramSocket 对象，准备在9998接收数据
        DatagramSocket socket = new DatagramSocket(9998);

        //2.将需要发送的数据，封装到 DatagramPacket对象
        byte[] data = "hello,明天早上跑步".getBytes();
        //说明：封装的DataGramPacket对象 包括数据字节数组，长度，主机地址，端口
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.2.4"), 9999);
        socket.send(packet);

        //作为接收方
        //2.构建一个DatagramPacket 对象，准备接收数据
        //  UDP协议规定，传输一个数据包最大值为64k
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);

        //3.调用接收方法，将通过网络传输的 DatagramPacket 对象
        // 填充到 packet对象
        // 提示：当有数据包发送到 本机的9999端口时，就会接收到数据
        //       如果没有数据包发送到 本机9999端口，就是阻塞等待
        socket.receive(datagramPacket);


        //4.可以把packet进行拆包，取出数据，并显示
        int length = datagramPacket.getLength(); //实际接收到的数据字节长度
        byte[] dataReceive = datagramPacket.getData();  //接收到的数据
        String s = new String(dataReceive, 0, length);
        System.out.println(s);

        //5.关闭资源
        socket.close();
    }

}
