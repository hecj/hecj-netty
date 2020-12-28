package cn.hecj.udp2;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 再启动这个类，这个算是服务端
 */
public class UdpServer2 {

    public static void main(String args[]) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        String str = "nice to meet you!";
        //构造数据报包
        DatagramPacket packet = new DatagramPacket(str.getBytes(), str.length(),
                InetAddress.getByName("localhost"), 7000);
        //发送
        socket.send(packet);

        byte[] bytes = new byte[1024];
        DatagramPacket repacket = new DatagramPacket(bytes, 100);
        socket.receive(repacket);
        System.out.println(new String(bytes, 0, repacket.getLength()));
        socket.close();
    }

}