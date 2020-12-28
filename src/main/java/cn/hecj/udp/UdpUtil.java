package cn.hecj.udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpUtil {

    private static String LOGSTASH_IP= "192.168.31.13";
    private static int PORT = 29000;

    public static void setIp(String ip){
        LOGSTASH_IP = ip;
    }

    private static DatagramSocket datagramSocket;

    public static void send(byte[] bytes){
        try {
            System.out.println("udp发送数据ip:"+LOGSTASH_IP+",port:"+PORT+",byte:"+bytes.length);
            InetAddress inetAddress = InetAddress.getByName(LOGSTASH_IP);
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length,inetAddress, PORT);
            if (null == datagramSocket) {
                datagramSocket = new DatagramSocket();
            }
            datagramSocket.send(dp);
//            datagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        byte[] bytes = new byte[1024*60];
        for(int i=0;i<10000;i++){
            send(bytes);
            try {
                Thread.sleep(10l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
