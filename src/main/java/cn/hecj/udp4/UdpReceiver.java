package cn.hecj.udp4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

public class UdpReceiver {

    public static void main(String[] args) throws Exception{

        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 8181; // 接收从8181发来的数据

        DatagramSocket socket = new DatagramSocket(port, address);

        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        String getMsg = new String(buf, 0, packet.getLength());
        System.out.println("客户端发送的数据为:"+getMsg);

        InetAddress clientAddress = packet.getAddress();
        int clientPort = packet.getPort();
        SocketAddress sendAddress = packet.getSocketAddress();
        String feedback = "Received";
        byte[] backbuf = feedback.getBytes();
//        DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length,clientAddress,clientPort);
        DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length,sendAddress);
        socket.send(sendPacket);

        socket.close();
    }
}
