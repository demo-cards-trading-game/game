package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Barriers2 extends JPanel {
	public static  JPanel[]  barriers   = new JPanel[5];
	public static  Card[]  cards   = new Card[5];
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private RPanel panel_1;
	private RPanel panel_2;
	private RPanel panel_3;
	private RPanel panel_4; 
	Graphics2D g2d;
	public Barriers2(int x ,int y) { // para de una vez conocer la ubicacion donde se pondra
		
		
		
		removeall();
		setLayout(null);
		setOpaque(false);
		setBounds(x,y,600,50);//se fija el tamaño y la posicion
		
		panel_1 = new RPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(128, 0, 65, 26);
		add(panel_1);
		
		panel_2 = new RPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(219, 0, 65, 26);
		add(panel_2);
		
		panel_3 = new RPanel();
		panel_3.setLayout(null);
		panel_3.setForeground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_3.setBackground(Color.GRAY);
		panel_3.setBounds(310, 0, 65, 26);
		add(panel_3);
		JPanel panel = new RPanel();
		panel.setBackground(Color.GRAY);
		panel.setForeground(new Color(0, 102, 0));
		panel.setBounds(47, 0, 65, 26);
		barriers[0]=panel;
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel.setLayout(null);
		add(panel);
		
		label_4 = new JLabel("Barrier");
		label_4.setBackground(Color.LIGHT_GRAY);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label_4.setBounds(0, 0, 65, 25);
		panel.add(label_4);
		panel_4 = new RPanel();
		panel_4.setLayout(null);
		panel_4.setForeground(Color.WHITE);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_4.setBackground(Color.GRAY);
		panel_4.setBounds(400, 0, 65, 26);
		add(panel_4);
		barriers[1]=panel_1;
		
		label = new JLabel("Barrier");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label.setBounds(0, 0, 65, 25);
		panel_1.add(label);
		barriers[2]=panel_2;
		
		label_1 = new JLabel("Barrier");
		label_1.setBackground(Color.GRAY);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label_1.setBounds(0, 0, 65, 25);
		panel_2.add(label_1);
		barriers[3]=panel_3;
		
		label_2 = new JLabel("Barrier");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label_2.setBounds(0, 0, 65, 25);
		panel_3.add(label_2);
		barriers[4]=panel_4;
		
		
		label_3 = new JLabel("Barrier");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label_3.setBounds(0,0, 65, 25);
		panel_4.add(label_3);
		barriers[2]=panel_2;
		for (int i=0;i<5;i++)
		barriers[i].setVisible(false);
		
		
		
	}
	int findwhere()
	{
		int x=-1;
		int i=0;
		while(x==-1 && i<5)
		{
			if(cards[i]==null)
			{
				x=i;
			}
			i++;
			
		}
		
		return x;
	}
	void addbarrier(Card recieved)
	{
		int i = findwhere();
		if(i!=-1){
		cards[i]=recieved;
		barriers[i].setVisible(true);
		
		repaint();
		
		}
		
	}
	void removebarrier(int pos)
	{
		barriers[pos].setVisible(false);
		cards[pos]=null;
		repaint();
	}
	void removeall()
	{
		
		
		
			for (int i=0;i<5;i++){
			cards[i]=null;
			}
		
	}
	int findwhich()//encuentra una barrier para jugarla
	{
		int which=-1,i=0;
		int found=0;
		while(i<4 && found==0)
		{
			if(barriers[i].isVisible())
			{
				which=i;
				found=1;
				
				
			}
			
		}
		return(which);
	
	}

	
}
