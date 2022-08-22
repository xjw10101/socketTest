package main.java.com.xjw.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPReceiveA {

    public static void main(String[] args) throws IOException {

        //1.创建一个 DatagramSocket 对象，准备在9999接收数据
        DatagramSocket socket = new DatagramSocket(9999);

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
        byte[] data = datagramPacket.getData();  //接收到的数据
        String s = new String(data, 0, length);
        System.out.println(s);

        //作为发送端
        //2.将需要发送的数据，封装到 DatagramPacket对象
        byte[] dataSend = "hello,明天早上吃饭".getBytes();
        //说明：封装的DataGramPacket对象 包括数据字节数组，长度，主机地址，端口
        DatagramPacket packet = new DatagramPacket(dataSend, dataSend.length, InetAddress.getByName("192.168.2.4"), 9998);
        socket.send(packet);

    }

}
