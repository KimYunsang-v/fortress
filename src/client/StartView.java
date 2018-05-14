package client;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class StartView extends JPanel{
	Container contentPane;
	UserInfo myUser;
	Graphics g;	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StartView startView = new StartView();
	}


	public StartView(){

		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(500, 600));
		frame.setLocation(50,50);
		contentPane = frame.getContentPane();

		JButton start = new JButton("Start");
		JButton rank = new JButton("Rank");

		JTextField nameField = new JTextField();
		contentPane.add(rank);
		contentPane.add(start);
		contentPane.add(nameField);
		contentPane.add(this);

		rank.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Rank rank = new Rank();
			}
		});

		start.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				myUser = new UserInfo();
				myUser.setName(nameField.getText());
				//myUser.setHP(100);
				GameThread gameThread = new GameThread(myUser);
				gameThread.start();
				frame.dispose();
			}
		});

		nameField.setBounds(200,250, 100, 50);
		start.setBounds(300,250, 70, 50);
		rank.setBounds(300,300, 70, 50);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		this.g = g;
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(getToolkit().getImage("startBackground.png"), 0, 0, this);
	}	
}
