package demo;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Barriers extends JPanel {
	public static  JPanel[]  barriers   = new JPanel[5];
	public static  Card[]  cards   = new Card[5];
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4; 
	 
	public Barriers(int x ,int y) { // para de una vez conocer la ubicacion donde se pondra
		
		
		
		removeall();
		setLayout(null);
		setOpaque(false);
		setBounds(x,y+10,600,50);//se fija el tamaño y la posicion
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setForeground(new Color(0, 102, 0));
		panel.setBounds(5, 0, 100, 50);
		barriers[0]=panel;
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setBounds(133, 0, 100, 50);
		add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_2.setBackground(new Color(128, 128, 128));
		panel_2.setBounds(254, 0, 100, 50);
		add(panel_2);
		
		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setForeground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_3.setBackground(new Color(128, 128, 128));
		panel_3.setBounds(376, 0, 100, 50);
		add(panel_3);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel.setLayout(null);
		add(panel);
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setForeground(Color.WHITE);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_4.setBackground(new Color(128, 128, 128));
		panel_4.setBounds(500, 0, 100, 50);
		add(panel_4);
		barriers[1]=panel_1;
		
		label = new JLabel("Barrier");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label.setBounds(10, 0, 80, 25);
		panel_1.add(label);
		barriers[2]=panel_2;
		
		label_1 = new JLabel("Barrier");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label_1.setBounds(10, 0, 68, 25);
		panel_2.add(label_1);
		barriers[3]=panel_3;
		
		label_2 = new JLabel("Barrier");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label_2.setBounds(10, 0, 80, 25);
		panel_3.add(label_2);
		barriers[4]=panel_4;
		
		
		label_3 = new JLabel("Barrier");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label_3.setBounds(10, 0, 77, 25);
		panel_4.add(label_3);
		
		label_4 = new JLabel("Barrier");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label_4.setBounds(10, 0, 80, 25);
		panel.add(label_4);
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
		cards[i]=recieved;
		System.out.println(""+recieved.GetName());
		barriers[i].setVisible(true);
		repaint();
		
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

}
