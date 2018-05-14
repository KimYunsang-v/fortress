package client;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Finish {
	Container contentPane;
	UserInfo myUser;
	RankDB db;
	Finish(UserInfo myUser){
		this.myUser = myUser;
		db = new RankDB();
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(300, 100));

		contentPane = frame.getContentPane();

		JLabel label = new JLabel();

		if(myUser.getHP() <= 0) {
			label.setText(myUser.getName() + "- Lose");
			db.UpdateDB(myUser.getName(),false);
		}
		else{
			label.setText(myUser.getName() + "- Win");
			db.UpdateDB(myUser.getName(),true);
		}
		contentPane.add(label);

		if(myUser.getNum() == 0) {
			frame.setLocation(50,50);
		}
		else
			frame.setLocation(300,50);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}