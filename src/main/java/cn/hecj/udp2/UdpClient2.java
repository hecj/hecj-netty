package cn.hecj.udp2;



import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 先启动这个类，这是客户端发送数据。
 */
public class UdpClient2
{
    public static void main(String args[]) throws Exception
    {
        byte[] bytes=new byte[1024];
        DatagramSocket socket=new DatagramSocket(7000);
        DatagramPacket repacket=new DatagramPacket(bytes,bytes.length);
        socket.receive(repacket);
        System.out.println(new String(bytes,0,repacket.getLength()));
        String str="I love You!";
        System.out.println("client address:"+repacket.getAddress()+",port:"+repacket.getPort());
        DatagramPacket packet=new DatagramPacket(str.getBytes(),str.length(),repacket.getAddress(),repacket.getPort());

        socket.send(packet);
        socket.close();
    }
}