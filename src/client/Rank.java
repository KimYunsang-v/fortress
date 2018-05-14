package client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Rank extends JPanel{	 

	ArrayList<RankVO> list=new ArrayList<RankVO>(); 
	private Color c1 = new Color(0,0,0);
	private Color c2 = new Color(255,0,0);

	public Rank()
	{
		RankDB db = new RankDB();
		list = db.selectRank();

		JFrame frame = new JFrame();
		frame.setLocation(100,10);
		Container contentPane = frame.getContentPane();

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(700,100));
		panel.setLayout(new GridLayout(1,3));
		panel.add(new JLabel("¿Ã∏ß"));
		panel.add(new JLabel("Win"));
		panel.add(new JLabel("Lose"));

		this.setPreferredSize(new Dimension(700,422));
		contentPane.add(panel,BorderLayout.NORTH);
		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(700,322));
		panel1.setLayout(new GridLayout(2,3));

		for(int i=0; i<list.size(); i++){
			panel1.add(new JLabel(list.get(i).getName()));
			panel1.add(new JLabel(Integer.toString(list.get(i).getWin())));
			panel1.add(new JLabel(Integer.toString(list.get(i).getLose())));
		}
		contentPane.add(panel1,BorderLayout.CENTER);

		panel.setBackground(c1);
		panel.setBackground(c2);	

		frame.pack();
		frame.setVisible(true);
	}
}
