package socket;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
public static void main(String[] args) {
	Socket socket=new Socket();
	//超时时间
	try {
		socket.setSoTimeout(3000);
		socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(),2000),3000);
	    System.out.println("已发起服务器连接，并进入后续流程~");
	    System.out.println("客户端信息："+socket.getLocalAddress()
	    +",P:"+socket.getLocalPort());
	    System.out.println("服务器信息："+socket.getInetAddress()
	    +",P:"+socket.getPort());
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}


}
