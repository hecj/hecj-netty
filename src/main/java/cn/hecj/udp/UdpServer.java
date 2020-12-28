package cn.hecj.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {

    DatagramSocket datagramSocket = null;

    private void connect(){
        try {
            datagramSocket = new DatagramSocket(29000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


    byte[] bytes = new byte[1024];

    private void readUDP() throws  Exception{
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        datagramSocket.receive(datagramPacket);
        int length = datagramPacket.getLength();
        System.out.println("接收字节数:"+length);
    }

    public static void main(String[] args) throws Exception{
        UdpServer udpClient = new UdpServer();
        udpClient.connect();

        while(true){
            udpClient.readUDP();
            Thread.sleep(100l);
        }
    }


}
