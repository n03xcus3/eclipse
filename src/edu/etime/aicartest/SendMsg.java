package edu.etime.aicartest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * 模拟发送传感数据
 * @author zw
 *
 */
public class SendMsg implements Runnable {

	@Override
	public void run() {
		
		while(true){
			Long l = System.currentTimeMillis();
			String str = l.toString();
			String temp = str.substring(str.length()-2, str.length());//温度
			String hum = str.substring(str.length()-4,str.length()-2);//湿度
			String light = "02"+str.substring(str.length()-3,str.length());//光照
			String ill = "03"+str.substring(str.length()-4,str.length());//酒精浓度
			String jl = "04"+str.substring(str.length()-5,str.length()-3);//距离
			for(AiCarServer s : Config.getSlist()){
				//温湿度：
				String th = "01"+temp+","+hum;
				//光照
				try {
					DatagramPacket dptemp = new DatagramPacket(th.getBytes(), th.getBytes().length,
							InetAddress.getByName(s.getServerip()),s.getServerport());
					DatagramPacket dplight = new DatagramPacket(light.getBytes(), light.getBytes().length,
							InetAddress.getByName(s.getServerip()),s.getServerport());
					DatagramPacket dpill = new DatagramPacket(ill.getBytes(), ill.getBytes().length,
							InetAddress.getByName(s.getServerip()),s.getServerport());
					DatagramPacket dpjl = new DatagramPacket(jl.getBytes(), jl.getBytes().length,
							InetAddress.getByName(s.getServerip()),s.getServerport());
					AiCarTest.getSock().send(dptemp);
					Thread.sleep(100);
					AiCarTest.getSock().send(dplight);
					Thread.sleep(100);
					AiCarTest.getSock().send(dpill);
					Thread.sleep(100);
					AiCarTest.getSock().send(dpjl);
					Thread.sleep(100);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}	
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
