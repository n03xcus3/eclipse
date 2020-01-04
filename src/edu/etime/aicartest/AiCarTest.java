package edu.etime.aicartest;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 智能小车模拟器
 * @author zw
 *
 */
public class AiCarTest {

	private static DatagramSocket sock;
	
	public static DatagramSocket getSock() {
		return sock;
	}

	public static void main(String[] args) {
		
		try {
			sock = new DatagramSocket(12345);
			Thread th1 = new Thread(new RecvMsg());
			th1.start();
			Thread th2 = new Thread(new SendMsg());
			th2.start();
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
