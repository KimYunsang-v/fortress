package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;

public class ServerMain {
	ServerSocket serverSocket;
	int i=0;
	private Map<String, DataOutputStream> clientMap = new HashMap<String, DataOutputStream>();
	private String msg;
	private UserInfo user;
	private Gson gson;
	int order = 0,wind,windDir,temp;
	Random rand = new Random();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerMain server = new ServerMain();		
	}

	public ServerMain(){
		gson = new Gson();
		try {
			ServerSocket serverSocket = new ServerSocket(9004);
			System.out.println("Listening...");
			setWind();
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Client connected.");				

				Receiver receiver = new Receiver(socket,i);
				receiver.start();				
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class Receiver extends Thread {
		/** XXX 리시버가 할일 : 네트워크 소켓을 받아서 계속듣고 보내는 일. */
		private DataInputStream in; // 데이터 입력 스트림
		private DataOutputStream out; // 데이터 아웃풋 스트림
		private String nick;
		Socket socket;
		public Receiver(Socket socket,int i) {
			gson=new Gson();
			this.socket = socket; 

			try {
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());	

				nick = in.readUTF();

				System.out.println(nick + " 님 입장  i = " + i);

				msg = in.readUTF();
				user = gson.fromJson(msg, UserInfo.class);
				addClient(msg,out);
				user.setNum(i);

				msg = gson.toJson(user);
				out.writeUTF(msg);

				out.writeInt(wind);

			} catch (IOException e) {
				e.printStackTrace();
			}			
		}

		@Override
		public void run() {					
			try {
				while (in != null) {
					msg = in.readUTF();// UTF로 읽어들인다.
					user = gson.fromJson(msg, UserInfo.class);
					System.out.println("UserHp = " + user.getHP());
					if(user.getHP() <= 0){
						break;
						//socket.close();
					}
					System.out.println("num = " + user.getNum() + "order = " +  order);
					if(user.getNum() == order){
						sendUser(msg);
						if(order == 0){
							order = 1;
						}
						else if(order ==1){
							order = 0;
							setWind();
						}
					}
					sendWind();
				}
			} catch (Exception e) {

			}finally{
				try {
					socket.close();
					//serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	public void setWind(){
		wind = rand.nextInt(5);
		windDir = rand.nextInt(2);
		if(windDir == 0){
			wind *= -1;
		}
	}

	public void sendUser (String msg){
		Iterator<String> iterator = clientMap.keySet().iterator(); //key셋으로 반복자지정
		String key = "";	

		while(iterator.hasNext()){
			key = iterator.next();// 반복자에서 하나하나 키를 빼온다.
			try{
				clientMap.get(key).writeUTF(msg);
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	public void sendWind (){
		Iterator<String> iterator = clientMap.keySet().iterator(); //key셋으로 반복자지정
		String key = "";	

		while(iterator.hasNext()){
			key = iterator.next();// 반복자에서 하나하나 키를 빼온다.
			try{
				clientMap.get(key).writeInt(wind);
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	public void sendMessage(){
		Iterator<String> iterator = clientMap.keySet().iterator(); //key셋으로 반복자지정
		String key = "";

		while(iterator.hasNext()){
			key = iterator.next();// 반복자에서 하나하나 키를 빼온다.
			try{
				clientMap.get(key).writeUTF(key);
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	public void addClient(String nick, DataOutputStream out) {
		clientMap.put(nick, out);
	}
}
