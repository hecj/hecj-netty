package cn.hecj.udp4;

import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

public class UdpSender {

    public static void main(String[] args) throws Exception{

        String msg = "hello world";
        byte[] buf = msg.getBytes();

        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 8181; // 接收从8081发来的数据

        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, address, port);

        DatagramSocket socket = new DatagramSocket(port, address);
        socket.send(datagramPacket);

        // 接收服务器反馈的数据
        byte[] backbuf = new byte[1024];
        DatagramPacket backPacket = new DatagramPacket(backbuf,backbuf.length);
        socket.receive(backPacket); // 接收返回的数据
        String backMsg = new String(backbuf,0,backPacket.getLength());
        System.out.println("服务器返回的数据:"+backMsg);

        socket.close();
    }
}
