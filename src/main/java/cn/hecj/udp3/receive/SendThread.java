package cn.hecj.udp3.receive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * 发送消息的线程类
 */
class SendThread implements Runnable{
	//将消息发送到指定端口和地址
	private static final int PORT_SEND = 8080;
	private static final String IP_SEND = "localhost";
	private DatagramSocket sendSocket;
 
	public SendThread(DatagramSocket sendSocket) {
		this.sendSocket = sendSocket;
	}
 
	@Override
	public void run() {
		BufferedReader br = null;
		try {
			while(true){
				//键盘录入
				br = new BufferedReader(new InputStreamReader(System.in));
				String line = null;
				while((line = br.readLine()) != null){
					//将键盘录入的内容转换成字节数组
					byte[] buf = line.getBytes();
					//实例化一个数据包，指定发送的内容，内容长度，发送的地址和端口
					DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName(IP_SEND), PORT_SEND);
					//发送数据包
					sendSocket.send(dp);
					//打印发送的内容
					System.out.println("I:" + line);
				}
			}
		} catch (IOException e) {
			System.out.println("send fail");
		}finally{
			if(sendSocket != null){
				sendSocket.close();
			}
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}