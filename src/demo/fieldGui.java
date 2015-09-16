package demo;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.util.Random;
public class fieldGui extends JPanel {
	public JButton btn;
	public JPanel panel2,panel,panel3,panel4,panel1,panel5; 
	public SmallCard[]  cards  = new SmallCard[5];
	public int current;
	public fieldGui(int posx,int posy) {
		
		setOpaque(false);
		setBounds(posx,posy, 500, 145);
		setLayout(null);
		
		 panel = new JPanel();
		panel.setOpaque(true);
		panel.setLayout(null);
		panel.setForeground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel.setBackground(new Color(204, 204, 204));
		panel.setBounds(0, 0, 100, 145);
		add(panel);
		current=0;
		 panel2 = new JPanel();
		panel2.setOpaque(true);
		panel2.setLayout(null);
		panel2.setForeground(Color.WHITE);
		panel2.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel2.setBackground(new Color(204, 204, 204));
		panel2.setBounds(100, 0, 100, 145);
		add(panel2);
		
		panel3 = new JPanel();
		panel3.setOpaque(true);
		panel3.setLayout(null);
		panel3.setForeground(Color.WHITE);
		panel3.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel3.setBackground(new Color(204, 204, 204));
		panel3.setBounds(200, 0, 100, 145);
		add(panel3);
		
		panel4 = new JPanel();
		panel4.setOpaque(true);
		panel4.setLayout(null);
		panel4.setForeground(Color.WHITE);
		panel4.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel4.setBackground(new Color(204, 204, 204));
		panel4.setBounds(300, 0, 100, 145);
		add(panel4);
		
		panel5 = new JPanel();
		panel5.setOpaque(true);
		panel5.setLayout(null);
		panel5.setForeground(Color.WHITE);
		panel5.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel5.setBackground(new Color(204, 204, 204));
		panel5.setBounds(400, 0, 100, 145);
		add(panel5);
			
	}
	void place(Card x,boolean bocabajo)
	{
		if(current==5)
		{
			quitar(4);
				
		}
	
		switch(current)
		{
		
		case 0:
			if(panel.isShowing()){
			remove(panel);
			cards[0]=new SmallCard(x,0,0);
			
			}
			break;
		case 1:
			if(panel2.isShowing())
			{
			remove(panel2);
			cards[current]=new SmallCard(x,100,0);
			}
			break;
		case 2: 
			if(panel3.isShowing())
			{
			remove(panel3);
			cards[current]=new SmallCard(x,200,0);
			}
			break;
		case 3 :
			if(panel4.isShowing()){
			remove(panel4);
			cards[current]=new SmallCard(x,300,0);
			}
			break;
			
		case 4:
			if(panel5.isShowing()){
			remove(panel5);
			cards[current]=new SmallCard(x,400,0);
			}
		
			break;
			
		}
		
		if(bocabajo)
		{
			
		}
		
	
		add(cards[current]);
		repaint();
		current++;
	}
	 
	public void quitar(int pos)
	{
		
		switch(pos)
		{
		case 0:
			if(cards[0].isShowing()){
			remove(cards[0]);
			add(panel);
			
			}
			break;
		case 1:
			if(cards[1].isShowing())
			{
			remove(cards[1]);
			add(panel2);
			}
			break;
		case 2: 
			if(cards[2].isShowing())
			{
			remove(cards[2]);
			add(panel3);
			}
			break;
		case 3 :
			if(cards[3].isShowing()){
			remove(cards[3]);
			add(panel4);
			}
			break;
			
		case 4:
			if(cards[4].isShowing()){
			remove(cards[4]);
			add(panel5);
			}
		
			break;
				
		}
		current--;
		System.out.println("quit current"+current);
		repaint();
	}
}
