package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.*;

public class GameView extends JPanel implements Serializable{
	Container content;
	JFrame frame;
	Graphics g;
	JButton myTank,enemy, angleUp, angleDown, powerUp, powerDown, launch,start;
	JTextField nameField;
	JLabel myNameLabel,enemyNameLabel;
	UserInfo myUser;
	int x,y;
	GameView view;
	GameThread gameThread;

	boolean isLaunch = false;
	boolean hit = false;

	//myTank ÁÂÇ¥ (120, 350)				
	//enemyTank ÁÂÇ¥ (765,350)

	public GameView(UserInfo myUser, GameThread gameThread,Status status) {
		// TODO Auto-generated constructor stub

		frame = new JFrame();
		frame.setPreferredSize(new Dimension(900, 700));
		frame.setLocation(20,30);
		content = frame.getContentPane();
		content.add(status,BorderLayout.NORTH);
		content.add(this,BorderLayout.CENTER);
		this.setLayout(null);
		this.myUser = myUser;
		this.gameThread = gameThread;

		myTank = new JButton();
		enemy = new JButton();
		angleUp =  new JButton();
		angleDown =  new JButton();
		powerUp =  new JButton();
		powerDown =  new JButton();
		launch = new JButton();
		myNameLabel = new JLabel(myUser.getName());

		angleUp.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myUser.UpAngle();
				System.out.println("angle Up !");
			}
		});

		angleDown.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myUser.DownAngle();
				System.out.println("angle Down !");
			}
		});

		powerUp.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myUser.UpPower();
				System.out.println("power Up !");
			}
		});

		powerDown.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myUser.DownPower();
				System.out.println("power Down !");
			}
		});

		launch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("launch");
				gameThread.sendUser(myUser);
			}
		});

		this.add(angleUp);
		this.add(angleDown);
		this.add(powerUp);
		this.add(powerDown);
		this.add(launch);



		angleUp.setOpaque(false);
		angleDown.setOpaque(false);
		powerUp.setOpaque(false);
		powerDown.setOpaque(false);
		launch.setOpaque(false);
		myNameLabel.setOpaque(false);

		angleUp.setBounds(320, 415, 30, 30);
		angleDown.setBounds(320, 455, 30, 30);
		powerUp.setBounds(505, 415, 30, 30);
		powerDown.setBounds(505, 455, 30, 30);
		launch.setBounds(582, 424, 260, 50);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		this.g=g;
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(getToolkit().getImage("background.png"), 0, 0, this);

		if(isLaunch){
			g.drawImage(getToolkit().getImage("missile.png"),x,y,this);	
			System.out.println("repaint!!");
		}

		if(hit){
			g.drawImage(getToolkit().getImage("missile.png"),x,y,this);	
			g.setFont(new Font("SansSerif", Font.BOLD, 50));
			g.setColor(new Color(255,0,0));
			g.drawString("Hit!!!", 350,200);
		}


	}
}