package client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.JButton;
import com.google.gson.Gson;



public class GameThread extends Thread{
	UserInfo user,myUser;	
	GameView gameView;
	GameThread gameThread;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	String enemyName;
	double r;
	int t=0,wind,windDir,temp;
	JButton launch;
	Gson gson;
	Status status;
	Random rand;

	GameThread(UserInfo myUser_){		
		this.myUser = myUser_;
		this.gameThread = this;
		gson = new Gson();

		try {
			socket = new Socket("127.0.0.1",9004);
			System.out.println("서버에 연결됨");

			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());

			out.writeUTF(myUser.getName());

			String msg = gson.toJson(myUser);
			out.writeUTF(msg);

			msg = in.readUTF();
			myUser = gson.fromJson(msg, UserInfo.class);

			System.out.println("쓰레드 생성시 num = " + myUser.getNum());			

			status = new Status();		
			status.setPreferredSize(new Dimension(900,150));

			wind = in.readInt();
			status.setWind(wind);

			gameView = new GameView(myUser,gameThread,status);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run(){
		String msg;
		while(myUser.getHP() > 0 ){
			try {
				System.out.println("Game run");     

				msg = in.readUTF();
				user = gson.fromJson(msg, UserInfo.class);

				temp = user.getPower();
				temp += wind;
				user.setPower(temp);

				System.out.println("user num = " + user.getNum() + "my num = " + myUser.getNum());

				if(user.getNum() == myUser.getNum()){
					gameView.isLaunch = true;
					MyLaunch launch = new MyLaunch(gameView,user,status,myUser);
					launch.start();
				}

				else if(user.getNum() != myUser.getNum()){
					gameView.isLaunch = true;
					EnemyLaunch launch = new EnemyLaunch(gameView,user,status,myUser);
					launch.start();					
				}

				if(myUser.getHP() <= 0)
					break;

				wind = in.readInt();
				status.setWind(wind);
				status.repaint();
				System.out.println(wind);	
				myUser.setAngle(0);
				myUser.setPower(0);
				System.out.println("UserHp = " + user.getHP());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			socket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendUser(UserInfo user){
		try {
			String msg = gson.toJson(user);
			out.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}