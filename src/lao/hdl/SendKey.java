package lao.hdl;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SendKey {
	DatagramSocket ds;
	public SendKey(){
		try {
			this.ds=new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void start(String state,int pkey){
		try {
			String str1=String.valueOf(pkey);
			String s=state+str1;
			byte[] bys=s.getBytes();
			InetAddress address;
			address = InetAddress.getByName("localhost");//176.128.12.6
			DatagramPacket dp=new DatagramPacket(bys,bys.length,address,10086);
			ds.send(dp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
