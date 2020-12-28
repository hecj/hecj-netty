package cn.hecj.udp3.send;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
 
public class Send {
	//接收消息的端口
	private static final int PORT_RECEIVE = 8080;
 
	public static void main(String[] args) {
		handler();
	}
 
	private static void handler() {
		DatagramSocket sendSocket = null;
		DatagramSocket receiveSocket = null;
		try {
			//实例化两个套接字，一个用于接收消息，一个用于发送消息
			sendSocket = new DatagramSocket();
			receiveSocket = new DatagramSocket(PORT_RECEIVE);
 
			//实例化线程并启动，一个用于接收消息，一个用于发送消息
			new Thread(new SendThread(sendSocket)).start();
			new Thread(new ReceiveThread(receiveSocket)).start();
		} catch (SocketException e) {
			System.out.println("handler:异常！");
		}
	}
}