package cn.hecj.udp5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

public class UdpReceiver1 {

    static SocketAddress sendAddress;

    public static void main(String[] args) throws Exception{

//        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 8181; // 接收从8181发来的数据

//        DatagramSocket socket = new DatagramSocket(port, address);
        DatagramSocket socket = new DatagramSocket(port);

        // 接收
        receive(socket);

        // 发送
        send(socket);

        Thread.sleep(10000000l);

        socket.close();
    }


    public static void receive(DatagramSocket socket ){
        new Thread(){
            @Override
            public void run() {
                try{
                    while(true){
                        byte[] buf = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(buf, buf.length);
                        socket.receive(packet);

                        String getMsg = new String(buf, 0, packet.getLength());
                        System.out.println("客户端发送的数据为:"+getMsg);

                        InetAddress clientAddress = packet.getAddress();
                        int clientPort = packet.getPort();
                        sendAddress = packet.getSocketAddress();
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }.start();
    }


    public static void send(DatagramSocket socket)throws Exception{
        for(int i=0;i<1000;i++){
            if(sendAddress == null){
                System.out.println("sendAddress为空");
                Thread.sleep(1000l);
                continue;
            }
            Thread.sleep(1000l);

            String feedback = "Received"+i;
            byte[] backbuf = feedback.getBytes();
  //        DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length,clientAddress,clientPort);
            DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length,sendAddress);
            System.out.println("发送数据开始:"+sendAddress);
            socket.send(sendPacket);
            System.out.println("发送数据完成");
        }

    }
}
