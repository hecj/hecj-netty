package cn.hecj.udp3.send;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/*
 * 接收消息的线程类
 */
class ReceiveThread implements Runnable{
	private DatagramSocket receiveSocket;
 
	public ReceiveThread(DatagramSocket receiveSocket) {
		this.receiveSocket = receiveSocket;
	}
 
	@Override
	public void run() {
		try {
			while(true){
				//一次接收的内容的最大容量
				byte[] buf = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				//接收数据包
				receiveSocket.receive(dp);
				//取得数据包里的内容 
				String data = new String(dp.getData(), 0, dp.getLength());
				//打印接收到的数据
				System.out.println("Other:" + data);
			}
		} catch (IOException e) {
			System.out.println("receive fail");
		}finally{
			if(receiveSocket != null){
				receiveSocket.close();
			}
		}
	}
}