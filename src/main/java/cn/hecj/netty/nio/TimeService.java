package cn.hecj.netty.nio;

/**
 * @author wangzhen 
 * @version 1.0  
 * @createDate：2015年11月19日 下午5:03:33 
 * 
 */
public class TimeService {
	public static void main(String[] args) {
		int port = 8080;
		MultiplexerTimeServer timeServer  = new MultiplexerTimeServer(port);
		new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
	}
}
