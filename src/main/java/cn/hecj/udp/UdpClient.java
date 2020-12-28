package cn.hecj.udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {

    private static String IP= "192.168.50.243";
    private static int PORT = 29000;

    private DatagramSocket datagramSocket;

    public void send(byte[] bytes){
        try {
            InetAddress inetAddress = InetAddress.getByName(IP);
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length,inetAddress, PORT);
            if (null == datagramSocket) {
                datagramSocket = new DatagramSocket();
            }
            System.out.println("发送字节数:"+bytes.length);
            datagramSocket.send(dp);
//            datagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){

        UdpClient udpClient = new UdpClient();

        byte[] bytes = new byte[1024];
        for(int i=0;i<100;i++){
            udpClient.send(bytes);
            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
