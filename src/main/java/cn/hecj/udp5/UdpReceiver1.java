package cn.hecj.udp5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

public class UdpReceiver1 {

    public static void main(String[] args) throws Exception{

//        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 8181; // 接收从8181发来的数据

//        DatagramSocket socket = new DatagramSocket(port, address);
        DatagramSocket socket = new DatagramSocket(port);

        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        String getMsg = new String(buf, 0, packet.getLength());
        System.out.println("客户端发送的数据为:"+getMsg);

        InetAddress clientAddress = packet.getAddress();
        int clientPort = packet.getPort();
        SocketAddress sendAddress = packet.getSocketAddress();

        send(socket, sendAddress);

        socket.close();
    }

    public static void send(DatagramSocket socket,SocketAddress sendAddress)throws Exception{
        for(int i=0;i<10;i++){
            String feedback = "Received"+i;
            byte[] backbuf = feedback.getBytes();
  //        DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length,clientAddress,clientPort);
            DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length,sendAddress);
            System.out.println("发送数据开始:");
            socket.send(sendPacket);
            System.out.println("发送数据完成");
        }

    }
}
