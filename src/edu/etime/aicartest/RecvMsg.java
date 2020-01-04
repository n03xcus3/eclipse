package edu.etime.aicartest;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 * 接收控制指令模拟类
 * @author zw
 *
 */
public class RecvMsg implements Runnable{

	@Override
	public void run() {
		
		while(true){
			byte[] buff = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buff, 1024);
			try {
				AiCarTest.getSock().receive(dp);
				String str = new String(buff, 0, dp.getLength());
				if(str.equals("00")){
					//服务器登录
					AiCarServer server = new AiCarServer();
					server.setServerip(dp.getAddress().toString().substring(1));
					server.setServerport(dp.getPort());
					Config.getSlist().add(server);
				}else{
					//显示消息
					System.out.println(str);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
