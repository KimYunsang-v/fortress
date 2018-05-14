package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Status extends JPanel{
	UserInfo myUser,user;
	Graphics g;
	int myHP=100,enemyHP = 100,wind=-2;
	public Status(){		

	}	
	public int getWind() {
		return wind;
	}
	public void setWind(int wind) {
		this.wind = wind;
	}
	public int getMyHP() {
		return myHP;
	}

	public void setMyHP(int myHP) {
		this.myHP = myHP;
	}
	public void MinusMyHP() {
		myHP -= 10;
	}

	public int getEnemyHP() {
		return enemyHP;
	}

	public void setEnemyHP(int enemyHP) {
		this.enemyHP = enemyHP;
	}	
	public void MinusEnemyHP() {
		enemyHP -= 10;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		this.g = g;

		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(getToolkit().getImage("upbackground.png"),0,0,this); 

		//내 스코어
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		g.setColor(new Color(255,0,0));
		g.drawString(Integer.toString(myHP), 0, 40);

		//상대 스코어
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		g.setColor(new Color(255,0,0));
		g.drawString(Integer.toString(enemyHP), 800, 40);

		// My HP
		g.setColor(new Color(0,0,0));
		g.fillRect(96, 26,208, 48);
		g.setColor(new Color(54,234,2));
		g.fill3DRect(100, 30, myHP*2, 40,true);

		// enemy HP
		g.setColor(new Color(0,0,0));
		g.fillRect(596, 26, 208, 48);
		g.setColor(new Color(54,234,2));
		g.fill3DRect(600, 30, enemyHP*2, 40,true);

		//바람
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		g.setColor(new Color(255,0,0));
		g.drawString(Integer.toString(wind), 450, 40);
	}
}
